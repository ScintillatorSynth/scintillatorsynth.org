---
date: 2020-10-25
title: "Dev Update and Envelopes"
linkTitle: "Dev Update and Envelopes"
description: "Some discussion of current thinking about Scintillator"
author: Luke Nihlen
---

Things have been very slow going on Scintillator recently, with real life interfering in
[some](https://en.wikipedia.org/wiki/CZU_Lightning_Complex_fires)
[nontrivial](https://newrepublic.com/article/159731/2020-election-sequel-2016) and even
[dangerous](https://twitter.com/ByDonkeys/status/1313830983007973376?s=20) ways. I've also been spending some time
working on Hadron, the JIT drop-in replacement for the sclang binary. Suffice to say, Scintillator has moved from a
monthly release cadence to a "every month there's been any work on the project" release cadence.

Thanks a ton to Nathan Ho for friendly motivation mostly in the form of sharing his own awesome inspirational software
work, but also for checking out Scintillator, making some really cool demo patches with it, and even
[fixing](https://github.com/ScintillatorSynth/Scintillator/pull/173) a bug in the server startup code.

To get back in the swing of Scintillator development I've been thinking some about envelopes again and how they might
plug in to the GPU-driven architecture of Scintillator. I want to design something that feels analogous to the
[Env](http://doc.sccode.org/Classes/Env.html) and [EnvGen](http://doc.sccode.org/Classes/EnvGen.html) classes used in
SuperCollider audio synthesis. It's an imperfect metaphor, however, because of the massive parallelism happening in
video synthesis when compared to audio synthesis.

One way to think about video synthesis in terms of audio synthesis is that each pixel is an independent "channel" of
audio synthesis, updated each frame by the synthesizer to determine the output color of that pixel for that frame. The
EnvGen class allows for modulation of some of its parameters at audio rates, including level scale and bias, and a time
scale. My interpretation of a analogous video class would be that each pixel-rate VGen could modulate the envelope
independently. That means we need an efficient way to compute envelope values independently at each pixel. After some
consideration of alternatives I've decided to use [Tweeny](https://github.com/mobius3/tweeny) to render out the entire
envelope at a fixed time scale, likely the frame rate (subject to some limits for long-running envelopes), then save
that result into an image which can be sampled at draw time by the individual VGens, using bilinear interpolation
between values for envelope curve approximation. There's graphics hardware available for fast bilinear interpolation
between samples, at all shader rates, so my hope is that this will allow for independent envelope calculations with
minimum performance penalty.

The other challenge imposed by this metaphor is the concept of the [Done](http://doc.sccode.org/Classes/Done.html)
action. In audio programming completing an envelope is a common way to trigger a done action, one of choices being to
delete the Synth from the server. This allows the language to fire off a large number of Synth instances to the server
at a high rate, confident that they will be removed from the synth runtime as soon as they have completed their
rendering task.

Running a bunch of pixels through envelopes independently, each with their own modulation on time, implies however that
we would need to poll the "done" state at each pixel to determine if it was in a done state or not. There could also be
different ways to determine "doneness," for example do we stop the overall Scinth when *any* pixel indicates it is done,
or do we check *all* of them, or perhaps only certain pixels? There's some interesting parallels in this consideration
to scatter/gather or [MapReduce](https://en.wikipedia.org/wiki/MapReduce) kind of designs here but reading values back
from pixel shaders and performing additional analysis on them seems like a very heavyweight operation for something that
is intended, at least in part, for the purpose of garbage collection on the server.

To be honest thinking about Done has held me up for a while on envelope design. I thought for a minute about trying to
compute bounds on the possible value of the time scaling modulation on the envelope but intuition suggests that this is
intractable, perhaps connected to the [Halting Problem](https://en.wikipedia.org/wiki/Halting_problem). For now the
decision is to decouple solving the general problem of Done from the envelope work, and to instead add a general purpose
timeout parameter to Scinth objects. This is an optional constant per-Scinth run time value that will cause the server
to free any Scinth that has been running for longer than that value. It's lightweight to compute, and it does not seem
unreasonable for users to estimate a reasonable upper bound for the runtime of their Scinths for the purposes of
automatic garbage collection. It also leaves plenty of room for a more robust and mature VGen-driven Done solution down
the road.

I've been thinking about the concept of a "probe" VGen, something that would allow for copying back selected data from
the GPU for various hard-coded treatments on the CPU, like printing to console, or possible Done computation. There's
also an obvious Done action for video and audio playback, but those are a bit different because the decodes happen on
the CPU anyway, so there's no need to extract some kind of signal from the GPU in that case. I do also plan to add some
kind of "reduction" type parallel algorithms as frame-rate VGens eventually. I had FFT in mind but also things like sum,
mean, standard deviation, and things like AND and OR could also work. This could allow users to "roll their own" much
more complex Done signals, if they so desired, including having a VGen at every pixel write out an individual Done
signal, then analyze that signal on the next frame using one of the reduction operators to determine the value for the
overall Scinth. But that's all much further down the road, and it's not clear is such an advanced use case would be
common enough to require more structured, formal support.

I've started some light implementation work on the video envelopes, although I don't know if I'll be likely to complete
the work by the end of the month, given my current productivity modifiers. I'm also starting to seriously consider
announcing the project more broadly, in the hope to gather more interest and therefore feedback, usage, interest,
engagement, and momentum.


---
date: 2020-03-30
title: "First Post and Dev Update"
linkTitle: "First Post and Dev Update"
description: "A discussion of the Dev Blog and a report on the development status of Scintillator"
author: Luke Nihlen
---

**Scintillator Development Blog**

Welcome to the shiny new Scintillator development blog, powered by [Hugo](https://gohugo.io/), a static site generator,
and the [Docsy](https://www.docsy.dev/) theme. I'll be using this site to post general updates about the state of
Scintillator development as well as to announce point releases of the software.

As Scintillator is an open source project, developed on [GitHub](https://github.com/ScintillatorSynth/Scintillator), it
is my hope that increased communication and visibility will lead to increased engagement with the software. I genuinely
want this software to be a benefit to the SuperCollider user and developer community.

As development continues I have the intention of posting an update here at least monthly, if not more frequently, about
the state of development and progress towards the first alpha release of Scintillator.

**Development Story To Date**

The [initial commit](https://github.com/ScintillatorSynth/Scintillator/commit/22a119411b520460871b2740cb8f67efa0753d5b)
to the [Scintillator Repository](https://github.com/ScintillatorSynth/Scintillator) was 1 March 2019, so just over a
year ago. To begin I followed the [Vulkan Tutorial](https://vulkan-tutorial.com/Introduction) pretty closely, checking
in progress periodically for a bit more than a month, until I got to a place where I was getting some graphical output
from Scintillator but much of the graphics pipeline was still very much hard-coded. I could render single fragment
shader on a triangle and that was about it.

Development stayed dormant for some time while I focused on other projects, but resumed mid-December 2019 after news
that I had been accepted to speak at the SuperCollider Symposium at Wesleyan, scheduled for the last weekend in March
2020. I set a goal to have some kind of presentable demo for the symposium and started in on the mountain of work
remaining to get to Alpha status.

Since resuming development some of the notable projects I've completed are:

 *  [ScinthDef Pipeline Generation](
    https://github.com/ScintillatorSynth/Scintillator/commit/4e3e9831b1a31fa5220473a43feb010a9001e9ee) - moving
    the synth away from hard-coded graphics in favor of on-the-fly compilation of a graphics pipline as specified by the
    ScinthDef mechanism.
 *  [Non-realtime Rendering Support](
    https://github.com/ScintillatorSynth/Scintillator/commit/444c79c61a5e80fe974dab6769338ae554a20fd9) - I made a
    separation between the *realtime* render use case, which is optimized to minimize latency, or the time from when an
    image frame is rendered and when it is shown the user, and *non-realtime* rendering, which is optimized for frame
    throughput, meaning that Scintillator will render frames for recording to still image or video as quickly as
    possible. This also adds support for [SwiftShader](https://github.com/google/swiftshader) rendering, allowing for
    offscreen rendering on devices with no GPU (or even windowing framework, like headless Linux boxes).
 *  [Image Generation Integration Tests](
    https://github.com/ScintillatorSynth/Scintillator/commit/6512a848a3a65f8e50813235342811e574aed3e5) - On each commit
    the [Travis Continuous Integration System](https://travis-ci.com/github/ScintillatorSynth/Scintillator) will now
    build Scintillator and generate a series of test images, compare them to the [reference
    images](https://github.com/ScintillatorSynth/TestGoldImages), and issue a report about if there are differences.
    This requires the non-realtime rendering support to render the screenshots offscreen and save them as images, and
    uses SwiftShader to minimize the chances of hardware differences between floating point implementations creating
    subtle differences between images on different machines.
 *  [Preliminary MacOS Support](
    https://github.com/ScintillatorSynth/Scintillator/commit/ffff4072a68395b7e7476fab779a0dfc5be6521e) - Getting the
    system to compile on MacOS, building an application bundle, and a [followup
    PR](https://github.com/ScintillatorSynth/Scintillator/commit/3ad0965d79639536a6f506d1410212171960611e) that adds
    Travis build and test along with more fit and finish, bringing MacOS to comporable to the level of development that
    the Linux version has reached.
 *  [Support for Image Buffers and Sampling](
    https://github.com/ScintillatorSynth/Scintillator/commit/3c3c42ecd6ee8273bf320eb6828fbdea9b6cd639) - Analogous to
    the [Buffer](https://doc.sccode.org/Classes/Buffer.html) class in SuperCollider, the ImageBuffer class allows for
    reading of a wide variety of image formats, and then rendering those images as part of a Scinth.

**Alpha Status**

Issues and feature requests are [tracked on GitHub](https://github.com/ScintillatorSynth/Scintillator/issues). The
overall project status right now is that Scintillator is in a pre-alpha state, meaning that it is not yet suitable for
release even as an experimental alpha. The purpose of an alpha release, however, is to gather early feedback and
identify major issues with the software. So it is not advisable generally to delay an alpha release indefinitely, but
rather only until enough of the software is in a usable enough state to respect people's time in evaluating and
providing feedback.

There is a [project on GitHub](https://github.com/ScintillatorSynth/Scintillator/projects/1) tracking the work remaining
for an initial alpha release. In brief, [getting this website up and
running](https://github.com/ScintillatorSynth/Scintillator/issues/28), adding more documentation on the [SuperCollider
classes](https://github.com/ScintillatorSynth/Scintillator/issues/4) as well as [building scinsynth from source](expand
and complete scinsynth building instructions), completing the [code signing work on
MacOS](https://github.com/ScintillatorSynth/Scintillator/issues/72), and finalizing the Quark setup are all that remains
between now and alpha-1, the first release of Scintillator.

Once the binaries are published on GitHub and available for download, at least for MacOS, my plan is to publish the
Quark to the master Quark list and send an email to sc-dev and sc-users announcing the general availability of the
Quark, setting expectations about the experimental nature and alpha status of the software, and soliciting feedback. And
then we're off to the races!

**Overall Roadmap**

This is a little bit fuzzy for now, my thinking in general is that I'll gather some feedback from a few rounds of alpha
and then try to assess distance to a public beta.

The difference between alpha and beta to my thinking is around API stability. In alpha software things are still very
much subject to change from release to release. Specifically, sclang and/or C++ synth classes are likely to be moved,
renamed, deleted, or added. It would not be advisable to plan a major piece or performance around Scintillator at this
time, as the entire software is subject to change without notice.

A beta, however, comes with it more of an expectation around stability and support. A beta can be considered a candidate
for a full production release, and so, barring bugfixes, major changes to the software are less likely, as a big change
is more likely to be planned for a subsequent release of the software.

By that thinking, it's hard to predict when the software will be ready for transition to Beta. I think there are a few
substantial features I would like to have in place before considering a Beta, most notably finalizing the initial
concepts around [render groups](https://github.com/ScintillatorSynth/Scintillator/issues/39), adding [audio input and
output](https://github.com/ScintillatorSynth/Scintillator/issues/7), video
[encoding](https://github.com/ScintillatorSynth/Scintillator/issues/10) and
[decoding](https://github.com/ScintillatorSynth/Scintillator/issues/9), and lastly [Microsoft Windows
support](https://github.com/ScintillatorSynth/Scintillator/issues/11). These issues are currently tracked in the [Beta
Release Project](https://github.com/ScintillatorSynth/Scintillator/projects/2), and updates will happen there, with
periodic summation here on the dev blog.

**Next Steps**

If you've read this far thanks for your interest in Scintillator! I'm working hard to finalize the alpha release and
hope to have that up by mid-April at the latest. Check back here or watch the SuperCollider mailing lists for a release
announcement, when the time comes.


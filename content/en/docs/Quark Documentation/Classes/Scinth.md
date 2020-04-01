---
title: Scinth
linkTitle: Scinth
date: 2020-03-31
weight: 5
description: Represents a running synth node on the Scintillator server.
---
A Scinth is the client-side representation of a synth node on the Scintillator server. A Scinth is a single video-producing using, analagous to a Classes/Synth on the audio server. What it does is defined by a Classes/ScinthDef, which specifices which Classes/VGens are used and how they are conncted together.
## Class Methods

### new
Create and return a new Scinth object, and immedately start the corresponding Scinth node on the server.#### Arguments 
##### defName
A Symbol specifying the name of the ScinthDef to use in creating the Scinth.##### args
An optional Classes/Array specifying initial values for the ScinthDef's controls. These are pairs of control name or index and value.##### server
An optional Classes/ScinServer object to play this Scinth on. This argument is analagous to the Synth.new target argument, but since Groups are not yet supported on Scintillator it is only possible to specify which server to send to.A new Scinth object.defNamenodeIDserver### run
Pauses or resumes a running Scinth. Paused Scinths produce no video output.Pausing a running Scinth is one area where the analagous behavior in audio makes for some ambiguity in a video synth. One could imagine two different behaviors for a paused video synth. The first is to continue to produce a still frame while paused, so continuing to render output but just not advancing the Scinth time, so animations would freeze. The other behavior is to stop producing video output entirely. This is what the current synth implementation does, as it seemed like the better fit for the analogy, e.g. audio synths do not reproduce their last frame of audio data when paused. When framebuffer support is added, it could be possible to render a Scinth output to a desired point, save the output in a framebuffer, pause the Scinth, and use the framebuffer as a frozen output. This would be similar to a "freeze" concept in audio, and it might make sense to make a VGen that does exactly that, to ease use.  if this seems like the counterintuitive approach to you.#### Arguments 
##### flag
A boolean. If true, resumes the Scinth if it was paused. If false, pauses the Scinth if it was running.The Scinth object itself, to enable method chaining.### free
Stops the Scinth if running and frees all server-side associated resources.### set
Sets control values on the Scinth.#### Arguments 
##### ... args
A sequence of pairs of control identifiers and values. The first item in the pair is either a Symbol naming a control or an integer control index. The second item in the pair is either a float or integer control value.The Scinth object itself, to enable method chaining.
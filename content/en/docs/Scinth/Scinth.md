---
title: Scinth
linkTitle: Scinth
weight: 5
description: Represents a running synth node on the Scintillator server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> <a href="{{< ref "/docs/Scinth/ScinNode" >}}">ScinNode</a> <a href="{{< ref "/docs/Scinth/ScinGroup" >}}">ScinGroup</a> 



## Description
---



A Scinth is the client-side representation of a synth node on the Scintillator server. A Scinth is a single video-producing using, analagous to a <a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a> on the audio server. What it does is defined by a <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>, which specifices which <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a>s are used and how they are conncted together.



## Class Methods
---



### Scinth.new(defName, args, target, addAction: 'addToTail')



Create and return a new Scinth object, and immedately start the corresponding Scinth node on the server.



#### Arguments

##### defName



A Symbol specifying the name of the ScinthDef to use in creating the Scinth.



##### args



An optional <a href="https://doc.sccode.org/Classes/Array.html">Array <img src="/images/external-link.svg" class="one-liner"></a> specifying initial values for the ScinthDef's controls. These are pairs of control name or index and value.



##### target



A ScinNode to add the new Scinth relative to. If nil, the default group on the default ScinServer will be used.



##### addAction



one of the following Symbols:


<table>


\addToHead



add at the head of the group specified by target, which must be a ScinGroup



\addToTail



(the default) add at the tail of the group specified by target, which must be a ScinGroup



\addAfter



add immediately after target in its server's node order



\addBefore



add immediately before target in its server's node order



\addReplace



replace target and take its place in its server's node order


</table>
{{% alert title="Note" %}}


Unlike the audio server, the default for Scinths is to add them to the <strong>tail</strong> of the target group. This means they will draw last in the group, overwriting (or blending with) anything that was drawn by previous Scinths.

{{% /alert %}}




#### Returns:



A new Scinth object.



## Instance Methods
---



### .run(flag: true)



Pauses or resumes a running Scinth. Paused Scinths produce no video output.



Pausing a running Scinth is one area where the analagous behavior in audio makes for some ambiguity in a video synth. One could imagine two different behaviors for a paused video synth. The first is to continue to produce a still frame while paused, so continuing to render output but just not advancing the Scinth time, so animations would freeze. The other behavior is to stop producing video output entirely. This is what the current synth implementation does, as it seemed like the better fit for the analogy, e.g. audio synths do not reproduce their last frame of audio data when paused. When framebuffer support is added, it could be possible to render a Scinth output to a desired point, save the output in a framebuffer, pause the Scinth, and use the framebuffer as a frozen output. This would be similar to a "freeze" concept in audio, and it might make sense to make a VGen that does exactly that, to ease use. <strong>Feedback welcome</strong> if this seems like the counterintuitive approach to you.



#### Arguments

##### flag



A boolean. If true, resumes the Scinth if it was paused. If false, pauses the Scinth if it was running.





#### Returns:



The Scinth object itself, to enable method chaining.



### .free



Stops the Scinth if running and frees all server-side associated resources.



### .set(... args)



Sets control values on the Scinth.



#### Arguments

##### ... args



A sequence of pairs of control identifiers and values. The first item in the pair is either a Symbol naming a control or an integer control index. The second item in the pair is either a float or integer control value.





#### Returns:



The Scinth object itself, to enable method chaining.



## Examples
---



{{< highlight supercollider >}}
(
~t = ScinthDef.new(\t, { |g, b|
    VRGBOut.pr(VSinOsc.pr(VX.pr(VNormPos.pr)), g, b);
}).add;
)

// Make a new Scinth and run it on the server.
(
~k = Scinth.new(\t, [\g, 0.5, \b, 0.3]);
)

// Change the colors of k.
(
~k.set(\g, 0.2);
)

// Pause k (will produce a black screen)
(
~k.run(false);
)

// Resume k (image is back again)
(
~k.run(true);
)

// Done with k, remove it from the server.
(
~k.free;
)
{{< /highlight >}}






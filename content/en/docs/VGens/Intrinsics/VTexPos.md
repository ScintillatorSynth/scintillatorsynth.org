---
title: VTexPos
linkTitle: VTexPos
weight: 5
description: VGen providing geometry-specific texture coordinates for image sampling.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Intrinsics/VNormPos" >}}">VNormPos</a> <a href="{{< ref "/docs/VGens/Built-In/VFragCoord" >}}">VFragCoord</a> <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> <a href="{{< ref "/docs/VGens/Image Sampling/VSampler" >}}">VSampler</a> 



## Description
---



VTexPos provides a texture coordinate representing the position on the underlying rendered geometry (currently only a full-screen rectangle). Unlike vertex coordinates such as those provided by <a href="{{< ref "/docs/VGens/Intrinsics/VNormPos" >}}">VNormPos</a>, texture coordinates vary from [0, 1] with 0, 0 in the upper-left hand corner. When rendering the full-screen rectangle they can be thought of as <a href="{{< ref "/docs/VGens/Built-In/VFragCoord" >}}">VFragCoord</a> scaled to resolution-independent units. TexPos is most commonly seen as in input to the <a href="{{< ref "/docs/VGens/Image Sampling/VSampler" >}}">VSampler</a> for rendering images.



<strong>Supported Rates: shape, pixel</strong>



## Class Methods
---



### VTexPos.sr



### VTexPos.pr



Make a VTexPos VGen at requested rate.



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

none

</td><td>

2

</td></tr>

</table>


#### Returns:



A texture coordinate usable in a ScinSampler.



## Examples
---



{{< highlight supercollider >}}
// This code snippet starts the server, loads the test image from the Scintillator
// image testing repository, and renders it to the entire quad using the TexPos
// VGen.
(
~o = ScinServerOptions.new;
~o.width = 400;
~o.height = 300;
~v = ScinServer.new(~o).boot;
)

(
~ib = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
)

(
~f = ScinthDef.new(\f, {
    VSampler.pr(~ib, VTexPos.pr);
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/VTexPosA.png" />

{{< highlight supercollider >}}
// Molly doesn't appreciate being rendered in the wrong aspect ratio! It compromises
// some of her remarkable charisma. We need to adjust for two different aspect ratios,
// the aspect ratio of the screen and the aspect ratio of the image.
(
~t.free;
)

(
~f = ScinthDef.new(\f, {
    var screenAspect = ~o.width / ~o.height;
    var imageAspect = ~ib.width / ~ib.height;
    VSampler.pr(~ib,
        VTexPos.pr * VVec2.pr(screenAspect, 1.0) / VVec2.pr(imageAspect, 1.0));
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/VTexPosB.png" />




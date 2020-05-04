---
title: TexPos
linkTitle: TexPos
weight: 5
description: VGen providing geometry-specific texture coordinates for image sampling.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> <a href="{{< ref "/docs/VGens/Built-In/FragCoord" >}}">FragCoord</a> <a href="{{< ref "/docs/Media/ImageBuffer" >}}">ImageBuffer</a> <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> 



## Description
---



TexPos provides a texture coordinate representing the position on the underlying rendered geometry (currently only a full-screen rectangle). Unlike vertex coordinates such as those provided by <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a>, texture coordinates vary from [0, 1] with 0, 0 in the upper-left hand corner. When rendering the full-screen rectangle they can be thought of as <a href="{{< ref "/docs/VGens/Built-In/FragCoord" >}}">FragCoord</a> scaled to resolution-independent units. TexPos is most commonly seen as in input to the <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> for rendering images.



## Class Methods
---



### TexPos.fr



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


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



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
~ib = ImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
)

(
~f = ScinthDef.new(\f, {
    Sampler.fr(~ib, TexPos.fr);
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/TexPosA.png" />

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
    Sampler.fr(~ib,
        TexPos.fr * Vec2.fr(screenAspect, 1.0) / Vec2.fr(imageAspect, 1.0));
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/TexPosB.png" />




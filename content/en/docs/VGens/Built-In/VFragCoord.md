---
title: VFragCoord
linkTitle: VFragCoord
weight: 5
description: Fragment shader pixel coordinates VGen.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Intrinsics/VNormPos" >}}">VNormPos</a> <a href="{{< ref "/docs/VGens/Intrinsics/VTexPos" >}}">VTexPos</a> <a href="{{< ref "/docs/VGens/Image Sampling/VTextureSize" >}}">VTextureSize</a> <a href="{{< ref "/docs/Developer Documentation/Scintillator-Default-Graphics-Setup" >}}">Scintillator Default Graphics Setup</a> 



## Description
---



FragCoord exposes the underlying GLSL primitive <code>gl_FragCoord</code>. It returns the position of the running fragment shader in pixel dimensions, with the origin in the upper left hand corner of the screen. Note that the FragCoord returns screen-relative coordinates, not geometry coordinates.



## Class Methods
---



### VFragCoord.pr



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


## Examples
---



{{< highlight supercollider >}}
(
~o = ScinServerOptions.new;
~v = ScinServer.new(~o).boot;
)

// Note this ScinthDef uses the ScinServerOptions object defined above
// to provide screen width and height as constants to the Scinth.
(
~f = ScinthDef.new(\f, {
    VRGBOut.pr(
        VX.pr(FragCoord.pr) / ~o.width,
        0.0,
        VY.pr(FragCoord.pr) / ~o.height);
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/VFragCoord.png" />




---
title: FragCoord
linkTitle: FragCoord
date: 2020-04-14
weight: 5
description: Fragment shader pixel coordinates VGen.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> <a href="{{< ref "/docs/VGens/Intrinsics/TexPos" >}}">TexPos</a> Reference/Scintillator-Default-Graphics-Setup 



## Description
---



FragCoord exposes the underlying GLSL primitive <code>gl_FragCoord</code>. It returns the position of the running fragment shader in pixel dimensions, with the origin in the upper left hand corner of the screen. Note that the FragCoord returns screen-relative coordinates, not geometry coordinates.



## Class Methods
---



### FragCoord.fg



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
(
~o = ScinServerOptions.new;
~v = ScinServer.new(~o).boot;
)

// Note this ScinthDef uses the ScinServerOptions object defined above
// to provide screen width and height as constants to the Scinth.
(
~f = ScinthDef.new(\f, {
    RGBOut.fg(
        VX.fg(FragCoord.fg) / ~o.width,
        0.0,
        VY.fg(FragCoord.fg) / ~o.height);
}).add;
)

(
~t = Scinth.new(\f);
)
{{< /highlight >}}

<img src="/images/schelp/FragCoordVis.png" />




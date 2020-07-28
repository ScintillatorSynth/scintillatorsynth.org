---
title: VRGBAOut
linkTitle: VRGBAOut
weight: 5
description: Red, green, blue, and alpha color output VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBOut" >}}">VRGBOut</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VBWOut" >}}">VBWOut</a> 



## Description
---



Groups single-channel red, green, blue, and alpha color values into the required 4-dimensional RGBA pixel output color signal.



<strong>Supported Rates: pixel</strong>



## Class Methods
---



### VRGBAOut.pr(red, green, blue, alpha)



Make a VRGBAOut VGen at pixel rate.



#### Arguments

##### red



The red color component value, between 0 and 1.



##### green



The green color component value, between 0 and 1.



##### blue



The blue color component value, between 0 and 1.



##### alpha



The alpha blending component values, between 0 and 1, with 0 meaning completely transparent and 1 meaning completely opaque.





#### Returns:



The vector <code>VVec4.pr(red, green, blue, alpha)</code>



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1, 1

</td><td>

4

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
// Demonstration of alpha blending between two Scinths. First we load two images:
(
~molly = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
~storm = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/storm.png".standardizePath);
)

// Then render the first image in a Scinth:
(
~m = ScinthDef.new(\m, {
    VSampler.pr(~molly, VTexPos.pr);
}).add;
)

(
~p = Scinth.new(\m);
)

// Then use VRGBAOut to render a second image on top, using the flower pattern
// described in the VDot example. Areas inside the flower will render opaque,
// there's a transition border with width controllable by the "bleed" parameter,
// and then areas outside of the flower render with clear alpha, showing the
// image from the first Scinth underneath.
(
~flower = ScinthDef.new(\flower, { |lobes=5, rad=1, bleed=9|
    var pos = VNormPos.pr;
    var posNorm = VNorm.pr(pos);
    var xaxis = VVec2.pr(1.0, 0.0);
    var theta = VDot.pr(posNorm, xaxis).acos;
    var r = abs(sin(theta * lobes)) * rad;
    var storm = VSampler.pr(~storm, VTexPos.pr);
    VRGBAOut.pr(VX.pr(storm), VY.pr(storm), VZ.pr(storm),
        1.0 - VClamp.pr(bleed * (VLength.pr(pos) - r), 0.0, 1.0));
}).add;
)

(
~k = Scinth.new(\flower);
)
{{< /highlight >}}

<img src="/images/schelp/VRGBAOut.png" />




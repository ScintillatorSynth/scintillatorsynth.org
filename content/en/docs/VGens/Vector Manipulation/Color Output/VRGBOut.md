---
title: VRGBOut
linkTitle: VRGBOut
weight: 5
description: Opaque red, green, blue color output VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBAOut" >}}">VRGBAOut</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VBWOut" >}}">VBWOut</a> 



## Description
---



Groups single-channel red, green, and blue color values into the required 4-dimensional RGBA pixel output color signal, with a constant of 1.0 for the opacity.



<strong>Supported Rates: pixel</strong>



## Class Methods
---



### VRGBOut.pr(red, green, blue)



Make a VRGBOut VGen at requested rate.



#### Arguments

##### red



The red color component value, between 0 and 1.



##### green



The green color component value, between 0 and 1.



##### blue



The blue color component value, between 0 and 1.





#### Returns:



The vector <code>VVec4.pr(red, green, blue, 1.0)</code>



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

4

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
// Building off VCross example for triangle-within-point
// detection, this example attempts to recreate the first
// image rendered by Scintillator of a RGB triangle.
(
~rgbTri = ScinthDef.new(\rgbTri, { |edgeLength = 2.0|
    // sqrt(3) / 2
    var height = 0.866025404 * edgeLength;
    var a = VVec3.pr(-0.5 * height, 0.5 * height, 0);
    var b = VVec3.pr(0.0, -0.5 * height, 0);
    var c = VVec3.pr(0.5 * height, 0.5 * height, 0);
    var ab = b - a;
    var ca = a - c;
    var bc = c - b;

    var pos = VNormPos.pr;
    var p = VVec3.pr(VX.pr(pos), VY.pr(pos), 0.0);
    var mask =
    VClamp.pr(VZ.pr(VCross.pr(ab, p - a)).sign, 0.0, 1.0) *
    VClamp.pr(VZ.pr(VCross.pr(ca, p - c)).sign, 0.0, 1.0) *
    VClamp.pr(VZ.pr(VCross.pr(bc, p - b)).sign, 0.0, 1.0);
    mask * VRGBOut.pr(
        1 - VDistance.pr(p, a),
        1 - VDistance.pr(p, b),
        1 - VDistance.pr(p, c));
}).add;
)

(
~rgb = Scinth.new(\rgbTri);
)
{{< /highlight >}}

<img src="/images/schelp/VRGBOut.png" />




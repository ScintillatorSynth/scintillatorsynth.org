---
title: VBWOut
linkTitle: VBWOut
weight: 5
description: Opaque black and white output VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBAOut" >}}">VRGBAOut</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBOut" >}}">VRGBOut</a> 



## Description
---



Provides a single value for all three color channels, resulting in a black-and-white, opaque color output signal.



<strong>Supported Rates: pixel</strong>



## Class Methods
---



### VBWOut.pr(value)



Make a VBWOut VGen at requested rate.



#### Arguments

##### value



The black and white value, with 0.0 representing black and 1.0 representing white.





#### Returns:



The vector <code>VVec4.pr(value, value, value, 1.0)</code>



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1

</td><td>

4

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
(
~blades = ScinthDef.new(\blades, { |scale=2.0, xmod=0.5, ymod=0.5|
    var pos = VNormPos.pr() * scale;
    var sawA = VSaw.pr(0.7, (VX.pr(pos) % xmod) * VY.pr(pos));
    var sawB = VSaw.pr(0.9, VX.pr(pos) * (VY.pr(pos) % ymod));
    VBWOut.pr(sawA * sawB);
}).add;
)
(
~k = Scinth.new(\blades);
)
{{< /highlight >}}

<img src="/images/schelp/VBWOut.png" />

{{< highlight supercollider >}}
(
~molly = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
)

(
~bwMolly = ScinthDef.new(\bwMolly, {
    var size = VTextureSize.fr(~molly);
    var aspect = VVec2.fr(VY.fr(size) / VX.fr(size), 1.0);
    var colors = VSampler.pr(~molly, VTexPos.pr * aspect);
    VBWOut.pr(VLength.pr(VVec3.pr(VX.pr(colors), VY.pr(colors), VZ.pr(colors))));
}).add;
)

(
~t = Scinth.new(\bwMolly);
)
{{< /highlight >}}

<img src="/images/schelp/VBWMolly.png" />




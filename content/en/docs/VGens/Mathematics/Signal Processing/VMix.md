---
title: VMix
linkTitle: VMix
weight: 5
description: Piecewise vector blending VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Signal Processing/VClamp" >}}">VClamp</a> <a href="{{< ref "/docs/VGens/Mathematics/Signal Processing/VStep" >}}">VStep</a> 



## Description
---



Similar to the binary operator <code>blend</code>, returns a linear mix of <code>u, v</code> with <code>a</code> between <code>[0, 1]</code>. Supports piecewise blend or a single blend argument to apply to all components



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VMix.fr(u, v, a)



### VMix.sr(u, v, a)



### VMix.pr(u, v, a)



Make a VMix VGen at requested rate.



#### Arguments

##### u



The first vector input to blend.



##### v



The second vector input to blend.



##### a



The blend factor. If a is a vector then each component of a will be used as the blend factor for the corresponding components in u and v. If a is a single number then it will apply that blend factor to all components of u and v.





#### Returns:



If 0, returns u. If 1 returns v. Generally returns <code>(u * (1 - a)) + (v * a)</code>



<strong>Dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2, 1

</td><td>

2

</td></tr>
<tr><td>

2, 2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3, 1

</td><td>

3

</td></tr>
<tr><td>

3, 3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4, 1

</td><td>

4

</td></tr>
<tr><td>

4, 4, 4

</td><td>

4

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
// Vector mix demo shows blending of two textures from left to right. On the left
// the x component of the texture position is zero, so VX.pr(VTexPos.pr) = 0.
// On the right it is 1. So on the left the VMix will return entirely the colors
// from the image of Molly on the couch, and on the right the colors come entirely
// from the image of Storm on the kitchen floor.
(
~v = ScinServer.new;
~v.options.width = 400;
~v.options.height = 300;
~v.boot;
)

(
~molly = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
~storm = ScinImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/storm.png".standardizePath);
)

(
~f = ScinthDef.new(\mix, {
    var m = VSampler.pr(~molly, VTexPos.pr);
    var s = VSampler.pr(~storm, VTexPos.pr);
    VMix.pr(m, s, VX.pr(VTexPos.pr));
}).add;
)

(
~t = Scinth.new(\mix);
)
{{< /highlight >}}

<img src="/images/schelp/VMix.png" />




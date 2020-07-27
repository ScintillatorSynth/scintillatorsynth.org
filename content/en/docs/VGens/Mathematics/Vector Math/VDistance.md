---
title: VDistance
linkTitle: VDistance
weight: 5
description: Computes the distance between two vectors
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/VLength" >}}">VLength</a> 



## Description
---



VDistance computes the distance between two vectors by calculating the length of their difference, essentially <code>VLength.pr(v - u)</code>.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VDistance.fr(u, v)



### VDistance.sr(u, v)



### VDistance.pr(u, v)



Make a VDistance VGen at the requested rate



#### Arguments

##### u



First vector input for distance computation.



##### v



Second vector input for distance computation.





#### Returns:



The distance between u and v.



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

1

</td></tr>
<tr><td>

3, 3

</td><td>

1

</td></tr>
<tr><td>

4, 4

</td><td>

1

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
(
// This example uses the Distance operator to plot the distance at each pixel to the
// moving point on three different Lissajous curves, one in each color channel.
// The dot parameter controls the size of the dot around each curve, by scaling how
// quickly the dot will drive the length to the clamp values as distance from each
// Lissajous point increases.
~k = ScinthDef.new(\k, { |dot = 0.5|
    var rx = VSinOsc.fr(freq: 1.5, mul: 0.9, add: 0.0);
    var ry = VSinOsc.fr(freq: 1, phase: pi / 4, mul: 0.9, add: 0.0);
    var gx = VSinOsc.fr(freq: 0.5, phase: pi / 2, mul: 0.9, add: 0.0);
    var gy = VSinOsc.fr(freq: 1.25, mul: 1.0, add: 0.0);
    var bx = VSinOsc.fr(freq: 0.75, phase: pi / 3, mul: 0.9, add: 0.0);
    var by = VSinOsc.fr(phase: 3 * pi / 4, mul: 0.9, add: 0.0);
    var pos = NormPos.fr;
    var r = Clamp.fr(1.0 - (Distance.fr(Vec2.fr(rx, ry), pos) / dot), 0.0, 1.0);
    var g = Clamp.fr(1.0 - (Distance.fr(Vec2.fr(gx, gy), pos) / dot), 0.0, 1.0);
    var b = Clamp.fr(1.0 - (Distance.fr(Vec2.fr(bx, by), pos) / dot), 0.0, 1.0);
    RGBOut.fr(r, g, b);
}).add;
)

(
~t = Scinth.new(\k);
)
{{< /highlight >}}

<img src="/images/schelp/VDistance.png" />
---
title: Distance
linkTitle: Distance
date: 2020-04-14
weight: 5
description: Computes the distance between two vectors
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a> 



## Description
---



Distance computes the distance between two vectors by calculating the length of their difference. It works on input dimensions 1-4.



## Class Methods
---



### Distance.fg(x, y)



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


#### Arguments

##### x



First vector input for distance computation.



##### y



Second vector input for distance computation.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



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
    var rx = ScinOsc.fg(freq: 1.5, mul: 0.9, add: 0.0);
    var ry = ScinOsc.fg(freq: 1, phase: pi / 4, mul: 0.9, add: 0.0);
    var gx = ScinOsc.fg(freq: 0.5, phase: pi / 2, mul: 0.9, add: 0.0);
    var gy = ScinOsc.fg(freq: 1.25, mul: 1.0, add: 0.0);
    var bx = ScinOsc.fg(freq: 0.75, phase: pi / 3, mul: 0.9, add: 0.0);
    var by = ScinOsc.fg(phase: 3 * pi / 4, mul: 0.9, add: 0.0);
    var pos = NormPos.fg;
    var r = Clamp.fg(1.0 - (Distance.fg(Vec2.fg(rx, ry), pos) / dot), 0.0, 1.0);
    var g = Clamp.fg(1.0 - (Distance.fg(Vec2.fg(gx, gy), pos) / dot), 0.0, 1.0);
    var b = Clamp.fg(1.0 - (Distance.fg(Vec2.fg(bx, by), pos) / dot), 0.0, 1.0);
    RGBOut.fg(r, g, b);
}).add;
)

(
~t = Scinth.new(\k);
)
{{< /highlight >}}

<img src="/images/schelp/Distance.png" />
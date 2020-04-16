---
title: Length
linkTitle: Length
date: 2020-04-14
weight: 5
description: Computes the length of the provided vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Distance" >}}">Distance</a> 



## Description
---



Length computes the length of the provided vector as the square root of the sum of the squares of its components. It works on input dimensions 1-4. In the 1D case it returns the absolute value of the input.



## Class Methods
---



### Length.fg(vec)



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

1

</td></tr>
<tr><td>

2

</td><td>

1

</td></tr>
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>


#### Arguments

##### vec



The input vector.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
(
// This demo shows a graph of the magnetic flux density around a
// dipole magnet pointing along the y axis. It is a 3D radial
// function and uses the Length operator to compute the radius
// at each pixel, which is an input to the function (see
// https://en.wikipedia.org/wiki/Magnetic_dipole), as well as
// the magnitude of the magnetic flux vector at each point,
// to generate the black and white image.
~k = ScinthDef.new(\k, {
    var pos = NormPos.fg;
    var r = Vec3.fg(VX.fg(pos), VY.fg(pos), 0.0);
    var magR = Length.fg(r);
    var m = Vec3.fg(0.0, 0.1, 0.0);
    var flux = Cross.fg(m, r) / magR.cubed;
    BWOut.fg(Clamp.fg(Length.fg(flux), 0.0, 1.0));
}).add;
)

(
~t = Scinth.new(\k);
)
{{< /highlight >}}

<img src="/images/schelp/Length.png" />
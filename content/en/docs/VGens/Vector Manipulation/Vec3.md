---
title: Vec3
linkTitle: Vec3
date: 2020-04-14
weight: 5
description: Pack three single-dimensional signals into a 3D signal.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a>, Vec3 packs single-dimensional signals into a single signal, in this case a 3-dimensional signal.



## Class Methods
---



### Vec3.fg(x: 0.0, y: 0.0, z: 0.0)



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

3

</td></tr>

</table>


#### Arguments

##### x



The first component in the resulting signal.



##### y



The second component in the resulting signal.



##### z



The third component in the resulting signal.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Vec3.fg(1, 2, 3);
var x = VX.fg(v); // x: 1
var y = VY.fg(v); // y: 2
var z = VZ.fg(v); // z: 3
{{< /highlight >}}






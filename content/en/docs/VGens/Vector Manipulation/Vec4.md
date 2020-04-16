---
title: Vec4
linkTitle: Vec4
date: 2020-04-15
weight: 5
description: Pack four single-dimensional signals into a 4D signal.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a>, Vec4 packs single-dimensional signals into a single signal, in this case a 4-dimensional signal.



## Class Methods
---



### Vec4.fg(x: 0.0, y: 0.0, z: 0.0, w: 0.0)



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


#### Arguments

##### x



The first component in the resulting signal.



##### y



The second component in the resulting signal.



##### z



The third component in the resulting signal.



##### w



The fourth component in the resulting signal.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Vec4.fg(1, 2, 3, 4);
var x = VX.fg(v); // x: 1
var y = VY.fg(v); // y: 2
var z = VZ.fg(v); // z: 3
var w = VW.fg(v); // w: 4
{{< /highlight >}}






---
title: Vec2
linkTitle: Vec2
weight: 5
description: Pack two single-dimensional signals into a 2D signal.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a>, Vec2 packs single-dimensional signals into a single signal, in this case a 2-dimensional signal.



## Class Methods
---



### Vec2.fr(x: 0.0, y: 0.0)



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

2

</td></tr>

</table>


#### Arguments

##### x



The first component in the resulting signal.



##### y



The second component in the resulting signal.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Vec3.fr(1, 2);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 2
{{< /highlight >}}






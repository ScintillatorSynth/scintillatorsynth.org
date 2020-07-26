---
title: VVec3
linkTitle: VVec3
weight: 5
description: Pack three single-dimensional signals into a 3D signal.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec4" >}}">VVec4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec2" >}}">VVec2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec4" >}}">VVec4</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec2" >}}">VVec2</a>, VVec3 packs single-dimensional signals into a single signal, in this case a 3-dimensional signal.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VVec3.fr(x: 0.0, y: 0.0, z: 0.0)



### VVec3.sr(x: 0.0, y: 0.0, z: 0.0)



### VVec3.pr(x: 0.0, y: 0.0, z: 0.0)



Make a VVec3 VGen at requested rate.



#### Arguments

##### x



The first component in the resulting signal.



##### y



The second component in the resulting signal.



##### z



The third component in the resulting signal.





#### Returns:



A 3D vector composed of (x, y, z).



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


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = VVec3.fr(1, 2, 3);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 2
var z = VZ.fr(v); // z: 3
{{< /highlight >}}






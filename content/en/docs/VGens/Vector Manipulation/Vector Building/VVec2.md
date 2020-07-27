---
title: VVec2
linkTitle: VVec2
weight: 5
description: Pack two single-dimensional signals into a 2D signal.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec4" >}}">VVec4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec3" >}}">VVec3</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec4" >}}">VVec4</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec3" >}}">VVec3</a>, VVec2 packs single-dimensional signals into a single signal, in this case a 2-dimensional signal.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VVec2.fr(x: 0.0, y: 0.0)



### VVec2.sr(x: 0.0, y: 0.0)



### VVec2.pr(x: 0.0, y: 0.0)



Make a VVec2 VGen at requested rate.



#### Arguments

##### x



The first component in the resulting signal.



##### y



The second component in the resulting signal.





#### Returns:



A 2D vector composed of (x, y).



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


## Examples
---



{{< highlight supercollider >}}
var v = VVec3.fr(1, 2);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 2
{{< /highlight >}}






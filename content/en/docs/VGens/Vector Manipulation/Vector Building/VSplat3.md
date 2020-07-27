---
title: VSplat3
linkTitle: VSplat3
weight: 5
description: Copy a single value into a 3D vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat4" >}}">VSplat4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat2" >}}">VSplat2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat2" >}}">VSplat2</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat4" >}}">VSplat4</a>, copies a single-dimensional value into each part of multidimensional signal, in this case a 3D signal.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VSplat3.fr(x)



### VSplat3.sr(x)



### VSplat3.pr(x)



Make a VSplat3 VGen at requested rate.



#### Arguments

##### x



The value to copy into all three channels.





#### Returns:



A 3D vector composed of (x, x, x).



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

3

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
var v = VSplat3.fr(1.0);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 1
var z = VZ.fr(v); // z: 1
{{< /highlight >}}






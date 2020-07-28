---
title: VSplat2
linkTitle: VSplat2
weight: 5
description: Copy a single value into a 2-D vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat4" >}}">VSplat4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat3" >}}">VSplat3</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat3" >}}">VSplat3</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat4" >}}">VSplat4</a>, copies a single-dimensional value into each part of multidimensional signal, in this case a 2D signal.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VSplat2.fr(x)



### VSplat2.sr(x)



### VSplat2.pr(x)



Make a VSplat2 VGen at requested rate.



#### Arguments

##### x



The value to copy into both channels.





#### Returns:



A 2D vector composed of (x, x).



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

2

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
var v = VSplat2.fr(1.0);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 1
{{< /highlight >}}






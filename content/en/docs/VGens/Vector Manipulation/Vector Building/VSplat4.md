---
title: VSplat4
linkTitle: VSplat4
weight: 5
description: Copy a single value into a 4D vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat3" >}}">VSplat3</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat2" >}}">VSplat2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat2" >}}">VSplat2</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VSplat3" >}}">VSplat3</a>, copies a single-dimensional value into each part of multidimensional signal, in this case a 4D signal.



## Class Methods
---



### VSplat4.fr(x)



### VSplat4.sr(x)



### VSplat4.pr(x)



Make a VSplat3 VGen at requested rate.



#### Arguments

##### x



The value to copy into all four channels.





#### Returns:



A 4D vector composed of (x, x, x, x).



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

4

</td></tr>

</table>


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = VSplat4.fr(1.0);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 1
var z = VZ.fr(v); // z: 1
var w = VW.fr(v); // w: 1
{{< /highlight >}}






---
title: Splat4
linkTitle: Splat4
weight: 5
description: Copy a single value into a 4-D vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat3" >}}">Splat3</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat2" >}}">Splat2</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat2" >}}">Splat2</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat3" >}}">Splat3</a>, copies a single-dimensional value into each part of multidimensional signal, in this case a 4-D signal.



## Class Methods
---



### Splat4.fg(x)



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


#### Arguments

##### x



The value to copy into all four channels.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Splat4.fg(1.0);
var x = VX.fg(v); // x: 1
var y = VY.fg(v); // y: 1
var z = VZ.fg(v); // z: 1
var w = VW.fg(v); // w: 1
{{< /highlight >}}






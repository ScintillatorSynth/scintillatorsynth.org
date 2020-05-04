---
title: Splat2
linkTitle: Splat2
weight: 5
description: Copy a single value into a 2-D vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat4" >}}">Splat4</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat3" >}}">Splat3</a> 



## Description
---



Like its partner classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat3" >}}">Splat3</a> and <a href="{{< ref "/docs/VGens/Vector Manipulation/Splat4" >}}">Splat4</a>, copies a single-dimensional value into each part of multidimensional signal, in this case a 2-D signal.



## Class Methods
---



### Splat2.fr(x)



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


#### Arguments

##### x



The value to copy into all three channels.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Splat2.fr(1.0);
var x = VX.fr(v); // x: 1
var y = VY.fr(v); // y: 1
{{< /highlight >}}






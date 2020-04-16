---
title: VW
linkTitle: VW
date: 2020-04-14
weight: 5
description: Extract the fourth single-dimensional element from a vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/VY" >}}">VY</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a> 



## Description
---



Like its sibling classes <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VY" >}}">VY</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a>, access a particular single-dimensional element of the provided vector, in this case the fourth element, commonly called <code>w</code> in computer graphics parlance.



## Class Methods
---



### VW.fg(vec)



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>


#### Arguments

##### vec



The vector to extract the fourth element from.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Vec4.fg(1, 2, 3, 4);
var w = VW.fg(v); // w: 4
{{< /highlight >}}






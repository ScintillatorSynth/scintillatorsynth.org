---
title: VY
linkTitle: VY
date: 2020-04-15
weight: 5
description: Extract the second single-dimensional element from a vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/VW" >}}">VW</a> 



## Description
---



Like its sibling classes <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VW" >}}">VW</a>, access a particular single-dimensional element of the provided vector, in this case the second element, commonly called <code>y</code> in computer graphics parlance.



## Class Methods
---



### VY.fg(vec)



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
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

2

</td><td>

1

</td></tr>

</table>


#### Arguments

##### vec



The vector to extract the second element from.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = Vec4.fg(1, 2, 3, 4);
var y = VY.fg(v); // y: 2
{{< /highlight >}}






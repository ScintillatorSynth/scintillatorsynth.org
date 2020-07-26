---
title: VY
linkTitle: VY
weight: 5
description: Extract the second single-dimensional element from a vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VZ" >}}">VZ</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VW" >}}">VW</a> 



## Description
---



Like its sibling classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VZ" >}}">VZ</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VW" >}}">VW</a>, access a particular single-dimensional element of the provided vector, in this case the second element, commonly called <code>y</code> in computer graphics parlance.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VY.fr(vec)



### VY.sr(vec)



### VY.pr(vec)



Make a VY VGen at requested rate.



#### Arguments

##### vec



The vector to extract the second element from.





#### Returns:



The second element in the vector.



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


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
var v = VVec4.fr(1, 2, 3, 4);
var y = VY.fr(v); // y: 2
{{< /highlight >}}






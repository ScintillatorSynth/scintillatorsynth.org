---
title: VZ
linkTitle: VZ
weight: 5
description: Extract the third single-dimensional element from a vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VY" >}}">VY</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VW" >}}">VW</a> 



## Description
---



Like its sibling classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VY" >}}">VY</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VW" >}}">VW</a>, access a particular single-dimensional element of the provided vector, in this case the third element, commonly called <code>z</code> in computer graphics parlance.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VZ.fr(vec)



### VZ.sr(vec)



### VZ.pr(vec)



Make a VZ VGen at requested rate.



#### Arguments

##### vec



The vector to extract the third element from.





#### Returns:



The third element in the vector.



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

</table>


## Examples
---



{{< highlight supercollider >}}
var v = VVec4.fr(1, 2, 3, 4);
var z = VZ.fr(v); // z: 3
{{< /highlight >}}






---
title: VW
linkTitle: VW
weight: 5
description: Extract the fourth single-dimensional element from a vector
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VY" >}}">VY</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VZ" >}}">VZ</a> 



## Description
---



Like its sibling classes <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VY" >}}">VY</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Element Access/VZ" >}}">VZ</a>, access a particular single-dimensional element of the provided vector, in this case the fourth element, commonly called <code>w</code> in computer graphics parlance.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VW.fr(vec)



### VW.sr(vec)



### VW.pr(vec)



Make a VW VGen at requested rate.



#### Arguments

##### vec



The vector to extract the fourth element from.





#### Returns:



The fourth element in the vector.



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


## Examples
---



{{< highlight supercollider >}}
var v = VVec4.fr(1, 2, 3, 4);
var w = VW.fr(v); // w: 4
{{< /highlight >}}






---
title: VDot
linkTitle: VDot
weight: 5
description: VGen to compute vector dot product
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/VCross" >}}">VCross</a> 



## Description
---



Computes the <a href="https://en.wikipedia.org/wiki/Dot_product">dot product <img src="/images/external-link.svg" class="one-liner"></a> of two vectors <em>u</em> and <em>v</em>.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VDot.fr(u, v)



### VDot.sr(u, v)



### VDot.pr(u, v)



Make a VDot VGen at requested rate.



#### Arguments

##### u



First vector input to cross product.



##### v



Second vector input to cross product.





#### Returns:



The dot product of <em>u</em> and <em>v</em>



<strong>Dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

1

</td></tr>
<tr><td>

3, 3

</td><td>

1

</td></tr>
<tr><td>

4, 4

</td><td>

1

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
(TODO)
{{< /highlight >}}


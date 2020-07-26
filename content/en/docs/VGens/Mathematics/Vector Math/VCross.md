---
title: VCross
linkTitle: VCross
weight: 5
description: VGen to compute vector cross product
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/VDot" >}}">VDot</a> 



## Description
---



Computes the <a href="https://en.wikipedia.org/wiki/Cross_product">cross product <img src="/images/external-link.svg" class="one-liner"></a> of two three dimensional vectors <em>u</em> and <em>v</em>.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VCross.fr(u, v)



### VCross.sr(u, v)



### VCross.pr(u, v)



Make a VCross VGen at requested rate.



#### Arguments

##### u



First vector input to cross product.



##### v



Second vector input to cross product.





#### Returns:



The cross product of <em>u</em> and <em>v</em>



<strong>Dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

3, 3

</td><td>

3

</td></tr>

</table>


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
(TODO)
{{< /highlight >}}


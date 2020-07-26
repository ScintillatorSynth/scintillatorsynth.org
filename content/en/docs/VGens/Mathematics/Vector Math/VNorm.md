---
title: VNorm
linkTitle: VNorm
weight: 5
description: Parallel vector with unit length VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/VLength" >}}">VLength</a> 



## Description
---



VNorm computes a <em>normalized</em> vector parallel to <code>v</code> with length <code>1</code>. For scalar values this is equivalent to the VSign VGen, returning -1 for all values < 0 and +1 for all values > 0.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VNorm.fr(v)



### VNorm.sr(v)



### VNorm.pr(v)



Make a VNorm VGen at requested rate.



#### Arguments

##### v



The input vector.





#### Returns:



A vector parallel to v but with unit length.



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

1

</td></tr>
<tr><td>

2

</td><td>

2

</td></tr>
<tr><td>

3

</td><td>

3

</td></tr>
<tr><td>

4

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
(
(TODO)
)
{{< /highlight >}}






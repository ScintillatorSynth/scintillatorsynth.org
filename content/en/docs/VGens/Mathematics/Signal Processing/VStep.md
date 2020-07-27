---
title: VStep
linkTitle: VStep
weight: 5
description: Dynamic threshold VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Mathematics/Signal Processing/VClamp" >}}">VClamp</a> 



## Description
---



Just like the binary operator <code>thresh</code>, returns <code>0</code> when <code>v < step</code>, otherwise <code>v</code>. Higher-dimensional versions of VStep do the comparison piecewise, meaning each component of v will be compaired against the same component of step.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VStep.fr(step, v)



### VStep.sr(step, v)



### VStep.pr(step, v)



Make a VStep VGen at requested rate.



#### Arguments

##### step



The minimum value v must exceed for the VGen to return v instead of 0.



##### v



The input value to compare against step.





#### Returns:



For each component of v, <code>0</code> when <code>v < step</code>, otherwise <code>v</code>



<strong>Dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2, 2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3, 3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4, 4, 4

</td><td>

4

</td></tr>

</table>


## Examples
---



{{< highlight supercollider >}}
(TODO)
{{< /highlight >}}


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



VNorm computes a <em>normalized</em> vector parallel to <code>v</code> with length <code>1</code>. For scalar values this is equivalent to the <code>sign</code> operation, returning -1 for all values < 0, 0 for 0, and +1 for all values > 0.



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


## Examples
---



{{< highlight supercollider >}}
// This example draws the unit circle by using VStep to only draw white
// when the distance between the position and the normalized position is
// within 0.1.
(
~normDist = ScinthDef.new(\normDist, {
    var pos = VNormPos.pr;
    VBWOut.pr(VStep.pr(0.9, 1.0 - VDistance.pr(pos, VNorm.pr(pos))));
}).add;
)

(
~k = Scinth.new(\normDist);
)
{{< /highlight >}}

<img src="/images/schelp/VNorm.png" />




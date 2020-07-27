---
title: VClamp
linkTitle: VClamp
weight: 5
description: Video oscillator to constrain a value between two parameters
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="https://doc.sccode.org/Classes/Clip.html">Clip <img src="/images/external-link.svg" class="one-liner"></a> <a href="{{< ref "/docs/VGens/Mathematics/Signal Processing/VStep" >}}">VStep</a> 



## Description
---



Analogous to the audio <a href="https://doc.sccode.org/Classes/Clip.html">Clip <img src="/images/external-link.svg" class="one-liner"></a> class. Returns the input <em>x</em> if min < v < max, <em>min</em> if v < min, <em>max</em> if v > max.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VClamp.fr(v, min, max)



### VClamp.sr(v, min, max)



### VClamp.pr(v, min, max)



Make a VClamp VGen at requested rate.



#### Arguments

##### v



Input value to clamp.



##### min



Minimum value to clamp v to.



##### max



Maximum value to clamp v to.





#### Returns:



<em>x</em> if min < v < max, <em>min</em> if v < min, <em>max</em> if v > max.



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


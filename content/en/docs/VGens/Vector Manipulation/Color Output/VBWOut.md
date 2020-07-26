---
title: VBWOut
linkTitle: VBWOut
weight: 5
description: Opaque black and white output VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBAOut" >}}">VRGBAOut</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBOut" >}}">VRGBOut</a> 



## Description
---



Provides a single value for all three color channels, resulting in a black-and-white, opaque color output signal.



<strong>Supported Rates: pixel</strong>



## Class Methods
---



### VBWOut.pr(value)



Make a VBWOut VGen at requested rate.



#### Arguments

##### value



The black and white value, with 0.0 representing black and 1.0 representing white.





#### Returns:



The vector <code>VVec4.pr(value, value, value, 1.0)</code>



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






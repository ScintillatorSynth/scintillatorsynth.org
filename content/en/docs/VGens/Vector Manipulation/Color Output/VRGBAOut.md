---
title: VRGBAOut
linkTitle: VRGBAOut
weight: 5
description: Red, green, blue, and alpha color output VGen
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VRGBOut" >}}">VRGBOut</a> <a href="{{< ref "/docs/VGens/Vector Manipulation/Color Output/VBWOut" >}}">VBWOut</a> 



## Description
---



Groups single-channel red, green, blue, and alpha color values into the required 4-dimensional RGBA pixel output color signal.



<strong>Supported Rates: pixel</strong>



## Class Methods
---



### .pr



Make a VRGBAOut VGen at requested rate.



#### Arguments

##### red



The red color component value, between 0 and 1.



##### green



The green color component value, between 0 and 1.



##### blue



The blue color component value, between 0 and 1.



##### alpha



The alpha blending component values, between 0 and 1, with 0 meaning completely transparent and 1 meaning completely opaque.





#### Returns:



The vector <code>VVec4.pr(red, green, blue, alpha)</code>



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1, 1

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






---
title: VSampler
linkTitle: VSampler
weight: 5
description: Represents a server-side graphics memory region for sampling static images.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> <a href="{{< ref "/docs/VGens/Intrinsics/VTexPos" >}}">VTexPos</a> <a href="{{< ref "/docs/VGens/Image Sampling/VTextureSize" >}}">VTextureSize</a> 



## Description
---



VSampler is the fundamental image sampling VGen in Scintillator. It takes a <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> id and a position as inputs and produces a <a href="{{< ref "/docs/VGens/Vector Manipulation/Vector Building/VVec4" >}}">VVec4</a> color output in <code>(red, green, blue, alpha)</code> order. VSamplers can be configured to sample the image buffer in a variety of ways which are documented below.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VSampler.fr(image, pos)



### VSampler.sr(image, pos)



### VSampler.pr(image, pos)



Make a VSampler VGen at the requested rate.



#### Arguments

##### image



The <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> object to sample or a server-issued image ID integer.



##### pos



A 2D vector to sample the input image at using the provided filter mode.





#### Returns:



The sampled color value of image at pos.



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

image, 2

</td><td>

4

</td></tr>

</table>


#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods


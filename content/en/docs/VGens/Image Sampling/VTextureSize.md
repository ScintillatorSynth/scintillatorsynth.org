---
title: VTextureSize
linkTitle: VTextureSize
weight: 5
description: VGen providing sampler image dimensions in pixels.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Image Sampling/VSampler" >}}">VSampler</a> <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> 



## Description
---



Loosely analogous to the <a href="https://doc.sccode.org/Classes/BufFrames.html">BufFrames <img src="/images/external-link.svg" class="one-liner"></a> UGen, this VGen provides a 2D vector containing the width and height of the sampled texture. This can be useful, for example, for adjusting the width and height of image sampling to respect aspect ratio.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VTextureSize.fr(image)



### VTextureSize.sr(image)



### VTextureSize.pr(image)



Make a VTextureSize VGen at the requested rate.



#### Arguments

##### image



The <a href="{{< ref "/docs/Media/ScinImageBuffer" >}}">ScinImageBuffer</a> object to return the dimensions of.





#### Returns:



A 2D vector containing the dimensions of the supplied image in (width, height) order.



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

image

</td><td>

2

</td></tr>

</table>

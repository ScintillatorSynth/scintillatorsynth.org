---
title: VNormPos
linkTitle: VNormPos
weight: 5
description: VGen for image size invariant fragment positions.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Built-In/VFragCoord" >}}">VFragCoord</a> <a href="{{< ref "/docs/VGens/Intrinsics/VTexPos" >}}">VTexPos</a> <a href="{{< ref "/docs/Developer Documentation/Scintillator-Default-Graphics-Setup" >}}">Scintillator Default Graphics Setup</a> 



## Description
---



VNormPos provides a 2D fragment screen position, adjusted for screen width and height and normalized to [-1, +1] in the shortest dimension, to ensure a square aspect ratio and full range of coordinates in the shortest pixel dimension. This example window is created with the default width of 800 and height of 600, so is therefore wider than it is tall. To adjust for this NormPos varies from -1 to +1 in the vertical axis but from -1.333 to +1.333 in the horizontal axis.

<img src="/images/schelp/VNormPosLayout.png" />

<strong>Supported Rates: shape, pixel</strong>



## Class Methods
---



### VNormPos.sr



### VNormPos.pr



Make a VNormPos VGen at requested rate.



#### Returns:



The position of the fragment or vertex in normalized space.



<strong>dimensions</strong>


<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

none

</td><td>

2

</td></tr>

</table>


## Examples
---



Because the example window is wider than it is tall, the circle generated is just touching the top and bottom of the frame and is centered in the horizontal frame with some space on either side to spare.



{{< highlight supercollider >}}
(
~k = ScinthDef.new(\k, {
    // This radius will go from black at the center where NormPos is close to zero
    // and increase to > 1 at the edges of the image.
    var r = VLength.pr(VNormPos.pr);
    // We use the Step function to clip all values of r > 1 to make the cirle more
    // visually obvious.
    var clip = 1.0 - VStep.pr(1.0, r);
    VBWOut.fr(r * clip);
}).add;
)

(
~t = Scinth.new(\k);
)
{{< /highlight >}}

<img src="/images/schelp/NormPosExample.png" />




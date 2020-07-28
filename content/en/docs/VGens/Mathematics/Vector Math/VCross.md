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


## Examples
---



{{< highlight supercollider >}}
// A common technique for determining if a point is within a triangle is to use
// the cross product of each of the three triangle lines, and compare that value
// to a reference point on the triangle. If the test point has the same sign of
// cross product as does the reference point that means it is on the same side
// of the line as the reference point, and therefore within the triangle.
// For more information see https://blackpawn.com/texts/pointinpoly/.
// This example uses the red channel to indicate every pixel passing the test
// that is to the right of the line from bottom left to center top. Green
// indicates everything above the bottom line. Blue indicates everything to
// the left of the line running from center top to bottom right. Pixels in
// the center are rendered as white because they pass all three line tests
// and so are rendered with red, green, and blue all at maximum.
(
~triTest = ScinthDef.new(\triTest, { |edgeLength = 1.0|
    // sqrt(3) / 2
    var height = 0.866025404 * edgeLength;
    var a = VVec3.pr(-0.5 * height, 0.5 * height, 0);
    var b = VVec3.pr(0.0, -0.5 * height, 0);
    var c = VVec3.pr(0.5 * height, 0.5 * height, 0);
    var ab = b - a;
    var ca = a - c;
    var bc = c - b;

    var pos = VNormPos.pr;
    var p = VVec3.pr(VX.pr(pos), VY.pr(pos), 0.0);
    VRGBOut.pr(
        (VZ.pr(VCross.pr(ab, p - a))).sign,
        (VZ.pr(VCross.pr(ca, p - c))).sign,
        (VZ.pr(VCross.pr(bc, p - b))).sign);
}).add;
)

(
~test = Scinth.new(\triTest);
)
{{< /highlight >}}

<img src="/images/schelp/VCross.png" />




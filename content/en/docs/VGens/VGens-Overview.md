---
title: VGens Overview
linkTitle: VGens Overview
date: 2020-04-15
weight: 5
description: A list of the available VGens within Scintillator.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="{{< ref "/docs/Guides/Scintillator-Parallel-Classes" >}}">Scintillator Parallel Classes</a> 



### Video Oscillators
---



There are only a few test oscillators implemented for now, with plans to add many more. One key conceptual difference between SuperCollider audio oscillators and Scintillator video oscillators to bear in mind is that video signals are constrained to values between <code>[0, 1]</code>, unlike audio signals, which operate normally between <code>[-1, 1]</code>.


<table>
<tr><td>

<a href="{{< ref "/docs/VGens/Video Oscillators/ScinOsc" >}}">ScinOsc</a><code>.fg(freq, phas, mul, add)</code>

</td><td>
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
</td><td>

Piecewise sinusodal oscillator, analogous to <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Video Oscillators/VSaw" >}}">VSaw</a><code>.fg(freq, phas, mul, add)</code>

</td><td>
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
</td><td>

Piecewise sawtooth oscillator, analogous to <a href="https://doc.sccode.org/Classes/LFSaw.html">LFSaw <img src="/images/external-link.svg" class="one-liner"></a>

</td></tr>

</table>


### Image Sampling
---



VGens for reading from <a href="{{< ref "/docs/Media/ImageBuffer" >}}">ImageBuffer</a> objects.


<table>
<tr><td>

<a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a><code>.fg(image, pos)</code>

</td><td>
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
</td><td>

Samples the provided imageBuffer at <code>pos</code> and returns the 4D color signal as <code>(r, g, b, a)</code>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Intrinsics/TexPos" >}}">TexPos</a><code>.fg()</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

-

</td><td>

2

</td></tr>

</table>
</td><td>

Texture Sampler position

</td></tr>
<tr><td>

Classes/TextureSize<code>.fg(image)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

-

</td><td>

2

</td></tr>

</table>
</td><td>

Returns the dimensions in pixels of the provided ImageBuffer. Roughly analogous to <a href="https://doc.sccode.org/Classes/BufFrames.html">BufFrames <img src="/images/external-link.svg" class="one-liner"></a>.

</td></tr>

</table>


### Fragment Position
---



Scintillator offers a few different means to determine the position of the current fragment shader relative to the geometry being rendered, or the onscreen pixel dimensions. The <a href="{{< ref "/docs/VGens/Intrinsics/TexPos" >}}">TexPos</a> VGen is in the Image Sampling section.


<table>
<tr><td>

<a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a><code>.fg()</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

-

</td><td>

2

</td></tr>

</table>
</td><td>

Normalized fragment position

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Intrinsics/TexPos" >}}">TexPos</a><code>.fg()</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

-

</td><td>

2

</td></tr>

</table>
</td><td>

Texture Sampler position

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Built-In/FragCoord" >}}">FragCoord</a><code>.fg()</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

-

</td><td>

2

</td></tr>

</table>
</td><td>

Onscreen coordinates of current fragment in pixels

</td></tr>

</table>


### Vector Manipulation
---



These utility UGens allow the grouping of one-dimensional elements into vectors, access to individual elements within vectors, and lastly a convenience function to repeat (or <em>splat</em>) a single element across all elements within a vector.



#### Building Vectors



Some VGens require inputs that are higher-dimensional vectors. To construct those inputs from single-dimensional components, Scintillator provides the <code>VecN</code> classes.


<table>
<tr><td>

<strong>VGen</strong>

</td><td>

<strong>dimensions</strong>

</td><td>

<strong>description</strong>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a><code>.fg(x, y)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

2

</td></tr>

</table>
</td><td>

Construct a 2D vector from individual elements <code>x</code> and <code>y</code>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a><code>.fg(x, y, z)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

3

</td></tr>

</table>
</td><td>

Construct a 3D vector from individual elements <code>x</code> and <code>y</code>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a><code>.fg(x, y, z, w)</code>

</td><td>
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
</td><td>

Construct a 4D vector from individual elements <code>x</code> and <code>y</code>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Splat2" >}}">Splat2</a><code>.fg(x)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1

</td><td>

2

</td></tr>

</table>
</td><td>

Construct a 2D vector from a single element copied into both

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Splat3" >}}">Splat3</a><code>.fg(x)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1

</td><td>

3

</td></tr>

</table>
</td><td>

Construct a 3D vector from a single element copied into both

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/Splat4" >}}">Splat4</a><code>.fg(x)</code>

</td><td>
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
</td><td>

Construct a 4D vector from a single element copied into both

</td></tr>

</table>


#### Accessing Elements Within Vectors



To break out a single-dimensional signal from a higher-dimensional vector, use the <code>VN</code> classes. These follow the computer graphics naming conventions for elements within the vector, where the names <em>x, y, z, w</em> are used to indicate the first through fourth element respectively.


<table>
<tr><td>

<strong>VGen</strong>

</td><td>

<strong>dimensions</strong>

</td><td>

<strong>description</strong>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a><code>.fg(v)</code>

</td><td>
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

1

</td></tr>
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>
</td><td>

Return the first element in the vector.

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/VY" >}}">VY</a><code>.fg(v)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

2

</td><td>

1

</td></tr>
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>
</td><td>

Return the second element in the vector.

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a><code>.fg(v)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>
</td><td>

Return the third element in the vector.

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Vector Manipulation/VW" >}}">VW</a><code>.fg(v)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>
</td><td>

Return the fourth element in the vector.

</td></tr>

</table>


#### Video Output Convenience



Any 4D output is considered valid <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> output.


<table>
<tr><td>

Classes/RGBOut<code>.fg(r, g, b)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

4

</td></tr>

</table>
</td><td>

Convenience object for color output at full alpha

</td></tr>
<tr><td>

Classes/RGBAOut<code>.fg(r, g, b)</code>

</td><td>
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
</td><td>

Convenience object for color output with alpha channel

</td></tr>
<tr><td>

Classes/BWOut<code>.fg(x)</code>

</td><td>
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
</td><td>

Convenience object for black and white output at full alpha

</td></tr>

</table>


### Mathematical Operations
---



Scintillator offers per-element (or <em>piecewise</em>) operations as well as some more traditional vector mathematical operations such as dot and cross products. Many unary and binary operations are offerred with the same names as their analog counterparts,



#### Vector Operations


<table>
<tr><td>

<strong>VGen</strong>

</td><td>

<strong>dimensions</strong>

</td><td>

<strong>description</strong>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Mathematics/Signal Processing/Clamp" >}}">Clamp</a><code>.fg(x, min, max)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4, 4

</td><td>

4

</td></tr>

</table>
</td><td>

Video equivalent of <a href="https://doc.sccode.org/Classes/Clip.html">Clip <img src="/images/external-link.svg" class="one-liner"></a> UGen, piecewise bounds input <code>x</code> between <code>[min, max]</code>

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a><code>.fg(x)</code>

</td><td>
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

1

</td></tr>
<tr><td>

3

</td><td>

1

</td></tr>
<tr><td>

4

</td><td>

1

</td></tr>

</table>
</td><td>

Returns the length of the vector <code>x</code>, or the square root of the sum of the squares

</td></tr>
<tr><td>

<a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Distance" >}}">Distance</a><code>.fg(x, y)</code>

</td><td>
<table>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

1

</td></tr>
<tr><td>

3, 3

</td><td>

1

</td></tr>
<tr><td>

4, 4

</td><td>

1

</td></tr>

</table>
</td><td>

Computes the distance between <code>x</code> and <code>y</code>, which is the length of the vector <code>x - y</code>

</td></tr>
<tr><td>

Classes/Step<code>.fg(step, x)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4

</td><td>

4

</td></tr>

</table>
</td><td>

Just like the binary operator <code>thresh</code>, returns <code>0</code> when <code>x < step</code>, otherwise <code>x</code>

</td></tr>
<tr><td>

Classes/VecMix<code>.fg(x, y, a)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2, 1

</td><td>

2

</td></tr>
<tr><td>

2, 2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3, 1

</td><td>

3

</td></tr>
<tr><td>

3, 3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4, 1

</td><td>

4

</td></tr>
<tr><td>

4, 4, 4

</td><td>

4

</td></tr>

</table>
</td><td>

Similar to the binary operator <code>blend</code>, returns a linear mix of <code>x, y</code> with <code>a</code> between <code>[0, 1]</code>. Supports piecewise comparison or a single blend argument to apply to all components

</td></tr>
<tr><td>

Classes/Dot<code>.fg(x, y)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

1

</td></tr>
<tr><td>

3, 3

</td><td>

1

</td></tr>
<tr><td>

4, 4

</td><td>

1

</td></tr>

</table>
</td><td>

Returns the dot product between <code>x</code> and <code>y</code>, or the sum of the product of each component in the vector

</td></tr>
<tr><td>

Classes/Cross<code>.fg(x, y)</code>

</td><td>
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
</td><td>

Returns the cross product of <code>x</code> and <code>y</code>

</td></tr>
<tr><td>

Classes/VNorm<code>.fg(x)</code>

</td><td>
<table>
<tr><td>

<strong>input</strong>

</td><td>

<strong>output</strong>

</td></tr>
<tr><td>

1, 1

</td><td>

1

</td></tr>
<tr><td>

2, 2

</td><td>

2

</td></tr>
<tr><td>

3, 3

</td><td>

3

</td></tr>
<tr><td>

4, 4

</td><td>

4

</td></tr>

</table>
</td><td>

Returns a <em>normalized</em> vector parallel to <code>x</code> with length <code>1</code>

</td></tr>

</table>


#### Built-In Unary Operations



The SuperCollider UGens support a broad variety of unary, binary, and n-ary mathematical operations. These are typically expressed as operators within the <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a> flow, and are transformed by the <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a> programming into one of the subclasss of <a href="https://doc.sccode.org/Classes/BasicOpUGen.html">BasicOpUGen <img src="/images/external-link.svg" class="one-liner"></a> before being sent to the server. Scintillator implements parallel classes for unary and binary operations, with the Classes/BasicOpVGen as the base class and Classes/UnaryOpVGen and Classes/BinaryOpVGen derived classes handling a subset of the operators handled by the UGen programming.



For documentation of these operators in base SuperCollider see the <a href="https://doc.sccode.org/Overviews/Operators.html">Operators <img src="/images/external-link.svg" class="one-liner"></a> overview or the <a href="https://doc.sccode.org/Classes/SimpleNumber.html">SimpleNumber <img src="/images/external-link.svg" class="one-liner"></a> class documentation.



The following tables detail the current supported operations along with the ones that are not yet supported, the name of the VGen sent to the server to realize the operation, a brief explanation of their function and conceptual mathematical code. Since all numbers within a Scintillator <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> must be floating-point, several of the integer operations like bit manipulation are marked as <em>not applicable</em>. If support is planned, but not yet implemented, the function is marked as <em>not yet implemented</em>.



All unary operations support inputs in 1-4 dimensions, and produce outputs of the same dimension. For higher-dimensional signals all operations happen <em>piecewise</em>, meaning the operator is applied to each component of the signal independently. For example if <code>b = a.neg</code> and both <code>a</code> and <code>b</code> are <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> objects then:



{{< highlight supercollider >}}
b = Vec4.fg(
    VX.fg(a).neg,
    VY.fg(a).neg,
    VZ.fg(a).neg,
    VW.fg(a).neg);
{{< /highlight >}}


<table>
<tr><td>

<strong>Operator</strong>

</td><td>

<strong>VGen</strong>

</td><td>

<strong>Description</strong>

</td><td>

<strong>conceptual sclang code</strong>

</td></tr>
<tr><td>

<code>neg</code>

</td><td>

<code>VNeg</code>

</td><td>

Unary negation

</td><td>

<code>x = -1 * x</code>

</td></tr>
<tr><td>

<code>reciprocal</code>

</td><td>

<code>VReciprocal</code>

</td><td>

Reciprocal division

</td><td>

<code>x = 1 / x</code>

</td></tr>
<tr><td>

<code>bitNot</code>

</td><td>

<em>not applicable</em>

</td><td>

Bitwise inversion

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>abs</code>

</td><td>

<code>VAbs</code>

</td><td>

Absolute value

</td><td>

<code>if (x < 0, { x.neg }, { x })</code>

</td></tr>
<tr><td>

<code>asFloat</code>

</td><td>

<em>not applicable</em>

</td><td>

Convert to float

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>asInteger</code>

</td><td>

<em>not applicable</em>

</td><td>

Convert to integer

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ceil</code>

</td><td>

<code>VCeil</code>

</td><td>

Nearest integer greater than x

</td><td>

<code>if ((x - x.asInteger) > 0, { (x.asInteger) + 1.0 }, { x })</code>

</td></tr>
<tr><td>

<code>floor</code>

</td><td>

<code>VFloor</code>

</td><td>

Nearest integer lesser than x

</td><td>

<code>x.asInteger.asFloat</code>

</td></tr>
<tr><td>

<code>frac</code>

</td><td>

<code>VFract</code>

</td><td>

Fractional remainder of x

</td><td>

<code>x - x.asInteger</code>

</td></tr>
<tr><td>

<code>sign</code>

</td><td>

<code>VSign</code>

</td><td>

Sign, either -1, 0, or +1 matching sign of x

</td><td>

<code>case { x < 0 } { -1.0 } { x == 0 } { 0.0 } { 1.0 }</code>

</td></tr>
<tr><td>

<code>squared</code>

</td><td>

converted to <code>x * x</code>

</td><td>

Square of a number

</td><td>

<code>x * x</code>

</td></tr>
<tr><td>

<code>cubed</code>

</td><td>

converted to <code>x * x * x</code>

</td><td>

Cube of a number

</td><td>

<code>x * x * x</code>

</td></tr>
<tr><td>

<code>sqrt</code>

</td><td>

<code>VSqrt</code>

</td><td>

Square root

</td><td>

<code>x.sqrt</code>

</td></tr>
<tr><td>

<code>exp</code>

</td><td>

<code>VExp</code>

</td><td>

Natural exponentiation

</td><td>

<code>e ** x</code>

</td></tr>
<tr><td>

<code>midicps</code>

</td><td>

<em>not yet implemented</em>

</td><td>

MIDI note to cycles per second

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>cpsmidi</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Cycles per second to MIDI note

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>midiratio</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert an interval in MIDI notes to a frequency ratio

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ratiomidi</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert a frequency ratio to an interval in MIDI notes

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ampdb</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert decibels to linear amplitude

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>dbamp</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert linear amplitude to decibels

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>octcps</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert decimal octaves to cycles per second

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>cpsoct</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Convert cycles per second to decimal octaves

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>log</code>

</td><td>

<code>VLog</code>

</td><td>

Natural logarithm

</td><td>

<code>x.log</code>

</td></tr>
<tr><td>

<code>log2</code>

</td><td>

<code>VLog2</code>

</td><td>

Base 2 logarithm

</td><td>

<code>x.log2</code>

</td></tr>
<tr><td>

<code>log10</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Base 10 logarithm

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>sin</code>

</td><td>

<code>VSin</code>

</td><td>

Sine

</td><td>

<code>x.sin</code>

</td></tr>
<tr><td>

<code>cos</code>

</td><td>

<code>VCos</code>

</td><td>

Cosine

</td><td>

<code>x.cos</code>

</td></tr>
<tr><td>

<code>tan</code>

</td><td>

<code>VTan</code>

</td><td>

Tangent

</td><td>

<code>x.tan</code>

</td></tr>
<tr><td>

<code>asin</code>

</td><td>

<code>VASin</code>

</td><td>

Arcsine

</td><td>

<code>x.asin</code>

</td></tr>
<tr><td>

<code>acos</code>

</td><td>

<code>VACos</code>

</td><td>

Arccosine

</td><td>

<code>x.acos</code>

</td></tr>
<tr><td>

<code>atan</code>

</td><td>

<code>VATan</code>

</td><td>

Arctangent

</td><td>

<code>x.atan</code>

</td></tr>
<tr><td>

<code>rand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns an evenly distributed random value between zero and x

</td><td>

<code>x.rand</code>

</td></tr>
<tr><td>

<code>rand2</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns an evenly distributed random value beteen -x and +x

</td><td>

<code>x.rand2</code>

</td></tr>
<tr><td>

<code>linrand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns a linearly distributed random value between x and zero

</td><td>

<code>x.linrand</code>

</td></tr>
<tr><td>

<code>bilinrand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns a linearly distributed random value between -x and +x

</td><td>

<code>x.bilinrand</code>

</td></tr>
<tr><td>

<code>sum3rand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns a value from an approximation of a Gaussian random distribution between x and zero

</td><td>

<code>x.sum3rand</code>

</td></tr>
<tr><td>

<code>distort</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Nonlinear distortion of x

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>softclip</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Distortion with a linear region from 0.25 to 0.75

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>coin</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Returns one or zero with the probability given by the argument

</td><td>

<code>x.coin</code>

</td></tr>
<tr><td>

<code>even</code>

</td><td>

<em>not applicable</em>

</td><td>

True if dividable by two with no remainder

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>odd</code>

</td><td>

<em>not applicable</em>

</td><td>

True if dividable by two with a remainder of 1

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>rectWindow</code>

</td><td>

<em>not yet implemented</em>

</td><td>

A value for a rectangular window function between 0 and 1

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>hanWindow</code>

</td><td>

<em>not yet implemented</em>

</td><td>

A value for a Hanning window function between 0 and 1

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>welWindow</code>

</td><td>

<em>not yet implemented</em>

</td><td>

A value for a Welsh window function between 0 and 1

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>triWindow</code>

</td><td>

<em>not yet implemented</em>

</td><td>

A value for a triangle window function between 0 and 1

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>scurve</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Map x on to an S-curve

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ramp</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Map receiver onto a ramp starting at 0

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>isPositive</code>

</td><td>

<em>not applicable</em>

</td><td>

True if x is >= 0

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>isNegative</code>

</td><td>

<em>not applicable</em>

</td><td>

True if x is < 0

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>isStrictlyPositive</code>

</td><td>

<em>not applicable</em>

</td><td>

True if x is > 0

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>rho</code>

</td><td>

<em>not yet implemented</em>

</td><td>

The polar radius of x

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>theta</code>

</td><td>

<em>not yet implemented</em>

</td><td>

The polar angle of x

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ref</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this operator does

</td><td>

<em>??</em>

</td></tr>

</table>


#### Built-In Binary Operations



Binary operations also happen <em>piecewise</em>, meaning that the binary operator is applied to each individual component separately. Conceptually if <code>c = a * b</code> and both <code>a</code> and <code>b</code> are <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a>s then:



{{< highlight supercollider >}}
c = Vec4.fg(
    VX.fg(a) * VX.fg(b),
    VY.fg(a) * VY.fg(b),
    VZ.fg(a) * VZ.fg(b),
    VW.fg(a) * VW.fg(b));
{{< /highlight >}}


<table>
<tr><td>

<strong>Operator</strong>

</td><td>

<strong>VGen</strong>

</td><td>

<strong>Description</strong>

</td><td>

<strong>conceptual sclang code</strong>

</td></tr>
<tr><td>

<code>rotate</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this operator does

</td><td>

<em>??</em>

</td></tr>
<tr><td>

<code>dist</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this opreator does

</td><td>

<em>??</em>

</td></tr>
<tr><td>

<code>+</code>

</td><td>

<code>VAdd</code>

</td><td>

Addition

</td><td>

<code>a + b</code>

</td></tr>
<tr><td>

<code>-</code>

</td><td>

<code>VSub</code>

</td><td>

Subtraction

</td><td>

<code>a - b</code>

</td></tr>
<tr><td>

<code>*</code>

</td><td>

<code>VMul</code>

</td><td>

Multiplication

</td><td>

<code>a * b</code>

</td></tr>
<tr><td>

<code>/</code>

</td><td>

<code>VDiv</code>

</td><td>

Division

</td><td>

<code>a / b</code>

</td></tr>
<tr><td>

<code>div</code>

</td><td>

<code>VDiv</code>

</td><td>

Division, TODO: validate if this is really division

</td><td>

<code>a / b</code>

</td></tr>
<tr><td>

<code>mod</code>

</td><td>

<code>VMod</code>

</td><td>

Floating-point modulo

</td><td>

<code>a % b</code>

</td></tr>
<tr><td>

<code>pow</code>

</td><td>

<code>VPow</code>

</td><td>

Exponentiation

</td><td>

<code>a ** b</code>

</td></tr>
<tr><td>

<code>min</code>

</td><td>

<code>VMin</code>

</td><td>

Piecewise minimum

</td><td>

<code>if (a < b, { a }, { b });</code>

</td></tr>
<tr><td>

<code>max</code>

</td><td>

<code>VMax</code>

</td><td>

Piecewise maximum

</td><td>

<code>if (a > b, { a }, { b });</code>

</td></tr>
<tr><td>

<code><</code>

</td><td>

<em>not applicable</em>

</td><td>

True if a < b

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code><=</code>

</td><td>

<em>not applicable</em>

</td><td>

True if a <= b

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>></code>

</td><td>

<em>not applicable</em>

</td><td>

True if a > b

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>>=</code>

</td><td>

<em>not applicable</em>

</td><td>

True if a >= b

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>bitAnd</code>

</td><td>

<em>not applicable</em>

</td><td>

Bitwise logical AND

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>bitOr</code>

</td><td>

<em>not applicable</em>

</td><td>

Bitwise logical OR

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>bitXor</code>

</td><td>

<em>not applicable</em>

</td><td>

Bitwise logical XOR

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>hammingDistance</code>

</td><td>

<em>not applicable</em>

</td><td>

Count of bits that are different

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>lcm</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Least common multiple

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>gcd</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Greates common divisor

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>round</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Round to a multiple of a number

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>roundUp</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Round up to a multiple of a number

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>trunc</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Truncate to a muliple of a number

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>atan2</code>

</td><td>

<code>VATan2</code>

</td><td>

Arctangent of a / b

</td><td>

<code>(a / b).atan</code>

</td></tr>
<tr><td>

<code>hypot</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Square root of the sum of the squares

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>hypotApx</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this operator does

</td><td>

<em>??</em>

</td></tr>
<tr><td>

<code>leftShift</code>

</td><td>

<em>not applicable</em>

</td><td>

Shift bits to the left

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>rightShift</code>

</td><td>

<em>not applicable</em>

</td><td>

Shift bits to the right

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>unsignedRightShift</code>

</td><td>

<em>not applicable</em>

</td><td>

Shift bits to the right without preserving sign bit

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>ring1</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Ring modulation plus first source

</td><td>

<code>(a * b) + a</code>

</td></tr>
<tr><td>

<code>ring2</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Ring modulation plus both sources

</td><td>

<code>(a * b) + a + b</code>

</td></tr>
<tr><td>

<code>ring3</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Ring modulation variant

</td><td>

<code>a * a * b</code>

</td></tr>
<tr><td>

<code>ring4</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Ring modulation variant

</td><td>

<code>(a * a * b) - (a * b * b)</code>

</td></tr>
<tr><td>

<code>difsqr</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Difference of squares

</td><td>

<code>(a * a) - (b * b)</code>

</td></tr>
<tr><td>

<code>sumsqr</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Sum of the squares

</td><td>

<code>(a**2) + (b**2)</code>

</td></tr>
<tr><td>

<code>sqrsum</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Square of the sum

</td><td>

<code>(a + b)**2</code>

</td></tr>
<tr><td>

<code>absdif</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Absolute value of the difference

</td><td>

<code>(a - b).abs</code>

</td></tr>
<tr><td>

<code>thresh</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Thresholding

</td><td>

<code>if (a < b, { 0 }, { a });</code>

</td></tr>
<tr><td>

<code>amclip</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Two quadrant multiply

</td><td>

<code>if (b <= 0, { 0 }, { a * b });</code>

</td></tr>
<tr><td>

<code>scaleneg</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Scale negative part of input

</td><td>

<code>if (a < 0, { a * b }, { a });</code>

</td></tr>
<tr><td>

<code>clip2</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Clip <code>a</code> to <code>+/-b</code>

</td><td>

<code>if (a.abs < b, { b * a.sign }, { a });</code>

</td></tr>
<tr><td>

<code>fold2</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Bilateral folding

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>wrap2</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Bilateral wrapping

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>excesss</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Residual clipping

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>firstArg</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this operator does

</td><td>

<em>??</em>

</td></tr>
<tr><td>

<code>rrand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Generate a uniformly distributed pseudorandom number in [a, b]

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>exprand</code>

</td><td>

<em>not yet implemented</em>

</td><td>

Generate an exponentially distributed pseudorandom number in [a, b]

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>@</code>

</td><td>

<em>unknown</em>

</td><td>

To be researched what this operator does

</td><td>

<em>??</em>

</td></tr>
<tr><td>

<code>||</code>

</td><td>

<em>not applicable</em>

</td><td>

Boolean logical OR

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>&&</code>

</td><td>

<em>not applicable</em>

</td><td>

Boolean logical AND

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>xor</code>

</td><td>

<em>not applicable</em>

</td><td>

Boolean logical XOR

</td><td>

<em>n/a</em>

</td></tr>
<tr><td>

<code>nand</code>

</td><td>

<em>not applicable</em>

</td><td>

Boolean logical NAND

</td><td>

<em>n/a</em>

</td></tr>

</table>

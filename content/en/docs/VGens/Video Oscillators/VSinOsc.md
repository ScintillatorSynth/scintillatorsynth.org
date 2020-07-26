---
title: VSinOsc
linkTitle: VSinOsc
weight: 5
description: Multidimensional sinusoidal video oscillator
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a> 



## Description
---



VSinOsc, just like the audio UGen <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a> UGen, is a sinusoidal oscillator. It can process inputs of one to four dimensions and produces an output in the same dimension. The arguments parallel the arguments used by <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, with the only difference being that the default <code>mul</code> and <code>add</code> argument values are modified to support producing a sin output range within <code>[0, 1]</code> instead of the audio range <code>[-1, 1]</code>.



<strong>Supported Rates: frame, shape, pixel</strong>



## Class Methods
---



### VSinOsc.fr(freq: 1.0, phase: 0.0, mul: 0.5, add: 0.5)



### VSinOsc.sr(freq: 1.0, phase: 0.0, mul: 0.5, add: 0.5)



### VSinOsc.pr(freq: 1.0, phase: 0.0, mul: 0.5, add: 0.5)



Make a VSinOsc VGen at requested rate.



#### Arguments

##### freq



Oscillator frequency in cycles per second or Hz.



##### phase



Oscillator phase in radians.



##### mul



Multiplier to apply to oscillator output.



##### add



Value to add to oscillator output after multiply.



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




#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
(
// This example demonstrates using the three-dimensional
// VSinOsc to produce separate red, green, and blue
// channel outputs. Note that all arguments have to be
// provided to the higher dimensional VSinOsc instances,
// because the defaults are only 1-dimensional, and
// Scintillator currently can't "autosplat" defaults to
// higher-dimensional arguments.
~k = ScinthDef.new(\k, { |dot = 0.5|
    var r = VLength.pr(NormPos.pr);
    var rgb = VSinOsc.pr(Vec3.pr(r, 2.0 * r, 3.0 * r),
        Vec3.pr, Splat3.pr(0.5), Splat3.pr(0.5));
    RGBOut.pr(VX.pr(rgb), VY.pr(rgb), VZ.pr(rgb));
}).add;
)

(
~t = Scinth.new(\k);
)
{{< /highlight >}}

<img src="/images/schelp/VSinOsc.png" />




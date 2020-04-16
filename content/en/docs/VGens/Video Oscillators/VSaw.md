---
title: VSaw
linkTitle: VSaw
date: 2020-04-15
weight: 5
description: Multidimensional sawtooth video oscillator
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="https://doc.sccode.org/Classes/LFSaw.html">LFSaw <img src="/images/external-link.svg" class="one-liner"></a> 



## Description
---



Similar to its analog sibling <a href="https://doc.sccode.org/Classes/LFSaw.html">LFSaw <img src="/images/external-link.svg" class="one-liner"></a> the VSaw is a video sawtooth oscillator. It can process inputs of one to four dimensions and produces an output in the same dimension. The arguments parallel the arguments used by <a href="https://doc.sccode.org/Classes/LFSaw.html">LFSaw <img src="/images/external-link.svg" class="one-liner"></a>, although the output is configured to range from <code>[0, 1]</code>.



## Class Methods
---



### VSaw.fg(freq: 1.0, phase: 0.0)



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


#### Arguments

##### freq



Oscillator frequency in cycles per second or Hz.



##### phase



Oscillator phase in radians.





#### Inherited class methods



## Instance Methods
---



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
// This example represents some of the early origins of the Scintillator logo.
// It uses a VSaw to generate the bands. Each pixel on the screen is oscillating
// at 5 Hz, but the phase changes as a function of distance from the center of
// the screen. This smooth variation in phase causes the bands to appear to
// move outward over time.
(
~t = ScinthDef.new(\t, {
    var pos = NormPos.fg.abs.neg;
    var z = min(VX.fg(pos), VY.fg(pos));
    BWOut.fg(Step.fg(0.5, VSaw.fg(freq: 5, phase: z)));
}).add;
)

(
~k = Scinth.new(\t);
)
{{< /highlight >}}

<img src="/images/schelp/VSaw.png" />
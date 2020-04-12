---
title: Scintillator Parallels to SuperCollider Synth Classes
linkTitle: Scintillator Parallels to SuperCollider Synth Classes
date: 2020-04-11
weight: 5
description: A table of Scintillator classes similar to scsynth classes
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: 



Scintillator is designed to parallel, in as much a way as makes sense, the existing design conventions for audio synthesis established by SuperCollider.



### General Class Parallels
---


<table>
<tr><td>

<strong>Parallel Class</strong>

</td><td>

<strong>Scintillator Class</strong>

</td><td>

<strong>comments</strong>

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

<a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>

</td><td>

The synth definition class for Scintillator, a template for <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> objects.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

<a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a>

</td><td>

A runnable instance of a ScinthDef template, capable of generating imagery on the server.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/Server.html">Server <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

<a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a>

</td><td>

Represents the Scintillator server application.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/ServerOptions.html">ServerOptions <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

<a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a>

</td><td>

Options for controlling various features of the Scintillator features at boot time.

</td></tr>

</table>


### VGen to UGen Parallels
---



For an exhaustive list of all supported Scintillator VGens, see the <a href="{{< ref "/docs/VGens/VGens-Overview" >}}">VGens-Overview</a>.


<table>
<tr><td>

<strong>Parallel UGen</strong>

</td><td>

<strong>VGen</strong>

</td><td>

<strong>comments</strong>

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/ScinOsc

</td><td>

Sinusoidal oscillator.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/LFSaw.html">LFSaw <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/VSaw

</td><td>

Sawtooth oscillator.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/PlayBuf.html">PlayBuf <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

<a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Image sampling VGen.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/BufFrames.html">BufFrames <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/TextureSize

</td><td>

Dimensions of image buffer in pixels.

</td></tr>
<tr><td>

Classes/Clip

</td><td>

Classes/Clamp

</td><td>

Constrain a signal within minimum and maximum values.

</td></tr>

</table>





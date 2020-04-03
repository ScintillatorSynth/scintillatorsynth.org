---
title: Scintillator Parallels to SuperCollider Synth Classes
linkTitle: Scintillator Parallels to SuperCollider Synth Classes
date: 2020-04-02
weight: 5
description: A table of Scintillator classes similar to scsynth classes
---


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

Classes/ScinthDef

</td><td>

The synth definition class for Scintillator, a template for Classes/Scinth objects.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/Scinth

</td><td>

A runnable instance of a ScinthDef template, capable of generating imagery on the server.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/Server.html">Server <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/ScinServer

</td><td>

Represents the Scintillator server application.

</td></tr>
<tr><td>

<a href="https://doc.sccode.org/Classes/ServerOptions.html">ServerOptions <img src="/images/external-link.svg" class="one-liner"></a>

</td><td>

Classes/ScinServerOptions

</td><td>

Options for controlling various features of the Scintillator features at boot time.

</td></tr>

</table>


### VGen to UGen Parallels
---


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

Classes/Sampler

</td><td>

Image sampling VGen. TODO: maybe consider making Sampler a pure base class.

</td></tr>

</table>

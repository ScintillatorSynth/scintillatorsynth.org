---
title: Scintillator Parallels to SuperCollider Synth Classes
linkTitle: Scintillator Parallels to SuperCollider Synth Classes
date: 2020-04-02
weight: 5
description: A table of Scintillator classes similar to scsynth classes
---
Scintillator is designed to parallel, in as much a way as makes sense, the existing design conventions for audio synthesis established by SuperCollider.

<table>
<tr><td><strong>Parallel Class</strong>

</td><td><strong>Scintillator Class</strong>

</td><td><strong>comments</strong>

</td></tr>
<tr><td>Classes/SynthDef

</td><td>Classes/ScinthDef

</td><td>The synth definition class for Scintillator, a template for Classes/Scinth objects.

</td></tr>
<tr><td>Classes/Synth

</td><td>Classes/Scinth

</td><td>A runnable instance of a ScinthDef template, capable of generating imagery on the server.

</td></tr>
<tr><td>Classes/Server

</td><td>Classes/ScinServer

</td><td>Represents the Scintillator server application.

</td></tr>
<tr><td>Classes/ServerOptions

</td><td>Classes/ScinServerOptions

</td><td>Options for controlling various features of the Scintillator features at boot time.

</td></tr>
</table>
<table>
<tr><td><strong>Parallel UGen</strong>

</td><td><strong>VGen</strong>

</td><td><strong>comments</strong>

</td></tr>
<tr><td>Classes/SinOsc

</td><td>Classes/ScinOsc

</td><td>Sinusoidal oscillator.

</td></tr>
<tr><td>Classes/LFSaw

</td><td>Classes/VSaw

</td><td>Sawtooth oscillator.

</td></tr>
<tr><td>Classes/PlayBuf

</td><td>Classes/Sampler

</td><td>Image sampling VGen. TODO: maybe consider making Sampler a pure base class.

</td></tr>
</table>

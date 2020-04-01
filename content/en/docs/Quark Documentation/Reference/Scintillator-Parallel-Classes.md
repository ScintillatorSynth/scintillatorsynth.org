---
title: Scintillator Parallels to SuperCollider Synth Classes
linkTitle: Scintillator Parallels to SuperCollider Synth Classes
date: 2020-03-31
weight: 5
description: A table of Scintillator classes similar to scsynth classes
---
Scintillator is designed to parallel, in as much a way as makes sense, the existing design conventions for audio synthesis established by SuperCollider.<div id='nil'><table>
<tr><td>**Parallel Class**<td>**Scintillator Class**<td>**comments**<tr><td>Classes/SynthDef<td>Classes/ScinthDef<td>The synth definition class for Scintillator, a template for Classes/Scinth objects.<tr><td>Classes/Synth<td>Classes/Scinth<td>A runnable instance of a ScinthDef template, capable of generating imagery on the server.<tr><td>Classes/Server<td>Classes/ScinServer<td>Represents the Scintillator server application.<tr><td>Classes/ServerOptions<td>Classes/ScinServerOptions<td>Options for controlling various features of the Scintillator features at boot time.</table>
<div id='nil'><table>
<tr><td>**Parallel UGen**<td>**VGen**<td>**comments**<tr><td>Classes/SinOsc<td>Classes/ScinOsc<td>Sinusoidal oscillator.<tr><td>Classes/LFSaw<td>Classes/VSaw<td>Sawtooth oscillator.<tr><td>Classes/PlayBuf<td>Classes/Sampler<td>Image sampling VGen. TODO: maybe consider making Sampler a pure base class.</table>

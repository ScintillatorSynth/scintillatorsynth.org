---
title: Scintillator User Guide
linkTitle: Scintillator User Guide
weight: 5
description: User manual for the Scintillator visual synthesizer.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> 



In keeping with the high-energy physics themes of SuperCollider, a <em>scintillator</em> is any material that produces light when struck by radition. Scintillator is intended to be an accompanying visual synthesizer designed to be intuitive to users already familiar with SuperCollider idioms. Distributed as a Quark plus a synthesizer binary, Scintillator follows the client/server archiecture established by SuperCollider, accepts <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>s in a similar manner to SuperCollider <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>s, provides facilities to invoke and control <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> instances similar to <a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a>, and so on. For a detailed list of classes with analogous SuperCollider classes see <a href="{{< ref "/docs/Guides/Scintillator-Parallel-Classes" >}}">Scintillator Parallel Classes</a>.



### Project Status (March 2020)
---



Scintillator is an open-source (GPLv3) project developed and maintained by Luke Nihlen. It is in early pre-alpha stage and the purpose of these first few releases is to gather feedback and identify any outstanding major bugs that might need to be resolved before considering a beta or even production-quality release. Central features are under active development, and the classes and their methods may change from point release to point release without notice. As such Scintillator is ready for review but building a larger composition or planning a performance on it may not be advisable.



All that said, it is my sincere hope you find Scintillator useful and interesting, and I'm keen to hear your feedback. Drop by the <a href="https://github.com/ScintillatorSynth/Scintillator">GitHub project page</a>, if you encounter any bugs or have any feature requests please feel free to file them there, or drop me an email at <code>scintillator.synth@gmail.com</code>.



### Installation
---



#### Quark Installation



Scintillator is distributed in two pieces and both are required in order for it to run. The first piece contains the SuperCollider classes and support, and is distributed as a Quark. Installation should be as simple as executing the following code:



{{< highlight supercollider >}}
(
Quarks.install("Scintillator");
)
{{< /highlight >}}



Or you can use the Quarks GUI to pick out Scintillator and install it. See <a href="https://doc.sccode.org/Guides/UsingQuarks.html">UsingQuarks <img src="/images/external-link.svg" class="one-liner"></a> for more information. The second half of the Scintillator distribution consists of the <code>scinsynth</code> binary, which is the C++-based visual synthesis server. Official releases of the Scintillator Quark will always have an associated server binary, which can be obtained from the GitHub releases page (link TBD).



#### Server Binary Installation on MacOS



Just like the audio synth, Scintillator includes a standalone video synth binary called <code>scinsynth</code>. The binary will need to be installed in the Scintillator Quark directory in the <code>/bin</code> subdirectory. The point of this initial release is to test if the MacOS redestributable binary is working and can run on different Macs than it was developed on. The Linux destributable binary is a work in progress and will be generally available for the official alpha release.



To get the MacOS binary head to the <a href="https://github.com/ScintillatorSynth/Scintillator/releases">GitHub Releases Page</a> and download the MacOS release binary for version 0.0.1. This is a zip file. Find the Scintillator quark directory (<a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> has the Quark path if you want to look there), and extract the zip file into the /bin subdirectory.



### Quick Startup - TBD
---



This section can serve to validate your Scintillator installation, as well as to establish some of the basic concepts and get some pixels lighting up on the screen.


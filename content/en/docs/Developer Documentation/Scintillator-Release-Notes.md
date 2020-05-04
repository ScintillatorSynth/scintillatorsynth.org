---
title: Scintillator Release Notes
linkTitle: Scintillator Release Notes
weight: 5
description: Reference and history for released versions of Scintillator.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Guides/Scintillator-User-Guide" >}}">Scintillator User Guide</a> 



### Release Notes
---



#### 0.0.5 3 May 2020

<ul>
<li>

Automatically install server binaries on Quark install [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/103">103</a>]

<li>

Renamed VGen fragment-rate functions from <code>.fg</code> to <code>.fr</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/107">107</a>]

<li>

Support for installations with spaces in pathnames [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/106">106</a>]

<li>

Initialize <code>ScinServer.default</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/104">104</a>]

<li>

Added an server installation script [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/101">101</a>]

<li>

Separating <code>ScinServer.boot</code> and <code>ScinServer.init</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/97">97</a>]

<li>

Add some VGen input validation [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/96">96</a>]

<li>

Fix for startup crash on MacOS when a Vulkan SDK is installed separately on the computer [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/93">93</a>]

</ul>


#### 0.0.4 22 April 2020



Adds the Linux scinsynth binary, distributed as an AppImage file.



#### 0.0.2, 0.0.3 18 April 2020



Second (and third) attempt at a pre-alpha release of Scintillator. Fixes a bug in the Quark metadata formatting.



#### 0.0.1 18 April 2020



This is a pre-alpha release of Scintillator. The primary purpose of this release is to test the distribution of the MacOS Scintillator server binary. The Linux redistributable binary is a work in progress, with the plan to release that in pre-alpha format for testing and validation as soon as it is ready.


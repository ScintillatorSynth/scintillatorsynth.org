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



#### 0.0.8 31 August 2020

<ul>
<li>

Adds support for Linux versions as old as Ubuntu Xenial. [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/168">168 <img src="/images/external-link.svg" class="one-liner"></a>,<a href="https://github.com/ScintillatorSynth/Scintillator/pull/167">167 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/157">157 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Adds support for ScinGroups and execution ordering on the server [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/162">162 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Automatically stop all Scinths when Cmd+Period is pressed. [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/155">155 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Fixes a bug in (<a href="https://github.com/ScintillatorSynth/Scintillator/issues/143">143 <img src="/images/external-link.svg" class="one-liner"></a>) in image saving in non-aligned image widths [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/154">154 <img src="/images/external-link.svg" class="one-liner"></a>]

</ul>


#### 0.0.7 27 July 2020

<ul>
<li>

Updates documentation and examples, moves to a consistent naming convention for all objects. [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/142">142 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Adds automatic server crash reporting, fixes a long-standing intermittent server crash (<a href="https://github.com/ScintillatorSynth/Scintillator/issues/115">115 <img src="/images/external-link.svg" class="one-liner"></a>) [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/137">137 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/138">138 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/140">140 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Adds support for shape-rate and frame-rate VGens [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/131">131 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/135">135 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Renames fragment-rate VGens to "pixel-rate" to avoid ambiguity with frame-rate VGens [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/129">129 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Adds preliminary support for realtime audio input via PortAudio [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/125">125 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/126">126 <img src="/images/external-link.svg" class="one-liner"></a>, <a href="https://github.com/ScintillatorSynth/Scintillator/pull/127">127 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Adds an automatic installer for the Windows binary [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/122">122 <img src="/images/external-link.svg" class="one-liner"></a>]

</ul>


#### 0.0.6 9 May 2020



Adds the Windows scinsynth binary.



#### 0.0.5 3 May 2020

<ul>
<li>

Automatically install server binaries on Quark install [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/103">103 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Renamed VGen fragment-rate functions from <code>.fg</code> to <code>.fr</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/107">107 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Support for installations with spaces in pathnames [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/106">106 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Initialize <code>ScinServer.default</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/104">104 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Added an server installation script [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/101">101 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Separating <code>ScinServer.boot</code> and <code>ScinServer.init</code> [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/97">97 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Add some VGen input validation [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/96">96 <img src="/images/external-link.svg" class="one-liner"></a>]

<li>

Fix for startup crash on MacOS when a Vulkan SDK is installed separately on the computer [<a href="https://github.com/ScintillatorSynth/Scintillator/pull/93">93 <img src="/images/external-link.svg" class="one-liner"></a>]

</ul>


#### 0.0.4 22 April 2020



Adds the Linux scinsynth binary, distributed as an AppImage file.



#### 0.0.2, 0.0.3 18 April 2020



Second (and third) attempt at a pre-alpha release of Scintillator. Fixes a bug in the Quark metadata formatting.



#### 0.0.1 18 April 2020



This is a pre-alpha release of Scintillator. The primary purpose of this release is to test the distribution of the MacOS Scintillator server binary. The Linux redistributable binary is a work in progress, with the plan to release that in pre-alpha format for testing and validation as soon as it is ready.


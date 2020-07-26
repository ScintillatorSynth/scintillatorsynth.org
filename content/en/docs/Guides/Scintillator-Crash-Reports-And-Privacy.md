---
title: Scintillator Crash Reports and Privacy
linkTitle: Scintillator Crash Reports and Privacy
weight: 5
description: A discussion of the privacy implications of uploading crash reports
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> 



### Crash Reports May Contain Personal Information
---



Scintillator uses <a href="https://chromium.googlesource.com/crashpad/crashpad/">CrashPad <img src="/images/external-link.svg" class="one-liner"></a>, an open-source crash reporting system used by a variety of programs but primarily Chromium and Google Chrome, to collect debugging information about the Scintillator Server when it crashes. If a program is crashing on a user's computer it can be extremely difficult to figure out as a developer how to fix the problem, and the crash reports can be enormously valuable to aid in fixing the bugs that are causing the crash.



One concern is that crash reports <em>may</em> contain some personally identifying information. The crash reporting system is not designed to maliciously collect personal information from your computer, but in trying to build a picture of what was going on with the synth server when it crashed, there may be some personal information contained in there like your username or technical information about the kind of computer that the server crashed on. See the Example Crash Report below for an understanding of the typical data included in a crash report.



Because of the potential privacy concerns with sharing crash reports, <em>it is the policy of the Scintillator developers that we will never upload a crash report without your consent.</em>



### Crash Report Life Cycle
---



When a crash is detected the crash report is written into file-based database and stored in the <code>.crash_reports</code> folder inside the Scintillator Quark directory. This file database has a unique identifer which allows developers to look up all uploaded crash reports from a given crash database.



All crash reports are uploaded over SSL, meaning that the crash report is protected by encryption while it is transfered over the internet. It is uploaded to a custom crash report telemetry system called <a href="https://github.com/ScintillatorSynth/Gargamelle">Gargamelle <img src="/images/external-link.svg" class="one-liner"></a>, which is another part of the Scintillator development ecosystem. This upload software runs on a cloud server with access control limited only to Scintillator developers.



The crash telemetry system makes no effort to link crash reports to individual people. For example, we do not retain the IP address of where a crash report upload came from. We do allow retrieval of crash reports based on crash ids, which are large random numbers. This allows users to provide bug reports containing crash ids and developers to find those in the crash report database.



We retain crash reports for 1 year from time of upload, after which they are automatically deleted. Some aggregate statistics will remain after a crash report is deleted, such as the number of crash reports uploaded on a given day or associated with a given version of Scintillator.



### Example Crash Report
---



This is an analysis of an example crash report as printed by the crash reporting tools. Note that it is not an exhaustive list of everything that might be included, and is more intended to convey the kind of information relayed by the system.

<pre>Foo</pre>
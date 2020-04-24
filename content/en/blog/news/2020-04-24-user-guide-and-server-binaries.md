---
date: 2020-04-24
title: "User Guide and Server Binaries"
linkTitle: "User Guide and Server Binaries"
description: "The latest on the push to alpha release"
author: Luke Nihlen
---

**Server Binaries**

The [GitHub Releases Page](https://github.com/ScintillatorSynth/Scintillator/releases) now has server binaries up for
both MacOS and Linux. Thanks to Josh Parmenter for helping to explain the arcana behind signing a MacOS binary for
redistribution. And thanks to Nathan Ho for suggesting I look at the [AppImage](https://appimage.org/) file format for a
distro-agnostic redestributable binary on Linux. I adjusted the sclang code in Scintillator to always refer to the
binary packages on both platforms, so even when running builds of Scintillator compiled from source the code is
expecting the packaging to be in place.

I have some ideas about some streamlining or hardening the install process, but would like some feedback from others
about what's working and what needs adjustment in the current process before continuing. Specifically before a more
broad alpha announcement I'm hoping to get some colleagues to at least "sanity check" the build and install process, so
that the alpha announcement has improved chances of not getting a bunch of early feedback along the lines of "it doesn't
work for me" based on some small detail in the install flow.

**User Guide**

Now that the details about the server binaries are finalized for alpha, I've completed the first draft of the
[Scintillator User Guide]({{< ref "/docs/Guides/Scintillator-User-Guide.md" >}}), which includes installation
instructions for Scintillator as well as a walkthrough explaining some of the existing core features and concepts of
Scintillator. The walkthrough can also serve as a validation of the Scintillator install, because it exercises the
server binary by generating some simple imagery.

I'm working on a setup here at home to record some YouTube videos detailing some of the features and capabilities of
Scintillator. Hoping to have at least an intro video in place before the broader alpha announcement. I'll post an update
here on the blog when I have more on that.


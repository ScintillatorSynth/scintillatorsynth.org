---
title: About Scintillator
linkTitle: About
menu:
    main:
        weight: 10
layout: docs
---

{{% blocks/cover title="Scintillator" height="auto" %}}
A <em>scintillator</em> is a material that emits light when struck by radiation.  Scintillator is a video synthesis
engine Quark and server binary for SuperCollider, in keeping with the particle physics name theme. By design it mirrors
many of the architecture and API choices made by SuperCollider, but in the video domain. It is open source (GPLv3) and
planned for distribution on Linux, MacOS, and Windows.

Scintillator is currently in pre-alpha status, with an initial experimental release, largely to gather feedback, planned
sometime in April 2020.
{{% /blocks/cover %}}

{{% blocks/section type="section" color="primary" %}}
## Why a video synth for SuperCollider?

###### Luke Nihlen - Scintillator Developer

After trying [Pure Data](https://puredata.info/), [Max + Jitter](https://cycling74.com/products/max), and building a
large modular synthesis rig, including several video synthesis modules by [LZX Industries](https://lzxindustries.net/)
and [Brown Shoes Only](https://www.brownshoesonly.com/about), I heard about SuperCollider from a friend and gave it a
try. While I find the SuperCollider approach to synthesis to resonate the most with me creatively, I was surprised to
discover that the video synthesis capabilities seemed to be so limited. Because SuperCollider is open source, it
occurred to me that one way I could contribute back to the development of SuperCollider would be to provide a video
synth myself. More selfishly, Scintillator represents some of the software that I'm most excited about using when
working with SuperCollider.

Because Scintillator is distributed as a SuperCollider Quark, it means that I don't have to be concerned necessarily
with bloating the core software or adding a bunch of unneeded dependencies and complexity for users that don't want to
explore video synthesis. But, for those who do, the goal is to build a high-performance video synth with extraordinary
creative and expressive depths, while maintaining as much as possible an intuitive usability that "just makes sense" for
anyone familiar with SuperCollider.
{{% /blocks/section %}}

{{% blocks/section type="section" color="white" %}}
## Scintillator Components

In keeping with the client/server architecture of SuperCollider, Scintillator consists of two components:

<table>
    <tr>
        <td><strong>Scintillator Quark</strong></td>
        <td>
            A SuperCollider <a href="https://github.com/supercollider-quarks/quarks">Quark</a> is a redestributable unit
            of code and documentation for SuperCollider. The Scintillator classes that facilitate controlling
            the video synth from SuperCollider are distributed as a Quark, and can be installed via conventional means
            using the <a href="http://doc.sccode.org/Classes/Quarks.html">Quarks</a> object inside of SuperCollider.
        </td>
    </tr>
    <tr>
        <td><strong>scinsynth server</strong></td>
        <td>
            The Scintillator video synthesis engine, similar to scsynth or supernova as provided with SuperCollider.
            This is a high-performance C++ program that uses <a href="https://www.khronos.org/vulkan">Vulkan</a> for
            hardware-accelerated graphics rendering. The scinsynth binary is distributed separately from the Quark but
            will be available for download in precompiled release versions from
            <a href="https://github.com/ScintillatorSynth/Scintillator">GitHub</a>. Like the SuperCollider synthesis
            programs, scinsynth is controlled primarily by <a href="http://opensoundcontrol.org/osc">OSC</a>, a network
            protocol with strong support from the SuperCollider language, as well as others. While not officially within
            scope of the Scintillator project itself, it is expected that programming languages outside of SuperCollider
            can control scinsynth via OSC in a manner very similar to scsynth or supernova.
        </td>
    </tr>
</table>

## Scintillator Features

The following is an overview of features that are either planned or already implemented for Scintillator. However, the
[GitHub Issues List](https://github.com/ScintillatorSynth/Scintillator/issues) remains the canonical list of issues and
features planned for Scintillator.

<table>
    <tr>
        <th>Feature</th>
        <th>State</th>
        <th>Comments</th>
    </tr>
    <tr>
        <td>Hardware-Accelerated Video Synthesis</td>
        <td>Done/Ongoing</td>
        <td>
            Because the Scintillator synth uses Vulkan, the next-generation graphics API, for all video rendering, it is
            ready to take full advantage of the power of modern graphics hardware.
        </td>
    </tr>
    <tr>
    </tr>
</table>
{{% /blocks/section %}}

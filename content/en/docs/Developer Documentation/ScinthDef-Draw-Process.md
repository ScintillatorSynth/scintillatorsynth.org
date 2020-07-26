---
title: ScinthDef Draw Process
linkTitle: ScinthDef Draw Process
weight: 5
description: How a ScinthDef is composed into Vulkan commands
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Guides/Scintillator-User-Guide" >}}">Scintillator User Guide</a> <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> 



### Draw Process Overview
---



ScinthDefs are provided to the server as a topologically sorted graph of VGens, proceeding from input to output. We require the final output VGen of every ScinthDef to be running at pixel rate and with a single four-dimensional output. As input to that final output VGen there can be constants, parameters, or other VGens running at pixel or slower rates. Any VGen can be a <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> or descendent VGen, which has dependencies on input images and Vulkan sampler configuration. Individual VGens may also rely on supporting shader <em>intrinsics</em>, detailed in the <a href="{{< ref "/docs/Developer Documentation/VGen-File-Format" >}}">VGen File Format</a> document, that add data dependencies. This document describes how this input is mapped to Vulkan constructs that are ready to be rendered per Scinth instance. Familiarity with Vulkan is assumed.



Constants are always hard-coded into shaders, and parameters are provided via push constant.



Frame-rate VGens, if any, are compiled together into a compute shader. Each frame, all running Scinths are asked to provide a secondary Vulkan command buffer, aggregated with the others into a primary command buffer. This is submitted to the compute command queue before the draw commands for any Scinth are created. Data transfer into and out of the compute shaders is done via a single uniform buffer, and the memory barriers are configured such that each compute shader will complete execution before the individual Scinth dependent draw commands will execute. As the compute shaders have their own descriptor pools we track any dependent Sampler inputs. If the ScinthDef has parameters and a compute shader the parameters are always provided via push constant to the compute shader.



Shape-rate VGens are grouped into a vertex shader, and pixel-rate VGens are grouped into the fragment shader, with that final output pixel-rate VGen configured to output to the output reserved name <code>gl_FragColor</code>. Shape-rate and pixel-rate VGens run within a draw call. The geometry drawn right now is hard-coded to a full-screen quad drawn as a triange strip of two triangles. Alpha blending is enabled between Scinths and each Scinth is rendered to the output frame buffer in order from oldest to newest.


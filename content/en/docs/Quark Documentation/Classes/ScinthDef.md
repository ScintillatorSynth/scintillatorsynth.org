---
title: ScinthDef
linkTitle: ScinthDef
date: 2020-03-31
weight: 5
description: Represents a Scintillator Synth Definition
---

## Description
ScinthDef is intended to be the visual synthesis paralell to the audio synthesis class Classes/SynthDef. The Scintillator server uses ScinthDefs as templates from which to build Classes/Scinth nodes, which run on the server to produce imagery.Graphics programming is inherently multidimensional, with video hardware designed to handle arithmetic operations on 8 or more signals simulatenously. This is called "Single Instruction Multiple Data" or SIMD and while many modern CPUs offer extensions allowing programming using SIMD, the presence and support of SIMD is assumed as essential on all graphics hardware. ScinthDefs are expected to produce a 4-channel output signal, with the values in each channel representing red, green, blue, and alpha color values respectively. This vector programming is a difference in the approach to video synthesis than in audio, where the default output is monophonic, and higher channel counts are supported with multichannel expansion.But multichannel expansion doesn't support simultaneous operations on the same signal. For example, let's say that we wanted to have four channels of audio output with different frequencies for each, something like:
```
(
~quad = SynthDef.new(\quad, {
    Out.ar(0, SinOsc.ar([220, 440, 880, 1760]));
}).add;
)
```
The signal graph for this SynthDef is going to produce four different SinOsc UGens, one each with the corresponding frequency inputs. However, modern graphics hardware is designed to compute sine and cosines (along with many other operations) in a SIMD fashion, meaning that a GPU can perform a sine on four different signals *in a single instruction*, provided the signals are packed into the appropriate data structure, in this case a ```vec4```, or fourth-dimensional vector.Given that the ```vec4``` represents a single signal, in this case a complete color signal, Scintillator supports higher-dimensional signals without requiring multichannel expansion. What this means is that a single variable can represent up to a four-dimensional vector, and VGens can accept inputs and produce outputs of varying dimensions. Each VGen specifices the supported input and output dimensions by providing two methods, ```inputDimensions``` and ```outputDimensions```, each of which must return Classes/Arrays of the same length. The ith element of each both arrays, taken as a pair, represents one combination of supported input and output dimensions. For more information about these methods please see the Classes/VGen documentation.When constructing a ScinthDef the VGens are subject to a validation step called *dimensional analysis*. The ScinthDef programming examines the dimension of each input and output for each VGen, and determines if the dimensions are a supported configuration. Looking at the following example code:
```
(
~vquad = ScinthDef.new(\vquad, {
    var length = Length.fg(NormPos.fg);
    ScinOsc.fg(Vec4.fg(1.0, 1.5, 2.0, 3.0), Vec4.fg(length, length * 2, length * 4, length * 8),
        Splat4.fg(0.5), Splat4.fg(0.5));
}).add;
)
```
From the top, the Classes/NormPos VGen takes no inputs and produces a single two-dimensional output, which is the sole input to the Classes/Length VGen. The Length VGen can accept inputs with dimesions from 1 to 4, and always produces a single-dimensional output, the scalar length of the input vector, in this case stored in the ```length``` variable.The Classes/ScinOsc VGen takes four inputs, just like its analagous audio class Classes/SinOsc, and they are ```freq```, ```phase```, ```mul```, and ```add```. The default values of ```mul``` and ```add``` are adjusted to reflect the fact that VGens produce output within the range of [0.0, 1.0] instead of the audio [-1.0, 1.0] range. Like Length, ScinOsc can accept inputs from 1 to 4 dimensions, but unlike Length, it will produce a single output of the same dimension as the inputs. So, a ScinOsc with 4 four-dimensional inputs, like in this case, will produce a single four-dimensional output.The ```freq``` and ```phase``` arguments to ScinOsc here use the Classes/Vec4 VGen, which takes 4 one-dimensional inputs and merges those into a single four-dimensional output. The ```mul``` and ```add``` arguments are left to defaults, but because ScinOsc requires that all of its inputs are the same dimension, we use the Classes/Splat4 VGen to make a four-dimensional vector out of a single one-dimensional input. Because the default arguments to ```mul``` and ```add``` are single-dimensional, we have to explicitly specify the higher-dimensional inputs to the ScinOsc, or it will fail dimensional analysis.This VGen will perform a single sine computation per pixel in the output, producing the four-dimensional output signal with is interpreted by the graphics hardware as the red, blue, green, and alpha color values at each pixel to produce an image.{{% alert title="Note" %}}
A planned "quality of life" improvement for ScinthDef dimensional analysis is called "autosplat", which would detect a dimension mismatch with constant inputs and add appropriate Splats to fix as needed. But, for now, they must be specified manually.{{% /alert %}}

## Class Methods

## Instance Methods

## Examples

```
(
~t = ScinthDef.new(\t, {
    BWOut.fg(ScinOsc.fg(1.0, Length.fg(NormPos.fg)));
});
)

(
~k = Scinth.new(\t, 1);
)
```

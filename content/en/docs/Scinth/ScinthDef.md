---
title: ScinthDef
linkTitle: ScinthDef
weight: 5
description: Represents a Scintillator Synth Definition
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a> <a href="{{< ref "/docs/Guides/Scintillator-Parallel-Classes" >}}">Scintillator Parallel Classes</a> <a href="{{< ref "/docs/Developer Documentation/Scintillator-ScinthDef-File-Format" >}}">Scintillator ScinthDef File Format</a> 



## Description
---



ScinthDef is intended to be the visual synthesis paralell to the audio synthesis class <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>. The Scintillator server uses ScinthDefs as templates from which to build <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> nodes, which run on the server to produce imagery.



#### Dimensional Analysis



Graphics programming is inherently multidimensional, with video hardware designed to handle arithmetic operations on 8 or more signals simulatenously. This is called "Single Instruction Multiple Data" or SIMD and while many modern CPUs offer extensions allowing programming using SIMD, the presence and support of SIMD is assumed as essential on all graphics hardware. ScinthDefs are expected to produce a 4-channel output signal, with the values in each channel representing red, green, blue, and alpha color values respectively. This vector programming is a difference in the approach to video synthesis than in audio, where the default output is monophonic, and higher channel counts are supported with multichannel expansion.



But multichannel expansion doesn't support simultaneous operations on the same signal. For example, let's say that we wanted to have four channels of audio output with different frequencies for each, something like:



{{< highlight supercollider >}}
(
~quad = SynthDef.new(\quad, {
    Out.ar(0, SinOsc.ar([220, 440, 880, 1760]));
}).add;
)
{{< /highlight >}}



The signal graph for this SynthDef is going to produce four different SinOsc UGens, one each with the corresponding frequency inputs. However, modern graphics hardware is designed to compute sine and cosines (along with many other operations) in a SIMD fashion, meaning that a GPU can perform a sine on four different signals <em>in a single instruction</em>, provided the signals are packed into the appropriate data structure, in this case a <code>vec4</code>, or fourth-dimensional vector.



Given that the <code>vec4</code> represents a single signal, in this case a complete color signal, Scintillator supports higher-dimensional signals without requiring multichannel expansion. What this means is that a single variable can represent up to a four-dimensional vector, and VGens can accept inputs and produce outputs of varying dimensions. Each VGen specifices the supported input and output dimensions by providing two methods, <code>inputDimensions</code> and <code>outputDimensions</code>, each of which must return <a href="https://doc.sccode.org/Classes/Array.html">Array <img src="/images/external-link.svg" class="one-liner"></a>s of the same length. The ith element of each both arrays, taken as a pair, represents one combination of supported input and output dimensions. For more information about these methods please see the <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> documentation.



When constructing a ScinthDef the VGens are subject to a validation step called <em>dimensional analysis</em>. The ScinthDef programming examines the dimension of each input and output for each VGen, and determines if the dimensions are a supported configuration. Looking at the following example code:



{{< highlight supercollider >}}
(
~vquad = ScinthDef.new(\vquad, {
    var length = Length.fr(NormPos.fr);
    VSinOsc.fr(
        Vec4.fr(1.0, 1.5, 2.0, 3.0),
        Vec4.fr(length, length * 2, length * 4, length * 8),
        Splat4.fr(0.5),
        Splat4.fr(0.5));
}).add;
)
{{< /highlight >}}



From the top, the Classes/NormPos VGen takes no inputs and produces a single two-dimensional output, which is the sole input to the Classes/Length VGen. The Length VGen can accept inputs with dimesions from 1 to 4, and always produces a single-dimensional output, the scalar length of the input vector, in this case stored in the <code>length</code> variable.



The <a href="{{< ref "/docs/VGens/Video Oscillators/VSinOsc" >}}">VSinOsc</a> VGen takes four inputs, just like its analagous audio class <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, and they are <code>freq</code>, <code>phase</code>, <code>mul</code>, and <code>add</code>. The default values of <code>mul</code> and <code>add</code> are adjusted to reflect the fact that VGens produce output within the range of [0.0, 1.0] instead of the audio [-1.0, 1.0] range. Like Length, VSinOsc can accept inputs from 1 to 4 dimensions, but unlike Length, it will produce a single output of the same dimension as the inputs. So, a VSinOsc with 4 four-dimensional inputs, like in this case, will produce a single four-dimensional output.



The <code>freq</code> and <code>phase</code> arguments to VSinOsc here use the Classes/Vec4 VGen, which takes 4 one-dimensional inputs and merges those into a single four-dimensional output. The <code>mul</code> and <code>add</code> arguments are left to defaults, but because VSinOsc requires that all of its inputs are the same dimension, we use the Classes/Splat4 VGen to make a four-dimensional vector out of a single one-dimensional input. Because the default arguments to <code>mul</code> and <code>add</code> are single-dimensional, we have to explicitly specify the higher-dimensional inputs to the VSinOsc, or it will fail dimensional analysis.



This VGen will perform a single sine computation per pixel in the output, producing the four-dimensional output signal with is interpreted by the graphics hardware as the red, blue, green, and alpha color values at each pixel to produce an image.

{{% alert title="Note" %}}


A planned "quality of life" improvement for ScinthDef dimensional analysis is called "autosplat", which would detect a dimension mismatch with constant inputs and add appropriate Splats to fix as needed. But, for now, they must be specified manually.

{{% /alert %}}


## Class Methods
---



### ScinthDef.new(name, vGenGraphFunc, shape, renderOptions)



#### Arguments

##### name



<strong>A symbol.</strong> The name to associate with this ScinthDef, for creating <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> instances from this definition.



##### vGenGraphFunc



<strong>A function.</strong> The graph function defining the how the <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> objects are connected.



##### shape



<strong>An optional Shape object.</strong> Describes the geometry to render the ScinthDef with. See the Classes/Shape documentation for more details.



##### renderOptions



<strong>An optional IndentityDictionary.</strong> The contents detail render options for this ScinthDef. Supported keys are as follows:


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>values</strong>

</td></tr>
<tr><td>

<code>polygonMode</code>

</td><td>
<table>
<tr><td>

<code>\fill</code>

</td><td>

The default, fills the polygons completely.

</td></tr>
<tr><td>

<code>\line</code>

</td><td>

Outlines the polygons only.

</td></tr>
<tr><td>

<code>\point</code>

</td><td>

Draws the vertices as points only.

</td></tr>

</table>
</td></tr>

</table>




#### Returns:



A new ScinthDef object.



## Instance Methods
---



### .add(server, completionMsg)



#### Arguments

##### server



A <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> instance, or nil, in which case the default ScinServer is chosen. The server to send the Scinth definition to.



##### completionMsg



<strong>An array</strong>, or nil. An optional message to be processed by the server once the ScinthDef has been processed.





### .asYAML(indentDepth: 0)



Returns a string in YAML format conforming to the <a href="{{< ref "/docs/Developer Documentation/Scintillator-ScinthDef-File-Format" >}}">Scintillator ScinthDef File Format</a> that describes this ScinthDef.



#### Arguments

##### indentDepth



<strong>An integer.</strong> How many spaces to indent the generated yaml, for embedding this string into a larger YAML document.





#### Returns:



A yaml string.



### .free



Removes the ScinthDef from the server. Any running Scinths are not impacted, but further requests to create Scinths from this ScinthDef will fail.



### .func



### .func = value


#### Returns:



Returns this definition's VGen Graph function.



### .name



### .name = value


#### Returns:



Returns this definition's name.



## Examples
---



{{< highlight supercollider >}}
(
~t = ScinthDef.new(\t, {
    BWOut.fr(VSinOsc.fr(1.0, Length.fr(NormPos.fr)));
});
)

(
~k = Scinth.new(\t, 1);
)
{{< /highlight >}}






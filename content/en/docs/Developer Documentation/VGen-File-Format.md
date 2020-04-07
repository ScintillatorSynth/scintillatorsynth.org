---
title: VGen File Format
linkTitle: VGen File Format
date: 2020-04-06
weight: 5
description: Description of VGen yaml file format used by Scintillator synth server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> Reference/Scintillator-Scinth-Definition-File-Format 



### File Format
---



A VGen file may contain multiple VGen definitions. Each definition at top level is a yaml dictionary with required and optional keys.


<table>
<tr><td>

<strong>key name</strong>

</td><td>

<strong>type</strong>

</td><td>

<strong>required or optional</strong>

</td><td>

<strong>description</strong>

</td></tr>
<tr><td>

<code>name</code>

</td><td>

string

</td><td>

<strong>required</strong>

</td><td>

The name of the VGen, must be unique. Used to identify this VGen in ScinthDef descriptions.

</td></tr>
<tr><td>

<code>sampler</code>

</td><td>

boolean

</td><td>

optional

</td><td>

If present, specifies if this VGen is a sampling VGen that will take an image input. If not present, the value is assumed to be false.

</td></tr>
<tr><td>

<code>inputs</code>

</td><td>

list

</td><td>

optional

</td><td>

A list identifying input names in the shader program. The ordering of inputs here must match the ordering of inputs on the ScinthDef, as those inputs can be supplied unnamed.

</td></tr>
<tr><td>

<code>outputs</code>

</td><td>

list

</td><td>

<strong>required</strong>

</td><td>

A list of strings identifying names of output parameters. At least one element must exist in this list.

</td></tr>
<tr><td>

<code>dimensions</code>

</td><td>

list

</td><td>

<strong>required</strong>

</td><td>

A list of dictionaries of keys mapping dimension on inputs to dimension on outputs of the VGen (see below).

</td></tr>
<tr><td>

<code>shader</code>

</td><td>

string

</td><td>

<strong>required</strong>

</td><td>

The GLSL shader program, with inputs, intrinsics, and intermediates, as well as exactly one instance of the keyword "@out" prefixed with @ symbols.

</td></tr>

</table>


#### dimensions list



The dimensions list enumerates the valid dimensions of input to, and output from, the VGen. As shader programs rely heavily on SIMD this means that many different configurations of output and input dimensions are typically supported by the built-in functions. The <code>dimension</code> key must be present in a valid VGen and contain a list with one or more dictionary elements with the following keys:


<table>
<tr><td>

<strong>key name</strong>

</td><td>

<strong>type</strong>

</td><td>

<strong>required or optional</strong>

</td><td>

<strong>description</strong>

</td></tr>
<tr><td>

<code>inputs</code>

</td><td>

list or integer (see note)

</td><td>

<strong>required</strong> if VGen has inputs

</td><td>

Describes the dimensionality of each input signal.

</td></tr>
<tr><td>

<code>outputs</code>

</td><td>

list or integer (see note)

</td><td>

<strong>required</strong>

</td><td>

Describes the dimensionality of each output signal.

</td></tr>

</table>
{{% alert title="Note" %}}


If a list, must have the same number of integer elements as there are number of inputs or outputs, each integer describing the dimensionality of inputs or outputs in order. If a single integer, all inputs or outputs are assumed to have that dimensionality.

{{% /alert %}}


### Input and Output Dimensions
---



Current VGen design assumes zero or more inputs, and exactly one output from each VGen. VGen inputs and outputs each have a <em>dimension</em> value from 1 to 4.



### Intrinsics
---



Some @names are reserved and will be automatically supplied to the VGen at compilation or runtime. They are described in short table form, with detailed descriptions below.


<table>
<tr><td>

<strong>name</strong>

</td><td>

<strong>dimensons</strong>

</td><td>

<strong>brief description</strong>

</td></tr>
<tr><td>

<code>@normPos</code>

</td><td>

2

</td><td>

Position of vertex or fragment in normalized space.

</td></tr>
<tr><td>

<code>@sampler</code>

</td><td>

1

</td><td>

The curently configured and bound sampler and image. Only usable in sampling VGens.

</td></tr>
<tr><td>

<code>@time</code>

</td><td>

1

</td><td>

The runtime of the Scinth instance in seconds.

</td></tr>
<tr><td>

<code>@texPos</code>

</td><td>

2

</td><td>

Position of the fragment in texture coordinates space.

</td></tr>

</table>


#### @normPos



The <em>normalized</em> position of the vertex or fragment. This is computed to allow for image coordinates with a 1:1 aspect ratio even though the output window or framebuffer might be rectangular. The @normPos is always a 2 dimensional value and sets up coordinate systems such that the image in smaller of width or height will run from -1.0 to 1.0 in normPos space, with the larger size running under -1.0 and over 1.0. So, for instance, if the image is a tall rectangle with height twice that of width, the normPos will run from -1.0 to 1.0 in width, and -2.0 to 2.0 in height.



#### @sampler



In sampling VGens, represents the currently bound Vulkan sampler object and image within the shader.



#### @time



The VGen will receive a upated value every frame which contains the elapsed time in seconds from when the Scinth instance started. The time intrinsic is always 1 dimensional.



#### @texPos



Texture coordinates are typically constrained within [0, 1] inclusive, with Samplers configured to do different things outside of the texture coordinate region. The synth will configure a shape to include texture coordinate information with the vertex and fragment shaders if this intrinsic is present. Texture coordinates are currently always assumed to be 2 dimensional.


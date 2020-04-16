---
title: Scintillator ScinthDef File Format
linkTitle: Scintillator ScinthDef File Format
date: 2020-04-14
weight: 5
description: Description of ScinthDef file format used by Scintillator synth server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="https://doc.sccode.org/Reference/Synth-Definition-File-Format.html">Synth-Definition-File-Format <img src="/images/external-link.svg" class="one-liner"></a> 



This is the documentation for the file format used to describe a Video synth, called a ScinthDef, to the Scintillator synth server, scinsynth. The development file format is in YAML, making for easier human readability and debugging. However, despite the difference in format from SuperCollider SynthDef files, the ScinthDef files mostly follow the same structure so should look conceptually familiar. The primary differences are:

<ul>
<li>

YAML can provide the length of a list of objects and so we omit sizes of lists as separate fields.

<li>

Constants are defined inline, instead of at the header block at the top of the file.

</ul>


### File Format
---



#### Top-Level Dictionary Spec



A ScinthDef file contains one or more YAML documents. Each document roughly follows the SuperCollider SynthDef structure except for that Constants are not identified verbatim but are rather provided directly in the input spec, in keeping with the desire to allow ScinthDef files to be human-readable, as well as the fact that the shader generation code inside of the synth does not require the constants to be separated out.


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>name</code>

</td><td>

string

</td><td>

name of the ScinthDef, used as primary means of identification

</td></tr>
<tr><td>

<code>parameters</code>

</td><td>

list

</td><td>

An <em>optional</em> key, if the ScinthDef has parameters, will be Parameter YAML dictionaries in an ordered list

</td></tr>
<tr><td>

<code>vgens</code>

</td><td>

list

</td><td>

the VGen YAML dictionaries in an ordered list

</td></tr>

</table>


#### Parameter Dictionary Spec


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>name</code>

</td><td>

string

</td><td>

Name of the parameter.

</td></tr>
<tr><td>

<code>defaultValue</code>

</td><td>

float

</td><td>

The default value of the parameter.

</td></tr>

</table>


#### VGen Dictionary Spec



Individual VGens are specified as YAML dictionaries and have the following keys:


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>className</code>

</td><td>

string

</td><td>

name of the VGen class, must match the name of a VGen configured on the server

</td></tr>
<tr><td>

<code>rate</code>

</td><td>

string

</td><td>

currently either <code>fragment</code> or <code>vertex</code>

</td></tr>
<tr><td>

<code>sampler</code>

</td><td>

dictionary

</td><td>

Only required for sampling VGens. Specifies sampler parameters.

</td></tr>
<tr><td>

<code>inputs</code>

</td><td>

list

</td><td>

input YAML dictionaries in an ordered list. May be absent if VGen has no inputs.

</td></tr>
<tr><td>

<code>outputs</code>

</td><td>

list

</td><td>

output YAML dictionaries in an ordered list

</td></tr>

</table>


#### VGen Sampler Dictionary Spec



Sampling VGens must include the sampler dictionary, which contains configuration data for the image sampler. For more details see the documentation on <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> parameters.


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>image</code>

</td><td>

integer

</td><td>

Depending on <code>imageArgType</code>, either an image buffer number or the index of the parameter expected to contain the image buffer number.

</td></tr>
<tr><td>

<code>imageArgType</code>

</td><td>

string

</td><td>

Either <code>constant</code>, in which case <code>image</code> must be a valid image buffer number, or <code>parameter</code>, in which case <code>image</code> must be a valid parameter index.

</td></tr>
<tr><td>

<code>minFilterMode</code>

</td><td>

string

</td><td>

<em>Optional.</em> Either <code>linear</code> or <code>nearest</code>. Default is <code>linear</code>.

</td></tr>
<tr><td>

<code>magFilterMode</code>

</td><td>

string

</td><td>

<em>Optional.</em> Either <code>linear</code> or <code>nearest</code>. Default is <code>linear</code>.

</td></tr>
<tr><td>

<code>enableAnisotropicFiltering</code>

</td><td>

boolean

</td><td>

<em>Optional</em>. Default is <code>true</code>.

</td></tr>
<tr><td>

<code>addressModeU</code>

</td><td>

string

</td><td>

<em>Optional.</em> One of <code>repeat</code>, <code>mirroredRepeat</code>, <code>clampToEdge</code>, or <code>clampToBorder</code>. Default is <code>clampToBorder</code>.

</td></tr>
<tr><td>

<code>addressModeV</code>

</td><td>

string

</td><td>

<em>Optional.</em> One of <code>repeat</code>, <code>mirroredRepeat</code>, <code>clampToEdge</code>, or <code>clampToBorder</code>. Default is <code>clampToBorder</code>.

</td></tr>
<tr><td>

<code>clampBorderColor</code>

</td><td>

string

</td><td>

<em>Optional.</em> Ignored unless <code>addressMode</code> is <code>clampToBorder</code>, in which case it is one of <code>transparentBlack</code>, <code>black</code>, or <code>white</code>. Default is <code>transparentBlack</code>.

</td></tr>

</table>


#### VGen Input Dictionary Spec



Inputs to VGens are polymorphic, and takes on a different structure depending on what kind of input is specified in the <code>type</code> field. All input dictionaries supply the <code>type</code> key as well as an optional <code>name</code> field:


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>name</code>

</td><td>

string

</td><td>

An <em>optional</em> string, provided for readability

</td></tr>
<tr><td>

<code>dimension</code>

</td><td>

integer

</td><td>

The dimensionality of the input

</td></tr>
<tr><td>

<code>type</code>

</td><td>

string

</td><td>

An enumerated type, for possible values see below

</td></tr>

</table>


The rest of the keys in the Input dictionary are a function of type and are detailed here:


<table>
<tr><td>

<strong>type</strong>

</td><td>

<strong>format</strong>

</td></tr>
<tr><td>

<code>vgen</code>

</td><td>
<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>vgenIndex</code>

</td><td>

int

</td><td>

The index of the VGen providing output to this input

</td></tr>
<tr><td>

<code>outputIndex</code>

</td><td>

int

</td><td>

The output index on that VGen

</td></tr>

</table>




</td></tr>
<tr><td>

<code>constant</code>

</td><td>
<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>value</code>

</td><td>

float

</td><td>

All constants will be treated as floating point numbers

</td></tr>

</table>




</td></tr>
<tr><td>

<code>parameter</code>

</td><td>
<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>index</code>

</td><td>

int

</td><td>

The parameter index to use.

</td></tr>

</table>
</td></tr>

</table>


#### VGen Output Dictionary Spec



Output entries specify dimensions on each output.


<table>
<tr><td>

<strong>key</strong>

</td><td>

<strong>YAML type</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

<code>dimension</code>

</td><td>

integer

</td><td>

The dimension of this output.

</td></tr>

</table>


### Example
---



We execute the following code:



{{< highlight supercollider >}}
(
~k = ScinthDef.new(\foo, {
    VOut.fg(0, ScinOsc.fg(200.0, 0.0, 0.9, 0.2));
});
~k.asYAML.postln;
)
{{< /highlight >}}



And ScinthDef produces the following:



{{< highlight supercollider >}}
- name: foo
  vgens:
    - className: ScinOsc
      rate: fragment
      inputs:
        - type: constant
          dimension: 1
          value: 200.0
        - type: constant
          dimension: 1
          value: 0.0
        - type: constant
          dimension: 1
          value: 0.9
        - type: constant
          dimension: 1
          value: 0.2
      outputs:
        - dimension: 1
    - className: VOut
      rate: fragment
      inputs:
        - type: constant
          value: 0
        - type: vgen
          vgenIndex: 0
          outputIndex: 0
{{< /highlight >}}






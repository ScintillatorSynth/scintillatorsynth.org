---
title: Scintillator ScinthDef File Format
linkTitle: Scintillator ScinthDef File Format
date: 2020-03-31
weight: 5
description: Description of ScinthDef file format used by Scintillator synth server.
---
This is the documentation for the file format used to describe a Video synth, called a ScinthDef, to the Scintillator synth server, scinsynth. The development file format is in YAML, making for easier human readability and debugging. However, despite the difference in format from SuperCollider SynthDef files, the ScinthDef files mostly follow the same structure so should look conceptually familiar. The primary differences are:<ul>
<li>YAML can provide the length of a list of objects and so we omit sizes of lists as separate fields.<li>Constants are defined inline, instead of at the header block at the top of the file.</ul>
<div id='nil'>A ScinthDef file contains one or more YAML documents. Each document roughly follows the SuperCollider SynthDef structure except for that Constants are not identified verbatim but are rather provided directly in the input spec, in keeping with the desire to allow ScinthDef files to be human-readable, as well as the fact that the shader generation code inside of the synth does not require the constants to be separated out.<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```name```<td>string<td>name of the ScinthDef, used as primary means of identification<tr><td>```parameters```<td>list<td>An *optional* key, if the ScinthDef has parameters, will be Parameter YAML dictionaries in an ordered list<tr><td>```vgens```<td>list<td>the VGen YAML dictionaries in an ordered list</table>
<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```name```<td>string<td>Name of the parameter.<tr><td>```defaultValue```<td>float<td>The default value of the parameter.</table>
Individual VGens are specified as YAML dictionaries and have the following keys:<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```className```<td>string<td>name of the VGen class, must match the name of a VGen configured on the server<tr><td>```rate```<td>string<td>currently either ```fragment``` or ```vertex```<tr><td>```sampler```<td>dictionary<td>Only required for sampling VGens. Specifies sampler parameters.<tr><td>```inputs```<td>list<td>input YAML dictionaries in an ordered list. May be absent if VGen has no inputs.<tr><td>```outputs```<td>list<td>output YAML dictionaries in an ordered list</table>
Sampling VGens must include the sampler dictionary, which contains configuration data for the image sampler. For more details see the documentation on Classes/Sampler parameters.<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```image```<td>integer<td>Depending on ```imageArgType```, either an image buffer number or the index of the parameter expected to contain the image buffer number.<tr><td>```imageArgType```<td>string<td>Either ```constant```, in which case ```image``` must be a valid image buffer number, or ```parameter```, in which case ```image``` must be a valid parameter index.<tr><td>```minFilterMode```<td>string<td>*Optional.* Either ```linear``` or ```nearest```. Default is ```linear```.<tr><td>```magFilterMode```<td>string<td>*Optional.* Either ```linear``` or ```nearest```. Default is ```linear```.<tr><td>```enableAnisotropicFiltering```<td>boolean<td>*Optional*. Default is ```true```.<tr><td>```addressModeU```<td>string<td>*Optional.* One of ```repeat```, ```mirroredRepeat```, ```clampToEdge```, or ```clampToBorder```. Default is ```clampToBorder```.<tr><td>```addressModeV```<td>string<td>*Optional.* One of ```repeat```, ```mirroredRepeat```, ```clampToEdge```, or ```clampToBorder```. Default is ```clampToBorder```.<tr><td>```clampBorderColor```<td>string<td>*Optional.* Ignored unless ```addressMode``` is ```clampToBorder```, in which case it is one of ```transparentBlack```, ```black```, or ```white```. Default is ```transparentBlack```.</table>
Inputs to VGens are polymorphic, and takes on a different structure depending on what kind of input is specified in the ```type``` field. All input dictionaries supply the ```type``` key as well as an optional ```name``` field:<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```name```<td>string<td>An *optional* string, provided for readability<tr><td>```dimension```<td>integer<td>The dimensionality of the input<tr><td>```type```<td>string<td>An enumerated type, for possible values see below</table>
The rest of the keys in the Input dictionary are a function of type and are detailed here:<table>
<tr><td>**type**<td>**format**<tr><td>```vgen```<td><table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```vgenIndex```<td>int<td>The index of the VGen providing output to this input<tr><td>```outputIndex```<td>int<td>The output index on that VGen</table>
<tr><td>```constant```<td><table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```value```<td>float<td>All constants will be treated as floating point numbers</table>
<tr><td>```parameter```<td><table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```index```<td>int<td>The parameter index to use.</table>
</table>
Output entries specify dimensions on each output.<table>
<tr><td>**key**<td>**YAML type**<td>**notes**<tr><td>```dimension```<td>integer<td>The dimension of this output.</table>
<div id='nil'>We execute the following code:
```
(
~k = ScinthDef.new(\foo, {
    VOut.fg(0, ScinOsc.fg(200.0, 0.0, 0.9, 0.2));
});
~k.asYAML.postln;
)
```
And ScinthDef produces the following:
```
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
```

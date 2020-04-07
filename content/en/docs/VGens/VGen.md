---
title: VGen
linkTitle: VGen
date: 2020-04-06
weight: 5
description: Abstract superclass of all visual unit generators.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> 



## Description
---



VGens are the atomic units of computation on the Scintillator visual synthesizer. The analagous class in audio synthesis is the <a href="https://doc.sccode.org/Classes/UGen.html">UGen <img src="/images/external-link.svg" class="one-liner"></a>. As the base class for all VGens it does no visual synthesis itself, so wouldn't normally be used directly as part of a <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>.



## Class Methods
---



#### Inherited class methods



## Instance Methods
---



### .scinthDef



### .scinthDef = value


A reference to the containing <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>. Used as part of building the ScinthDef.



### .outDims



### .outDims = value


Used as part of the ScinthDef building process. An array representing the selected output dimensions from those supported within <code>outputDimensions</code>, as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> documentation.



### .inDims



### .inDims = value


Used as part of the ScinthDef building process. An array representing the selected input dimensions from those supported within <code>inputDimensions</code>, as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> documentation.



### .inputDimensions



An array of arrays representing all accepted input dimensions, corresponding at each index with the same output dimension entries.



### .outputDimensions



An array of arrays representing all accepted output dimensions, corresponding at each index with the same input dimension entries.



### .name



The class name of this VGen, as determined at ScinthDef build time. Used for building ScinthDef output.



### .rate



### .rate = value


A symbol describing the rate of the VGen instance, although currently only <code>\fragment</code> is supported.



#### Inherited instance methods


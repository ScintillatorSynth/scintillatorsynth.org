---
title: VGen
linkTitle: VGen
date: 2020-04-01
weight: 5
description: Abstract superclass of all visual unit generators.
---


## Description

VGens are the atomic units of computation on the Scintillator visual synthesizer. The analagous class in audio synthesis is the Classes/UGen.

## Instance Methods


#### .-scinthDef

</h3>
<div class='method'>A reference to the containing Classes/ScinthDef. Used as part of building the ScinthDef.</div>

#### .-outDims

</h3>
<div class='method'>Used as part of the ScinthDef building process. An array representing the selected output dimensions from those supported within <code>outputDimensions</code>, as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the Classes/ScinthDef documentation.</div>

#### .-inDims

</h3>
<div class='method'>Used as part of the ScinthDef building process. An array representing the selected input dimensions from those supported within <code>inputDimensions</code>, as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the Classes/ScinthDef documentation.</div>

#### .-inputDimensions

</h3>
<div class='method'>An array of arrays representing all accepted input dimensions, corresponding at each index with the same output dimension entries.</div>

#### .-outputDimensions

</h3>
<div class='method'>An array of arrays representing all accepted output dimensions, corresponding at each index with the same input dimension entries.</div>

#### .-name

</h3>
<div class='method'>The class name of this VGen, as determined at ScinthDef build time. Used for building ScinthDef output.</div>

#### .-rate

</h3>
<div class='method'>A symbol describing the rate of the VGen instance, although currently only <code>\fragment</code> is supported.</div>

## Examples

<code>(some example code)</code>

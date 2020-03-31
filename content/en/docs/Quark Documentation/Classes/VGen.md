---
title: VGen
linkTitle: VGen
date: 2020-03-31
weight: 5
description: Abstract superclass of all visual unit generators.
---
VGens are the atomic units of computation on the Scintillator visual synthesizer. The analagous class in audio synthesis is the Classes/UGen.
## Class Methods
buildScinthDefmultiNewListmultiNewsingleNewscinthIndexcomposeUnaryOpinitisVGenasVGenInputoutputIndexcomposeBinaryOpcomposeNAryOpreverseComposeBinaryOpnumOutputsisSamplerVGeninputsaddToScinthisValidVGenInput### scinthDef
A reference to the containing Classes/ScinthDef. Used as part of building the ScinthDef.### outDims
Used as part of the ScinthDef building process. An array representing the selected output dimensions from those supported within , as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the Classes/ScinthDef documentation.### inDims
Used as part of the ScinthDef building process. An array representing the selected input dimensions from those supported within , as determined by ScinthDef dimensional analysis. For more information see the discussion around dimensional analysis in the Classes/ScinthDef documentation.### inputDimensions
An array of arrays representing all accepted input dimensions, corresponding at each index with the same output dimension entries.### outputDimensions
An array of arrays representing all accepted output dimensions, corresponding at each index with the same input dimension entries.### name
The class name of this VGen, as determined at ScinthDef build time. Used for building ScinthDef output.### rate
A symbol describing the rate of the VGen instance, although currently only  is supported.
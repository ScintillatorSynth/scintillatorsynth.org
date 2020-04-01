---
title: Scinth
linkTitle: Scinth
date: 2020-03-31
weight: 5
description: Represents a running synth node on the Scintillator server.
---

## Description
A Scinth is the client-side representation of a synth node on the Scintillator server. A Scinth is a single video-producing using, analagous to a Classes/Synth on the audio server. What it does is defined by a Classes/ScinthDef, which specifices which Classes/VGens are used and how they are conncted together.
## Class Methods

## Instance Methods

## Examples

```
(
~t = ScinthDef.new(\t, { |g, b|
    RGBOut.fg(ScinOsc.fg(VX.fg(NormPos.fg)), g, b);
}).add;
)

// Make a new Scinth and run it on the server.
(
~k = Scinth.new(\t, [\g, 0.5, \b, 0.3]);
)

// Change the colors of k.
(
~k.set(\g, 0.2);
)

// Pause k (will produce a black screen)
(
~k.run(false);
)

// Resume k (image is back again)
(
~k.run(true);
)

// Done with k, remove it from the server.
(
~k.free;
)
```

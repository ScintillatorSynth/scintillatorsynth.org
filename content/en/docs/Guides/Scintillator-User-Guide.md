---
title: Scintillator User Guide
linkTitle: Scintillator User Guide
weight: 5
description: User manual for the Scintillator video synthesizer.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> 



### Overview
---



Scintillator is a video synthesis extension for SuperCollider. It is distributed as a Quark, but requires an additional download and installation step to get the synthesizer program.



Scintillator is designed to be intuitive to users already familiar with SuperCollider idioms. It follows the client/server archiecture established by SuperCollider, accepts <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>s in a similar manner to SuperCollider <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>s, provides facilities to invoke and control <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> instances similar to <a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a>, and so on. For a detailed list of classes with analogous SuperCollider classes see <a href="{{< ref "/docs/Guides/Scintillator-Parallel-Classes" >}}">Scintillator Parallel Classes</a>.



### Installation Instructions
---



#### 1. Install Scintillator Quark



Run the following code:



{{< highlight supercollider >}}
(
Quarks.install("Scintillator");
)
{{< /highlight >}}



Or you can use the Quarks GUI to pick out Scintillator and install it. See <a href="https://doc.sccode.org/Guides/UsingQuarks.html">UsingQuarks <img src="/images/external-link.svg" class="one-liner"></a> for more information.



#### 2. Recompile Class Library



After any Quark installation you need to recompile the SuperCollider class library. In the IDE menu select Langage -> Recompile Class Library. This should also kick off the automatic installation of the ScinServer binary.



### Tutorial
---



This section can serve to validate your Scintillator installation, as well as to establish some of the basic concepts and get some pixels lighting up on the screen. The first step will be to get an instance of the video server running, which will require installing the correct server binary for your platform of choice. To validate that the server binary is installed correctly we'll start the server, define a <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>, and render the definition with a <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a>.



#### Starting the Server



SuperCollider by convention stores the default audio synth server in the <code>s</code> environment variable. While developing Scintillator I have often found it convenient to store the video synth server in the <code>v</code> environment variable, but storing it in any suitable variable is fine.



{{< highlight supercollider >}}
(
~v = ScinServer.new.boot;
)
{{< /highlight >}}

<img src="/images/schelp/empty-window.png" />

This should bring up an empty window with a black background. By default the window is 800 pixels wide and 600 pixels tall. It is configured to float on top of all other windows on the screen, but not to steal keyboard focus. The hope was that you could pop open these windows and still continue typing away in your SuperCollider IDE (or other editor of choice) uninterrupted. The server has many boot-time configuration options, for more details see the <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> documentation.



#### Creating a ScinthDef



The simplest imaginable <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> sets the same color everywhere on the screen. We'll use the Classes/RGBOut <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> for that.



{{< highlight supercollider >}}
(
~red = ScinthDef.new(\red, {
    RGBOut.fr(1.0, 0.0, 0.0);
}).add;
)
{{< /highlight >}}



You'll notice after running this code the screen is still black. This is because, just like the audio class <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>, the server has only received a template for creating <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a>s. We'll create a <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> now from the <code>\red</code> template.



{{< highlight supercollider >}}
(
~redScinth = Scinth.new(\red);
)
{{< /highlight >}}

<img src="/images/schelp/red-window.png" />

What's happening here is that for every frame, and at every pixel, the graphics hardware is running an instance of the <code>\red</code> ScinthDef to compute the color of that pixel. Since <code>\red</code> defines that pixel as a constant, that number defines the output everywhere, giving a field of solid red. We'll make a more interesting <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> soon, so let's turn this one off for now:



{{< highlight supercollider >}}
(
~redScinth.free;
)
{{< /highlight >}}

<img src="/images/schelp/empty-window.png" />

#### Time-Varying Video Synths



The <a href="{{< ref "/docs/VGens/Video Oscillators/VSinOsc" >}}">VSinOsc</a> oscillator is similar to the similar class <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, with the soft <em>sc</em> sound at the start intended to be a play on the name scintillator. There are some differences in a video synth from an audio one, however. The first consideration is that signals don't have to vary over time to impact the output. The first Scinth, <code>\red</code>, should prove that. A similar constant output in an audio context would be inaudible, except for that it might damage certain audo setups, so caution is advised in testing that assertion!



Another point to note is that while audio signals normally operate in the domain from -1 to +1, with negative signals indicating a moment of underpressure in the sound wave, video signals only vary from 0 to 1. Light can only be present or absent in this video synthesizer, with 0 indicating no light and 1 indicating maximum brightness of light. Most video cards will clamp output between 0 and 1, but Scintillator doesn't take any special steps to limit signals, and video signals outside of the range are typically clamped. As a result of the different range, many of the default inputs on <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a>s with analogous <a href="https://doc.sccode.org/Classes/UGen.html">UGen <img src="/images/external-link.svg" class="one-liner"></a>s are are adjusted so that instead of providing a signal input from -1 to +1 they output from 0 to 1. This can be seen, for example, in the defaults to <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, where the <code>mul</code> and <code>add</code> arguments are both <code>0.5</code>, constraining the video signal between 0 and 1.



{{< highlight supercollider >}}
(
~w = ScinthDef.new(\wave, { |f=1|
    BWOut.fr(VSinOsc.fr(freq: f));
}).add;
)

(
~k = Scinth.new(\wave);
)
{{< /highlight >}}



This should produce a 1 Hz wave, with the entire screen lightening and darkening in unison. Each frame, each pixel is subject to the same computation, and so will produce an output pixel with the same level of brightness. A few other things to note from this example. First is that the video signal at 1Hz is easily observeable, whereas a 1 Hz audio signal is below the range of human hearing. The second is to note that this Scinth has a <em>parameter</em>, just like audio <a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a> objects. Try playing around with the parameter to see the frequency change in the output.

{{% alert title="Note" %}}


Flashing warning! It is possible to set the frequency in a way that might be harmful to folks sensitive to flashing lights. Best to try and stay under 1 or 2 Hz unless you are sure you are comfortable going higher, or if you make the window very small.

{{% /alert %}}


{{< highlight supercollider >}}
(
~k.set(\f, 0.2);
)
{{< /highlight >}}



Another thing to note is that Scintillator does its best to track your visual display refresh rate. Typical consumer displays refresh at 60 Hz, sometimes slower at 30 Hz. Some gaming monitors go as high as 240 Hz. So setting a frequency higher than about 30Hz is not going to notably change the flashing frequency. This can be considered a rough visual proof of the Nyquist Theorem.



#### Space-Varying Video Synths



So we've seen time-varying signals, what about spatial variation? Let's clean up the current Scinth, and define a new Scinth using the Classes/NormPos VGen:



{{< highlight supercollider >}}
(
~k.free;
)

(
~spot = ScinthDef.new(\spot, {
    BWOut.fr(Length.fr(NormPos.fr));
}).add;
)

(
~d = Scinth.new(\spot);
)
{{< /highlight >}}

<img src="/images/schelp/spot.png" />

What's going on here? At every pixel, Classes/NormPos is producing a <em>2-dimensional</em> constant signal that varies from -1 to +1 in the y (or vertical) dimension, and from around -1.33 to +1.33 in the x (or horizontal) dimension. The Classes/NormPos documentation has details about how the coordinate system is set up. That 2D signal is then being converted by the Classes/Length VGen into a single, mono signal, which varies from 0 or black at the origin in the center of the screen, to 1 (or greater than 1) at the edges of the screen. The video hardware is clamping the signal at 1, which is why outside of the top and bottom of the image the gradient stops at the edge of the unit circle.



#### Dimensions in Video Signals



Astute readers may have noticed in the previous discussion that Classes/NormPos produces a 2D output signal for consumption by the Classes/Length VGen. There's a longer discussion about signal dimension in the "Dimensional Analysis" section of the <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> documentation. In short, video signals <em>at output</em> are always four-dimensional signals, and in Scintillator are always packed as <code>red, green, blue, alpha</code> signals. The multichannel expansion mechanism used in SuperCollider is flexible in that it allows SynthDefs to be defined for audio signals varying from a signal channel to complex multichannel ambisonic arrangements. Video signals trade a lot of this flexibility for the massive parallelism required to compute a color at each pixel 60 times per second. Furthermore, video cards are optimized to handle mathematical operations on all four channels at once in a single instruction. So instead of treating every ScinthDef as a hard-coded 4-channel signal chain, Scintillator tracks which combination of dimensions in input and output each VGen will produce, and validates at definition time if the combination of VGens and signal dimensions is valid. Furthermore, each VGen includes in its documentation a discussion of the supported pairs of input and output channels.



So, for example, the Classes/NormPos VGen accepts no inputs and produces a 2D output always. The Classes/Length VGen accepts inputs from 1 to 4 channels and computes a vector length on the input. The result of the operation is a scalar, so regardless of the dimension that the input was Classes/Length will always produce a single-dimensional output. This single-dimensional output is accepted by BWOut, which <em>splats</em> the single input into the first three <code>red, blue, green</code> output channels, and adds the hard-coded <code>alpha</code> channel at 1.0, or completely opaque.



There are helper VGens that can pack single-channel signals into multi-channel ones, these are the <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a>, and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> classes. There are also VGens for extracting single-channel signals from individual channels from multi-channel signals, these are the <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VY" >}}">VY</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a>, and <a href="{{< ref "/docs/VGens/Vector Manipulation/VW" >}}">VW</a> VGens.



Scintillator supports most common mathematical operations from 1-4 dimensions. Additionally, many vector operations support combining with scalars. For instance, it is possible to multiply a single-channel value against 1, 2, 3, or 4 channel values. For the current list of supported operations see the <a href="{{< ref "/docs/Guides/VGens-Overview" >}}">VGens Overview</a>. The important thing to understand is that multi-channel operations typically operate <em>per-channel</em>. An example might make this more clear:



{{< highlight supercollider >}}
// Clean up the \spot Scinth from the above example
(
~d.free;
)

(
~rings = ScinthDef.new(\rings, {
    var rad = Length.fr(NormPos.fr);  // rad is one-dimensional
    var rgb = rad * Vec3.fr(31, 41, 61); // rgb is 3D
    rgb = 0.5 + (rgb.sin * 0.5); // This is all 3D math!
    Vec4.fr(VX.fr(rgb), VY.fr(rgb), VZ.fr(rgb), 1.0);
}).add;
)

(
~rg = Scinth.new(\rings);
)
{{< /highlight >}}

<img src="/images/schelp/rings.png" />

Important to understand that the <code>sin</code> operation is happening on <em>each channel independently</em>. It can be instructive to re-run this example with only one color channel enabled at a time, to understand what is happening in the red, green, and blue channels independently before trying to understand how the colors are mixing in the combined image. This example also demonstrates that any 4-D vector is accepted as valid output, the Classes/RGBOut, Classes/RGBAOut, and Classes/BWOut classes are just convenience methods.



#### Variation in Time and Space



The last experiment in this quick start guide is to combine variation in both time and space together:



{{< highlight supercollider >}}
// Remove the \rings Scinth from the above example
(
~rg.free;
)

(
~zoom = ScinthDef.new(\zoom, {
    var pos = NormPos.fr;
    var box = 1.0 - max(VX.fr(pos).abs, VY.fr(pos).abs);
    BWOut.fr(VSaw.fr(phase: box));
}).add;
)

(
~z = Scinth.new(\zoom);
)

(
~z.free;
)
{{< /highlight >}}



It can be instructive to think about why the <code>phase</code> argument to VSaw is needed to create the zoom effect. Remember that all pixels run this program independently of each other, every frame.



#### Next Steps



Scintillator is still in active development. The <a href="https://scintillatorsynth.org/blog/">Development Blog <img src="/images/external-link.svg" class="one-liner"></a> typically has the most up-to-date information.



It's definitely worth perusing the <a href="{{< ref "/docs/Guides/VGens-Overview" >}}">VGens Overview</a> to get a better understanding of currently supported VGens. There are a lot of additional features planned for Scintillator, which will likely be documented in separate, independent guides. Lastly, join the conversation! Post your feedback and questions on the <a href="https://github.com/ScintillatorSynth/Scintillator">GitHub page <img src="/images/external-link.svg" class="one-liner"></a>, find me on the SuperCollider slack channel, or drop me a line over email.


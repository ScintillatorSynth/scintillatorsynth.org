---
title: Scintillator User Guide
linkTitle: Scintillator User Guide
weight: 5
description: User manual for the Scintillator visual synthesizer.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> 



In keeping with the high-energy physics themes of SuperCollider, a <em>scintillator</em> is any material that produces light when struck by radition. Scintillator is intended to be an accompanying visual synthesizer designed to be intuitive to users already familiar with SuperCollider idioms. Distributed as a Quark plus a synthesizer binary, Scintillator follows the client/server archiecture established by SuperCollider, accepts <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a>s in a similar manner to SuperCollider <a href="https://doc.sccode.org/Classes/SynthDef.html">SynthDef <img src="/images/external-link.svg" class="one-liner"></a>s, provides facilities to invoke and control <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> instances similar to <a href="https://doc.sccode.org/Classes/Synth.html">Synth <img src="/images/external-link.svg" class="one-liner"></a>, and so on. For a detailed list of classes with analogous SuperCollider classes see <a href="{{< ref "/docs/Guides/Scintillator-Parallel-Classes" >}}">Scintillator Parallel Classes</a>.



### Project Status (April 2020)
---



Scintillator is an open-source (GPLv3) project developed and maintained by Luke Nihlen. It is in early pre-alpha stage and the purpose of these first few releases is to gather feedback and identify any outstanding major bugs that might need to be resolved before considering a beta or even production-quality release. Central features are under active development, and the classes and their methods may change from point release to point release without notice. As such Scintillator is ready for review but building a larger composition or planning a performance on it may not be advisable.



All that said, it is my sincere hope you find Scintillator useful and interesting, and I'm keen to hear your feedback. Drop by the <a href="https://github.com/ScintillatorSynth/Scintillator">GitHub project page</a>, if you encounter any bugs or have any feature requests please feel free to file them there, or drop me an email at <code>scintillator.synth@gmail.com</code>.



### Installation
---



#### Quark Installation



Scintillator is distributed in two pieces and both are required in order for it to run. The first piece contains the SuperCollider classes and support, and is distributed as a Quark. Installation should be as simple as executing the following code:



{{< highlight supercollider >}}
(
Quarks.install("Scintillator");
)
{{< /highlight >}}



Or you can use the Quarks GUI to pick out Scintillator and install it. See <a href="https://doc.sccode.org/Guides/UsingQuarks.html">UsingQuarks <img src="/images/external-link.svg" class="one-liner"></a> for more information.



#### Server Binary Installation



The second half of the Scintillator distribution consists of the <code>scinsynth</code> binary, which is the C++-based visual synthesis server. Official releases of the Scintillator Quark will always have an associated server binary, which can be obtained from the <a href="https://github.com/ScintillatorSynth/Scintillator/releases">GitHub Releases Page</a>. Each platform names the server binary file slightly differently, but generally once the binary is downloaded you need to move it into the <code>/bin</code> subdirectory inside of the Scintillator Quark directory. One way to quickly find the quark directory is to use the <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> class, which computes the location by querying the Quarks system:



{{< highlight supercollider >}}
// Prints the path of the quark to the console.
(
~o = ScinServerOptions.new;
~o.quarkPath.postln;
)
{{< /highlight >}}



The binary is named and installed a bit differently on each platform:


<table>
<tr><td>

<strong>platform</strong>

</td><td>

<strong>binary name</strong>

</td><td>

<strong>post-install step</strong>

</td></tr>
<tr><td>

MacOS

</td><td>

<code>scinsynth.app.zip</code>

</td><td>

Need to extract the zip file

</td></tr>
<tr><td>

Linux

</td><td>

<code>scinsynth-x86_64.AppImage</code>

</td><td>

Need to mark the file as executable

</td></tr>
<tr><td>

Windows

</td><td>

<em>not yet supported</em>

</td><td>

Windows support planned for production release

</td></tr>

</table>


### Quick Startup
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
    RGBOut.fg(1.0, 0.0, 0.0);
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



The <a href="{{< ref "/docs/VGens/Video Oscillators/ScinOsc" >}}">ScinOsc</a> oscillator is similar to the similar class <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, with the soft <em>sc</em> sound at the start intended to be a play on the name scintillator. There are some differences in a video synth from an audio one, however. The first consideration is that signals don't have to vary over time to impact the output. The first Scinth, <code>\red</code>, should prove that. A similar constant output in an audio context would be inaudible, except for that it might damage certain audo setups, so caution is advised in testing that assertion!



Another point to note is that while audio signals normally operate in the domain from -1 to +1, with negative signals indicating a moment of underpressure in the sound wave, video signals only vary from 0 to 1. Light can only be present or absent in this video synthesizer, with 0 indicating no light and 1 indicating maximum brightness of light. Most video cards will clamp output between 0 and 1, but Scintillator doesn't take any special steps to limit signals, and video signals outside of the range are typically clamped. As a result of the different range, many of the default inputs on <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a>s with analogous <a href="https://doc.sccode.org/Classes/UGen.html">UGen <img src="/images/external-link.svg" class="one-liner"></a>s are are adjusted so that instead of providing a signal input from -1 to +1 they output from 0 to 1. This can be seen, for example, in the defaults to <a href="https://doc.sccode.org/Classes/SinOsc.html">SinOsc <img src="/images/external-link.svg" class="one-liner"></a>, where the <code>mul</code> and <code>add</code> arguments are both <code>0.5</code>, constraining the video signal between 0 and 1.



{{< highlight supercollider >}}
(
~w = ScinthDef.new(\wave, { |f=1|
    BWOut.fg(ScinOsc.fg(freq: f));
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



So we've seen time-varying signals, what about spatial variation? Let's clean up the current Scinth, and define a new Scinth using the <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> VGen:



{{< highlight supercollider >}}
(
~k.free;
)

(
~spot = ScinthDef.new(\spot, {
    BWOut.fg(Length.fg(NormPos.fg));
}).add;
)

(
~d = Scinth.new(\spot);
)
{{< /highlight >}}

<img src="/images/schelp/spot.png" />

What's going on here? At every pixel, <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> is producing a <em>2-dimensional</em> constant signal that varies from -1 to +1 in the y (or vertical) dimension, and from around -1.33 to +1.33 in the x (or horizontal) dimension. The <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> documentation has details about how the coordinate system is set up. That 2D signal is then being converted by the <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a> VGen into a single, mono signal, which varies from 0 or black at the origin in the center of the screen, to 1 (or greater than 1) at the edges of the screen. The video hardware is clamping the signal at 1, which is why outside of the top and bottom of the image the gradient stops at the edge of the unit circle.



#### Dimensions in Video Signals



Astute readers may have noticed in the previous discussion that <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> produces a 2D output signal for consumption by the <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a> VGen. There's a longer discussion about signal dimension in the "Dimensional Analysis" section of the <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> documentation. In short, video signals <em>at output</em> are always four-dimensional signals, and in Scintillator are always packed as <code>red, green, blue, alpha</code> signals. The multichannel expansion mechanism used in SuperCollider is flexible in that it allows SynthDefs to be defined for audio signals varying from a signal channel to complex multichannel ambisonic arrangements. Video signals trade a lot of this flexibility for the massive parallelism required to compute a color at each pixel 60 times per second. Furthermore, video cards are optimized to handle mathematical operations on all four channels at once in a single instruction. So instead of treating every ScinthDef as a hard-coded 4-channel signal chain, Scintillator tracks which combination of dimensions in input and output each VGen will produce, and validates at definition time if the combination of VGens and signal dimensions is valid. Furthermore, each VGen includes in its documentation a discussion of the supported pairs of input and output channels.



So, for example, the <a href="{{< ref "/docs/VGens/Intrinsics/NormPos" >}}">NormPos</a> VGen accepts no inputs and produces a 2D output always. The <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a> VGen accepts inputs from 1 to 4 channels and computes a vector length on the input. The result of the operation is a scalar, so regardless of the dimension that the input was <a href="{{< ref "/docs/VGens/Mathematics/Vector Math/Length" >}}">Length</a> will always produce a single-dimensional output. This single-dimensional output is accepted by BWOut, which <em>splats</em> the single input into the first three <code>red, blue, green</code> output channels, and adds the hard-coded <code>alpha</code> channel at 1.0, or completely opaque.



There are helper VGens that can pack single-channel signals into multi-channel ones, these are the <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec2" >}}">Vec2</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec3" >}}">Vec3</a>, and <a href="{{< ref "/docs/VGens/Vector Manipulation/Vec4" >}}">Vec4</a> classes. There are also VGens for extracting single-channel signals from individual channels from multi-channel signals, these are the <a href="{{< ref "/docs/VGens/Vector Manipulation/VX" >}}">VX</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VY" >}}">VY</a>, <a href="{{< ref "/docs/VGens/Vector Manipulation/VZ" >}}">VZ</a>, and <a href="{{< ref "/docs/VGens/Vector Manipulation/VW" >}}">VW</a> VGens.



Scintillator supports most common mathematical operations from 1-4 dimensions. Additionally, many vector operations support combining with scalars. For instance, it is possible to multiply a single-channel value against 1, 2, 3, or 4 channel values. For the current list of supported operations see the <a href="{{< ref "/docs/VGens/VGens-Overview" >}}">VGens Overview</a>. The important thing to understand is that multi-channel operations typically operate <em>per-channel</em>. An example might make this more clear:



{{< highlight supercollider >}}
// Clean up the \spot Scinth from the above example
(
~d.free;
)

(
~rings = ScinthDef.new(\rings, {
    var rad = Length.fg(NormPos.fg);  // rad is one-dimensional
    var rgb = rad * Vec3.fg(31, 41, 61); // rgb is 3D
    rgb = 0.5 + (rgb.sin * 0.5); // This is all 3D math!
    Vec4.fg(VX.fg(rgb), VY.fg(rgb), VZ.fg(rgb), 1.0);
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
    var pos = NormPos.fg;
    var box = 1.0 - max(VX.fg(pos).abs, VY.fg(pos).abs);
    BWOut.fg(VSaw.fg(phase: box));
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



It's definitely worth perusing the <a href="{{< ref "/docs/VGens/VGens-Overview" >}}">VGens Overview</a> to get a better understanding of currently supported VGens, to expand your creative options. There are a lot of additional features planned for Scintillator, which will likely be documented in separate, independent guides. Lastly, join the conversation! Post your feedback and questions on the <a href="https://github.com/ScintillatorSynth/Scintillator">GitHub page</a>, find me on the SuperCollider slack channel, or drop me a line over email.


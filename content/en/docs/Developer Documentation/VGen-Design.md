---
title: VGen Design Document
linkTitle: VGen Design Document
date: 2020-04-11
weight: 5
description: Notes about the design of a VGen.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/VGen" >}}">VGen</a> 



Theese are some early notes, and need to be reorganized, possibly moved to the devblog.



### VGen Design Concepts
---



#### Coordinate Space



By default we work in normalized coordinate spaces. The origin is at the center of the space, with X increasing to the right and Y increasing moving up. TBD on direction of Z, pending decision of handedness of Vulkan, it should be the same.



The normal extents of the space are from -Pi to +Pi in both X and Y directions. It should be possible to alter the coordinate system of a VGen, along with projection. Basically there should be functionality to alter the vertex program, and some way to specify which chain of fragment programs (VGens) it applies to.



That's a VertexSpace - a domain in which fragments are applied? There's one by default described above?



#### Pre-defined variables.



Things that every VGen gets "for free"


<table>
<tr><td>

x

</td><td>

X coordinate of fragment

</td></tr>
<tr><td>

y

</td><td>

Y coordinate of fragment

</td></tr>
<tr><td>

z

</td><td>

Z coordinate of fragment (typically 0?)

</td></tr>
<tr><td>

w

</td><td>

W coordinate of fragment (depends on coord setup)

</td></tr>
<tr><td>

r

</td><td>

computed as magnitude of x, y, z, w, if needed.

</td></tr>
<tr><td>

pixel coords as i, j, k?

</td></tr>

</table>


#### Output



By default single-channel intensity. Could look at multichannel expansion here. Let's avoid assignment of meaning to the values until its time to color pixels, meaning that the same VSynthDef can be used to do a single channel of color, or HSV, or whatever, its just tables of numbers until late.



#### Rendering



In general there's three (ish) pieces to a render, and these names are horrible so rethink:



Vertexy kinds of things (Context? Space? Extent?) The VGens, which get compiled into a fragment program A framebuffer, which has some rules about how the VGens map to pixel values.



There's some prebuilt vertex type things, like the one described in coordinate space, and it can also take some parameters. The server provides an output framebuffer, which is configured at boot time. Server can also make arbitrarily many intermediate framebuffers for rendering stuff.



It might be a good simplification to keep the vertex program and the framebuffer together.



#### Clock Precision



Using a float32 for elapsed time in seconds since start of run, and assuming 23 bits of mantissa, I compute that within 2 hours of operation the least significant bit of the mantissa will be at millisecond granularity a little after 2 hours. That means that the smallest increment in the time float will be around a milllisecond in size, an tracking smaller increments in time will be beyond the precision of the time counter.



Assuming a frame rate of 60 Hz there's roughly 16 ms between frames. That means that in roughly 36 hours of operation the timer will have moved up to 16 ms increments being the smallest precision increment we can pack into a 32 bit float. This is all back-of-the-envelope, but it's starting to feel like Nyquist sampling rules will apply, probably meaning that the smallest we would want to go is 2 increments between frames, so roughly 18 hours of runtime. A simple test might be to look at animations running after having started the clock artificially high. But loss of precision in the time variable is an obvious problem on longer-running synths, and I can't think of an obvious solution. Video cards are still strongly optimized for 32-bit math, so forcing everything into 64-bit modes doesn't seem prudent. Using an integer to represent elapsed times in milliseconds also doesn't seem like it will help, because the first thing everyone will do is cast it into float32, re-introducing the same loss of precision.



It might be worth looking in to how scsynth solves this problem. Of course, because that's running on the CPU the obvious thing to do is to just use double precision floating point numbers for everything related to time.



Furthermore, probably all of this help documentation should move over to my blog.


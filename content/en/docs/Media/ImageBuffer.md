---
title: ImageBuffer
linkTitle: ImageBuffer
weight: 5
description: Represents a server-side graphics memory region for sampling static images.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/VGens/Intrinsics/TexPos" >}}">TexPos</a> <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> 



## Description
---



The ImageBuffer class is designed to be analagous to the SuperCollider audio <a href="https://doc.sccode.org/Classes/Buffer.html">Buffer <img src="/images/external-link.svg" class="one-liner"></a> class, but for reading static images for sampling. ImageBuffers are most commonly used inside of <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> functions as arguments to <a href="https://doc.sccode.org/Classes/Sampler.html">Sampler <img src="/images/external-link.svg" class="one-liner"></a> instances. Currently ImageBuffer support is limited to reading static images, but more may be added in the future.



## Class Methods
---



### ImageBuffer.read(server, path, width, height, action, bufnum)



Attempts to open the image file at the provided path and read the metadata. If that is successful the server will decode the image file, optionally resize it to the provided width and height, and upload it to the graphics hardware so it is ready for use.



The <strong>width</strong> and <strong>height</strong> arguments are both by default -1, which is an instruction to the server to respect the original dimensions of the image. If only one of the dimension arguments is supplied by -1, the server will scale the image to the non-negative dimension and then preserve the aspect ratio of the image by scaling the negative dimension to the appropriate size. Of course, if both dimensions are non-negative then the server will scale the image to the provided dimensions, disregarding the aspect ratio of the input image.



For example, for a source image that is 200 pixels wide and 100 pixels tall:


<table>
<tr><td>

<strong>width requested</strong>

</td><td>

<strong>height requested</strong>

</td><td>

<strong>buffer width</strong>

</td><td>

<strong>buffer height</strong>

</td><td>

<strong>notes</strong>

</td></tr>
<tr><td>

400

</td><td>

100

</td><td>

400

</td><td>

100

</td><td>

Server will disregard aspect ratio of source image if both requested dimensions are nonnegative.

</td></tr>
<tr><td>

-1

</td><td>

50

</td><td>

100

</td><td>

50

</td><td>

In order to maintain 2:1 aspect ratio server has computed a width of 50 px.

</td></tr>
<tr><td>

400

</td><td>

-1

</td><td>

400

</td><td>

200

</td><td>

In order to maintain 2:1 aspect ratio server has computed a height of 400 px.

</td></tr>
<tr><td>

-1

</td><td>

-1

</td><td>

200

</td><td>

100

</td><td>

Server has allocated width and height of source image.

</td></tr>

</table>


#### Arguments

##### server



The <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> on which to read the image and allocate the buffer. If nil, will use <code>ScinServer.default</code>.



##### path



A string containing the path to the image file to read.



##### width



An optional integer describing a desired width to scale the image to, or -1.



##### height



An optional integer describing a desired height to scale the image to, or -1.



##### action



An optional function to be evaluated once the image has been decoded, uploaded, and this ImageBuffer's instance variables have been updated. The function will be passed this ImageBuffer as an argument.



##### bufnum



An explicitly specified buffer number. While buffer numbers for ImageBuffer are set on the client, and not allocated on the server, any load or delete operations on an ImageBuffer will clobber any exising ImageBuffer with the same buffer number. So, like <a href="https://doc.sccode.org/Classes/Buffer.html">Buffer <img src="/images/external-link.svg" class="one-liner"></a>, the best practice is to leave this unspecified.





#### Inherited class methods



## Instance Methods
---



### .width



Returns the width of the ImageBuffer in pixels.



### .server



The <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> that owns the associated buffer.



### .bufnum



The integer buffer number that uniquely identifies this buffer on the server.



### .height



Returns the height of the ImageBuffer in pixels.



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
// Creates a chromakey-like effect using the VecMix VGen to choose between two different
// image buffers based on distance of the first image from the provided target rgb color.
(
~o = ScinServerOptions.new;
~o.width = 400;
~o.height = 300;
~v = ScinServer.new(~o).boot;
)

(
~molly = ImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/molly.png".standardizePath);
~storm = ImageBuffer.read(path: "~/src/TestGoldImages/sourceMedia/storm.png".standardizePath);
)

(
~f = ScinthDef.new(\chromaKey, { |r, g, b, key = 0.25|
    var m = Sampler.fg(~molly, TexPos.fg);
    var s = Sampler.fg(~storm, TexPos.fg);
    var dist = Length.fg(m - Vec4.fg(r, g, b, 1.0));
    var pick = Step.fg(key, dist);
    VecMix.fg(s, m, pick);
}).add;
)

(
~t = Scinth.new(\chromaKey);
)
{{< /highlight >}}

<img src="/images/schelp/ImageBufferA.png" />

{{< highlight supercollider >}}
// Now vary the parameters of the ScinthDef to pick up a brighter color and with a larger key
// threshold.
(
~t.set(\r, 0.64, \g, 0.64, \b, 0.64, \key, 0.5);
)
{{< /highlight >}}

<img src="/images/schelp/ImageBufferB.png" />




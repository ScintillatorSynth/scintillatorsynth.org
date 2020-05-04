---
title: ScinServerOptions
linkTitle: ScinServerOptions
weight: 5
description: Encapsulates command line and other options for the Scintillator synthesis server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> 



## Description
---



This class is intended to be analogous to the <a href="https://doc.sccode.org/Classes/ServerOptions.html">ServerOptions <img src="/images/external-link.svg" class="one-liner"></a> class used to boot the SuperCollider audio synthesis server. Like that object, every <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> has an instance of ScinServerOptions created for it if one is not provided to it by the <code>options</code> argument on <a href="{{< ref "/docs/Scintillator Server/ScinServer" >}}">ScinServer</a> creation.



Also like <a href="https://doc.sccode.org/Classes/ServerOptions.html">ServerOptions <img src="/images/external-link.svg" class="one-liner"></a>, these parameters are translated into command line arguments for the server boot, so changing the options after the server has booted will take no effect until the server is restarted.



## Class Methods
---



### ScinServerOptions.new



Creates a new ScinServerOptions instance with all default options.



#### Inherited class methods



## Instance Methods
---



#### Server Boot Options



### .portNumber



### .portNumber = value


An integer, default <strong>5511</strong>. ScinServer binds both TCP and UDP ports at the provided port number.



### .dumpOSC



### .dumpOSC = value


A boolean, default <strong>false</strong>. If true, ScinServer will log all received OSC message at the informational log level. This can also be changed at runtime with the ScinServer <code>dumpOSC</code> method.



### .createWindow



### .createWindow = value


A boolean, default <strong>true</strong>. If true, ScinServer will create a window onscreen for rendering. If false, it will not, and all rendering will occur offscreen.



### .swiftshader



### .swiftshader = value


A boolean, default <strong>false</strong>. If true, ScinServer will ignore any installed graphics hardware and instead will use the open-source software renderer SwiftShader.



### .frameRate



### .frameRate = value


An integer, default <strong>-1</strong>. If a negative number, the server will run in <em>interactive</em> mode, optimized to reduce latency from render to presentation. If zero the server will run in <em>snapshot</em> mode, and will only render a new frame when instructed by an <code>advanceFrame</code> call. If a positive number the server will run in <em>throughput</em> mode, will render frames as quickly as possible, updated the onscreen window (if present) at the provided refresh rate. For more information see Guides/ScinServer-Recording



### .logLevel



### .logLevel = value


An integer, default <strong>3</strong>. Sets the initial logging level of the server. The values range from most verbose at 0 to disabling logging entirely at 6. All level values are inclusive of the higher-level values, meaning that 0 will include all higher levels, and 5 will only show cirtical errors. The values for the log level are as follows:


<table>
<tr><td>

0

</td><td>

Trace

</td></tr>
<tr><td>

1

</td><td>

Debug

</td></tr>
<tr><td>

2

</td><td>

Informational

</td></tr>
<tr><td>

3

</td><td>

Warnings

</td></tr>
<tr><td>

4

</td><td>

Errors

</td></tr>
<tr><td>

5

</td><td>

Critical Errors

</td></tr>
<tr><td>

6

</td><td>

Disable Logging

</td></tr>

</table>


### .deviceName



### .deviceName = value


A string, default <strong>empty</strong>. If empty, this argument is ignored. If non-empty, the server will attempt to match the fragment of the name in the argument against the names of the available devices and choose the first matching device name it encounters, or exit if no match can be made. The synth logs on startup at the Informational level all the Vulkan-supported devices available on the computer it is running on, so the log can be consulted as a source to pick names from. For example this code boots the server with the right log level to see the supported devices:



{{< highlight supercollider >}}
(
var options = ScinServerOptions.new;
options.logLevel = 2;
~v = ScinServer.new(options).boot;
)
{{< /highlight >}}



Which on my laptop produces in the log stream (among other things) the following console log entry:

<pre>[1586121895.197] 9593357 [info] found 3 Vulkan devices:
  name: AMD Radeon Pro 5500M, type: Discrete GPU, vendorID: 1002, deviceID: 7340
  name: Intel(R) UHD Graphics 630, type: Integrated GPU, vendorID: 8086, deviceID: 3e9b
  name: SwiftShader Device (LLVM 7.0.0), type: CPU, vendorID: 1ae0, deviceID: c0de

[1586121895.197] 9593357 [info] Choosing fastest device class Discrete GPU, device AMD Radeon Pro 5500M</pre>

From a performance standpoint Discrete GPUs are typically faster than Integrated GPUs, which are typically much faster than CPU (or software) renderers, and by default the server will pick the highest-performing device by category, so in this case the <code>AMD Radeon Pro 5500M</code>, but if I wanted ensure that the Intel integrated GPU got picked I could provide the (note: case-sensitive) following string in options:



{{< highlight supercollider >}}
(
var options = ScinServerOptions.new;
options.logLevel = 2;
options.deviceName = "Intel";
~v = ScinServer.new(options).boot;
)
{{< /highlight >}}



Which when run produces the following logstream:

<pre>[1586122278.866] 9595542 [info] Device name Intel match, selecting Intel(R) UHD Graphics 630</pre>

### .width



### .width = value


An integer, default <strong>800</strong>. The width of the window (or framebuffer, in the case of offscreen rendering) to create in pixels.



### .height



### .height = value


An integer, default <strong>600</strong>. The height of the window (or framebuffer, in the case of offscreen rendering) to create in pixels.



### .alwaysOnTop



### .alwaysOnTop = value


A boolean, default <strong>true</strong>. Just like the <a href="https://doc.sccode.org/Classes/Window.html">Window <img src="/images/external-link.svg" class="one-liner"></a> method of the same name, if true the server will create a window that floats on top of other windows. If false, the window will allow other windows to layer on top of it. If <code>createWindow</code> is false this value is ignored.



#### Other Methods



### .asOptionsString



Returns a string reflecting the current options and which can be passed to the command line of the ScinServer binary on boot. Used primarily by ScinServer code on server boot.



### .onServerError



### .onServerError = value


A function, default <strong>empty</strong>. This function will be called if the ScinServer object detects an abnormal server exit code.



#### Inherited instance methods


---
title: ScinServerOptions
linkTitle: ScinServerOptions
date: 2020-04-03
weight: 5
description: Encapsulates commadn line and other options for the Scintillator synthesis server.
---


## Description
---



This class is intended to be analogous to the <a href="https://doc.sccode.org/Classes/ServerOptions.html">ServerOptions <img src="/images/external-link.svg" class="one-liner"></a> class used to boot the SuperCollider audio synthesis server. Like that object, every <a href="{{< ref "/docs/Quark Documentation/Classes/ScinServer" >}}">ScinServer</a> has an instance of ScinServerOptions created for it if one is not provided to it by the <code>options</code> argument on <a href="{{< ref "/docs/Quark Documentation/Classes/ScinServer" >}}">ScinServer</a> creation.



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


An integer, default <strong>-1</strong>. If a negative number, the server will run in <em>interactive</em> mode, optimized to reduce latency from render to presentation. If zero the server will run in <em>snapshot</em> mode, and will only render a new frame when instructed by an <code>advanceFrame</code> call. If a positive number the server will run in <em>throughput</em> mode, will render frames as quickly as possible, updated the onscreen window (if present) at the provided refresh rate. For more information see Reference/ScinServer-Recording



### .logLevel



### .logLevel = value


(describe method here)



#### Returns:



(describe returnvalue here)



### .onServerError



### .onServerError = value


(describe method here)



#### Returns:



(describe returnvalue here)



### .deviceName



### .deviceName = value


(describe method here)



#### Returns:



(describe returnvalue here)



### .width



### .width = value


(describe method here)



#### Returns:



(describe returnvalue here)



### .init



(describe method here)



#### Returns:



(describe returnvalue here)



### .keepOnTop



### .keepOnTop = value


(describe method here)



#### Returns:



(describe returnvalue here)



### .height



### .height = value


(describe method here)



#### Returns:



(describe returnvalue here)



#### Other Methods



### .asOptionsString



Returns a string reflecting the current options and which can be passed to the command line of the ScinServer binary on boot. Used primarily by ScinServer code on server boot.



### .quarkPath



### .quarkPath = value


A convenience method that exposes the path to the Scintillator Quark. Used primarily by the Scintillator integration test suite and other internal tooling to quickly locate the Scintillator project files.



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
(some example code)
{{< /highlight >}}






---
title: ScinServer
linkTitle: ScinServer
date: 2020-04-02
weight: 5
description: Represents a Scintillator server application.
---


## Description
---



This class is analagous to the SuperCollider <a href="https://doc.sccode.org/Classes/Server.html">Server <img src="/images/external-link.svg" class="one-liner"></a> class. It is a client-side representation of an instance of the Scintillator video synthesis server.



## Class Methods
---



### ScinServer.default



### ScinServer.default = value


Get or set the default Scintillator server. This is the server reference that will be used as the default in all server arguments to ScinthDef and others that accept an optional server argument. By default is the local server instance.



### ScinServer.new(options)



Creates a new ScinServer instance. For now only local servers are supported.



#### Arguments

##### options



An optional instance of ScinServerOptions. If <code>nil</code>, an instance of ScinServerOptions will be created using the default values.





#### Inherited class methods



## Instance Methods
---



### .boot



It not already booted, boots the Scintillator synthesis server.



### .numberOfWarnings



Returns the current number of errors reported by the server since boot.



### .numberOfErrors



Returns the current number of errors reported by the server since boot.





### .logLevel = level


Updates the logging level on the server to the provided argument. Lower log levels are inclusive of all higher log levels, so they tend to log more, and the highest log level turns off logging. This overrides any command-line argument supplied to scinsynth at startup.



#### Arguments

##### level



The values for the log level are as follows:


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




### .screenShot(fileName, mimeType, onReady, onComplete)



Requests the server to take a screen shot of the next frame rendered, encode it into the provided file format, and save to disk.



#### Arguments

##### fileName



A string with the full path and file name, including extension, of the desired file to save the screenshot image to.



##### mimeType



An optional string. A hint to the image encoder as to which file format is desired, for example <code>"image/png"</code>, <code>"image/gif"</code>, <code>"image/jpeg"</code>.



##### onReady



An optional function to call when the ScinServer responds that it has enqueued the screenshot for encode on the next rendered frame.



##### onComplete



An optional function to call when the ScinServer responds that it has completed encoding and writing the image to disc.

{{% alert title="Note" %}}


The screenShot functionality is only supported in non-realtime rendering modes. See Guides/ScinServer-Recording for more information.

{{% /alert %}}




### .advanceFrame(num, denom)



If the server is configured with zero frame rate, will advance the time on the synth by the provided fraction of time in seconds and render a new frame. Otherwise this command is ignored.



#### Arguments

##### num



An integer representing the numerator in the fraction of time to advance the frame by.



##### denom



An integer representing the denominator in the fraction of time to advance the frame by. Sending time in terms of fractions allows for traditional media frame rates (like 24 frames per second).





### .bootSync(condition)



(describe method here)



#### Arguments

##### condition



(describe argument here)





#### Returns:



(describe returnvalue here)



### .dumpOSC(on)



(describe method here)



#### Arguments

##### on



(describe argument here)





#### Returns:



(describe returnvalue here)



### .sendMsg(... msg)



(describe method here)



#### Arguments

##### ... msg



(describe argument here)





#### Returns:



(describe returnvalue here)



### .serverBooting



(describe method here)



#### Returns:



(describe returnvalue here)



### .quit



(describe method here)



#### Returns:



(describe returnvalue here)



### .doWhenBooted(onComplete)



(describe method here)



#### Arguments

##### onComplete



(describe argument here)





#### Returns:



(describe returnvalue here)



### .serverRunning



(describe method here)



#### Returns:



(describe returnvalue here)



### .waitForBoot(onComplete)



(describe method here)



#### Arguments

##### onComplete



(describe argument here)





#### Returns:



(describe returnvalue here)



#### Asynchronous Commands



The server provides support for waiting on the completion of asynchronous OSC-commands such as reading or writing sound files.

{{% alert title="Note" %}}


The following methods must be called from within a running <a href="https://doc.sccode.org/Classes/Routine.html">Routine <img src="/images/external-link.svg" class="one-liner"></a>. Explicitly passing in a <a href="https://doc.sccode.org/Classes/Condition.html">Condition <img src="/images/external-link.svg" class="one-liner"></a> allows multiple elements to depend on different conditions. The examples below should make clear how all this works.

{{% /alert %}}


### .sync(condition)



(describe method here)



#### Arguments

##### condition



(describe argument here)





#### Returns:



(describe returnvalue here)



### .queueScreenShotSync(fileName, mimeType, onComplete, condition)



Call from a <a href="https://doc.sccode.org/Classes/Routine.html">Routine <img src="/images/external-link.svg" class="one-liner"></a>. Requests the server take a screenshot and blocks the calling thread until the screenshot is complete.



#### Arguments

##### fileName



(describe argument here)



##### mimeType



(describe argument here)



##### onComplete



(describe argument here)



##### condition



(describe argument here)





#### Returns:



(describe returnvalue here)



#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
(some example code)
{{< /highlight >}}






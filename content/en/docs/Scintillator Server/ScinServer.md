---
title: ScinServer
linkTitle: ScinServer
weight: 5
description: Represents a Scintillator server application.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> 



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



An optional instance of <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a>. If <code>nil</code>, an instance of ScinServerOptions will be created using the default values.





#### Inherited class methods



## Instance Methods
---



### .boot



It not already booted, boots the Scintillator synthesis server.



### .numberOfWarnings



Returns the current number of errors reported by the server since boot.



### .numberOfErrors



Returns the current number of errors reported by the server since boot.



### .options



Provides access to the <a href="{{< ref "/docs/Scintillator Server/ScinServerOptions" >}}">ScinServerOptions</a> object that was or will be used to boot the Server. This allows for convenient modifications of the options before boot, without having to create a separate ScinServerOptions object to provide on the call to new().





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

{{% alert title="Note" %}}


The screenShot functionality is only supported in non-realtime rendering modes. See Guides/ScinServer-Recording for more information.

{{% /alert %}}


#### Arguments

##### fileName



A string with the full path and file name, including extension, of the desired file to save the screenshot image to.



##### mimeType



An optional string. A hint to the image encoder as to which file format is desired, for example <code>"image/png"</code>, <code>"image/gif"</code>, <code>"image/jpeg"</code>.



##### onReady



An optional function to call when the ScinServer responds that it has enqueued the screenshot for encode on the next rendered frame.



##### onComplete



An optional function to call when the ScinServer responds that it has completed encoding and writing the image to disc.





### .advanceFrame(num, denom)



If the server is configured with zero frame rate, will advance the time on the synth by the provided fraction of time in seconds and render a new frame. Otherwise this command is ignored.



#### Arguments

##### num



An integer representing the numerator in the fraction of time to advance the frame by.



##### denom



An integer representing the denominator in the fraction of time to advance the frame by. Sending time in terms of fractions allows for traditional media frame rates (like 24 frames per second).





### .dumpOSC(on)



Controls if the server should dump received OSC messages to the log or not.

{{% alert title="Note" %}}


The server writes OSC messages to the log at the <em>Informational</em> level, meaning that any logLevel higher than that will not show the OSC messages. Also note that the default log level for ScinServer is at the <em>Warning</em> level, meaning that at default logging dumpOSC will not appear to work.

{{% /alert %}}


#### Arguments

##### on



A boolean. If true, the server will start dumping incoming OSC messages to the log. If false, the server will stop.





### .sendMsg(... msg)



Sends the provided arguments as an OSC message to the associated server process.



#### Arguments

##### ... msg



The OSC message to send, typically starting with an OSC path like <code>/scin_logLevel</code>. For documentation about supported command consult the <a href="{{< ref "/docs/Developer Documentation/Scintillator-Scinth-Server-Command-Reference" >}}">Scintillator Scinth Server Command Reference</a>.





### .serverBooting



Returns true if the server is in the process of booting, specifically that the ScinServer instance object has started the server but not yet heard back a response to the status polling.



### .quit



Requests the server stop immediately.



### .doWhenBooted(onComplete)



Adds the provided function to the list of functions to be called after the ScinServer instance object detects that the server has booted.



#### Arguments

##### onComplete



A function, the method to be called when boot is detected. The function is called with one argument, which is the ScinServer object which just booted.





### .serverRunning



Returns true if the server associated with this ScinServer instance object is currently running.



### .waitForBoot(onComplete)



Convenience method that adds the provided function to the boot callbacks list and then boots the server if it is not currently running.



#### Arguments

##### onComplete



The function to call when the server is detected as booted. The function is called with one argument, the ScinServer object which just booted.





#### Synchronous Commands



The server provides support for waiting on the completion of asynchronous OSC-commands such as reading or writing sound files, making them synchronous.

{{% alert title="Note" %}}


The following methods must be called from within a running <a href="https://doc.sccode.org/Classes/Routine.html">Routine <img src="/images/external-link.svg" class="one-liner"></a>. Explicitly passing in a <a href="https://doc.sccode.org/Classes/Condition.html">Condition <img src="/images/external-link.svg" class="one-liner"></a> allows multiple elements to depend on different conditions. The examples below should make clear how all this works.

{{% /alert %}}


### .sync(condition)



Sends a request to the server to finish all <em>asynchronous commands</em>, such as compiling a <a href="{{< ref "/docs/Scinth/ScinthDef" >}}">ScinthDef</a> or decoding an image. Will block the calling thread until the completion of all pending tasks is reported by the server.



#### Arguments

##### condition



An optional <a href="https://doc.sccode.org/Classes/Condition.html">Condition <img src="/images/external-link.svg" class="one-liner"></a> to use to block the Routine. If not provided ScinServer will make a new one for use.





### .queueScreenShotSync(fileName, mimeType, onComplete, condition)



Call from a <a href="https://doc.sccode.org/Classes/Routine.html">Routine <img src="/images/external-link.svg" class="one-liner"></a>. Requests the server take a screenshot and blocks the calling thread until the screenshot is <em>queued</em> to render on the next frame.

{{% alert title="Note" %}}


The screenshot functionality is only supported in non-realtime rendering modes. See Guides/ScinServer-Recording for more information.

{{% /alert %}}


#### Arguments

##### fileName



A string containing the path and file name to save the resulting image to.



##### mimeType



An optional string with a mime type to provide additional context to the image encoder (in addition to the fileName extension) about what image encoding type is requested for saving the image as.



##### onComplete



An optional function to callback when the screenshot has been completed, meaning the render has completed and the file has been saved to disc.



##### condition



An optional <a href="https://doc.sccode.org/Classes/Condition.html">Condition <img src="/images/external-link.svg" class="one-liner"></a> object to use to wait on. If not provided ScinServer will create its own.





### .bootSync(condition)



Boot the server, then block the Routine until the server is detected as having booted.



#### Arguments

##### condition



An optional <a href="https://doc.sccode.org/Classes/Condition.html">Condition <img src="/images/external-link.svg" class="one-liner"></a> object to use to wait on. If not provided ScinServer will create its own.





#### Developer and Diagnostic Commands



### .postCrashReports



Requests the server to post some detail about any server crash reports that have been stored in the crash report database. Will produce output in the post window that looks something like:

<pre>[1594932178.669] 285299 [info] Crash report database contains 3 reports:
[1594932178.669] 285299 [info]     id: 1a77de76-f2c2-4b22-9d2a-bd5016772a2f, on: Thu 16 Jul 13:26:37 2020, uploaded: no
[1594932178.669] 285299 [info]     id: bac6608c-05cd-4f03-827b-780588d5ee1c, on: Thu 16 Jul 13:07:48 2020, uploaded: no
[1594932178.669] 285299 [info]     id: 250c0c0d-8b2c-4a16-8240-576cc26e80d1, on: Thu 16 Jul 13:14:25 2020, uploaded: yes</pre>

The <code>id</code> field can be copied and pasted into a request to upload individual crash reports, as well as posted in bug reports to the Scintillator developers.

{{% alert title="Note" %}}


Please read the <a href="{{< ref "/docs/Guides/Scintillator-Crash-Reports-And-Privacy" >}}">Scintillator Crash Reports And Privacy</a> discussion before uploading crash reports.

{{% /alert %}}


### .uploadCrashReport(id)



Marks a crash report as ok to upload. The reports will generally be uploaded shortly after the next time the Scintillator Server boots, assuming the computer is connected to the internet and the crash report server is available.

{{% alert title="Note" %}}


Please read the <a href="{{< ref "/docs/Guides/Scintillator-Crash-Reports-And-Privacy" >}}">Scintillator Crash Reports And Privacy</a> discussion before uploading crash reports.

{{% /alert %}}


#### Arguments

##### id



A string with the complete crash report id.





### .uploadAllCrashReports



Marks all un-uploaded crash reports with a request for upload.

{{% alert title="Note" %}}


Please read the <a href="{{< ref "/docs/Guides/Scintillator-Crash-Reports-And-Privacy" >}}">Scintillator Crash Reports And Privacy</a> discussion before uploading crash reports.

{{% /alert %}}


#### Inherited instance methods



## Examples
---



{{< highlight supercollider >}}
// This example starts a ScinServer configured to render offscreen into a 1024x768 image buffer.
// It creates a new monochrome ScinthDef, makes a Scinth instance of it, renders a single frame,
// requests a screenshot, renders a frame which is saved as that screenshot, changes a parameter
// on the running Scinth, takes another screenshot, then quits the server.
(
~scinOptions = ScinServerOptions.new;
~scinOptions.frameRate = 0;
~scinOptions.width = 1024;
~scinOptions.height = 768;
~scinOptions.logLevel = 2;
~scinOptions.createWindow = false;
~scinOptions.swiftshader = true;

// Run on a Routine so we can use the blocking
fork {
    var c, f;
    c = Condition.new;
    f = { c.test = true; c.signal; };

    // Boot the server, and wait for it to boot.
    ~videoServer = ScinServer.new(~scinOptions).bootSync;

    // Send a ScinthDef to the server
    ~def = ScinthDef.new(\t, { |sx = 1.0, sy = 2.0|
        BWOut.fr(VSinOsc.fr(1.0, Length.fr(NormPos.fr * Vec2.fr(sx, sy))));
    }).add;

    // Wait for the server to complete building the ScinthDef just provided.
    ~videoServer.sync;

    // Start rendering an instance of the ScinthDef on next frame render.
    ~scinth = Scinth.new(\t);

    // Render one frame, then advance time by 1/24th of a second.
    ~videoServer.advanceFrame(1, 24);

    // Queue a screenshot request for render, wait until queuing is complete.
    c.test = false;
    ~videoServer.queueScreenShotSync("test1.png", "image/png", onComplete: f);

    // Render a frame, to capture the screenshot.
    ~videoServer.advanceFrame(1, 24);

    // Block until the onComplete function is called, which will clear the
    // Condition.
    c.wait;

    // Set a parameter on the running Scinth.
    ~scinth.set(\sx, 2.0, \sy, 1.0);
    ~videoServer.advanceFrame(1, 24);

    // Request another screenshot.
    c.test = false;
    ~videoServer.queueScreenShotSync("test2.png", "image/png", onComplete: f);
    ~videoServer.advanceFrame(1, 24);
    c.wait;

    // Exit the server.
    ~videoServer.quit;
};
)
{{< /highlight >}}






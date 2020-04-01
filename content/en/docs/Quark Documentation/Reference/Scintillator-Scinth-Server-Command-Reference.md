---
title: Scintillator Scinth Server Command Reference
linkTitle: Scintillator Scinth Server Command Reference
date: 2020-03-31
weight: 5
description: A list of all OSC commands accepted by scscinth
---
<div id='nil'>While the SuperCollider synth programs support a variety of connection modalities and multi-user scenarios, at present scinsynth only supports a single client commnicating exclusively over UDP. For asynchronous messages the server will respond to whatever UDP address and port messages are received from.In general Scintillator attempts to follow a similar command list and structure to that of the SuperCollider sound synthesis servers, with the addition of a ```/scin_``` prefix to each OSC command and response.Some commands are marked as **asynchronous**, which in this context means they will send a response message back to the sender, sometimes **/scin_done**, upon completion of the command. For any command marked as asynchronous Scintillator is designed to always return a message, even if the execution of the command failed or the message body didn't parse.<div id='nil'>Quit program. Exits the scinsynth server.<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done** just before completion.</dl>
Query the status. Replies to sender with the following message:<dl>
<dt>/scin_status.reply<dd><table>
<tr><td>**int**<td>number of running Scinths.<tr><td>**int**<td>number of groups.<tr><td>**int**<td>number of loaded Scinth defintions.<tr><td>**int**<td>number of warnings in log.<tr><td>**int**<td>number of errors in log.<tr><td>**double**<td>approximate graphics memory consumed, in bytes.<tr><td>**double**<td>approximage graphics memory available, in bytes. Some graphics devices don't support this statistic, in which case it will be 0.<tr><td>**int**<td>target frame rate in frames per second (can be -1 or 0)<tr><td>**double**<td>computed mean framerate.<tr><td>**int**<td>number of dropped frames, if running real time, 0 otherwise.</table>
</dl>
Log incoming OSC messages.<table>
<tr><td>**int**<td>0 to disable logging. Nonzero to enable.</table>
Notify when all outstanding aync commands have completed.Replies with a `/scin_synced` message when all asynchronous commands received before this one have completed. The reply will contain the sent unique ID.<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_synced, ID** when complete.</dl>
Set the logging level for the log streams. Lower log levels are inclusive of all higher log levels, so they tend to log more, and the highest log level turns off logging. This overrides any command-line argument supplied to scinsynth at startup.This command is similar to the SuperCollider server command `/error` but offers more options for access to the built-in logging.<table>
<tr><td>**int**<td>log level</table>
The values for the log level are as follows:<table>
<tr><td>0<td>Trace<tr><td>1<td>Debug<tr><td>2<td>Informational<tr><td>3<td>Warnings<tr><td>4<td>Errors<tr><td>5<td>Critical Errors<tr><td>6<td>Disable Logging</table>
Query the Scintillator version. Replies to sender with the following message:<dl>
<dt>/scin_version.reply<dd><table>
<tr><td>**string**<td>Program name. Will always be "scinsynth".<tr><td>**int**<td>Scintillator major version number.<tr><td>**int**<td>Scintillator minor version number.<tr><td>**int**<td>Scintillator patch version number.<tr><td>**string**<td>Git branch name.<tr><td>**string**<td>First seven hex digits of the commit hash.</table>
</dl>
It is expected that the scinsynth binary will always have the same major, minor, and patch version as the Scintillator Quark.<div id='nil'>Recive a Scinth definition yaml file.<table>
<tr><td>**string**<td>A string of yaml data containing one or more ScinthDef documents.<tr><td>**bytes**<td>optional, an OSC message to execute upon completion.</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done** when complete.</dl>
Load a ScinthDef yaml file from disk.<table>
<tr><td>**string**<td>pathname of file to load.<tr><td>**bytes**<td>optional, an OSC message to execute upon completion.</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done** when complete.</dl>
Load a directory of Scinth definitions.<table>
<tr><td>**string**<td>directory of *.yaml files to load.<tr><td>**bytes**<td>optional, an OSC message to execute upon completion.</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done** when complete.</dl>
Delete Scinth defenitions. Note that any existing Scinths will continue to run, and the resources associated with this ScinthDef will not be reclaimed until all running Scinth instances built from this ScinthDef are also freed.<table>
<tr><td>N * **string**<td>ScinthDef names to free.</table>
<div id='nil'>Delete a node.<table>
<tr><td>N * **int**<td>node ID</table>
Stops a node abruptly, removes it from its group, and frees its memory. More than one nodeID may be specified as additional integer arguments in the message.Turn node on or off from rendering.<table>
<tr><td>N*<td><table>
<tr><td>**int**<td>node ID<tr><td>**int**<td>run flag</table>
</table>
<ul>
<li>if the run flag is set to zero then the node will not be rendered.<li>if the run flag is set to any nonzero value then it will be rendered.</ul>
Can take pairs of arguments and will apply the run flag to each provided node ID at the same time.Set a node's control value(s).<table>
<tr><td>**int**<td>node ID<tr><td>N *<td><table>
<tr><td>**int** or **string**<td>A control index or name<tr><td>**int** or **float**<td>A control value</table>
</table>
<div id='nil'>Create a new Scinth.<table>
<tr><td>**string**<td>scinth definition name<tr><td>**int**<td>scinth ID<tr><td>**int**<td>add action (not yet supported)<tr><td>**int**<td>add target ID (not yet supported)<tr><td>N *<td><table>
<tr><td>**int** or **string**<td>A control index or name<tr><td>**int** or **float**<td>A control value</table>
</table>
As Scintillator does not currently support groups, offscreen render targets, or scinth controls, only the first two fields are processed by the server. The server will add the Scinth to the default group and start playing it immediately.{{% alert title="Note" %}}
The SuperCollider Synthesis Server command reference specifies if the provided ID number is -1, the server reserves all negative numbers for IDs, and so will assign a unique negative value to the Synth instance. Scintillator server is similar, but will treat *any* negative value as a request for a unique ID to be assigned.{{% /alert %}}
<div id='nil'>Sets the background color for the provided group.TODO<div id='nil'>The server leaves designation of unique image buffer numbers to the client. Allocation of an image buffer with a number already associated with another image buffer will result in the prior image buffer being deallocated.Decodes an image, optionally resamples it to the provided size, allocates memory on the graphics device, and transfers the decoded image data to the image buffer.<table>
<tr><td>**int**<td>buffer number<tr><td>**string**<td>path to image file to decode<tr><td>**int**<td>width of image (can be -1 to respect width of source image)<tr><td>**int**<td>height of image (can be -1 to respect height of source image)<tr><td>**bytes**<td>(optional) an OSC message to execute upon completion</table>
If both width and height are provided the source image will be resampled to that size, ignoring aspect ratio. If one of the width or height values is -1 then the image will be resampled to a size respecting the provided dimension and maintaining the aspect ratio of the other dimension. If both values are -1 then the image won't be resampled but will be loaded at original size. For example, for a source image that is 200 pixels wide and 100 pixels tall:<table>
<tr><td>**width requested**<td>**height requested**<td>**buffer width**<td>**buffer height**<td>**notes**<tr><td>400<td>100<td>400<td>100<td>Server will disregard aspect ratio of source image if both requested dimensions are nonnegative.<tr><td>-1<td>50<td>100<td>50<td>In order to maintain 2:1 aspect ratio server has computed a width of 50 px.<tr><td>400<td>-1<td>400<td>200<td>In order to maintain 2:1 aspect ratio server has computed a height of 400 px.<tr><td>-1<td>-1<td>200<td>100<td>Server has allocated width and height of source image.</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done /scin_ib_readImage bufNum** when complete.</dl>
Get ImageBuffer info.<table>
<tr><td>N * **int**<td>Image buffer number(s)</table>
Responds to the sender with a **/scin_ib_info** message. The arguments to **/scin_ib_info** are as follows:<table>
<tr><td>N *<td><table>
<tr><td>**int**<td>Image bufer number<tr><td>**int**<td>Image size in bytes, 0 if invalid<tr><td>**int**<td>Image width, -1 if invalid<tr><td>**int**<td>Image height, -1 if invalid</table>
</table>
<div id='nil'>Server will save a screenshot image of the next rendered frame to the supplied file name.<table>
<tr><td>**string**<td>file name and path to save the screenshot to.<tr><td>**string**<td>(optional) a mime type of the desired image file, to guide container and codec selection.</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender twice with updates. The first response is **/scin_nrt_screenShot.ready**, followed by the file name, which is sent once the screenshot is ready to record, and lastly a boolean with file status. The second reponse is on completion of the saving of the screenshot file, the server replies with **/scin_done** followed by **/scin_nrt_screenShot**, the name of the file saved, and a boolean with status on completion.</dl>
If framerate is at zero, renders one frame at the current time and then advances the time by the supplied fractional increment, in seconds.<table>
<tr><td>**int**<td>numerator for fractional time advancement<tr><td>**int**<td>denominator for fractional time advancement</table>
<dl>
<dt>Asynchronous.<dd>Replies to sender with **/scin_done** followed by **/scin_nrt_advanceFrame**.</dl>
<div id='nil'>Commands useful for accessing diagnostic functions or automated validation of scinsynth behavior.Respond back to the caller with the provided message.<table>
<tr><td>**bytes**<td>An OSC message to respond to the caller with.</table>
<dl>
<dt>Asynchronous.<dd>Replies to the sender immediately with the provided message blob.</dl>
Add the supplied string to the logs at the supplied level.<table>
<tr><td>**int**<td>A log level, from 0-6, to associate with this log entry. See the table in **/scin_logLevel** for the log levels.<tr><td>**string**<td>The string to place in the logs.</table>
Puts one of the asynchronous worker threads to sleep for the specified number of seconds.<table>
<tr><td>**int**<td>The number of seconds to sleep for.</table>
{{% alert title="Note" %}}
This will tie up one of the available threads on the server, and is therefore not recommended for use in a performance or recording context.{{% /alert %}}
<dl>
<dt>Asynchronous.<dd>Replies to the sender on wakeup with **/scin_awake**.</dl>

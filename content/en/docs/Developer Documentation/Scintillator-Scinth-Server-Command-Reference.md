---
title: Scintillator Scinth Server Command Reference
linkTitle: Scintillator Scinth Server Command Reference
weight: 5
description: A list of all OSC commands accepted by scinsynth
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->


### OSC Communication Details
---



While the SuperCollider synth programs support a variety of connection modalities and multi-user scenarios, at present scinsynth only supports a single client commnicating exclusively over UDP. For asynchronous messages the server will respond to whatever UDP address and port messages are received from.



In general Scintillator attempts to follow the command syntax and semantics of the SuperCollider sound synthesis servers, with the addition of a <code>/scin_</code> prefix to each OSC command and response. In some cases the commands are identical, and the documentation has been copied verbatim from the Reference/Server-Command-Reference.



Some commands are marked as <strong>asynchronous</strong>, which in this context means they will send a response message back to the sender, sometimes <strong>/scin_done</strong>, upon completion of the command. For any command marked as asynchronous Scintillator is designed to always return a message, even if the execution of the command failed or the message body didn't parse.



### Master Controls
---



#### /scin_quit



Quit program. Exits the scinsynth server.


<table>


Asynchronous.



Replies to sender with <strong>/scin_done</strong> just before completion.


</table>


#### /scin_status



Query the status. Replies to sender with the following message:


<table>


/scin_status.reply


<table>
<tr><td>

<strong>int</strong>

</td><td>

number of Scinths in the render tree.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

number of Groups in the render tree.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

number of loaded ScinthDefs.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

cumulative number of warnings in log.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

cumulative number of errors in log.

</td></tr>
<tr><td>

<strong>double</strong>

</td><td>

approximate graphics memory consumed, in bytes.

</td></tr>
<tr><td>

<strong>double</strong>

</td><td>

approximage graphics memory available, in bytes. Some graphics drivers don't support this statistic, in which case it will be 0.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

target frame rate in frames per second (can be -1 or 0)

</td></tr>
<tr><td>

<strong>double</strong>

</td><td>

computed mean framerate.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

number of dropped frames, if running real time, 0 otherwise.

</td></tr>

</table>

</table>


#### /scin_dumpOSC



Log incoming OSC messages.


<table>
<tr><td>

<strong>int</strong>

</td><td>

0 to disable logging. Nonzero to enable.

</td></tr>

</table>


#### /scin_sync



Notify when all outstanding aync commands have completed.



Replies with a <code>/scin_synced</code> message when all asynchronous commands received before this one have completed. The reply will contain the sent unique ID.


<table>


Asynchronous.



Replies to sender with <strong>/scin_synced, ID</strong> when complete.


</table>


#### /scin_logLevel



Set the logging level for the log streams. Lower log levels are inclusive of all higher log levels, so they tend to log more, and the highest log level turns off logging. This overrides any command-line argument supplied to scinsynth at startup.



This command is similar to the SuperCollider server command <code>/error</code> but offers more options for access to the built-in logging.


<table>
<tr><td>

<strong>int</strong>

</td><td>

log level

</td></tr>

</table>


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


#### /scin_version



Query the Scintillator version. Replies to sender with the following message:


<table>


/scin_version.reply


<table>
<tr><td>

<strong>string</strong>

</td><td>

Program name. Will always be "scinsynth".

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Scintillator major version number.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Scintillator minor version number.

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Scintillator patch version number.

</td></tr>
<tr><td>

<strong>string</strong>

</td><td>

Git branch name.

</td></tr>
<tr><td>

<strong>string</strong>

</td><td>

First seven hex digits of the commit hash.

</td></tr>

</table>

</table>


It is expected that the scinsynth binary will always have the same major, minor, and patch version as the Scintillator Quark.



### Scinth Definition Commands
---



#### /scin_d_recv



Recive a Scinth definition yaml file.


<table>
<tr><td>

<strong>string</strong>

</td><td>

A string of yaml data containing one or more ScinthDef documents.

</td></tr>
<tr><td>

<strong>bytes</strong>

</td><td>

optional, an OSC message to execute upon completion.

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender with <strong>/scin_done</strong> when complete.


</table>


#### /scin_d_load



Load a ScinthDef yaml file from disk.


<table>
<tr><td>

<strong>string</strong>

</td><td>

pathname of file to load.

</td></tr>
<tr><td>

<strong>bytes</strong>

</td><td>

optional, an OSC message to execute upon completion.

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender with <strong>/scin_done</strong> when complete.


</table>


#### /scin_d_loadDir



Load a directory of Scinth definitions.


<table>
<tr><td>

<strong>string</strong>

</td><td>

directory of *.yaml files to load.

</td></tr>
<tr><td>

<strong>bytes</strong>

</td><td>

optional, an OSC message to execute upon completion.

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender with <strong>/scin_done</strong> when complete.


</table>


#### /scin_d_free



Delete Scinth defenitions. Note that any existing Scinths will continue to run, and the resources associated with this ScinthDef will not be reclaimed until all running Scinth instances built from this ScinthDef are also freed.


<table>
<tr><td>

N * <strong>string</strong>

</td><td>

ScinthDef names to free.

</td></tr>

</table>


### Node Commands
---



#### /scin_n_free



Delete a node.


<table>
<tr><td>

N * <strong>int</strong>

</td><td>

node ID

</td></tr>

</table>


Stops a node abruptly, removes it from its group, and frees its memory. More than one nodeID may be specified as additional integer arguments in the message. If a node is a group this command will free all contained nodes as well.



#### /scin_n_run



Turn node on or off from rendering.


<table>
<tr><td>

N*

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

node ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

run flag

</td></tr>

</table>
</td></tr>

</table>
<ul>
<li>

if the run flag is set to zero then the node will not be rendered.

<li>

if the run flag is set to any nonzero value then it will be rendered.

</ul>


Can take pairs of arguments and will apply the run flag to each provided node ID at the same time.



#### /scin_n_set



Set a node's control value(s).


<table>
<tr><td>

<strong>int</strong>

</td><td>

node ID

</td></tr>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong> or <strong>string</strong>

</td><td>

A control index or name

</td></tr>
<tr><td>

<strong>int</strong> or <strong>float</strong>

</td><td>

A control value

</td></tr>

</table>
</td></tr>

</table>


#### /scin_n_before



Place a node before another.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

the ID of the node to place (A)

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

the ID of the node before which the above is placed (B)

</td></tr>

</table>
</td></tr>

</table>


Places node A in the same group as node B, to execute immediately before node B.



#### /scin_n_after



Place a node after another.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

the ID of the node to place (A)

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

the ID of the node after which the above is placed (B)

</td></tr>

</table>
</td></tr>

</table>


Places node A in the same group as node B, to execute immediately after node B.



#### /scin_n_order



Move and order a list of nodes.


<table>
<tr><td>

<strong>int</strong>

</td><td>

add action (0,1,2 or 3 see below)

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

add target ID

</td></tr>
<tr><td>

N * <strong>int</strong>

</td><td>

node IDs

</td></tr>

</table>


Move the listed nodes to the location specified by the target and add action, and place them in the order specified. Nodes which have already been freed will be ignored.


<table>


add actions:


<table>
<tr><td>

0

</td><td>

construct the node order at the head of the group specified by the add target ID.

</td></tr>
<tr><td>

1

</td><td>

construct the node order at the tail of the group specified by the add target ID.

</td></tr>
<tr><td>

2

</td><td>

construct the node order just before the node specified by the add target ID.

</td></tr>
<tr><td>

3

</td><td>

construct the node order just after the node specified by the add target ID.

</td></tr>

</table>

</table>


### Scinth Commands
---



#### /scin_s_new



Create a new Scinth.


<table>
<tr><td>

<strong>string</strong>

</td><td>

scinth definition name

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

scinth ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

add action

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

add target ID

</td></tr>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong> or <strong>string</strong>

</td><td>

A control index or name

</td></tr>
<tr><td>

<strong>int</strong> or <strong>float</strong>

</td><td>

A control value

</td></tr>

</table>
</td></tr>

</table>


Create a new Scinth instance from the given ScinthDef, give it an ID, and add it to the tree of nodes. There are four ways to add the node to the tree as determined by the add action argument, with the same values as the SuperCollider audio synth server, defined as follows:


<table>


add actions:


<table>
<tr><td>

0

</td><td>

add the new node to the the head of the group specified by the add target ID.

</td></tr>
<tr><td>

1

</td><td>

add the new node to the the tail of the group specified by the add target ID.

</td></tr>
<tr><td>

2

</td><td>

add the new node just before the node specified by the add target ID.

</td></tr>
<tr><td>

3

</td><td>

add the new node just after the node specified by the add target ID.

</td></tr>
<tr><td>

4

</td><td>

the new node replaces the node specified by the add target ID. The target node is freed.

</td></tr>

</table>

</table>
{{% alert title="Note" %}}


The SuperCollider Synthesis Server command reference specifies if the provided ID number is -1, the server reserves all negative numbers for IDs, and so will assign a unique negative value to the Synth instance. Scintillator server is similar, but will treat <em>any</em> negative value as a request for a unique ID to be assigned.

{{% /alert %}}


### Group Commands
---



#### /scin_g_new



Create a new group.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

new group ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

add action (0,1,2, 3 or 4 see below)

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

add target ID

</td></tr>

</table>
</td></tr>

</table>


Create a new group and add it to the tree of nodes. There are four ways to add the group to the tree as determined by the add action argument which is defined as follows (the same as for <strong>/s_new</strong>):


<table>


add actions:


<table>
<tr><td>

0

</td><td>

add the new group to the the head of the group specified by the add target ID.

</td></tr>
<tr><td>

1

</td><td>

add the new group to the the tail of the group specified by the add target ID.

</td></tr>
<tr><td>

2

</td><td>

add the new group just before the node specified by the add target ID.

</td></tr>
<tr><td>

3

</td><td>

add the new group just after the node specified by the add target ID.

</td></tr>
<tr><td>

4

</td><td>

the new node replaces the node specified by the add target ID. The target node is freed.

</td></tr>

</table>

</table>


Multiple groups may be created in one command by adding arguments.



#### /scin_g_head



Add node to head of group.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

group ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

node ID

</td></tr>

</table>
</td></tr>

</table>


Adds the node to the head (first to be executed) of the group.



#### /scin_g_tail



Add node to tail of group.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

group ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

node ID

</td></tr>

</table>
</td></tr>

</table>


Adds the node to the tail (last to be executed) of the group.



#### /scin_g_freeAll



Deletes all nodes in a group, leaving the group empty but intact.


<table>
<tr><td>

N * <strong>int</strong>

</td><td>

group ID(s)

</td></tr>

</table>


Recursively frees all nodes in the groups specified.



#### /scin_g_deepFree



Free all Scinths in this group and all its sub-groups.


<table>
<tr><td>

N * <strong>int</strong>

</td><td>

group ID(s)

</td></tr>

</table>


Traverses all groups below this group and frees all the Scinths. Sub-groups are not freed. A list of groups may be specified.



#### /scin_g_dumpTree



Post a representation of this group's node subtree.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

group ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

flag; if not 0 the current control (arg) values for synths will be posted

</td></tr>

</table>
</td></tr>

</table>


Posts a representation of this group's node subtree, i.e. all the groups and synths contained within it, optionally including the current control values for synths. The information is posted at the informational level in the log.



#### /scin_g_queryTree



Get a representation of this group's node subtree.


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

group ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

flag: if not 0 the current control (arg) values for Scinths will be included

</td></tr>

</table>
</td></tr>

</table>


Request a representation of this group's node subtree, i.e. all the groups and synths contained within it. Replies to the sender with a <strong>/g_queryTree.reply</strong> message listing all of the nodes contained within the group in the following format:


<table>
<tr><td>

<strong>int</strong>

</td><td>

flag: if Scinth control values are included 1, else 0

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

node ID of the requested group

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

number of child nodes contained within the requested group

</td></tr>
<tr><td>

then for each node in the subtree:

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

node ID

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

number of child nodes contained within this node. If -1 this is a Scinth, if >=0 it's a group

</td></tr>
<tr><td>

then, if this node is a Scinth:

</td></tr>
<tr><td>

<strong>symbol</strong>

</td><td>

the ScinthDef name for this node.

</td></tr>
<tr><td>

then, if flag (see above) is true:

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

numControls for this synth (M)

</td></tr>
<tr><td>

M *

</td><td>
<table>
<tr><td>

<strong>symbol</strong>

</td><td>

control name

</td></tr>
<tr><td>

<strong>float</strong>

</td><td>

value

</td></tr>

</table>
</td></tr>

</table>
</td></tr>

</table>


N.B. The order of nodes corresponds to their execution order on the server. Thus child nodes (those contained within a group) are listed immediately following their parent.



### ImageBuffer Commands
---



The server leaves designation of unique image buffer numbers to the client. Allocation of an image buffer with a number already associated with another image buffer will result in the prior image buffer being deallocated.



#### /scin_ib_allocRead



Decodes an image, optionally resamples it to the provided size, allocates memory on the graphics device, and transfers the decoded image data to the image buffer.


<table>
<tr><td>

<strong>int</strong>

</td><td>

buffer number

</td></tr>
<tr><td>

<strong>string</strong>

</td><td>

path to image file to decode

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

width of image (can be -1 to respect width of source image)

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

height of image (can be -1 to respect height of source image)

</td></tr>
<tr><td>

<strong>bytes</strong>

</td><td>

(optional) an OSC message to execute upon completion

</td></tr>

</table>


If both width and height are provided the source image will be resampled to that size, ignoring aspect ratio. If one of the width or height values is -1 then the image will be resampled to a size respecting the provided dimension and maintaining the aspect ratio of the other dimension. If both values are -1 then the image won't be resampled but will be loaded at original size. For example, for a source image that is 200 pixels wide and 100 pixels tall:


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

<table>


Asynchronous.



Replies to sender with <strong>/scin_done /scin_ib_readImage bufNum</strong> when complete.


</table>


#### /scin_ib_query



Get ImageBuffer info.


<table>
<tr><td>

N * <strong>int</strong>

</td><td>

Image buffer number(s)

</td></tr>

</table>


Responds to the sender with a <strong>/scin_ib_info</strong> message. The arguments to <strong>/scin_ib_info</strong> are as follows:


<table>
<tr><td>

N *

</td><td>
<table>
<tr><td>

<strong>int</strong>

</td><td>

Image bufer number

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Image size in bytes, 0 if invalid

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Image width, -1 if invalid

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

Image height, -1 if invalid

</td></tr>

</table>
</td></tr>

</table>


### Non Real Time Commands
---



#### /scin_nrt_screenShot



Server will save a screenshot image of the next rendered frame to the supplied file name.


<table>
<tr><td>

<strong>string</strong>

</td><td>

file name and path to save the screenshot to.

</td></tr>
<tr><td>

<strong>string</strong>

</td><td>

(optional) a mime type of the desired image file, to guide container and codec selection.

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender twice with updates. The first response is <strong>/scin_nrt_screenShot.ready</strong>, followed by the file name, which is sent once the screenshot is ready to record, and lastly a boolean with file status. The second reponse is on completion of the saving of the screenshot file, the server replies with <strong>/scin_done</strong> followed by <strong>/scin_nrt_screenShot</strong>, the name of the file saved, and a boolean with status on completion.


</table>


#### /scin_nrt_advanceFrame



If framerate is at zero, renders one frame at the current time and then advances the time by the supplied fractional increment, in seconds.


<table>
<tr><td>

<strong>int</strong>

</td><td>

numerator for fractional time advancement

</td></tr>
<tr><td>

<strong>int</strong>

</td><td>

denominator for fractional time advancement

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender with <code>[ /scin_done, /scin_nrt_advanceFrame ]</code>.


</table>


### Testing, Debugging and Development Commands
---



Commands useful for accessing the diagnostic functions or automated validation of scinsynth behavior.



#### /scin_echo



Respond back to the caller with the provided message.


<table>
<tr><td>

<strong>bytes</strong>

</td><td>

An OSC message to respond to the caller with.

</td></tr>

</table>

<table>


Asynchronous.



Replies to the sender immediately with the provided message blob.


</table>


#### /scin_logAppend



Add the supplied string to the logs at the supplied level.


<table>
<tr><td>

<strong>int</strong>

</td><td>

A log level, from 0-6, to associate with this log entry. See the table in <strong>/scin_logLevel</strong> for the log levels.

</td></tr>
<tr><td>

<strong>string</strong>

</td><td>

The string to place in the logs.

</td></tr>

</table>


#### /scin_sleepFor



Puts one of the asynchronous worker threads to sleep for the specified number of seconds.


<table>
<tr><td>

<strong>int</strong>

</td><td>

The number of seconds to sleep for.

</td></tr>

</table>
{{% alert title="Note" %}}


This will tie up one of the available threads on the server, and is therefore not recommended for use in a performance or recording context.

{{% /alert %}}

<table>


Asynchronous.



Replies to the sender on wakeup with <strong>/scin_awake</strong>.


</table>


#### /scin_logCrashReports



Prints a complete list of crash reports in the log, at the informational log level (2).


<table>


Asynchronous.



Replies to sender with <code>[ /scin_done, /scin_logCrashReports ]</code>.


</table>


subsection:/scin_uploadCrashReport



Mark a specific crash report, or all not uploaded reports, as upload requested.


<table>
<tr><td>

<strong>string</strong>

</td><td>

A crash report UUID, or the string "all", in which case all reports will marked for upload.

</td></tr>

</table>

<table>


Asynchronous.



Replies to sender with <code>[ /scin_done, /scin_uploadCrashReport ]</code>.


</table>





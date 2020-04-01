---
title: ScinServer
linkTitle: ScinServer
date: 2020-04-01
weight: 5
description: Represents a Scintillator server application.
---


## Description

This class is analagous to the SuperCollider Classes/Server class. It is a client-side representation of an instance of the Scintillator video synthesis server.

## Class Methods



#### .*default

</h3>
<div class='method'>Get or set the default Scintillator server. This is the server reference that will be used as the default in all server arguments to ScinthDef and others that accept an optional server argument. By default is the local server instance.</div>

#### .*new

</h3>
<div class='method'>Creates a new ScinServer instance. For now only local servers are supported.

##### Arguments

<tr><td class='argumentname'>options<td class='argumentdesc'>An optional instance of ScinServerOptions. If <code>nil</code>, an instance of ScinServerOptions will be created using the default values.</div>

## Instance Methods


#### .-queueScreenShotSync

</h3>
<div class='method'>Call from a Classes/Routine. Requests the server take a screenshot and blocks the calling thread until the screenshot is complete.

##### Arguments

<tr><td class='argumentname'>fileName<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>mimeType<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>onComplete<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>condition<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-logLevel

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>level<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-screenShot

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>fileName<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>mimeType<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>onReady<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>onComplete<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-init

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-advanceFrame

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>num<td class='argumentdesc'>(describe argument here)<tr><td class='argumentname'>denom<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-boot

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-numberOfWarnings

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-numberOfErrors

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-bootSync

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>condition<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-dumpOSC

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>on<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-sync

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>condition<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-sendMsg

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>... msg<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-serverBooting

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-quit

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-doWhenBooted

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>onComplete<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

#### .-serverRunning

</h3>
<div class='method'>(describe method here)

##### Returns:

(describe returnvalue here)</div>

#### .-waitForBoot

</h3>
<div class='method'>(describe method here)

##### Arguments

<tr><td class='argumentname'>onComplete<td class='argumentdesc'>(describe argument here)

##### Returns:

(describe returnvalue here)</div>

## Examples

<code>(some example code)</code>

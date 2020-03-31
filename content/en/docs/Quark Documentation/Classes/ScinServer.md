---
title: ScinServer
linkTitle: ScinServer
date: 2020-03-31
weight: 5
description: Represents a Scintillator server application.
---
This class is analagous to the SuperCollider Classes/Server class. It is a client-side representation of an instance of the Scintillator video synthesis server.
## Class Methods

### default
Get or set the default Scintillator server. This is the server reference that will be used as the default in all server arguments to ScinthDef and others that accept an optional server argument. By default is the local server instance.
### new
Creates a new ScinServer instance. For now only local servers are supported.#### Arguments 
##### options
An optional instance of ScinServerOptions. If , an instance of ScinServerOptions will be created using the default values.### queueScreenShotSync
Call from a Classes/Routine. Requests the server take a screenshot and blocks the calling thread until the screenshot is complete.#### Arguments 
##### fileName
(describe argument here)##### mimeType
(describe argument here)##### onComplete
(describe argument here)##### condition
(describe argument here)(describe returnvalue here)### logLevel
(describe method here)#### Arguments 
##### level
(describe argument here)(describe returnvalue here)### screenShot
(describe method here)#### Arguments 
##### fileName
(describe argument here)##### mimeType
(describe argument here)##### onReady
(describe argument here)##### onComplete
(describe argument here)(describe returnvalue here)### init
(describe method here)(describe returnvalue here)### advanceFrame
(describe method here)#### Arguments 
##### num
(describe argument here)##### denom
(describe argument here)(describe returnvalue here)### boot
(describe method here)(describe returnvalue here)### numberOfWarnings
(describe method here)(describe returnvalue here)### numberOfErrors
(describe method here)(describe returnvalue here)### bootSync
(describe method here)#### Arguments 
##### condition
(describe argument here)(describe returnvalue here)### dumpOSC
(describe method here)#### Arguments 
##### on
(describe argument here)(describe returnvalue here)### sync
(describe method here)#### Arguments 
##### condition
(describe argument here)(describe returnvalue here)### sendMsg
(describe method here)#### Arguments 
##### ... msg
(describe argument here)(describe returnvalue here)### serverBooting
(describe method here)(describe returnvalue here)### quit
(describe method here)(describe returnvalue here)### doWhenBooted
(describe method here)#### Arguments 
##### onComplete
(describe argument here)(describe returnvalue here)### serverRunning
(describe method here)(describe returnvalue here)### waitForBoot
(describe method here)#### Arguments 
##### onComplete
(describe argument here)(describe returnvalue here)
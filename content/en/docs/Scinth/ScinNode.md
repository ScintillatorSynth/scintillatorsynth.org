---
title: ScinNode
linkTitle: ScinNode
weight: 5
description: Abstract superclass of Scinth and ScinGroup
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> <a href="{{< ref "/docs/Scinth/ScinGroup" >}}">ScinGroup</a> Classes/Node 



## Description
---



Analogous to (and patterned after) the SuperCollider audio synthesis Classes/Node, ScinNode represents a server asset and encapsulates behavior common to both <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a>s and <a href="{{< ref "/docs/Scinth/ScinGroup" >}}">ScinGroup</a>s. Users of Scintillator will not normally interact with ScinNodes, choosing rather the derived classes <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> and <a href="{{< ref "/docs/Scinth/ScinGroup" >}}">ScinGroup</a>.



## Class Methods
---



### ScinNode.actionNumberFor(addAction)



A convenience routine to convert one of the addAction symbols, used on various ScinNode, ScinGroup, and ScinServer methods, to the corresponding number used in the OSC messages sent to scinserver.



#### Arguments

##### addAction



A symbol, one of <code>\addToHead</code>, <code>\addToTail</code>, <code>\addBefore</code>, <code>\addAfter</code>, or <code>\addReplace</code>.





#### Returns:



the numeric equivalent of the provided add action.



## Instance Methods
---



### .nodeID



### .nodeID = value


#### Returns:



The unique integer identifier for this node. Can be negative to represent server-generated node ids.



### .server



### .server = value


#### Returns:



The <a href="{{< ref "/docs/Server/ScinServer" >}}">ScinServer</a> where this ScinNode is running.



### .moveToHead(targetGroup)



Sends a request to the server to move this ScinNode to the first position in targetGroup, meaning it will render before any other nodes in that group.



#### Arguments

##### targetGroup



If a ScinGroup the server will move this to the head of that group. If nil the server will move the node to the head of the default group on the server.





### .set(... args)



Set the provided parameter name, value pairs on this node. If this node is a group this command will set for every node contained within the group.



#### Arguments

##### ... args



The pairs of parameter name as a symbol, parameter value as a Float to set.





### .group



### .group = value


#### Returns:



The ScinGroup that contains this ScinNode.



### .run(flag: true)



Sends a command to the server to run or pause this ScinNode, depending on the value of flag. If this group will set the run state on every contained node.



#### Arguments

##### flag



A boolean. If true, will run the ScinNode. If false, will pause.





### .moveBefore(targetNode)



Sends a request to the server to move this ScinNode to directly before the targetNode, contained in the same group as targetNode. This means this Node will execute immediately before targetNode on the server.



#### Arguments

##### targetNode



The node that this node will move directly before.





### .free



Immediately stop this ScinNode and remove it from the render tree. If this node is a group all subnodes will also be freed.



### .moveAfter(targetNode)



Sends a request to the server to move this ScinNode to directly after the targetNode, contained in the same group as targetNode. This means this Node will execute immediately after targetNode on the server.



#### Arguments

##### targetNode



The node that this node will move directly after.





### .moveToTail(targetGroup)



Sends a request to the server to move this ScinNode to the last position in targetGroup, meaning it will render after any other nodes in that group.



#### Arguments

##### targetGroup



If a ScinGroup the server will move this to the tail of that group. If nil the server will move the node to the tail of the default group on the server.




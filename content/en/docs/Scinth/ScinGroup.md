---
title: ScinGroup
linkTitle: ScinGroup
weight: 5
description: Represents a group of other Scinths or ScinGroups running on the Scintillator server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Scinth/Scinth" >}}">Scinth</a> <a href="{{< ref "/docs/Scinth/ScinNode" >}}">ScinNode</a> Classes/Group 



## Description
---



A ScinGroup is analogous to the audio synthesis class Classes/Group. It is useful for controlling the order of execution of Scinths on the server. Like Classes/Node, some of the methods useful for controlling ScinGroups are defined in <a href="{{< ref "/docs/Scinth/ScinNode" >}}">ScinNode</a>, so it is useful to review that help file as well for methods that apply to ScinGroups and Scinths both.



## Class Methods
---



### ScinGroup.new(target, addAction: 'addToTail')



Creates a new ScinGroup and requests its creation on the Scintillator server.



#### Arguments

##### target



A target for this ScinGroup. The target can be a ScinGroup or a Scinth. If it is a ScinServer, it will be converted to the default group on that server. If it is nil, it will be converted to the default group on the default server.



##### addAction


<table>
<tr><td>

\addToHead

</td><td>

add at the head of the group specified by target, which must be a ScinGroup

</td></tr>
<tr><td>

\addToTail

</td><td>

(the default) add at the tail of the group specified by target, which must be a ScinGroup

</td></tr>
<tr><td>

\addAfter

</td><td>

add immediately after target in its server's node order

</td></tr>
<tr><td>

\addBefore

</td><td>

add immediately before target in its server's node order

</td></tr>
<tr><td>

\addReplace

</td><td>

replace target and take its place in its server's node order

</td></tr>

</table>
{{% alert title="Note" %}}


ScinGroup and Scinth both differ from the audio counterparts by providing <code>\addToTail</code> as the default, meaning that new ScinNodes are by default executed last in their group on the server.

{{% /alert %}}




## Instance Methods
---



### .queryTree(callback, includeControls: false)



Query the server to understand the structure of this particular ScinGroup. See the <code>/scin_g_queryTree</code> documentation in the <a href="{{< ref "/docs/Developer Documentation/Scintillator-Scinth-Server-Command-Reference" >}}">Scintillator Scinth Server Command Reference</a> for details about the returned array.



#### Arguments

##### callback



A function to call with the array returned from the server. The one argument to the function will be OSC response array in its entirety.



##### includeControls



A boolean, default false. If true the returned call from the server will include the values of all of the parameters in each Scinth contained in the ScinGroup.





### .moveNodeToHead(node)



Move the supplied node (either a Scinth or a ScinGroup) to the head of this group, so that it is executed first.



#### Arguments

##### node



The Scinth or ScinGroup to move.





### .dumpTree(postControls: false)



Request that the server print a log of all descendants of this group. The log is printed at the informational level, so if log levels are higher than informational the printout will not show. Note that the default logging level for the server is warning and above. To change the log level see the ScinServer documentation.



#### Arguments

##### postControls



A boolean, default false. If true the log message will include the names and values of all the controls in each Scinth logged.





### .freeAll



Recursively frees all children of this ScinGroup, leaving the ScinGroup itself intact and empty.



### .moveNodeToTail(node)



Move the supplied node (either a Scinth or a ScinGroup) to the tail of this group, so that it is executed last.



#### Arguments

##### node



The Scinth or ScinGroup to move.





### .deepFree



Recursively frees all Scinths only for this node and any descendent groups. This leaves the group structure intact but removes all Scinths.



## Examples
---



{{< highlight supercollider >}}
(
~scinths = [];
~groups = [];
// This ScinthDef defines a Scinth that draws a vertical bar of width 0.25
// with color and position defined as parameters.
~bar = ScinthDef.new(\bar, { |x = 0.0, r = 1.0, g = 1.0, b = 1.0|
    var xpos = (VX.pr(VNormPos.pr) - x).abs;
    VRGBAOut.pr(r, g, b, 1.0 - VStep.pr(0.125, xpos));
}).add;
~colors = [
    [ 1.0,   0.224, 0.0   ],
    [ 1.0,   0.498, 0.353 ],
    [ 1.0,   0.357, 0.173 ],
    [ 0.855, 0.192, 0.0   ],
    [ 0.671, 0.149, 0.0   ],
    [ 0.031, 0.486, 0.729 ],
    [ 0.302, 0.616, 0.784 ],
    [ 0.161, 0.541, 0.745 ],
    [ 0.02,  0.361, 0.545 ],
    [ 0.016, 0.282, 0.424 ],
    [ 0.482, 0.929, 0.0   ],
    [ 0.651, 0.941, 0.333 ],
    [ 0.561, 0.933, 0.161 ],
    [ 0.404, 0.773, 0.0   ],
    [ 0.314, 0.604, 0.0   ]
];
~positions = [];
15.do({ |i|
    var x = (i - 7) / 7.0;
    ~positions = ~positions.add(x);
});
// Add 15 Scinths drawing from left to right. Because the default is to add Scinths to the tail of the
// default group, we see the flat list of Scinths in the default group and the first Scinth on the left
// is almost completely covered by the next one, while the last Scinth on the right is completely exposed.
15.do({ |i|
    ~scinths = ~scinths.add(Scinth.new(\bar, [x: ~positions[i],
        r: ~colors[i][0], g: ~colors[i][1], b: ~colors[i][2]]));
});

ScinServer.default.defaultGroup.dumpTree;
)
{{< /highlight >}}

<img src="/images/schelp/ScinGroup-left-to-right.png" />

dumpTree output:

<pre>NODE TREE ScinGroup 1002
    1049 bar
    1050 bar
    1051 bar
    1052 bar
    1053 bar
    1054 bar
    1055 bar
    1056 bar
    1057 bar
    1058 bar
    1059 bar
    1060 bar
    1061 bar
    1062 bar
    1063 bar</pre>

{{< highlight supercollider >}}
(
// Now we create 8 groups and move nodes around so that the first to draw are the outer two
// Scinths, followed by the pair more inside, and so on until ~groups[7] which draws the
// center Scinth last, so it will render on top of all others.
8.do({ |i| ~groups = ~groups.add(ScinGroup.new) });
7.do({ |i|
    ~groups[i].moveNodeToHead(~scinths[i]);
    ~groups[i].moveNodeToTail(~scinths[14 - i]);
});
~scinths[7].moveToHead(~groups[7]);

ScinServer.default.defaultGroup.dumpTree;
)
{{< /highlight >}}

<img src="/images/schelp/ScinGroup-outside-in.png" />

dumpTree output:

<pre>NODE TREE ScinGroup 1002
    1064 group
        1049 bar
        1063 bar
    1065 group
        1050 bar
        1062 bar
    1066 group
        1051 bar
        1061 bar
    1067 group
        1052 bar
        1060 bar
    1068 group
        1053 bar
        1059 bar
    1069 group
        1054 bar
        1058 bar
    1070 group
        1055 bar
        1057 bar
    1071 group
        1056 bar</pre>




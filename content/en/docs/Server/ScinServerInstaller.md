---
title: ScinServerInstaller
linkTitle: ScinServerInstaller
weight: 5
description: An automatic installation script for the Scintillator server.
---
<!-- generated file, please edit the original .schelp file(in the Scintillator repository) and then run schelpToMarkDown.scdscript to regenerate. -->
###### See also: <a href="{{< ref "/docs/Server/ScinServer" >}}">ScinServer</a> 



## Description
---



Scintillator is distributed in two parts, the Quark code and the server binary. The ScinServerInstaller class is distributed with the Scintillator quark and can be used to update or install the server binary.

{{% alert title="Note" %}}


By default, the installer is set to automatically install the server binary.



When automatic installation is enabled ScinServerInstaller will check the binary every time SuperCollider starts.



This behaviour can be disabled, see ScinServerInstaller.disableAutoInstall

{{% /alert %}}


## Class Methods
---



### ScinServerInstaller.setup(cleanup: true, validate: true)



Checks if the server binary is installed and if it is the correct version. If not, it will download, validate, and install the correct server binary.



#### Arguments

##### cleanup



A boolean, default true. If true, the setup script will delete the downloaded files and any old server binaries that might be still around. If false, it will rename any existing server binaries with their version suffix, and keep the downloaded files in place.



##### validate



A boolean, default true. If true, the script will also compute a hash of the downloaded file and compare it to a precomputed value. If false it will skip validation.





### ScinServerInstaller.abort



Call to cancel the installation script while it's in progress.



### ScinServerInstaller.autoInstallEnabled



Returns true if automatic installation is enabled, otherwise false



### ScinServerInstaller.disableAutoInstall



Disables automatic installation



### ScinServerInstaller.enableAutoInstall



Enables automatic installation



## Examples
---



{{< highlight supercollider >}}
// Script to update Scintillator to latest version.
(
Quarks.update("Scintillator");
)

// !! Recompile Class Libary Here !!

(
ScinServerInstaller.setup;
)
{{< /highlight >}}






// Script to parse all of the .schelp files inside the Scintillator Quark and emit markdown files for Hugo
// to convert to HTML, in keeping with the theme of the rest of the scintillatorsynth.org site. It assumes
// the Scintillator Quark is currently configured and installed, because it will crib the path of the Quark
// from the server options class. It also assumes this script is open in the IDE and is the currently
// selected file, in order to derive the target path where to write the output markdown files.
(
var scriptPath, mdPath, pngPath, options, quarkHelpPath, validLinkSet;

scriptPath = PathName.new(PathName.new(ScIDE.currentPath).parentPath);
mdPath = scriptPath.parentPath +/+ "content" +/+ "en" +/+ "docs" +/+ "Quark Documentation";
pngPath = scriptPath.parentPath +/+ "static" +/+ "images" +/+ "schelp";
options = ScinServerOptions.new;
quarkHelpPath = PathName.new(options.quarkPath +/+ "HelpSource");
validLinkSet = IdentitySet.new;
// First pass to build the set of existing schelp files, to construct links from
quarkHelpPath.filesDo({ |helpFile|
	if (helpFile.extension == "schelp", {
		validLinkSet.add((helpFile.folderName ++ "/" ++ helpFile.fileNameWithoutExtension).asSymbol);
	});
});

MarkdownRenderer.mdLinkWhitelist = validLinkSet;

quarkHelpPath.filesDo({ |helpFile|
	if (helpFile.extension == "schelp", {
		var docTree = SCDoc.parseFileFull(helpFile.fullPath);
		var docName = helpFile.folderName ++ "/" ++ helpFile.fileNameWithoutExtension;
		var docEntry = SCDocEntry.new(docTree, docName);
		var mdFilePath = mdPath +/+ helpFile.folderName +/+ helpFile.fileNameWithoutExtension ++ ".md";
		var mdFile = File.open(mdFilePath.standardizePath, "w");
		if (mdFile.isOpen.not, {
			"*** error opening file %".format(mdFilePath).postln;
			^nil;
		});
		"% -> %".format(helpFile.fullPath, docEntry.categories).postln;
		MarkdownRenderer.renderDocument(mdFile, docEntry, docTree);
		mdFile.close;
	}, {
		// Copy directly any png images to our static content directory, so that hopefully the image
		// links "just work."
		if (helpFile.extension == "png", {
			var destFile = pngPath +/+ helpFile.fileName;
			if (File.exists(destFile), {
				File.delete(destFile);
			});
			File.copy(helpFile.fullPath, destFile);
		});
	});
});
)

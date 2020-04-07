// Script to parse all of the .schelp files inside the Scintillator Quark and emit markdown files for Hugo
// to convert to HTML, in keeping with the theme of the rest of the scintillatorsynth.org site. It assumes
// the Scintillator Quark is currently configured and installed, because it will crib the path of the Quark
// from the server options class. It also assumes this script is open in the IDE and is the currently
// selected file, in order to derive the target path where to write the output markdown files.
(
var scriptPath, mdPath, pngPath, options, quarkHelpPath, validLinkSet;
var docTrees, docEntries;
scriptPath = PathName.new(PathName.new(ScIDE.currentPath).parentPath);
mdPath = scriptPath.parentPath +/+ "content" +/+ "en" +/+ "docs";
pngPath = scriptPath.parentPath +/+ "static" +/+ "images" +/+ "schelp";
options = ScinServerOptions.new;
quarkHelpPath = PathName.new(options.quarkPath +/+ "HelpSource");
validLinkSet = IdentityDictionary.new;
docTrees = IdentityDictionary.new;
docEntries = IdentityDictionary.new;

// First pass to build the set of existing schelp files extract their category tree, and
// construct links for them filed under categories.
quarkHelpPath.filesDo({ |helpFile|
	if (helpFile.extension == "schelp", {
		var docTree = SCDoc.parseFileFull(helpFile.fullPath);
		var docName = (helpFile.folderName ++ "/" ++ helpFile.fileNameWithoutExtension).asSymbol;
		var docEntry = SCDocEntry.new(docTree, docName.asString);
		var docPath = (docEntry.categories[0].split($>)[2..]).join("/");
		File.mkdir(mdPath +/+ docPath);
		validLinkSet.put(docName, docPath);
		docTrees.put(docName, docTree);
		docEntries.put(docName, docEntry);
	}, {
		// Copy directly any png images to our static content directory, so assuming all images
		// are provided in the same directory as their referring pages the links should "just work."
		if (helpFile.extension == "png", {
			var destFile = pngPath +/+ helpFile.fileName;
			if (File.exists(destFile), {
				File.delete(destFile);
			});
			File.copy(helpFile.fullPath, destFile);
		});
	});
});

MarkdownRenderer.mdLinkWhitelist = validLinkSet;

quarkHelpPath.filesDo({ |helpFile|
	if (helpFile.extension == "schelp", {
		var docName = (helpFile.folderName ++ "/" ++ helpFile.fileNameWithoutExtension).asSymbol;
		var docEntry = docEntries.at(docName);
		var docTree = docTrees.at(docName);
		var mdFilePath = mdPath +/+ validLinkSet.at(docName) +/+ helpFile.fileNameWithoutExtension ++ ".md";
		var mdFile = File.open(mdFilePath.standardizePath, "w");
		if (mdFile.isOpen.not, {
			"*** error opening file %".format(mdFilePath).postln;
			^nil;
		});
		MarkdownRenderer.renderDocument(mdFile, docEntry, docTree);
		mdFile.close;
	});
});
)

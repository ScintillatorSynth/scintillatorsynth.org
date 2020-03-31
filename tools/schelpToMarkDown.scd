// Script to parse all of the .schelp files inside the Scintillator Quark and emit markdown files for Hugo
// to convert to HTML, in keeping with the theme of the rest of the scintillatorsynth.org site. It assumes
// the Scintillator Quark is currently configured and installed, because it will crib the path of the Quark
// from the server options class. It also assumes this script is open in the IDE and is the currently
// selected file, in order to derive the target path where to write the output markdown files.
(
var traverse, scriptPath, mdPath, quarkHelpPath, options;

traverse = { |tree, mdFile, depth|
	var tab = "";
	depth.do({ tab = tab + " "; });
	if (tree.text.notNil, {
		"%% - %".format(tab, tree.id, tree.text).postln;
	}, {
		"%%".format(tab, tree.id).postln;
	});
	switch (tree.id,
		'DOCUMENT', { },
		'HEADER', {
			mdFile.write("---\n");
		},
		'TITLE', {
			mdFile.write("title: %\nlinkTitle: %\ndate: %\nweight: 5\n".format(
				tree.text, tree.text, Date.getDate.format("%Y-%m-%d")));
		},
		'SUMMARY', {
			mdFile.write("description: %\n".format(tree.text));
		},
		'CATEGORIES', { tree.children = nil; },
		'RELATED', { tree.children = nil; },
		'BODY', {
			// End the header.
			mdFile.write("---\n");
		},
		'DESCRIPTION', { },
		'PROSE', { },
		'TEXT', { mdFile.write(tree.text); },
		'LINK', { mdFile.write(tree.text); },
		'CLASSMETHODS', { mdFile.write("\n## Class Methods\n"); },
		'CMETHOD', { mdFile.write("\n"); },
		'METHODNAMES', { mdFile.write("### "); },
		'STRING', { mdFile.write(tree.text); },
		'METHODBODY', { mdFile.write("\n"); },
		'ARGUMENTS', { mdFile.write("#### Arguments \n"); },
		'ARGUMENT', { mdFile.write("##### %\n".format(tree.text)); },
		{ /* "opener unhandled id: %".format(tree.id).postln; */ }
	);

	if (tree.children.notNil, {
		tree.children.do({ |subTree|
			traverse.value(subTree, mdFile, depth + 1);
		});
	});
};

scriptPath = PathName.new(PathName.new(ScIDE.currentPath).parentPath);
mdPath = scriptPath.parentPath +/+ "content" +/+ "en" +/+ "docs" +/+ "Quark Documentation";
options = ScinServerOptions.new;
quarkHelpPath = PathName.new(options.quarkPath +/+ "HelpSource");
quarkHelpPath.filesDo({ |helpFile|
	if (helpFile.extension == "schelp", {
		var docTree = SCDoc.parseFileFull(helpFile.fullPath);
		var mdFilePath = mdPath +/+ helpFile.folderName +/+ helpFile.fileNameWithoutExtension ++ ".md";
		var mdFile = File.open(mdFilePath.standardizePath, "w");
		if (mdFile.isOpen.not, {
			"*** error opening file %".format(mdFilePath).postln;
			^nil;
		});
		traverse.value(docTree, mdFile, 0);
		mdFile.close;
		^nil;
	});
});
)
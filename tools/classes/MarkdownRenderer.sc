// Given an input .schelp file and an output text file, this specialized class will save Markdown
// compatible with the scintillatorsynth.org Hugo Docsy theme, for use on the website. It allows
// the documentation most relevant to the Scintillator Quark to be provided alongside with the
// Quark and work in the integrated help system, but not have to be re-written to also work on
// the website. This class cribs heavily from SCDocHTMLRenderer, so credit for any good ideas on
// display here belongs to them.
MarkDownRenderer {
	// A set of link destinations for which it is valid to construct a link to the SuperCollider
	// documentation website for.
	classvar scLinkWhitelist;
	classvar scLinkPrefix = "https://doc.sccode.org/";

	*initClass {
		scLinkWhitelist = IdentitySet.new;
		scLinkWhiteList.add('Classes/Server');
	}

	*renderSubtree { |stream, node|
		var f, z, img;
		switch(node.id,
			\PROSE, {
				this.renderChildren(stream, node);
			},
			\NL, { }, // these shouldn't be here..
// Plain text and modal tags
			\TEXT, {
				stream << this.escapeSpecialChars(node.text);
			},
			\LINK, {
				stream << this.htmlForLink(node.text);
			},
			\CODEBLOCK, {
				stream << "<textarea class='editor'>"
				<< this.escapeSpecialChars(node.text)
				<< "</textarea>\n";
			},
			\CODE, {
				stream << "<code>"
				<< this.escapeSpecialChars(node.text)
				<< "</code>";
			},
			\EMPHASIS, {
				stream << "<em>" << this.escapeSpecialChars(node.text) << "</em>";
			},
			\TELETYPEBLOCK, {
				stream << "<pre>" << this.escapeSpecialChars(node.text) << "</pre>";
			},
			\TELETYPE, {
				stream << "<code>" << this.escapeSpecialChars(node.text) << "</code>";
			},
			\STRONG, {
				stream << "<strong>" << this.escapeSpecialChars(node.text) << "</strong>";
			},
			\SOFT, {
				stream << "<span class='soft'>" << this.escapeSpecialChars(node.text) << "</span>";
			},
			\ANCHOR, {
				stream << "<a class='anchor' name='" << this.escapeSpacesInAnchor(node.text) << "'>&nbsp;</a>";
			},
			\KEYWORD, {
				node.children.do {|child|
					stream << "<a class='anchor' name='kw_" << this.escapeSpacesInAnchor(child.text) << "'>&nbsp;</a>";
				}
			},
			\IMAGE, {
				f = node.text.split($#);
				stream << "<div class='image'>";
				img = "<img src='" ++ f[0] ++ "'/>";
				if(f[2].isNil) {
					stream << img;
				} {
					stream << this.htmlForLink(f[2]++"#"++(f[3]?"")++"#"++img,false);
				};
				f[1] !? { stream << "<br><b>" << f[1] << "</b>" }; // ugly..
				stream << "</div>\n";
			},
// Other stuff
			\NOTE, {
				stream << "<div class='note'><span class='notelabel'>NOTE:</span> ";
				noParBreak = true;
				this.renderChildren(stream, node);
				stream << "</div>";
			},
			\WARNING, {
				stream << "<div class='warning'><span class='warninglabel'>WARNING:</span> ";
				noParBreak = true;
				this.renderChildren(stream, node);
				stream << "</div>";
			},
			\FOOTNOTE, {
				footNotes = footNotes.add(node);
				stream << "<a class='footnote anchor' name='footnote_org_"
				<< footNotes.size
				<< "' href='#footnote_"
				<< footNotes.size
				<< "'><sup>"
				<< footNotes.size
				<< "</sup></a> ";
			},
			\CLASSTREE, {
				stream << "<ul class='tree'>";
				this.renderClassTree(stream, node.text.asSymbol.asClass);
				stream << "</ul>";
			},
// Lists and tree
			\LIST, {
				stream << "<ul>\n";
				this.renderChildren(stream, node);
				stream << "</ul>\n";
			},
			\TREE, {
				stream << "<ul class='tree'>\n";
				this.renderChildren(stream, node);
				stream << "</ul>\n";
			},
			\NUMBEREDLIST, {
				stream << "<ol>\n";
				this.renderChildren(stream, node);
				stream << "</ol>\n";
			},
			\ITEM, { // for LIST, TREE and NUMBEREDLIST
				stream << "<li>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
// Definitionlist
			\DEFINITIONLIST, {
				stream << "<dl>\n";
				this.renderChildren(stream, node);
				stream << "</dl>\n";
			},
			\DEFLISTITEM, {
				this.renderChildren(stream, node);
			},
			\TERM, {
				stream << "<dt>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
			\DEFINITION, {
				stream << "<dd>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
// Tables
			\TABLE, {
				stream << "<table>\n";
				this.renderChildren(stream, node);
				stream << "</table>\n";
			},
			\TABROW, {
				stream << "<tr>";
				this.renderChildren(stream, node);
			},
			\TABCOL, {
				stream << "<td>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
// Methods
			\CMETHOD, {
				this.renderMethod(
					stream, node,
					\classMethod,
					currentClass !? {currentClass.class},
					currentImplClass !? {currentImplClass.class}
				);
			},
			\IMETHOD, {
				this.renderMethod(
					stream, node,
					\instanceMethod,
					currentClass,
					currentImplClass
				);
			},
			\METHOD, {
				this.renderMethod(
					stream, node,
					\genericMethod,
					nil, nil
				);
			},
			\CPRIVATE, {},
			\IPRIVATE, {},
			\COPYMETHOD, {},
			\CCOPYMETHOD, {},
			\ICOPYMETHOD, {},
			\ARGUMENTS, {
				stream << "<h4>Arguments:</h4>\n<table class='arguments'>\n";
				currArg = 0;
				if(currentMethod.notNil and: {node.children.size < (currentNArgs-1)}) {
					"SCDoc: In %\n"
					"  Method %% has % args, but doc has % argument:: tags.".format(
						currDoc.fullPath,
						if(currentMethod.ownerClass.isMetaClass) {"*"} {"-"},
						currentMethod.name,
						currentNArgs-1,
						node.children.size,
					).warn;
				};
				this.renderChildren(stream, node);
				stream << "</table>";
			},
			\ARGUMENT, {
				currArg = currArg + 1;
				stream << "<tr><td class='argumentname'>";
				if(node.text.isNil) {
					currentMethod !? {
						if(currentMethod.varArgs and: {currArg==(currentMethod.argNames.size-1)}) {
							stream << "... ";
						};
						stream << if(currArg < currentMethod.argNames.size) {
							if(currArg > minArgs) {
								"("++currentMethod.argNames[currArg]++")";
							} {
								currentMethod.argNames[currArg];
							}
						} {
							"(arg"++currArg++")" // excessive arg
						};
					};
				} {
					stream << if(currentMethod.isNil or: {currArg < currentMethod.argNames.size}) {
						currentMethod !? {
							f = currentMethod.argNames[currArg].asString;
							if(
								(z = if(currentMethod.varArgs and: {currArg==(currentMethod.argNames.size-1)})
										{"... "++f} {f}
								) != node.text;
							) {
								"SCDoc: In %\n"
								"  Method %% has arg named '%', but doc has 'argument:: %'.".format(
									currDoc.fullPath,
									if(currentMethod.ownerClass.isMetaClass) {"*"} {"-"},
									currentMethod.name,
									z,
									node.text,
								).warn;
							};
						};
						if(currArg > minArgs) {
							"("++node.text++")";
						} {
							node.text;
						};
					} {
						"("++node.text++")" // excessive arg
					};
				};
				stream << "<td class='argumentdesc'>";
				this.renderChildren(stream, node);
			},
			\RETURNS, {
				stream << "<h4>Returns:</h4>\n<div class='returnvalue'>";
				this.renderChildren(stream, node);
				stream << "</div>";

			},
			\DISCUSSION, {
				stream << "<h4>Discussion:</h4>\n";
				this.renderChildren(stream, node);
			},
// Sections
			\CLASSMETHODS, {
				if(node.notPrivOnly) {
					stream << "<h2><a class='anchor' name='classmethods'>Class Methods</a></h2>\n";
				};
				this.renderChildren(stream, node);
			},
			\INSTANCEMETHODS, {
				if(node.notPrivOnly) {
					stream << "<h2><a class='anchor' name='instancemethods'>Instance Methods</a></h2>\n";
				};
				this.renderChildren(stream, node);
			},
			\DESCRIPTION, {
				stream << "<h2><a class='anchor' name='description'>Description</a></h2>\n";
				this.renderChildren(stream, node);
			},
			\EXAMPLES, {
				stream << "<h2><a class='anchor' name='examples'>Examples</a></h2>\n";
				this.renderChildren(stream, node);
			},
			\SECTION, {
				stream << "<h2><a class='anchor' name='" << this.escapeSpacesInAnchor(node.text)
				<< "'>" << this.escapeSpecialChars(node.text) << "</a></h2>\n";
				if(node.makeDiv.isNil) {
					this.renderChildren(stream, node);
				} {
					stream << "<div id='" << node.makeDiv << "'>";
					this.renderChildren(stream, node);
					stream << "</div>";
				};
			},
			\SUBSECTION, {
				stream << "<h3><a class='anchor' name='" << this.escapeSpacesInAnchor(node.text)
				<< "'>" << this.escapeSpecialChars(node.text) << "</a></h3>\n";
				if(node.makeDiv.isNil) {
					this.renderChildren(stream, node);
				} {
					stream << "<div id='" << node.makeDiv << "'>";
					this.renderChildren(stream, node);
					stream << "</div>";
				};
			},
			{
				"SCDoc: In %\n"
				"  Unknown SCDocNode id: %".format(currDoc.fullPath, node.id).warn;
				this.renderChildren(stream, node);
			}
		);
	}

	new { |schelp, md|

	}

}

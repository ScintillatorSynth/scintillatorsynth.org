// Given an input .schelp file and an output text file, this specialized class will save Markdown
// compatible with the scintillatorsynth.org Hugo Docsy theme, for use on the website. It allows
// the documentation most relevant to the Scintillator Quark to be provided alongside with the
// Quark and work in the integrated help system, but not have to be re-written to also work on
// the website. This class cribs heavily from SCDocHTMLRenderer, so credit for any good ideas on
// display here belongs to them.
MarkdownRenderer {
	// A set of link destinations for which it is valid to construct a link to the SuperCollider
	// documentation website for.
	classvar scLinkWhitelist;
	classvar scLinkPrefix = "https://doc.sccode.org/";

	classvar <binaryOperatorCharacters = "!@%&*-+=|<>?/";
	classvar currentClass, currentImplClass, currentMethod, currArg;
	classvar currentNArgs;
	classvar footNotes;
	classvar noParBreak;
	classvar currDoc;
	classvar minArgs;
	classvar baseDir;

	*initClass {
		scLinkWhitelist = IdentitySet.new;
		scLinkWhitelist.add('Classes/Server');
	}

	// Markdown is much more tolerant of special characters, but we keep this method now in the
	// event that something does need escaping.
	*escapeSpecialChars { |str|
		^str;
	}

	*escapeSpacesInAnchor { |str|
		^str;
	}

	*mdForLink { |link|
		^link;
	}

	*renderDocument { |stream, docTree|
		this.renderHeader(stream, docTree.children[0]);
		this.renderBody(stream, docTree.children[1]);
	}

	*renderHeader { |stream, header|
		if (header.id !== 'HEADER', {
			"expecting HEADER but got %".format(header.id).warn;
			^nil;
		});
		stream << "---\n";
		header.children.do({ |node|
			switch(node.id,
				\TITLE, {
					stream << "title: %\nlinkTitle: %\ndate: %\nweight: 5\n".format(
                               node.text, node.text, Date.getDate.format("%Y-%m-%d"));
				},
				\SUMMARY, {
					stream << "description: %\n".format(node.text);
				},
				\CATEGORIES, {
					// TBD
				},
				\RELATED, {
					// TBD
				},
				{ "got unknown header tag %".format(node.id).warn; }
			);
		});
		stream << "---\n";
	}

	*renderBody { |stream, body|
		if (body.id !== 'BODY', {
			"expecting BODY but got %".format(body.id).warn;
			^nil;
		});
		this.renderChildren(stream, body);
	}

	*renderChildren { |stream, node|
		node.children.do { |child| this.renderSubtree(stream, child) };
	}

	*renderMethod { |stream, node, methodType, cls, icls|
//		this.renderChildren(stream, node);
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
				stream << this.mdForLink(node.text);
			},
			\CODEBLOCK, {
				stream << "\n```\n"
				<< this.escapeSpecialChars(node.text)
				<< "\n```\n";
			},
			\CODE, {
				stream << "```" << this.escapeSpecialChars(node.text) << "```";
			},
			\EMPHASIS, {
				stream << "*" << this.escapeSpecialChars(node.text) << "*";
			},
			\TELETYPEBLOCK, {
				stream << "\n```\n" << this.escapeSpecialChars(node.text) << "\n```\n";
			},
			\TELETYPE, {
				stream << "`" << this.escapeSpecialChars(node.text) << "`";
			},
			\STRONG, {
				stream << "**" << this.escapeSpecialChars(node.text) << "**";
			},
			\SOFT, {  // no markdown support
				stream << this.escapeSpecialChars(node.text);
			},
			\ANCHOR, {
				// Stick with HTML for this one.
//				stream << "<a class='anchor' name='" << this.escapeSpacesInAnchor(node.text) << "'>&nbsp;</a>";
			},
			\KEYWORD, {
//				node.children.do {|child|
//					stream << "<a class='anchor' name='kw_" << this.escapeSpacesInAnchor(child.text) << "'>&nbsp;</a>";
//				}
			},
			\IMAGE, {
				/* IMAGES TBD
				f = node.text.split($#);
				stream << "<div class='image'>";
				img = "<img src='" ++ f[0] ++ "'/>";
				if(f[2].isNil) {
					stream << img;
				} {
					stream << this.mdForLink(f[2]++"#"++(f[3]?"")++"#"++img,false);
				};
				f[1] !? { stream << "<br><b>" << f[1] << "</b>" }; // ugly..
				stream << "</div>\n";
				*/
			},
// Other stuff
			\NOTE, {
				stream << "{{% alert title=\"Note\" %}}\n";
				noParBreak = true;
				this.renderChildren(stream, node);
				stream << "{{% /alert %}}\n";
			},
			\WARNING, {
				stream << "{{% alert color=\"warning\" %}}\n";
				noParBreak = true;
				this.renderChildren(stream, node);
				stream << "{{% /alert %}}";
			},
			\FOOTNOTE, {
				/* FOOTNOTES TBD
				footNotes = footNotes.add(node);
				stream << "<a class='footnote anchor' name='footnote_org_"
				<< footNotes.size
				<< "' href='#footnote_"
				<< footNotes.size
				<< "'><sup>"
				<< footNotes.size
				<< "</sup></a> ";
				*/
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
				stream << "\n##### Arguments\n";
				currArg = 0;
				if(currentMethod.notNil and: {node.children.size < (currentNArgs-1)}) {
					/*
					"SCDoc: In %\n"
					"  Method %% has % args, but doc has % argument:: tags.".format(
						currDoc.fullPath,
						if(currentMethod.ownerClass.isMetaClass) {"*"} {"-"},
						currentMethod.name,
						currentNArgs-1,
						node.children.size,
					).warn; */
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
							) { /*
								"SCDoc: In %\n"
								"  Method %% has arg named '%', but doc has 'argument:: %'.".format(
									currDoc.fullPath,
									if(currentMethod.ownerClass.isMetaClass) {"*"} {"-"},
									currentMethod.name,
									z,
									node.text,
								).warn; */
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
				stream << "\n### Discussion:\n";
				this.renderChildren(stream, node);
			},
// Sections
			\CLASSMETHODS, {
				if(node.notPrivOnly) {
					stream << "\n## Class Methods\n";
				};
				this.renderChildren(stream, node);
			},
			\INSTANCEMETHODS, {
				if(node.notPrivOnly) {
					stream << "\n## Instance Methods\n";
				};
				this.renderChildren(stream, node);
			},
			\DESCRIPTION, {
				stream << "\n## Description\n";
				this.renderChildren(stream, node);
			},
			\EXAMPLES, {
				stream << "\n## Examples\n";
				this.renderChildren(stream, node);
			},
			\SECTION, {
//				stream << "<h2><a class='anchor' name='" << this.escapeSpacesInAnchor(node.text)
//				<< "'>" << this.escapeSpecialChars(node.text) << "</a></h2>\n";
//				if(node.makeDiv.isNil) {
//					this.renderChildren(stream, node);
//				} {
					stream << "<div id='" << node.makeDiv << "'>";
					this.renderChildren(stream, node);
//					stream << "</div>";
//				};
			},
			\SUBSECTION, {
//				stream << "<h3><a class='anchor' name='" << this.escapeSpacesInAnchor(node.text)
//				<< "'>" << this.escapeSpecialChars(node.text) << "</a></h3>\n";
//				if(node.makeDiv.isNil) {
//					this.renderChildren(stream, node);
//				} {
//					stream << "<div id='" << node.makeDiv << "'>";
					this.renderChildren(stream, node);
//					stream << "</div>";
//				};
			},
			{
				"Unknown SCDocNode id: %".format(node.id).warn;
				this.renderChildren(stream, node);
			}
		);
	}
}

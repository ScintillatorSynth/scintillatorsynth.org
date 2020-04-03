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
	// A set of link destinations for which it is valid to construct an internal link to the
	// Scintillator documentation website for.
	classvar <>mdLinkWhitelist;

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
		scLinkWhitelist.add('Guides/UsingQuarks');
		scLinkWhitelist.add('Classes/Array');
		scLinkWhitelist.add('Classes/Condition');
		scLinkWhitelist.add('Classes/LFSaw');
		scLinkWhitelist.add('Classes/PlayBuf');
		scLinkWhitelist.add('Classes/Routine');
		scLinkWhitelist.add('Classes/Server');
		scLinkWhitelist.add('Classes/ServerOptions');
		scLinkWhitelist.add('Classes/SinOsc');
		scLinkWhitelist.add('Classes/SynthDef');
		scLinkWhitelist.add('Classes/Synth');
		scLinkWhitelist.add('Classes/UGen');
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
		var name = link.split($/).wrapAt(-1);
		var md;
		if (mdLinkWhitelist.includes(link.asSymbol), {
			md = "<a href=\"{{< ref \"/docs/Quark Documentation/%\" >}}\">%</a>".format(link, name);
		}, {
			if (scLinkWhitelist.includes(link.asSymbol), {
				md = "<a href=\"%%.html\">% <img src=\"/images/external-link.svg\" class=\"one-liner\"></a>"
				.format(scLinkPrefix, link, name);
			}, {
				"%: link miss on link %".format(currDoc.path, link).warn;
				md = link;
			});
		});
		^md;
	}

	*renderDocument { |stream, docEntry, docTree|
		var head = docTree.children[0];
		var body = docTree.children[1];
		var redirect;
		currDoc = docEntry;
		footNotes = nil;
		noParBreak = false;

		if(docEntry.isClassDoc) {
			currentClass = docEntry.klass;
			currentImplClass = docEntry.implKlass;
			if(currentClass != Object) {
				body.addDivAfter(\CLASSMETHODS,"inheritedclassmets","Inherited class methods");
				body.addDivAfter(\INSTANCEMETHODS,"inheritedinstmets","Inherited instance methods");
			};
			body.sortClassDoc;
		} {
			currentClass = nil;
			currentImplClass = nil;
		};

		this.renderHeader(stream, head);
		this.renderBody(stream, body);
	}

	*renderHeader { |stream, header|
		if (header.id !== 'HEADER', {
			"expecting HEADER but got %".format(header.id).error;
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
		var methodTypeIndicator;
		var methodCodePrefix;
		var args = node.text ?? ""; // only outside class/instance methods
		var names = node.children[0].children.collect(_.text);
		var mstat, sym, m, m2, mname2;
		var lastargs, args2;
		var x, maxargs = -1;
		var methArgsMismatch = false;

		methodTypeIndicator = switch(
			methodType,
			\classMethod, { "*" },
			\instanceMethod, { "-" },
			\genericMethod, { "" }
		);

		minArgs = inf;
		currentMethod = nil;
		names.do {|mname|
			methodCodePrefix = switch(
				methodType,
				\classMethod, { if(cls.notNil) { cls.name.asString[5..] } { "" } ++ "." },
				\instanceMethod, {
					// If the method name contains any valid binary operator character, remove the
					// "." to reduce confusion.
					if(mname.asString.any(this.binaryOperatorCharacters.contains(_)), { "" }, { "." })
				},
				\genericMethod, { "" }
			);

			mname2 = this.escapeSpecialChars(mname);
			if(cls.notNil) {
				mstat = 0;
				sym = mname.asSymbol;
				//check for normal method or getter
				m = icls !? {icls.findRespondingMethodFor(sym.asGetter)};
				m = m ?? {cls.findRespondingMethodFor(sym.asGetter)};
				m !? {
					mstat = mstat | 1;
					args = this.makeArgString(m);
					args2 = m.argNames !? {m.argNames[1..]};
				};
				//check for setter
				m2 = icls !? {icls.findRespondingMethodFor(sym.asSetter)};
				m2 = m2 ?? {cls.findRespondingMethodFor(sym.asSetter)};
				m2 !? {
					mstat = mstat | 2;
					args = m2.argNames !? {this.makeArgString(m2,false)} ?? {"value"};
					args2 = m2.argNames !? {m2.argNames[1..]};
				};
				maxargs.do {|i|
					var a = args2 !? args2[i];
					var b = lastargs[i];
					if(a!=b and: {a!=nil} and: {b!=nil}) {
						methArgsMismatch = true;
					}
				};
				lastargs = args2;
				case
					{args2.size>maxargs} {
						maxargs = args2.size;
						currentMethod = m2 ?? m;
					}
					{args2.size<minArgs} {
						minArgs = args2.size;
					};
			} {
				m = nil;
				m2 = nil;
				mstat = 1;
			};

			x = {
				// no newline at end to allow for arguments to be appended.
				stream << "\n\n### " << methodCodePrefix << mname;
			};

			switch (mstat,
				// getter only
				1, { x.value; stream << args; },
				// getter and setter
				3, { x.value; },
				// method not found
				0, {
					"SCDoc: In %\n"
					"  Method %% not found.".format(currDoc.fullPath, methodTypeIndicator, mname2).warn;
					x.value;
					stream << ": METHOD NOT FOUND!";
				}
			);

			stream << "\n\n";

			// has setter
			if(mstat & 2 > 0) {
				x.value;
				if(args2.size<2) {
					stream << " = " << args << "\n";
				} {
					stream << "_(" << args << ")\n";
				}
			};

			m = m ?? m2;
/*
			m !? {
//				if(m.isExtensionOf(cls) and: {icls.isNil or: {m.isExtensionOf(icls)}}) {
//					stream << "<div class='extmethod'>From extension in <a href='"
//					<< URI.fromLocalPath(m.filenameSymbol.asString).asString << "'>"
//					<< m.filenameSymbol << "</a></div>\n";
//				} {
					if(m.ownerClass == icls) {
						stream << "<div class='supmethod'>From implementing class</div>\n";
					} {
						if(m.ownerClass != cls) {
							m = m.ownerClass.name;
							m = if(m.isMetaClassName) {m.asString.drop(5)} {m};
							stream << "<div class='supmethod'>From superclass: <a href='"
							<< baseDir << "/Classes/" << m << ".html'>" << m << "</a></div>\n";
						}
					}
//				};
			};
*/
		};

		if(methArgsMismatch) {
			"SCDoc: In %\n"
			"  Grouped methods % do not have the same argument signature."
			.format(currDoc.fullPath, names).warn;
		};

		// ignore trailing mul add arguments
		if(currentMethod.notNil) {
			currentNArgs = currentMethod.argNames.size;
			if(currentNArgs > 2
			and: {currentMethod.argNames[currentNArgs-1] == \add}
			and: {currentMethod.argNames[currentNArgs-2] == \mul}) {
				currentNArgs = currentNArgs - 2;
			}
		} {
			currentNArgs = 0;
		};

		if(node.children.size > 1) {
//			stream << "<div class='method'>";
			this.renderChildren(stream, node.children[1]);
//			stream << "</div>";
		};
		currentMethod = nil;
	}

	*renderSubtree { |stream, node|
		var f, z, img;
		switch(node.id,
			\PROSE, {
				stream << "\n\n";
				this.renderChildren(stream, node);
				stream << "\n\n";
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
				stream << "\n\n{{< highlight supercollider >}}\n"
				<< this.escapeSpecialChars(node.text)
				<< "\n{{< /highlight >}}\n\n";
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
			\SOFT, {  // no markdown support
				stream << this.escapeSpecialChars(node.text);
			},
			\ANCHOR, {
				stream << node.text;
//				stream << "<a class='anchor' name='" << this.escapeSpacesInAnchor(node.text) << "'>&nbsp;</a>";
			},
			\KEYWORD, {
				node.children.do {|child|
					stream << child.text;
//					stream << "<a class='anchor' name='kw_" << this.escapeSpacesInAnchor(child.text) << "'>&nbsp;</a>";
				}
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
				stream << "\n<table>\n";
				this.renderChildren(stream, node);
				stream << "\n</table>\n";
			},
			\DEFLISTITEM, {
				this.renderChildren(stream, node);
			},
			\TERM, {
//				stream << "<dt>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
			\DEFINITION, {
//				stream << "<dd>";
				noParBreak = true;
				this.renderChildren(stream, node);
			},
// Tables
			\TABLE, {
				stream << "\n<table>\n";
				this.renderChildren(stream, node);
				stream << "\n</table>\n";
			},
			\TABROW, {
				stream << "<tr>";
				this.renderChildren(stream, node);
				stream << "</tr>\n";
			},
			\TABCOL, {
				stream << "<td>";
				noParBreak = true;
				this.renderChildren(stream, node);
				stream << "</td>";
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
				stream << "\n\n#### Arguments\n\n";
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
			},
			\ARGUMENT, {
				currArg = currArg + 1;
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
							/* if(
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
							}; */
						};
						if(currArg > minArgs) {
							"("++node.text++")";
						} {
							"##### " ++ node.text;
						};
					} {
						"("++node.text++")" // excessive arg
					};
				};
				stream << "\n\n";
				this.renderChildren(stream, node);
				stream << "\n\n";
			},
			\RETURNS, {
				stream << "\n\n#### Returns:\n\n";
				this.renderChildren(stream, node);

			},
			\DISCUSSION, {
				stream << "\n\n### Discussion:\n\n";
				this.renderChildren(stream, node);
			},
// Sections
			\CLASSMETHODS, {
				if(node.notPrivOnly) {
					stream << "\n\n## Class Methods\n---\n\n";
				};
				this.renderChildren(stream, node);
			},
			\INSTANCEMETHODS, {
				if(node.notPrivOnly) {
					stream << "\n\n## Instance Methods\n---\n\n";
				};
				this.renderChildren(stream, node);
			},
			\DESCRIPTION, {
				stream << "\n\n## Description\n---\n\n";
				this.renderChildren(stream, node);
			},
			\EXAMPLES, {
				stream << "\n\n## Examples\n---\n\n";
				this.renderChildren(stream, node);
			},
			\SECTION, {
				stream << "\n\n### %\n---\n\n".format(node.text);
//				stream << "<h2><a class='anchor' name='" << this.escapeSpacesInAnchor(node.text)
//				<< "'>" << this.escapeSpecialChars(node.text) << "</a></h2>\n";
//				if(node.makeDiv.isNil) {
//					this.renderChildren(stream, node);
//				} {
//					stream << "<div id='" << node.makeDiv << "'>";
					this.renderChildren(stream, node);
//					stream << "</div>";
//				};
			},
			\SUBSECTION, {
				stream << "\n\n#### %\n\n".format(node.text);
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

	*makeArgString {|m, par=true|
		var res = "";
		var value;
		var l = m.argNames;
		var last = l.size-1;
		l.do {|a,i|
			if (i>0) { //skip 'this' (first arg)
				if(i==last and: {m.varArgs}) {
					res = res ++ "... " ++ a;
				} {
					if (i>1) { res = res ++ ", " };
					res = res ++ a;
					(value = m.prototypeFrame[i]) !? {
						value = if(value.class===Float) { value.asString } { value.cs };
						res = res ++ ": " ++ value;
					};
				};
			};
		};
		if (res.notEmpty and: par) {
			^("("++res++")");
		};
		^res;
	}
}

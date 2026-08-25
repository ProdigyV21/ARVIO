package org.jsoup.parser;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.Token;

/* JADX INFO: loaded from: classes5.dex */
enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else {
                if (!token.isDoctype()) {
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.BeforeHtml);
                    return htmlTreeBuilder.process(token);
                }
                Token.Doctype doctypeAsDoctype = token.asDoctype();
                DocumentType documentType = new DocumentType(htmlTreeBuilder.settings.normalizeTag(doctypeAsDoctype.getName()), doctypeAsDoctype.getPublicIdentifier(), doctypeAsDoctype.getSystemIdentifier());
                documentType.setPubSysKey(doctypeAsDoctype.getPubSysKey());
                htmlTreeBuilder.getDocument().appendChild(documentType);
                htmlTreeBuilder.onNodeInserted(documentType);
                if (doctypeAsDoctype.isForceQuirks()) {
                    htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(HtmlTreeBuilderState.BeforeHtml);
            }
            return true;
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag("html");
            htmlTreeBuilder.transition(HtmlTreeBuilderState.BeforeHead);
            return htmlTreeBuilder.process(token);
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                htmlTreeBuilder.insertElementFor(token.asStartTag());
                htmlTreeBuilder.transition(HtmlTreeBuilderState.BeforeHead);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                return anythingElse(token, htmlTreeBuilder);
            }
            if (!token.isEndTag()) {
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return HtmlTreeBuilderState.InBody.process(token, htmlTreeBuilder);
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals(TtmlNode.TAG_HEAD)) {
                htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insertElementFor(token.asStartTag()));
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InHead);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                htmlTreeBuilder.processStartTag(TtmlNode.TAG_HEAD);
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.processStartTag(TtmlNode.TAG_HEAD);
            return htmlTreeBuilder.process(token);
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.processEndTag(TtmlNode.TAG_HEAD);
            return treeBuilder.process(token);
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            int i10 = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i10 == 1) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else {
                if (i10 == 2) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (i10 == 3) {
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    if (strNormalName.equals("html")) {
                        return HtmlTreeBuilderState.InBody.process(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InHeadEmpty)) {
                        Element elementInsertEmptyElementFor = htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                        if (strNormalName.equals(TtmlNode.RUBY_BASE) && elementInsertEmptyElementFor.hasAttr("href")) {
                            htmlTreeBuilder.maybeSetBaseUri(elementInsertEmptyElementFor);
                        }
                    } else if (strNormalName.equals("meta")) {
                        htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                    } else if (strNormalName.equals(LinkHeader.Parameters.Title)) {
                        HtmlTreeBuilderState.handleRcData(startTagAsStartTag, htmlTreeBuilder);
                    } else if (StringUtil.inSorted(strNormalName, Constants.InHeadRaw)) {
                        HtmlTreeBuilderState.handleRawtext(startTagAsStartTag, htmlTreeBuilder);
                    } else if (strNormalName.equals("noscript")) {
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        htmlTreeBuilder.transition(HtmlTreeBuilderState.InHeadNoscript);
                    } else if (strNormalName.equals("script")) {
                        htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                        htmlTreeBuilder.markInsertionMode();
                        htmlTreeBuilder.transition(HtmlTreeBuilderState.Text);
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else {
                        if (strNormalName.equals(TtmlNode.TAG_HEAD)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        if (!strNormalName.equals("template")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        htmlTreeBuilder.insertMarkerToFormattingElements();
                        htmlTreeBuilder.framesetOk(false);
                        HtmlTreeBuilderState htmlTreeBuilderState = HtmlTreeBuilderState.InTemplate;
                        htmlTreeBuilder.transition(htmlTreeBuilderState);
                        htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState);
                    }
                } else {
                    if (i10 != 4) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    String strNormalName2 = token.asEndTag().normalName();
                    if (strNormalName2.equals(TtmlNode.TAG_HEAD)) {
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterHead);
                    } else {
                        if (StringUtil.inSorted(strNormalName2, Constants.InHeadEnd)) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        if (!strNormalName2.equals("template")) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        if (htmlTreeBuilder.onStack(strNormalName2)) {
                            htmlTreeBuilder.generateImpliedEndTags(true);
                            if (!htmlTreeBuilder.currentElementIs(strNormalName2)) {
                                htmlTreeBuilder.error(this);
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName2);
                            htmlTreeBuilder.clearFormattingElementsToLastMarker();
                            htmlTreeBuilder.popTemplateMode();
                            htmlTreeBuilder.resetInsertionMode();
                        } else {
                            htmlTreeBuilder.error(this);
                        }
                    }
                }
            }
            return true;
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.insertCharacterNode(new Token.Character().data(token.toString()));
            return true;
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("noscript")) {
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InHead);
                return true;
            }
            if (HtmlTreeBuilderState.isWhitespace(token) || token.isComment() || (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("br")) {
                return anythingElse(token, htmlTreeBuilder);
            }
            if ((!token.isStartTag() || !StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) && !token.isEndTag()) {
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag(TtmlNode.TAG_BODY);
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (!token.isStartTag()) {
                if (!token.isEndTag()) {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
                String strNormalName = token.asEndTag().normalName();
                if (StringUtil.inSorted(strNormalName, Constants.AfterHeadBody)) {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
                if (strNormalName.equals("template")) {
                    htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                    return true;
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            Token.StartTag startTagAsStartTag = token.asStartTag();
            String strNormalName2 = startTagAsStartTag.normalName();
            if (strNormalName2.equals("html")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (strNormalName2.equals(TtmlNode.TAG_BODY)) {
                htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                htmlTreeBuilder.framesetOk(false);
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InBody);
                return true;
            }
            if (strNormalName2.equals("frameset")) {
                htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InFrameset);
                return true;
            }
            if (!StringUtil.inSorted(strNormalName2, Constants.InBodyStartToHead)) {
                if (strNormalName2.equals(TtmlNode.TAG_HEAD)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                anythingElse(token, htmlTreeBuilder);
                return true;
            }
            htmlTreeBuilder.error(this);
            Element headElement = htmlTreeBuilder.getHeadElement();
            htmlTreeBuilder.push(headElement);
            htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
            htmlTreeBuilder.removeFromStack(headElement);
            return true;
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        private static final int MaxStackScan = 24;

        private boolean inBodyEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Token.EndTag endTagAsEndTag;
            String strNormalName;
            endTagAsEndTag = token.asEndTag();
            strNormalName = endTagAsEndTag.normalName();
            strNormalName.getClass();
            switch (strNormalName) {
                case "template":
                    htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                    return true;
                case "p":
                    if (!htmlTreeBuilder.inButtonScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processStartTag(strNormalName);
                        return htmlTreeBuilder.process(endTagAsEndTag);
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case "br":
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.processStartTag("br");
                    return false;
                case "dd":
                case "dt":
                    if (!htmlTreeBuilder.inScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    String[] strArr = Constants.Headings;
                    if (!htmlTreeBuilder.inScope(strArr)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strArr);
                    return true;
                case "li":
                    if (!htmlTreeBuilder.inListItemScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(strNormalName);
                    if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(strNormalName);
                    return true;
                case "body":
                    if (!htmlTreeBuilder.inScope(TtmlNode.TAG_BODY)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.onNodeClosed(htmlTreeBuilder.getFromStack(TtmlNode.TAG_BODY));
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterBody);
                    return true;
                case "form":
                    if (!htmlTreeBuilder.onStack("template")) {
                        FormElement formElement = htmlTreeBuilder.getFormElement();
                        htmlTreeBuilder.setFormElement(null);
                        if (formElement == null || !htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.removeFromStack(formElement);
                    } else {
                        if (!htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(strNormalName);
                    }
                    return true;
                case "html":
                    if (!htmlTreeBuilder.onStack(TtmlNode.TAG_BODY)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterBody);
                    return htmlTreeBuilder.process(token);
                case "span":
                case "sarcasm":
                    return anyOtherEndTag(token, htmlTreeBuilder);
                default:
                    if (StringUtil.inSorted(strNormalName, Constants.InBodyEndAdoptionFormatters)) {
                        return inBodyEndTagAdoption(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InBodyEndClosers)) {
                        if (!htmlTreeBuilder.inScope(strNormalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(strNormalName);
                    } else {
                        if (!StringUtil.inSorted(strNormalName, Constants.InBodyStartApplets)) {
                            return anyOtherEndTag(token, htmlTreeBuilder);
                        }
                        if (!htmlTreeBuilder.inScope(ContentDisposition.Parameters.Name)) {
                            if (!htmlTreeBuilder.inScope(strNormalName)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.generateImpliedEndTags();
                            if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                                htmlTreeBuilder.error(this);
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName);
                            htmlTreeBuilder.clearFormattingElementsToLastMarker();
                        }
                    }
                    return true;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v10 */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6, types: [int] */
        private boolean inBodyEndTagAdoption(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String strNormalName = token.asEndTag().normalName();
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            boolean z = false;
            int i10 = 0;
            while (i10 < 8) {
                Element activeFormattingElement = htmlTreeBuilder.getActiveFormattingElement(strNormalName);
                if (activeFormattingElement == null) {
                    return anyOtherEndTag(token, htmlTreeBuilder);
                }
                if (!htmlTreeBuilder.onStack(activeFormattingElement)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    return true;
                }
                if (!htmlTreeBuilder.inScope(activeFormattingElement.normalName())) {
                    htmlTreeBuilder.error(this);
                    return z;
                }
                if (htmlTreeBuilder.currentElement() != activeFormattingElement) {
                    htmlTreeBuilder.error(this);
                }
                int size = stack.size();
                Element element = null;
                int iPositionOfElement = -1;
                boolean z5 = z;
                int i11 = 1;
                Element element2 = null;
                while (true) {
                    if (i11 >= size || i11 >= 64) {
                        break;
                    }
                    Element element3 = stack.get(i11);
                    if (element3 != activeFormattingElement) {
                        if (z5 && HtmlTreeBuilder.isSpecial(element3)) {
                            element = element3;
                            break;
                        }
                    } else {
                        element2 = stack.get(i11 - 1);
                        iPositionOfElement = htmlTreeBuilder.positionOfElement(element3);
                        z5 = true;
                    }
                    i11++;
                }
                if (element == null) {
                    htmlTreeBuilder.popStackToClose(activeFormattingElement.normalName());
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    return true;
                }
                Element elementAboveOnStack = element;
                Element element4 = elementAboveOnStack;
                for (?? r82 = z; r82 < 3; r82++) {
                    if (htmlTreeBuilder.onStack(elementAboveOnStack)) {
                        elementAboveOnStack = htmlTreeBuilder.aboveOnStack(elementAboveOnStack);
                    }
                    if (!htmlTreeBuilder.isInActiveFormattingElements(elementAboveOnStack)) {
                        htmlTreeBuilder.removeFromStack(elementAboveOnStack);
                    } else {
                        if (elementAboveOnStack == activeFormattingElement) {
                            break;
                        }
                        Element element5 = new Element(htmlTreeBuilder.tagFor(elementAboveOnStack.nodeName(), ParseSettings.preserveCase), htmlTreeBuilder.getBaseUri());
                        htmlTreeBuilder.replaceActiveFormattingElement(elementAboveOnStack, element5);
                        htmlTreeBuilder.replaceOnStack(elementAboveOnStack, element5);
                        if (element4 == element) {
                            iPositionOfElement = htmlTreeBuilder.positionOfElement(element5) + 1;
                        }
                        if (element4.parent() != null) {
                            element4.remove();
                        }
                        element5.appendChild(element4);
                        elementAboveOnStack = element5;
                        element4 = elementAboveOnStack;
                    }
                }
                if (element2 != null) {
                    if (StringUtil.inSorted(element2.normalName(), Constants.InBodyEndTableFosters)) {
                        if (element4.parent() != null) {
                            element4.remove();
                        }
                        htmlTreeBuilder.insertInFosterParent(element4);
                    } else {
                        if (element4.parent() != null) {
                            element4.remove();
                        }
                        element2.appendChild(element4);
                    }
                }
                Element element6 = new Element(activeFormattingElement.tag(), htmlTreeBuilder.getBaseUri());
                element6.attributes().addAll(activeFormattingElement.attributes());
                element6.appendChildren(element.childNodes());
                element.appendChild(element6);
                htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                htmlTreeBuilder.pushWithBookmark(element6, iPositionOfElement);
                htmlTreeBuilder.removeFromStack(activeFormattingElement);
                htmlTreeBuilder.insertOnStackAfter(element, element6);
                i10++;
                z = false;
            }
            return true;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0030  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean inBodyStartTag(org.jsoup.parser.Token r23, org.jsoup.parser.HtmlTreeBuilder r24) {
            /*
                Method dump skipped, instruction units count: 2504
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.inBodyStartTag(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
        
            return true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean anyOtherEndTag(org.jsoup.parser.Token r7, org.jsoup.parser.HtmlTreeBuilder r8) {
            /*
                r6 = this;
                org.jsoup.parser.Token$EndTag r7 = r7.asEndTag()
                java.lang.String r7 = r7.normalName
                java.util.ArrayList r0 = r8.getStack()
                org.jsoup.nodes.Element r1 = r8.getFromStack(r7)
                r2 = 0
                if (r1 != 0) goto L15
                r8.error(r6)
                return r2
            L15:
                int r1 = r0.size()
                r3 = 1
                int r1 = r1 - r3
            L1b:
                if (r1 < 0) goto L46
                java.lang.Object r4 = r0.get(r1)
                org.jsoup.nodes.Element r4 = (org.jsoup.nodes.Element) r4
                boolean r5 = r4.nameIs(r7)
                if (r5 == 0) goto L39
                r8.generateImpliedEndTags(r7)
                boolean r0 = r8.currentElementIs(r7)
                if (r0 != 0) goto L35
                r8.error(r6)
            L35:
                r8.popStackToClose(r7)
                goto L46
            L39:
                boolean r4 = org.jsoup.parser.HtmlTreeBuilder.isSpecial(r4)
                if (r4 == 0) goto L43
                r8.error(r6)
                return r2
            L43:
                int r1 = r1 + (-1)
                goto L1b
            L46:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.anyOtherEndTag(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    return inBodyStartTag(token, htmlTreeBuilder);
                case 4:
                    return inBodyEndTag(token, htmlTreeBuilder);
                case 5:
                    Token.Character characterAsCharacter = token.asCharacter();
                    if (characterAsCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.framesetOk() && HtmlTreeBuilderState.isWhitespace(characterAsCharacter)) {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                        return true;
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case 6:
                    if (htmlTreeBuilder.templateModeSize() > 0) {
                        return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InTemplate);
                    }
                    if (!htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return true;
                default:
                    return true;
            }
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return htmlTreeBuilder.process(token);
            }
            if (!token.isEndTag()) {
                return true;
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        public boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.setFosterInserts(true);
            htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            htmlTreeBuilder.setFosterInserts(false);
            return true;
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter() && StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                htmlTreeBuilder.resetPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableText);
                return htmlTreeBuilder.process(token);
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!token.isStartTag()) {
                if (!token.isEndTag()) {
                    if (!token.isEOF()) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if (htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                    }
                    return true;
                }
                String strNormalName = token.asEndTag().normalName();
                if (strNormalName.equals("table")) {
                    if (!htmlTreeBuilder.inTableScope(strNormalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.popStackToClose("table");
                    htmlTreeBuilder.resetInsertionMode();
                } else {
                    if (StringUtil.inSorted(strNormalName, Constants.InTableEndErr)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (!strNormalName.equals("template")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                }
                return true;
            }
            Token.StartTag startTagAsStartTag = token.asStartTag();
            String strNormalName2 = startTagAsStartTag.normalName();
            if (strNormalName2.equals(MediaTrack.ROLE_CAPTION)) {
                htmlTreeBuilder.clearStackToTableContext();
                htmlTreeBuilder.insertMarkerToFormattingElements();
                htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InCaption);
            } else if (strNormalName2.equals("colgroup")) {
                htmlTreeBuilder.clearStackToTableContext();
                htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InColumnGroup);
            } else {
                if (strNormalName2.equals("col")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.processStartTag("colgroup");
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(strNormalName2, Constants.InTableToBody)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableBody);
                } else {
                    if (StringUtil.inSorted(strNormalName2, Constants.InTableAddBody)) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.processStartTag("tbody");
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName2.equals("table")) {
                        htmlTreeBuilder.error(this);
                        if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                            return false;
                        }
                        htmlTreeBuilder.popStackToClose(strNormalName2);
                        if (htmlTreeBuilder.resetInsertionMode()) {
                            return htmlTreeBuilder.process(token);
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                        return true;
                    }
                    if (StringUtil.inSorted(strNormalName2, Constants.InTableToHead)) {
                        return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                    }
                    if (strNormalName2.equals("input")) {
                        if (!startTagAsStartTag.hasAttributes() || !startTagAsStartTag.attributes.get(LinkHeader.Parameters.Type).equalsIgnoreCase("hidden")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                    } else {
                        if (!strNormalName2.equals("form")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.error(this);
                        if (htmlTreeBuilder.getFormElement() != null || htmlTreeBuilder.onStack("template")) {
                            return false;
                        }
                        htmlTreeBuilder.insertFormElement(startTagAsStartTag, false, false);
                    }
                }
            }
            return true;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.type == Token.TokenType.Character) {
                Token.Character characterAsCharacter = token.asCharacter();
                if (characterAsCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.addPendingTableCharacters(characterAsCharacter);
                return true;
            }
            if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                Token token2 = htmlTreeBuilder.currentToken;
                for (Token.Character character : htmlTreeBuilder.getPendingTableCharacters()) {
                    htmlTreeBuilder.currentToken = character;
                    if (HtmlTreeBuilderState.isWhitespace(character)) {
                        htmlTreeBuilder.insertCharacterNode(character);
                    } else {
                        htmlTreeBuilder.error(this);
                        if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                            htmlTreeBuilder.setFosterInserts(true);
                            htmlTreeBuilder.process(character, HtmlTreeBuilderState.InBody);
                            htmlTreeBuilder.setFosterInserts(false);
                        } else {
                            htmlTreeBuilder.process(character, HtmlTreeBuilderState.InBody);
                        }
                    }
                }
                htmlTreeBuilder.currentToken = token2;
                htmlTreeBuilder.resetPendingTableCharacters();
            }
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return htmlTreeBuilder.process(token);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag() && token.asEndTag().normalName().equals(MediaTrack.ROLE_CAPTION)) {
                if (!htmlTreeBuilder.inTableScope(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags();
                if (!htmlTreeBuilder.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(MediaTrack.ROLE_CAPTION);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InTable);
                return true;
            }
            if ((!token.isStartTag() || !StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) && (!token.isEndTag() || !token.asEndTag().normalName().equals("table"))) {
                if (!token.isEndTag() || !StringUtil.inSorted(token.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                    return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!htmlTreeBuilder.inTableScope(MediaTrack.ROLE_CAPTION)) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.generateImpliedEndTags(false);
            if (!htmlTreeBuilder.currentElementIs(MediaTrack.ROLE_CAPTION)) {
                htmlTreeBuilder.error(this);
            }
            htmlTreeBuilder.popStackToClose(MediaTrack.ROLE_CAPTION);
            htmlTreeBuilder.clearFormattingElementsToLastMarker();
            HtmlTreeBuilderState htmlTreeBuilderState = HtmlTreeBuilderState.InTable;
            htmlTreeBuilder.transition(htmlTreeBuilderState);
            htmlTreeBuilderState.process(token, htmlTreeBuilder);
            return true;
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.currentElementIs("colgroup")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(HtmlTreeBuilderState.InTable);
            htmlTreeBuilder.process(token);
            return true;
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Token.StartTag startTagAsStartTag;
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            int i10 = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i10 != 1) {
                if (i10 == 2) {
                    htmlTreeBuilder.error(this);
                } else if (i10 == 3) {
                    startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    strNormalName.getClass();
                    switch (strNormalName) {
                        case "template":
                            htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                            break;
                        case "col":
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                            break;
                        case "html":
                            return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                } else {
                    if (i10 != 4) {
                        if (i10 != 6) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        if (htmlTreeBuilder.currentElementIs("html")) {
                            return true;
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    String strNormalName2 = token.asEndTag().normalName();
                    strNormalName2.getClass();
                    if (strNormalName2.equals("template")) {
                        htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                    } else {
                        if (!strNormalName2.equals("colgroup")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        if (!htmlTreeBuilder.currentElementIs(strNormalName2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(HtmlTreeBuilderState.InTable);
                    }
                }
            } else {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            }
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InTable);
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.inTableScope("tbody") && !htmlTreeBuilder.inTableScope("thead") && !htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.clearStackToTableBodyContext();
            htmlTreeBuilder.processEndTag(htmlTreeBuilder.currentElement().normalName());
            return htmlTreeBuilder.process(token);
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            int i10 = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i10 == 3) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (strNormalName.equals("tr")) {
                    htmlTreeBuilder.clearStackToTableBodyContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.InRow);
                    return true;
                }
                if (!StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                    return StringUtil.inSorted(strNormalName, Constants.InTableBodyExit) ? exitTableBody(token, htmlTreeBuilder) : anythingElse(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.processStartTag("tr");
                return htmlTreeBuilder.process(startTagAsStartTag);
            }
            if (i10 != 4) {
                return anythingElse(token, htmlTreeBuilder);
            }
            String strNormalName2 = token.asEndTag().normalName();
            if (!StringUtil.inSorted(strNormalName2, Constants.InTableEndIgnore)) {
                if (strNormalName2.equals("table")) {
                    return exitTableBody(token, htmlTreeBuilder);
                }
                if (!StringUtil.inSorted(strNormalName2, Constants.InTableBodyEndIgnore)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.clearStackToTableBodyContext();
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(HtmlTreeBuilderState.InTable);
            return true;
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InTable);
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                String strNormalName = startTagAsStartTag.normalName();
                if (StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    htmlTreeBuilder.transition(HtmlTreeBuilderState.InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    return true;
                }
                if (!StringUtil.inSorted(strNormalName, Constants.InRowMissing)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if (!htmlTreeBuilder.inTableScope("tr")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.clearStackToTableRowContext();
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableBody);
                return htmlTreeBuilder.process(token);
            }
            if (!token.isEndTag()) {
                return anythingElse(token, htmlTreeBuilder);
            }
            String strNormalName2 = token.asEndTag().normalName();
            if (strNormalName2.equals("tr")) {
                if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.clearStackToTableRowContext();
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableBody);
                return true;
            }
            if (strNormalName2.equals("table")) {
                if (!htmlTreeBuilder.inTableScope("tr")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.clearStackToTableRowContext();
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableBody);
                return htmlTreeBuilder.process(token);
            }
            if (!StringUtil.inSorted(strNormalName2, Constants.InTableToBody)) {
                if (!StringUtil.inSorted(strNormalName2, Constants.InRowIgnore)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!htmlTreeBuilder.inTableScope(strNormalName2)) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!htmlTreeBuilder.inTableScope("tr")) {
                return false;
            }
            htmlTreeBuilder.clearStackToTableRowContext();
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(HtmlTreeBuilderState.InTableBody);
            return htmlTreeBuilder.process(token);
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope("td")) {
                htmlTreeBuilder.processEndTag("td");
            } else {
                htmlTreeBuilder.processEndTag("th");
            }
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!token.isEndTag()) {
                if (!token.isStartTag() || !StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if (htmlTreeBuilder.inTableScope("td") || htmlTreeBuilder.inTableScope("th")) {
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            String strNormalName = token.asEndTag().normalName();
            if (!StringUtil.inSorted(strNormalName, Constants.InCellNames)) {
                if (StringUtil.inSorted(strNormalName, Constants.InCellBody)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (!StringUtil.inSorted(strNormalName, Constants.InCellTable)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if (htmlTreeBuilder.inTableScope(strNormalName)) {
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!htmlTreeBuilder.inTableScope(strNormalName)) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.transition(HtmlTreeBuilderState.InRow);
                return false;
            }
            htmlTreeBuilder.generateImpliedEndTags();
            if (!htmlTreeBuilder.currentElementIs(strNormalName)) {
                htmlTreeBuilder.error(this);
            }
            htmlTreeBuilder.popStackToClose(strNormalName);
            htmlTreeBuilder.clearFormattingElementsToLastMarker();
            htmlTreeBuilder.transition(HtmlTreeBuilderState.InRow);
            return true;
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String strNormalName;
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    Token.StartTag startTagAsStartTag = token.asStartTag();
                    String strNormalName2 = startTagAsStartTag.normalName();
                    if (strNormalName2.equals("html")) {
                        return htmlTreeBuilder.process(startTagAsStartTag, HtmlTreeBuilderState.InBody);
                    }
                    if (strNormalName2.equals("option")) {
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    } else {
                        if (!strNormalName2.equals("optgroup")) {
                            if (strNormalName2.equals("select")) {
                                htmlTreeBuilder.error(this);
                                return htmlTreeBuilder.processEndTag("select");
                            }
                            if (!StringUtil.inSorted(strNormalName2, Constants.InSelectEnd)) {
                                return (strNormalName2.equals("script") || strNormalName2.equals("template")) ? htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead) : anythingElse(token, htmlTreeBuilder);
                            }
                            htmlTreeBuilder.error(this);
                            if (!htmlTreeBuilder.inSelectScope("select")) {
                                return false;
                            }
                            htmlTreeBuilder.processEndTag("select");
                            return htmlTreeBuilder.process(startTagAsStartTag);
                        }
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        if (htmlTreeBuilder.currentElementIs("optgroup")) {
                            htmlTreeBuilder.processEndTag("optgroup");
                        }
                        htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                    }
                    return true;
                case 4:
                    strNormalName = token.asEndTag().normalName();
                    strNormalName.getClass();
                    switch (strNormalName) {
                        case "template":
                            return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                        case "option":
                            if (htmlTreeBuilder.currentElementIs("option")) {
                                htmlTreeBuilder.pop();
                            } else {
                                htmlTreeBuilder.error(this);
                            }
                            return true;
                        case "select":
                            if (!htmlTreeBuilder.inSelectScope(strNormalName)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.popStackToClose(strNormalName);
                            htmlTreeBuilder.resetInsertionMode();
                            return true;
                        case "optgroup":
                            if (htmlTreeBuilder.currentElementIs("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).nameIs("optgroup")) {
                                htmlTreeBuilder.processEndTag("option");
                            }
                            if (htmlTreeBuilder.currentElementIs("optgroup")) {
                                htmlTreeBuilder.pop();
                            } else {
                                htmlTreeBuilder.error(this);
                            }
                            return true;
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                    Token.Character characterAsCharacter = token.asCharacter();
                    if (characterAsCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                    return true;
                case 6:
                    if (!htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                    }
                    return true;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InSelectTableEnd)) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.popStackToClose("select");
                htmlTreeBuilder.resetInsertionMode();
                return htmlTreeBuilder.process(token);
            }
            if (!token.isEndTag() || !StringUtil.inSorted(token.asEndTag().normalName(), Constants.InSelectTableEnd)) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InSelect);
            }
            htmlTreeBuilder.error(this);
            if (!htmlTreeBuilder.inTableScope(token.asEndTag().normalName())) {
                return false;
            }
            htmlTreeBuilder.popStackToClose("select");
            htmlTreeBuilder.resetInsertionMode();
            return htmlTreeBuilder.process(token);
        }
    },
    InTemplate { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
                    return true;
                case 3:
                    String strNormalName = token.asStartTag().normalName();
                    if (StringUtil.inSorted(strNormalName, Constants.InTemplateToHead)) {
                        htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                        return true;
                    }
                    if (StringUtil.inSorted(strNormalName, Constants.InTemplateToTable)) {
                        htmlTreeBuilder.popTemplateMode();
                        HtmlTreeBuilderState htmlTreeBuilderState = HtmlTreeBuilderState.InTable;
                        htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState);
                        htmlTreeBuilder.transition(htmlTreeBuilderState);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("col")) {
                        htmlTreeBuilder.popTemplateMode();
                        HtmlTreeBuilderState htmlTreeBuilderState2 = HtmlTreeBuilderState.InColumnGroup;
                        htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState2);
                        htmlTreeBuilder.transition(htmlTreeBuilderState2);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("tr")) {
                        htmlTreeBuilder.popTemplateMode();
                        HtmlTreeBuilderState htmlTreeBuilderState3 = HtmlTreeBuilderState.InTableBody;
                        htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState3);
                        htmlTreeBuilder.transition(htmlTreeBuilderState3);
                        return htmlTreeBuilder.process(token);
                    }
                    if (strNormalName.equals("td") || strNormalName.equals("th")) {
                        htmlTreeBuilder.popTemplateMode();
                        HtmlTreeBuilderState htmlTreeBuilderState4 = HtmlTreeBuilderState.InRow;
                        htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState4);
                        htmlTreeBuilder.transition(htmlTreeBuilderState4);
                        return htmlTreeBuilder.process(token);
                    }
                    htmlTreeBuilder.popTemplateMode();
                    HtmlTreeBuilderState htmlTreeBuilderState5 = HtmlTreeBuilderState.InBody;
                    htmlTreeBuilder.pushTemplateMode(htmlTreeBuilderState5);
                    htmlTreeBuilder.transition(htmlTreeBuilderState5);
                    return htmlTreeBuilder.process(token);
                case 4:
                    if (token.asEndTag().normalName().equals("template")) {
                        htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                case 6:
                    if (!htmlTreeBuilder.onStack("template")) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.popStackToClose("template");
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.popTemplateMode();
                    htmlTreeBuilder.resetInsertionMode();
                    if (htmlTreeBuilder.state() == HtmlTreeBuilderState.InTemplate || htmlTreeBuilder.templateModeSize() >= 12) {
                        return true;
                    }
                    return htmlTreeBuilder.process(token);
                default:
                    return true;
            }
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Element fromStack = htmlTreeBuilder.getFromStack("html");
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                if (fromStack != null) {
                    htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), fromStack);
                    return true;
                }
                htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (!token.isEndTag() || !token.asEndTag().normalName().equals("html")) {
                if (token.isEOF()) {
                    return true;
                }
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.resetBody();
                return htmlTreeBuilder.process(token);
            }
            if (htmlTreeBuilder.isFragmentParsing()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (fromStack != null) {
                htmlTreeBuilder.onNodeClosed(fromStack);
            }
            htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterAfterBody);
            return true;
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Token.StartTag startTagAsStartTag;
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else {
                if (token.isDoctype()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (token.isStartTag()) {
                    startTagAsStartTag = token.asStartTag();
                    String strNormalName = startTagAsStartTag.normalName();
                    strNormalName.getClass();
                    switch (strNormalName) {
                        case "frameset":
                            htmlTreeBuilder.insertElementFor(startTagAsStartTag);
                            break;
                        case "html":
                            return htmlTreeBuilder.process(startTagAsStartTag, HtmlTreeBuilderState.InBody);
                        case "frame":
                            htmlTreeBuilder.insertEmptyElementFor(startTagAsStartTag);
                            break;
                        case "noframes":
                            return htmlTreeBuilder.process(startTagAsStartTag, HtmlTreeBuilderState.InHead);
                        default:
                            htmlTreeBuilder.error(this);
                            return false;
                    }
                } else if (token.isEndTag() && token.asEndTag().normalName().equals("frameset")) {
                    if (htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.pop();
                    if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElementIs("frameset")) {
                        htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterFrameset);
                    }
                } else {
                    if (!token.isEOF()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (!htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                    }
                }
            }
            return true;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                htmlTreeBuilder.transition(HtmlTreeBuilderState.AfterAfterFrameset);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
            }
            if (token.isEOF()) {
                return true;
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype() || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), htmlTreeBuilder.getDocument());
                return true;
            }
            if (token.isEOF()) {
                return true;
            }
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.resetBody();
            return htmlTreeBuilder.process(token);
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InBody);
            }
            if (token.isEOF()) {
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                return htmlTreeBuilder.process(token, HtmlTreeBuilderState.InHead);
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.24
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            int i10 = AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i10 == 1) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
            } else if (i10 == 2) {
                htmlTreeBuilder.error(this);
            } else if (i10 == 3) {
                Token.StartTag startTagAsStartTag = token.asStartTag();
                if (StringUtil.in(startTagAsStartTag.normalName, Constants.InForeignToHtml)) {
                    return processAsHtml(token, htmlTreeBuilder);
                }
                if (startTagAsStartTag.normalName.equals("font") && (startTagAsStartTag.hasAttributeIgnoreCase(TtmlNode.ATTR_TTS_COLOR) || startTagAsStartTag.hasAttributeIgnoreCase("face") || startTagAsStartTag.hasAttributeIgnoreCase(ContentDisposition.Parameters.Size))) {
                    return processAsHtml(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.insertForeignElementFor(startTagAsStartTag, htmlTreeBuilder.currentElement().tag().namespace());
            } else if (i10 == 4) {
                Token.EndTag endTagAsEndTag = token.asEndTag();
                if (endTagAsEndTag.normalName.equals("br") || endTagAsEndTag.normalName.equals(TtmlNode.TAG_P)) {
                    return processAsHtml(token, htmlTreeBuilder);
                }
                if (endTagAsEndTag.normalName.equals("script") && htmlTreeBuilder.currentElementIs("script", Parser.NamespaceSvg)) {
                    htmlTreeBuilder.pop();
                    return true;
                }
                ArrayList<Element> stack = htmlTreeBuilder.getStack();
                if (stack.isEmpty()) {
                    Validate.wtf("Stack unexpectedly empty");
                }
                int size = stack.size() - 1;
                Element element = stack.get(size);
                if (!element.nameIs(endTagAsEndTag.normalName)) {
                    htmlTreeBuilder.error(this);
                }
                while (size != 0) {
                    if (element.nameIs(endTagAsEndTag.normalName)) {
                        htmlTreeBuilder.popStackToCloseAnyNamespace(element.normalName());
                        return true;
                    }
                    size--;
                    element = stack.get(size);
                    if (element.tag().namespace().equals(Parser.NamespaceHtml)) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                }
            } else if (i10 == 5) {
                Token.Character characterAsCharacter = token.asCharacter();
                if (characterAsCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                    htmlTreeBuilder.error(this);
                } else if (HtmlTreeBuilderState.isWhitespace(characterAsCharacter)) {
                    htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                } else {
                    htmlTreeBuilder.insertCharacterNode(characterAsCharacter);
                    htmlTreeBuilder.framesetOk(false);
                }
            }
            return true;
        }

        public boolean processAsHtml(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.state().process(token, htmlTreeBuilder);
        }
    };

    private static final String nullString = String.valueOf((char) 0);

    /* JADX INFO: renamed from: org.jsoup.parser.HtmlTreeBuilderState$25, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass25 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$Token$TokenType;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            $SwitchMap$org$jsoup$parser$Token$TokenType = iArr;
            try {
                iArr[Token.TokenType.Comment.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Doctype.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.StartTag.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EndTag.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Character.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static final class Constants {
        static final String[] InHeadEmpty = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link"};
        static final String[] InHeadRaw = {"noframes", "style"};
        static final String[] InHeadEnd = {TtmlNode.TAG_BODY, "br", "html"};
        static final String[] AfterHeadBody = {TtmlNode.TAG_BODY, "br", "html"};
        static final String[] BeforeHtmlToHead = {TtmlNode.TAG_BODY, "br", TtmlNode.TAG_HEAD, "html"};
        static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", "style"};
        static final String[] InBodyStartToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "template", LinkHeader.Parameters.Title};
        static final String[] InBodyStartPClosers = {"address", "article", "aside", "blockquote", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", TtmlNode.TAG_P, "section", "summary", "ul"};
        static final String[] Headings = {"h1", "h2", "h3", "h4", "h5", "h6"};
        static final String[] InBodyStartLiBreakers = {"address", TtmlNode.TAG_DIV, TtmlNode.TAG_P};
        static final String[] DdDt = {"dd", "dt"};
        static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        static final String[] InBodyStartMedia = {"param", "source", "track"};
        static final String[] InBodyStartInputAttribs = {"action", ContentDisposition.Parameters.Name, "prompt"};
        static final String[] InBodyStartDrop = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "frame", TtmlNode.TAG_HEAD, "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndClosers = {"address", "article", "aside", "blockquote", "button", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
        static final String[] InBodyEndOtherErrors = {TtmlNode.TAG_BODY, "dd", "dt", "html", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndAdoptionFormatters = {CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "big", "code", "em", "font", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "nobr", CmcdData.STREAMING_FORMAT_SS, "small", "strike", "strong", TtmlNode.TAG_TT, "u"};
        static final String[] InBodyEndTableFosters = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableToBody = {"tbody", "tfoot", "thead"};
        static final String[] InTableAddBody = {"td", "th", "tr"};
        static final String[] InTableToHead = {"script", "style", "template"};
        static final String[] InCellNames = {"td", "th"};
        static final String[] InCellBody = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html"};
        static final String[] InCellTable = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InCellCol = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndErr = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableFoster = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableBodyExit = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InTableBodyEndIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th", "tr"};
        static final String[] InRowMissing = {MediaTrack.ROLE_CAPTION, "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        static final String[] InRowIgnore = {TtmlNode.TAG_BODY, MediaTrack.ROLE_CAPTION, "col", "colgroup", "html", "td", "th"};
        static final String[] InSelectEnd = {"input", "keygen", "textarea"};
        static final String[] InSelectTableEnd = {MediaTrack.ROLE_CAPTION, "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndIgnore = {"tbody", "tfoot", "thead"};
        static final String[] InHeadNoscriptIgnore = {TtmlNode.TAG_HEAD, "noscript"};
        static final String[] InCaptionIgnore = {TtmlNode.TAG_BODY, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTemplateToHead = {TtmlNode.RUBY_BASE, "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", LinkHeader.Parameters.Title};
        static final String[] InTemplateToTable = {MediaTrack.ROLE_CAPTION, "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InForeignToHtml = {"b", "big", "blockquote", TtmlNode.TAG_BODY, "br", TtmlNode.CENTER, "code", "dd", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "em", "embed", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "hr", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "img", "li", "listing", "menu", "meta", "nobr", "ol", TtmlNode.TAG_P, "pre", TtmlNode.ATTR_TTS_RUBY, CmcdData.STREAMING_FORMAT_SS, "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "table", TtmlNode.TAG_TT, "u", "ul", "var"};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insertElementFor(startTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insertElementFor(startTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.isCharacter()) {
            return StringUtil.isBlank(token.asCharacter().getData());
        }
        return false;
    }

    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);
}

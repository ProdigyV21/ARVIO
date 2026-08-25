package org.jsoup.parser;

import a0.c;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import fi.iki.elonen.t;
import io.ktor.http.LinkHeader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/* JADX INFO: loaded from: classes5.dex */
public class HtmlTreeBuilder extends TreeBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MaxScopeSearchDepth = 100;
    private static final int maxQueueDepth = 256;
    private static final int maxUsedFormattingElements = 12;
    private boolean baseUriSetFromDoc;
    private Element contextElement;
    private Token.EndTag emptyEnd;
    private FormElement formElement;
    private ArrayList<Element> formattingElements;
    private boolean fosterInserts;
    private boolean fragmentParsing;
    private boolean framesetOk;
    private Element headElement;
    private HtmlTreeBuilderState originalState;
    private List<Token.Character> pendingTableCharacters;
    private final String[] specificScopeTarget = {null};
    private HtmlTreeBuilderState state;
    private ArrayList<HtmlTreeBuilderState> tmplInsertMode;
    static final String[] TagsSearchInScope = {"applet", MediaTrack.ROLE_CAPTION, "html", "marquee", "object", "table", "td", "th"};
    static final String[] TagSearchList = {"ol", "ul"};
    static final String[] TagSearchButton = {"button"};
    static final String[] TagSearchTableScope = {"html", "table"};
    static final String[] TagSearchSelectScope = {"optgroup", "option"};
    static final String[] TagSearchEndTags = {"dd", "dt", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc"};
    static final String[] TagThoroughSearchEndTags = {MediaTrack.ROLE_CAPTION, "colgroup", "dd", "dt", "li", "optgroup", "option", TtmlNode.TAG_P, "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
    static final String[] TagSearchSpecial = {"address", "applet", "area", "article", "aside", TtmlNode.RUBY_BASE, "basefont", "bgsound", "blockquote", TtmlNode.TAG_BODY, "br", "button", MediaTrack.ROLE_CAPTION, TtmlNode.CENTER, "col", "colgroup", "command", "dd", "details", "dir", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", TtmlNode.TAG_P, "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", LinkHeader.Parameters.Title, "tr", "ul", "wbr", "xmp"};
    static final String[] TagMathMlTextIntegration = {"mi", "mn", "mo", "ms", "mtext"};
    static final String[] TagSvgHtmlIntegration = {"desc", "foreignObject", LinkHeader.Parameters.Title};

    private void clearStackToContext(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = this.stack.get(size);
            if (Parser.NamespaceHtml.equals(element.tag().namespace()) && (StringUtil.in(element.normalName(), strArr) || element.nameIs("html"))) {
                return;
            }
            pop();
        }
    }

    private void doInsertElement(Element element, Token token) {
        FormElement formElement;
        if (element.tag().isFormListed() && (formElement = this.formElement) != null) {
            formElement.addElement(element);
        }
        if (element.hasAttr("xmlns") && !element.attr("xmlns").equals(element.tag().namespace())) {
            error("Invalid xmlns attribute [%s] on tag [%s]", element.attr("xmlns"), element.tagName());
        }
        if (isFosterInserts() && StringUtil.inSorted(currentElement().normalName(), HtmlTreeBuilderState.Constants.InTableFoster)) {
            insertInFosterParent(element);
        } else {
            currentElement().appendChild(element);
        }
        push(element);
    }

    private boolean inSpecificScope(String str, String[] strArr, String[] strArr2) {
        String[] strArr3 = this.specificScopeTarget;
        strArr3[0] = str;
        return inSpecificScope(strArr3, strArr, strArr2);
    }

    public static boolean isHtmlIntegration(Element element) {
        if (Parser.NamespaceMathml.equals(element.tag().namespace()) && element.nameIs("annotation-xml")) {
            String strNormalize = Normalizer.normalize(element.attr("encoding"));
            if (strNormalize.equals(t.MIME_HTML) || strNormalize.equals("application/xhtml+xml")) {
                return true;
            }
        }
        return Parser.NamespaceSvg.equals(element.tag().namespace()) && StringUtil.in(element.tagName(), TagSvgHtmlIntegration);
    }

    public static boolean isMathmlTextIntegration(Element element) {
        return Parser.NamespaceMathml.equals(element.tag().namespace()) && StringUtil.inSorted(element.normalName(), TagMathMlTextIntegration);
    }

    private static boolean isSameFormattingElement(Element element, Element element2) {
        return element.normalName().equals(element2.normalName()) && element.attributes().equals(element2.attributes());
    }

    public static boolean isSpecial(Element element) {
        return StringUtil.inSorted(element.normalName(), TagSearchSpecial);
    }

    private static void replaceInQueue(ArrayList<Element> arrayList, Element element, Element element2) {
        int iLastIndexOf = arrayList.lastIndexOf(element);
        Validate.isTrue(iLastIndexOf != -1);
        arrayList.set(iLastIndexOf, element2);
    }

    public Element aboveOnStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                return this.stack.get(size - 1);
            }
        }
        return null;
    }

    public void addPendingTableCharacters(Token.Character character) {
        this.pendingTableCharacters.add(character.clone());
    }

    public void checkActiveFormattingElements(Element element) {
        int size = this.formattingElements.size();
        int i10 = size - 13;
        int i11 = 0;
        if (i10 < 0) {
            i10 = 0;
        }
        for (int i12 = size - 1; i12 >= i10; i12--) {
            Element element2 = this.formattingElements.get(i12);
            if (element2 == null) {
                return;
            }
            if (isSameFormattingElement(element, element2)) {
                i11++;
            }
            if (i11 == 3) {
                this.formattingElements.remove(i12);
                return;
            }
        }
    }

    public void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty() && removeLastFormattingElement() != null) {
        }
    }

    public void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    public void clearStackToTableContext() {
        clearStackToContext("table", "template");
    }

    public void clearStackToTableRowContext() {
        clearStackToContext("tr", "template");
    }

    public void closeElement(String str) {
        generateImpliedEndTags(str);
        if (!str.equals(currentElement().normalName())) {
            error(state());
        }
        popStackToClose(str);
    }

    public Element createElementFor(Token.StartTag startTag, String str, boolean z) {
        Attributes attributesNormalizeAttributes = startTag.attributes;
        if (!z) {
            attributesNormalizeAttributes = this.settings.normalizeAttributes(attributesNormalizeAttributes);
        }
        if (attributesNormalizeAttributes != null && !attributesNormalizeAttributes.isEmpty() && attributesNormalizeAttributes.deduplicate(this.settings) > 0) {
            error("Dropped duplicate attribute(s) in tag [%s]", startTag.normalName);
        }
        Tag tagTagFor = tagFor(startTag.tagName, str, z ? ParseSettings.preserveCase : this.settings);
        return tagTagFor.normalName().equals("form") ? new FormElement(tagTagFor, null, attributesNormalizeAttributes) : new Element(tagTagFor, null, attributesNormalizeAttributes);
    }

    public HtmlTreeBuilderState currentTemplateMode() {
        if (this.tmplInsertMode.size() > 0) {
            return (HtmlTreeBuilderState) c.h(1, this.tmplInsertMode);
        }
        return null;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    public void error(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.parser.getErrors().canAddError()) {
            this.parser.getErrors().add(new ParseError(this.reader, "Unexpected %s token [%s] when in state [%s]", this.currentToken.tokenType(), this.currentToken, htmlTreeBuilderState));
        }
    }

    public void framesetOk(boolean z) {
        this.framesetOk = z;
    }

    public void generateImpliedEndTags(String str) {
        while (StringUtil.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            if (str != null && currentElementIs(str)) {
                return;
            } else {
                pop();
            }
        }
    }

    public Element getActiveFormattingElement(String str) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            Element element = this.formattingElements.get(size);
            if (element == null) {
                return null;
            }
            if (element.nameIs(str)) {
                return element;
            }
        }
        return null;
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public Document getDocument() {
        return this.doc;
    }

    public FormElement getFormElement() {
        return this.formElement;
    }

    public Element getFromStack(String str) {
        int size = this.stack.size();
        int i10 = size - 1;
        int i11 = i10 >= 256 ? size - 257 : 0;
        while (i10 >= i11) {
            Element element = this.stack.get(i10);
            if (element.elementIs(str, Parser.NamespaceHtml)) {
                return element;
            }
            i10--;
        }
        return null;
    }

    public Element getHeadElement() {
        return this.headElement;
    }

    public List<Token.Character> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    public ArrayList<Element> getStack() {
        return this.stack;
    }

    public boolean inButtonScope(String str) {
        return inScope(str, TagSearchButton);
    }

    public boolean inListItemScope(String str) {
        return inScope(str, TagSearchList);
    }

    public boolean inScope(String[] strArr) {
        return inSpecificScope(strArr, TagsSearchInScope, (String[]) null);
    }

    public boolean inSelectScope(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            String strNormalName = this.stack.get(size).normalName();
            if (strNormalName.equals(str)) {
                return true;
            }
            if (!StringUtil.inSorted(strNormalName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    public boolean inTableScope(String str) {
        return inSpecificScope(str, TagSearchTableScope, (String[]) null);
    }

    @Override // org.jsoup.parser.TreeBuilder
    public void initialiseParse(Reader reader, String str, Parser parser) {
        super.initialiseParse(reader, str, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList<>();
        this.tmplInsertMode = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag(this);
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    public void insertCharacterNode(Token.Character character) {
        insertCharacterToElement(character, currentElement());
    }

    public void insertCharacterToElement(Token.Character character, Element element) {
        String strNormalName = element.normalName();
        String data = character.getData();
        Node cDataNode = character.isCData() ? new CDataNode(data) : isContentForTagData(strNormalName) ? new DataNode(data) : new TextNode(data);
        element.appendChild(cDataNode);
        onNodeInserted(cDataNode);
    }

    public void insertCommentNode(Token.Comment comment) {
        Comment comment2 = new Comment(comment.getData());
        currentElement().appendChild(comment2);
        onNodeInserted(comment2);
    }

    public Element insertElementFor(Token.StartTag startTag) {
        Element elementCreateElementFor = createElementFor(startTag, Parser.NamespaceHtml, false);
        doInsertElement(elementCreateElementFor, startTag);
        if (startTag.isSelfClosing()) {
            Tag tag = elementCreateElementFor.tag();
            if (!tag.isKnownTag()) {
                tag.setSelfClosing();
            } else if (!tag.isEmpty()) {
                this.tokeniser.error("Tag [%s] cannot be self closing; not a void tag", tag.normalName());
            }
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit(this.emptyEnd.reset().name(elementCreateElementFor.tagName()));
        }
        return elementCreateElementFor;
    }

    public Element insertEmptyElementFor(Token.StartTag startTag) {
        Element elementCreateElementFor = createElementFor(startTag, Parser.NamespaceHtml, false);
        doInsertElement(elementCreateElementFor, startTag);
        pop();
        return elementCreateElementFor;
    }

    public Element insertForeignElementFor(Token.StartTag startTag, String str) {
        Element elementCreateElementFor = createElementFor(startTag, str, true);
        doInsertElement(elementCreateElementFor, startTag);
        if (startTag.isSelfClosing()) {
            elementCreateElementFor.tag().setSelfClosing();
            pop();
        }
        return elementCreateElementFor;
    }

    public FormElement insertFormElement(Token.StartTag startTag, boolean z, boolean z5) {
        FormElement formElement = (FormElement) createElementFor(startTag, Parser.NamespaceHtml, false);
        if (!z5 || !onStack("template")) {
            setFormElement(formElement);
        }
        doInsertElement(formElement, startTag);
        if (!z) {
            pop();
        }
        return formElement;
    }

    public void insertInFosterParent(Node node) {
        Element elementAboveOnStack;
        Element fromStack = getFromStack("table");
        boolean z = false;
        if (fromStack == null) {
            elementAboveOnStack = this.stack.get(0);
        } else if (fromStack.parent() != null) {
            elementAboveOnStack = fromStack.parent();
            z = true;
        } else {
            elementAboveOnStack = aboveOnStack(fromStack);
        }
        if (!z) {
            elementAboveOnStack.appendChild(node);
        } else {
            Validate.notNull(fromStack);
            fromStack.before(node);
        }
    }

    public void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    public void insertOnStackAfter(Element element, Element element2) {
        int iLastIndexOf = this.stack.lastIndexOf(element);
        Validate.isTrue(iLastIndexOf != -1);
        this.stack.add(iLastIndexOf + 1, element2);
    }

    @Override // org.jsoup.parser.TreeBuilder
    public boolean isContentForTagData(String str) {
        return str.equals("script") || str.equals("style");
    }

    public boolean isFosterInserts() {
        return this.fosterInserts;
    }

    public boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    public boolean isInActiveFormattingElements(Element element) {
        return onStack(this.formattingElements, element);
    }

    public Element lastFormattingElement() {
        if (this.formattingElements.size() > 0) {
            return (Element) c.h(1, this.formattingElements);
        }
        return null;
    }

    public void markInsertionMode() {
        this.originalState = this.state;
    }

    public void maybeSetBaseUri(Element element) {
        if (this.baseUriSetFromDoc) {
            return;
        }
        String strAbsUrl = element.absUrl("href");
        if (strAbsUrl.length() != 0) {
            this.baseUri = strAbsUrl;
            this.baseUriSetFromDoc = true;
            this.doc.setBaseUri(strAbsUrl);
        }
    }

    public boolean onStack(Element element) {
        return onStack(this.stack, element);
    }

    public boolean onStackNot(String[] strArr) {
        int size = this.stack.size();
        int i10 = size - 1;
        int i11 = i10 > 100 ? size - 101 : 0;
        while (i10 >= i11) {
            if (!StringUtil.inSorted(this.stack.get(i10).normalName(), strArr)) {
                return true;
            }
            i10--;
        }
        return false;
    }

    public HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public List<Node> parseFragment(String str, Element element, String str2, Parser parser) {
        Element element2;
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(new StringReader(str), str2, parser);
        this.contextElement = element;
        this.fragmentParsing = true;
        if (element != null) {
            if (element.ownerDocument() != null) {
                this.doc.quirksMode(element.ownerDocument().quirksMode());
            }
            String strNormalName = element.normalName();
            strNormalName.getClass();
            switch (strNormalName) {
                case "template":
                    this.tokeniser.transition(TokeniserState.Data);
                    pushTemplateMode(HtmlTreeBuilderState.InTemplate);
                    break;
                case "iframe":
                case "xmp":
                case "style":
                case "noframes":
                case "noembed":
                    this.tokeniser.transition(TokeniserState.Rawtext);
                    break;
                case "textarea":
                case "title":
                    this.tokeniser.transition(TokeniserState.Rcdata);
                    break;
                case "script":
                    this.tokeniser.transition(TokeniserState.ScriptData);
                    break;
                case "plaintext":
                    this.tokeniser.transition(TokeniserState.PLAINTEXT);
                    break;
                default:
                    this.tokeniser.transition(TokeniserState.Data);
                    break;
            }
            element2 = new Element(tagFor(strNormalName, this.settings), str2);
            this.doc.appendChild(element2);
            push(element2);
            resetInsertionMode();
            Element elementParent = element;
            while (true) {
                if (elementParent != null) {
                    if (elementParent instanceof FormElement) {
                        this.formElement = (FormElement) elementParent;
                    } else {
                        elementParent = elementParent.parent();
                    }
                }
            }
        } else {
            element2 = null;
        }
        runParser();
        if (element == null) {
            return this.doc.childNodes();
        }
        List<Node> listSiblingNodes = element2.siblingNodes();
        if (!listSiblingNodes.isEmpty()) {
            element2.insertChildren(-1, listSiblingNodes);
        }
        return element2.childNodes();
    }

    public Element popStackToClose(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element elementPop = pop();
            if (elementPop.elementIs(str, Parser.NamespaceHtml)) {
                return elementPop;
            }
        }
        return null;
    }

    public Element popStackToCloseAnyNamespace(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element elementPop = pop();
            if (elementPop.nameIs(str)) {
                return elementPop;
            }
        }
        return null;
    }

    public HtmlTreeBuilderState popTemplateMode() {
        if (this.tmplInsertMode.size() <= 0) {
            return null;
        }
        return this.tmplInsertMode.remove(r0.size() - 1);
    }

    public int positionOfElement(Element element) {
        for (int i10 = 0; i10 < this.formattingElements.size(); i10++) {
            if (element == this.formattingElements.get(i10)) {
                return i10;
            }
        }
        return -1;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        return (useCurrentOrForeignInsert(token) ? this.state : HtmlTreeBuilderState.ForeignContent).process(token, this);
    }

    public void pushActiveFormattingElements(Element element) {
        checkActiveFormattingElements(element);
        this.formattingElements.add(element);
    }

    public void pushTemplateMode(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.tmplInsertMode.add(htmlTreeBuilderState);
    }

    public void pushWithBookmark(Element element, int i10) {
        checkActiveFormattingElements(element);
        try {
            this.formattingElements.add(i10, element);
        } catch (IndexOutOfBoundsException unused) {
            this.formattingElements.add(element);
        }
    }

    public void reconstructFormattingElements() {
        Element elementLastFormattingElement;
        if (this.stack.size() > 256 || (elementLastFormattingElement = lastFormattingElement()) == null || onStack(elementLastFormattingElement)) {
            return;
        }
        int size = this.formattingElements.size();
        int i10 = size - 12;
        if (i10 < 0) {
            i10 = 0;
        }
        boolean z = true;
        int i11 = size - 1;
        int i12 = i11;
        while (i12 != i10) {
            i12--;
            elementLastFormattingElement = this.formattingElements.get(i12);
            if (elementLastFormattingElement == null || onStack(elementLastFormattingElement)) {
                z = false;
                break;
            }
        }
        while (true) {
            if (!z) {
                i12++;
                elementLastFormattingElement = this.formattingElements.get(i12);
            }
            Validate.notNull(elementLastFormattingElement);
            Element element = new Element(tagFor(elementLastFormattingElement.normalName(), this.settings), null, elementLastFormattingElement.attributes().clone());
            doInsertElement(element, null);
            this.formattingElements.set(i12, element);
            if (i12 == i11) {
                return;
            } else {
                z = false;
            }
        }
    }

    public void removeFromActiveFormattingElements(Element element) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            if (this.formattingElements.get(size) == element) {
                this.formattingElements.remove(size);
                return;
            }
        }
    }

    public boolean removeFromStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                this.stack.remove(size);
                onNodeClosed(element);
                return true;
            }
        }
        return false;
    }

    public Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    public void replaceActiveFormattingElement(Element element, Element element2) {
        replaceInQueue(this.formattingElements, element, element2);
    }

    public void replaceOnStack(Element element, Element element2) {
        replaceInQueue(this.stack, element, element2);
    }

    public void resetBody() {
        if (!onStack(TtmlNode.TAG_BODY)) {
            this.stack.add(this.doc.body());
        }
        transition(HtmlTreeBuilderState.InBody);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0155  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean resetInsertionMode() {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.resetInsertionMode():boolean");
    }

    public void resetPendingTableCharacters() {
        this.pendingTableCharacters.clear();
    }

    public void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    public void setFosterInserts(boolean z) {
        this.fosterInserts = z;
    }

    public void setHeadElement(Element element) {
        this.headElement = element;
    }

    public HtmlTreeBuilderState state() {
        return this.state;
    }

    public int templateModeSize() {
        return this.tmplInsertMode.size();
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }

    public void transition(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.state = htmlTreeBuilderState;
    }

    public boolean useCurrentOrForeignInsert(Token token) {
        if (this.stack.isEmpty()) {
            return true;
        }
        Element elementCurrentElement = currentElement();
        String strNamespace = elementCurrentElement.tag().namespace();
        if (Parser.NamespaceHtml.equals(strNamespace)) {
            return true;
        }
        if (isMathmlTextIntegration(elementCurrentElement) && ((token.isStartTag() && !"mglyph".equals(token.asStartTag().normalName) && !"malignmark".equals(token.asStartTag().normalName)) || token.isCharacter())) {
            return true;
        }
        if (Parser.NamespaceMathml.equals(strNamespace) && elementCurrentElement.nameIs("annotation-xml") && token.isStartTag() && "svg".equals(token.asStartTag().normalName)) {
            return true;
        }
        if (isHtmlIntegration(elementCurrentElement) && (token.isStartTag() || token.isCharacter())) {
            return true;
        }
        return token.isEOF();
    }

    public boolean framesetOk() {
        return this.framesetOk;
    }

    public boolean inScope(String str) {
        return inScope(str, null);
    }

    @Override // org.jsoup.parser.TreeBuilder
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    public boolean onStack(String str) {
        return getFromStack(str) != null;
    }

    private boolean inSpecificScope(String[] strArr, String[] strArr2, String[] strArr3) {
        int size = this.stack.size();
        int i10 = size - 1;
        int i11 = i10 > 100 ? size - 101 : 0;
        while (i10 >= i11) {
            Element element = this.stack.get(i10);
            if (element.tag().namespace().equals(Parser.NamespaceHtml)) {
                String strNormalName = element.normalName();
                if (StringUtil.inSorted(strNormalName, strArr)) {
                    return true;
                }
                if (StringUtil.inSorted(strNormalName, strArr2)) {
                    return false;
                }
                if (strArr3 != null && StringUtil.inSorted(strNormalName, strArr3)) {
                    return false;
                }
            }
            i10--;
        }
        return false;
    }

    private static boolean onStack(ArrayList<Element> arrayList, Element element) {
        int size = arrayList.size();
        int i10 = size - 1;
        int i11 = i10 >= 256 ? size - 257 : 0;
        while (i10 >= i11) {
            if (arrayList.get(i10) == element) {
                return true;
            }
            i10--;
        }
        return false;
    }

    public boolean inScope(String str, String[] strArr) {
        return inSpecificScope(str, TagsSearchInScope, strArr);
    }

    public boolean process(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        return htmlTreeBuilderState.process(token, this);
    }

    public void generateImpliedEndTags() {
        generateImpliedEndTags(false);
    }

    public void popStackToClose(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element elementPop = pop();
            if (StringUtil.inSorted(elementPop.normalName(), strArr) && Parser.NamespaceHtml.equals(elementPop.tag().namespace())) {
                return;
            }
        }
    }

    public void generateImpliedEndTags(boolean z) {
        String[] strArr = z ? TagThoroughSearchEndTags : TagSearchEndTags;
        while (Parser.NamespaceHtml.equals(currentElement().tag().namespace()) && StringUtil.inSorted(currentElement().normalName(), strArr)) {
            pop();
        }
    }
}

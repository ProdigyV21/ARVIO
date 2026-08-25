package org.jsoup.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.internal.SharedConstants;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Range;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes5.dex */
public abstract class TreeBuilder {
    String baseUri;
    Token currentToken;
    Document doc;
    private final Token.EndTag end = new Token.EndTag(this);
    protected Parser parser;
    CharacterReader reader;
    Map<String, Tag> seenTags;
    ParseSettings settings;
    ArrayList<Element> stack;
    private Token.StartTag start;
    Tokeniser tokeniser;
    boolean trackSourceRange;

    private void trackNodePosition(Node node, boolean z) {
        if (this.trackSourceRange) {
            Token token = this.currentToken;
            int iStartPos = token.startPos();
            int iEndPos = token.endPos();
            if (node instanceof Element) {
                Element element = (Element) node;
                if (token.isEOF()) {
                    if (element.endSourceRange().isTracked()) {
                        return;
                    } else {
                        iStartPos = this.reader.pos();
                    }
                } else if (!z ? !(element.tag().isEmpty() || element.tag().isSelfClosing() || (token.isEndTag() && element.normalName().equals(token.asEndTag().normalName))) : !(token.isStartTag() && element.normalName().equals(token.asStartTag().normalName))) {
                }
                iEndPos = iStartPos;
            }
            node.attributes().userData(z ? SharedConstants.RangeKey : SharedConstants.EndRangeKey, new Range(new Range.Position(iStartPos, this.reader.lineNumber(iStartPos), this.reader.columnNumber(iStartPos)), new Range.Position(iEndPos, this.reader.lineNumber(iEndPos), this.reader.columnNumber(iEndPos))));
        }
    }

    public Element currentElement() {
        int size = this.stack.size();
        return size > 0 ? this.stack.get(size - 1) : this.doc;
    }

    public boolean currentElementIs(String str) {
        Element elementCurrentElement;
        return this.stack.size() != 0 && (elementCurrentElement = currentElement()) != null && elementCurrentElement.normalName().equals(str) && elementCurrentElement.tag().namespace().equals(Parser.NamespaceHtml);
    }

    public String defaultNamespace() {
        return Parser.NamespaceHtml;
    }

    public abstract ParseSettings defaultSettings();

    public void error(String str) {
        error(str, null);
    }

    public void initialiseParse(Reader reader, String str, Parser parser) {
        Validate.notNullParam(reader, "input");
        Validate.notNullParam(str, "baseUri");
        Validate.notNull(parser);
        Document document = new Document(parser.defaultNamespace(), str);
        this.doc = document;
        document.parser(parser);
        this.parser = parser;
        this.settings = parser.settings();
        this.reader = new CharacterReader(reader);
        this.trackSourceRange = parser.isTrackPosition();
        this.reader.trackNewlines(parser.isTrackErrors() || this.trackSourceRange);
        this.tokeniser = new Tokeniser(this);
        this.stack = new ArrayList<>(32);
        this.seenTags = new HashMap();
        Token.StartTag startTag = new Token.StartTag(this);
        this.start = startTag;
        this.currentToken = startTag;
        this.baseUri = str;
    }

    public boolean isContentForTagData(String str) {
        return false;
    }

    public abstract TreeBuilder newInstance();

    public void onNodeClosed(Node node) {
        trackNodePosition(node, false);
    }

    public void onNodeInserted(Node node) {
        trackNodePosition(node, true);
    }

    public Document parse(Reader reader, String str, Parser parser) {
        initialiseParse(reader, str, parser);
        runParser();
        this.reader.close();
        this.reader = null;
        this.tokeniser = null;
        this.stack = null;
        this.seenTags = null;
        return this.doc;
    }

    public abstract List<Node> parseFragment(String str, Element element, String str2, Parser parser);

    public final Element pop() {
        Element elementRemove = this.stack.remove(this.stack.size() - 1);
        onNodeClosed(elementRemove);
        return elementRemove;
    }

    public abstract boolean process(Token token);

    public boolean processEndTag(String str) {
        Token token = this.currentToken;
        Token.EndTag endTag = this.end;
        return token == endTag ? process(new Token.EndTag(this).name(str)) : process(endTag.reset().name(str));
    }

    public boolean processStartTag(String str) {
        Token.StartTag startTag = this.start;
        return this.currentToken == startTag ? process(new Token.StartTag(this).name(str)) : process(startTag.reset().name(str));
    }

    public final void push(Element element) {
        this.stack.add(element);
        onNodeInserted(element);
    }

    public void runParser() {
        Tokeniser tokeniser = this.tokeniser;
        Token.TokenType tokenType = Token.TokenType.EOF;
        while (true) {
            Token token = tokeniser.read();
            this.currentToken = token;
            process(token);
            if (token.type == tokenType) {
                break;
            } else {
                token.reset();
            }
        }
        while (!this.stack.isEmpty()) {
            pop();
        }
    }

    public Tag tagFor(String str, String str2, ParseSettings parseSettings) {
        Tag tag = this.seenTags.get(str);
        if (tag != null && tag.namespace().equals(str2)) {
            return tag;
        }
        Tag tagValueOf = Tag.valueOf(str, str2, parseSettings);
        this.seenTags.put(str, tagValueOf);
        return tagValueOf;
    }

    public void error(String str, Object... objArr) {
        ParseErrorList errors = this.parser.getErrors();
        if (errors.canAddError()) {
            errors.add(new ParseError(this.reader, str, objArr));
        }
    }

    public boolean currentElementIs(String str, String str2) {
        Element elementCurrentElement;
        return this.stack.size() != 0 && (elementCurrentElement = currentElement()) != null && elementCurrentElement.normalName().equals(str) && elementCurrentElement.tag().namespace().equals(str2);
    }

    public boolean processStartTag(String str, Attributes attributes) {
        Token.StartTag startTag = this.start;
        if (this.currentToken == startTag) {
            return process(new Token.StartTag(this).nameAttr(str, attributes));
        }
        startTag.reset();
        startTag.nameAttr(str, attributes);
        return process(startTag);
    }

    public Tag tagFor(String str, ParseSettings parseSettings) {
        return tagFor(str, defaultNamespace(), parseSettings);
    }
}

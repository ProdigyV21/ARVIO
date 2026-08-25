package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Token;

/* JADX INFO: loaded from: classes5.dex */
public class XmlTreeBuilder extends TreeBuilder {
    private static final int maxQueueDepth = 256;

    /* JADX INFO: renamed from: org.jsoup.parser.XmlTreeBuilder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$Token$TokenType;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            $SwitchMap$org$jsoup$parser$Token$TokenType = iArr;
            try {
                iArr[Token.TokenType.StartTag.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EndTag.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Comment.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Character.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.Doctype.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jsoup$parser$Token$TokenType[Token.TokenType.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // org.jsoup.parser.TreeBuilder
    public String defaultNamespace() {
        return Parser.NamespaceXml;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.preserveCase;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public void initialiseParse(Reader reader, String str, Parser parser) {
        super.initialiseParse(reader, str, parser);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml).prettyPrint(false);
    }

    public void insertCharacterFor(Token.Character character) {
        String data = character.getData();
        insertLeafNode(character.isCData() ? new CDataNode(data) : new TextNode(data));
    }

    public void insertCommentFor(Token.Comment comment) {
        XmlDeclaration xmlDeclarationAsXmlDeclaration;
        Comment comment2 = new Comment(comment.getData());
        if (comment.bogus && comment2.isXmlDeclaration() && (xmlDeclarationAsXmlDeclaration = comment2.asXmlDeclaration()) != null) {
            comment2 = xmlDeclarationAsXmlDeclaration;
        }
        insertLeafNode(comment2);
    }

    public void insertDoctypeFor(Token.Doctype doctype) {
        DocumentType documentType = new DocumentType(this.settings.normalizeTag(doctype.getName()), doctype.getPublicIdentifier(), doctype.getSystemIdentifier());
        documentType.setPubSysKey(doctype.getPubSysKey());
        insertLeafNode(documentType);
    }

    public void insertElementFor(Token.StartTag startTag) {
        Tag tagTagFor = tagFor(startTag.name(), this.settings);
        Attributes attributes = startTag.attributes;
        if (attributes != null) {
            attributes.deduplicate(this.settings);
        }
        Element element = new Element(tagTagFor, null, this.settings.normalizeAttributes(startTag.attributes));
        currentElement().appendChild(element);
        push(element);
        if (startTag.isSelfClosing()) {
            tagTagFor.setSelfClosing();
            pop();
        }
    }

    public void insertLeafNode(LeafNode leafNode) {
        currentElement().appendChild(leafNode);
        onNodeInserted(leafNode);
    }

    @Deprecated
    public void insertNode(Node node) {
        currentElement().appendChild(node);
        onNodeInserted(node);
    }

    public Document parse(Reader reader, String str) {
        return parse(reader, str, new Parser(this));
    }

    public List<Node> parseFragment(String str, String str2, Parser parser) {
        initialiseParse(new StringReader(str), str2, parser);
        runParser();
        return this.doc.childNodes();
    }

    public void popStackToClose(Token.EndTag endTag) {
        Element element;
        String strNormalizeTag = this.settings.normalizeTag(endTag.tagName);
        int size = this.stack.size();
        int i10 = size + (-1) >= 256 ? size - 257 : 0;
        int size2 = this.stack.size() - 1;
        while (true) {
            if (size2 < i10) {
                element = null;
                break;
            }
            element = this.stack.get(size2);
            if (element.nodeName().equals(strNormalizeTag)) {
                break;
            } else {
                size2--;
            }
        }
        if (element == null) {
            return;
        }
        for (int size3 = this.stack.size() - 1; size3 >= 0 && pop() != element; size3--) {
        }
    }

    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        this.currentToken = token;
        switch (AnonymousClass1.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
            case 1:
                insertElementFor(token.asStartTag());
                break;
            case 2:
                popStackToClose(token.asEndTag());
                break;
            case 3:
                insertCommentFor(token.asComment());
                break;
            case 4:
                insertCharacterFor(token.asCharacter());
                break;
            case 5:
                insertDoctypeFor(token.asDoctype());
                break;
            case 6:
                break;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                break;
        }
        return true;
    }

    @Override // org.jsoup.parser.TreeBuilder
    public XmlTreeBuilder newInstance() {
        return new XmlTreeBuilder();
    }

    public Document parse(String str, String str2) {
        return parse(new StringReader(str), str2, new Parser(this));
    }

    @Deprecated
    public void insertNode(Node node, Token token) {
        currentElement().appendChild(node);
        onNodeInserted(node);
    }

    @Override // org.jsoup.parser.TreeBuilder
    public List<Node> parseFragment(String str, Element element, String str2, Parser parser) {
        return parseFragment(str, str2, parser);
    }
}

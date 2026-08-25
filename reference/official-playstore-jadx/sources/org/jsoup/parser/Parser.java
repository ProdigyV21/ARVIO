package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes5.dex */
public class Parser {
    public static final String NamespaceHtml = "http://www.w3.org/1999/xhtml";
    public static final String NamespaceMathml = "http://www.w3.org/1998/Math/MathML";
    public static final String NamespaceSvg = "http://www.w3.org/2000/svg";
    public static final String NamespaceXml = "http://www.w3.org/XML/1998/namespace";
    private ParseErrorList errors;
    private ParseSettings settings;
    private boolean trackPosition;
    private TreeBuilder treeBuilder;

    public Parser(TreeBuilder treeBuilder) {
        this.trackPosition = false;
        this.treeBuilder = treeBuilder;
        this.settings = treeBuilder.defaultSettings();
        this.errors = ParseErrorList.noTracking();
    }

    public static Parser htmlParser() {
        return new Parser(new HtmlTreeBuilder());
    }

    public static Document parse(String str, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.parse(new StringReader(str), str2, new Parser(htmlTreeBuilder));
    }

    public static Document parseBodyFragment(String str, String str2) {
        Document documentCreateShell = Document.createShell(str2);
        Element elementBody = documentCreateShell.body();
        List<Node> fragment = parseFragment(str, elementBody, str2);
        Node[] nodeArr = (Node[]) fragment.toArray(new Node[0]);
        for (int length = nodeArr.length - 1; length > 0; length--) {
            nodeArr[length].remove();
        }
        for (Node node : nodeArr) {
            elementBody.appendChild(node);
        }
        return documentCreateShell;
    }

    public static List<Node> parseFragment(String str, Element element, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.parseFragment(str, element, str2, new Parser(htmlTreeBuilder));
    }

    public static List<Node> parseXmlFragment(String str, String str2) {
        XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
        return xmlTreeBuilder.parseFragment(str, str2, new Parser(xmlTreeBuilder));
    }

    public static String unescapeEntities(String str, boolean z) {
        Parser parserHtmlParser = htmlParser();
        parserHtmlParser.treeBuilder.initialiseParse(new StringReader(str), "", parserHtmlParser);
        return new Tokeniser(parserHtmlParser.treeBuilder).unescapeEntities(z);
    }

    public static Parser xmlParser() {
        return new Parser(new XmlTreeBuilder());
    }

    public String defaultNamespace() {
        return getTreeBuilder().defaultNamespace();
    }

    public ParseErrorList getErrors() {
        return this.errors;
    }

    public TreeBuilder getTreeBuilder() {
        return this.treeBuilder;
    }

    public boolean isContentForTagData(String str) {
        return getTreeBuilder().isContentForTagData(str);
    }

    public boolean isTrackErrors() {
        return this.errors.getMaxSize() > 0;
    }

    public boolean isTrackPosition() {
        return this.trackPosition;
    }

    public Parser newInstance() {
        return new Parser(this);
    }

    public List<Node> parseFragmentInput(String str, Element element, String str2) {
        return this.treeBuilder.parseFragment(str, element, str2, this);
    }

    public Document parseInput(String str, String str2) {
        return this.treeBuilder.parse(new StringReader(str), str2, this);
    }

    public Parser setTrackErrors(int i10) {
        this.errors = i10 > 0 ? ParseErrorList.tracking(i10) : ParseErrorList.noTracking();
        return this;
    }

    public Parser setTrackPosition(boolean z) {
        this.trackPosition = z;
        return this;
    }

    public Parser setTreeBuilder(TreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        treeBuilder.parser = this;
        return this;
    }

    public Parser settings(ParseSettings parseSettings) {
        this.settings = parseSettings;
        return this;
    }

    public Document parseInput(Reader reader, String str) {
        return this.treeBuilder.parse(reader, str, this);
    }

    public ParseSettings settings() {
        return this.settings;
    }

    public static List<Node> parseFragment(String str, Element element, String str2, ParseErrorList parseErrorList) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser(htmlTreeBuilder);
        parser.errors = parseErrorList;
        return htmlTreeBuilder.parseFragment(str, element, str2, parser);
    }

    private Parser(Parser parser) {
        this.trackPosition = false;
        this.treeBuilder = parser.treeBuilder.newInstance();
        this.errors = new ParseErrorList(parser.errors);
        this.settings = new ParseSettings(parser.settings);
        this.trackPosition = parser.trackPosition;
    }
}

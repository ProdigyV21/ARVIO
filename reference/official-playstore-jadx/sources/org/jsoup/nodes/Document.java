package org.jsoup.nodes;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Evaluator;

/* JADX INFO: loaded from: classes5.dex */
public class Document extends Element {
    private static final Evaluator titleEval = new Evaluator.Tag(LinkHeader.Parameters.Title);
    private Connection connection;
    private final String location;
    private OutputSettings outputSettings;
    private Parser parser;
    private QuirksMode quirksMode;
    private boolean updateMetaCharset;

    public static class OutputSettings implements Cloneable {
        private Charset charset;
        Entities.CoreCharset coreCharset;
        private Entities.EscapeMode escapeMode = Entities.EscapeMode.base;
        private final ThreadLocal<CharsetEncoder> encoderThreadLocal = new ThreadLocal<>();
        private boolean prettyPrint = true;
        private boolean outline = false;
        private int indentAmount = 1;
        private int maxPaddingWidth = 30;
        private Syntax syntax = Syntax.html;

        public enum Syntax {
            html,
            xml
        }

        public OutputSettings() {
            charset(DataUtil.UTF_8);
        }

        public Charset charset() {
            return this.charset;
        }

        public CharsetEncoder encoder() {
            CharsetEncoder charsetEncoder = this.encoderThreadLocal.get();
            return charsetEncoder != null ? charsetEncoder : prepareEncoder();
        }

        public Entities.EscapeMode escapeMode() {
            return this.escapeMode;
        }

        public int indentAmount() {
            return this.indentAmount;
        }

        public int maxPaddingWidth() {
            return this.maxPaddingWidth;
        }

        public boolean outline() {
            return this.outline;
        }

        public CharsetEncoder prepareEncoder() {
            CharsetEncoder charsetEncoderNewEncoder = this.charset.newEncoder();
            this.encoderThreadLocal.set(charsetEncoderNewEncoder);
            return charsetEncoderNewEncoder;
        }

        public boolean prettyPrint() {
            return this.prettyPrint;
        }

        public Syntax syntax() {
            return this.syntax;
        }

        public OutputSettings charset(Charset charset) {
            this.charset = charset;
            this.coreCharset = Entities.CoreCharset.byName(charset.name());
            return this;
        }

        public OutputSettings clone() {
            try {
                OutputSettings outputSettings = (OutputSettings) super.clone();
                outputSettings.charset(this.charset.name());
                outputSettings.escapeMode = Entities.EscapeMode.valueOf(this.escapeMode.name());
                return outputSettings;
            } catch (CloneNotSupportedException e5) {
                throw new RuntimeException(e5);
            }
        }

        public OutputSettings escapeMode(Entities.EscapeMode escapeMode) {
            this.escapeMode = escapeMode;
            return this;
        }

        public OutputSettings indentAmount(int i10) {
            Validate.isTrue(i10 >= 0);
            this.indentAmount = i10;
            return this;
        }

        public OutputSettings maxPaddingWidth(int i10) {
            Validate.isTrue(i10 >= -1);
            this.maxPaddingWidth = i10;
            return this;
        }

        public OutputSettings outline(boolean z) {
            this.outline = z;
            return this;
        }

        public OutputSettings prettyPrint(boolean z) {
            this.prettyPrint = z;
            return this;
        }

        public OutputSettings syntax(Syntax syntax) {
            this.syntax = syntax;
            if (syntax == Syntax.xml) {
                escapeMode(Entities.EscapeMode.xhtml);
            }
            return this;
        }

        public OutputSettings charset(String str) {
            charset(Charset.forName(str));
            return this;
        }
    }

    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public Document(String str, String str2) {
        super(Tag.valueOf("#root", str, ParseSettings.htmlDefault), str2);
        this.outputSettings = new OutputSettings();
        this.quirksMode = QuirksMode.noQuirks;
        this.updateMetaCharset = false;
        this.location = str2;
        this.parser = Parser.htmlParser();
    }

    public static Document createShell(String str) {
        Validate.notNull(str);
        Document document = new Document(str);
        document.parser = document.parser();
        Element elementAppendElement = document.appendElement("html");
        elementAppendElement.appendElement(TtmlNode.TAG_HEAD);
        elementAppendElement.appendElement(TtmlNode.TAG_BODY);
        return document;
    }

    private void ensureMetaCharsetElement() {
        if (this.updateMetaCharset) {
            OutputSettings.Syntax syntax = outputSettings().syntax();
            if (syntax == OutputSettings.Syntax.html) {
                Element elementSelectFirst = selectFirst("meta[charset]");
                if (elementSelectFirst != null) {
                    elementSelectFirst.attr(HttpAuthHeader.Parameters.Charset, charset().displayName());
                } else {
                    head().appendElement("meta").attr(HttpAuthHeader.Parameters.Charset, charset().displayName());
                }
                select("meta[name=charset]").remove();
                return;
            }
            if (syntax == OutputSettings.Syntax.xml) {
                Node node = ensureChildNodes().get(0);
                if (!(node instanceof XmlDeclaration)) {
                    XmlDeclaration xmlDeclaration = new XmlDeclaration("xml", false);
                    xmlDeclaration.attr("version", "1.0");
                    xmlDeclaration.attr("encoding", charset().displayName());
                    prependChild(xmlDeclaration);
                    return;
                }
                XmlDeclaration xmlDeclaration2 = (XmlDeclaration) node;
                if (xmlDeclaration2.name().equals("xml")) {
                    xmlDeclaration2.attr("encoding", charset().displayName());
                    if (xmlDeclaration2.hasAttr("version")) {
                        xmlDeclaration2.attr("version", "1.0");
                        return;
                    }
                    return;
                }
                XmlDeclaration xmlDeclaration3 = new XmlDeclaration("xml", false);
                xmlDeclaration3.attr("version", "1.0");
                xmlDeclaration3.attr("encoding", charset().displayName());
                prependChild(xmlDeclaration3);
            }
        }
    }

    private Element htmlEl() {
        for (Element elementFirstElementChild = firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs("html")) {
                return elementFirstElementChild;
            }
        }
        return appendElement("html");
    }

    public Element body() {
        Element elementHtmlEl = htmlEl();
        for (Element elementFirstElementChild = elementHtmlEl.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs(TtmlNode.TAG_BODY) || elementFirstElementChild.nameIs("frameset")) {
                return elementFirstElementChild;
            }
        }
        return elementHtmlEl.appendElement(TtmlNode.TAG_BODY);
    }

    public void charset(Charset charset) {
        updateMetaCharsetElement(true);
        this.outputSettings.charset(charset);
        ensureMetaCharsetElement();
    }

    public Connection connection() {
        Connection connection = this.connection;
        return connection == null ? Jsoup.newSession() : connection;
    }

    public Element createElement(String str) {
        return new Element(Tag.valueOf(str, this.parser.defaultNamespace(), ParseSettings.preserveCase), baseUri());
    }

    public DocumentType documentType() {
        for (Node node : this.childNodes) {
            if (node instanceof DocumentType) {
                return (DocumentType) node;
            }
            if (!(node instanceof LeafNode)) {
                return null;
            }
        }
        return null;
    }

    public FormElement expectForm(String str) {
        for (Element element : select(str)) {
            if (element instanceof FormElement) {
                return (FormElement) element;
            }
        }
        Validate.fail("No form elements matched the query '%s' in the document.", str);
        return null;
    }

    public List<FormElement> forms() {
        return select("form").forms();
    }

    public Element head() {
        Element elementHtmlEl = htmlEl();
        for (Element elementFirstElementChild = elementHtmlEl.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
            if (elementFirstElementChild.nameIs(TtmlNode.TAG_HEAD)) {
                return elementFirstElementChild;
            }
        }
        return elementHtmlEl.prependElement(TtmlNode.TAG_HEAD);
    }

    public String location() {
        return this.location;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public String nodeName() {
        return "#document";
    }

    @Override // org.jsoup.nodes.Node
    public String outerHtml() {
        return super.html();
    }

    public OutputSettings outputSettings() {
        return this.outputSettings;
    }

    public Parser parser() {
        return this.parser;
    }

    public QuirksMode quirksMode() {
        return this.quirksMode;
    }

    @Override // org.jsoup.nodes.Element
    public Element text(String str) {
        body().text(str);
        return this;
    }

    public String title() {
        Element elementSelectFirst = head().selectFirst(titleEval);
        return elementSelectFirst != null ? StringUtil.normaliseWhitespace(elementSelectFirst.text()).trim() : "";
    }

    public void updateMetaCharsetElement(boolean z) {
        this.updateMetaCharset = z;
    }

    public Document outputSettings(OutputSettings outputSettings) {
        Validate.notNull(outputSettings);
        this.outputSettings = outputSettings;
        return this;
    }

    public Document parser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public Document quirksMode(QuirksMode quirksMode) {
        this.quirksMode = quirksMode;
        return this;
    }

    public boolean updateMetaCharsetElement() {
        return this.updateMetaCharset;
    }

    public Document connection(Connection connection) {
        Validate.notNull(connection);
        this.connection = connection;
        return this;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public Document shallowClone() {
        Document document = new Document(tag().namespace(), baseUri());
        Attributes attributes = this.attributes;
        if (attributes != null) {
            document.attributes = attributes.clone();
        }
        document.outputSettings = this.outputSettings.clone();
        return document;
    }

    public void title(String str) {
        Validate.notNull(str);
        Element elementSelectFirst = head().selectFirst(titleEval);
        if (elementSelectFirst == null) {
            elementSelectFirst = head().appendElement(LinkHeader.Parameters.Title);
        }
        elementSelectFirst.text(str);
    }

    public Charset charset() {
        return this.outputSettings.charset();
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Document mo7038clone() {
        Document document = (Document) super.mo7038clone();
        document.outputSettings = this.outputSettings.clone();
        return document;
    }

    public Document(String str) {
        this(Parser.NamespaceHtml, str);
    }
}

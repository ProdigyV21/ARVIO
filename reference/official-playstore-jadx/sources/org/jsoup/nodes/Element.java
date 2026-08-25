package org.jsoup.nodes;

import androidx.compose.material3.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.sentry.android.core.j1;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

/* JADX INFO: loaded from: classes5.dex */
public class Element extends Node {
    Attributes attributes;
    List<Node> childNodes;
    private WeakReference<List<Element>> shadowChildrenRef;
    private Tag tag;
    private static final List<Element> EmptyChildren = Collections.EMPTY_LIST;
    private static final Pattern ClassSplit = Pattern.compile("\\s+");
    private static final String BaseUriKey = Attributes.internalKey("baseUri");

    public static final class NodeList extends ChangeNotifyingArrayList<Node> {
        private final Element owner;

        public NodeList(Element element, int i10) {
            super(i10);
            this.owner = element;
        }

        @Override // org.jsoup.helper.ChangeNotifyingArrayList
        public void onContentsChanged() {
            this.owner.nodelistChanged();
        }
    }

    public static class TextAccumulator implements NodeVisitor {
        private final StringBuilder accum;

        public TextAccumulator(StringBuilder sb2) {
            this.accum = sb2;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i10) {
            if (node instanceof TextNode) {
                Element.appendNormalisedText(this.accum, (TextNode) node);
            } else if (node instanceof Element) {
                Element element = (Element) node;
                if (this.accum.length() > 0) {
                    if ((element.isBlock() || element.nameIs("br")) && !TextNode.lastCharIsWhitespace(this.accum)) {
                        this.accum.append(' ');
                    }
                }
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i10) {
            if (node instanceof Element) {
                Element element = (Element) node;
                Node nodeNextSibling = node.nextSibling();
                if (element.isBlock()) {
                    if (((nodeNextSibling instanceof TextNode) || ((nodeNextSibling instanceof Element) && !((Element) nodeNextSibling).tag.formatAsBlock())) && !TextNode.lastCharIsWhitespace(this.accum)) {
                        this.accum.append(' ');
                    }
                }
            }
        }
    }

    public Element(String str, String str2) {
        this(Tag.valueOf(str, str2, ParseSettings.preserveCase), (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendNormalisedText(StringBuilder sb2, TextNode textNode) {
        String wholeText = textNode.getWholeText();
        if (preserveWhitespace(textNode.parentNode) || (textNode instanceof CDataNode)) {
            sb2.append(wholeText);
        } else {
            StringUtil.appendNormalisedWhitespace(sb2, wholeText, TextNode.lastCharIsWhitespace(sb2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendWholeText(Node node, StringBuilder sb2) {
        if (node instanceof TextNode) {
            sb2.append(((TextNode) node).getWholeText());
        } else if (node.nameIs("br")) {
            sb2.append("\n");
        }
    }

    private String cssSelectorComponent() {
        String strReplace = TokenQueue.escapeCssIdentifier(tagName()).replace("\\:", "|");
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        sbBorrowBuilder.append(strReplace);
        StringUtil.StringJoiner stringJoiner = new StringUtil.StringJoiner(".");
        Iterator<String> it = classNames().iterator();
        while (it.hasNext()) {
            stringJoiner.add(TokenQueue.escapeCssIdentifier(it.next()));
        }
        String strComplete = stringJoiner.complete();
        if (strComplete.length() > 0) {
            sbBorrowBuilder.append('.');
            sbBorrowBuilder.append(strComplete);
        }
        if (parent() == null || (parent() instanceof Document)) {
            return StringUtil.releaseBuilder(sbBorrowBuilder);
        }
        sbBorrowBuilder.insert(0, " > ");
        if (parent().select(sbBorrowBuilder.toString()).size() > 1) {
            sbBorrowBuilder.append(String.format(":nth-child(%d)", Integer.valueOf(elementSiblingIndex() + 1)));
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    private <T> List<T> filterNodes(Class<T> cls) {
        Stream stream = Collection.EL.stream(this.childNodes);
        Objects.requireNonNull(cls);
        return (List) stream.filter(new androidx.credentials.provider.utils.a(cls, 4)).map(new androidx.credentials.provider.a(cls, 11)).collect(Collectors.collectingAndThen(Collectors.toList(), new a()));
    }

    private static <E extends Element> int indexInList(Element element, List<E> list) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (list.get(i10) == element) {
                return i10;
            }
        }
        return 0;
    }

    private boolean isFormatAsBlock(Document.OutputSettings outputSettings) {
        if (this.tag.isBlock()) {
            return true;
        }
        return (parent() != null && parent().tag().formatAsBlock()) || outputSettings.outline();
    }

    private boolean isInlineable(Document.OutputSettings outputSettings) {
        if (this.tag.isInline()) {
            return ((parent() != null && !parent().isBlock()) || isEffectivelyFirst() || outputSettings.outline() || nameIs("br")) ? false : true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$data$2(StringBuilder sb2, Node node, int i10) {
        if (node instanceof DataNode) {
            sb2.append(((DataNode) node).getWholeData());
        } else if (node instanceof Comment) {
            sb2.append(((Comment) node).getData());
        } else if (node instanceof CDataNode) {
            sb2.append(((CDataNode) node).getWholeText());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ NodeFilter.FilterResult lambda$hasText$1(AtomicBoolean atomicBoolean, Node node, int i10) {
        if (!(node instanceof TextNode) || ((TextNode) node).isBlank()) {
            return NodeFilter.FilterResult.CONTINUE;
        }
        atomicBoolean.set(true);
        return NodeFilter.FilterResult.STOP;
    }

    public static boolean preserveWhitespace(Node node) {
        if (node instanceof Element) {
            Element elementParent = (Element) node;
            int i10 = 0;
            while (!elementParent.tag.preserveWhitespace()) {
                elementParent = elementParent.parent();
                i10++;
                if (i10 >= 6 || elementParent == null) {
                }
            }
            return true;
        }
        return false;
    }

    private static String searchUpForAttribute(Element element, String str) {
        while (element != null) {
            Attributes attributes = element.attributes;
            if (attributes != null && attributes.hasKey(str)) {
                return element.attributes.get(str);
            }
            element = element.parent();
        }
        return "";
    }

    public Element addClass(String str) {
        Validate.notNull(str);
        Set<String> setClassNames = classNames();
        setClassNames.add(str);
        classNames(setClassNames);
        return this;
    }

    public Element append(String str) {
        Validate.notNull(str);
        addChildren((Node[]) NodeUtils.parser(this).parseFragmentInput(str, this, baseUri()).toArray(new Node[0]));
        return this;
    }

    public Element appendChild(Node node) {
        Validate.notNull(node);
        reparentChild(node);
        ensureChildNodes();
        this.childNodes.add(node);
        node.setSiblingIndex(this.childNodes.size() - 1);
        return this;
    }

    public Element appendChildren(java.util.Collection<? extends Node> collection) {
        insertChildren(-1, collection);
        return this;
    }

    public Element appendElement(String str) {
        return appendElement(str, this.tag.namespace());
    }

    public Element appendText(String str) {
        Validate.notNull(str);
        appendChild(new TextNode(str));
        return this;
    }

    public Element appendTo(Element element) {
        Validate.notNull(element);
        element.appendChild(this);
        return this;
    }

    public Attribute attribute(String str) {
        if (hasAttributes()) {
            return attributes().attribute(str);
        }
        return null;
    }

    @Override // org.jsoup.nodes.Node
    public Attributes attributes() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
        }
        return this.attributes;
    }

    @Override // org.jsoup.nodes.Node
    public String baseUri() {
        return searchUpForAttribute(this, BaseUriKey);
    }

    public Element child(int i10) {
        return childElementsList().get(i10);
    }

    public List<Element> childElementsList() {
        List<Element> list;
        if (childNodeSize() == 0) {
            return EmptyChildren;
        }
        WeakReference<List<Element>> weakReference = this.shadowChildrenRef;
        if (weakReference != null && (list = weakReference.get()) != null) {
            return list;
        }
        int size = this.childNodes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i10 = 0; i10 < size; i10++) {
            Node node = this.childNodes.get(i10);
            if (node instanceof Element) {
                arrayList.add((Element) node);
            }
        }
        this.shadowChildrenRef = new WeakReference<>(arrayList);
        return arrayList;
    }

    @Override // org.jsoup.nodes.Node
    public int childNodeSize() {
        return this.childNodes.size();
    }

    public Elements children() {
        return new Elements(childElementsList());
    }

    public int childrenSize() {
        return childElementsList().size();
    }

    public String className() {
        return attr("class").trim();
    }

    public Set<String> classNames() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(ClassSplit.split(className())));
        linkedHashSet.remove("");
        return linkedHashSet;
    }

    public Element closest(String str) {
        return closest(QueryParser.parse(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0036, code lost:
    
        if (r2.get(0) == r5) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String cssSelector() {
        /*
            r5 = this;
            java.lang.String r0 = r5.id()
            int r0 = r0.length()
            r1 = 0
            if (r0 <= 0) goto L39
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "#"
            r0.<init>(r2)
            java.lang.String r2 = r5.id()
            java.lang.String r2 = org.jsoup.parser.TokenQueue.escapeCssIdentifier(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            org.jsoup.nodes.Document r2 = r5.ownerDocument()
            if (r2 == 0) goto L38
            org.jsoup.select.Elements r2 = r2.select(r0)
            int r3 = r2.size()
            r4 = 1
            if (r3 != r4) goto L39
            java.lang.Object r2 = r2.get(r1)
            if (r2 != r5) goto L39
        L38:
            return r0
        L39:
            java.lang.StringBuilder r0 = org.jsoup.internal.StringUtil.borrowBuilder()
            r2 = r5
        L3e:
            if (r2 == 0) goto L50
            boolean r3 = r2 instanceof org.jsoup.nodes.Document
            if (r3 != 0) goto L50
            java.lang.String r3 = r2.cssSelectorComponent()
            r0.insert(r1, r3)
            org.jsoup.nodes.Element r2 = r2.parent()
            goto L3e
        L50:
            java.lang.String r0 = org.jsoup.internal.StringUtil.releaseBuilder(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Element.cssSelector():java.lang.String");
    }

    public String data() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        traverse((NodeVisitor) new io.sentry.cache.a(sbBorrowBuilder, 10));
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public List<DataNode> dataNodes() {
        return filterNodes(DataNode.class);
    }

    public Map<String, String> dataset() {
        return attributes().dataset();
    }

    @Override // org.jsoup.nodes.Node
    public void doSetBaseUri(String str) {
        attributes().put(BaseUriKey, str);
    }

    public boolean elementIs(String str, String str2) {
        return this.tag.normalName().equals(str) && this.tag.namespace().equals(str2);
    }

    public int elementSiblingIndex() {
        if (parent() == null) {
            return 0;
        }
        return indexInList(this, parent().childElementsList());
    }

    public Range endSourceRange() {
        return Range.of(this, false);
    }

    @Override // org.jsoup.nodes.Node
    public List<Node> ensureChildNodes() {
        if (this.childNodes == Node.EmptyNodes) {
            this.childNodes = new NodeList(this, 4);
        }
        return this.childNodes;
    }

    public Element expectFirst(String str) {
        return (Element) Validate.ensureNotNull(Selector.selectFirst(str, this), parent() != null ? "No elements matched the query '%s' on element '%s'." : "No elements matched the query '%s' in the document.", str, tagName());
    }

    public Element firstElementChild() {
        for (Node nodeFirstChild = firstChild(); nodeFirstChild != null; nodeFirstChild = nodeFirstChild.nextSibling()) {
            if (nodeFirstChild instanceof Element) {
                return (Element) nodeFirstChild;
            }
        }
        return null;
    }

    public Element firstElementSibling() {
        return parent() != null ? parent().firstElementChild() : this;
    }

    @Deprecated
    public Element forEach(Consumer<? super Element> consumer) {
        stream().forEach(consumer);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ Node forEachNode(Consumer consumer) {
        return forEachNode((Consumer<? super Node>) consumer);
    }

    public Elements getAllElements() {
        return Collector.collect(new Evaluator.AllElements(), this);
    }

    public Element getElementById(String str) {
        Validate.notEmpty(str);
        Elements elementsCollect = Collector.collect(new Evaluator.Id(str), this);
        if (elementsCollect.size() > 0) {
            return elementsCollect.get(0);
        }
        return null;
    }

    public Elements getElementsByAttribute(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Attribute(str.trim()), this);
    }

    public Elements getElementsByAttributeStarting(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.AttributeStarting(str.trim()), this);
    }

    public Elements getElementsByAttributeValue(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValue(str, str2), this);
    }

    public Elements getElementsByAttributeValueContaining(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueContaining(str, str2), this);
    }

    public Elements getElementsByAttributeValueEnding(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueEnding(str, str2), this);
    }

    public Elements getElementsByAttributeValueMatching(String str, Pattern pattern) {
        return Collector.collect(new Evaluator.AttributeWithValueMatching(str, pattern), this);
    }

    public Elements getElementsByAttributeValueNot(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueNot(str, str2), this);
    }

    public Elements getElementsByAttributeValueStarting(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueStarting(str, str2), this);
    }

    public Elements getElementsByClass(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Class(str), this);
    }

    public Elements getElementsByIndexEquals(int i10) {
        return Collector.collect(new Evaluator.IndexEquals(i10), this);
    }

    public Elements getElementsByIndexGreaterThan(int i10) {
        return Collector.collect(new Evaluator.IndexGreaterThan(i10), this);
    }

    public Elements getElementsByIndexLessThan(int i10) {
        return Collector.collect(new Evaluator.IndexLessThan(i10), this);
    }

    public Elements getElementsByTag(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Tag(Normalizer.normalize(str)), this);
    }

    public Elements getElementsContainingOwnText(String str) {
        return Collector.collect(new Evaluator.ContainsOwnText(str), this);
    }

    public Elements getElementsContainingText(String str) {
        return Collector.collect(new Evaluator.ContainsText(str), this);
    }

    public Elements getElementsMatchingOwnText(Pattern pattern) {
        return Collector.collect(new Evaluator.MatchesOwn(pattern), this);
    }

    public Elements getElementsMatchingText(Pattern pattern) {
        return Collector.collect(new Evaluator.Matches(pattern), this);
    }

    @Override // org.jsoup.nodes.Node
    public boolean hasAttributes() {
        return this.attributes != null;
    }

    public boolean hasChildNodes() {
        return this.childNodes != Node.EmptyNodes;
    }

    public boolean hasClass(String str) {
        String str2;
        Attributes attributes = this.attributes;
        if (attributes == null) {
            return false;
        }
        String ignoreCase = attributes.getIgnoreCase("class");
        int length = ignoreCase.length();
        int length2 = str.length();
        if (length != 0 && length >= length2) {
            if (length == length2) {
                return str.equalsIgnoreCase(ignoreCase);
            }
            int i10 = 0;
            boolean z = false;
            int i11 = 0;
            while (i10 < length) {
                if (!Character.isWhitespace(ignoreCase.charAt(i10))) {
                    str2 = str;
                    if (!z) {
                        i11 = i10;
                        z = true;
                    }
                } else if (z) {
                    if (i10 - i11 == length2) {
                        str2 = str;
                        if (ignoreCase.regionMatches(true, i11, str2, 0, length2)) {
                            return true;
                        }
                    } else {
                        str2 = str;
                    }
                    z = false;
                } else {
                    str2 = str;
                }
                i10++;
                str = str2;
            }
            String str3 = str;
            if (z && length - i11 == length2) {
                return ignoreCase.regionMatches(true, i11, str3, 0, length2);
            }
        }
        return false;
    }

    public boolean hasText() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        filter((NodeFilter) new j1(atomicBoolean));
        return atomicBoolean.get();
    }

    public String html() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        html(sbBorrowBuilder);
        String strReleaseBuilder = StringUtil.releaseBuilder(sbBorrowBuilder);
        return NodeUtils.outputSettings(this).prettyPrint() ? strReleaseBuilder.trim() : strReleaseBuilder;
    }

    public String id() {
        Attributes attributes = this.attributes;
        return attributes != null ? attributes.getIgnoreCase(TtmlNode.ATTR_ID) : "";
    }

    public Element insertChildren(int i10, java.util.Collection<? extends Node> collection) {
        Validate.notNull(collection, "Children collection to be inserted must not be null.");
        int iChildNodeSize = childNodeSize();
        if (i10 < 0) {
            i10 += iChildNodeSize + 1;
        }
        Validate.isTrue(i10 >= 0 && i10 <= iChildNodeSize, "Insert position out of bounds.");
        addChildren(i10, (Node[]) new ArrayList(collection).toArray(new Node[0]));
        return this;
    }

    public boolean is(String str) {
        return is(QueryParser.parse(str));
    }

    public boolean isBlock() {
        return this.tag.isBlock();
    }

    public Element lastElementChild() {
        for (Node nodeLastChild = lastChild(); nodeLastChild != null; nodeLastChild = nodeLastChild.previousSibling()) {
            if (nodeLastChild instanceof Element) {
                return (Element) nodeLastChild;
            }
        }
        return null;
    }

    public Element lastElementSibling() {
        return parent() != null ? parent().lastElementChild() : this;
    }

    public Element nextElementSibling() {
        Node nodeNextSibling = this;
        do {
            nodeNextSibling = nodeNextSibling.nextSibling();
            if (nodeNextSibling == null) {
                return null;
            }
        } while (!(nodeNextSibling instanceof Element));
        return (Element) nodeNextSibling;
    }

    public Elements nextElementSiblings() {
        return nextElementSiblings(true);
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return this.tag.getName();
    }

    @Override // org.jsoup.nodes.Node
    public void nodelistChanged() {
        super.nodelistChanged();
        this.shadowChildrenRef = null;
    }

    @Override // org.jsoup.nodes.Node
    public String normalName() {
        return this.tag.normalName();
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        if (shouldIndent(outputSettings) && (!(appendable instanceof StringBuilder) || ((StringBuilder) appendable).length() > 0)) {
            indent(appendable, i10, outputSettings);
        }
        appendable.append('<').append(tagName());
        Attributes attributes = this.attributes;
        if (attributes != null) {
            attributes.html(appendable, outputSettings);
        }
        if (!this.childNodes.isEmpty() || !this.tag.isSelfClosing()) {
            appendable.append('>');
        } else if (outputSettings.syntax() == Document.OutputSettings.Syntax.html && this.tag.isEmpty()) {
            appendable.append('>');
        } else {
            appendable.append(" />");
        }
    }

    @Override // org.jsoup.nodes.Node
    public void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        if (this.childNodes.isEmpty() && this.tag.isSelfClosing()) {
            return;
        }
        if (outputSettings.prettyPrint() && !this.childNodes.isEmpty() && ((this.tag.formatAsBlock() && !preserveWhitespace(this.parentNode)) || (outputSettings.outline() && (this.childNodes.size() > 1 || (this.childNodes.size() == 1 && (this.childNodes.get(0) instanceof Element)))))) {
            indent(appendable, i10, outputSettings);
        }
        appendable.append("</").append(tagName()).append('>');
    }

    public String ownText() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        ownText(sbBorrowBuilder);
        return StringUtil.releaseBuilder(sbBorrowBuilder).trim();
    }

    public Elements parents() {
        Elements elements = new Elements();
        for (Element elementParent = parent(); elementParent != null && !elementParent.nameIs("#root"); elementParent = elementParent.parent()) {
            elements.add(elementParent);
        }
        return elements;
    }

    public Element prepend(String str) {
        Validate.notNull(str);
        addChildren(0, (Node[]) NodeUtils.parser(this).parseFragmentInput(str, this, baseUri()).toArray(new Node[0]));
        return this;
    }

    public Element prependChild(Node node) {
        Validate.notNull(node);
        addChildren(0, node);
        return this;
    }

    public Element prependChildren(java.util.Collection<? extends Node> collection) {
        insertChildren(0, collection);
        return this;
    }

    public Element prependElement(String str) {
        return prependElement(str, this.tag.namespace());
    }

    public Element prependText(String str) {
        Validate.notNull(str);
        prependChild(new TextNode(str));
        return this;
    }

    public Element previousElementSibling() {
        Node nodePreviousSibling = this;
        do {
            nodePreviousSibling = nodePreviousSibling.previousSibling();
            if (nodePreviousSibling == null) {
                return null;
            }
        } while (!(nodePreviousSibling instanceof Element));
        return (Element) nodePreviousSibling;
    }

    public Elements previousElementSiblings() {
        return nextElementSiblings(false);
    }

    public Element removeClass(String str) {
        Validate.notNull(str);
        Set<String> setClassNames = classNames();
        setClassNames.remove(str);
        classNames(setClassNames);
        return this;
    }

    public Elements select(String str) {
        return Selector.select(str, this);
    }

    public Element selectFirst(String str) {
        return Selector.selectFirst(str, this);
    }

    public Elements selectXpath(String str) {
        return new Elements((List<Element>) NodeUtils.selectXpath(str, this, Element.class));
    }

    public boolean shouldIndent(Document.OutputSettings outputSettings) {
        return outputSettings.prettyPrint() && isFormatAsBlock(outputSettings) && !isInlineable(outputSettings) && !preserveWhitespace(this.parentNode);
    }

    public Elements siblingElements() {
        if (this.parentNode == null) {
            return new Elements(0);
        }
        List<Element> listChildElementsList = parent().childElementsList();
        Elements elements = new Elements(listChildElementsList.size() - 1);
        for (Element element : listChildElementsList) {
            if (element != this) {
                elements.add(element);
            }
        }
        return elements;
    }

    public Stream<Element> stream() {
        return NodeUtils.stream(this, Element.class);
    }

    public Tag tag() {
        return this.tag;
    }

    public String tagName() {
        return this.tag.getName();
    }

    public String text() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        NodeTraversor.traverse(new TextAccumulator(sbBorrowBuilder), this);
        return StringUtil.releaseBuilder(sbBorrowBuilder).trim();
    }

    public List<TextNode> textNodes() {
        return filterNodes(TextNode.class);
    }

    public Element toggleClass(String str) {
        Validate.notNull(str);
        Set<String> setClassNames = classNames();
        if (setClassNames.contains(str)) {
            setClassNames.remove(str);
        } else {
            setClassNames.add(str);
        }
        classNames(setClassNames);
        return this;
    }

    public String val() {
        return elementIs("textarea", Parser.NamespaceHtml) ? text() : attr("value");
    }

    public String wholeOwnText() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        int iChildNodeSize = childNodeSize();
        for (int i10 = 0; i10 < iChildNodeSize; i10++) {
            appendWholeText(this.childNodes.get(i10), sbBorrowBuilder);
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public String wholeText() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        nodeStream().forEach(new b(sbBorrowBuilder, 0));
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public Element(String str) {
        this(Tag.valueOf(str, Parser.NamespaceHtml, ParseSettings.preserveCase), "", null);
    }

    private Elements nextElementSiblings(boolean z) {
        Elements elements = new Elements();
        if (this.parentNode == null) {
            return elements;
        }
        elements.add(this);
        return z ? elements.nextAll() : elements.prevAll();
    }

    public Element appendElement(String str, String str2) {
        Element element = new Element(Tag.valueOf(str, str2, NodeUtils.parser(this).settings()), baseUri());
        appendChild(element);
        return element;
    }

    @Override // org.jsoup.nodes.Node
    public Element attr(String str, String str2) {
        super.attr(str, str2);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element clearAttributes() {
        if (this.attributes != null) {
            super.clearAttributes();
            if (this.attributes.size() == 0) {
                this.attributes = null;
            }
        }
        return this;
    }

    public Element closest(Evaluator evaluator) {
        Validate.notNull(evaluator);
        Element elementRoot = root();
        Element elementParent = this;
        while (!evaluator.lambda$asPredicate$0(elementRoot, elementParent)) {
            elementParent = elementParent.parent();
            if (elementParent == null) {
                return null;
            }
        }
        return elementParent;
    }

    @Override // org.jsoup.nodes.Node
    public Element doClone(Node node) {
        Element element = (Element) super.doClone(node);
        Attributes attributes = this.attributes;
        element.attributes = attributes != null ? attributes.clone() : null;
        NodeList nodeList = new NodeList(element, this.childNodes.size());
        element.childNodes = nodeList;
        nodeList.addAll(this.childNodes);
        return element;
    }

    @Override // org.jsoup.nodes.Node
    public Element empty() {
        Iterator<Node> it = this.childNodes.iterator();
        while (it.hasNext()) {
            it.next().parentNode = null;
        }
        this.childNodes.clear();
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element filter(NodeFilter nodeFilter) {
        return (Element) super.filter(nodeFilter);
    }

    @Override // org.jsoup.nodes.Node
    public Element forEachNode(Consumer<? super Node> consumer) {
        return (Element) super.forEachNode(consumer);
    }

    public Elements getElementsByAttributeValueMatching(String str, String str2) {
        try {
            return getElementsByAttributeValueMatching(str, Pattern.compile(str2));
        } catch (PatternSyntaxException e5) {
            throw new IllegalArgumentException(d.C("Pattern syntax error: ", str2), e5);
        }
    }

    public Elements getElementsMatchingOwnText(String str) {
        try {
            return getElementsMatchingOwnText(Pattern.compile(str));
        } catch (PatternSyntaxException e5) {
            throw new IllegalArgumentException(d.C("Pattern syntax error: ", str), e5);
        }
    }

    public Elements getElementsMatchingText(String str) {
        try {
            return getElementsMatchingText(Pattern.compile(str));
        } catch (PatternSyntaxException e5) {
            throw new IllegalArgumentException(d.C("Pattern syntax error: ", str), e5);
        }
    }

    public Element id(String str) {
        Validate.notNull(str);
        attr(TtmlNode.ATTR_ID, str);
        return this;
    }

    public boolean is(Evaluator evaluator) {
        return evaluator.lambda$asPredicate$0(root(), this);
    }

    @Override // org.jsoup.nodes.Node
    public final Element parent() {
        return (Element) this.parentNode;
    }

    public Element prependElement(String str, String str2) {
        Element element = new Element(Tag.valueOf(str, str2, NodeUtils.parser(this).settings()), baseUri());
        prependChild(element);
        return element;
    }

    @Override // org.jsoup.nodes.Node
    public Element removeAttr(String str) {
        return (Element) super.removeAttr(str);
    }

    @Override // org.jsoup.nodes.Node
    public Element root() {
        return (Element) super.root();
    }

    public Elements select(Evaluator evaluator) {
        return Selector.select(evaluator, this);
    }

    public Element selectFirst(Evaluator evaluator) {
        return Collector.findFirst(evaluator, this);
    }

    public <T extends Node> List<T> selectXpath(String str, Class<T> cls) {
        return NodeUtils.selectXpath(str, this, cls);
    }

    @Override // org.jsoup.nodes.Node
    public Element shallowClone() {
        String strBaseUri = baseUri();
        if (strBaseUri.isEmpty()) {
            strBaseUri = null;
        }
        Tag tag = this.tag;
        Attributes attributes = this.attributes;
        return new Element(tag, strBaseUri, attributes != null ? attributes.clone() : null);
    }

    public Element tagName(String str) {
        return tagName(str, this.tag.namespace());
    }

    @Override // org.jsoup.nodes.Node
    public Element traverse(NodeVisitor nodeVisitor) {
        return (Element) super.traverse(nodeVisitor);
    }

    @Override // org.jsoup.nodes.Node
    public Element wrap(String str) {
        return (Element) super.wrap(str);
    }

    public Element(Tag tag, String str, Attributes attributes) {
        Validate.notNull(tag);
        this.childNodes = Node.EmptyNodes;
        this.attributes = attributes;
        this.tag = tag;
        if (str != null) {
            setBaseUri(str);
        }
    }

    @Override // org.jsoup.nodes.Node
    public Element after(String str) {
        return (Element) super.after(str);
    }

    public Element attr(String str, boolean z) {
        attributes().put(str, z);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element before(String str) {
        return (Element) super.before(str);
    }

    @Override // org.jsoup.nodes.Node
    /* JADX INFO: renamed from: clone */
    public Element mo7038clone() {
        return (Element) super.mo7038clone();
    }

    public Element tagName(String str, String str2) {
        Validate.notEmptyParam(str, "tagName");
        Validate.notEmptyParam(str2, "namespace");
        this.tag = Tag.valueOf(str, str2, NodeUtils.parser(this).settings());
        return this;
    }

    private void ownText(StringBuilder sb2) {
        for (int i10 = 0; i10 < childNodeSize(); i10++) {
            Node node = this.childNodes.get(i10);
            if (node instanceof TextNode) {
                appendNormalisedText(sb2, (TextNode) node);
            } else if (node.nameIs("br") && !TextNode.lastCharIsWhitespace(sb2)) {
                sb2.append(" ");
            }
        }
    }

    @Override // org.jsoup.nodes.Node
    public Element after(Node node) {
        return (Element) super.after(node);
    }

    @Override // org.jsoup.nodes.Node
    public Element before(Node node) {
        return (Element) super.before(node);
    }

    public Element classNames(Set<String> set) {
        Validate.notNull(set);
        if (set.isEmpty()) {
            attributes().remove("class");
            return this;
        }
        attributes().put("class", StringUtil.join(set, " "));
        return this;
    }

    public Element text(String str) {
        Validate.notNull(str);
        empty();
        Document documentOwnerDocument = ownerDocument();
        if (documentOwnerDocument != null && documentOwnerDocument.parser().isContentForTagData(normalName())) {
            appendChild(new DataNode(str));
            return this;
        }
        appendChild(new TextNode(str));
        return this;
    }

    public Element val(String str) {
        if (elementIs("textarea", Parser.NamespaceHtml)) {
            text(str);
            return this;
        }
        attr("value", str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public <T extends Appendable> T html(T t2) {
        int size = this.childNodes.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.childNodes.get(i10).outerHtml(t2);
        }
        return t2;
    }

    public Element html(String str) {
        empty();
        append(str);
        return this;
    }

    public Element insertChildren(int i10, Node... nodeArr) {
        Validate.notNull(nodeArr, "Children collection to be inserted must not be null.");
        int iChildNodeSize = childNodeSize();
        if (i10 < 0) {
            i10 += iChildNodeSize + 1;
        }
        Validate.isTrue(i10 >= 0 && i10 <= iChildNodeSize, "Insert position out of bounds.");
        addChildren(i10, nodeArr);
        return this;
    }

    public Element(Tag tag, String str) {
        this(tag, str, null);
    }
}

package org.jsoup.nodes;

import j$.util.stream.Stream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Node implements Cloneable {
    static final List<Node> EmptyNodes = Collections.EMPTY_LIST;
    static final String EmptyString = "";
    Node parentNode;
    int siblingIndex;

    public static class OuterHtmlVisitor implements NodeVisitor {
        private final Appendable accum;
        private final Document.OutputSettings out;

        public OuterHtmlVisitor(Appendable appendable, Document.OutputSettings outputSettings) {
            this.accum = appendable;
            this.out = outputSettings;
            outputSettings.prepareEncoder();
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i10) {
            try {
                node.outerHtmlHead(this.accum, i10, this.out);
            } catch (IOException e5) {
                throw new SerializationException(e5);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i10) {
            if (node.nodeName().equals("#text")) {
                return;
            }
            try {
                node.outerHtmlTail(this.accum, i10, this.out);
            } catch (IOException e5) {
                throw new SerializationException(e5);
            }
        }
    }

    private void addSiblingHtml(int i10, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(i10, (Node[]) NodeUtils.parser(this).parseFragmentInput(str, parent() instanceof Element ? (Element) parent() : null, baseUri()).toArray(new Node[0]));
    }

    private Element getDeepChild(Element element) {
        Element elementFirstElementChild = element.firstElementChild();
        while (true) {
            Element element2 = elementFirstElementChild;
            Element element3 = element;
            element = element2;
            if (element == null) {
                return element3;
            }
            elementFirstElementChild = element.firstElementChild();
        }
    }

    private void reindexChildren(int i10) {
        int iChildNodeSize = childNodeSize();
        if (iChildNodeSize == 0) {
            return;
        }
        List<Node> listEnsureChildNodes = ensureChildNodes();
        while (i10 < iChildNodeSize) {
            listEnsureChildNodes.get(i10).setSiblingIndex(i10);
            i10++;
        }
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        return (hasAttributes() && attributes().hasKeyIgnoreCase(str)) ? StringUtil.resolve(baseUri(), attributes().getIgnoreCase(str)) : "";
    }

    public void addChildren(Node... nodeArr) {
        List<Node> listEnsureChildNodes = ensureChildNodes();
        for (Node node : nodeArr) {
            reparentChild(node);
            listEnsureChildNodes.add(node);
            node.setSiblingIndex(listEnsureChildNodes.size() - 1);
        }
    }

    public Node after(String str) {
        addSiblingHtml(this.siblingIndex + 1, str);
        return this;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return "";
        }
        String ignoreCase = attributes().getIgnoreCase(str);
        return ignoreCase.length() > 0 ? ignoreCase : str.startsWith("abs:") ? absUrl(str.substring(4)) : "";
    }

    public abstract Attributes attributes();

    public int attributesSize() {
        if (hasAttributes()) {
            return attributes().size();
        }
        return 0;
    }

    public abstract String baseUri();

    public Node before(String str) {
        addSiblingHtml(this.siblingIndex, str);
        return this;
    }

    public Node childNode(int i10) {
        return ensureChildNodes().get(i10);
    }

    public abstract int childNodeSize();

    public List<Node> childNodes() {
        if (childNodeSize() == 0) {
            return EmptyNodes;
        }
        List<Node> listEnsureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size());
        arrayList.addAll(listEnsureChildNodes);
        return Collections.unmodifiableList(arrayList);
    }

    public Node[] childNodesAsArray() {
        return (Node[]) ensureChildNodes().toArray(new Node[0]);
    }

    public List<Node> childNodesCopy() {
        List<Node> listEnsureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size());
        Iterator<Node> it = listEnsureChildNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().mo7038clone());
        }
        return arrayList;
    }

    public Node clearAttributes() {
        if (hasAttributes()) {
            Iterator<Attribute> it = attributes().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
        return this;
    }

    public Node doClone(Node node) {
        Document documentOwnerDocument;
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            node2.siblingIndex = node == null ? 0 : this.siblingIndex;
            if (node == null && !(this instanceof Document) && (documentOwnerDocument = ownerDocument()) != null) {
                Document documentShallowClone = documentOwnerDocument.shallowClone();
                node2.parentNode = documentShallowClone;
                documentShallowClone.ensureChildNodes().add(node2);
            }
            return node2;
        } catch (CloneNotSupportedException e5) {
            throw new RuntimeException(e5);
        }
    }

    public abstract void doSetBaseUri(String str);

    public abstract Node empty();

    public abstract List<Node> ensureChildNodes();

    public boolean equals(Object obj) {
        return this == obj;
    }

    public Node filter(NodeFilter nodeFilter) {
        Validate.notNull(nodeFilter);
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public Node firstChild() {
        if (childNodeSize() == 0) {
            return null;
        }
        return ensureChildNodes().get(0);
    }

    public Node forEachNode(Consumer<? super Node> consumer) {
        Validate.notNull(consumer);
        nodeStream().forEach(consumer);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return false;
        }
        if (str.startsWith("abs:")) {
            String strSubstring = str.substring(4);
            if (attributes().hasKeyIgnoreCase(strSubstring) && !absUrl(strSubstring).isEmpty()) {
                return true;
            }
        }
        return attributes().hasKeyIgnoreCase(str);
    }

    public abstract boolean hasAttributes();

    public boolean hasParent() {
        return this.parentNode != null;
    }

    public boolean hasSameValue(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return outerHtml().equals(((Node) obj).outerHtml());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public <T extends Appendable> T html(T t2) {
        outerHtml(t2);
        return t2;
    }

    public void indent(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException {
        appendable.append('\n').append(StringUtil.padding(outputSettings.indentAmount() * i10, outputSettings.maxPaddingWidth()));
    }

    public final boolean isEffectivelyFirst() {
        int i10 = this.siblingIndex;
        if (i10 == 0) {
            return true;
        }
        if (i10 == 1) {
            Node nodePreviousSibling = previousSibling();
            if ((nodePreviousSibling instanceof TextNode) && ((TextNode) nodePreviousSibling).isBlank()) {
                return true;
            }
        }
        return false;
    }

    public Node lastChild() {
        int iChildNodeSize = childNodeSize();
        if (iChildNodeSize == 0) {
            return null;
        }
        return ensureChildNodes().get(iChildNodeSize - 1);
    }

    public boolean nameIs(String str) {
        return normalName().equals(str);
    }

    public Node nextSibling() {
        Node node = this.parentNode;
        if (node == null) {
            return null;
        }
        List<Node> listEnsureChildNodes = node.ensureChildNodes();
        int i10 = this.siblingIndex + 1;
        if (listEnsureChildNodes.size() > i10) {
            return listEnsureChildNodes.get(i10);
        }
        return null;
    }

    public abstract String nodeName();

    public Stream<Node> nodeStream() {
        return NodeUtils.stream(this, Node.class);
    }

    public void nodelistChanged() {
    }

    public String normalName() {
        return nodeName();
    }

    public String outerHtml() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        outerHtml(sbBorrowBuilder);
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public abstract void outerHtmlHead(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException;

    public abstract void outerHtmlTail(Appendable appendable, int i10, Document.OutputSettings outputSettings) throws IOException;

    public Document ownerDocument() {
        Node nodeRoot = root();
        if (nodeRoot instanceof Document) {
            return (Document) nodeRoot;
        }
        return null;
    }

    public Node parent() {
        return this.parentNode;
    }

    public boolean parentElementIs(String str, String str2) {
        Node node = this.parentNode;
        return node != null && (node instanceof Element) && ((Element) node).elementIs(str, str2);
    }

    public boolean parentNameIs(String str) {
        Node node = this.parentNode;
        return node != null && node.normalName().equals(str);
    }

    public final Node parentNode() {
        return this.parentNode;
    }

    public Node previousSibling() {
        Node node = this.parentNode;
        if (node != null && this.siblingIndex > 0) {
            return node.ensureChildNodes().get(this.siblingIndex - 1);
        }
        return null;
    }

    public void remove() {
        Node node = this.parentNode;
        if (node != null) {
            node.removeChild(this);
        }
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        if (hasAttributes()) {
            attributes().removeIgnoreCase(str);
        }
        return this;
    }

    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        int i10 = node.siblingIndex;
        ensureChildNodes().remove(i10);
        reindexChildren(i10);
        node.parentNode = null;
    }

    public void reparentChild(Node node) {
        node.setParentNode(this);
    }

    public void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        if (node == node2) {
            return;
        }
        Node node3 = node2.parentNode;
        if (node3 != null) {
            node3.removeChild(node2);
        }
        int i10 = node.siblingIndex;
        ensureChildNodes().set(i10, node2);
        node2.parentNode = this;
        node2.setSiblingIndex(i10);
        node.parentNode = null;
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    public Node root() {
        Node node = this;
        while (true) {
            Node node2 = node.parentNode;
            if (node2 == null) {
                return node;
            }
            node = node2;
        }
    }

    public void setBaseUri(String str) {
        Validate.notNull(str);
        doSetBaseUri(str);
    }

    public void setParentNode(Node node) {
        Validate.notNull(node);
        Node node2 = this.parentNode;
        if (node2 != null) {
            node2.removeChild(this);
        }
        this.parentNode = node;
    }

    public void setSiblingIndex(int i10) {
        this.siblingIndex = i10;
    }

    public Node shallowClone() {
        return doClone(null);
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    public List<Node> siblingNodes() {
        Node node = this.parentNode;
        if (node == null) {
            return Collections.EMPTY_LIST;
        }
        List<Node> listEnsureChildNodes = node.ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size() - 1);
        for (Node node2 : listEnsureChildNodes) {
            if (node2 != this) {
                arrayList.add(node2);
            }
        }
        return arrayList;
    }

    public Range sourceRange() {
        return Range.of(this, true);
    }

    public String toString() {
        return outerHtml();
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Node unwrap() {
        Validate.notNull(this.parentNode);
        Node nodeFirstChild = firstChild();
        this.parentNode.addChildren(this.siblingIndex, childNodesAsArray());
        remove();
        return nodeFirstChild;
    }

    public Node wrap(String str) {
        Validate.notEmpty(str);
        Node node = this.parentNode;
        List<Node> fragmentInput = NodeUtils.parser(this).parseFragmentInput(str, (node == null || !(node instanceof Element)) ? this instanceof Element ? (Element) this : null : (Element) node, baseUri());
        Node node2 = fragmentInput.get(0);
        if (node2 instanceof Element) {
            Element element = (Element) node2;
            Element deepChild = getDeepChild(element);
            Node node3 = this.parentNode;
            if (node3 != null) {
                node3.replaceChild(this, element);
            }
            deepChild.addChildren(this);
            if (fragmentInput.size() > 0) {
                for (int i10 = 0; i10 < fragmentInput.size(); i10++) {
                    Node node4 = fragmentInput.get(i10);
                    if (element != node4) {
                        Node node5 = node4.parentNode;
                        if (node5 != null) {
                            node5.removeChild(node4);
                        }
                        element.after(node4);
                    }
                }
            }
        }
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        if (node.parentNode == this.parentNode) {
            node.remove();
        }
        this.parentNode.addChildren(this.siblingIndex + 1, node);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        if (node.parentNode == this.parentNode) {
            node.remove();
        }
        this.parentNode.addChildren(this.siblingIndex, node);
        return this;
    }

    @Override // 
    /* JADX INFO: renamed from: clone */
    public Node mo7038clone() {
        Node nodeDoClone = doClone(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(nodeDoClone);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            int iChildNodeSize = node.childNodeSize();
            for (int i10 = 0; i10 < iChildNodeSize; i10++) {
                List<Node> listEnsureChildNodes = node.ensureChildNodes();
                Node nodeDoClone2 = listEnsureChildNodes.get(i10).doClone(node);
                listEnsureChildNodes.set(i10, nodeDoClone2);
                linkedList.add(nodeDoClone2);
            }
        }
        return nodeDoClone;
    }

    public <T extends Node> Stream<T> nodeStream(Class<T> cls) {
        return NodeUtils.stream(this, cls);
    }

    public void outerHtml(Appendable appendable) {
        NodeTraversor.traverse(new OuterHtmlVisitor(appendable, NodeUtils.outputSettings(this)), this);
    }

    public void addChildren(int i10, Node... nodeArr) {
        Validate.notNull(nodeArr);
        if (nodeArr.length == 0) {
            return;
        }
        List<Node> listEnsureChildNodes = ensureChildNodes();
        Node nodeParent = nodeArr[0].parent();
        if (nodeParent != null && nodeParent.childNodeSize() == nodeArr.length) {
            List<Node> listEnsureChildNodes2 = nodeParent.ensureChildNodes();
            int length = nodeArr.length;
            while (true) {
                int i11 = length - 1;
                if (length > 0) {
                    if (nodeArr[i11] != listEnsureChildNodes2.get(i11)) {
                        break;
                    } else {
                        length = i11;
                    }
                } else {
                    boolean z = childNodeSize() == 0;
                    nodeParent.empty();
                    listEnsureChildNodes.addAll(i10, Arrays.asList(nodeArr));
                    int length2 = nodeArr.length;
                    while (true) {
                        int i12 = length2 - 1;
                        if (length2 <= 0) {
                            break;
                        }
                        nodeArr[i12].parentNode = this;
                        length2 = i12;
                    }
                    if (z && nodeArr[0].siblingIndex == 0) {
                        return;
                    }
                    reindexChildren(i10);
                    return;
                }
            }
        }
        Validate.noNullElements(nodeArr);
        for (Node node : nodeArr) {
            reparentChild(node);
        }
        listEnsureChildNodes.addAll(i10, Arrays.asList(nodeArr));
        reindexChildren(i10);
    }

    public Node attr(String str, String str2) {
        attributes().putIgnoreCase(NodeUtils.parser(this).settings().normalizeAttribute(str), str2);
        return this;
    }
}

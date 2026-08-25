package org.jsoup.helper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.Selector;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes5.dex */
public class W3CDom {
    private static final String ContextNodeProperty = "jsoupContextNode";
    private static final String ContextProperty = "jsoupContextSource";
    public static final String SourceProperty = "jsoupSource";
    public static final String XPathFactoryProperty = "javax.xml.xpath.XPathFactory:jsoup";
    protected DocumentBuilderFactory factory;
    private boolean namespaceAware = true;

    public static class W3CBuilder implements NodeVisitor {
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private final Element contextElement;
        private Node dest;
        private final Document doc;
        private boolean namespaceAware = true;
        private final Stack<HashMap<String, String>> namespacesStack;
        private Document.OutputSettings.Syntax syntax;

        public W3CBuilder(org.w3c.dom.Document document) {
            Stack<HashMap<String, String>> stack = new Stack<>();
            this.namespacesStack = stack;
            this.syntax = Document.OutputSettings.Syntax.xml;
            this.doc = document;
            stack.push(new HashMap<>());
            this.dest = document;
            Element element = (Element) document.getUserData(W3CDom.ContextProperty);
            this.contextElement = element;
            org.jsoup.nodes.Document documentOwnerDocument = element.ownerDocument();
            if (this.namespaceAware && documentOwnerDocument != null && (documentOwnerDocument.parser().getTreeBuilder() instanceof HtmlTreeBuilder)) {
                stack.peek().put("", Parser.NamespaceHtml);
            }
        }

        private void append(Node node, org.jsoup.nodes.Node node2) {
            node.setUserData(W3CDom.SourceProperty, node2, null);
            this.dest.appendChild(node);
        }

        private void copyAttributes(org.jsoup.nodes.Node node, org.w3c.dom.Element element) {
            for (Attribute attribute : node.attributes()) {
                String validKey = Attribute.getValidKey(attribute.getKey(), this.syntax);
                if (validKey != null) {
                    element.setAttribute(validKey, attribute.getValue());
                }
            }
        }

        private String updateNamespaces(Element element) {
            Iterator<Attribute> it = element.attributes().iterator();
            while (true) {
                String strSubstring = "";
                if (!it.hasNext()) {
                    break;
                }
                Attribute next = it.next();
                String key = next.getKey();
                if (!key.equals(xmlnsKey)) {
                    if (key.startsWith(xmlnsPrefix)) {
                        strSubstring = key.substring(6);
                    }
                }
                this.namespacesStack.peek().put(strSubstring, next.getValue());
            }
            int iIndexOf = element.tagName().indexOf(58);
            return iIndexOf > 0 ? element.tagName().substring(0, iIndexOf) : "";
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(org.jsoup.nodes.Node node, int i10) {
            this.namespacesStack.push(new HashMap<>(this.namespacesStack.peek()));
            if (!(node instanceof Element)) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    append(this.doc.createTextNode(textNode.getWholeText()), textNode);
                    return;
                } else if (node instanceof Comment) {
                    Comment comment = (Comment) node;
                    append(this.doc.createComment(comment.getData()), comment);
                    return;
                } else {
                    if (node instanceof DataNode) {
                        DataNode dataNode = (DataNode) node;
                        append(this.doc.createTextNode(dataNode.getWholeData()), dataNode);
                        return;
                    }
                    return;
                }
            }
            Element element = (Element) node;
            String str = this.namespaceAware ? this.namespacesStack.peek().get(updateNamespaces(element)) : null;
            String strTagName = element.tagName();
            if (str == null) {
                try {
                    if (strTagName.contains(":")) {
                        str = "";
                    }
                } catch (DOMException unused) {
                    append(this.doc.createTextNode("<" + strTagName + ">"), element);
                    return;
                }
            }
            org.w3c.dom.Element elementCreateElementNS = this.doc.createElementNS(str, strTagName);
            copyAttributes(element, elementCreateElementNS);
            append(elementCreateElementNS, element);
            if (element == this.contextElement) {
                this.doc.setUserData(W3CDom.ContextNodeProperty, elementCreateElementNS, null);
            }
            this.dest = elementCreateElementNS;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(org.jsoup.nodes.Node node, int i10) {
            if ((node instanceof Element) && (this.dest.getParentNode() instanceof org.w3c.dom.Element)) {
                this.dest = this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }
    }

    public W3CDom() {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        this.factory = documentBuilderFactoryNewInstance;
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap("html");
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    public static String asString(org.w3c.dom.Document document, Map<String, String> map) {
        try {
            DOMSource dOMSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            if (map != null) {
                transformerNewTransformer.setOutputProperties(propertiesFromMap(map));
            }
            if (document.getDoctype() != null) {
                DocumentType doctype = document.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    transformerNewTransformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    transformerNewTransformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    transformerNewTransformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            transformerNewTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (TransformerException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public static org.w3c.dom.Document convert(org.jsoup.nodes.Document document) {
        return new W3CDom().fromJsoup(document);
    }

    private static HashMap<String, String> methodMap(String str) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method", str);
        return map;
    }

    public static Properties propertiesFromMap(Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    public Node contextNode(org.w3c.dom.Document document) {
        return (Node) document.getUserData(ContextNodeProperty);
    }

    public org.w3c.dom.Document fromJsoup(org.jsoup.nodes.Document document) {
        return fromJsoup((Element) document);
    }

    public boolean namespaceAware() {
        return this.namespaceAware;
    }

    public NodeList selectXpath(String str, org.w3c.dom.Document document) {
        return selectXpath(str, (Node) document);
    }

    public <T extends org.jsoup.nodes.Node> List<T> sourceNodes(NodeList nodeList, Class<T> cls) {
        Validate.notNull(nodeList);
        Validate.notNull(cls);
        ArrayList arrayList = new ArrayList(nodeList.getLength());
        for (int i10 = 0; i10 < nodeList.getLength(); i10++) {
            Object userData = nodeList.item(i10).getUserData(SourceProperty);
            if (cls.isInstance(userData)) {
                arrayList.add(cls.cast(userData));
            }
        }
        return arrayList;
    }

    public void convert(org.jsoup.nodes.Document document, org.w3c.dom.Document document2) {
        convert((Element) document, document2);
    }

    public org.w3c.dom.Document fromJsoup(Element element) {
        Validate.notNull(element);
        try {
            DocumentBuilder documentBuilderNewDocumentBuilder = this.factory.newDocumentBuilder();
            DOMImplementation dOMImplementation = documentBuilderNewDocumentBuilder.getDOMImplementation();
            org.w3c.dom.Document documentNewDocument = documentBuilderNewDocumentBuilder.newDocument();
            org.jsoup.nodes.Document documentOwnerDocument = element.ownerDocument();
            org.jsoup.nodes.DocumentType documentType = documentOwnerDocument != null ? documentOwnerDocument.documentType() : null;
            if (documentType != null) {
                try {
                    documentNewDocument.appendChild(dOMImplementation.createDocumentType(documentType.name(), documentType.publicId(), documentType.systemId()));
                } catch (DOMException unused) {
                }
            }
            documentNewDocument.setXmlStandalone(true);
            documentNewDocument.setUserData(ContextProperty, element instanceof org.jsoup.nodes.Document ? element.firstElementChild() : element, null);
            if (documentOwnerDocument != null) {
                element = documentOwnerDocument;
            }
            convert(element, documentNewDocument);
            return documentNewDocument;
        } catch (ParserConfigurationException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public W3CDom namespaceAware(boolean z) {
        this.namespaceAware = z;
        this.factory.setNamespaceAware(z);
        return this;
    }

    public NodeList selectXpath(String str, Node node) {
        Validate.notEmptyParam(str, "xpath");
        Validate.notNullParam(node, "contextNode");
        try {
            NodeList nodeList = (NodeList) (System.getProperty(XPathFactoryProperty) != null ? XPathFactory.newInstance("jsoup") : XPathFactory.newInstance()).newXPath().compile(str).evaluate(node, XPathConstants.NODESET);
            Validate.notNull(nodeList);
            return nodeList;
        } catch (XPathExpressionException e5) {
            e = e5;
            throw new Selector.SelectorParseException(e, "Could not evaluate XPath query [%s]: %s", str, e.getMessage());
        } catch (XPathFactoryConfigurationException e6) {
            e = e6;
            throw new Selector.SelectorParseException(e, "Could not evaluate XPath query [%s]: %s", str, e.getMessage());
        }
    }

    public void convert(Element element, org.w3c.dom.Document document) {
        W3CBuilder w3CBuilder = new W3CBuilder(document);
        w3CBuilder.namespaceAware = this.namespaceAware;
        org.jsoup.nodes.Document documentOwnerDocument = element.ownerDocument();
        if (documentOwnerDocument != null) {
            if (!StringUtil.isBlank(documentOwnerDocument.location())) {
                document.setDocumentURI(documentOwnerDocument.location());
            }
            w3CBuilder.syntax = documentOwnerDocument.outputSettings().syntax();
        }
        if (element instanceof org.jsoup.nodes.Document) {
            element = element.firstElementChild();
        }
        NodeTraversor.traverse(w3CBuilder, element);
    }

    public String asString(org.w3c.dom.Document document) {
        return asString(document, null);
    }
}

package org.jsoup.nodes;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;

/* JADX INFO: loaded from: classes5.dex */
final class NodeUtils {
    public static Document.OutputSettings outputSettings(Node node) {
        Document documentOwnerDocument = node.ownerDocument();
        return documentOwnerDocument != null ? documentOwnerDocument.outputSettings() : new Document("").outputSettings();
    }

    public static Parser parser(Node node) {
        Document documentOwnerDocument = node.ownerDocument();
        return (documentOwnerDocument == null || documentOwnerDocument.parser() == null) ? new Parser(new HtmlTreeBuilder()) : documentOwnerDocument.parser();
    }

    public static <T extends Node> List<T> selectXpath(String str, Element element, Class<T> cls) {
        Validate.notEmpty(str);
        Validate.notNull(element);
        Validate.notNull(cls);
        W3CDom w3CDomNamespaceAware = new W3CDom().namespaceAware(false);
        return w3CDomNamespaceAware.sourceNodes(w3CDomNamespaceAware.selectXpath(str, w3CDomNamespaceAware.contextNode(w3CDomNamespaceAware.fromJsoup(element))), cls);
    }

    public static <T extends Node> Spliterator<T> spliterator(Iterator<T> it) {
        return Spliterators.spliteratorUnknownSize(it, 273);
    }

    public static <T extends Node> Stream<T> stream(Node node, Class<T> cls) {
        return StreamSupport.stream(spliterator(new NodeIterator(node, cls)), false);
    }
}

package org.jsoup.select;

import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes5.dex */
@FunctionalInterface
public interface NodeVisitor {
    void head(Node node, int i10);

    void tail(Node node, int i10);
}

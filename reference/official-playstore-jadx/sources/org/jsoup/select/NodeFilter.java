package org.jsoup.select;

import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes5.dex */
public interface NodeFilter {

    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    FilterResult head(Node node, int i10);

    FilterResult tail(Node node, int i10);
}

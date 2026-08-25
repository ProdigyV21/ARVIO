package org.jsoup.select;

import j$.util.stream.Collectors;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes5.dex */
public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator evaluator, Element element) {
        evaluator.reset();
        return (Elements) element.stream().filter(evaluator.asPredicate(element)).collect(Collectors.toCollection(new e(1)));
    }

    public static Element findFirst(Evaluator evaluator, Element element) {
        evaluator.reset();
        return element.stream().filter(evaluator.asPredicate(element)).findFirst().orElse(null);
    }
}

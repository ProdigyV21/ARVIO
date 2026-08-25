package org.jsoup.select;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes5.dex */
public class Selector {

    public static class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String str) {
            super(str);
        }

        public SelectorParseException(String str, Object... objArr) {
            super(String.format(str, objArr));
        }

        public SelectorParseException(Throwable th, String str, Object... objArr) {
            super(String.format(str, objArr), th);
        }
    }

    private Selector() {
    }

    public static Elements filterOut(Collection<Element> collection, Collection<Element> collection2) {
        Elements elements = new Elements();
        for (Element element : collection) {
            Iterator<Element> it = collection2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    elements.add(element);
                    break;
                }
                if (element.equals(it.next())) {
                    break;
                }
            }
        }
        return elements;
    }

    public static Elements select(String str, Element element) {
        Validate.notEmpty(str);
        return select(QueryParser.parse(str), element);
    }

    public static Element selectFirst(String str, Element element) {
        Validate.notEmpty(str);
        return Collector.findFirst(QueryParser.parse(str), element);
    }

    public static Elements select(Evaluator evaluator, Element element) {
        Validate.notNull(evaluator);
        Validate.notNull(element);
        return Collector.collect(evaluator, element);
    }

    public static Elements select(String str, Iterable<Element> iterable) {
        Validate.notEmpty(str);
        Validate.notNull(iterable);
        Evaluator evaluator = QueryParser.parse(str);
        Elements elements = new Elements();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator<Element> it = iterable.iterator();
        while (it.hasNext()) {
            for (Element element : select(evaluator, it.next())) {
                if (identityHashMap.put(element, Boolean.TRUE) == null) {
                    elements.add(element);
                }
            }
        }
        return elements;
    }
}

package org.jsoup.select;

import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/* JADX INFO: loaded from: classes5.dex */
public class Elements extends ArrayList<Element> implements List {
    public Elements() {
    }

    private <T extends Node> java.util.List<T> childNodesOfType(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        for (Element element : this) {
            for (int i10 = 0; i10 < element.childNodeSize(); i10++) {
                Node nodeChildNode = element.childNode(i10);
                if (cls.isInstance(nodeChildNode)) {
                    arrayList.add(cls.cast(nodeChildNode));
                }
            }
        }
        return arrayList;
    }

    private Elements siblings(String str, boolean z, boolean z5) {
        Elements elements = new Elements();
        Evaluator evaluator = str != null ? QueryParser.parse(str) : null;
        for (Element elementNextElementSibling : this) {
            do {
                elementNextElementSibling = z ? elementNextElementSibling.nextElementSibling() : elementNextElementSibling.previousElementSibling();
                if (elementNextElementSibling != null) {
                    if (evaluator == null) {
                        elements.add(elementNextElementSibling);
                    } else if (elementNextElementSibling.is(evaluator)) {
                        elements.add(elementNextElementSibling);
                    }
                }
            } while (z5);
        }
        return elements;
    }

    public Elements addClass(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().addClass(str);
        }
        return this;
    }

    public Elements after(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().after(str);
        }
        return this;
    }

    public Elements append(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().append(str);
        }
        return this;
    }

    public String attr(String str) {
        for (Element element : this) {
            if (element.hasAttr(str)) {
                return element.attr(str);
            }
        }
        return "";
    }

    public Elements before(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().before(str);
        }
        return this;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        remove();
        super.clear();
    }

    public java.util.List<Comment> comments() {
        return childNodesOfType(Comment.class);
    }

    public java.util.List<DataNode> dataNodes() {
        return childNodesOfType(DataNode.class);
    }

    public java.util.List<String> eachAttr(String str) {
        ArrayList arrayList = new ArrayList(size());
        for (Element element : this) {
            if (element.hasAttr(str)) {
                arrayList.add(element.attr(str));
            }
        }
        return arrayList;
    }

    public java.util.List<String> eachText() {
        ArrayList arrayList = new ArrayList(size());
        for (Element element : this) {
            if (element.hasText()) {
                arrayList.add(element.text());
            }
        }
        return arrayList;
    }

    public Elements empty() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().empty();
        }
        return this;
    }

    public Elements eq(int i10) {
        return size() > i10 ? new Elements(get(i10)) : new Elements();
    }

    public Elements filter(NodeFilter nodeFilter) {
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public Element first() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    @Override // java.util.ArrayList, java.lang.Iterable, j$.util.Collection
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable$CC.$default$forEach(this, consumer);
    }

    public java.util.List<FormElement> forms() {
        ArrayList arrayList = new ArrayList();
        for (Element element : this) {
            if (element instanceof FormElement) {
                arrayList.add((FormElement) element);
            }
        }
        return arrayList;
    }

    public boolean hasAttr(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().hasAttr(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasClass(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().hasClass(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasText() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().hasText()) {
                return true;
            }
        }
        return false;
    }

    public String html() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sbBorrowBuilder.length() != 0) {
                sbBorrowBuilder.append("\n");
            }
            sbBorrowBuilder.append(element.html());
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public boolean is(String str) {
        Evaluator evaluator = QueryParser.parse(str);
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().is(evaluator)) {
                return true;
            }
        }
        return false;
    }

    public Element last() {
        if (isEmpty()) {
            return null;
        }
        return get(size() - 1);
    }

    public Elements next() {
        return siblings(null, true, false);
    }

    public Elements nextAll() {
        return siblings(null, true, true);
    }

    public Elements not(String str) {
        return Selector.filterOut(this, Selector.select(str, this));
    }

    public String outerHtml() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sbBorrowBuilder.length() != 0) {
                sbBorrowBuilder.append("\n");
            }
            sbBorrowBuilder.append(element.outerHtml());
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    @Override // java.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    public Elements parents() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next().parents());
        }
        return new Elements(linkedHashSet);
    }

    public Elements prepend(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().prepend(str);
        }
        return this;
    }

    public Elements prev() {
        return siblings(null, false, false);
    }

    public Elements prevAll() {
        return siblings(null, false, true);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    public Elements removeAttr(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().removeAttr(str);
        }
        return this;
    }

    public Elements removeClass(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().removeClass(str);
        }
        return this;
    }

    @Override // java.util.ArrayList, java.util.Collection, j$.util.Collection
    public boolean removeIf(Predicate<? super Element> predicate) {
        Iterator<Element> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.List, j$.util.List
    public void replaceAll(UnaryOperator<Element> unaryOperator) {
        for (int i10 = 0; i10 < size(); i10++) {
            set(i10, (Element) unaryOperator.apply(get(i10)));
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        Iterator<Element> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public Elements select(String str) {
        return Selector.select(str, this);
    }

    @Override // java.util.ArrayList, java.util.List, j$.util.List
    public /* synthetic */ void sort(Comparator comparator) {
        List.CC.$default$sort(this, comparator);
    }

    @Override // java.util.ArrayList, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream stream() {
        return Stream.Wrapper.convert(stream());
    }

    public Elements tagName(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().tagName(str);
        }
        return this;
    }

    public String text() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sbBorrowBuilder.length() != 0) {
                sbBorrowBuilder.append(" ");
            }
            sbBorrowBuilder.append(element.text());
        }
        return StringUtil.releaseBuilder(sbBorrowBuilder);
    }

    public java.util.List<TextNode> textNodes() {
        return childNodesOfType(TextNode.class);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return outerHtml();
    }

    public Elements toggleClass(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().toggleClass(str);
        }
        return this;
    }

    public Elements traverse(NodeVisitor nodeVisitor) {
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Elements unwrap() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().unwrap();
        }
        return this;
    }

    public String val() {
        return size() > 0 ? first().val() : "";
    }

    public Elements wrap(String str) {
        Validate.notEmpty(str);
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().wrap(str);
        }
        return this;
    }

    public Elements(int i10) {
        super(i10);
    }

    @Override // java.util.ArrayList
    public Elements clone() {
        Elements elements = new Elements(size());
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            elements.add(it.next().mo7038clone());
        }
        return elements;
    }

    public Elements next(String str) {
        return siblings(str, true, false);
    }

    public Elements nextAll(String str) {
        return siblings(str, true, true);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream parallelStream() {
        return StreamSupport.stream(Collection.EL.b(this), true);
    }

    public Elements prev(String str) {
        return siblings(str, false, false);
    }

    public Elements prevAll(String str) {
        return siblings(str, false, true);
    }

    public Elements remove() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        return this;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public Element set(int i10, Element element) {
        Validate.notNull(element);
        Element element2 = (Element) super.set(i10, element);
        element2.replaceWith(element);
        return element2;
    }

    @Override // java.util.ArrayList, java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection
    public /* synthetic */ j$.util.Spliterator spliterator() {
        return List.CC.$default$spliterator(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    public Elements(java.util.Collection<Element> collection) {
        super(collection);
    }

    public Elements(java.util.List<Element> list) {
        super(list);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public Element remove(int i10) {
        Element element = (Element) super.remove(i10);
        element.remove();
        return element;
    }

    public Elements val(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().val(str);
        }
        return this;
    }

    public Elements(Element... elementArr) {
        super(Arrays.asList(elementArr));
    }

    public Elements attr(String str, String str2) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().attr(str, str2);
        }
        return this;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int iIndexOf = super.indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    public Elements html(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().html(str);
        }
        return this;
    }
}

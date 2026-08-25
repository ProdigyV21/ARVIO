package io.ktor.http.cio.internals;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\b\b\b\u0000\u0018\u0000 \u0017*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002\u0017\u0018B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006JS\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree;", "", "T", "Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "root", "<init>", "(Lio/ktor/http/cio/internals/AsciiCharTree$Node;)V", "", "sequence", "", "fromIdx", TtmlNode.END, "", "lowerCase", "Lkotlin/Function2;", "", "stopPredicate", "", "search", "(Ljava/lang/CharSequence;IIZLr7/p;)Ljava/util/List;", "Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "getRoot", "()Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "Companion", "Node", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AsciiCharTree<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Node<T> root;

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jy\u0010\u0013\u001a\u00020\u0012\"\b\b\u0001\u0010\u0004*\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00060\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\r2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J+\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0016\"\b\b\u0001\u0010\u0004*\u00020\u00152\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0004\b\u0013\u0010\u0017JY\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0016\"\b\b\u0001\u0010\u0004*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\r2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0013\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree$Companion;", "", "<init>", "()V", "T", "", "Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "resultList", "", "from", "", "maxLength", "idx", "Lkotlin/Function1;", "length", "Lkotlin/Function2;", "", "charAt", "Lx6/t0;", "build", "(Ljava/util/List;Ljava/util/List;IILr7/l;Lr7/p;)V", "", "Lio/ktor/http/cio/internals/AsciiCharTree;", "(Ljava/util/List;)Lio/ktor/http/cio/internals/AsciiCharTree;", "(Ljava/util/List;Lr7/l;Lr7/p;)Lio/ktor/http/cio/internals/AsciiCharTree;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final <T extends CharSequence> AsciiCharTree<T> build(List<? extends T> from) {
            return build(from, AsciiCharTree$Companion$build$1.INSTANCE, AsciiCharTree$Companion$build$2.INSTANCE);
        }

        private Companion() {
        }

        public final <T> AsciiCharTree<T> build(List<? extends T> from, l<? super T, Integer> length, p<? super T, ? super Integer, Character> charAt) {
            T t2;
            Iterator<T> it = from.iterator();
            if (it.hasNext()) {
                T next = it.next();
                if (it.hasNext()) {
                    Comparable comparable = (Comparable) length.invoke(next);
                    do {
                        T next2 = it.next();
                        Comparable comparable2 = (Comparable) length.invoke(next2);
                        if (comparable.compareTo(comparable2) < 0) {
                            next = next2;
                            comparable = comparable2;
                        }
                    } while (it.hasNext());
                }
                t2 = next;
            } else {
                t2 = null;
            }
            if (t2 == null) {
                throw new NoSuchElementException("Unable to build char tree from an empty list");
            }
            int iIntValue = ((Number) length.invoke(t2)).intValue();
            if (!from.isEmpty()) {
                Iterator<T> it2 = from.iterator();
                while (it2.hasNext()) {
                    if (((Number) length.invoke(it2.next())).intValue() == 0) {
                        throw new IllegalArgumentException("There should be no empty entries");
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            build(arrayList, from, iIntValue, 0, length, charAt);
            arrayList.trimToSize();
            return new AsciiCharTree<>(new Node((char) 0, z.f19728i, arrayList));
        }

        private final <T> void build(List<Node<T>> resultList, List<? extends T> from, int maxLength, int idx, l<? super T, Integer> length, p<? super T, ? super Integer, Character> charAt) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (T t2 : from) {
                Character ch = (Character) charAt.invoke(t2, Integer.valueOf(idx));
                ch.charValue();
                Object arrayList = linkedHashMap.get(ch);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(ch, arrayList);
                }
                ((List) arrayList).add(t2);
            }
            p<? super T, ? super Integer, Character> pVar = charAt;
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                char cCharValue = ((Character) entry.getKey()).charValue();
                List list = (List) entry.getValue();
                int i10 = idx + 1;
                ArrayList arrayList2 = new ArrayList();
                Companion companion = AsciiCharTree.INSTANCE;
                ArrayList arrayList3 = new ArrayList();
                for (T t10 : list) {
                    if (((Number) length.invoke(t10)).intValue() > i10) {
                        arrayList3.add(t10);
                    }
                }
                companion.build(arrayList2, arrayList3, maxLength, i10, length, pVar);
                arrayList2.trimToSize();
                ArrayList arrayList4 = new ArrayList();
                for (T t11 : list) {
                    if (((Number) length.invoke(t11)).intValue() == i10) {
                        arrayList4.add(t11);
                    }
                }
                resultList.add(new Node<>(cCharValue, arrayList4, arrayList2));
                pVar = charAt;
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\u0006¢\u0006\u0002\u0010\bR!\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00000\n¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "T", "", "ch", "", "exact", "", "children", "(CLjava/util/List;Ljava/util/List;)V", "array", "", "getArray", "()[Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "[Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "getCh", "()C", "getChildren", "()Ljava/util/List;", "getExact", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Node<T> {
        private final Node<T>[] array;
        private final char ch;
        private final List<Node<T>> children;
        private final List<T> exact;

        /* JADX WARN: Multi-variable type inference failed */
        public Node(char c10, List<? extends T> list, List<Node<T>> list2) {
            this.ch = c10;
            this.exact = list;
            this.children = list2;
            Node<T>[] nodeArr = new Node[256];
            for (int i10 = 0; i10 < 256; i10++) {
                Iterator<T> it = this.children.iterator();
                Node<T> node = null;
                boolean z = false;
                Node<T> node2 = null;
                while (true) {
                    if (it.hasNext()) {
                        T next = it.next();
                        if (((Node) next).ch == i10) {
                            if (z) {
                                break;
                            }
                            z = true;
                            node2 = next;
                        }
                    } else if (z) {
                        node = node2;
                    }
                }
                nodeArr[i10] = node;
            }
            this.array = nodeArr;
        }

        public final Node<T>[] getArray() {
            return this.array;
        }

        public final char getCh() {
            return this.ch;
        }

        public final List<Node<T>> getChildren() {
            return this.children;
        }

        public final List<T> getExact() {
            return this.exact;
        }
    }

    public AsciiCharTree(Node<T> node) {
        this.root = node;
    }

    public static /* synthetic */ List search$default(AsciiCharTree asciiCharTree, CharSequence charSequence, int i10, int i11, boolean z, p pVar, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        if ((i12 & 8) != 0) {
            z = false;
        }
        return asciiCharTree.search(charSequence, i10, i11, z, pVar);
    }

    public final Node<T> getRoot() {
        return this.root;
    }

    public final List<T> search(CharSequence sequence, int fromIdx, int end, boolean lowerCase, p<? super Character, ? super Integer, Boolean> stopPredicate) {
        if (sequence.length() == 0) {
            throw new IllegalArgumentException("Couldn't search in char tree for empty string");
        }
        Node<T> node = this.root;
        while (fromIdx < end) {
            char cCharAt = sequence.charAt(fromIdx);
            if (((Boolean) stopPredicate.invoke(Character.valueOf(cCharAt), Integer.valueOf(cCharAt))).booleanValue()) {
                break;
            }
            Node<T> node2 = node.getArray()[cCharAt];
            if (node2 == null) {
                node = lowerCase ? node.getArray()[Character.toLowerCase(cCharAt)] : null;
                if (node == null) {
                    return z.f19728i;
                }
            } else {
                node = node2;
            }
            fromIdx++;
        }
        return node.getExact();
    }
}

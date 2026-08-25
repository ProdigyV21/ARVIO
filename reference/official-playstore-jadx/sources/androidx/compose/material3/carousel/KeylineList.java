package androidx.compose.material3.carousel;

import i7.n;
import io.ktor.http.ContentDisposition;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.p;
import x7.h;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010*\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\u0010\u0004J\u0011\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0002H\u0096\u0003J\u0017\u0010!\u001a\u00020\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#H\u0096\u0001J\u000e\u0010$\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020%J\u0011\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\tH\u0096\u0003J\u000e\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020%J\u000e\u0010*\u001a\u00020\u00022\u0006\u0010)\u001a\u00020%J\u0011\u0010+\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0002H\u0096\u0001J\t\u0010,\u001a\u00020\u001fH\u0096\u0001J\u0006\u0010-\u001a\u00020\u001fJ\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020%J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000201H\u0096\u0003J\u000e\u00102\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020%J\u0011\u00103\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0002H\u0096\u0001J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000205H\u0096\u0001J\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u0002052\u0006\u0010'\u001a\u00020\tH\u0096\u0001J\u001f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u00107\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0096\u0001R\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0012\u0010\u001c\u001a\u00020\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000b¨\u00069"}, d2 = {"Landroidx/compose/material3/carousel/KeylineList;", "", "Landroidx/compose/material3/carousel/Keyline;", "keylines", "(Ljava/util/List;)V", "firstFocal", "getFirstFocal", "()Landroidx/compose/material3/carousel/Keyline;", "firstFocalIndex", "", "getFirstFocalIndex", "()I", "firstNonAnchor", "getFirstNonAnchor", "firstNonAnchorIndex", "getFirstNonAnchorIndex", "lastFocal", "getLastFocal", "lastFocalIndex", "getLastFocalIndex", "lastNonAnchor", "getLastNonAnchor", "lastNonAnchorIndex", "getLastNonAnchorIndex", "pivot", "getPivot", "pivotIndex", "getPivotIndex", ContentDisposition.Parameters.Size, "getSize", "contains", "", "element", "containsAll", "elements", "", "firstIndexAfterFocalRangeWithSize", "", "get", "index", "getKeylineAfter", "unadjustedOffset", "getKeylineBefore", "indexOf", "isEmpty", "isFirstFocalItemAtStartOfContainer", "isLastFocalItemAtEndOfContainer", "carouselMainAxisSize", "iterator", "", "lastIndexBeforeFocalRangeWithSize", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeylineList implements List<Keyline>, s7.a, j$.util.List {
    public static final int $stable = 8;
    private final /* synthetic */ List<Keyline> $$delegate_0;
    private final Keyline firstFocal;
    private final int firstFocalIndex;
    private final Keyline firstNonAnchor;
    private final int firstNonAnchorIndex;
    private final Keyline lastFocal;
    private final int lastFocalIndex;
    private final Keyline lastNonAnchor;
    private final int lastNonAnchorIndex;
    private final Keyline pivot;
    private final int pivotIndex;

    public KeylineList(List<Keyline> list) {
        int iNextIndex;
        int iNextIndex2;
        this.$$delegate_0 = list;
        Iterator<Keyline> it = iterator();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            iNextIndex = -1;
            if (!it.hasNext()) {
                i11 = -1;
                break;
            } else if (it.next().isPivot()) {
                break;
            } else {
                i11++;
            }
        }
        this.pivotIndex = i11;
        this.pivot = get(i11);
        Iterator<Keyline> it2 = iterator();
        int i12 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i12 = -1;
                break;
            } else if (!it2.next().isAnchor()) {
                break;
            } else {
                i12++;
            }
        }
        this.firstNonAnchorIndex = i12;
        this.firstNonAnchor = get(i12);
        ListIterator<Keyline> listIterator = listIterator(size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex2 = -1;
                break;
            } else if (!listIterator.previous().isAnchor()) {
                iNextIndex2 = listIterator.nextIndex();
                break;
            }
        }
        this.lastNonAnchorIndex = iNextIndex2;
        this.lastNonAnchor = get(iNextIndex2);
        Iterator<Keyline> it3 = iterator();
        while (true) {
            if (!it3.hasNext()) {
                i10 = -1;
                break;
            } else if (it3.next().isFocal()) {
                break;
            } else {
                i10++;
            }
        }
        this.firstFocalIndex = i10;
        Keyline keyline = (Keyline) x.p0(i10, this);
        if (keyline == null) {
            throw new NoSuchElementException("All KeylineLists must have at least one focal keyline");
        }
        this.firstFocal = keyline;
        ListIterator<Keyline> listIterator2 = listIterator(size());
        while (true) {
            if (!listIterator2.hasPrevious()) {
                break;
            } else if (listIterator2.previous().isFocal()) {
                iNextIndex = listIterator2.nextIndex();
                break;
            }
        }
        this.lastFocalIndex = iNextIndex;
        Keyline keyline2 = (Keyline) x.p0(iNextIndex, this);
        if (keyline2 == null) {
            throw new NoSuchElementException("All KeylineLists must have at least one focal keyline");
        }
        this.lastFocal = keyline2;
    }

    /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
    public void add2(int i10, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i10, Collection<? extends Keyline> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Keyline element) {
        return this.$$delegate_0.contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        return this.$$delegate_0.containsAll(elements);
    }

    public final int firstIndexAfterFocalRangeWithSize(float size) {
        Object next;
        Iterator it = new i(this.lastFocalIndex, t7.a.w(this), 1).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (get(((Number) next).intValue()).getSize() == size) {
                break;
            }
        }
        Integer num = (Integer) next;
        return num != null ? num.intValue() : t7.a.w(this);
    }

    @Override // java.lang.Iterable, j$.util.Collection
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable$CC.$default$forEach(this, consumer);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public Keyline get(int index) {
        return this.$$delegate_0.get(index);
    }

    public final Keyline getFirstFocal() {
        return this.firstFocal;
    }

    public final int getFirstFocalIndex() {
        return this.firstFocalIndex;
    }

    public final Keyline getFirstNonAnchor() {
        return this.firstNonAnchor;
    }

    public final int getFirstNonAnchorIndex() {
        return this.firstNonAnchorIndex;
    }

    public final Keyline getKeylineAfter(float unadjustedOffset) {
        Keyline keyline;
        int size = size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                keyline = null;
                break;
            }
            keyline = get(i10);
            if (keyline.getUnadjustedOffset() >= unadjustedOffset) {
                break;
            }
            i10++;
        }
        Keyline keyline2 = keyline;
        return keyline2 == null ? (Keyline) x.w0(this) : keyline2;
    }

    public final Keyline getKeylineBefore(float unadjustedOffset) {
        int size = size() - 1;
        if (size >= 0) {
            while (true) {
                int i10 = size - 1;
                Keyline keyline = get(size);
                if (keyline.getUnadjustedOffset() < unadjustedOffset) {
                    return keyline;
                }
                if (i10 < 0) {
                    break;
                }
                size = i10;
            }
        }
        return (Keyline) x.m0(this);
    }

    public final Keyline getLastFocal() {
        return this.lastFocal;
    }

    public final int getLastFocalIndex() {
        return this.lastFocalIndex;
    }

    public final Keyline getLastNonAnchor() {
        return this.lastNonAnchor;
    }

    public final int getLastNonAnchorIndex() {
        return this.lastNonAnchorIndex;
    }

    public final Keyline getPivot() {
        return this.pivot;
    }

    public final int getPivotIndex() {
        return this.pivotIndex;
    }

    public int getSize() {
        return this.$$delegate_0.size();
    }

    public int indexOf(Keyline element) {
        return this.$$delegate_0.indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    public final boolean isFirstFocalItemAtStartOfContainer() {
        return this.firstFocal.getOffset() - (this.firstFocal.getSize() / ((float) 2)) >= 0.0f && p.a(this.firstFocal, this.firstNonAnchor);
    }

    public final boolean isLastFocalItemAtEndOfContainer(float carouselMainAxisSize) {
        return (this.lastFocal.getSize() / ((float) 2)) + this.lastFocal.getOffset() <= carouselMainAxisSize && p.a(this.lastFocal, this.lastNonAnchor);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Keyline> iterator() {
        return this.$$delegate_0.iterator();
    }

    public final int lastIndexBeforeFocalRangeWithSize(float size) {
        Object next;
        int i10 = this.firstFocalIndex - 1;
        h hVar = new h(i10, n.a(i10, 0, -1), -1);
        while (true) {
            if (!hVar.f22624m) {
                next = null;
                break;
            }
            next = hVar.next();
            if (get(((Number) next).intValue()).getSize() == size) {
                break;
            }
        }
        Integer num = (Integer) next;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public int lastIndexOf(Keyline element) {
        return this.$$delegate_0.lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<Keyline> listIterator() {
        return this.$$delegate_0.listIterator();
    }

    @Override // java.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public Keyline remove(int i10) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ boolean removeIf(Predicate predicate) {
        return Collection.CC.$default$removeIf(this, predicate);
    }

    @Override // java.util.List, j$.util.List
    public void replaceAll(UnaryOperator<Keyline> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(java.util.Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
    public Keyline set2(int i10, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List, j$.util.List
    public void sort(Comparator<? super Keyline> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream stream() {
        return Stream.Wrapper.convert(stream());
    }

    @Override // java.util.List
    public List<Keyline> subList(int fromIndex, int toIndex) {
        return this.$$delegate_0.subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return o.a(this);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ void add(int i10, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(java.util.Collection<? extends Keyline> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Keyline) {
            return contains((Keyline) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Keyline) {
            return indexOf((Keyline) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Keyline) {
            return lastIndexOf((Keyline) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<Keyline> listIterator(int index) {
        return this.$$delegate_0.listIterator(index);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream parallelStream() {
        return StreamSupport.stream(Collection.EL.b(this), true);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Keyline remove(int i10) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Keyline set(int i10, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable, j$.util.List, j$.util.Collection
    public /* synthetic */ j$.util.Spliterator spliterator() {
        return List.CC.$default$spliterator(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    public boolean add(Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) o.b(this, tArr);
    }

    @Override // java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

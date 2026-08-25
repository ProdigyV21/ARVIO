package io.github.jan.supabase.collections;

import androidx.compose.foundation.c;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.ktor.http.ContentDisposition;
import ja.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.o;
import s7.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001b\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0003\"\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0017J\u0018\u0010\u0018\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016¢\u0006\u0004\b \u0010!J\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b \u0010\"J\u0017\u0010#\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b#\u0010\u0019J%\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0016¢\u0006\u0004\b&\u0010'J \u0010(\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b(\u0010)J\u001d\u0010*\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b*\u0010\u0012J\u001d\u0010+\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b+\u0010\u0012J\u0017\u0010,\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b,\u0010\u0017J\u0017\u0010-\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b/\u0010.J\u001d\u00100\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b0\u0010\u0012J\u0018\u00101\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b1\u0010\u0017R \u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000003028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u0010\t\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107¨\u00068"}, d2 = {"Lio/github/jan/supabase/collections/AtomicMutableList;", "E", "", "", "elements", "<init>", "([Ljava/lang/Object;)V", "", "index", ContentDisposition.Parameters.Size, "Lx6/t0;", "checkElementIndex", "(II)V", "clear", "()V", "", "", "addAll", "(Ljava/util/Collection;)Z", "(ILjava/util/Collection;)Z", "element", "add", "(ILjava/lang/Object;)V", "(Ljava/lang/Object;)Z", "get", "(I)Ljava/lang/Object;", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "removeAt", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "retainAll", "removeAll", "remove", "lastIndexOf", "(Ljava/lang/Object;)I", "indexOf", "containsAll", "contains", "Lja/a;", "", "list", "Lja/a;", "getSize", "()I", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SupabaseInternal
public final class AtomicMutableList<E> implements List<E>, e {
    private final a list;

    public AtomicMutableList(E... eArr) {
        this.list = new a(t7.a.E(Arrays.copyOf(eArr, eArr.length)));
    }

    private final void checkElementIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(c.n(index, size, "index: ", ", size: "));
        }
    }

    @Override // java.util.List
    public void add(int index, E element) {
        Object objB;
        z6.c cVarN;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            cVarN.add(index, element);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> elements) {
        Object objB;
        z6.c cVarN;
        boolean zAddAll;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            zAddAll = cVarN.addAll(elements);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        Object objB;
        a aVar = this.list;
        do {
            objB = aVar.b();
        } while (!aVar.a(objB, t7.a.e(t7.a.n())));
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return ((List) this.list.b()).contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        return ((List) this.list.b()).containsAll(elements);
    }

    @Override // java.util.List
    public E get(int index) {
        return (E) ((List) this.list.b()).get(index);
    }

    public int getSize() {
        return ((List) this.list.b()).size();
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        return ((List) this.list.b()).indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return ((List) this.list.b()).isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new ArrayList((Collection) this.list.b()).iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        return ((List) this.list.b()).lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new ArrayList((Collection) this.list.b()).listIterator();
    }

    @Override // java.util.List
    public final /* bridge */ E remove(int i10) {
        return removeAt(i10);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        Object objB;
        z6.c cVarN;
        boolean zRemoveAll;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            zRemoveAll = cVarN.removeAll(elements);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return zRemoveAll;
    }

    public E removeAt(int index) {
        Object objB;
        z6.c cVarN;
        checkElementIndex(index, size());
        E e5 = get(index);
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            cVarN.remove(index);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return e5;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        Object objB;
        z6.c cVarN;
        boolean zRetainAll;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            zRetainAll = cVarN.retainAll(elements);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return zRetainAll;
    }

    @Override // java.util.List
    public E set(int index, E element) {
        Object objB;
        z6.c cVarN;
        checkElementIndex(index, size());
        E e5 = get(index);
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            cVarN.set(index, element);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return e5;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        return new ArrayList((Collection) this.list.b()).subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return o.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object element) {
        Object objB;
        z6.c cVarN;
        boolean zRemove;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            zRemove = cVarN.remove(element);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) o.b(this, tArr);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        return new ArrayList((Collection) this.list.b()).listIterator(index);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E element) {
        Object objB;
        z6.c cVarN;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            cVarN.add(element);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return true;
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        Object objB;
        z6.c cVarN;
        boolean zAddAll;
        a aVar = this.list;
        do {
            objB = aVar.b();
            cVarN = t7.a.n();
            cVarN.addAll((List) objB);
            zAddAll = cVarN.addAll(index, elements);
        } while (!aVar.a(objB, t7.a.e(cVarN)));
        return zAddAll;
    }
}

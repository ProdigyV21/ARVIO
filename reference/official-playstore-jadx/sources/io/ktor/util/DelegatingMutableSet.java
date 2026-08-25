package io.ktor.util;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.o;
import r7.l;
import s7.d;
import s7.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003BG\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\b\u0006\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\b\u0006¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\b\u0010\fJ\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u000b*\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0016¢\u0006\u0004\b\u0007\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0017\u0010\u0010J\u001d\u0010\u0018\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u0018\u0010\u0013J\u001d\u0010\u0019\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u0019\u0010\u0013J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u0010J\u001d\u0010\u001b\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\u001b\u0010\u0013J\u000f\u0010\u001c\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH\u0096\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\u001a\u0010&\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010$H\u0096\u0002¢\u0006\u0004\b&\u0010\u0010J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010*R%\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\b\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010+R%\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\b\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010+R\u001a\u0010,\u001a\u00020!8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010#¨\u0006/"}, d2 = {"Lio/ktor/util/DelegatingMutableSet;", "From", "To", "", "delegate", "Lkotlin/Function1;", "Lx6/n;", "convertTo", "convert", "<init>", "(Ljava/util/Set;Lr7/l;Lr7/l;)V", "", "(Ljava/util/Collection;)Ljava/util/Collection;", "element", "", "add", "(Ljava/lang/Object;)Z", "elements", "addAll", "(Ljava/util/Collection;)Z", "Lx6/t0;", "clear", "()V", "remove", "removeAll", "retainAll", "contains", "containsAll", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "hashCode", "()I", "", "other", "equals", "", "toString", "()Ljava/lang/String;", "Ljava/util/Set;", "Lr7/l;", ContentDisposition.Parameters.Size, "I", "getSize", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class DelegatingMutableSet<From, To> implements Set<To>, h {
    private final l<To, From> convert;
    private final l<From, To> convertTo;
    private final Set<From> delegate;
    private final int size;

    /* JADX INFO: renamed from: io.ktor.util.DelegatingMutableSet$iterator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010)\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00018\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"io/ktor/util/DelegatingMutableSet$iterator$1", "", "", "hasNext", "()Z", LinkHeader.Rel.Next, "()Ljava/lang/Object;", "Lx6/t0;", "remove", "()V", "delegateIterator", "Ljava/util/Iterator;", "getDelegateIterator", "()Ljava/util/Iterator;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<To>, d {
        private final Iterator<From> delegateIterator;
        final /* synthetic */ DelegatingMutableSet<From, To> this$0;

        public AnonymousClass1(DelegatingMutableSet<From, To> delegatingMutableSet) {
            this.this$0 = delegatingMutableSet;
            this.delegateIterator = ((DelegatingMutableSet) delegatingMutableSet).delegate.iterator();
        }

        public final Iterator<From> getDelegateIterator() {
            return this.delegateIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.delegateIterator.hasNext();
        }

        @Override // java.util.Iterator
        public To next() {
            return (To) ((DelegatingMutableSet) this.this$0).convertTo.invoke(this.delegateIterator.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.delegateIterator.remove();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DelegatingMutableSet(Set<From> set, l<? super From, ? extends To> lVar, l<? super To, ? extends From> lVar2) {
        this.delegate = set;
        this.convertTo = lVar;
        this.convert = lVar2;
        this.size = set.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(To element) {
        return this.delegate.add((From) this.convert.invoke(element));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends To> elements) {
        return this.delegate.addAll(convert(elements));
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object element) {
        return this.delegate.contains(this.convert.invoke(element));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        return this.delegate.containsAll(convert(elements));
    }

    public Collection<From> convert(Collection<? extends To> collection) {
        Collection<? extends To> collection2 = collection;
        ArrayList arrayList = new ArrayList(s.U(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(this.convert.invoke(it.next()));
        }
        return arrayList;
    }

    public Collection<To> convertTo(Collection<? extends From> collection) {
        Collection<? extends From> collection2 = collection;
        ArrayList arrayList = new ArrayList(s.U(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(this.convertTo.invoke(it.next()));
        }
        return arrayList;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object other) {
        if (other != null && (other instanceof Set)) {
            Collection<To> collectionConvertTo = convertTo(this.delegate);
            if (((Set) other).containsAll(collectionConvertTo) && collectionConvertTo.containsAll((Collection) other)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return this.size;
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        return this.delegate.hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<To> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object element) {
        return this.delegate.remove(this.convert.invoke(element));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        return this.delegate.removeAll(convert(elements));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        return this.delegate.retainAll(convert(elements));
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return o.a(this);
    }

    public String toString() {
        return convertTo(this.delegate).toString();
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) o.b(this, tArr);
    }
}

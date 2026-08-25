package io.ktor.util;

import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.p;
import s7.h;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004B\u0017\b\u0016\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0004J\u001d\u0010\u0013\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u0010J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u000bJ\u001d\u0010\u0016\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0004\b\u0016\u0010\u0010J\u000f\u0010\u0017\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\"\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Lio/ktor/util/CaseInsensitiveSet;", "", "", "<init>", "()V", "", "initial", "(Ljava/lang/Iterable;)V", "element", "", "add", "(Ljava/lang/String;)Z", "remove", "", "elements", "addAll", "(Ljava/util/Collection;)Z", "Lx6/t0;", "clear", "removeAll", "retainAll", "contains", "containsAll", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "Lio/ktor/util/CaseInsensitiveMap;", "backingMap", "Lio/ktor/util/CaseInsensitiveMap;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CaseInsensitiveSet implements Set<String>, h {
    private final CaseInsensitiveMap<Boolean> backingMap;

    public CaseInsensitiveSet() {
        this.backingMap = new CaseInsensitiveMap<>();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends String> elements) {
        Iterator<? extends String> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (add(it.next())) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.backingMap.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof String) {
            return contains((String) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        return this.backingMap.keySet().containsAll(elements);
    }

    public int getSize() {
        return this.backingMap.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.backingMap.keySet().iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        return this.backingMap.keySet().removeAll(elements);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        return this.backingMap.keySet().retainAll(elements);
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return o.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(String element) {
        if (this.backingMap.containsKey((Object) element)) {
            return false;
        }
        this.backingMap.put(element, Boolean.TRUE);
        return true;
    }

    public boolean contains(String element) {
        return this.backingMap.containsKey((Object) element);
    }

    public boolean remove(String element) {
        return p.a(this.backingMap.remove((Object) element), Boolean.TRUE);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) o.b(this, tArr);
    }

    public CaseInsensitiveSet(Iterable<String> iterable) {
        this();
        x.b0(this, iterable);
    }
}

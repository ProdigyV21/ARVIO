package t;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Iterator, Map.Entry {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21794i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f21795l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21796m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ e f21797n;

    public c(e eVar) {
        this.f21797n = eVar;
        this.f21794i = eVar.f21849m - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f21796m) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i10 = this.f21795l;
        e eVar = this.f21797n;
        return kotlin.jvm.internal.p.a(key, eVar.h(i10)) && kotlin.jvm.internal.p.a(entry.getValue(), eVar.k(this.f21795l));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (this.f21796m) {
            return this.f21797n.h(this.f21795l);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.f21796m) {
            return this.f21797n.k(this.f21795l);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f21795l < this.f21794i;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f21796m) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i10 = this.f21795l;
        e eVar = this.f21797n;
        Object objH = eVar.h(i10);
        Object objK = eVar.k(this.f21795l);
        return (objH == null ? 0 : objH.hashCode()) ^ (objK != null ? objK.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f21795l++;
        this.f21796m = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f21796m) {
            throw new IllegalStateException();
        }
        this.f21797n.i(this.f21795l);
        this.f21795l--;
        this.f21794i--;
        this.f21796m = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f21796m) {
            return this.f21797n.j(this.f21795l, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}

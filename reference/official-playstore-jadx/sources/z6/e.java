package z6;

import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.p;
import s7.g;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements Map.Entry, g.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f23221i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f23222l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23223m;

    public e(f fVar, int i10) {
        this.f23221i = fVar;
        this.f23222l = i10;
        this.f23223m = fVar.f23231r;
    }

    public final void a() {
        if (this.f23221i.f23231r != this.f23223m) {
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return p.a(entry.getKey(), getKey()) && p.a(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        a();
        return this.f23221i.f23224i[this.f23222l];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        a();
        return this.f23221i.f23225l[this.f23222l];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        a();
        f fVar = this.f23221i;
        fVar.e();
        Object[] objArr = fVar.f23225l;
        if (objArr == null) {
            int length = fVar.f23224i.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            fVar.f23225l = objArr;
        }
        int i10 = this.f23222l;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getKey());
        sb2.append('=');
        sb2.append(getValue());
        return sb2.toString();
    }
}

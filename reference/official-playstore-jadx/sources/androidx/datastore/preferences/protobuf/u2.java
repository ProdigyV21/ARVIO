package androidx.datastore.preferences.protobuf;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class u2 implements Map.Entry, Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Comparable f2536i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f2537l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r2 f2538m;

    public u2(r2 r2Var, Comparable comparable, Object obj) {
        this.f2538m = r2Var;
        this.f2536i = comparable;
        this.f2537l = obj;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f2536i.compareTo(((u2) obj).f2536i);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Comparable comparable = this.f2536i;
                if (comparable == null ? key == null : comparable.equals(key)) {
                    Object obj2 = this.f2537l;
                    Object value = entry.getValue();
                    if (obj2 == null ? value == null : obj2.equals(value)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f2536i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f2537l;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.f2536i;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f2537l;
        return (obj != null ? obj.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f2538m.c();
        Object obj2 = this.f2537l;
        this.f2537l = obj;
        return obj2;
    }

    public final String toString() {
        return this.f2536i + "=" + this.f2537l;
    }
}

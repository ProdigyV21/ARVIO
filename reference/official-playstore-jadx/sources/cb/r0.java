package cb;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 implements Map.Entry, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f7551i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f7552l;

    public r0(Object obj, Object obj2) {
        this.f7551i = obj;
        this.f7552l = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r0)) {
            return false;
        }
        r0 r0Var = (r0) obj;
        return kotlin.jvm.internal.p.a(this.f7551i, r0Var.f7551i) && kotlin.jvm.internal.p.a(this.f7552l, r0Var.f7552l);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f7551i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f7552l;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f7551i;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f7552l;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MapEntry(key=");
        sb2.append(this.f7551i);
        sb2.append(", value=");
        return androidx.compose.material3.d.p(sb2, this.f7552l, ')');
    }
}

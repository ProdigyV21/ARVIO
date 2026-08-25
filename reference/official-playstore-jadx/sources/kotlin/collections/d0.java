package kotlin.collections;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19689b;

    public d0(int i10, Object obj) {
        this.f19688a = i10;
        this.f19689b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d0)) {
            return false;
        }
        d0 d0Var = (d0) obj;
        return this.f19688a == d0Var.f19688a && kotlin.jvm.internal.p.a(this.f19689b, d0Var.f19689b);
    }

    public final int hashCode() {
        int i10 = this.f19688a * 31;
        Object obj = this.f19689b;
        return i10 + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("IndexedValue(index=");
        sb2.append(this.f19688a);
        sb2.append(", value=");
        return androidx.compose.material3.d.p(sb2, this.f19689b, ')');
    }
}

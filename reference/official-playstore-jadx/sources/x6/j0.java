package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f22587i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22588l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22589m;

    public j0(Object obj, Object obj2, Object obj3) {
        this.f22587i = obj;
        this.f22588l = obj2;
        this.f22589m = obj3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j0)) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return kotlin.jvm.internal.p.a(this.f22587i, j0Var.f22587i) && kotlin.jvm.internal.p.a(this.f22588l, j0Var.f22588l) && kotlin.jvm.internal.p.a(this.f22589m, j0Var.f22589m);
    }

    public final int hashCode() {
        Object obj = this.f22587i;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f22588l;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.f22589m;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(this.f22587i);
        sb2.append(", ");
        sb2.append(this.f22588l);
        sb2.append(", ");
        return androidx.compose.material3.d.p(sb2, this.f22589m, ')');
    }
}

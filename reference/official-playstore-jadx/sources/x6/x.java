package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f22608i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22609l;

    public x(Object obj, Object obj2) {
        this.f22608i = obj;
        this.f22609l = obj2;
    }

    public final Object a() {
        return this.f22608i;
    }

    public final Object b() {
        return this.f22609l;
    }

    public final Object c() {
        return this.f22608i;
    }

    public final Object d() {
        return this.f22609l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return kotlin.jvm.internal.p.a(this.f22608i, xVar.f22608i) && kotlin.jvm.internal.p.a(this.f22609l, xVar.f22609l);
    }

    public final int hashCode() {
        Object obj = this.f22608i;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f22609l;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(this.f22608i);
        sb2.append(", ");
        return androidx.compose.material3.d.p(sb2, this.f22609l, ')');
    }
}

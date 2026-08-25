package db;

import eb.j0;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends g0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f14924i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ab.g f14925l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f14926m;

    public u(Object obj, boolean z, ab.g gVar) {
        this.f14924i = z;
        this.f14925l = gVar;
        this.f14926m = obj.toString();
        if (gVar != null && !gVar.isInline()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    @Override // db.g0
    public final String b() {
        return this.f14926m;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || u.class != obj.getClass()) {
            return false;
        }
        u uVar = (u) obj;
        return this.f14924i == uVar.f14924i && kotlin.jvm.internal.p.a(this.f14926m, uVar.f14926m);
    }

    public final int hashCode() {
        return this.f14926m.hashCode() + ((this.f14924i ? 1231 : 1237) * 31);
    }

    @Override // db.g0
    public final String toString() {
        boolean z = this.f14924i;
        String str = this.f14926m;
        if (!z) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        j0.a(sb2, str);
        return sb2.toString();
    }
}

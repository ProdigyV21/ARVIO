package com.google.firebase.components;

/* JADX INFO: loaded from: classes4.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f14281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f14283c;

    public m(int i10, int i11, Class cls) {
        this(s.a(cls), i10, i11);
    }

    public static m a(s sVar) {
        return new m(sVar, 1, 0);
    }

    public static m b(Class cls) {
        return new m(1, 0, cls);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f14281a.equals(mVar.f14281a) && this.f14282b == mVar.f14282b && this.f14283c == mVar.f14283c;
    }

    public final int hashCode() {
        return ((((this.f14281a.hashCode() ^ 1000003) * 1000003) ^ this.f14282b) * 1000003) ^ this.f14283c;
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("Dependency{anInterface=");
        sb2.append(this.f14281a);
        sb2.append(", type=");
        int i10 = this.f14282b;
        sb2.append(i10 == 1 ? "required" : i10 == 0 ? "optional" : "set");
        sb2.append(", injection=");
        int i11 = this.f14283c;
        if (i11 == 0) {
            str = "direct";
        } else if (i11 == 1) {
            str = "provider";
        } else {
            if (i11 != 2) {
                throw new AssertionError(a0.c.i(i11, "Unsupported injection: "));
            }
            str = "deferred";
        }
        return a0.c.p(sb2, str, "}");
    }

    public m(s sVar, int i10, int i11) {
        androidx.work.impl.t.f(sVar, "Null dependency anInterface.");
        this.f14281a = sVar;
        this.f14282b = i10;
        this.f14283c = i11;
    }
}

package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t f12438c = new t(s.f12420i, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final t f12439d = new t(s.f12425p, 1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f12440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12441b;

    public t(s sVar, int i10) {
        this.f12440a = sVar;
        this.f12441b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || t.class != obj.getClass()) {
            return false;
        }
        t tVar = (t) obj;
        return this.f12440a == tVar.f12440a && this.f12441b == tVar.f12441b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f12440a);
        sb2.append(" ");
        int i10 = this.f12441b;
        sb2.append(i10 != 1 ? i10 != 2 ? "null" : "slice" : "meet");
        return sb2.toString();
    }
}

package androidx.core.graphics;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f2085e = new c(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2087b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2088c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2089d;

    public c(int i10, int i11, int i12, int i13) {
        this.f2086a = i10;
        this.f2087b = i11;
        this.f2088c = i12;
        this.f2089d = i13;
    }

    public static c a(c cVar, c cVar2) {
        return b(Math.max(cVar.f2086a, cVar2.f2086a), Math.max(cVar.f2087b, cVar2.f2087b), Math.max(cVar.f2088c, cVar2.f2088c), Math.max(cVar.f2089d, cVar2.f2089d));
    }

    public static c b(int i10, int i11, int i12, int i13) {
        return (i10 == 0 && i11 == 0 && i12 == 0 && i13 == 0) ? f2085e : new c(i10, i11, i12, i13);
    }

    public static c c(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets d() {
        return b.a(this.f2086a, this.f2087b, this.f2088c, this.f2089d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2089d == cVar.f2089d && this.f2086a == cVar.f2086a && this.f2088c == cVar.f2088c && this.f2087b == cVar.f2087b;
    }

    public final int hashCode() {
        return (((((this.f2086a * 31) + this.f2087b) * 31) + this.f2088c) * 31) + this.f2089d;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Insets{left=");
        sb2.append(this.f2086a);
        sb2.append(", top=");
        sb2.append(this.f2087b);
        sb2.append(", right=");
        sb2.append(this.f2088c);
        sb2.append(", bottom=");
        return a0.c.o(sb2, this.f2089d, '}');
    }
}

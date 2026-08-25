package gb;

import java.io.Closeable;
import java.io.EOFException;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k0 f15780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i0 f15781l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f15782m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f15783n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y f15784o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a0 f15785p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final s0 f15786q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final p0 f15787r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final p0 f15788s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final p0 f15789t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f15790u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final long f15791v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final com.google.android.gms.common.api.internal.g0 f15792w;
    public h x;

    public p0(k0 k0Var, i0 i0Var, String str, int i10, y yVar, a0 a0Var, s0 s0Var, p0 p0Var, p0 p0Var2, p0 p0Var3, long j10, long j11, com.google.android.gms.common.api.internal.g0 g0Var) {
        this.f15780i = k0Var;
        this.f15781l = i0Var;
        this.f15782m = str;
        this.f15783n = i10;
        this.f15784o = yVar;
        this.f15785p = a0Var;
        this.f15786q = s0Var;
        this.f15787r = p0Var;
        this.f15788s = p0Var2;
        this.f15789t = p0Var3;
        this.f15790u = j10;
        this.f15791v = j11;
        this.f15792w = g0Var;
    }

    public static String j(p0 p0Var, String str) {
        String strA = p0Var.f15785p.a(str);
        if (strA == null) {
            return null;
        }
        return strA;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        s0 s0Var = this.f15786q;
        if (s0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        s0Var.close();
    }

    public final h i() {
        h hVar = this.x;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = h.f15686n;
        h hVarA = kotlin.reflect.b0.A(this.f15785p);
        this.x = hVarA;
        return hVarA;
    }

    public final boolean k() {
        int i10 = this.f15783n;
        return 200 <= i10 && i10 < 300;
    }

    public final o0 l() {
        o0 o0Var = new o0();
        o0Var.f15762a = this.f15780i;
        o0Var.f15763b = this.f15781l;
        o0Var.f15764c = this.f15783n;
        o0Var.f15765d = this.f15782m;
        o0Var.f15766e = this.f15784o;
        o0Var.f15767f = this.f15785p.c();
        o0Var.f15768g = this.f15786q;
        o0Var.f15769h = this.f15787r;
        o0Var.f15770i = this.f15788s;
        o0Var.f15771j = this.f15789t;
        o0Var.k = this.f15790u;
        o0Var.f15772l = this.f15791v;
        o0Var.f15773m = this.f15792w;
        return o0Var;
    }

    public final r0 o(long j10) throws EOFException {
        s0 s0Var = this.f15786q;
        xb.i0 i0VarPeek = s0Var.o().peek();
        xb.j jVar = new xb.j();
        i0VarPeek.request(j10);
        long jMin = Math.min(j10, i0VarPeek.f22756l.f22759l);
        while (jMin > 0) {
            long j11 = i0VarPeek.read(jVar, jMin);
            if (j11 == -1) {
                throw new EOFException();
            }
            jMin -= j11;
        }
        return new r0(s0Var.l(), jVar.f22759l, jVar, 0);
    }

    public final String toString() {
        return "Response{protocol=" + this.f15781l + ", code=" + this.f15783n + ", message=" + this.f15782m + ", url=" + this.f15780i.f15730a + '}';
    }
}

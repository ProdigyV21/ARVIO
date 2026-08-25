package retrofit2;

import androidx.appcompat.app.i1;
import gb.i;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q0 f21609i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object[] f21610l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i.a f21611m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final l f21612n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile boolean f21613o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public gb.i f21614p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Throwable f21615q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f21616r;

    public y(q0 q0Var, Object[] objArr, i.a aVar, l lVar) {
        this.f21609i = q0Var;
        this.f21610l = objArr;
        this.f21611m = aVar;
        this.f21612n = lVar;
    }

    @Override // retrofit2.c
    public final void G(f fVar) {
        gb.i iVar;
        Throwable th;
        synchronized (this) {
            try {
                if (this.f21616r) {
                    throw new IllegalStateException("Already executed.");
                }
                this.f21616r = true;
                iVar = this.f21614p;
                th = this.f21615q;
                if (iVar == null && th == null) {
                    try {
                        gb.i iVarA = a();
                        this.f21614p = iVarA;
                        iVar = iVarA;
                    } catch (Throwable th2) {
                        th = th2;
                        b1.o(th);
                        this.f21615q = th;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        if (th != null) {
            fVar.onFailure(th);
            return;
        }
        if (this.f21613o) {
            iVar.cancel();
        }
        iVar.K0(new fi.iki.elonen.f((Object) this, (Object) fVar, false));
    }

    public final gb.i a() {
        androidx.tv.foundation.lazy.list.k kVar;
        gb.b0 b0VarD;
        q0 q0Var = this.f21609i;
        b1[] b1VarArr = q0Var.f21574j;
        Object[] objArr = this.f21610l;
        int length = objArr.length;
        if (length != b1VarArr.length) {
            throw new IllegalArgumentException(androidx.compose.material3.d.j(b1VarArr.length, ")", a0.c.s(length, "Argument count (", ") doesn't match expected count (")));
        }
        o0 o0Var = new o0(q0Var.f21567c, q0Var.f21566b, q0Var.f21568d, q0Var.f21569e, q0Var.f21570f, q0Var.f21571g, q0Var.f21572h, q0Var.f21573i);
        if (q0Var.k) {
            length--;
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i10 = 0; i10 < length; i10++) {
            arrayList.add(objArr[i10]);
            b1VarArr[i10].a(o0Var, objArr[i10]);
        }
        androidx.tv.foundation.lazy.list.k kVar2 = o0Var.f21533d;
        if (kVar2 != null) {
            b0VarD = kVar2.d();
        } else {
            String str = o0Var.f21532c;
            gb.b0 b0Var = o0Var.f21531b;
            b0Var.getClass();
            try {
                kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(b0Var, str);
            } catch (IllegalArgumentException unused) {
                kVar = null;
            }
            b0VarD = kVar != null ? kVar.d() : null;
            if (b0VarD == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + b0Var + ", Relative: " + o0Var.f21532c);
            }
        }
        gb.n0 l0Var = o0Var.k;
        if (l0Var == null) {
            fi.iki.elonen.f fVar = o0Var.f21539j;
            if (fVar != null) {
                l0Var = new gb.w((ArrayList) fVar.f15417l, (ArrayList) fVar.f15416i);
            } else {
                i1 i1Var = o0Var.f21538i;
                if (i1Var != null) {
                    ArrayList arrayList2 = (ArrayList) i1Var.f1061n;
                    if (arrayList2.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    l0Var = new gb.f0((xb.m) i1Var.f1059l, (gb.d0) i1Var.f1060m, ib.c.x(arrayList2));
                } else if (o0Var.f21537h) {
                    long j10 = 0;
                    ib.c.c(j10, j10, j10);
                    l0Var = new gb.m0(null, 0, new byte[0]);
                }
            }
        }
        gb.d0 d0Var = o0Var.f21536g;
        gb.z zVar = o0Var.f21535f;
        if (d0Var != null) {
            if (l0Var != null) {
                l0Var = new gb.l0(l0Var, d0Var);
            } else {
                zVar.a(HttpConnection.CONTENT_TYPE, d0Var.f15635a);
            }
        }
        gb.j0 j0Var = o0Var.f21534e;
        j0Var.f15722a = b0VarD;
        j0Var.f15724c = zVar.e().c();
        j0Var.f(o0Var.f21530a, l0Var);
        j0Var.h(s.class, new s(q0Var.f21565a, arrayList));
        return this.f21611m.a(j0Var.b());
    }

    public final gb.i b() throws IOException {
        gb.i iVar = this.f21614p;
        if (iVar != null) {
            return iVar;
        }
        Throwable th = this.f21615q;
        if (th != null) {
            if (th instanceof IOException) {
                throw ((IOException) th);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw ((Error) th);
        }
        try {
            gb.i iVarA = a();
            this.f21614p = iVarA;
            return iVarA;
        } catch (IOException | Error | RuntimeException e5) {
            b1.o(e5);
            this.f21615q = e5;
            throw e5;
        }
    }

    public final r0 c(gb.p0 p0Var) throws IOException {
        gb.s0 s0Var = p0Var.f15786q;
        gb.o0 o0VarL = p0Var.l();
        o0VarL.f15768g = new x(s0Var.l(), s0Var.k());
        gb.p0 p0VarA = o0VarL.a();
        int i10 = p0VarA.f15783n;
        if (i10 < 200 || i10 >= 300) {
            try {
                s0Var.o().D(new xb.j());
                s0Var.l();
                s0Var.k();
                if (p0VarA.k()) {
                    throw new IllegalArgumentException("rawResponse should not be successful response");
                }
                return new r0(p0VarA, null);
            } finally {
                s0Var.close();
            }
        }
        if (i10 == 204 || i10 == 205) {
            s0Var.close();
            if (p0VarA.k()) {
                return new r0(p0VarA, null);
            }
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        w wVar = new w(s0Var);
        try {
            Object objW = this.f21612n.w(wVar);
            if (p0VarA.k()) {
                return new r0(p0VarA, objW);
            }
            throw new IllegalArgumentException("rawResponse must be successful response");
        } catch (RuntimeException e5) {
            IOException iOException = wVar.f21605n;
            if (iOException == null) {
                throw e5;
            }
            throw iOException;
        }
    }

    @Override // retrofit2.c
    public final void cancel() {
        gb.i iVar;
        this.f21613o = true;
        synchronized (this) {
            iVar = this.f21614p;
        }
        if (iVar != null) {
            iVar.cancel();
        }
    }

    public final Object clone() {
        return new y(this.f21609i, this.f21610l, this.f21611m, this.f21612n);
    }

    @Override // retrofit2.c
    public final boolean k() {
        boolean z = true;
        if (this.f21613o) {
            return true;
        }
        synchronized (this) {
            try {
                gb.i iVar = this.f21614p;
                if (iVar == null || !iVar.k()) {
                    z = false;
                }
            } finally {
            }
        }
        return z;
    }

    @Override // retrofit2.c
    public final synchronized gb.k0 request() {
        try {
        } catch (IOException e5) {
            throw new RuntimeException("Unable to create request.", e5);
        }
        return b().request();
    }

    @Override // retrofit2.c
    /* JADX INFO: renamed from: clone */
    public final c mo7042clone() {
        return new y(this.f21609i, this.f21610l, this.f21611m, this.f21612n);
    }
}

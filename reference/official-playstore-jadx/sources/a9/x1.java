package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class x1 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements y1 {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final x1 f733v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final a f734w = new a(21);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f735l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f736m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f737n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f738o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k1 f739p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f740q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k1 f741r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f742s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public byte f743t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f744u;

    static {
        x1 x1Var = new x1();
        f733v = x1Var;
        x1Var.f737n = 0;
        x1Var.f738o = 0;
        k1 k1Var = k1.D;
        x1Var.f739p = k1Var;
        x1Var.f740q = 0;
        x1Var.f741r = k1Var;
        x1Var.f742s = 0;
    }

    public x1(w1 w1Var) {
        super(w1Var);
        this.f743t = (byte) -1;
        this.f744u = -1;
        this.f735l = w1Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f744u;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f736m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f737n) : 0;
        if ((this.f736m & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f738o);
        }
        if ((this.f736m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f739p);
        }
        if ((this.f736m & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, this.f741r);
        }
        if ((this.f736m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(5, this.f740q);
        }
        if ((this.f736m & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(6, this.f742s);
        }
        int size = this.f735l.size() + i() + iB;
        this.f744u = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        w1 w1Var = new w1();
        k1 k1Var = k1.D;
        w1Var.f719q = k1Var;
        w1Var.f721s = k1Var;
        w1Var.i(this);
        return w1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        w1 w1Var = new w1();
        k1 k1Var = k1.D;
        w1Var.f719q = k1Var;
        w1Var.f721s = k1Var;
        return w1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f733v;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f736m & 1) == 1) {
            jVar.m(1, this.f737n);
        }
        if ((this.f736m & 2) == 2) {
            jVar.m(2, this.f738o);
        }
        if ((this.f736m & 4) == 4) {
            jVar.o(3, this.f739p);
        }
        if ((this.f736m & 16) == 16) {
            jVar.o(4, this.f741r);
        }
        if ((this.f736m & 8) == 8) {
            jVar.m(5, this.f740q);
        }
        if ((this.f736m & 32) == 32) {
            jVar.m(6, this.f742s);
        }
        fVar.f0(200, jVar);
        jVar.r(this.f735l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f743t;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i10 = this.f736m;
        if ((i10 & 2) != 2) {
            this.f743t = (byte) 0;
            return false;
        }
        if ((i10 & 4) == 4 && !this.f739p.isInitialized()) {
            this.f743t = (byte) 0;
            return false;
        }
        if ((this.f736m & 16) == 16 && !this.f741r.isInitialized()) {
            this.f743t = (byte) 0;
            return false;
        }
        if (h()) {
            this.f743t = (byte) 1;
            return true;
        }
        this.f743t = (byte) 0;
        return false;
    }

    public x1() {
        this.f743t = (byte) -1;
        this.f744u = -1;
        this.f735l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public x1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f743t = (byte) -1;
        this.f744u = -1;
        boolean z = false;
        this.f737n = 0;
        this.f738o = 0;
        k1 k1Var = k1.D;
        this.f739p = k1Var;
        this.f740q = 0;
        this.f741r = k1Var;
        this.f742s = 0;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f736m |= 1;
                            this.f737n = iVar.k();
                        } else if (iN != 16) {
                            j1 j1VarP = null;
                            if (iN == 26) {
                                if ((this.f736m & 4) == 4) {
                                    k1 k1Var2 = this.f739p;
                                    k1Var2.getClass();
                                    j1VarP = k1.p(k1Var2);
                                }
                                k1 k1Var3 = (k1) iVar.g(k1.E, lVar);
                                this.f739p = k1Var3;
                                if (j1VarP != null) {
                                    j1VarP.j(k1Var3);
                                    this.f739p = j1VarP.h();
                                }
                                this.f736m |= 4;
                            } else if (iN == 34) {
                                if ((this.f736m & 16) == 16) {
                                    k1 k1Var4 = this.f741r;
                                    k1Var4.getClass();
                                    j1VarP = k1.p(k1Var4);
                                }
                                k1 k1Var5 = (k1) iVar.g(k1.E, lVar);
                                this.f741r = k1Var5;
                                if (j1VarP != null) {
                                    j1VarP.j(k1Var5);
                                    this.f741r = j1VarP.h();
                                }
                                this.f736m |= 16;
                            } else if (iN == 40) {
                                this.f736m |= 8;
                                this.f740q = iVar.k();
                            } else if (iN != 48) {
                                if (!m(iVar, jVarJ, lVar, iN)) {
                                }
                            } else {
                                this.f736m |= 32;
                                this.f742s = iVar.k();
                            }
                        } else {
                            this.f736m |= 2;
                            this.f738o = iVar.k();
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f735l = fVar.k();
                        throw th2;
                    }
                    this.f735l = fVar.k();
                    l();
                    throw th;
                }
            } catch (InvalidProtocolBufferException e5) {
                e5.f19759i = this;
                throw e5;
            } catch (IOException e6) {
                InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                invalidProtocolBufferException.f19759i = this;
                throw invalidProtocolBufferException;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f735l = fVar.k();
            throw th3;
        }
        this.f735l = fVar.k();
        l();
    }
}

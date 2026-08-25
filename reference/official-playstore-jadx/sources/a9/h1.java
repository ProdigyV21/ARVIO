package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class h1 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements i1 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final h1 f471r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a f472s = new a(17);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f473i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f474l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g1 f475m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k1 f476n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f477o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f478p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f479q;

    static {
        h1 h1Var = new h1();
        f471r = h1Var;
        h1Var.f475m = g1.INV;
        h1Var.f476n = k1.D;
        h1Var.f477o = 0;
    }

    public h1() {
        this.f478p = (byte) -1;
        this.f479q = -1;
        this.f473i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f479q;
        if (i10 != -1) {
            return i10;
        }
        int iA = (this.f474l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.a(1, this.f475m.f459i) : 0;
        if ((this.f474l & 2) == 2) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, this.f476n);
        }
        if ((this.f474l & 4) == 4) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.b(3, this.f477o);
        }
        int size = this.f473i.size() + iA;
        this.f479q = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        f1 f1VarH = f1.h();
        f1VarH.i(this);
        return f1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return f1.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f474l & 1) == 1) {
            jVar.l(1, this.f475m.f459i);
        }
        if ((this.f474l & 2) == 2) {
            jVar.o(2, this.f476n);
        }
        if ((this.f474l & 4) == 4) {
            jVar.m(3, this.f477o);
        }
        jVar.r(this.f473i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f478p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f474l & 2) != 2 || this.f476n.isInitialized()) {
            this.f478p = (byte) 1;
            return true;
        }
        this.f478p = (byte) 0;
        return false;
    }

    public h1(f1 f1Var) {
        this.f478p = (byte) -1;
        this.f479q = -1;
        this.f473i = f1Var.f19815i;
    }

    public h1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f478p = (byte) -1;
        this.f479q = -1;
        g1 g1Var = g1.INV;
        this.f475m = g1Var;
        this.f476n = k1.D;
        boolean z = false;
        this.f477o = 0;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        j1 j1VarP = null;
                        g1 g1Var2 = null;
                        if (iN == 8) {
                            int iK = iVar.k();
                            if (iK == 0) {
                                g1Var2 = g1.IN;
                            } else if (iK == 1) {
                                g1Var2 = g1.OUT;
                            } else if (iK == 2) {
                                g1Var2 = g1Var;
                            } else if (iK == 3) {
                                g1Var2 = g1.STAR;
                            }
                            if (g1Var2 == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK);
                            } else {
                                this.f474l |= 1;
                                this.f475m = g1Var2;
                            }
                        } else if (iN == 18) {
                            if ((this.f474l & 2) == 2) {
                                k1 k1Var = this.f476n;
                                k1Var.getClass();
                                j1VarP = k1.p(k1Var);
                            }
                            k1 k1Var2 = (k1) iVar.g(k1.E, lVar);
                            this.f476n = k1Var2;
                            if (j1VarP != null) {
                                j1VarP.j(k1Var2);
                                this.f476n = j1VarP.h();
                            }
                            this.f474l |= 2;
                        } else if (iN != 24) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            this.f474l |= 4;
                            this.f477o = iVar.k();
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f473i = fVar.k();
                        throw th2;
                    }
                    this.f473i = fVar.k();
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
            this.f473i = fVar.k();
            throw th3;
        }
        this.f473i = fVar.k();
    }
}

package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements z0 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final y0 f755r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a f756s = new a(14);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f757i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f758l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f759m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f760n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public x0 f761o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f762p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f763q;

    static {
        y0 y0Var = new y0();
        f755r = y0Var;
        y0Var.f759m = -1;
        y0Var.f760n = 0;
        y0Var.f761o = x0.PACKAGE;
    }

    public y0() {
        this.f762p = (byte) -1;
        this.f763q = -1;
        this.f757i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f763q;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f758l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f759m) : 0;
        if ((this.f758l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f760n);
        }
        if ((this.f758l & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(3, this.f761o.f732i);
        }
        int size = this.f757i.size() + iB;
        this.f763q = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        w0 w0VarH = w0.h();
        w0VarH.i(this);
        return w0VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return w0.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f758l & 1) == 1) {
            jVar.m(1, this.f759m);
        }
        if ((this.f758l & 2) == 2) {
            jVar.m(2, this.f760n);
        }
        if ((this.f758l & 4) == 4) {
            jVar.l(3, this.f761o.f732i);
        }
        jVar.r(this.f757i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f762p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f758l & 2) == 2) {
            this.f762p = (byte) 1;
            return true;
        }
        this.f762p = (byte) 0;
        return false;
    }

    public y0(w0 w0Var) {
        this.f762p = (byte) -1;
        this.f763q = -1;
        this.f757i = w0Var.f19815i;
    }

    public y0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        x0 x0Var;
        this.f762p = (byte) -1;
        this.f763q = -1;
        this.f759m = -1;
        boolean z = false;
        this.f760n = 0;
        x0 x0Var2 = x0.PACKAGE;
        this.f761o = x0Var2;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f758l |= 1;
                            this.f759m = iVar.k();
                        } else if (iN == 16) {
                            this.f758l |= 2;
                            this.f760n = iVar.k();
                        } else if (iN != 24) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            int iK = iVar.k();
                            if (iK == 0) {
                                x0Var = x0.CLASS;
                            } else if (iK != 1) {
                                x0Var = iK != 2 ? null : x0.LOCAL;
                            } else {
                                x0Var = x0Var2;
                            }
                            if (x0Var == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK);
                            } else {
                                this.f758l |= 4;
                                this.f761o = x0Var;
                            }
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e5) {
                    e5.f19759i = this;
                    throw e5;
                } catch (IOException e6) {
                    InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e6.getMessage());
                    invalidProtocolBufferException.f19759i = this;
                    throw invalidProtocolBufferException;
                }
            } catch (Throwable th) {
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f757i = fVar.k();
                    throw th2;
                }
                this.f757i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f757i = fVar.k();
            throw th3;
        }
        this.f757i = fVar.k();
    }
}

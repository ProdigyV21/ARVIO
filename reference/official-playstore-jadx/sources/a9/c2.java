package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class c2 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements d2 {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final c2 f362u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final a f363v = new a(22);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f364i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f365l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f366m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f367n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a2 f368o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f369p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f370q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public b2 f371r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public byte f372s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f373t;

    static {
        c2 c2Var = new c2();
        f362u = c2Var;
        c2Var.f366m = 0;
        c2Var.f367n = 0;
        c2Var.f368o = a2.ERROR;
        c2Var.f369p = 0;
        c2Var.f370q = 0;
        c2Var.f371r = b2.LANGUAGE_VERSION;
    }

    public c2() {
        this.f372s = (byte) -1;
        this.f373t = -1;
        this.f364i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f373t;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f365l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f366m) : 0;
        if ((this.f365l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f367n);
        }
        if ((this.f365l & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(3, this.f368o.f332i);
        }
        if ((this.f365l & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(4, this.f369p);
        }
        if ((this.f365l & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(5, this.f370q);
        }
        if ((this.f365l & 32) == 32) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(6, this.f371r.f347i);
        }
        int size = this.f364i.size() + iB;
        this.f373t = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        z1 z1VarH = z1.h();
        z1VarH.i(this);
        return z1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return z1.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f365l & 1) == 1) {
            jVar.m(1, this.f366m);
        }
        if ((this.f365l & 2) == 2) {
            jVar.m(2, this.f367n);
        }
        if ((this.f365l & 4) == 4) {
            jVar.l(3, this.f368o.f332i);
        }
        if ((this.f365l & 8) == 8) {
            jVar.m(4, this.f369p);
        }
        if ((this.f365l & 16) == 16) {
            jVar.m(5, this.f370q);
        }
        if ((this.f365l & 32) == 32) {
            jVar.l(6, this.f371r.f347i);
        }
        jVar.r(this.f364i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f372s == 1) {
            return true;
        }
        this.f372s = (byte) 1;
        return true;
    }

    public c2(z1 z1Var) {
        this.f372s = (byte) -1;
        this.f373t = -1;
        this.f364i = z1Var.f19815i;
    }

    public c2(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        this.f372s = (byte) -1;
        this.f373t = -1;
        boolean z = false;
        this.f366m = 0;
        this.f367n = 0;
        a2 a2Var = a2.ERROR;
        this.f368o = a2Var;
        this.f369p = 0;
        this.f370q = 0;
        b2 b2Var = b2.LANGUAGE_VERSION;
        this.f371r = b2Var;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f365l |= 1;
                            this.f366m = iVar.k();
                        } else if (iN != 16) {
                            b2 b2Var2 = null;
                            a2 a2Var2 = null;
                            if (iN == 24) {
                                int iK = iVar.k();
                                if (iK == 0) {
                                    a2Var2 = a2.WARNING;
                                } else if (iK == 1) {
                                    a2Var2 = a2Var;
                                } else if (iK == 2) {
                                    a2Var2 = a2.HIDDEN;
                                }
                                if (a2Var2 == null) {
                                    jVarJ.v(iN);
                                    jVarJ.v(iK);
                                } else {
                                    this.f365l |= 4;
                                    this.f368o = a2Var2;
                                }
                            } else if (iN == 32) {
                                this.f365l |= 8;
                                this.f369p = iVar.k();
                            } else if (iN == 40) {
                                this.f365l |= 16;
                                this.f370q = iVar.k();
                            } else if (iN != 48) {
                                if (!iVar.q(iN, jVarJ)) {
                                }
                            } else {
                                int iK2 = iVar.k();
                                if (iK2 == 0) {
                                    b2Var2 = b2Var;
                                } else if (iK2 == 1) {
                                    b2Var2 = b2.COMPILER_VERSION;
                                } else if (iK2 == 2) {
                                    b2Var2 = b2.API_VERSION;
                                }
                                if (b2Var2 == null) {
                                    jVarJ.v(iN);
                                    jVarJ.v(iK2);
                                } else {
                                    this.f365l |= 32;
                                    this.f371r = b2Var2;
                                }
                            }
                        } else {
                            this.f365l |= 2;
                            this.f367n = iVar.k();
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
                    this.f364i = fVar.k();
                    throw th2;
                }
                this.f364i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f364i = fVar.k();
            throw th3;
        }
        this.f364i = fVar.k();
    }
}

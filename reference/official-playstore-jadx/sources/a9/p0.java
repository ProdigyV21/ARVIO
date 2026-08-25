package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements q0 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final p0 f614t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final a f615u = new a(11);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f616l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f617m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d1 f618n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a1 f619o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public n0 f620p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f621q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public byte f622r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f623s;

    static {
        p0 p0Var = new p0();
        f614t = p0Var;
        p0Var.f618n = d1.f396o;
        p0Var.f619o = a1.f322o;
        p0Var.f620p = n0.f594u;
        p0Var.f621q = Collections.EMPTY_LIST;
    }

    public p0(o0 o0Var) {
        super(o0Var);
        this.f622r = (byte) -1;
        this.f623s = -1;
        this.f616l = o0Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f623s;
        if (i10 != -1) {
            return i10;
        }
        int iD = (this.f617m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, this.f618n) : 0;
        if ((this.f617m & 2) == 2) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, this.f619o);
        }
        if ((this.f617m & 4) == 4) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f620p);
        }
        for (int i11 = 0; i11 < this.f621q.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f621q.get(i11));
        }
        int size = this.f616l.size() + i() + iD;
        this.f623s = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        o0 o0VarI = o0.i();
        o0VarI.j(this);
        return o0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return o0.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f614t;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f617m & 1) == 1) {
            jVar.o(1, this.f618n);
        }
        if ((this.f617m & 2) == 2) {
            jVar.o(2, this.f619o);
        }
        if ((this.f617m & 4) == 4) {
            jVar.o(3, this.f620p);
        }
        for (int i10 = 0; i10 < this.f621q.size(); i10++) {
            jVar.o(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f621q.get(i10));
        }
        fVar.f0(200, jVar);
        jVar.r(this.f616l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f622r;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f617m & 2) == 2 && !this.f619o.isInitialized()) {
            this.f622r = (byte) 0;
            return false;
        }
        if ((this.f617m & 4) == 4 && !this.f620p.isInitialized()) {
            this.f622r = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f621q.size(); i10++) {
            if (!((n) this.f621q.get(i10)).isInitialized()) {
                this.f622r = (byte) 0;
                return false;
            }
        }
        if (h()) {
            this.f622r = (byte) 1;
            return true;
        }
        this.f622r = (byte) 0;
        return false;
    }

    public p0() {
        this.f622r = (byte) -1;
        this.f623s = -1;
        this.f616l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public p0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f622r = (byte) -1;
        this.f623s = -1;
        this.f618n = d1.f396o;
        this.f619o = a1.f322o;
        this.f620p = n0.f594u;
        this.f621q = Collections.EMPTY_LIST;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        char c10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        m0 m0VarI = null;
                        c1 c1Var = null;
                        v0 v0Var = null;
                        if (iN == 10) {
                            if ((this.f617m & 1) == 1) {
                                d1 d1Var = this.f618n;
                                d1Var.getClass();
                                c1Var = new c1();
                                c1Var.f361m = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
                                c1Var.h(d1Var);
                            }
                            d1 d1Var2 = (d1) iVar.g(d1.f397p, lVar);
                            this.f618n = d1Var2;
                            if (c1Var != null) {
                                c1Var.h(d1Var2);
                                this.f618n = c1Var.g();
                            }
                            this.f617m |= 1;
                        } else if (iN == 18) {
                            if ((this.f617m & 2) == 2) {
                                a1 a1Var = this.f619o;
                                a1Var.getClass();
                                v0Var = new v0();
                                v0Var.f706m = Collections.EMPTY_LIST;
                                v0Var.h(a1Var);
                            }
                            a1 a1Var2 = (a1) iVar.g(a1.f323p, lVar);
                            this.f619o = a1Var2;
                            if (v0Var != null) {
                                v0Var.h(a1Var2);
                                this.f619o = v0Var.g();
                            }
                            this.f617m |= 2;
                        } else if (iN == 26) {
                            if ((this.f617m & 4) == 4) {
                                n0 n0Var = this.f620p;
                                n0Var.getClass();
                                m0VarI = m0.i();
                                m0VarI.j(n0Var);
                            }
                            n0 n0Var2 = (n0) iVar.g(n0.f595v, lVar);
                            this.f620p = n0Var2;
                            if (m0VarI != null) {
                                m0VarI.j(n0Var2);
                                this.f620p = m0VarI.h();
                            }
                            this.f617m |= 4;
                        } else if (iN != 34) {
                            if (!m(iVar, jVarJ, lVar, iN)) {
                            }
                        } else {
                            int i10 = (c10 == true ? 1 : 0) & '\b';
                            c10 = c10;
                            if (i10 != 8) {
                                this.f621q = new ArrayList();
                                c10 = '\b';
                            }
                            this.f621q.add(iVar.g(n.U, lVar));
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (((c10 == true ? 1 : 0) & '\b') == 8) {
                        this.f621q = Collections.unmodifiableList(this.f621q);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f616l = fVar.k();
                        throw th2;
                    }
                    this.f616l = fVar.k();
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
        if (((c10 == true ? 1 : 0) & '\b') == 8) {
            this.f621q = Collections.unmodifiableList(this.f621q);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f616l = fVar.k();
            throw th3;
        }
        this.f616l = fVar.k();
        l();
    }
}

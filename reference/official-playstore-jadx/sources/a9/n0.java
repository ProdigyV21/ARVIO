package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements r0 {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final n0 f594u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final a f595v = new a(10);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f598n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f599o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f600p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public u1 f601q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public f2 f602r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public byte f603s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f604t;

    static {
        n0 n0Var = new n0();
        f594u = n0Var;
        List list = Collections.EMPTY_LIST;
        n0Var.f598n = list;
        n0Var.f599o = list;
        n0Var.f600p = list;
        n0Var.f601q = u1.f692q;
        n0Var.f602r = f2.f440o;
    }

    public n0(m0 m0Var) {
        super(m0Var);
        this.f603s = (byte) -1;
        this.f604t = -1;
        this.f596l = m0Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f604t;
        if (i10 != -1) {
            return i10;
        }
        int iD = 0;
        for (int i11 = 0; i11 < this.f598n.size(); i11++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f598n.get(i11));
        }
        for (int i12 = 0; i12 < this.f599o.size(); i12++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f599o.get(i12));
        }
        for (int i13 = 0; i13 < this.f600p.size(); i13++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f600p.get(i13));
        }
        if ((this.f597m & 1) == 1) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(30, this.f601q);
        }
        if ((this.f597m & 2) == 2) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(32, this.f602r);
        }
        int size = this.f596l.size() + i() + iD;
        this.f604t = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        m0 m0VarI = m0.i();
        m0VarI.j(this);
        return m0VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return m0.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f594u;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        for (int i10 = 0; i10 < this.f598n.size(); i10++) {
            jVar.o(3, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f598n.get(i10));
        }
        for (int i11 = 0; i11 < this.f599o.size(); i11++) {
            jVar.o(4, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f599o.get(i11));
        }
        for (int i12 = 0; i12 < this.f600p.size(); i12++) {
            jVar.o(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f600p.get(i12));
        }
        if ((this.f597m & 1) == 1) {
            jVar.o(30, this.f601q);
        }
        if ((this.f597m & 2) == 2) {
            jVar.o(32, this.f602r);
        }
        fVar.f0(200, jVar);
        jVar.r(this.f596l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f603s;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f598n.size(); i10++) {
            if (!((i0) this.f598n.get(i10)).isInitialized()) {
                this.f603s = (byte) 0;
                return false;
            }
        }
        for (int i11 = 0; i11 < this.f599o.size(); i11++) {
            if (!((t0) this.f599o.get(i11)).isInitialized()) {
                this.f603s = (byte) 0;
                return false;
            }
        }
        for (int i12 = 0; i12 < this.f600p.size(); i12++) {
            if (!((m1) this.f600p.get(i12)).isInitialized()) {
                this.f603s = (byte) 0;
                return false;
            }
        }
        if ((this.f597m & 1) == 1 && !this.f601q.isInitialized()) {
            this.f603s = (byte) 0;
            return false;
        }
        if (h()) {
            this.f603s = (byte) 1;
            return true;
        }
        this.f603s = (byte) 0;
        return false;
    }

    public n0() {
        this.f603s = (byte) -1;
        this.f604t = -1;
        this.f596l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public n0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f603s = (byte) -1;
        this.f604t = -1;
        List list = Collections.EMPTY_LIST;
        this.f598n = list;
        this.f599o = list;
        this.f600p = list;
        this.f601q = u1.f692q;
        this.f602r = f2.f440o;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        int i10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 26) {
                            int i11 = (i10 == true ? 1 : 0) & 1;
                            i10 = i10;
                            if (i11 != 1) {
                                this.f598n = new ArrayList();
                                i10 = (i10 == true ? 1 : 0) | 1;
                            }
                            this.f598n.add(iVar.g(i0.F, lVar));
                        } else if (iN == 34) {
                            int i12 = (i10 == true ? 1 : 0) & 2;
                            i10 = i10;
                            if (i12 != 2) {
                                this.f599o = new ArrayList();
                                i10 = (i10 == true ? 1 : 0) | 2;
                            }
                            this.f599o.add(iVar.g(t0.F, lVar));
                        } else if (iN != 42) {
                            e2 e2Var = null;
                            t1 t1VarH = null;
                            if (iN == 242) {
                                if ((this.f597m & 1) == 1) {
                                    u1 u1Var = this.f601q;
                                    u1Var.getClass();
                                    t1VarH = u1.h(u1Var);
                                }
                                u1 u1Var2 = (u1) iVar.g(u1.f693r, lVar);
                                this.f601q = u1Var2;
                                if (t1VarH != null) {
                                    t1VarH.i(u1Var2);
                                    this.f601q = t1VarH.g();
                                }
                                this.f597m |= 1;
                            } else if (iN != 258) {
                                if (!m(iVar, jVarJ, lVar, iN)) {
                                }
                            } else {
                                if ((this.f597m & 2) == 2) {
                                    f2 f2Var = this.f602r;
                                    f2Var.getClass();
                                    e2Var = new e2();
                                    e2Var.f422m = Collections.EMPTY_LIST;
                                    e2Var.h(f2Var);
                                }
                                f2 f2Var2 = (f2) iVar.g(f2.f441p, lVar);
                                this.f602r = f2Var2;
                                if (e2Var != null) {
                                    e2Var.h(f2Var2);
                                    this.f602r = e2Var.g();
                                }
                                this.f597m |= 2;
                            }
                        } else {
                            int i13 = (i10 == true ? 1 : 0) & 4;
                            i10 = i10;
                            if (i13 != 4) {
                                this.f600p = new ArrayList();
                                i10 = (i10 == true ? 1 : 0) | 4;
                            }
                            this.f600p.add(iVar.g(m1.z, lVar));
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (((i10 == true ? 1 : 0) & 1) == 1) {
                        this.f598n = Collections.unmodifiableList(this.f598n);
                    }
                    if (((i10 == true ? 1 : 0) & 2) == 2) {
                        this.f599o = Collections.unmodifiableList(this.f599o);
                    }
                    if (((i10 == true ? 1 : 0) & 4) == 4) {
                        this.f600p = Collections.unmodifiableList(this.f600p);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f596l = fVar.k();
                        throw th2;
                    }
                    this.f596l = fVar.k();
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
        if (((i10 == true ? 1 : 0) & 1) == 1) {
            this.f598n = Collections.unmodifiableList(this.f598n);
        }
        if (((i10 == true ? 1 : 0) & 2) == 2) {
            this.f599o = Collections.unmodifiableList(this.f599o);
        }
        if (((i10 == true ? 1 : 0) & 4) == 4) {
            this.f600p = Collections.unmodifiableList(this.f600p);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f596l = fVar.k();
            throw th3;
        }
        this.f596l = fVar.k();
        l();
    }
}

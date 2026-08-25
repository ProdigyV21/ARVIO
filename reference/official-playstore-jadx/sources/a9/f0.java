package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements g0 {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final f0 f423v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final a f424w = new a(8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f425i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f426l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f427m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f428n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public e0 f429o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k1 f430p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f431q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f432r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f433s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public byte f434t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f435u;

    static {
        f0 f0Var = new f0();
        f423v = f0Var;
        f0Var.f427m = 0;
        f0Var.f428n = 0;
        f0Var.f429o = e0.TRUE;
        f0Var.f430p = k1.D;
        f0Var.f431q = 0;
        List list = Collections.EMPTY_LIST;
        f0Var.f432r = list;
        f0Var.f433s = list;
    }

    public f0() {
        this.f434t = (byte) -1;
        this.f435u = -1;
        this.f425i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f435u;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f426l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f427m) : 0;
        if ((this.f426l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f428n);
        }
        if ((this.f426l & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(3, this.f429o.f420i);
        }
        if ((this.f426l & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, this.f430p);
        }
        if ((this.f426l & 16) == 16) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(5, this.f431q);
        }
        for (int i11 = 0; i11 < this.f432r.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f432r.get(i11));
        }
        for (int i12 = 0; i12 < this.f433s.size(); i12++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(7, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f433s.get(i12));
        }
        int size = this.f425i.size() + iB;
        this.f435u = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        d0 d0VarH = d0.h();
        d0VarH.i(this);
        return d0VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return d0.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f426l & 1) == 1) {
            jVar.m(1, this.f427m);
        }
        if ((this.f426l & 2) == 2) {
            jVar.m(2, this.f428n);
        }
        if ((this.f426l & 4) == 4) {
            jVar.l(3, this.f429o.f420i);
        }
        if ((this.f426l & 8) == 8) {
            jVar.o(4, this.f430p);
        }
        if ((this.f426l & 16) == 16) {
            jVar.m(5, this.f431q);
        }
        for (int i10 = 0; i10 < this.f432r.size(); i10++) {
            jVar.o(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f432r.get(i10));
        }
        for (int i11 = 0; i11 < this.f433s.size(); i11++) {
            jVar.o(7, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f433s.get(i11));
        }
        jVar.r(this.f425i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f434t;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f426l & 8) == 8 && !this.f430p.isInitialized()) {
            this.f434t = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f432r.size(); i10++) {
            if (!((f0) this.f432r.get(i10)).isInitialized()) {
                this.f434t = (byte) 0;
                return false;
            }
        }
        for (int i11 = 0; i11 < this.f433s.size(); i11++) {
            if (!((f0) this.f433s.get(i11)).isInitialized()) {
                this.f434t = (byte) 0;
                return false;
            }
        }
        this.f434t = (byte) 1;
        return true;
    }

    public f0(d0 d0Var) {
        this.f434t = (byte) -1;
        this.f435u = -1;
        this.f425i = d0Var.f19815i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        e0 e0Var;
        this.f434t = (byte) -1;
        this.f435u = -1;
        boolean z = false;
        this.f427m = 0;
        this.f428n = 0;
        e0 e0Var2 = e0.TRUE;
        this.f429o = e0Var2;
        this.f430p = k1.D;
        this.f431q = 0;
        List list = Collections.EMPTY_LIST;
        this.f432r = list;
        this.f433s = list;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        int i10 = 0;
        while (!z) {
            try {
                try {
                    try {
                        int iN = iVar.n();
                        if (iN != 0) {
                            if (iN == 8) {
                                this.f426l |= 1;
                                this.f427m = iVar.k();
                            } else if (iN != 16) {
                                Object objP = null;
                                if (iN == 24) {
                                    int iK = iVar.k();
                                    if (iK != 0) {
                                        if (iK == 1) {
                                            objP = e0.FALSE;
                                        } else if (iK == 2) {
                                            objP = e0.NULL;
                                        }
                                        e0Var = objP;
                                    } else {
                                        e0Var = e0Var2;
                                    }
                                    if (e0Var == 0) {
                                        jVarJ.v(iN);
                                        jVarJ.v(iK);
                                    } else {
                                        this.f426l |= 4;
                                        this.f429o = e0Var;
                                    }
                                } else if (iN == 34) {
                                    if ((this.f426l & 8) == 8) {
                                        k1 k1Var = this.f430p;
                                        k1Var.getClass();
                                        objP = k1.p(k1Var);
                                    }
                                    j1 j1Var = objP;
                                    k1 k1Var2 = (k1) iVar.g(k1.E, lVar);
                                    this.f430p = k1Var2;
                                    if (j1Var != 0) {
                                        j1Var.j(k1Var2);
                                        this.f430p = j1Var.h();
                                    }
                                    this.f426l |= 8;
                                } else if (iN != 40) {
                                    a aVar = f424w;
                                    if (iN == 50) {
                                        if ((i10 & 32) != 32) {
                                            this.f432r = new ArrayList();
                                            i10 |= 32;
                                        }
                                        this.f432r.add(iVar.g(aVar, lVar));
                                    } else if (iN != 58) {
                                        if (!iVar.q(iN, jVarJ)) {
                                        }
                                    } else {
                                        if ((i10 & 64) != 64) {
                                            this.f433s = new ArrayList();
                                            i10 |= 64;
                                        }
                                        this.f433s.add(iVar.g(aVar, lVar));
                                    }
                                } else {
                                    this.f426l |= 16;
                                    this.f431q = iVar.k();
                                }
                            } else {
                                this.f426l |= 2;
                                this.f428n = iVar.k();
                            }
                        }
                        z = true;
                    } catch (IOException e5) {
                        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(e5.getMessage());
                        invalidProtocolBufferException.f19759i = this;
                        throw invalidProtocolBufferException;
                    }
                } catch (InvalidProtocolBufferException e6) {
                    e6.f19759i = this;
                    throw e6;
                }
            } catch (Throwable th) {
                if ((i10 & 32) == 32) {
                    this.f432r = Collections.unmodifiableList(this.f432r);
                }
                if ((i10 & 64) == 64) {
                    this.f433s = Collections.unmodifiableList(this.f433s);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f425i = fVar.k();
                    throw th2;
                }
                this.f425i = fVar.k();
                throw th;
            }
        }
        if ((i10 & 32) == 32) {
            this.f432r = Collections.unmodifiableList(this.f432r);
        }
        if ((i10 & 64) == 64) {
            this.f433s = Collections.unmodifiableList(this.f433s);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f425i = fVar.k();
            throw th3;
        }
        this.f425i = fVar.k();
    }
}

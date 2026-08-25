package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class r1 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements s1 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final r1 f645w;
    public static final a x = new a(19);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f646l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f647m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f648n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f649o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f650p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public q1 f651q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f652r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f653s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f654t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte f655u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f656v;

    static {
        r1 r1Var = new r1();
        f645w = r1Var;
        r1Var.f648n = 0;
        r1Var.f649o = 0;
        r1Var.f650p = false;
        r1Var.f651q = q1.INV;
        List list = Collections.EMPTY_LIST;
        r1Var.f652r = list;
        r1Var.f653s = list;
    }

    public r1(p1 p1Var) {
        super(p1Var);
        this.f654t = -1;
        this.f655u = (byte) -1;
        this.f656v = -1;
        this.f646l = p1Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f656v;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f647m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f648n) : 0;
        if ((this.f647m & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f649o);
        }
        if ((this.f647m & 4) == 4) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.h(3) + 1;
        }
        if ((this.f647m & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(4, this.f651q.f644i);
        }
        for (int i11 = 0; i11 < this.f652r.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f652r.get(i11));
        }
        int iC = 0;
        for (int i12 = 0; i12 < this.f653s.size(); i12++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f653s.get(i12)).intValue());
        }
        int iC2 = iB + iC;
        if (!this.f653s.isEmpty()) {
            iC2 = iC2 + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.f654t = iC;
        int size = this.f646l.size() + i() + iC2;
        this.f656v = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        p1 p1VarI = p1.i();
        p1VarI.j(this);
        return p1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return p1.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f645w;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) throws IOException {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f647m & 1) == 1) {
            jVar.m(1, this.f648n);
        }
        if ((this.f647m & 2) == 2) {
            jVar.m(2, this.f649o);
        }
        if ((this.f647m & 4) == 4) {
            boolean z = this.f650p;
            jVar.x(3, 0);
            jVar.q(z ? 1 : 0);
        }
        if ((this.f647m & 8) == 8) {
            jVar.l(4, this.f651q.f644i);
        }
        for (int i10 = 0; i10 < this.f652r.size(); i10++) {
            jVar.o(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f652r.get(i10));
        }
        if (this.f653s.size() > 0) {
            jVar.v(50);
            jVar.v(this.f654t);
        }
        for (int i11 = 0; i11 < this.f653s.size(); i11++) {
            jVar.n(((Integer) this.f653s.get(i11)).intValue());
        }
        fVar.f0(1000, jVar);
        jVar.r(this.f646l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f655u;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i10 = this.f647m;
        if ((i10 & 1) != 1) {
            this.f655u = (byte) 0;
            return false;
        }
        if ((i10 & 2) != 2) {
            this.f655u = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f652r.size(); i11++) {
            if (!((k1) this.f652r.get(i11)).isInitialized()) {
                this.f655u = (byte) 0;
                return false;
            }
        }
        if (h()) {
            this.f655u = (byte) 1;
            return true;
        }
        this.f655u = (byte) 0;
        return false;
    }

    public r1() {
        this.f654t = -1;
        this.f655u = (byte) -1;
        this.f656v = -1;
        this.f646l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public r1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        q1 q1Var;
        this.f654t = -1;
        this.f655u = (byte) -1;
        this.f656v = -1;
        this.f648n = 0;
        this.f649o = 0;
        this.f650p = false;
        q1 q1Var2 = q1.INV;
        this.f651q = q1Var2;
        List list = Collections.EMPTY_LIST;
        this.f652r = list;
        this.f653s = list;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        int i10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f647m |= 1;
                            this.f648n = iVar.k();
                        } else if (iN == 16) {
                            this.f647m |= 2;
                            this.f649o = iVar.k();
                        } else if (iN == 24) {
                            this.f647m |= 4;
                            this.f650p = iVar.l() != 0;
                        } else if (iN == 32) {
                            int iK = iVar.k();
                            if (iK == 0) {
                                q1Var = q1.IN;
                            } else if (iK != 1) {
                                q1Var = iK != 2 ? null : q1Var2;
                            } else {
                                q1Var = q1.OUT;
                            }
                            if (q1Var == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK);
                            } else {
                                this.f647m |= 8;
                                this.f651q = q1Var;
                            }
                        } else if (iN == 42) {
                            if ((i10 & 16) != 16) {
                                this.f652r = new ArrayList();
                                i10 |= 16;
                            }
                            this.f652r.add(iVar.g(k1.E, lVar));
                        } else if (iN == 48) {
                            if ((i10 & 32) != 32) {
                                this.f653s = new ArrayList();
                                i10 |= 32;
                            }
                            this.f653s.add(Integer.valueOf(iVar.k()));
                        } else if (iN != 50) {
                            if (!m(iVar, jVarJ, lVar, iN)) {
                            }
                        } else {
                            int iD = iVar.d(iVar.k());
                            if ((i10 & 32) != 32 && iVar.b() > 0) {
                                this.f653s = new ArrayList();
                                i10 |= 32;
                            }
                            while (iVar.b() > 0) {
                                this.f653s.add(Integer.valueOf(iVar.k()));
                            }
                            iVar.c(iD);
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i10 & 16) == 16) {
                        this.f652r = Collections.unmodifiableList(this.f652r);
                    }
                    if ((i10 & 32) == 32) {
                        this.f653s = Collections.unmodifiableList(this.f653s);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f646l = fVar.k();
                        throw th2;
                    }
                    this.f646l = fVar.k();
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
        if ((i10 & 16) == 16) {
            this.f652r = Collections.unmodifiableList(this.f652r);
        }
        if ((i10 & 32) == 32) {
            this.f653s = Collections.unmodifiableList(this.f653s);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f646l = fVar.k();
            throw th3;
        }
        this.f646l = fVar.k();
        l();
    }
}

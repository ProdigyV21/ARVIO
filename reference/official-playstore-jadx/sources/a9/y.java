package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends kotlin.reflect.jvm.internal.impl.protobuf.u implements z {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final y f745s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a f746t = new a(6);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f747i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f748l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public w f749m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f750n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public f0 f751o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public x f752p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte f753q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f754r;

    static {
        y yVar = new y();
        f745s = yVar;
        yVar.f749m = w.RETURNS_CONSTANT;
        yVar.f750n = Collections.EMPTY_LIST;
        yVar.f751o = f0.f423v;
        yVar.f752p = x.AT_MOST_ONCE;
    }

    public y() {
        this.f753q = (byte) -1;
        this.f754r = -1;
        this.f747i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f754r;
        if (i10 != -1) {
            return i10;
        }
        int iA = (this.f748l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.a(1, this.f749m.f711i) : 0;
        for (int i11 = 0; i11 < this.f750n.size(); i11++) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f750n.get(i11));
        }
        if ((this.f748l & 2) == 2) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f751o);
        }
        if ((this.f748l & 4) == 4) {
            iA += kotlin.reflect.jvm.internal.impl.protobuf.j.a(4, this.f752p.f727i);
        }
        int size = this.f747i.size() + iA;
        this.f754r = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        v vVarH = v.h();
        vVarH.i(this);
        return vVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return v.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f748l & 1) == 1) {
            jVar.l(1, this.f749m.f711i);
        }
        for (int i10 = 0; i10 < this.f750n.size(); i10++) {
            jVar.o(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f750n.get(i10));
        }
        if ((this.f748l & 2) == 2) {
            jVar.o(3, this.f751o);
        }
        if ((this.f748l & 4) == 4) {
            jVar.l(4, this.f752p.f727i);
        }
        jVar.r(this.f747i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f753q;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f750n.size(); i10++) {
            if (!((f0) this.f750n.get(i10)).isInitialized()) {
                this.f753q = (byte) 0;
                return false;
            }
        }
        if ((this.f748l & 2) != 2 || this.f751o.isInitialized()) {
            this.f753q = (byte) 1;
            return true;
        }
        this.f753q = (byte) 0;
        return false;
    }

    public y(v vVar) {
        this.f753q = (byte) -1;
        this.f754r = -1;
        this.f747i = vVar.f19815i;
    }

    public y(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f753q = (byte) -1;
        this.f754r = -1;
        w wVar = w.RETURNS_CONSTANT;
        this.f749m = wVar;
        this.f750n = Collections.EMPTY_LIST;
        this.f751o = f0.f423v;
        x xVar = x.AT_MOST_ONCE;
        this.f752p = xVar;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        char c10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        x xVar2 = null;
                        w wVar2 = null;
                        d0 d0VarH = null;
                        if (iN == 8) {
                            int iK = iVar.k();
                            if (iK == 0) {
                                wVar2 = wVar;
                            } else if (iK == 1) {
                                wVar2 = w.CALLS;
                            } else if (iK == 2) {
                                wVar2 = w.RETURNS_NOT_NULL;
                            }
                            if (wVar2 == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK);
                            } else {
                                this.f748l |= 1;
                                this.f749m = wVar2;
                            }
                        } else if (iN == 18) {
                            int i10 = (c10 == true ? 1 : 0) & 2;
                            c10 = c10;
                            if (i10 != 2) {
                                this.f750n = new ArrayList();
                                c10 = 2;
                            }
                            this.f750n.add(iVar.g(f0.f424w, lVar));
                        } else if (iN == 26) {
                            if ((this.f748l & 2) == 2) {
                                f0 f0Var = this.f751o;
                                f0Var.getClass();
                                d0VarH = d0.h();
                                d0VarH.i(f0Var);
                            }
                            f0 f0Var2 = (f0) iVar.g(f0.f424w, lVar);
                            this.f751o = f0Var2;
                            if (d0VarH != null) {
                                d0VarH.i(f0Var2);
                                this.f751o = d0VarH.g();
                            }
                            this.f748l |= 2;
                        } else if (iN != 32) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            int iK2 = iVar.k();
                            if (iK2 == 0) {
                                xVar2 = xVar;
                            } else if (iK2 == 1) {
                                xVar2 = x.EXACTLY_ONCE;
                            } else if (iK2 == 2) {
                                xVar2 = x.AT_LEAST_ONCE;
                            }
                            if (xVar2 == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK2);
                            } else {
                                this.f748l |= 4;
                                this.f752p = xVar2;
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
                if (((c10 == true ? 1 : 0) & 2) == 2) {
                    this.f750n = Collections.unmodifiableList(this.f750n);
                }
                try {
                    jVarJ.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f747i = fVar.k();
                    throw th2;
                }
                this.f747i = fVar.k();
                throw th;
            }
        }
        if (((c10 == true ? 1 : 0) & 2) == 2) {
            this.f750n = Collections.unmodifiableList(this.f750n);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f747i = fVar.k();
            throw th3;
        }
        this.f747i = fVar.k();
    }
}

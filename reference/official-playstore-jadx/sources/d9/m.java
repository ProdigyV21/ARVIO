package d9;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.b0;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends u implements n {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final m f14842w;
    public static final a9.a x = new a9.a(28);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f14843i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14844l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14845m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14846n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f14847o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public l f14848p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f14849q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14850r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f14851s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f14852t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte f14853u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f14854v;

    static {
        m mVar = new m();
        f14842w = mVar;
        mVar.f14845m = 1;
        mVar.f14846n = 0;
        mVar.f14847o = "";
        mVar.f14848p = l.NONE;
        List list = Collections.EMPTY_LIST;
        mVar.f14849q = list;
        mVar.f14851s = list;
    }

    public m() {
        this.f14850r = -1;
        this.f14852t = -1;
        this.f14853u = (byte) -1;
        this.f14854v = -1;
        this.f14843i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        kotlin.reflect.jvm.internal.impl.protobuf.g b0Var;
        int i10 = this.f14854v;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f14844l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f14845m) : 0;
        if ((this.f14844l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f14846n);
        }
        if ((this.f14844l & 8) == 8) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.a(3, this.f14848p.f14841i);
        }
        int iC = 0;
        for (int i11 = 0; i11 < this.f14849q.size(); i11++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f14849q.get(i11)).intValue());
        }
        int iC2 = iB + iC;
        if (!this.f14849q.isEmpty()) {
            iC2 = iC2 + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.f14850r = iC;
        int iC3 = 0;
        for (int i12 = 0; i12 < this.f14851s.size(); i12++) {
            iC3 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f14851s.get(i12)).intValue());
        }
        int size = iC2 + iC3;
        if (!this.f14851s.isEmpty()) {
            size = size + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC3);
        }
        this.f14852t = iC3;
        if ((this.f14844l & 4) == 4) {
            Object obj = this.f14847o;
            if (obj instanceof String) {
                try {
                    b0Var = new b0(((String) obj).getBytes("UTF-8"));
                    this.f14847o = b0Var;
                } catch (UnsupportedEncodingException e5) {
                    throw new RuntimeException("UTF-8 not supported?", e5);
                }
            } else {
                b0Var = (kotlin.reflect.jvm.internal.impl.protobuf.g) obj;
            }
            size += b0Var.size() + kotlin.reflect.jvm.internal.impl.protobuf.j.f(b0Var.size()) + kotlin.reflect.jvm.internal.impl.protobuf.j.h(6);
        }
        int size2 = this.f14843i.size() + size;
        this.f14854v = size2;
        return size2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        k kVarH = k.h();
        kVarH.i(this);
        return kVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return k.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        kotlin.reflect.jvm.internal.impl.protobuf.g b0Var;
        a();
        if ((this.f14844l & 1) == 1) {
            jVar.m(1, this.f14845m);
        }
        if ((this.f14844l & 2) == 2) {
            jVar.m(2, this.f14846n);
        }
        if ((this.f14844l & 8) == 8) {
            jVar.l(3, this.f14848p.f14841i);
        }
        if (this.f14849q.size() > 0) {
            jVar.v(34);
            jVar.v(this.f14850r);
        }
        for (int i10 = 0; i10 < this.f14849q.size(); i10++) {
            jVar.n(((Integer) this.f14849q.get(i10)).intValue());
        }
        if (this.f14851s.size() > 0) {
            jVar.v(42);
            jVar.v(this.f14852t);
        }
        for (int i11 = 0; i11 < this.f14851s.size(); i11++) {
            jVar.n(((Integer) this.f14851s.get(i11)).intValue());
        }
        if ((this.f14844l & 4) == 4) {
            Object obj = this.f14847o;
            if (obj instanceof String) {
                try {
                    b0Var = new b0(((String) obj).getBytes("UTF-8"));
                    this.f14847o = b0Var;
                } catch (UnsupportedEncodingException e5) {
                    throw new RuntimeException("UTF-8 not supported?", e5);
                }
            } else {
                b0Var = (kotlin.reflect.jvm.internal.impl.protobuf.g) obj;
            }
            jVar.x(6, 2);
            jVar.v(b0Var.size());
            jVar.r(b0Var);
        }
        jVar.r(this.f14843i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f14853u == 1) {
            return true;
        }
        this.f14853u = (byte) 1;
        return true;
    }

    public m(k kVar) {
        this.f14850r = -1;
        this.f14852t = -1;
        this.f14853u = (byte) -1;
        this.f14854v = -1;
        this.f14843i = kVar.f19815i;
    }

    public m(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        l lVar;
        this.f14850r = -1;
        this.f14852t = -1;
        this.f14853u = (byte) -1;
        this.f14854v = -1;
        this.f14845m = 1;
        boolean z = false;
        this.f14846n = 0;
        this.f14847o = "";
        l lVar2 = l.NONE;
        this.f14848p = lVar2;
        List list = Collections.EMPTY_LIST;
        this.f14849q = list;
        this.f14851s = list;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        int i10 = 0;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f14844l |= 1;
                            this.f14845m = iVar.k();
                        } else if (iN == 16) {
                            this.f14844l |= 2;
                            this.f14846n = iVar.k();
                        } else if (iN == 24) {
                            int iK = iVar.k();
                            if (iK == 0) {
                                lVar = lVar2;
                            } else if (iK != 1) {
                                lVar = iK != 2 ? null : l.DESC_TO_CLASS_ID;
                            } else {
                                lVar = l.INTERNAL_TO_CLASS_ID;
                            }
                            if (lVar == null) {
                                jVarJ.v(iN);
                                jVarJ.v(iK);
                            } else {
                                this.f14844l |= 8;
                                this.f14848p = lVar;
                            }
                        } else if (iN == 32) {
                            if ((i10 & 16) != 16) {
                                this.f14849q = new ArrayList();
                                i10 |= 16;
                            }
                            this.f14849q.add(Integer.valueOf(iVar.k()));
                        } else if (iN == 34) {
                            int iD = iVar.d(iVar.k());
                            if ((i10 & 16) != 16 && iVar.b() > 0) {
                                this.f14849q = new ArrayList();
                                i10 |= 16;
                            }
                            while (iVar.b() > 0) {
                                this.f14849q.add(Integer.valueOf(iVar.k()));
                            }
                            iVar.c(iD);
                        } else if (iN == 40) {
                            if ((i10 & 32) != 32) {
                                this.f14851s = new ArrayList();
                                i10 |= 32;
                            }
                            this.f14851s.add(Integer.valueOf(iVar.k()));
                        } else if (iN == 42) {
                            int iD2 = iVar.d(iVar.k());
                            if ((i10 & 32) != 32 && iVar.b() > 0) {
                                this.f14851s = new ArrayList();
                                i10 |= 32;
                            }
                            while (iVar.b() > 0) {
                                this.f14851s.add(Integer.valueOf(iVar.k()));
                            }
                            iVar.c(iD2);
                        } else if (iN != 50) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            b0 b0VarE = iVar.e();
                            this.f14844l |= 4;
                            this.f14847o = b0VarE;
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i10 & 16) == 16) {
                        this.f14849q = Collections.unmodifiableList(this.f14849q);
                    }
                    if ((i10 & 32) == 32) {
                        this.f14851s = Collections.unmodifiableList(this.f14851s);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f14843i = fVar.k();
                        throw th2;
                    }
                    this.f14843i = fVar.k();
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
            this.f14849q = Collections.unmodifiableList(this.f14849q);
        }
        if ((i10 & 32) == 32) {
            this.f14851s = Collections.unmodifiableList(this.f14851s);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f14843i = fVar.k();
            throw th3;
        }
        this.f14843i = fVar.k();
    }
}

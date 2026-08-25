package a9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends kotlin.reflect.jvm.internal.impl.protobuf.q implements r {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final q f631s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a f632t = new a(4);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f633l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f634m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f635n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f636o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f637p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte f638q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f639r;

    static {
        q qVar = new q();
        f631s = qVar;
        qVar.f635n = 6;
        List list = Collections.EMPTY_LIST;
        qVar.f636o = list;
        qVar.f637p = list;
    }

    public q(p pVar) {
        super(pVar);
        this.f638q = (byte) -1;
        this.f639r = -1;
        this.f633l = pVar.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f639r;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f634m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f635n) : 0;
        for (int i11 = 0; i11 < this.f636o.size(); i11++) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f636o.get(i11));
        }
        int iC = 0;
        for (int i12 = 0; i12 < this.f637p.size(); i12++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f637p.get(i12)).intValue());
        }
        int size = this.f633l.size() + i() + (this.f637p.size() * 2) + iB + iC;
        this.f639r = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        p pVarI = p.i();
        pVarI.j(this);
        return pVarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return p.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f631s;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f634m & 1) == 1) {
            jVar.m(1, this.f635n);
        }
        for (int i10 = 0; i10 < this.f636o.size(); i10++) {
            jVar.o(2, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f636o.get(i10));
        }
        for (int i11 = 0; i11 < this.f637p.size(); i11++) {
            jVar.m(31, ((Integer) this.f637p.get(i11)).intValue());
        }
        fVar.f0(19000, jVar);
        jVar.r(this.f633l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f638q;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i10 = 0; i10 < this.f636o.size(); i10++) {
            if (!((x1) this.f636o.get(i10)).isInitialized()) {
                this.f638q = (byte) 0;
                return false;
            }
        }
        if (h()) {
            this.f638q = (byte) 1;
            return true;
        }
        this.f638q = (byte) 0;
        return false;
    }

    public q() {
        this.f638q = (byte) -1;
        this.f639r = -1;
        this.f633l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public q(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f638q = (byte) -1;
        this.f639r = -1;
        this.f635n = 6;
        List list = Collections.EMPTY_LIST;
        this.f636o = list;
        this.f637p = list;
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
                            this.f634m |= 1;
                            this.f635n = iVar.k();
                        } else if (iN == 18) {
                            if ((i10 & 2) != 2) {
                                this.f636o = new ArrayList();
                                i10 |= 2;
                            }
                            this.f636o.add(iVar.g(x1.f734w, lVar));
                        } else if (iN == 248) {
                            if ((i10 & 4) != 4) {
                                this.f637p = new ArrayList();
                                i10 |= 4;
                            }
                            this.f637p.add(Integer.valueOf(iVar.k()));
                        } else if (iN != 250) {
                            if (!m(iVar, jVarJ, lVar, iN)) {
                            }
                        } else {
                            int iD = iVar.d(iVar.k());
                            if ((i10 & 4) != 4 && iVar.b() > 0) {
                                this.f637p = new ArrayList();
                                i10 |= 4;
                            }
                            while (iVar.b() > 0) {
                                this.f637p.add(Integer.valueOf(iVar.k()));
                            }
                            iVar.c(iD);
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i10 & 2) == 2) {
                        this.f636o = Collections.unmodifiableList(this.f636o);
                    }
                    if ((i10 & 4) == 4) {
                        this.f637p = Collections.unmodifiableList(this.f637p);
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f633l = fVar.k();
                        throw th2;
                    }
                    this.f633l = fVar.k();
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
        if ((i10 & 2) == 2) {
            this.f636o = Collections.unmodifiableList(this.f636o);
        }
        if ((i10 & 4) == 4) {
            this.f637p = Collections.unmodifiableList(this.f637p);
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f633l = fVar.k();
            throw th3;
        }
        this.f633l = fVar.k();
        l();
    }
}

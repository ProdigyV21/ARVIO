package d9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends u implements i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final h f14816t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final a9.a f14817u = new a9.a(26);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f14818i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14819l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f14820m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f14821n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public e f14822o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e f14823p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public e f14824q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public byte f14825r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f14826s;

    static {
        h hVar = new h();
        f14816t = hVar;
        hVar.f14820m = b.f14791q;
        e eVar = e.f14802q;
        hVar.f14821n = eVar;
        hVar.f14822o = eVar;
        hVar.f14823p = eVar;
        hVar.f14824q = eVar;
    }

    public h() {
        this.f14825r = (byte) -1;
        this.f14826s = -1;
        this.f14818i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f14826s;
        if (i10 != -1) {
            return i10;
        }
        int iD = (this.f14819l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.d(1, this.f14820m) : 0;
        if ((this.f14819l & 2) == 2) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, this.f14821n);
        }
        if ((this.f14819l & 4) == 4) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(3, this.f14822o);
        }
        if ((this.f14819l & 8) == 8) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(4, this.f14823p);
        }
        if ((this.f14819l & 16) == 16) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, this.f14824q);
        }
        int size = this.f14818i.size() + iD;
        this.f14826s = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        g gVarH = g.h();
        gVarH.i(this);
        return gVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return g.h();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f14819l & 1) == 1) {
            jVar.o(1, this.f14820m);
        }
        if ((this.f14819l & 2) == 2) {
            jVar.o(2, this.f14821n);
        }
        if ((this.f14819l & 4) == 4) {
            jVar.o(3, this.f14822o);
        }
        if ((this.f14819l & 8) == 8) {
            jVar.o(4, this.f14823p);
        }
        if ((this.f14819l & 16) == 16) {
            jVar.o(5, this.f14824q);
        }
        jVar.r(this.f14818i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f14825r == 1) {
            return true;
        }
        this.f14825r = (byte) 1;
        return true;
    }

    public h(g gVar) {
        this.f14825r = (byte) -1;
        this.f14826s = -1;
        this.f14818i = gVar.f19815i;
    }

    public h(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f14825r = (byte) -1;
        this.f14826s = -1;
        this.f14820m = b.f14791q;
        e eVar = e.f14802q;
        this.f14821n = eVar;
        this.f14822o = eVar;
        this.f14823p = eVar;
        this.f14824q = eVar;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        d dVarH = null;
                        a aVar = null;
                        d dVarH2 = null;
                        d dVarH3 = null;
                        d dVarH4 = null;
                        if (iN == 10) {
                            if ((this.f14819l & 1) == 1) {
                                b bVar = this.f14820m;
                                bVar.getClass();
                                aVar = new a();
                                aVar.h(bVar);
                            }
                            b bVar2 = (b) iVar.g(b.f14792r, lVar);
                            this.f14820m = bVar2;
                            if (aVar != null) {
                                aVar.h(bVar2);
                                this.f14820m = aVar.g();
                            }
                            this.f14819l |= 1;
                        } else if (iN == 18) {
                            if ((this.f14819l & 2) == 2) {
                                e eVar2 = this.f14821n;
                                eVar2.getClass();
                                dVarH2 = e.h(eVar2);
                            }
                            e eVar3 = (e) iVar.g(e.f14803r, lVar);
                            this.f14821n = eVar3;
                            if (dVarH2 != null) {
                                dVarH2.h(eVar3);
                                this.f14821n = dVarH2.g();
                            }
                            this.f14819l |= 2;
                        } else if (iN == 26) {
                            if ((this.f14819l & 4) == 4) {
                                e eVar4 = this.f14822o;
                                eVar4.getClass();
                                dVarH3 = e.h(eVar4);
                            }
                            e eVar5 = (e) iVar.g(e.f14803r, lVar);
                            this.f14822o = eVar5;
                            if (dVarH3 != null) {
                                dVarH3.h(eVar5);
                                this.f14822o = dVarH3.g();
                            }
                            this.f14819l |= 4;
                        } else if (iN == 34) {
                            if ((this.f14819l & 8) == 8) {
                                e eVar6 = this.f14823p;
                                eVar6.getClass();
                                dVarH4 = e.h(eVar6);
                            }
                            e eVar7 = (e) iVar.g(e.f14803r, lVar);
                            this.f14823p = eVar7;
                            if (dVarH4 != null) {
                                dVarH4.h(eVar7);
                                this.f14823p = dVarH4.g();
                            }
                            this.f14819l |= 8;
                        } else if (iN != 42) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            if ((this.f14819l & 16) == 16) {
                                e eVar8 = this.f14824q;
                                eVar8.getClass();
                                dVarH = e.h(eVar8);
                            }
                            e eVar9 = (e) iVar.g(e.f14803r, lVar);
                            this.f14824q = eVar9;
                            if (dVarH != null) {
                                dVarH.h(eVar9);
                                this.f14824q = dVarH.g();
                            }
                            this.f14819l |= 16;
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
                    this.f14818i = fVar.k();
                    throw th2;
                }
                this.f14818i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f14818i = fVar.k();
            throw th3;
        }
        this.f14818i = fVar.k();
    }
}

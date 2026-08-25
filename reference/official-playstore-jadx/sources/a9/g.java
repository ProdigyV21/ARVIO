package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends kotlin.reflect.jvm.internal.impl.protobuf.u implements h {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final g f446q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f447r = new a(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f448i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f449l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f450m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f451n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f452o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f453p;

    static {
        g gVar = new g();
        f446q = gVar;
        gVar.f450m = 0;
        gVar.f451n = e.z;
    }

    public g() {
        this.f452o = (byte) -1;
        this.f453p = -1;
        this.f448i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f453p;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f449l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f450m) : 0;
        if ((this.f449l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.d(2, this.f451n);
        }
        int size = this.f448i.size() + iB;
        this.f453p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        b bVar = new b();
        bVar.f335n = e.z;
        bVar.h(this);
        return bVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        b bVar = new b();
        bVar.f335n = e.z;
        return bVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f449l & 1) == 1) {
            jVar.m(1, this.f450m);
        }
        if ((this.f449l & 2) == 2) {
            jVar.o(2, this.f451n);
        }
        jVar.r(this.f448i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f452o;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i10 = this.f449l;
        if ((i10 & 1) != 1) {
            this.f452o = (byte) 0;
            return false;
        }
        if ((i10 & 2) != 2) {
            this.f452o = (byte) 0;
            return false;
        }
        if (this.f451n.isInitialized()) {
            this.f452o = (byte) 1;
            return true;
        }
        this.f452o = (byte) 0;
        return false;
    }

    public g(b bVar) {
        this.f452o = (byte) -1;
        this.f453p = -1;
        this.f448i = bVar.f19815i;
    }

    public g(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        c cVarH;
        this.f452o = (byte) -1;
        this.f453p = -1;
        boolean z = false;
        this.f450m = 0;
        this.f451n = e.z;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    try {
                        int iN = iVar.n();
                        if (iN != 0) {
                            if (iN == 8) {
                                this.f449l |= 1;
                                this.f450m = iVar.k();
                            } else if (iN != 18) {
                                if (!iVar.q(iN, jVarJ)) {
                                }
                            } else {
                                if ((this.f449l & 2) == 2) {
                                    e eVar = this.f451n;
                                    eVar.getClass();
                                    cVarH = c.h();
                                    cVarH.i(eVar);
                                } else {
                                    cVarH = null;
                                }
                                e eVar2 = (e) iVar.g(e.A, lVar);
                                this.f451n = eVar2;
                                if (cVarH != null) {
                                    cVarH.i(eVar2);
                                    this.f451n = cVarH.g();
                                }
                                this.f449l |= 2;
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e5) {
                        e5.f19759i = this;
                        throw e5;
                    }
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
                    this.f448i = fVar.k();
                    throw th2;
                }
                this.f448i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f448i = fVar.k();
            throw th3;
        }
        this.f448i = fVar.k();
    }
}

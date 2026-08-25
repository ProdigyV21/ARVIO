package d9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends u implements c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final b f14791q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a9.a f14792r = new a9.a(24);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f14793i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14794l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14795m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14796n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f14797o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14798p;

    static {
        b bVar = new b();
        f14791q = bVar;
        bVar.f14795m = 0;
        bVar.f14796n = 0;
    }

    public b() {
        this.f14797o = (byte) -1;
        this.f14798p = -1;
        this.f14793i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f14798p;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f14794l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f14795m) : 0;
        if ((this.f14794l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f14796n);
        }
        int size = this.f14793i.size() + iB;
        this.f14798p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        a aVar = new a();
        aVar.h(this);
        return aVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return new a();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f14794l & 1) == 1) {
            jVar.m(1, this.f14795m);
        }
        if ((this.f14794l & 2) == 2) {
            jVar.m(2, this.f14796n);
        }
        jVar.r(this.f14793i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f14797o == 1) {
            return true;
        }
        this.f14797o = (byte) 1;
        return true;
    }

    public b(a aVar) {
        this.f14797o = (byte) -1;
        this.f14798p = -1;
        this.f14793i = aVar.f19815i;
    }

    public b(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        this.f14797o = (byte) -1;
        this.f14798p = -1;
        boolean z = false;
        this.f14795m = 0;
        this.f14796n = 0;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f14794l |= 1;
                            this.f14795m = iVar.k();
                        } else if (iN != 16) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            this.f14794l |= 2;
                            this.f14796n = iVar.k();
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
                    this.f14793i = fVar.k();
                    throw th2;
                }
                this.f14793i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f14793i = fVar.k();
            throw th3;
        }
        this.f14793i = fVar.k();
    }
}

package d9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends u implements f {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f14802q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a9.a f14803r = new a9.a(25);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f14804i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14805l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14806m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14807n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f14808o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14809p;

    static {
        e eVar = new e();
        f14802q = eVar;
        eVar.f14806m = 0;
        eVar.f14807n = 0;
    }

    public e() {
        this.f14808o = (byte) -1;
        this.f14809p = -1;
        this.f14804i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public static d h(e eVar) {
        d dVar = new d();
        dVar.h(eVar);
        return dVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f14809p;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f14805l & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f14806m) : 0;
        if ((this.f14805l & 2) == 2) {
            iB += kotlin.reflect.jvm.internal.impl.protobuf.j.b(2, this.f14807n);
        }
        int size = this.f14804i.size() + iB;
        this.f14809p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        return h(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return new d();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        if ((this.f14805l & 1) == 1) {
            jVar.m(1, this.f14806m);
        }
        if ((this.f14805l & 2) == 2) {
            jVar.m(2, this.f14807n);
        }
        jVar.r(this.f14804i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f14808o == 1) {
            return true;
        }
        this.f14808o = (byte) 1;
        return true;
    }

    public e(d dVar) {
        this.f14808o = (byte) -1;
        this.f14809p = -1;
        this.f14804i = dVar.f19815i;
    }

    public e(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        this.f14808o = (byte) -1;
        this.f14809p = -1;
        boolean z = false;
        this.f14806m = 0;
        this.f14807n = 0;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f14805l |= 1;
                            this.f14806m = iVar.k();
                        } else if (iN != 16) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            this.f14805l |= 2;
                            this.f14807n = iVar.k();
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
                    this.f14804i = fVar.k();
                    throw th2;
                }
                this.f14804i = fVar.k();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f14804i = fVar.k();
            throw th3;
        }
        this.f14804i = fVar.k();
    }
}

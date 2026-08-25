package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends kotlin.reflect.jvm.internal.impl.protobuf.q implements c0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final b0 f336q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f337r = new a(7);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f338l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f339m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f340n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public byte f341o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f342p;

    static {
        b0 b0Var = new b0();
        f336q = b0Var;
        b0Var.f340n = 0;
    }

    public b0(a0 a0Var) {
        super(a0Var);
        this.f341o = (byte) -1;
        this.f342p = -1;
        this.f338l = a0Var.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f342p;
        if (i10 != -1) {
            return i10;
        }
        int size = this.f338l.size() + i() + ((this.f339m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f340n) : 0);
        this.f342p = size;
        return size;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        a0 a0Var = new a0();
        a0Var.h(this);
        return a0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return new a0();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return f336q;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f339m & 1) == 1) {
            jVar.m(1, this.f340n);
        }
        fVar.f0(200, jVar);
        jVar.r(this.f338l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.f341o;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if (h()) {
            this.f341o = (byte) 1;
            return true;
        }
        this.f341o = (byte) 0;
        return false;
    }

    public b0() {
        this.f341o = (byte) -1;
        this.f342p = -1;
        this.f338l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    public b0(kotlin.reflect.jvm.internal.impl.protobuf.i iVar, kotlin.reflect.jvm.internal.impl.protobuf.l lVar) {
        this.f341o = (byte) -1;
        this.f342p = -1;
        boolean z = false;
        this.f340n = 0;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        while (!z) {
            try {
                try {
                    try {
                        int iN = iVar.n();
                        if (iN != 0) {
                            if (iN != 8) {
                                if (!m(iVar, jVarJ, lVar, iN)) {
                                }
                            } else {
                                this.f339m |= 1;
                                this.f340n = iVar.k();
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
                    this.f338l = fVar.k();
                    throw th2;
                }
                this.f338l = fVar.k();
                l();
                throw th;
            }
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f338l = fVar.k();
            throw th3;
        }
        this.f338l = fVar.k();
        l();
    }
}

package a9;

import java.io.IOException;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class d1 extends kotlin.reflect.jvm.internal.impl.protobuf.u implements e1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final d1 f396o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f397p = new a(15);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f398i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public kotlin.reflect.jvm.internal.impl.protobuf.z f399l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte f400m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f401n;

    static {
        d1 d1Var = new d1();
        f396o = d1Var;
        d1Var.f399l = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
    }

    public d1() {
        this.f400m = (byte) -1;
        this.f401n = -1;
        this.f398i = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.f401n;
        if (i10 != -1) {
            return i10;
        }
        int size = 0;
        for (int i11 = 0; i11 < this.f399l.size(); i11++) {
            kotlin.reflect.jvm.internal.impl.protobuf.g gVarO = this.f399l.O(i11);
            size += gVarO.size() + kotlin.reflect.jvm.internal.impl.protobuf.j.f(gVarO.size());
        }
        int size2 = this.f398i.size() + this.f399l.size() + size;
        this.f401n = size2;
        return size2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        c1 c1Var = new c1();
        c1Var.f361m = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
        c1Var.h(this);
        return c1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        c1 c1Var = new c1();
        c1Var.f361m = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
        return c1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        for (int i10 = 0; i10 < this.f399l.size(); i10++) {
            kotlin.reflect.jvm.internal.impl.protobuf.g gVarO = this.f399l.O(i10);
            jVar.x(1, 2);
            jVar.v(gVarO.size());
            jVar.r(gVarO);
        }
        jVar.r(this.f398i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        if (this.f400m == 1) {
            return true;
        }
        this.f400m = (byte) 1;
        return true;
    }

    public d1(c1 c1Var) {
        this.f400m = (byte) -1;
        this.f401n = -1;
        this.f398i = c1Var.f19815i;
    }

    public d1(kotlin.reflect.jvm.internal.impl.protobuf.i iVar) {
        this.f400m = (byte) -1;
        this.f401n = -1;
        this.f399l = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
        kotlin.reflect.jvm.internal.impl.protobuf.f fVar = new kotlin.reflect.jvm.internal.impl.protobuf.f();
        kotlin.reflect.jvm.internal.impl.protobuf.j jVarJ = kotlin.reflect.jvm.internal.impl.protobuf.j.j(fVar, 1);
        boolean z = false;
        boolean z5 = false;
        while (!z) {
            try {
                try {
                    int iN = iVar.n();
                    if (iN != 0) {
                        if (iN != 10) {
                            if (!iVar.q(iN, jVarJ)) {
                            }
                        } else {
                            kotlin.reflect.jvm.internal.impl.protobuf.b0 b0VarE = iVar.e();
                            if (!z5) {
                                this.f399l = new kotlin.reflect.jvm.internal.impl.protobuf.y();
                                z5 = true;
                            }
                            this.f399l.u0(b0VarE);
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if (z5) {
                        this.f399l = this.f399l.l();
                    }
                    try {
                        jVarJ.i();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f398i = fVar.k();
                        throw th2;
                    }
                    this.f398i = fVar.k();
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
        if (z5) {
            this.f399l = this.f399l.l();
        }
        try {
            jVarJ.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f398i = fVar.k();
            throw th3;
        }
        this.f398i = fVar.k();
    }
}

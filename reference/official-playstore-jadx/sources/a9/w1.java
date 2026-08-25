package a9;

import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class w1 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements y1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f716n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f717o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f718p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k1 f719q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f720r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public k1 f721s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f722t;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        x1 x1VarH = h();
        if (x1VarH.isInitialized()) {
            return x1VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        w1 w1Var = new w1();
        k1 k1Var = k1.D;
        w1Var.f719q = k1Var;
        w1Var.f721s = k1Var;
        w1Var.i(h());
        return w1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((x1) uVar);
        return this;
    }

    public final x1 h() {
        x1 x1Var = new x1(this);
        int i10 = this.f716n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        x1Var.f737n = this.f717o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        x1Var.f738o = this.f718p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        x1Var.f739p = this.f719q;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        x1Var.f740q = this.f720r;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        x1Var.f741r = this.f721s;
        if ((i10 & 32) == 32) {
            i11 |= 32;
        }
        x1Var.f742s = this.f722t;
        x1Var.f736m = i11;
        return x1Var;
    }

    public final void i(x1 x1Var) {
        k1 k1Var;
        k1 k1Var2;
        if (x1Var == x1.f733v) {
            return;
        }
        int i10 = x1Var.f736m;
        if ((i10 & 1) == 1) {
            int i11 = x1Var.f737n;
            this.f716n = 1 | this.f716n;
            this.f717o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = x1Var.f738o;
            this.f716n = 2 | this.f716n;
            this.f718p = i12;
        }
        if ((i10 & 4) == 4) {
            k1 k1Var3 = x1Var.f739p;
            if ((this.f716n & 4) != 4 || (k1Var2 = this.f719q) == k1.D) {
                this.f719q = k1Var3;
            } else {
                j1 j1VarP = k1.p(k1Var2);
                j1VarP.j(k1Var3);
                this.f719q = j1VarP.h();
            }
            this.f716n |= 4;
        }
        int i13 = x1Var.f736m;
        if ((i13 & 8) == 8) {
            int i14 = x1Var.f740q;
            this.f716n = 8 | this.f716n;
            this.f720r = i14;
        }
        if ((i13 & 16) == 16) {
            k1 k1Var4 = x1Var.f741r;
            if ((this.f716n & 16) != 16 || (k1Var = this.f721s) == k1.D) {
                this.f721s = k1Var4;
            } else {
                j1 j1VarP2 = k1.p(k1Var);
                j1VarP2.j(k1Var4);
                this.f721s = j1VarP2.h();
            }
            this.f716n |= 16;
        }
        if ((x1Var.f736m & 32) == 32) {
            int i15 = x1Var.f742s;
            this.f716n = 32 | this.f716n;
            this.f722t = i15;
        }
        g(x1Var);
        this.f19815i = this.f19815i.b(x1Var.f735l);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r3, kotlin.reflect.jvm.internal.impl.protobuf.l r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            a9.a r1 = a9.x1.f734w     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.x1 r1 = new a9.x1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.x1 r4 = (a9.x1) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.w1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

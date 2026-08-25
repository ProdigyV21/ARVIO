package a9;

/* JADX INFO: loaded from: classes5.dex */
public final class z1 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements d2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f764l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f766n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a2 f767o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f768p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f769q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public b2 f770r;

    public static z1 h() {
        z1 z1Var = new z1();
        z1Var.f767o = a2.ERROR;
        z1Var.f770r = b2.LANGUAGE_VERSION;
        return z1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        c2 c2VarG = g();
        c2VarG.isInitialized();
        return c2VarG;
    }

    public final Object clone() {
        z1 z1VarH = h();
        z1VarH.i(g());
        return z1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((c2) uVar);
        return this;
    }

    public final c2 g() {
        c2 c2Var = new c2(this);
        int i10 = this.f764l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        c2Var.f366m = this.f765m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        c2Var.f367n = this.f766n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        c2Var.f368o = this.f767o;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        c2Var.f369p = this.f768p;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        c2Var.f370q = this.f769q;
        if ((i10 & 32) == 32) {
            i11 |= 32;
        }
        c2Var.f371r = this.f770r;
        c2Var.f365l = i11;
        return c2Var;
    }

    public final void i(c2 c2Var) {
        if (c2Var == c2.f362u) {
            return;
        }
        int i10 = c2Var.f365l;
        if ((i10 & 1) == 1) {
            int i11 = c2Var.f366m;
            this.f764l = 1 | this.f764l;
            this.f765m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = c2Var.f367n;
            this.f764l = 2 | this.f764l;
            this.f766n = i12;
        }
        if ((i10 & 4) == 4) {
            a2 a2Var = c2Var.f368o;
            a2Var.getClass();
            this.f764l = 4 | this.f764l;
            this.f767o = a2Var;
        }
        int i13 = c2Var.f365l;
        if ((i13 & 8) == 8) {
            int i14 = c2Var.f369p;
            this.f764l = 8 | this.f764l;
            this.f768p = i14;
        }
        if ((i13 & 16) == 16) {
            int i15 = c2Var.f370q;
            this.f764l = 16 | this.f764l;
            this.f769q = i15;
        }
        if ((i13 & 32) == 32) {
            b2 b2Var = c2Var.f371r;
            b2Var.getClass();
            this.f764l = 32 | this.f764l;
            this.f770r = b2Var;
        }
        this.f19815i = this.f19815i.b(c2Var.f364i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r2, kotlin.reflect.jvm.internal.impl.protobuf.l r3) throws java.lang.Throwable {
        /*
            r1 = this;
            r3 = 0
            a9.a r0 = a9.c2.f363v     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.c2 r0 = new a9.c2     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.i(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.c2 r0 = (a9.c2) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.i(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.z1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

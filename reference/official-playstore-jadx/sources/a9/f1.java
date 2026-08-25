package a9;

import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class f1 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements i1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f436l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g1 f437m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k1 f438n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f439o;

    public static f1 h() {
        f1 f1Var = new f1();
        f1Var.f437m = g1.INV;
        f1Var.f438n = k1.D;
        return f1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        h1 h1VarG = g();
        if (h1VarG.isInitialized()) {
            return h1VarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        f1 f1VarH = h();
        f1VarH.i(g());
        return f1VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((h1) uVar);
        return this;
    }

    public final h1 g() {
        h1 h1Var = new h1(this);
        int i10 = this.f436l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        h1Var.f475m = this.f437m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        h1Var.f476n = this.f438n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        h1Var.f477o = this.f439o;
        h1Var.f474l = i11;
        return h1Var;
    }

    public final void i(h1 h1Var) {
        k1 k1Var;
        if (h1Var == h1.f471r) {
            return;
        }
        if ((h1Var.f474l & 1) == 1) {
            g1 g1Var = h1Var.f475m;
            g1Var.getClass();
            this.f436l = 1 | this.f436l;
            this.f437m = g1Var;
        }
        if ((h1Var.f474l & 2) == 2) {
            k1 k1Var2 = h1Var.f476n;
            if ((this.f436l & 2) != 2 || (k1Var = this.f438n) == k1.D) {
                this.f438n = k1Var2;
            } else {
                j1 j1VarP = k1.p(k1Var);
                j1VarP.j(k1Var2);
                this.f438n = j1VarP.h();
            }
            this.f436l |= 2;
        }
        if ((h1Var.f474l & 4) == 4) {
            int i10 = h1Var.f477o;
            this.f436l = 4 | this.f436l;
            this.f439o = i10;
        }
        this.f19815i = this.f19815i.b(h1Var.f473i);
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
            a9.a r1 = a9.h1.f472s     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.h1 r1 = new a9.h1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.h1 r4 = (a9.h1) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.f1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

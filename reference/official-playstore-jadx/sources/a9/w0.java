package a9;

import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class w0 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements z0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f712l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f713m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f714n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public x0 f715o;

    public static w0 h() {
        w0 w0Var = new w0();
        w0Var.f713m = -1;
        w0Var.f715o = x0.PACKAGE;
        return w0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        y0 y0VarG = g();
        if (y0VarG.isInitialized()) {
            return y0VarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        w0 w0VarH = h();
        w0VarH.i(g());
        return w0VarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((y0) uVar);
        return this;
    }

    public final y0 g() {
        y0 y0Var = new y0(this);
        int i10 = this.f712l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        y0Var.f759m = this.f713m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        y0Var.f760n = this.f714n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        y0Var.f761o = this.f715o;
        y0Var.f758l = i11;
        return y0Var;
    }

    public final void i(y0 y0Var) {
        if (y0Var == y0.f755r) {
            return;
        }
        int i10 = y0Var.f758l;
        if ((i10 & 1) == 1) {
            int i11 = y0Var.f759m;
            this.f712l = 1 | this.f712l;
            this.f713m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = y0Var.f760n;
            this.f712l = 2 | this.f712l;
            this.f714n = i12;
        }
        if ((i10 & 4) == 4) {
            x0 x0Var = y0Var.f761o;
            x0Var.getClass();
            this.f712l = 4 | this.f712l;
            this.f715o = x0Var;
        }
        this.f19815i = this.f19815i.b(y0Var.f757i);
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
            a9.a r0 = a9.y0.f756s     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.y0 r0 = new a9.y0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.i(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.y0 r0 = (a9.y0) r0     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.w0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

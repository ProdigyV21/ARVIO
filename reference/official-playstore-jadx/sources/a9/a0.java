package a9;

import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements c0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f320n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f321o;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        b0 b0Var = new b0(this);
        int i10 = (this.f320n & 1) != 1 ? 0 : 1;
        b0Var.f340n = this.f321o;
        b0Var.f339m = i10;
        if (b0Var.isInitialized()) {
            return b0Var;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        a0 a0Var = new a0();
        b0 b0Var = new b0(this);
        int i10 = (this.f320n & 1) != 1 ? 0 : 1;
        b0Var.f340n = this.f321o;
        b0Var.f339m = i10;
        a0Var.h(b0Var);
        return a0Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((b0) uVar);
        return this;
    }

    public final void h(b0 b0Var) {
        if (b0Var == b0.f336q) {
            return;
        }
        if ((b0Var.f339m & 1) == 1) {
            int i10 = b0Var.f340n;
            this.f320n = 1 | this.f320n;
            this.f321o = i10;
        }
        g(b0Var);
        this.f19815i = this.f19815i.b(b0Var.f338l);
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
            a9.a r1 = a9.b0.f337r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.b0 r1 = new a9.b0     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.b0 r4 = (a9.b0) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.h(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.a0.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

package a9;

/* JADX INFO: loaded from: classes5.dex */
public final class c1 extends kotlin.reflect.jvm.internal.impl.protobuf.o implements e1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f360l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public kotlin.reflect.jvm.internal.impl.protobuf.z f361m;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        d1 d1VarG = g();
        d1VarG.isInitialized();
        return d1VarG;
    }

    public final Object clone() {
        c1 c1Var = new c1();
        c1Var.f361m = kotlin.reflect.jvm.internal.impl.protobuf.y.f19849l;
        c1Var.h(g());
        return c1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((d1) uVar);
        return this;
    }

    public final d1 g() {
        d1 d1Var = new d1(this);
        if ((this.f360l & 1) == 1) {
            this.f361m = this.f361m.l();
            this.f360l &= -2;
        }
        d1Var.f399l = this.f361m;
        return d1Var;
    }

    public final void h(d1 d1Var) {
        if (d1Var == d1.f396o) {
            return;
        }
        if (!d1Var.f399l.isEmpty()) {
            if (this.f361m.isEmpty()) {
                this.f361m = d1Var.f399l;
                this.f360l &= -2;
            } else {
                if ((this.f360l & 1) != 1) {
                    this.f361m = new kotlin.reflect.jvm.internal.impl.protobuf.y(this.f361m);
                    this.f360l |= 1;
                }
                this.f361m.addAll(d1Var.f399l);
            }
        }
        this.f19815i = this.f19815i.b(d1Var.f398i);
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
            a9.a r0 = a9.d1.f397p     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.d1 r0 = new a9.d1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.d1 r0 = (a9.d1) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.h(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.c1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

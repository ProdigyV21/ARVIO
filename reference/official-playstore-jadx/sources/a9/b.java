package a9;

import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.reflect.jvm.internal.impl.protobuf.o implements h {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f333l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f334m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f335n;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        g gVarG = g();
        if (gVarG.isInitialized()) {
            return gVarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        b bVar = new b();
        bVar.f335n = e.z;
        bVar.h(g());
        return bVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        h((g) uVar);
        return this;
    }

    public final g g() {
        g gVar = new g(this);
        int i10 = this.f333l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        gVar.f450m = this.f334m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        gVar.f451n = this.f335n;
        gVar.f449l = i11;
        return gVar;
    }

    public final void h(g gVar) {
        e eVar;
        if (gVar == g.f446q) {
            return;
        }
        int i10 = gVar.f449l;
        if ((i10 & 1) == 1) {
            int i11 = gVar.f450m;
            this.f333l = 1 | this.f333l;
            this.f334m = i11;
        }
        if ((i10 & 2) == 2) {
            e eVar2 = gVar.f451n;
            if ((this.f333l & 2) != 2 || (eVar = this.f335n) == e.z) {
                this.f335n = eVar2;
            } else {
                c cVarH = c.h();
                cVarH.i(eVar);
                cVarH.i(eVar2);
                this.f335n = cVarH.g();
            }
            this.f333l |= 2;
        }
        this.f19815i = this.f19815i.b(gVar.f448i);
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
            a9.a r1 = a9.g.f447r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.g r1 = new a9.g     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.g r4 = (a9.g) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.b.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

package d9;

import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends kotlin.reflect.jvm.internal.impl.protobuf.o implements f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14799l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14800m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14801n;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final c0 build() {
        e eVarG = g();
        eVarG.isInitialized();
        return eVarG;
    }

    public final Object clone() {
        d dVar = new d();
        dVar.h(g());
        return dVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(u uVar) {
        h((e) uVar);
        return this;
    }

    public final e g() {
        e eVar = new e(this);
        int i10 = this.f14799l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        eVar.f14806m = this.f14800m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        eVar.f14807n = this.f14801n;
        eVar.f14805l = i11;
        return eVar;
    }

    public final void h(e eVar) {
        if (eVar == e.f14802q) {
            return;
        }
        int i10 = eVar.f14805l;
        if ((i10 & 1) == 1) {
            int i11 = eVar.f14806m;
            this.f14799l = 1 | this.f14799l;
            this.f14800m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = eVar.f14807n;
            this.f14799l = 2 | this.f14799l;
            this.f14801n = i12;
        }
        this.f19815i = this.f19815i.b(eVar.f14804i);
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
            a9.a r0 = d9.e.f14803r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            d9.e r0 = new d9.e     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            d9.e r0 = (d9.e) r0     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: d9.d.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

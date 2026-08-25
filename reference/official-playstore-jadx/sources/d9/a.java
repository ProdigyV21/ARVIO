package d9;

import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends kotlin.reflect.jvm.internal.impl.protobuf.o implements c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14788l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14789m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14790n;

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final c0 build() {
        b bVarG = g();
        bVarG.isInitialized();
        return bVarG;
    }

    public final Object clone() {
        a aVar = new a();
        aVar.h(g());
        return aVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(u uVar) {
        h((b) uVar);
        return this;
    }

    public final b g() {
        b bVar = new b(this);
        int i10 = this.f14788l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        bVar.f14795m = this.f14789m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        bVar.f14796n = this.f14790n;
        bVar.f14794l = i11;
        return bVar;
    }

    public final void h(b bVar) {
        if (bVar == b.f14791q) {
            return;
        }
        int i10 = bVar.f14794l;
        if ((i10 & 1) == 1) {
            int i11 = bVar.f14795m;
            this.f14788l = 1 | this.f14788l;
            this.f14789m = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = bVar.f14796n;
            this.f14788l = 2 | this.f14788l;
            this.f14790n = i12;
        }
        this.f19815i = this.f19815i.b(bVar.f14793i);
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
            a9.a r0 = d9.b.f14792r     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            d9.b r0 = new d9.b     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r0 = r2.f19759i     // Catch: java.lang.Throwable -> Lf
            d9.b r0 = (d9.b) r0     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: d9.a.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

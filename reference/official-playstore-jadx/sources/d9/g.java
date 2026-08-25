package d9;

import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends kotlin.reflect.jvm.internal.impl.protobuf.o implements i {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14810l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f14811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f14812n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public e f14813o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e f14814p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public e f14815q;

    public static g h() {
        g gVar = new g();
        gVar.f14811m = b.f14791q;
        e eVar = e.f14802q;
        gVar.f14812n = eVar;
        gVar.f14813o = eVar;
        gVar.f14814p = eVar;
        gVar.f14815q = eVar;
        return gVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final c0 build() {
        h hVarG = g();
        hVarG.isInitialized();
        return hVarG;
    }

    public final Object clone() {
        g gVarH = h();
        gVarH.i(g());
        return gVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(u uVar) {
        i((h) uVar);
        return this;
    }

    public final h g() {
        h hVar = new h(this);
        int i10 = this.f14810l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        hVar.f14820m = this.f14811m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        hVar.f14821n = this.f14812n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        hVar.f14822o = this.f14813o;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        hVar.f14823p = this.f14814p;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        hVar.f14824q = this.f14815q;
        hVar.f14819l = i11;
        return hVar;
    }

    public final void i(h hVar) {
        e eVar;
        e eVar2;
        e eVar3;
        e eVar4;
        b bVar;
        if (hVar == h.f14816t) {
            return;
        }
        if ((hVar.f14819l & 1) == 1) {
            b bVar2 = hVar.f14820m;
            if ((this.f14810l & 1) != 1 || (bVar = this.f14811m) == b.f14791q) {
                this.f14811m = bVar2;
            } else {
                a aVar = new a();
                aVar.h(bVar);
                aVar.h(bVar2);
                this.f14811m = aVar.g();
            }
            this.f14810l |= 1;
        }
        if ((hVar.f14819l & 2) == 2) {
            e eVar5 = hVar.f14821n;
            if ((this.f14810l & 2) != 2 || (eVar4 = this.f14812n) == e.f14802q) {
                this.f14812n = eVar5;
            } else {
                d dVarH = e.h(eVar4);
                dVarH.h(eVar5);
                this.f14812n = dVarH.g();
            }
            this.f14810l |= 2;
        }
        if ((hVar.f14819l & 4) == 4) {
            e eVar6 = hVar.f14822o;
            if ((this.f14810l & 4) != 4 || (eVar3 = this.f14813o) == e.f14802q) {
                this.f14813o = eVar6;
            } else {
                d dVarH2 = e.h(eVar3);
                dVarH2.h(eVar6);
                this.f14813o = dVarH2.g();
            }
            this.f14810l |= 4;
        }
        if ((hVar.f14819l & 8) == 8) {
            e eVar7 = hVar.f14823p;
            if ((this.f14810l & 8) != 8 || (eVar2 = this.f14814p) == e.f14802q) {
                this.f14814p = eVar7;
            } else {
                d dVarH3 = e.h(eVar2);
                dVarH3.h(eVar7);
                this.f14814p = dVarH3.g();
            }
            this.f14810l |= 8;
        }
        if ((hVar.f14819l & 16) == 16) {
            e eVar8 = hVar.f14824q;
            if ((this.f14810l & 16) != 16 || (eVar = this.f14815q) == e.f14802q) {
                this.f14815q = eVar8;
            } else {
                d dVarH4 = e.h(eVar);
                dVarH4.h(eVar8);
                this.f14815q = dVarH4.g();
            }
            this.f14810l |= 16;
        }
        this.f19815i = this.f19815i.b(hVar.f14818i);
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
            a9.a r1 = d9.h.f14817u     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            d9.h r1 = new d9.h     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            d9.h r4 = (d9.h) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: d9.g.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}

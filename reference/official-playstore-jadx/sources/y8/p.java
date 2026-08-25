package y8;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f22964b = Collections.singleton(z8.a.CLASS);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set f22965c = kotlin.collections.r.p0(new z8.a[]{z8.a.FILE_FACADE, z8.a.MULTIFILE_CLASS_PART});

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e9.f f22966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e9.f f22967e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public s9.j f22968a;

    static {
        new e9.f(false, new int[]{1, 1, 2});
        f22966d = new e9.f(false, new int[]{1, 1, 11});
        f22967e = new e9.f(false, new int[]{1, 1, 13});
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t a(g8.h0 r12, y8.y r13) {
        /*
            r11 = this;
            java.lang.String r1 = "Could not read data from "
            o5.a r0 = r13.b()
            java.lang.Object r2 = r0.f20779f
            java.lang.String[] r2 = (java.lang.String[]) r2
            if (r2 != 0) goto L10
            java.lang.Object r2 = r0.f20780g
            java.lang.String[] r2 = (java.lang.String[]) r2
        L10:
            r3 = 0
            if (r2 == 0) goto L20
            java.lang.Object r0 = r0.f20777d
            z8.a r0 = (z8.a) r0
            java.util.Set r4 = y8.p.f22965c
            boolean r0 = r4.contains(r0)
            if (r0 == 0) goto L20
            goto L21
        L20:
            r2 = r3
        L21:
            if (r2 != 0) goto L24
            goto L72
        L24:
            o5.a r0 = r13.b()
            java.lang.Object r0 = r0.f20781h
            java.lang.String[] r0 = (java.lang.String[]) r0
            if (r0 != 0) goto L2f
            goto L72
        L2f:
            x6.x r0 = e9.h.h(r2, r0)     // Catch: java.lang.Throwable -> L34 kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L36
            goto L70
        L34:
            r0 = move-exception
            goto L4d
        L36:
            r0 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L34
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = r13.d()     // Catch: java.lang.Throwable -> L34
            r4.append(r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L34
            r2.<init>(r1, r0)     // Catch: java.lang.Throwable -> L34
            throw r2     // Catch: java.lang.Throwable -> L34
        L4d:
            s9.j r1 = r11.c()
            s9.k r1 = r1.f21708c
            r1.getClass()
            o5.a r1 = r13.b()
            java.lang.Object r1 = r1.f20778e
            e9.f r1 = (e9.f) r1
            s9.j r2 = r11.c()
            s9.k r2 = r2.f21708c
            r2.getClass()
            e9.f r2 = e9.f.f15073g
            boolean r1 = r1.b(r2)
            if (r1 != 0) goto Lb7
            r0 = r3
        L70:
            if (r0 != 0) goto L73
        L72:
            return r3
        L73:
            java.lang.Object r1 = r0.f22608i
            r5 = r1
            e9.g r5 = (e9.g) r5
            java.lang.Object r0 = r0.f22609l
            r4 = r0
            a9.n0 r4 = (a9.n0) r4
            y8.r r7 = new y8.r
            r11.d(r13)
            r11.e(r13)
            r11.b(r13)
            r7.<init>(r13, r4, r5)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t r2 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t
            o5.a r13 = r13.b()
            java.lang.Object r13 = r13.f20778e
            r6 = r13
            e9.f r6 = (e9.f) r6
            s9.j r8 = r11.c()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "scope for "
            r13.<init>(r0)
            r13.append(r7)
            java.lang.String r0 = " in "
            r13.append(r0)
            r13.append(r12)
            java.lang.String r9 = r13.toString()
            y8.o r10 = y8.o.f22963i
            r3 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return r2
        Lb7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: y8.p.a(g8.h0, y8.y):kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.t");
    }

    public final int b(y yVar) {
        c().f21708c.getClass();
        int i10 = yVar.b().f20776c;
        if ((i10 & 64) != 0 && (i10 & 32) == 0) {
            return 2;
        }
        int i11 = yVar.b().f20776c;
        return ((i11 & 16) == 0 || (i11 & 32) != 0) ? 1 : 3;
    }

    public final s9.j c() {
        s9.j jVar = this.f22968a;
        if (jVar != null) {
            return jVar;
        }
        kotlin.jvm.internal.p.i("components");
        throw null;
    }

    public final s9.q d(y yVar) {
        c().f21708c.getClass();
        e9.f fVar = (e9.f) yVar.b().f20778e;
        c().f21708c.getClass();
        e9.f fVar2 = e9.f.f15073g;
        if (fVar.b(fVar2)) {
            return null;
        }
        e9.f fVar3 = (e9.f) yVar.b().f20778e;
        c().f21708c.getClass();
        c().f21708c.getClass();
        boolean z = ((e9.f) yVar.b().f20778e).f15075f;
        fVar2.getClass();
        e9.f fVar4 = z ? fVar2 : e9.f.f15074h;
        int i10 = fVar4.f7425b;
        int i11 = fVar2.f7425b;
        return new s9.q(fVar3, fVar2, fVar2, (i10 <= i11 && (i10 < i11 || fVar4.f7426c <= fVar2.f7426c)) ? fVar2 : fVar4, yVar.d(), yVar.h());
    }

    public final boolean e(y yVar) {
        c().f21708c.getClass();
        c().f21708c.getClass();
        return (yVar.b().f20776c & 2) != 0 && ((e9.f) yVar.b().f20778e).equals(f22966d);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final s9.d f(y8.y r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Could not read data from "
            o5.a r1 = r6.b()
            java.lang.Object r2 = r1.f20779f
            java.lang.String[] r2 = (java.lang.String[]) r2
            if (r2 != 0) goto L10
            java.lang.Object r2 = r1.f20780g
            java.lang.String[] r2 = (java.lang.String[]) r2
        L10:
            r3 = 0
            if (r2 == 0) goto L20
            java.lang.Object r1 = r1.f20777d
            z8.a r1 = (z8.a) r1
            java.util.Set r4 = y8.p.f22964b
            boolean r1 = r4.contains(r1)
            if (r1 == 0) goto L20
            goto L21
        L20:
            r2 = r3
        L21:
            if (r2 != 0) goto L24
            goto L72
        L24:
            o5.a r1 = r6.b()
            java.lang.Object r1 = r1.f20781h
            java.lang.String[] r1 = (java.lang.String[]) r1
            if (r1 != 0) goto L2f
            goto L72
        L2f:
            x6.x r0 = e9.h.f(r2, r1)     // Catch: java.lang.Throwable -> L34 kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L36
            goto L70
        L34:
            r0 = move-exception
            goto L4d
        L36:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L34
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L34
            java.lang.String r0 = r6.d()     // Catch: java.lang.Throwable -> L34
            r4.append(r0)     // Catch: java.lang.Throwable -> L34
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L34
            r2.<init>(r0, r1)     // Catch: java.lang.Throwable -> L34
            throw r2     // Catch: java.lang.Throwable -> L34
        L4d:
            s9.j r1 = r5.c()
            s9.k r1 = r1.f21708c
            r1.getClass()
            o5.a r1 = r6.b()
            java.lang.Object r1 = r1.f20778e
            e9.f r1 = (e9.f) r1
            s9.j r2 = r5.c()
            s9.k r2 = r2.f21708c
            r2.getClass()
            e9.f r2 = e9.f.f15073g
            boolean r1 = r1.b(r2)
            if (r1 != 0) goto L97
            r0 = r3
        L70:
            if (r0 != 0) goto L73
        L72:
            return r3
        L73:
            java.lang.Object r1 = r0.f22608i
            e9.g r1 = (e9.g) r1
            java.lang.Object r0 = r0.f22609l
            a9.n r0 = (a9.n) r0
            y8.z r2 = new y8.z
            r5.d(r6)
            r5.e(r6)
            r5.b(r6)
            r2.<init>(r6)
            s9.d r3 = new s9.d
            o5.a r6 = r6.b()
            java.lang.Object r6 = r6.f20778e
            e9.f r6 = (e9.f) r6
            r3.<init>(r1, r0, r6, r2)
            return r3
        L97:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: y8.p.f(y8.y):s9.d");
    }
}

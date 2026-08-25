package y8;

import a9.i0;
import a9.k1;
import a9.r1;
import a9.t0;
import androidx.compose.material3.TextFieldImplKt;
import com.google.common.util.concurrent.p0;
import com.google.common.util.concurrent.r0;
import g8.v0;
import h.f0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g implements s9.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l8.c f22935i;

    public g(l8.c cVar) {
        this.f22935i = cVar;
    }

    public static /* synthetic */ List c(g gVar, androidx.appcompat.view.menu.e eVar, a0 a0Var, Boolean bool, boolean z, int i10) {
        boolean z5 = (i10 & 4) == 0;
        if ((i10 & 16) != 0) {
            bool = null;
        }
        return gVar.a(eVar, a0Var, z5, false, bool, (i10 & 32) != 0 ? false : z);
    }

    public static a0 d(kotlin.reflect.jvm.internal.impl.protobuf.c0 c0Var, c9.f fVar, androidx.work.impl.constraints.j jVar, int i10, boolean z) {
        d9.h hVar;
        if (c0Var instanceof a9.q) {
            kotlin.reflect.jvm.internal.impl.protobuf.l lVar = e9.h.f15080a;
            e9.e eVarA = e9.h.a((a9.q) c0Var, fVar, jVar);
            if (eVarA != null) {
                return p0.k(eVarA);
            }
        } else if (c0Var instanceof i0) {
            kotlin.reflect.jvm.internal.impl.protobuf.l lVar2 = e9.h.f15080a;
            e9.e eVarC = e9.h.c((i0) c0Var, fVar, jVar);
            if (eVarC != null) {
                return p0.k(eVarC);
            }
        } else if ((c0Var instanceof t0) && (hVar = (d9.h) r0.l((kotlin.reflect.jvm.internal.impl.protobuf.q) c0Var, d9.q.f14866d)) != null) {
            int iC = f0.c(i10);
            if (iC == 1) {
                return ac.b.G((t0) c0Var, fVar, jVar, true, true, z);
            }
            if (iC == 2) {
                if ((hVar.f14819l & 4) != 4) {
                    return null;
                }
                d9.e eVar = hVar.f14822o;
                return new a0(fVar.getString(eVar.f14806m).concat(fVar.getString(eVar.f14807n)));
            }
            if (iC != 3 || (hVar.f14819l & 8) != 8) {
                return null;
            }
            d9.e eVar2 = hVar.f14823p;
            return new a0(fVar.getString(eVar2.f14806m).concat(fVar.getString(eVar2.f14807n)));
        }
        return null;
    }

    @Override // s9.c
    public final List A(androidx.appcompat.view.menu.e eVar, t0 t0Var) {
        return h(eVar, t0Var, 2);
    }

    @Override // s9.c
    public final List B(androidx.appcompat.view.menu.e eVar, t0 t0Var) {
        return h(eVar, t0Var, 3);
    }

    @Override // s9.c
    public final ArrayList D(s9.y yVar) {
        v0 v0Var = (v0) yVar.f1286d;
        z zVar = v0Var instanceof z ? (z) v0Var : null;
        y yVar2 = zVar != null ? zVar.f22986i : null;
        if (yVar2 != null) {
            ArrayList arrayList = new ArrayList(1);
            yVar2.a(new f(this, arrayList));
            return arrayList;
        }
        throw new IllegalStateException(("Class for loading annotations is not found: " + yVar.f21768g.b()).toString());
    }

    @Override // s9.c
    public final List E(androidx.appcompat.view.menu.e eVar, a9.b0 b0Var) {
        return c(this, eVar, new a0(androidx.compose.foundation.c.m('#', ((c9.f) eVar.f1284b).getString(b0Var.f340n), e9.b.b(((s9.y) eVar).f21768g.c()))), null, false, 60);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    @Override // s9.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List F(androidx.appcompat.view.menu.e r10, kotlin.reflect.jvm.internal.impl.protobuf.c0 r11, int r12, int r13, a9.x1 r14) {
        /*
            r9 = this;
            java.lang.Object r14 = r10.f1284b
            c9.f r14 = (c9.f) r14
            java.lang.Object r0 = r10.f1285c
            androidx.work.impl.constraints.j r0 = (androidx.work.impl.constraints.j) r0
            r1 = 0
            y8.a0 r12 = d(r11, r14, r0, r12, r1)
            if (r12 == 0) goto L88
            boolean r14 = r11 instanceof a9.i0
            r0 = 32
            r2 = 64
            r3 = 1
            if (r14 == 0) goto L26
            a9.i0 r11 = (a9.i0) r11
            int r11 = r11.f486m
            r14 = r11 & 32
            if (r14 != r0) goto L21
            goto L24
        L21:
            r11 = r11 & r2
            if (r11 != r2) goto L4b
        L24:
            r1 = r3
            goto L4b
        L26:
            boolean r14 = r11 instanceof a9.t0
            if (r14 == 0) goto L37
            a9.t0 r11 = (a9.t0) r11
            int r11 = r11.f677m
            r14 = r11 & 32
            if (r14 != r0) goto L33
            goto L36
        L33:
            r11 = r11 & r2
            if (r11 != r2) goto L4b
        L36:
            goto L24
        L37:
            boolean r14 = r11 instanceof a9.q
            if (r14 == 0) goto L70
            r11 = r10
            s9.y r11 = (s9.y) r11
            a9.m r14 = r11.f21769h
            a9.m r0 = a9.m.ENUM_CLASS
            if (r14 != r0) goto L46
            r1 = 2
            goto L4b
        L46:
            boolean r11 = r11.f21770i
            if (r11 == 0) goto L4b
            goto L24
        L4b:
            int r13 = r13 + r1
            y8.a0 r5 = new y8.a0
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = r12.f22913a
            r11.append(r12)
            r11.append(r2)
            r11.append(r13)
            java.lang.String r11 = r11.toString()
            r5.<init>(r11)
            r7 = 0
            r8 = 60
            r6 = 0
            r3 = r9
            r4 = r10
            java.util.List r10 = c(r3, r4, r5, r6, r7, r8)
            return r10
        L70:
            java.lang.UnsupportedOperationException r10 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Unsupported message: "
            r12.<init>(r13)
            java.lang.Class r11 = r11.getClass()
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            r10.<init>(r11)
            throw r10
        L88:
            kotlin.collections.z r10 = kotlin.collections.z.f19728i
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: y8.g.F(androidx.appcompat.view.menu.e, kotlin.reflect.jvm.internal.impl.protobuf.c0, int, int, a9.x1):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List a(androidx.appcompat.view.menu.e r9, y8.a0 r10, boolean r11, boolean r12, java.lang.Boolean r13, boolean r14) {
        /*
            r8 = this;
            r0 = r8
            y8.n r0 = (y8.n) r0
            e9.f r7 = r0.f22962p
            l8.c r6 = r8.f22935i
            r1 = r9
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            y8.y r9 = a.a.T(r1, r2, r3, r4, r5, r6, r7)
            if (r9 != 0) goto L2c
            boolean r9 = r1 instanceof s9.y
            r11 = 0
            if (r9 == 0) goto L2b
            r9 = r1
            s9.y r9 = (s9.y) r9
            java.lang.Object r9 = r9.f1286d
            g8.v0 r9 = (g8.v0) r9
            boolean r12 = r9 instanceof y8.z
            if (r12 == 0) goto L25
            y8.z r9 = (y8.z) r9
            goto L26
        L25:
            r9 = r11
        L26:
            if (r9 == 0) goto L2b
            y8.y r9 = r9.f22986i
            goto L2c
        L2b:
            r9 = r11
        L2c:
            if (r9 != 0) goto L2f
            goto L44
        L2f:
            r11 = r8
            y8.e r11 = (y8.e) r11
            u9.q r11 = r11.f22922l
            java.lang.Object r9 = r11.invoke(r9)
            y8.h r9 = (y8.h) r9
            java.util.HashMap r9 = r9.f22936a
            java.lang.Object r9 = r9.get(r10)
            java.util.List r9 = (java.util.List) r9
            if (r9 != 0) goto L46
        L44:
            kotlin.collections.z r9 = kotlin.collections.z.f19728i
        L46:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: y8.g.a(androidx.appcompat.view.menu.e, y8.a0, boolean, boolean, java.lang.Boolean, boolean):java.util.List");
    }

    @Override // s9.c
    public final List b(androidx.appcompat.view.menu.e eVar, kotlin.reflect.jvm.internal.impl.protobuf.c0 c0Var, int i10) {
        a0 a0VarD = d(c0Var, (c9.f) eVar.f1284b, (androidx.work.impl.constraints.j) eVar.f1285c, i10, false);
        return a0VarD != null ? c(this, eVar, new a0(a0.c.p(new StringBuilder(), a0VarD.f22913a, "@0")), null, false, 60) : kotlin.collections.z.f19728i;
    }

    public final boolean f(f9.b bVar) {
        if (bVar.f() == null || !kotlin.jvm.internal.p.a(bVar.i().d(), TextFieldImplKt.ContainerId)) {
            return false;
        }
        kb.d dVarB = this.f22935i.b(bVar);
        l8.e eVar = dVarB != null ? (l8.e) dVarB.f19673l : null;
        if (eVar == null) {
            return false;
        }
        LinkedHashSet linkedHashSet = c8.b.f7422a;
        kotlin.jvm.internal.f0 f0Var = new kotlin.jvm.internal.f0();
        eVar.a(new c8.a(f0Var));
        return f0Var.f19738i;
    }

    @Override // s9.c
    public final ArrayList g(k1 k1Var, c9.f fVar) {
        Iterable iterable = (Iterable) k1Var.j(d9.q.f14868f);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((n) this).f22961o.I((a9.j) it.next(), fVar));
        }
        return arrayList;
    }

    public final List h(androidx.appcompat.view.menu.e eVar, t0 t0Var, int i10) {
        androidx.work.impl.constraints.j jVar = (androidx.work.impl.constraints.j) eVar.f1285c;
        c9.f fVar = (c9.f) eVar.f1284b;
        Boolean boolC = c9.e.B.c(t0Var.f678n);
        boolean zD = e9.h.d(t0Var);
        if (i10 == 1) {
            a0 a0VarG = ac.b.G(t0Var, fVar, jVar, (40 & 8) == 0, (40 & 16) == 0, true);
            if (a0VarG != null) {
                return c(this, eVar, a0VarG, boolC, zD, 8);
            }
        } else {
            a0 a0VarG2 = ac.b.G(t0Var, fVar, jVar, (40 & 8) == 0, (40 & 16) == 0, true);
            if (a0VarG2 != null) {
                if (kotlin.text.o.T(a0VarG2.f22913a, "$delegate", false) == (i10 == 3)) {
                    return a(eVar, a0VarG2, true, true, boolC, zD);
                }
            }
        }
        return kotlin.collections.z.f19728i;
    }

    @Override // s9.c
    public final List i(androidx.appcompat.view.menu.e eVar, kotlin.reflect.jvm.internal.impl.protobuf.c0 c0Var, int i10) {
        if (i10 == 2) {
            return h(eVar, (t0) c0Var, 1);
        }
        a0 a0VarD = d(c0Var, (c9.f) eVar.f1284b, (androidx.work.impl.constraints.j) eVar.f1285c, i10, false);
        return a0VarD == null ? kotlin.collections.z.f19728i : c(this, eVar, a0VarD, null, false, 60);
    }

    @Override // s9.c
    public final ArrayList r(r1 r1Var, c9.f fVar) {
        Iterable iterable = (Iterable) r1Var.j(d9.q.f14870h);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((n) this).f22961o.I((a9.j) it.next(), fVar));
        }
        return arrayList;
    }
}

package j8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public class k extends w implements g8.e {
    public final boolean O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(g8.f fVar, g8.j jVar, h8.h hVar, boolean z, int i10, g8.v0 v0Var) {
        super(i10, f9.h.f15376e, fVar, jVar, v0Var, hVar);
        if (fVar == null) {
            v(0);
            throw null;
        }
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (i10 == 0) {
            v(2);
            throw null;
        }
        if (v0Var == null) {
            v(3);
            throw null;
        }
        this.O = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void v(int r8) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.k.v(int):void");
    }

    @Override // j8.w, g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.C(this, obj);
    }

    @Override // j8.w
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] */
    public k I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, g8.v0 v0Var, h8.h hVar) {
        if (kVar == null) {
            v(23);
            throw null;
        }
        if (i10 == 0) {
            v(24);
            throw null;
        }
        if (hVar == null) {
            v(25);
            throw null;
        }
        if (i10 == 1 || i10 == 4) {
            return new k((g8.f) kVar, this, hVar, this.O, 1, v0Var);
        }
        throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + kVar + "\nkind: " + g8.b.r(i10));
    }

    @Override // j8.p, g8.k
    /* JADX INFO: renamed from: S0, reason: merged with bridge method [inline-methods] */
    public final g8.f d() {
        g8.f fVar = (g8.f) super.d();
        if (fVar != null) {
            return fVar;
        }
        v(17);
        throw null;
    }

    public final void T0(List list, g8.p pVar) {
        if (list != null) {
            U0(list, pVar, d().n());
        } else {
            v(13);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void U0(java.util.List r12, g8.p r13, java.util.List r14) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L58
            if (r14 == 0) goto L52
            g8.f r1 = r11.d()
            boolean r2 = r1.i()
            if (r2 == 0) goto L1f
            g8.k r1 = r1.d()
            boolean r2 = r1 instanceof g8.f
            if (r2 == 0) goto L1f
            g8.f r1 = (g8.f) r1
            g8.s0 r1 = r1.F0()
            r4 = r1
            goto L20
        L1f:
            r4 = r0
        L20:
            g8.f r1 = r11.d()
            java.util.List r2 = r1.W()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L3c
            java.util.List r1 = r1.W()
            if (r1 == 0) goto L36
        L34:
            r5 = r1
            goto L41
        L36:
            r12 = 15
            v(r12)
            throw r0
        L3c:
            java.util.List r1 = java.util.Collections.EMPTY_LIST
            if (r1 == 0) goto L4c
            goto L34
        L41:
            r8 = 0
            r9 = 1
            r3 = 0
            r2 = r11
            r7 = r12
            r10 = r13
            r6 = r14
            r2.L0(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        L4c:
            r12 = 16
            v(r12)
            throw r0
        L52:
            r12 = 12
            v(r12)
            throw r0
        L58:
            r12 = 10
            v(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.k.U0(java.util.List, g8.p, java.util.List):void");
    }

    @Override // g8.j
    public final boolean Z() {
        return this.O;
    }

    @Override // g8.j
    public final g8.f a0() {
        g8.f fVarD = d();
        if (fVarD != null) {
            return fVarD;
        }
        v(18);
        throw null;
    }

    @Override // j8.w, g8.c, g8.a
    public final Collection k() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        v(21);
        throw null;
    }

    @Override // j8.w, g8.c
    public final g8.c v0(g8.f fVar, int i10, g8.p pVar) {
        return (g8.e) G0(fVar, i10, pVar);
    }

    @Override // j8.w, g8.c
    public final void z0(Collection collection) {
        if (collection != null) {
            return;
        }
        v(22);
        throw null;
    }

    @Override // j8.w, g8.w, g8.x0
    public final g8.e b(y0 y0Var) {
        if (y0Var != null) {
            return (g8.e) super.b(y0Var);
        }
        v(20);
        throw null;
    }

    @Override // j8.w, j8.p, j8.o, g8.k
    public final g8.e a() {
        g8.e eVar = (g8.e) super.a();
        if (eVar != null) {
            return eVar;
        }
        v(19);
        throw null;
    }
}

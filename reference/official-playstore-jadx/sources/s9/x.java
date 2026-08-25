package s9;

import a9.h2;
import a9.k1;
import a9.t0;
import a9.x1;
import j8.v0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f21764a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final fi.iki.elonen.f f21765b;

    public x(m mVar) {
        this.f21764a = mVar;
        j jVar = mVar.f21731a;
        this.f21765b = new fi.iki.elonen.f(jVar.f21707b, jVar.f21716l);
    }

    public final androidx.appcompat.view.menu.e a(g8.k kVar) {
        if (kVar instanceof g8.h0) {
            f9.c cVarC = ((g8.h0) kVar).c();
            m mVar = this.f21764a;
            return new z(cVarC, mVar.f21732b, mVar.f21734d, mVar.f21737g);
        }
        if (kVar instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) {
            return ((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) kVar).G;
        }
        return null;
    }

    public final h8.h b(kotlin.reflect.jvm.internal.impl.protobuf.q qVar, int i10, int i11) {
        return !c9.e.f7434c.c(i10).booleanValue() ? h8.g.f15863a : new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.y(this.f21764a.f21731a.f21706a, new t(this, qVar, i11, 0));
    }

    public final h8.h c(t0 t0Var, boolean z) {
        return !c9.e.f7434c.c(t0Var.f678n).booleanValue() ? h8.g.f15863a : new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.y(this.f21764a.f21731a.f21706a, new u(this, z, t0Var));
    }

    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.d d(a9.q qVar, boolean z) {
        g8.p pVar;
        m mVar = this.f21764a;
        g8.f fVar = (g8.f) mVar.f21733c;
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.d dVar = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.d(fVar, null, b(qVar, qVar.f635n, 1), z, 1, qVar, mVar.f21732b, mVar.f21734d, mVar.f21735e, mVar.f21737g, null);
        List listG = mVar.a(dVar, kotlin.collections.z.f19728i, mVar.f21732b, mVar.f21734d, mVar.f21735e, mVar.f21736f).f21739i.g(qVar.f636o, qVar, 1);
        h2 h2Var = (h2) c9.e.f7435d.c(qVar.f635n);
        switch (h2Var == null ? -1 : b0.f21681b[h2Var.ordinal()]) {
            case 1:
                pVar = g8.q.f15527d;
                break;
            case 2:
                pVar = g8.q.f15524a;
                break;
            case 3:
                pVar = g8.q.f15525b;
                break;
            case 4:
                pVar = g8.q.f15526c;
                break;
            case 5:
                pVar = g8.q.f15528e;
                break;
            case 6:
                pVar = g8.q.f15529f;
                break;
            default:
                pVar = g8.q.f15524a;
                break;
        }
        dVar.T0(listG, pVar);
        dVar.Q0(fVar.m());
        dVar.B = fVar.i0();
        dVar.G = !c9.e.f7445o.c(qVar.f635n).booleanValue();
        return dVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.v e(a9.i0 r29) {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s9.x.e(a9.i0):kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.v");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015c  */
    /* JADX WARN: Type inference failed for: r14v23, types: [u9.t] */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r7v0, types: [g8.a, g8.p0, j8.l0, j8.p, j8.x0, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17, types: [j8.n0] */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u f(a9.t0 r34) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s9.x.f(a9.t0):kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u");
    }

    public final List g(List list, kotlin.reflect.jvm.internal.impl.protobuf.q qVar, int i10) {
        int i11;
        h8.h yVar;
        x xVar = this;
        m mVar = xVar.f21764a;
        androidx.work.impl.constraints.j jVar = mVar.f21734d;
        g0 g0Var = mVar.f21738h;
        g8.a aVar = (g8.a) mVar.f21733c;
        androidx.appcompat.view.menu.e eVarA = xVar.a(aVar.d());
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        int i12 = 0;
        for (Object obj : list) {
            int i13 = i12 + 1;
            v9.w wVarG = null;
            if (i12 < 0) {
                t7.a.Q();
                throw null;
            }
            x1 x1Var = (x1) obj;
            int i14 = (x1Var.f736m & 1) == 1 ? x1Var.f737n : 0;
            if (eVarA == null || !c9.e.f7434c.c(i14).booleanValue()) {
                i11 = i12;
                yVar = h8.g.f15863a;
            } else {
                i11 = i12;
                yVar = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.y(mVar.f21731a.f21706a, new w(xVar, eVarA, qVar, i10, i11, x1Var));
            }
            f9.f fVarX = t7.a.x(mVar.f21732b, x1Var.f738o);
            v9.w wVarG2 = g0Var.g(f4.f.A(x1Var, jVar));
            boolean zBooleanValue = c9.e.H.c(i14).booleanValue();
            boolean zBooleanValue2 = c9.e.I.c(i14).booleanValue();
            boolean zBooleanValue3 = c9.e.J.c(i14).booleanValue();
            int i15 = x1Var.f736m;
            k1 k1VarB = (i15 & 16) == 16 ? x1Var.f741r : (i15 & 32) == 32 ? jVar.b(x1Var.f742s) : null;
            if (k1VarB != null) {
                wVarG = g0Var.g(k1VarB);
            }
            ArrayList arrayList2 = arrayList;
            arrayList2.add(new v0(aVar, null, i11, yVar, fVarX, wVarG2, zBooleanValue, zBooleanValue2, zBooleanValue3, wVarG, g8.v0.f15554b));
            arrayList = arrayList2;
            i12 = i13;
            xVar = this;
        }
        return kotlin.collections.x.c1(arrayList);
    }
}

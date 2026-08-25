package androidx.datastore.preferences.protobuf;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final class v implements a4, p2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n0 f2539b = new n0(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2540a;

    public v(u uVar) {
        f1.a(uVar, "output");
        this.f2540a = uVar;
        uVar.f2535c = this;
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void A(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.F(i10, ((Long) list.get(i11)).longValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Long) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 8;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.G(((Long) list.get(i11)).longValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void B(float f10, int i10) {
        u uVar = (u) this.f2540a;
        uVar.getClass();
        uVar.D(i10, Float.floatToRawIntBits(f10));
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void C(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.Q(i10, ((Long) list.get(i11)).longValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iW = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            iW += u.w(((Long) list.get(i12)).longValue());
        }
        uVar.P(iW);
        while (i11 < list.size()) {
            uVar.R(((Long) list.get(i11)).longValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void D(int i10, long j10) {
        ((u) this.f2540a).Q(i10, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void E(int i10, boolean z) {
        ((u) this.f2540a).z(i10, z);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void F(int i10, int i11) {
        ((u) this.f2540a).D(i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void G(int i10) {
        ((u) this.f2540a).N(i10, 3);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void H(int i10, m mVar) {
        ((u) this.f2540a).B(i10, mVar);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void I(int i10) {
        ((u) this.f2540a).N(i10, 4);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void J(int i10, int i11) {
        ((u) this.f2540a).H(i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void K(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.z(i10, ((Boolean) list.get(i11)).booleanValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Boolean) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12++;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.y(((Boolean) list.get(i11)).booleanValue() ? (byte) 1 : (byte) 0);
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void L(int i10, int i11) {
        ((u) this.f2540a).O(i10, (i11 >> 31) ^ (i11 << 1));
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void M(int i10, List list) {
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((u) this.f2540a).B(i10, (m) list.get(i11));
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void a(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.O(i10, ((Integer) list.get(i11)).intValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iV = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            iV += u.v(((Integer) list.get(i12)).intValue());
        }
        uVar.P(iV);
        while (i11 < list.size()) {
            uVar.P(((Integer) list.get(i11)).intValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void b(int i10, androidx.appcompat.app.i1 i1Var, Map map) {
        u uVar = (u) this.f2540a;
        uVar.getClass();
        for (Map.Entry entry : map.entrySet()) {
            uVar.N(i10, 2);
            uVar.P(o1.a(i1Var, entry.getKey(), entry.getValue()));
            Object key = entry.getKey();
            Object value = entry.getValue();
            k0.e(uVar, (y3) i1Var.f1059l, 1, key);
            k0.e(uVar, (y3) i1Var.f1060m, 2, value);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void c(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                int iIntValue = ((Integer) list.get(i11)).intValue();
                uVar.O(i10, (iIntValue >> 31) ^ (iIntValue << 1));
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iV = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            int iIntValue2 = ((Integer) list.get(i12)).intValue();
            iV += u.v((iIntValue2 >> 31) ^ (iIntValue2 << 1));
        }
        uVar.P(iV);
        while (i11 < list.size()) {
            int iIntValue3 = ((Integer) list.get(i11)).intValue();
            uVar.P((iIntValue3 >> 31) ^ (iIntValue3 << 1));
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void d(int i10, int i11) {
        ((u) this.f2540a).D(i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void e(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.H(i10, ((Integer) list.get(i11)).intValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iR = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            iR += u.r(((Integer) list.get(i12)).intValue());
        }
        uVar.P(iR);
        while (i11 < list.size()) {
            uVar.I(((Integer) list.get(i11)).intValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void f(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                float fFloatValue = ((Float) list.get(i11)).floatValue();
                uVar.getClass();
                uVar.D(i10, Float.floatToRawIntBits(fFloatValue));
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Float) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 4;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.E(Float.floatToRawIntBits(((Float) list.get(i11)).floatValue()));
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void g(int i10, List list) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!(list instanceof i1)) {
            while (i11 < list.size()) {
                uVar.L(i10, (String) list.get(i11));
                i11++;
            }
            return;
        }
        i1 i1Var = (i1) list;
        while (i11 < list.size()) {
            Object objJ0 = i1Var.J0(i11);
            if (objJ0 instanceof String) {
                uVar.L(i10, (String) objJ0);
            } else {
                uVar.B(i10, (m) objJ0);
            }
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void h(int i10, List list, o2 o2Var) {
        for (int i11 = 0; i11 < list.size(); i11++) {
            r(i10, list.get(i11), o2Var);
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void i(int i10, String str) {
        ((u) this.f2540a).L(i10, str);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void j(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.D(i10, ((Integer) list.get(i11)).intValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Integer) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 4;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.E(((Integer) list.get(i11)).intValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void k(int i10, long j10) {
        ((u) this.f2540a).Q(i10, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void l(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.D(i10, ((Integer) list.get(i11)).intValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Integer) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 4;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.E(((Integer) list.get(i11)).intValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void m(int i10, int i11) {
        ((u) this.f2540a).H(i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void n(int i10, long j10) {
        ((u) this.f2540a).F(i10, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void o(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.H(i10, ((Integer) list.get(i11)).intValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iR = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            iR += u.r(((Integer) list.get(i12)).intValue());
        }
        uVar.P(iR);
        while (i11 < list.size()) {
            uVar.I(((Integer) list.get(i11)).intValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void p(int i10, Object obj, o2 o2Var) {
        ((u) this.f2540a).J(i10, (v1) obj, o2Var);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void q(int i10, long j10) {
        ((u) this.f2540a).Q(i10, (j10 >> 63) ^ (j10 << 1));
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void r(int i10, Object obj, o2 o2Var) {
        u uVar = (u) this.f2540a;
        uVar.N(i10, 3);
        o2Var.h((v1) obj, uVar.f2535c);
        uVar.N(i10, 4);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void s(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.F(i10, ((Long) list.get(i11)).longValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Long) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 8;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.G(((Long) list.get(i11)).longValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void t(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                long jLongValue = ((Long) list.get(i11)).longValue();
                uVar.Q(i10, (jLongValue >> 63) ^ (jLongValue << 1));
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iW = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            long jLongValue2 = ((Long) list.get(i12)).longValue();
            iW += u.w((jLongValue2 >> 63) ^ (jLongValue2 << 1));
        }
        uVar.P(iW);
        while (i11 < list.size()) {
            long jLongValue3 = ((Long) list.get(i11)).longValue();
            uVar.R((jLongValue3 >> 63) ^ (jLongValue3 << 1));
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void u(int i10, int i11) {
        ((u) this.f2540a).O(i10, i11);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void v(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                uVar.Q(i10, ((Long) list.get(i11)).longValue());
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int iW = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            iW += u.w(((Long) list.get(i12)).longValue());
        }
        uVar.P(iW);
        while (i11 < list.size()) {
            uVar.R(((Long) list.get(i11)).longValue());
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void w(int i10, double d4) {
        u uVar = (u) this.f2540a;
        uVar.getClass();
        uVar.F(i10, Double.doubleToRawLongBits(d4));
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void x(boolean z, List list, int i10) {
        u uVar = (u) this.f2540a;
        int i11 = 0;
        if (!z) {
            while (i11 < list.size()) {
                double dDoubleValue = ((Double) list.get(i11)).doubleValue();
                uVar.getClass();
                uVar.F(i10, Double.doubleToRawLongBits(dDoubleValue));
                i11++;
            }
            return;
        }
        uVar.N(i10, 2);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            ((Double) list.get(i13)).getClass();
            Logger logger = u.f2533d;
            i12 += 8;
        }
        uVar.P(i12);
        while (i11 < list.size()) {
            uVar.G(Double.doubleToRawLongBits(((Double) list.get(i11)).doubleValue()));
            i11++;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void y(int i10, long j10) {
        ((u) this.f2540a).F(i10, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.a4
    public void z(int i10, List list, o2 o2Var) {
        for (int i11 = 0; i11 < list.size(); i11++) {
            p(i10, list.get(i11), o2Var);
        }
    }

    public v() {
        u1 u1Var;
        try {
            u1Var = (u1) Class.forName("androidx.datastore.preferences.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            u1Var = f2539b;
        }
        u1[] u1VarArr = {n0.f2479b, u1Var};
        n1 n1Var = new n1();
        n1Var.f2481a = u1VarArr;
        Charset charset = f1.f2421a;
        this.f2540a = n1Var;
    }
}

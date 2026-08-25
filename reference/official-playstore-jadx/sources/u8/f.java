package u8;

import h9.h;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import p9.n;
import v9.a0;
import v9.b0;
import v9.d1;
import v9.k0;
import v9.q;
import v9.r0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends q implements a0 {
    public f(b0 b0Var, b0 b0Var2, int i10) {
        super(b0Var, b0Var2);
        w9.c.f22506a.d(b0Var, b0Var2);
    }

    public static final ArrayList R0(h hVar, w wVar) throws IOException {
        List<r0> listG0 = wVar.G0();
        ArrayList arrayList = new ArrayList(s.U(listG0, 10));
        for (r0 r0Var : listG0) {
            StringBuilder sb2 = new StringBuilder();
            x.t0(Collections.singletonList(r0Var), sb2, ", ", null, null, new h9.f(hVar, 0), 60);
            arrayList.add(sb2.toString());
        }
        return arrayList;
    }

    public static final String S0(String str, String str2) {
        if (!o.U(str, '<')) {
            return str;
        }
        return o.G0('<', str, str) + '<' + str2 + '>' + o.E0('>', str, str);
    }

    @Override // v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        return new f(this.f22241l, this.f22242m);
    }

    @Override // v9.d1
    public final d1 M0(boolean z) {
        return new f(this.f22241l.M0(z), this.f22242m.M0(z), 0);
    }

    @Override // v9.d1
    public final d1 N0(w9.e eVar) {
        return new f(this.f22241l, this.f22242m);
    }

    @Override // v9.d1
    public final d1 O0(k0 k0Var) {
        return new f(this.f22241l.O0(k0Var), this.f22242m.O0(k0Var), 0);
    }

    @Override // v9.q
    public final b0 P0() {
        return this.f22241l;
    }

    @Override // v9.q
    public final String Q0(h hVar, h hVar2) throws IOException {
        b0 b0Var = this.f22241l;
        String strV = hVar.V(b0Var);
        b0 b0Var2 = this.f22242m;
        String strV2 = hVar.V(b0Var2);
        if (hVar2.f15910a.n()) {
            return "raw (" + strV + ".." + strV2 + ')';
        }
        if (b0Var2.G0().isEmpty()) {
            return hVar.E(strV, strV2, I0().j());
        }
        ArrayList arrayListR0 = R0(hVar, b0Var);
        ArrayList arrayListR02 = R0(hVar, b0Var2);
        String strU0 = x.u0(arrayListR0, ", ", null, null, e.f22072i, 30);
        ArrayList<x6.x> arrayListI1 = x.i1(arrayListR0, arrayListR02);
        if (arrayListI1.isEmpty()) {
            strV2 = S0(strV2, strU0);
        } else {
            for (x6.x xVar : arrayListI1) {
                String str = (String) xVar.f22608i;
                String str2 = (String) xVar.f22609l;
                if (!p.a(str, o.r0(str2, "out ")) && !str2.equals("*")) {
                    break;
                }
            }
            strV2 = S0(strV2, strU0);
        }
        String strS0 = S0(strV, strU0);
        return p.a(strS0, strV2) ? strS0 : hVar.E(strS0, strV2, I0().j());
    }

    @Override // v9.q, v9.w
    public final n l() {
        g8.h hVarE = I0().e();
        g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
        if (fVar != null) {
            return fVar.x(new d());
        }
        throw new IllegalStateException(("Incorrect classifier: " + I0().e()).toString());
    }
}

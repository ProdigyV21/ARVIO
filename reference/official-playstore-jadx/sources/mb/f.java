package mb;

import com.google.android.gms.common.api.internal.g0;
import gb.b0;
import gb.c0;
import gb.k0;
import gb.p0;
import java.util.ArrayList;
import kotlin.jvm.internal.p;
import lb.i;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements c0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f20362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f20363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f20364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g0 f20365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k0 f20366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f20367f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f20368g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f20369h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20370i;

    public f(i iVar, ArrayList arrayList, int i10, g0 g0Var, k0 k0Var, int i11, int i12, int i13) {
        this.f20362a = iVar;
        this.f20363b = arrayList;
        this.f20364c = i10;
        this.f20365d = g0Var;
        this.f20366e = k0Var;
        this.f20367f = i11;
        this.f20368g = i12;
        this.f20369h = i13;
    }

    public static f b(f fVar, int i10, g0 g0Var, k0 k0Var, int i11) {
        if ((i11 & 1) != 0) {
            i10 = fVar.f20364c;
        }
        int i12 = i10;
        if ((i11 & 2) != 0) {
            g0Var = fVar.f20365d;
        }
        g0 g0Var2 = g0Var;
        if ((i11 & 4) != 0) {
            k0Var = fVar.f20366e;
        }
        int i13 = fVar.f20367f;
        int i14 = fVar.f20368g;
        int i15 = fVar.f20369h;
        return new f(fVar.f20362a, fVar.f20363b, i12, g0Var2, k0Var, i13, i14, i15);
    }

    @Override // gb.c0.a
    public final p0 a(k0 k0Var) {
        ArrayList arrayList = this.f20363b;
        int size = arrayList.size();
        int i10 = this.f20364c;
        if (i10 >= size) {
            throw new IllegalStateException("Check failed.");
        }
        this.f20370i++;
        g0 g0Var = this.f20365d;
        if (g0Var != null) {
            lb.e eVar = (lb.e) g0Var.f12736d;
            b0 b0Var = k0Var.f15730a;
            b0 b0Var2 = eVar.f20096b.f15612h;
            if (b0Var.f15621e != b0Var2.f15621e || !p.a(b0Var.f15620d, b0Var2.f15620d)) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i10 - 1) + " must retain the same host and port").toString());
            }
            if (this.f20370i != 1) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i10 - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i11 = i10 + 1;
        f fVarB = b(this, i11, null, k0Var, 58);
        c0 c0Var = (c0) arrayList.get(i10);
        p0 p0VarIntercept = c0Var.intercept(fVarB);
        if (p0VarIntercept == null) {
            throw new NullPointerException("interceptor " + c0Var + " returned null");
        }
        if (g0Var != null && i11 < arrayList.size() && fVarB.f20370i != 1) {
            throw new IllegalStateException(("network interceptor " + c0Var + " must call proceed() exactly once").toString());
        }
        if (p0VarIntercept.f15786q != null) {
            return p0VarIntercept;
        }
        throw new IllegalStateException(("interceptor " + c0Var + " returned a response with no body").toString());
    }

    @Override // gb.c0.a
    public final k0 request() {
        return this.f20366e;
    }
}

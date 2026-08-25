package gb;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f15730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a0 f15732c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n0 f15733d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f15734e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f15735f;

    public k0(b0 b0Var, String str, a0 a0Var, n0 n0Var, Map map) {
        this.f15730a = b0Var;
        this.f15731b = str;
        this.f15732c = a0Var;
        this.f15733d = n0Var;
        this.f15734e = map;
    }

    public final h a() {
        h hVar = this.f15735f;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = h.f15686n;
        h hVarA = kotlin.reflect.b0.A(this.f15732c);
        this.f15735f = hVarA;
        return hVarA;
    }

    public final j0 b() {
        j0 j0Var = new j0();
        j0Var.f15726e = new LinkedHashMap();
        j0Var.f15722a = this.f15730a;
        j0Var.f15723b = this.f15731b;
        j0Var.f15725d = this.f15733d;
        Map map = this.f15734e;
        j0Var.f15726e = map.isEmpty() ? new LinkedHashMap() : new LinkedHashMap(map);
        j0Var.f15724c = this.f15732c.c();
        return j0Var;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Request{method=");
        sb2.append(this.f15731b);
        sb2.append(", url=");
        sb2.append(this.f15730a);
        a0 a0Var = this.f15732c;
        if (a0Var.size() != 0) {
            sb2.append(", headers=[");
            int i10 = 0;
            for (Object obj : a0Var) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    t7.a.Q();
                    throw null;
                }
                x6.x xVar = (x6.x) obj;
                String str = (String) xVar.f22608i;
                String str2 = (String) xVar.f22609l;
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(str);
                sb2.append(':');
                sb2.append(str2);
                i10 = i11;
            }
            sb2.append(']');
        }
        Map map = this.f15734e;
        if (!map.isEmpty()) {
            sb2.append(", tags=");
            sb2.append(map);
        }
        sb2.append('}');
        return sb2.toString();
    }
}

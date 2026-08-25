package retrofit2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21503b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f21504c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f21505d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l f21506e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f21507f;

    public g0(Method method, int i10, gb.a0 a0Var, l lVar) {
        this.f21504c = method;
        this.f21505d = i10;
        this.f21507f = a0Var;
        this.f21506e = lVar;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        int i10 = this.f21503b;
        l lVar = this.f21506e;
        Object obj2 = this.f21507f;
        Method method = this.f21504c;
        int i11 = this.f21505d;
        switch (i10) {
            case 0:
                if (obj == null) {
                    return;
                }
                try {
                    o0Var.c((gb.a0) obj2, (gb.n0) lVar.w(obj));
                    return;
                } catch (IOException e5) {
                    throw b1.k(method, i11, "Unable to convert " + obj + " to RequestBody", e5);
                }
            default:
                Map map = (Map) obj;
                if (map == null) {
                    throw b1.k(method, i11, "Part map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw b1.k(method, i11, "Part map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw b1.k(method, i11, a0.c.l("Part map contained null value for key '", str, "'."), new Object[0]);
                    }
                    o0Var.c(t7.a.H("Content-Disposition", a0.c.l("form-data; name=\"", str, "\""), "Content-Transfer-Encoding", (String) obj2), (gb.n0) lVar.w(value));
                }
                return;
        }
    }

    public g0(Method method, int i10, l lVar, String str) {
        this.f21504c = method;
        this.f21505d = i10;
        this.f21506e = lVar;
        this.f21507f = str;
    }
}

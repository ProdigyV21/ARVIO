package retrofit2;

import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f21499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f21500d;

    public /* synthetic */ f0(Method method, int i10, int i11) {
        this.f21498b = i11;
        this.f21499c = method;
        this.f21500d = i10;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        switch (this.f21498b) {
            case 0:
                Map map = (Map) obj;
                int i10 = this.f21500d;
                Method method = this.f21499c;
                if (map == null) {
                    throw b1.k(method, i10, "Header map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw b1.k(method, i10, "Header map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw b1.k(method, i10, a0.c.l("Header map contained null value for key '", str, "'."), new Object[0]);
                    }
                    o0Var.b(str, value.toString());
                }
                return;
            case 1:
                gb.a0 a0Var = (gb.a0) obj;
                if (a0Var == null) {
                    throw b1.k(this.f21499c, this.f21500d, "Headers parameter must not be null.", new Object[0]);
                }
                gb.z zVar = o0Var.f21535f;
                zVar.getClass();
                int size = a0Var.size();
                for (int i11 = 0; i11 < size; i11++) {
                    zVar.c(a0Var.b(i11), a0Var.e(i11));
                }
                return;
            default:
                if (obj == null) {
                    throw b1.k(this.f21499c, this.f21500d, "@Url parameter is null.", new Object[0]);
                }
                o0Var.f21532c = obj.toString();
                return;
        }
    }
}

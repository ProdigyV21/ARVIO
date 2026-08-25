package retrofit2;

import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21492b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f21493c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f21494d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21495e;

    public /* synthetic */ d0(Method method, int i10, boolean z, int i11) {
        this.f21492b = i11;
        this.f21493c = method;
        this.f21494d = i10;
        this.f21495e = z;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        switch (this.f21492b) {
            case 0:
                Map map = (Map) obj;
                int i10 = this.f21494d;
                Method method = this.f21493c;
                if (map == null) {
                    throw b1.k(method, i10, "Field map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw b1.k(method, i10, "Field map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw b1.k(method, i10, a0.c.l("Field map contained null value for key '", str, "'."), new Object[0]);
                    }
                    String string = value.toString();
                    if (string == null) {
                        throw b1.k(method, i10, "Field map value '" + value + "' converted to null by " + a.class.getName() + " for key '" + str + "'.", new Object[0]);
                    }
                    o0Var.a(str, string, this.f21495e);
                }
                return;
            default:
                Map map2 = (Map) obj;
                int i11 = this.f21494d;
                Method method2 = this.f21493c;
                if (map2 == null) {
                    throw b1.k(method2, i11, "Query map was null", new Object[0]);
                }
                for (Map.Entry entry2 : map2.entrySet()) {
                    String str2 = (String) entry2.getKey();
                    if (str2 == null) {
                        throw b1.k(method2, i11, "Query map contained null key.", new Object[0]);
                    }
                    Object value2 = entry2.getValue();
                    if (value2 == null) {
                        throw b1.k(method2, i11, a0.c.l("Query map contained null value for key '", str2, "'."), new Object[0]);
                    }
                    String string2 = value2.toString();
                    if (string2 == null) {
                        throw b1.k(method2, i11, "Query map value '" + value2 + "' converted to null by " + a.class.getName() + " for key '" + str2 + "'.", new Object[0]);
                    }
                    o0Var.d(str2, string2, this.f21495e);
                }
                return;
        }
    }
}

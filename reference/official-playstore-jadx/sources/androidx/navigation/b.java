package androidx.navigation;

import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f4521l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f4522m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f4523n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f4524o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f4525p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final b f4526q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4527i;

    static {
        int i10 = 1;
        f4521l = new b(i10, 0);
        f4522m = new b(i10, 1);
        f4523n = new b(i10, 2);
        f4524o = new b(i10, 3);
        f4525p = new b(i10, 4);
        f4526q = new b(i10, 5);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i10, int i11) {
        super(i10);
        this.f4527i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4527i) {
            case 0:
                Context context = (Context) obj;
                if (context instanceof ContextWrapper) {
                    return ((ContextWrapper) context).getBaseContext();
                }
                return null;
            case 1:
                Context context2 = (Context) obj;
                if (context2 instanceof ContextWrapper) {
                    return ((ContextWrapper) context2).getBaseContext();
                }
                return null;
            case 2:
                d0 d0Var = (d0) obj;
                h0 h0Var = d0Var.f4658l;
                if (h0Var == null || h0Var.f4677t != d0Var.f4662p) {
                    return null;
                }
                return h0Var;
            case 3:
                d0 d0Var2 = (d0) obj;
                h0 h0Var2 = d0Var2.f4658l;
                if (h0Var2 == null || h0Var2.f4677t != d0Var2.f4662p) {
                    return null;
                }
                return h0Var2;
            case 4:
                return ((d0) obj).f4658l;
            default:
                d0 d0Var3 = (d0) obj;
                if (!(d0Var3 instanceof h0)) {
                    return null;
                }
                h0 h0Var3 = (h0) d0Var3;
                return h0Var3.f(h0Var3.f4677t, true);
        }
    }
}

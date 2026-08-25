package w5;

import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q0 f22444a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r7.a f22445b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f22446c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f22447d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y f22448e;

    public h0() {
        g0 g0Var = g0.f22436i;
        this.f22444a = q0.f22484a;
        this.f22445b = g0Var;
        this.f22446c = kotlin.text.u.O(((UUID) g0Var.invoke()).toString(), "-", "", false).toLowerCase(Locale.ROOT);
        this.f22447d = -1;
    }

    public final y a() {
        y yVar = this.f22448e;
        if (yVar != null) {
            return yVar;
        }
        kotlin.jvm.internal.p.i("currentSession");
        throw null;
    }
}

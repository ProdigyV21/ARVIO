package androidx.core.view;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f2229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f2230b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f2231c = new HashMap();

    public f0(Runnable runnable) {
        this.f2229a = runnable;
    }

    public final void a(i0 i0Var) {
        this.f2230b.remove(i0Var);
        e0 e0Var = (e0) this.f2231c.remove(i0Var);
        if (e0Var != null) {
            e0Var.f2220a.removeObserver(e0Var.f2221b);
            e0Var.f2221b = null;
        }
        this.f2229a.run();
    }
}

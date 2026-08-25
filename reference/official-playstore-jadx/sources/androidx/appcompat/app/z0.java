package androidx.appcompat.app;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class z0 implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f1220i = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayDeque f1221l = new ArrayDeque();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a1 f1222m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Runnable f1223n;

    public z0(a1 a1Var) {
        this.f1222m = a1Var;
    }

    public final void a() {
        synchronized (this.f1220i) {
            try {
                Runnable runnable = (Runnable) this.f1221l.poll();
                this.f1223n = runnable;
                if (runnable != null) {
                    this.f1222m.execute(runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        synchronized (this.f1220i) {
            try {
                this.f1221l.add(new androidx.activity.s(this, runnable, 1));
                if (this.f1223n == null) {
                    a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

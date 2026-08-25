package androidx.work.impl.utils;

import com.google.common.util.concurrent.w0;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class l implements androidx.work.impl.utils.taskexecutor.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Executor f7045l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Runnable f7046m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayDeque f7044i = new ArrayDeque();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f7047n = new Object();

    public l(Executor executor) {
        this.f7045l = executor;
    }

    public final boolean a() {
        boolean z;
        synchronized (this.f7047n) {
            z = !this.f7044i.isEmpty();
        }
        return z;
    }

    public final void b() {
        Runnable runnable = (Runnable) this.f7044i.poll();
        this.f7046m = runnable;
        if (runnable != null) {
            this.f7045l.execute(runnable);
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        synchronized (this.f7047n) {
            try {
                this.f7044i.add(new w0(this, runnable, 9));
                if (this.f7046m == null) {
                    b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

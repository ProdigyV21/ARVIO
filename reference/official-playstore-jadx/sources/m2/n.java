package m2;

import com.google.common.util.concurrent.d1;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import ka.x1;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements d1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.work.impl.utils.futures.k f20211i = new androidx.work.impl.utils.futures.k();

    public n(x1 x1Var) {
        x1Var.invokeOnCompletion(new ab.h(this, 24));
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        this.f20211i.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return this.f20211i.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f20211i.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f20211i.f7037i instanceof androidx.work.impl.utils.futures.a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f20211i.isDone();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        return this.f20211i.get(j10, timeUnit);
    }
}

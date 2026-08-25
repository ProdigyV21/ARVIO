package e0;

import com.google.common.util.concurrent.d1;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class k implements d1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final WeakReference f14998i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j f14999l = new j(this);

    public k(h hVar) {
        this.f14998i = new WeakReference(hVar);
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        this.f14999l.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        h hVar = (h) this.f14998i.get();
        boolean zCancel = this.f14999l.cancel(z);
        if (zCancel && hVar != null) {
            hVar.f14993a = null;
            hVar.f14994b = null;
            hVar.f14995c.n(null);
        }
        return zCancel;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f14999l.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f14999l.f14990i instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f14999l.isDone();
    }

    public final String toString() {
        return this.f14999l.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        return this.f14999l.get(j10, timeUnit);
    }
}

package ka;

import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 implements z0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ScheduledFuture f19658i;

    public y0(ScheduledFuture scheduledFuture) {
        this.f19658i = scheduledFuture;
    }

    @Override // ka.z0
    public final void dispose() {
        this.f19658i.cancel(false);
    }

    public final String toString() {
        return "DisposableFutureHandle[" + this.f19658i + ']';
    }
}

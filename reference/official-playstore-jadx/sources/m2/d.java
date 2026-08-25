package m2;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicInteger f20183a = new AtomicInteger(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f20184b;

    public d(boolean z) {
        this.f20184b = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        StringBuilder sbS = androidx.compose.material3.d.s(this.f20184b ? "WM.task-" : "androidx.work-");
        sbS.append(this.f20183a.incrementAndGet());
        return new Thread(runnable, sbS.toString());
    }
}

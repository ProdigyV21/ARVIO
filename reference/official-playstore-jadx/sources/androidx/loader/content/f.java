package androidx.loader.content;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicInteger f3340b;

    public f(int i10) {
        this.f3339a = i10;
        switch (i10) {
            case 1:
                this.f3340b = new AtomicInteger(0);
                break;
            default:
                this.f3340b = new AtomicInteger(1);
                break;
        }
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f3339a) {
            case 0:
                return new Thread(runnable, "ModernAsyncTask #" + this.f3340b.getAndIncrement());
            default:
                Thread thread = new Thread(runnable);
                thread.setName("arch_disk_io_" + this.f3340b.getAndIncrement());
                return thread;
        }
    }
}

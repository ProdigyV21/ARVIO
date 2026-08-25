package ib;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f16266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f16267b;

    public /* synthetic */ b(String str, boolean z) {
        this.f16266a = str;
        this.f16267b = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f16266a);
        thread.setDaemon(this.f16267b);
        return thread;
    }
}

package androidx.core.provider;

import android.os.Process;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends Thread {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2166i;

    public m(Runnable runnable) {
        super(runnable, "fonts-androidx");
        this.f2166i = 10;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.f2166i);
        super.run();
    }
}

package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.PreCacheHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3813i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreCacheHelper.DownloadCallback f3814l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PreCacheHelper.Task f3815m;

    public /* synthetic */ p(PreCacheHelper.DownloadCallback downloadCallback, PreCacheHelper.Task task, int i10) {
        this.f3813i = i10;
        this.f3814l = downloadCallback;
        this.f3815m = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3813i) {
            case 0:
                this.f3814l.lambda$onDownloadProgress$5(this.f3815m);
                break;
            default:
                this.f3814l.lambda$onDownloadStopped$3(this.f3815m);
                break;
        }
    }
}

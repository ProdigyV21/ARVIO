package androidx.media3.exoplayer.scheduler;

import androidx.media3.exoplayer.scheduler.RequirementsWatcher;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3741i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ RequirementsWatcher.NetworkCallback f3742l;

    public /* synthetic */ a(RequirementsWatcher.NetworkCallback networkCallback, int i10) {
        this.f3741i = i10;
        this.f3742l = networkCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3741i) {
            case 0:
                this.f3742l.lambda$postCheckRequirements$0();
                break;
            default:
                this.f3742l.lambda$postRecheckNotMetNetworkRequirements$1();
                break;
        }
    }
}

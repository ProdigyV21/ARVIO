package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.PreCacheHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3816i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreCacheHelper.ReleasableExecutorSupplier f3817l;

    public /* synthetic */ r(PreCacheHelper.ReleasableExecutorSupplier releasableExecutorSupplier, int i10) {
        this.f3816i = i10;
        this.f3817l = releasableExecutorSupplier;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3816i) {
            case 0:
                this.f3817l.lambda$onExecutorReleased$0();
                break;
            default:
                this.f3817l.onExecutorReleased();
                break;
        }
    }
}

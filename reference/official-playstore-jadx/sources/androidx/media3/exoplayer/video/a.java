package androidx.media3.exoplayer.video;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3850i;

    public /* synthetic */ a(int i10) {
        this.f3850i = i10;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f3850i) {
            case 0:
                DefaultVideoSink.lambda$new$0(runnable);
                break;
            default:
                PlaybackVideoGraphWrapper.lambda$static$0(runnable);
                break;
        }
    }
}

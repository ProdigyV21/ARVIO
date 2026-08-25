package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoFrameReleaseHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3851i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3852l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f3851i = i10;
        this.f3852l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3851i) {
            case 0:
                ((DefaultVideoSink) this.f3852l).lambda$handleInputFrame$2();
                break;
            case 1:
                ((VideoFrameReleaseHelper.VSyncSamplerV33) this.f3852l).lambda$onVsync$0();
                break;
            default:
                ((PlaybackVideoGraphWrapper) this.f3852l).lambda$flush$1();
                break;
        }
    }
}

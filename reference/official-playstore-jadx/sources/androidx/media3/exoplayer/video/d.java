package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.DefaultVideoSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3853i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DefaultVideoSink.FrameRendererImpl f3854l;

    public /* synthetic */ d(DefaultVideoSink.FrameRendererImpl frameRendererImpl, int i10) {
        this.f3853i = i10;
        this.f3854l = frameRendererImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3853i) {
            case 0:
                this.f3854l.lambda$renderFrame$1();
                break;
            default:
                this.f3854l.lambda$dropFrame$2();
                break;
        }
    }
}

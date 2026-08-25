package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3862i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ VideoSink.Listener f3863l;

    public /* synthetic */ g(VideoSink.Listener listener, int i10) {
        this.f3862i = i10;
        this.f3863l = listener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3862i) {
            case 0:
                this.f3863l.onFrameDropped();
                break;
            case 1:
                this.f3863l.onFirstFrameRendered();
                break;
            default:
                this.f3863l.onFrameAvailableForRendering();
                break;
        }
    }
}

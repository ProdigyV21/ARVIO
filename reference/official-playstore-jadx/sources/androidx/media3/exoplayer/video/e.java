package androidx.media3.exoplayer.video;

import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.CodecParameters;
import androidx.media3.exoplayer.video.DefaultVideoSink;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3855i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3856l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3857m;

    public /* synthetic */ e(Object obj, Object obj2, int i10) {
        this.f3855i = i10;
        this.f3857m = obj;
        this.f3856l = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3855i) {
            case 0:
                ((DefaultVideoSink.FrameRendererImpl) this.f3857m).lambda$onVideoSizeChanged$0((VideoSize) this.f3856l);
                break;
            case 1:
                ((VideoSink.Listener) this.f3857m).onVideoSizeChanged((VideoSize) this.f3856l);
                break;
            case 2:
                ((VideoRendererEventListener.EventDispatcher) this.f3857m).lambda$decoderReleased$7((String) this.f3856l);
                break;
            case 3:
                ((VideoRendererEventListener.EventDispatcher) this.f3857m).lambda$videoSizeChanged$5((VideoSize) this.f3856l);
                break;
            case 4:
                ((VideoRendererEventListener.EventDispatcher) this.f3857m).lambda$videoCodecError$9((Exception) this.f3856l);
                break;
            default:
                ((VideoRendererEventListener.EventDispatcher) this.f3857m).lambda$videoCodecParametersChanged$10((CodecParameters) this.f3856l);
                break;
        }
    }
}

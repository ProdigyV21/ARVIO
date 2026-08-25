package androidx.media3.exoplayer.video;

import androidx.media3.common.Format;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3858i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3859l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3860m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3861n;

    public /* synthetic */ f(Object obj, Object obj2, Object obj3, int i10) {
        this.f3858i = i10;
        this.f3859l = obj;
        this.f3860m = obj2;
        this.f3861n = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3858i) {
            case 0:
                ((PlaybackVideoGraphWrapper.InputVideoSink) this.f3859l).lambda$onError$1((VideoSink.Listener) this.f3860m, (VideoFrameProcessingException) this.f3861n);
                break;
            default:
                ((VideoRendererEventListener.EventDispatcher) this.f3859l).lambda$inputFormatChanged$2((Format) this.f3860m, (DecoderReuseEvaluation) this.f3861n);
                break;
        }
    }
}

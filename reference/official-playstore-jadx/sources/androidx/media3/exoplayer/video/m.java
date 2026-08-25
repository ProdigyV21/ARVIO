package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3872i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f3873l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f3874m;

    public /* synthetic */ m(VideoRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters, int i10) {
        this.f3872i = i10;
        this.f3873l = eventDispatcher;
        this.f3874m = decoderCounters;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3872i) {
            case 0:
                this.f3873l.lambda$enabled$0(this.f3874m);
                break;
            default:
                this.f3873l.lambda$disabled$8(this.f3874m);
                break;
        }
    }
}

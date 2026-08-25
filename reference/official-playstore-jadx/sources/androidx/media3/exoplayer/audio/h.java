package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3556i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f3557l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AudioSink.AudioTrackConfig f3558m;

    public /* synthetic */ h(AudioRendererEventListener.EventDispatcher eventDispatcher, AudioSink.AudioTrackConfig audioTrackConfig, int i10) {
        this.f3556i = i10;
        this.f3557l = eventDispatcher;
        this.f3558m = audioTrackConfig;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3556i) {
            case 0:
                this.f3557l.lambda$audioTrackInitialized$10(this.f3558m);
                break;
            default:
                this.f3557l.lambda$audioTrackReleased$11(this.f3558m);
                break;
        }
    }
}

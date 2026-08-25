package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3543i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f3544l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f3545m;

    public /* synthetic */ d(AudioRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters, int i10) {
        this.f3543i = i10;
        this.f3544l = eventDispatcher;
        this.f3545m = decoderCounters;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3543i) {
            case 0:
                this.f3544l.lambda$enabled$0(this.f3545m);
                break;
            default:
                this.f3544l.lambda$disabled$6(this.f3545m);
                break;
        }
    }
}

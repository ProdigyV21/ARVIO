package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3549i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f3550l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3551m;

    public /* synthetic */ f(Object obj, long j10, int i10) {
        this.f3549i = i10;
        this.f3551m = obj;
        this.f3550l = j10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3549i) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f3551m).lambda$positionAdvancing$3(this.f3550l);
                break;
            default:
                ((PreloadMediaSource) this.f3551m).lambda$preload$0(this.f3550l);
                break;
        }
    }
}

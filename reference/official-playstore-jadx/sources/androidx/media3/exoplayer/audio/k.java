package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3565i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f3566l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3567m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f3568n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3569o;

    public /* synthetic */ k(Object obj, String str, long j10, long j11, int i10) {
        this.f3565i = i10;
        this.f3569o = obj;
        this.f3566l = str;
        this.f3567m = j10;
        this.f3568n = j11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3565i) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f3569o).lambda$decoderInitialized$1(this.f3566l, this.f3567m, this.f3568n);
                break;
            default:
                ((VideoRendererEventListener.EventDispatcher) this.f3569o).lambda$decoderInitialized$1(this.f3566l, this.f3567m, this.f3568n);
                break;
        }
    }
}

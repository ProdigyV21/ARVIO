package androidx.media3.exoplayer.hls;

import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3664i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3665l;

    public /* synthetic */ m(Object obj, int i10) {
        this.f3664i = i10;
        this.f3665l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3664i) {
            case 0:
                ((HlsSampleStreamWrapper) this.f3665l).maybeFinishPrepare();
                break;
            case 1:
                ((HlsSampleStreamWrapper) this.f3665l).onTracksEnded();
                break;
            default:
                ((HlsSampleStreamWrapper.Callback) this.f3665l).onPrepared();
                break;
        }
    }
}

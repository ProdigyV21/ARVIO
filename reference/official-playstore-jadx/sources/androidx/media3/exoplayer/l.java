package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3681i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ BandwidthMeter f3682l;

    public /* synthetic */ l(BandwidthMeter bandwidthMeter, int i10) {
        this.f3681i = i10;
        this.f3682l = bandwidthMeter;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3681i) {
            case 0:
                return ExoPlayer.Builder.lambda$setBandwidthMeter$20(this.f3682l);
            default:
                return ExoPlayer.Builder.lambda$new$12(this.f3682l);
        }
    }
}

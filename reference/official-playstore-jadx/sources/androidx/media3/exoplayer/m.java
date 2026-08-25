package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.trackselection.TrackSelector;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3687i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TrackSelector f3688l;

    public /* synthetic */ m(TrackSelector trackSelector, int i10) {
        this.f3687i = i10;
        this.f3688l = trackSelector;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3687i) {
            case 0:
                return ExoPlayer.Builder.lambda$new$10(this.f3688l);
            default:
                return ExoPlayer.Builder.lambda$setTrackSelector$18(this.f3688l);
        }
    }
}

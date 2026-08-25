package androidx.media3.common;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.util.Size;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class t implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3401i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SimpleBasePlayer.State f3402l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3403m;

    public /* synthetic */ t(SimpleBasePlayer.State state, Object obj, int i10) {
        this.f3401i = i10;
        this.f3402l = state;
        this.f3403m = obj;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3401i) {
            case 0:
                return SimpleBasePlayer.lambda$setVideoTextureView$22(this.f3402l, (Size) this.f3403m);
            case 1:
                return SimpleBasePlayer.lambda$setPlaylistMetadata$15(this.f3402l, (MediaMetadata) this.f3403m);
            case 2:
                return SimpleBasePlayer.lambda$setVideoSurfaceHolder$20(this.f3402l, (SurfaceHolder) this.f3403m);
            case 3:
                return SimpleBasePlayer.lambda$setVideoSurfaceView$21(this.f3402l, (SurfaceView) this.f3403m);
            case 4:
                return SimpleBasePlayer.lambda$setAudioAttributes$32(this.f3402l, (AudioAttributes) this.f3403m);
            case 5:
                return ((SimpleBasePlayer) this.f3403m).lambda$stop$12(this.f3402l);
            case 6:
                return SimpleBasePlayer.lambda$setPlaybackParameters$11(this.f3402l, (PlaybackParameters) this.f3403m);
            default:
                return SimpleBasePlayer.lambda$setTrackSelectionParameters$14(this.f3402l, (TrackSelectionParameters) this.f3403m);
        }
    }

    public /* synthetic */ t(SimpleBasePlayer simpleBasePlayer, SimpleBasePlayer.State state) {
        this.f3401i = 5;
        this.f3403m = simpleBasePlayer;
        this.f3402l = state;
    }
}

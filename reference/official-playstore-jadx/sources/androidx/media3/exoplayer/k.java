package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3678i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSource.Factory f3679l;

    public /* synthetic */ k(MediaSource.Factory factory, int i10) {
        this.f3678i = i10;
        this.f3679l = factory;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3678i) {
            case 0:
                return ExoPlayer.Builder.lambda$new$7(this.f3679l);
            case 1:
                return ExoPlayer.Builder.lambda$new$9(this.f3679l);
            case 2:
                return ExoPlayer.Builder.lambda$new$5(this.f3679l);
            default:
                return ExoPlayer.Builder.lambda$setMediaSourceFactory$17(this.f3679l);
        }
    }
}

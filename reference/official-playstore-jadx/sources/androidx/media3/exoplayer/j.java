package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3672i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ RenderersFactory f3673l;

    public /* synthetic */ j(RenderersFactory renderersFactory, int i10) {
        this.f3672i = i10;
        this.f3673l = renderersFactory;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3672i) {
            case 0:
                return ExoPlayer.Builder.lambda$setRenderersFactory$16(this.f3673l);
            case 1:
                return ExoPlayer.Builder.lambda$new$6(this.f3673l);
            case 2:
                return ExoPlayer.Builder.lambda$new$2(this.f3673l);
            default:
                return ExoPlayer.Builder.lambda$new$8(this.f3673l);
        }
    }
}

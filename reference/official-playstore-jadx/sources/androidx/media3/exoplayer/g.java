package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3627i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ LoadControl f3628l;

    public /* synthetic */ g(LoadControl loadControl, int i10) {
        this.f3627i = i10;
        this.f3628l = loadControl;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3627i) {
            case 0:
                return ExoPlayer.Builder.lambda$setLoadControl$19(this.f3628l);
            default:
                return ExoPlayer.Builder.lambda$new$11(this.f3628l);
        }
    }
}

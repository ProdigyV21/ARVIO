package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3382i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SimpleBasePlayer.State f3383l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3384m;

    public /* synthetic */ n(SimpleBasePlayer.State state, boolean z, int i10) {
        this.f3382i = i10;
        this.f3383l = state;
        this.f3384m = z;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3382i) {
            case 0:
                return SimpleBasePlayer.lambda$setPlayWhenReady$1(this.f3383l, this.f3384m);
            case 1:
                return SimpleBasePlayer.lambda$setShuffleModeEnabled$9(this.f3383l, this.f3384m);
            case 2:
                return SimpleBasePlayer.lambda$setDeviceMuted$30(this.f3383l, this.f3384m);
            default:
                return SimpleBasePlayer.lambda$setDeviceMuted$31(this.f3383l, this.f3384m);
        }
    }
}

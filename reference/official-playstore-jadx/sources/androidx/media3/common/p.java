package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3388i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SimpleBasePlayer.State f3389l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3390m;

    public /* synthetic */ p(SimpleBasePlayer.State state, int i10, int i11) {
        this.f3388i = i11;
        this.f3389l = state;
        this.f3390m = i10;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3388i) {
            case 0:
                return SimpleBasePlayer.lambda$setDeviceVolume$24(this.f3389l, this.f3390m);
            case 1:
                return SimpleBasePlayer.lambda$setDeviceVolume$25(this.f3389l, this.f3390m);
            default:
                return SimpleBasePlayer.lambda$setRepeatMode$8(this.f3389l, this.f3390m);
        }
    }
}

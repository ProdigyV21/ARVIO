package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3374i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SimpleBasePlayer.State f3375l;

    public /* synthetic */ k(SimpleBasePlayer.State state, int i10) {
        this.f3374i = i10;
        this.f3375l = state;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3374i) {
            case 0:
                return SimpleBasePlayer.lambda$unmute$18(this.f3375l);
            case 1:
                return SimpleBasePlayer.lambda$mute$17(this.f3375l);
            case 2:
                return SimpleBasePlayer.lambda$setVideoSurface$19(this.f3375l);
            case 3:
                return SimpleBasePlayer.lambda$increaseDeviceVolume$27(this.f3375l);
            case 4:
                return SimpleBasePlayer.lambda$decreaseDeviceVolume$28(this.f3375l);
            case 5:
                return SimpleBasePlayer.lambda$release$13(this.f3375l);
            case 6:
                return SimpleBasePlayer.lambda$clearVideoOutput$23(this.f3375l);
            case 7:
                return SimpleBasePlayer.lambda$increaseDeviceVolume$26(this.f3375l);
            case 8:
                return SimpleBasePlayer.lambda$prepare$7(this.f3375l);
            default:
                return SimpleBasePlayer.lambda$decreaseDeviceVolume$29(this.f3375l);
        }
    }
}

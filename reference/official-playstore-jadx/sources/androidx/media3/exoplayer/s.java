package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3739i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f3740l;

    public /* synthetic */ s(boolean z, int i10) {
        this.f3739i = i10;
        this.f3740l = z;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3739i) {
            case 0:
                ((Player.Listener) obj).onSkipSilenceEnabledChanged(this.f3740l);
                break;
            case 1:
                ((Player.Listener) obj).onShuffleModeEnabledChanged(this.f3740l);
                break;
            default:
                ((Player.Listener) obj).onSkipSilenceEnabledChanged(this.f3740l);
                break;
        }
    }
}

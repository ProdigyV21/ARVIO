package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class y implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3886i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f3887l;

    public /* synthetic */ y(int i10, int i11) {
        this.f3886i = i11;
        this.f3887l = i10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3886i) {
            case 0:
                ((Player.Listener) obj).onRepeatModeChanged(this.f3887l);
                break;
            default:
                ((Player.Listener) obj).onAudioSessionIdChanged(this.f3887l);
                break;
        }
    }
}

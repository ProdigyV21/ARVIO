package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3881i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f3882l;

    public /* synthetic */ x(long j10, int i10) {
        this.f3881i = i10;
        this.f3882l = j10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3881i) {
            case 0:
                ((Player.Listener) obj).onSeekBackIncrementChanged(this.f3882l);
                break;
            case 1:
                ((Player.Listener) obj).onMaxSeekToPreviousPositionChanged(this.f3882l);
                break;
            default:
                ((Player.Listener) obj).onSeekForwardIncrementChanged(this.f3882l);
                break;
        }
    }
}

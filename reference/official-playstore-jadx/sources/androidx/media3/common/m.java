package androidx.media3.common;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import l3.a;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements ListenerSet.Event, a.InterfaceC0242a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3378i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f3379l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3380m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3381n;

    public /* synthetic */ m(int i10, int i11, Object obj, Object obj2) {
        this.f3378i = i11;
        this.f3380m = obj;
        this.f3381n = obj2;
        this.f3379l = i10;
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        k3.f fVar = (k3.f) this.f3380m;
        fVar.f19491d.a((com.google.android.datatransport.runtime.i) this.f3381n, this.f3379l + 1);
        return null;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3378i) {
            case 0:
                SimpleBasePlayer.lambda$updateStateAndInformListeners$35(this.f3379l, (Player.PositionInfo) this.f3380m, (Player.PositionInfo) this.f3381n, (Player.Listener) obj);
                break;
            default:
                ((AnalyticsListener) obj).onMediaItemTransition((AnalyticsListener.EventTime) this.f3380m, (MediaItem) this.f3381n, this.f3379l);
                break;
        }
    }

    public /* synthetic */ m(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
        this.f3378i = 0;
        this.f3379l = i10;
        this.f3380m = positionInfo;
        this.f3381n = positionInfo2;
    }
}

package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.exoplayer.upstream.experimental.ExperimentalBandwidthMeter;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements NetworkTypeObserver.Listener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3844b;

    public /* synthetic */ f(Object obj, int i10) {
        this.f3843a = i10;
        this.f3844b = obj;
    }

    @Override // androidx.media3.common.util.NetworkTypeObserver.Listener
    public final void onNetworkTypeChanged(int i10) throws Throwable {
        switch (this.f3843a) {
            case 0:
                ((DefaultBandwidthMeter) this.f3844b).onNetworkTypeChanged(i10);
                break;
            default:
                ((ExperimentalBandwidthMeter) this.f3844b).onNetworkTypeChanged(i10);
                break;
        }
    }
}

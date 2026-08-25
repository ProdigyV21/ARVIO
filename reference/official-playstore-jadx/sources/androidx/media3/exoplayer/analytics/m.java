package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3494i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3495l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3496m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3497n;

    public /* synthetic */ m(AnalyticsListener.EventTime eventTime, int i10, int i11, boolean z) {
        this.f3494i = i11;
        this.f3495l = eventTime;
        this.f3497n = z;
        this.f3496m = i10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3494i) {
            case 0:
                ((AnalyticsListener) obj).onPlayerStateChanged(this.f3495l, this.f3497n, this.f3496m);
                break;
            case 1:
                ((AnalyticsListener) obj).onDeviceVolumeChanged(this.f3495l, this.f3496m, this.f3497n);
                break;
            default:
                ((AnalyticsListener) obj).onPlayWhenReadyChanged(this.f3495l, this.f3497n, this.f3496m);
                break;
        }
    }

    public /* synthetic */ m(AnalyticsListener.EventTime eventTime, int i10, boolean z) {
        this.f3494i = 1;
        this.f3495l = eventTime;
        this.f3496m = i10;
        this.f3497n = z;
    }
}

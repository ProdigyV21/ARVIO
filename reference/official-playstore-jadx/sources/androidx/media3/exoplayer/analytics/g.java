package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3473i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3474l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f3475m;

    public /* synthetic */ g(AnalyticsListener.EventTime eventTime, String str, int i10) {
        this.f3473i = i10;
        this.f3474l = eventTime;
        this.f3475m = str;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3473i) {
            case 0:
                ((AnalyticsListener) obj).onAudioDecoderReleased(this.f3474l, this.f3475m);
                break;
            default:
                ((AnalyticsListener) obj).onVideoDecoderReleased(this.f3474l, this.f3475m);
                break;
        }
    }
}

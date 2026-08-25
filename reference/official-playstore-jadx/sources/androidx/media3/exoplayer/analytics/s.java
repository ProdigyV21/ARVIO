package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3517i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3518l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f3519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3520n;

    public /* synthetic */ s(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
        this.f3517i = i10;
        this.f3518l = eventTime;
        this.f3519m = loadEventInfo;
        this.f3520n = mediaLoadData;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3517i) {
            case 0:
                ((AnalyticsListener) obj).onLoadCanceled(this.f3518l, this.f3519m, this.f3520n);
                break;
            default:
                ((AnalyticsListener) obj).onLoadCompleted(this.f3518l, this.f3519m, this.f3520n);
                break;
        }
    }
}

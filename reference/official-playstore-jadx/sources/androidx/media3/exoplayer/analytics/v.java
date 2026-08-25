package androidx.media3.exoplayer.analytics;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3528i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3529l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaMetadata f3530m;

    public /* synthetic */ v(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata, int i10) {
        this.f3528i = i10;
        this.f3529l = eventTime;
        this.f3530m = mediaMetadata;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3528i) {
            case 0:
                ((AnalyticsListener) obj).onPlaylistMetadataChanged(this.f3529l, this.f3530m);
                break;
            default:
                ((AnalyticsListener) obj).onMediaMetadataChanged(this.f3529l, this.f3530m);
                break;
        }
    }
}

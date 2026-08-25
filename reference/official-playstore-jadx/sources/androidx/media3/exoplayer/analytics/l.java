package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements ListenerSet.Event, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f3489i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3490l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ IOException f3491m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3492n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3493o;

    public /* synthetic */ l(Object obj, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.f3493o = obj;
        this.f3489i = loadEventInfo;
        this.f3490l = mediaLoadData;
        this.f3491m = iOException;
        this.f3492n = z;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaSourceEventListener.EventDispatcher) this.f3493o).lambda$loadError$3(this.f3489i, this.f3490l, this.f3491m, this.f3492n, (MediaSourceEventListener) obj);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((AnalyticsListener) obj).onLoadError((AnalyticsListener.EventTime) this.f3493o, this.f3489i, this.f3490l, this.f3491m, this.f3492n);
    }
}

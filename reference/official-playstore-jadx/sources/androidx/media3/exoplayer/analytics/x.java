package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import l3.a;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x implements ListenerSet.Event, a.InterfaceC0242a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f3536i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3537l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3538m;

    public /* synthetic */ x(Object obj, Object obj2, long j10) {
        this.f3537l = obj;
        this.f3538m = obj2;
        this.f3536i = j10;
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        k3.f fVar = (k3.f) this.f3537l;
        fVar.f19490c.z(fVar.f19494g.a() + this.f3536i, (com.google.android.datatransport.runtime.i) this.f3538m);
        return null;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ((AnalyticsListener) obj).onRenderedFirstFrame((AnalyticsListener.EventTime) this.f3537l, this.f3538m, this.f3536i);
    }
}

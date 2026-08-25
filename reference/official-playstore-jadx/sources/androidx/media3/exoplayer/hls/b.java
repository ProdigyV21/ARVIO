package androidx.media3.exoplayer.hls;

import android.content.Context;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import com.arflix.tv.util.SentryCrashReporter;
import io.sentry.a1;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.b4;
import io.sentry.m1;
import io.sentry.r4;
import io.sentry.w5;
import io.sentry.y6;
import java.util.List;
import l3.a;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Consumer, DefaultTrackSelector.TrackInfo.Factory, r4, b4, a.InterfaceC0242a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3633i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3634l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3635m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3636n;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i10) {
        this.f3633i = i10;
        this.f3635m = obj;
        this.f3634l = obj2;
        this.f3636n = obj3;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f3633i) {
            case 0:
                HlsInterstitialsAdsLoader.lambda$handleContentTimelineChanged$1((AdsMediaSource) this.f3635m, this.f3634l, (Timeline) this.f3636n, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
            case 1:
                ((HlsInterstitialsAdsLoader.Listener) obj).onStart((MediaItem) this.f3635m, this.f3634l, (AdViewProvider) this.f3636n);
                break;
            default:
                ((MediaSourceEventListener.EventDispatcher) this.f3635m).lambda$upstreamDiscarded$4((MediaSource.MediaPeriodId) this.f3634l, (MediaLoadData) this.f3636n, (MediaSourceEventListener) obj);
                break;
        }
    }

    @Override // io.sentry.b4
    public void b(m1 m1Var) {
        io.sentry.android.core.internal.gestures.g gVar = (io.sentry.android.core.internal.gestures.g) this.f3635m;
        a1 a1Var = (a1) this.f3634l;
        m1 m1Var2 = (m1) this.f3636n;
        if (m1Var == null) {
            a1Var.y(m1Var2);
        } else {
            gVar.f16543m.getLogger().q(w5.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", m1Var2.getName());
        }
    }

    @Override // io.sentry.r4
    public void c(y6 y6Var) {
        SentryCrashReporter.initialize$lambda$0$0((String) this.f3635m, (SentryCrashReporter) this.f3634l, (Context) this.f3636n, (SentryAndroidOptions) y6Var);
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public List create(int i10, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.lambda$selectTextTrack$4((DefaultTrackSelector.Parameters) this.f3635m, (String) this.f3634l, (String) this.f3636n, i10, trackGroup, iArr);
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        j3.a aVar = (j3.a) this.f3635m;
        com.google.android.datatransport.runtime.i iVar = (com.google.android.datatransport.runtime.i) this.f3634l;
        aVar.f19203d.a0(iVar, (com.google.android.datatransport.runtime.h) this.f3636n);
        aVar.f19200a.a(iVar, 1);
        return null;
    }
}

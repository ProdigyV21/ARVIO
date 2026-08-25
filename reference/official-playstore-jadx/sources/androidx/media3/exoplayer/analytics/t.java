package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Format;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class t implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3521i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3522l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Format f3523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ DecoderReuseEvaluation f3524n;

    public /* synthetic */ t(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation, int i10) {
        this.f3521i = i10;
        this.f3522l = eventTime;
        this.f3523m = format;
        this.f3524n = decoderReuseEvaluation;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3521i) {
            case 0:
                ((AnalyticsListener) obj).onVideoInputFormatChanged(this.f3522l, this.f3523m, this.f3524n);
                break;
            default:
                ((AnalyticsListener) obj).onAudioInputFormatChanged(this.f3522l, this.f3523m, this.f3524n);
                break;
        }
    }
}

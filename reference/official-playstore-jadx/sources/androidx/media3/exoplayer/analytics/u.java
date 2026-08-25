package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class u implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3525i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3526l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AudioSink.AudioTrackConfig f3527m;

    public /* synthetic */ u(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig, int i10) {
        this.f3525i = i10;
        this.f3526l = eventTime;
        this.f3527m = audioTrackConfig;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3525i) {
            case 0:
                ((AnalyticsListener) obj).onAudioTrackInitialized(this.f3526l, this.f3527m);
                break;
            default:
                ((AnalyticsListener) obj).onAudioTrackReleased(this.f3526l, this.f3527m);
                break;
        }
    }
}

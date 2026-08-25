package androidx.media3.exoplayer;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3632b;

    public /* synthetic */ h(Object obj, int i10) {
        this.f3631a = i10;
        this.f3632b = obj;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3631a) {
            case 0:
                return ExoPlayer.Builder.lambda$setAnalyticsCollector$21((AnalyticsCollector) this.f3632b, (Clock) obj);
            case 1:
                return ExoPlayer.Builder.lambda$new$13((AnalyticsCollector) this.f3632b, (Clock) obj);
            default:
                return ((StreamVolumeManager) this.f3632b).lambda$release$12((StreamVolumeManager.StreamVolumeState) obj);
        }
    }
}

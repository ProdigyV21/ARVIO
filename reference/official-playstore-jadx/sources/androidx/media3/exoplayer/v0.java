package androidx.media3.exoplayer;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.extractor.Extractor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v0 implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3848a;

    public /* synthetic */ v0(int i10) {
        this.f3848a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3848a) {
            case 0:
                return StreamVolumeManager.lambda$release$11((StreamVolumeManager.StreamVolumeState) obj);
            case 1:
                return StreamVolumeManager.lambda$increaseVolume$5((StreamVolumeManager.StreamVolumeState) obj);
            case 2:
                return StreamVolumeManager.lambda$decreaseVolume$7((StreamVolumeManager.StreamVolumeState) obj);
            case 3:
                return new DefaultAnalyticsCollector((Clock) obj);
            default:
                return MediaExtractorCompatInternal.lambda$selectExtractor$0((Extractor) obj);
        }
    }
}

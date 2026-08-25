package androidx.media3.exoplayer.source;

import android.os.Bundle;
import androidx.media3.common.TrackGroup;
import androidx.media3.extractor.Extractor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3767a;

    public /* synthetic */ k(int i10) {
        this.f3767a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3767a) {
            case 0:
                return MergingMediaPeriod.lambda$selectTracks$0((MediaPeriod) obj);
            case 1:
                return BundledExtractorsAdapter.lambda$init$0((Extractor) obj);
            case 2:
                return TrackGroupArray.lambda$getTrackTypes$0((TrackGroup) obj);
            case 3:
                return ((TrackGroup) obj).toBundle();
            default:
                return TrackGroup.fromBundle((Bundle) obj);
        }
    }
}

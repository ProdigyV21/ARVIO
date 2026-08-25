package androidx.media3.exoplayer.hls;

import androidx.media3.extractor.metadata.id3.PrivFrame;
import com.google.common.base.r;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3663i;

    public /* synthetic */ k(int i10) {
        this.f3663i = i10;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        switch (this.f3663i) {
            case 0:
                return HlsMediaChunk.lambda$peekId3PrivTimestamp$0((PrivFrame) obj);
            default:
                return DefaultHlsExtractorFactory.lambda$isFmp4Variant$0((HlsTrackMetadataEntry) obj);
        }
    }
}

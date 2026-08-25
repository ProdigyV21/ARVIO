package androidx.media3.exoplayer.source;

import android.media.MediaParser;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.SampleQueue;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements Consumer, ProgressiveMediaExtractor.Factory {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3777i;

    public /* synthetic */ p(int i10) {
        this.f3777i = i10;
    }

    public static /* bridge */ /* synthetic */ MediaParser.SeekPoint b(Object obj) {
        return (MediaParser.SeekPoint) obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f3777i) {
            case 0:
                SampleQueue.lambda$new$0((SampleQueue.SharedSampleMetadata) obj);
                break;
            default:
                SpannedData.lambda$new$0(obj);
                break;
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor.Factory
    public ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
        return MediaParserExtractorAdapter.lambda$static$0(playerId);
    }
}

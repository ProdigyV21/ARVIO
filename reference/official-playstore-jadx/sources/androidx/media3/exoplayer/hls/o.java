package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements HlsExtractorFactory {
    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public HlsMediaChunkExtractor createExtractor(Uri uri, Format format, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput, PlayerId playerId) {
        return MediaParserHlsMediaChunkExtractor.lambda$static$0(uri, format, list, timestampAdjuster, map, extractorInput, playerId);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* synthetic */ HlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean z) {
        return a.a(this, z);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* synthetic */ HlsExtractorFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        return a.b(this, i10);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* synthetic */ Format getOutputTextFormat(Format format) {
        return a.c(this, format);
    }

    @Override // androidx.media3.exoplayer.hls.HlsExtractorFactory
    public /* synthetic */ HlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return a.d(this, factory);
    }
}

package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.common.util.h;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = new h(21);

    Extractor[] createExtractors();

    Extractor[] createExtractors(Uri uri, Map<String, List<String>> map);

    ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10);

    @Deprecated
    ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z);

    ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory);
}

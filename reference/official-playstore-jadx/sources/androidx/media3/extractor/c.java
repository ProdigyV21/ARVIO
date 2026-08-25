package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class c {
    public static Extractor[] a(ExtractorsFactory extractorsFactory, Uri uri, Map map) {
        return extractorsFactory.createExtractors();
    }

    public static /* synthetic */ Extractor[] e() {
        return new Extractor[0];
    }

    public static ExtractorsFactory b(ExtractorsFactory extractorsFactory, int i10) {
        return extractorsFactory;
    }

    public static ExtractorsFactory c(ExtractorsFactory extractorsFactory, boolean z) {
        return extractorsFactory;
    }

    public static ExtractorsFactory d(ExtractorsFactory extractorsFactory, SubtitleParser.Factory factory) {
        return extractorsFactory;
    }
}

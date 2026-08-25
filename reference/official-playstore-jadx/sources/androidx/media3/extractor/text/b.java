package androidx.media3.extractor.text;

import androidx.media3.common.util.Consumer;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.e1;
import com.google.common.collect.h1;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    public static void a(SubtitleParser subtitleParser, byte[] bArr, SubtitleParser.OutputOptions outputOptions, Consumer consumer) {
        subtitleParser.parse(bArr, 0, bArr.length, outputOptions, consumer);
    }

    public static Subtitle b(SubtitleParser subtitleParser, byte[] bArr, int i10, int i11) {
        e1 e1VarK = h1.k();
        subtitleParser.parse(bArr, i10, i11, SubtitleParser.OutputOptions.ALL, new a3.b(e1VarK, 10));
        return new CuesWithTimingSubtitle(e1VarK.f());
    }

    public static void c(SubtitleParser subtitleParser) {
    }
}

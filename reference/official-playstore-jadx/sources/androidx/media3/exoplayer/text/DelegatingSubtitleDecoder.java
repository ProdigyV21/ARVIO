package androidx.media3.exoplayer.text;

import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;

/* JADX INFO: loaded from: classes3.dex */
final class DelegatingSubtitleDecoder extends SimpleSubtitleDecoder {
    private final SubtitleParser subtitleParser;

    public DelegatingSubtitleDecoder(String str, SubtitleParser subtitleParser) {
        super(str);
        this.subtitleParser = subtitleParser;
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i10, boolean z) {
        if (z) {
            this.subtitleParser.reset();
        }
        return this.subtitleParser.parseToLegacySubtitle(bArr, 0, i10);
    }
}

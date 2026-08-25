package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public interface SubtitleParser {

    public interface Factory {
        public static final Factory UNSUPPORTED = new Factory() { // from class: androidx.media3.extractor.text.SubtitleParser.Factory.1
            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public SubtitleParser create(Format format) {
                throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public int getCueReplacementBehavior(Format format) {
                return 1;
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public boolean supportsFormat(Format format) {
                return false;
            }
        };

        SubtitleParser create(Format format);

        int getCueReplacementBehavior(Format format);

        boolean supportsFormat(Format format);
    }

    public static class OutputOptions {
        private static final OutputOptions ALL = new OutputOptions(C.TIME_UNSET, false);
        public final boolean outputAllCues;
        public final long startTimeUs;

        private OutputOptions(long j10, boolean z) {
            this.startTimeUs = j10;
            this.outputAllCues = z;
        }

        public static OutputOptions allCues() {
            return ALL;
        }

        public static OutputOptions cuesAfterThenRemainingCuesBefore(long j10) {
            return new OutputOptions(j10, true);
        }

        public static OutputOptions onlyCuesAfter(long j10) {
            return new OutputOptions(j10, false);
        }
    }

    int getCueReplacementBehavior();

    void parse(byte[] bArr, int i10, int i11, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    void parse(byte[] bArr, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    Subtitle parseToLegacySubtitle(byte[] bArr, int i10, int i11);

    void reset();
}

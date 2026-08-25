package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface TsPayloadReader {
    public static final int FLAG_DATA_ALIGNMENT_INDICATOR = 4;
    public static final int FLAG_PAYLOAD_UNIT_START_INDICATOR = 1;
    public static final int FLAG_RANDOM_ACCESS_INDICATOR = 2;

    public static final class DvbSubtitleInfo {
        public final byte[] initializationData;
        public final String language;
        public final int type;

        public DvbSubtitleInfo(String str, int i10, byte[] bArr) {
            this.language = str;
            this.type = i10;
            this.initializationData = bArr;
        }
    }

    public static final class EsInfo {
        public static final int AUDIO_TYPE_CLEAN_EFFECTS = 1;
        public static final int AUDIO_TYPE_HEARING_IMPAIRED = 2;
        public static final int AUDIO_TYPE_UNDEFINED = 0;
        public static final int AUDIO_TYPE_VISUAL_IMPAIRED_COMMENTARY = 3;
        public final int audioType;
        public final byte[] descriptorBytes;
        public final List<DvbSubtitleInfo> dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface AudioType {
        }

        public EsInfo(int i10, String str, int i11, List<DvbSubtitleInfo> list, byte[] bArr) {
            this.streamType = i10;
            this.language = str;
            this.audioType = i11;
            this.dvbSubtitleInfos = list == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(list);
            this.descriptorBytes = bArr;
        }

        public int getRoleFlags() {
            int i10 = this.audioType;
            if (i10 != 2) {
                return i10 != 3 ? 0 : 512;
            }
            return 2048;
        }
    }

    public interface Factory {
        SparseArray<TsPayloadReader> createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int i10, EsInfo esInfo);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class TrackIdGenerator {
        private static final int ID_UNSET = Integer.MIN_VALUE;
        private final int firstTrackId;
        private String formatId;
        private final String formatIdPrefix;
        private int trackId;
        private final int trackIdIncrement;

        public TrackIdGenerator(int i10, int i11) {
            this(Integer.MIN_VALUE, i10, i11);
        }

        private void maybeThrowUninitializedError() {
            if (this.trackId == Integer.MIN_VALUE) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }

        public void generateNewId() {
            int i10 = this.trackId;
            this.trackId = i10 == Integer.MIN_VALUE ? this.firstTrackId : i10 + this.trackIdIncrement;
            this.formatId = this.formatIdPrefix + this.trackId;
        }

        public String getFormatId() {
            maybeThrowUninitializedError();
            return this.formatId;
        }

        public int getTrackId() {
            maybeThrowUninitializedError();
            return this.trackId;
        }

        public TrackIdGenerator(int i10, int i11, int i12) {
            this.formatIdPrefix = i10 != Integer.MIN_VALUE ? a2.i(i10, DomExceptionUtils.SEPARATOR) : "";
            this.firstTrackId = i11;
            this.trackIdIncrement = i12;
            this.trackId = Integer.MIN_VALUE;
            this.formatId = "";
        }
    }

    void consume(ParsableByteArray parsableByteArray, int i10) throws ParserException;

    void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator);

    void seek();
}

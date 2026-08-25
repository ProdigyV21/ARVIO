package com.arflix.tv.player.dvmkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexProvider;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackAwareSeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.ThumbnailMetadata;
import androidx.media3.extractor.mp4.b;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import j$.util.Objects;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SSA = "S_TEXT/SSA";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 2;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_BITS_PER_CHANNEL = 21938;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_RELATIVE_POSITION = 240;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK = 247;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int MAX_CHUNKS_TO_SCAN_FOR_THUMBNAIL = 20;
    private static final long MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL = 10000000;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private long cuesContentPosition;
    private long currentCueClusterPosition;
    private long currentCueRelativePosition;
    private long currentCueTimeUs;
    private int currentCueTrackNumber;
    private Track currentTrack;
    private byte[] dolbyVisionSampleBuffer;
    private final DolbyVisionSampleTransformer dolbyVisionSampleTransformer;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private boolean inCuesElement;
    private boolean isWebm;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final boolean parseSubtitlesDuringExtraction;
    private boolean pendingEndTracks;
    private final SparseArray<List<MatroskaSeekMap.CuePointData>> perTrackCues;
    private int primarySeekTrackNumber;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new b(18);
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    public interface DolbyVisionSampleTransformer {
        int lastTransformedSampleLength();

        byte[] onDolbyVisionBlockAdditionalData(byte[] bArr, int i10, byte[] bArr2);

        String onDolbyVisionCodecString(String str, byte[] bArr);

        void onHevcSample(int i10, byte[] bArr, byte[] bArr2);

        byte[] transformHevcSample(byte[] bArr, int i10, int i11, byte[] bArr2, byte[] bArr3);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public final class InnerEbmlProcessor implements EbmlProcessor {
        public /* synthetic */ InnerEbmlProcessor(MatroskaExtractor matroskaExtractor, int i10) {
            this();
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void binaryElement(int i10, int i11, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i10, i11, extractorInput);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void endMasterElement(int i10) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i10);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void floatElement(int i10, double d4) throws ParserException {
            MatroskaExtractor.this.floatElement(i10, d4);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public int getElementType(int i10) {
            return MatroskaExtractor.this.getElementType(i10);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void integerElement(int i10, long j10) throws ParserException {
            MatroskaExtractor.this.integerElement(i10, j10);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public boolean isLevel1Element(int i10) {
            return MatroskaExtractor.this.isLevel1Element(i10);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void startMasterElement(int i10, long j10, long j11) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i10, j10, j11);
        }

        @Override // com.arflix.tv.player.dvmkv.EbmlProcessor
        public void stringElement(int i10, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i10, str);
        }

        private InnerEbmlProcessor() {
        }
    }

    public static final class MatroskaSeekMap implements TrackAwareSeekMap, ChunkIndexProvider {
        private final ChunkIndex chunkIndex;
        private final long durationUs;
        private final SparseArray<List<CuePointData>> perTrackCues;
        private final int primarySeekTrackNumber;

        public static final class CuePointData implements Comparable<CuePointData> {
            private final long clusterPosition;
            private final long relativePosition;
            private final long timeUs;

            public /* synthetic */ CuePointData(int i10, long j10, long j11, long j12) {
                this(j10, j11, j12);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof CuePointData)) {
                    return false;
                }
                CuePointData cuePointData = (CuePointData) obj;
                return this.timeUs == cuePointData.timeUs && this.clusterPosition == cuePointData.clusterPosition && this.relativePosition == cuePointData.relativePosition;
            }

            public int hashCode() {
                return Objects.hash(Long.valueOf(this.timeUs), Long.valueOf(this.clusterPosition), Long.valueOf(this.relativePosition));
            }

            private CuePointData(long j10, long j11, long j12) {
                this.timeUs = j10;
                this.clusterPosition = j11;
                this.relativePosition = j12;
            }

            @Override // java.lang.Comparable
            public int compareTo(CuePointData cuePointData) {
                return Long.compare(this.timeUs, cuePointData.timeUs);
            }
        }

        public MatroskaSeekMap(SparseArray<List<CuePointData>> sparseArray, long j10, int i10, long j11, long j12) {
            this.perTrackCues = sparseArray;
            this.durationUs = j10;
            this.primarySeekTrackNumber = i10;
            this.chunkIndex = buildChunkIndex(sparseArray, j10, i10, j11, j12);
        }

        private static ChunkIndex buildChunkIndex(SparseArray<List<CuePointData>> sparseArray, long j10, int i10, long j11, long j12) {
            int i11;
            List<CuePointData> list = sparseArray.get(i10);
            if (list == null || list.isEmpty()) {
                return null;
            }
            int size = list.size();
            int[] iArrCopyOf = new int[size];
            long[] jArrCopyOf = new long[size];
            long[] jArrCopyOf2 = new long[size];
            long[] jArrCopyOf3 = new long[size];
            int i12 = 0;
            for (int i13 = 0; i13 < size; i13++) {
                CuePointData cuePointData = list.get(i13);
                jArrCopyOf3[i13] = cuePointData.timeUs;
                jArrCopyOf[i13] = cuePointData.clusterPosition;
            }
            while (true) {
                i11 = size - 1;
                if (i12 >= i11) {
                    break;
                }
                int i14 = i12 + 1;
                iArrCopyOf[i12] = (int) (jArrCopyOf[i14] - jArrCopyOf[i12]);
                jArrCopyOf2[i12] = jArrCopyOf3[i14] - jArrCopyOf3[i12];
                i12 = i14;
            }
            int i15 = i11;
            while (i15 > 0 && jArrCopyOf3[i15] >= j10) {
                i15--;
            }
            iArrCopyOf[i15] = (int) ((j11 + j12) - jArrCopyOf[i15]);
            jArrCopyOf2[i15] = j10 - jArrCopyOf3[i15];
            if (i15 < i11) {
                Log.w(MatroskaExtractor.TAG, "Discarding trailing cue points with timestamps greater than total duration.");
                int i16 = i15 + 1;
                iArrCopyOf = Arrays.copyOf(iArrCopyOf, i16);
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, i16);
                jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i16);
                jArrCopyOf3 = Arrays.copyOf(jArrCopyOf3, i16);
            }
            return new ChunkIndex(iArrCopyOf, jArrCopyOf, jArrCopyOf2, jArrCopyOf3);
        }

        @Override // androidx.media3.extractor.ChunkIndexProvider
        public ChunkIndex getChunkIndex() {
            return this.chunkIndex;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j10) {
            ChunkIndex chunkIndex = this.chunkIndex;
            return chunkIndex != null ? chunkIndex.getSeekPoints(j10) : new SeekMap.SeekPoints(SeekPoint.START);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return isSeekable(this.primarySeekTrackNumber);
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public boolean isSeekable(int i10) {
            List<CuePointData> list = this.perTrackCues.get(i10);
            return (list == null || list.isEmpty()) ? false : true;
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public SeekMap.SeekPoints getSeekPoints(long j10, int i10) {
            int i11;
            int i12;
            List<CuePointData> list = this.perTrackCues.get(i10);
            if ((list == null || list.isEmpty()) && i10 != (i11 = this.primarySeekTrackNumber)) {
                list = this.perTrackCues.get(i11);
            }
            if (list != null && !list.isEmpty()) {
                int iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super CuePointData>>) list, new CuePointData(0, j10, -1L, -1L), true, false);
                if (iBinarySearchFloor != -1) {
                    CuePointData cuePointData = list.get(iBinarySearchFloor);
                    SeekPoint seekPoint = new SeekPoint(cuePointData.timeUs, cuePointData.clusterPosition);
                    if (cuePointData.timeUs < j10 && (i12 = iBinarySearchFloor + 1) < list.size()) {
                        CuePointData cuePointData2 = list.get(i12);
                        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(cuePointData2.timeUs, cuePointData2.clusterPosition));
                    }
                    return new SeekMap.SeekPoints(seekPoint);
                }
                CuePointData cuePointData3 = list.get(0);
                return new SeekMap.SeekPoints(new SeekPoint(cuePointData3.timeUs, cuePointData3.clusterPosition));
            }
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
    }

    public static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        private int blockAddIdType;
        public String codecId;
        public byte[] codecPrivate;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagForced;
        public Format format;
        public boolean hasContentEncryption;
        public boolean isWebm;
        public int maxBlockAdditionId;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public byte[] pendingDolbyVisionBlockAdditionalData;
        public byte[] sampleStrippedBytes;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public int width = -1;
        public int height = -1;
        public int bitsPerChannel = -1;
        public int displayWidth = -1;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int projectionType = -1;
        public float projectionPoseYaw = 0.0f;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public byte[] projectionData = null;
        public int stereoMode = -1;
        public boolean hasColorInfo = false;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public int colorRange = -1;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int dolbyVisionProfile = -1;
        public int channelCount = 1;
        public int audioBitDepth = -1;
        public int sampleRate = 8000;
        public long codecDelayNs = 0;
        public long seekPreRollNs = 0;
        public boolean waitingForDtsAnalysis = false;
        public boolean flagDefault = true;
        private String language = "eng";

        /* JADX INFO: Access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            this.output.getClass();
        }

        private static long findBestThumbnailPresentationTimeUs(List<MatroskaSeekMap.CuePointData> list, long j10, long j11, long j12) {
            long j13;
            long j14;
            if (list.isEmpty()) {
                return C.TIME_UNSET;
            }
            int iMin = Math.min(list.size(), 20);
            double d4 = 0.0d;
            int i10 = -1;
            for (int i11 = 0; i11 < iMin; i11++) {
                MatroskaSeekMap.CuePointData cuePointData = list.get(i11);
                if (cuePointData.timeUs > MatroskaExtractor.MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL) {
                    break;
                }
                if (i11 < list.size() - 1) {
                    MatroskaSeekMap.CuePointData cuePointData2 = list.get(i11 + 1);
                    j14 = (cuePointData2.clusterPosition + cuePointData2.relativePosition) - (cuePointData.clusterPosition + cuePointData.relativePosition);
                    j13 = cuePointData2.timeUs - cuePointData.timeUs;
                } else {
                    long j15 = (j11 + j12) - (cuePointData.clusterPosition + cuePointData.relativePosition);
                    j13 = j10 - cuePointData.timeUs;
                    j14 = j15;
                }
                if (j13 > 0) {
                    double d10 = j14 / j13;
                    if (d10 > d4) {
                        i10 = i11;
                        d4 = d10;
                    }
                }
            }
            return i10 == -1 ? C.TIME_UNSET : list.get(i10).timeUs;
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, null);
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.put((byte) 0);
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) (this.maxMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) (this.minMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) this.maxContentLuminance);
            byteBufferOrder.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeAddThumbnailMetadata(SparseArray<List<MatroskaSeekMap.CuePointData>> sparseArray, long j10, long j11, long j12) {
            List<MatroskaSeekMap.CuePointData> list;
            if (this.type != 2 || (list = sparseArray.get(this.number)) == null || list.isEmpty()) {
                return;
            }
            long jFindBestThumbnailPresentationTimeUs = findBestThumbnailPresentationTimeUs(list, j10, j11, j12);
            if (jFindBestThumbnailPresentationTimeUs != C.TIME_UNSET) {
                Format format = this.format;
                format.getClass();
                Metadata metadata = format.metadata;
                ThumbnailMetadata thumbnailMetadata = new ThumbnailMetadata(jFindBestThumbnailPresentationTimeUs);
                this.format = this.format.buildUpon().setMetadata(metadata == null ? new Metadata(thumbnailMetadata) : metadata.copyWithAppendedEntries(thumbnailMetadata)).build();
            }
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (littleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_DIVX, null);
                }
                if (littleEndianUnsignedInt == 859189832) {
                    return new Pair<>(MimeTypes.VIDEO_H263, null);
                }
                if (littleEndianUnsignedInt != 826496599) {
                    Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                    return new Pair<>(MimeTypes.VIDEO_UNKNOWN, null);
                }
                byte[] data = parsableByteArray.getData();
                for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                    if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                        return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                    }
                }
                throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort == 65534) {
                    parsableByteArray.setPosition(24);
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits()) {
                        if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            int i10;
            int i11;
            try {
                if (bArr[0] != 2) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i12 = 0;
                int i13 = 1;
                while (true) {
                    i10 = bArr[i13];
                    if ((i10 & 255) != 255) {
                        break;
                    }
                    i12 += 255;
                    i13++;
                }
                int i14 = i13 + 1;
                int i15 = i12 + (i10 & 255);
                int i16 = 0;
                while (true) {
                    i11 = bArr[i14];
                    if ((i11 & 255) != 255) {
                        break;
                    }
                    i16 += 255;
                    i14++;
                }
                int i17 = i14 + 1;
                int i18 = i16 + (i11 & 255);
                if (bArr[i17] != 1) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr2 = new byte[i15];
                System.arraycopy(bArr, i17, bArr2, 0, i15);
                int i19 = i17 + i15;
                if (bArr[i19] != 3) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i20 = i19 + i18;
                if (bArr[i20] != 5) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr3 = new byte[bArr.length - i20];
                System.arraycopy(bArr, i20, bArr3, 0, bArr.length - i20);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            return MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId) ? z : this.maxBlockAdditionId > 0;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:223:0x044d  */
        /* JADX WARN: Removed duplicated region for block: B:238:0x048a  */
        /* JADX WARN: Removed duplicated region for block: B:250:0x04ab  */
        /* JADX WARN: Removed duplicated region for block: B:261:0x04d9  */
        /* JADX WARN: Removed duplicated region for block: B:274:0x0504  */
        /* JADX WARN: Removed duplicated region for block: B:277:0x0511  */
        /* JADX WARN: Removed duplicated region for block: B:278:0x0514  */
        /* JADX WARN: Removed duplicated region for block: B:281:0x0523  */
        /* JADX WARN: Removed duplicated region for block: B:282:0x0534  */
        /* JADX WARN: Removed duplicated region for block: B:331:0x060d  */
        /* JADX WARN: Removed duplicated region for block: B:350:0x0663  */
        /* JADX WARN: Removed duplicated region for block: B:355:0x067e  */
        /* JADX WARN: Removed duplicated region for block: B:356:0x0682  */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
        @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void initializeFormat(int r23, com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer r24) throws androidx.media3.common.ParserException {
            /*
                Method dump skipped, instruction units count: 1924
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.player.dvmkv.MatroskaExtractor.Track.initializeFormat(int, com.arflix.tv.player.dvmkv.MatroskaExtractor$DolbyVisionSampleTransformer):void");
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
            this.pendingDolbyVisionBlockAdditionalData = null;
        }
    }

    static {
        HashMap map = new HashMap();
        a2.E(0, map, "htc_video_rotA-000", 90, "htc_video_rotA-090");
        a2.E(180, map, "htc_video_rotA-180", 270, "htc_video_rotA-270");
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(map);
    }

    @Deprecated
    public MatroskaExtractor() {
        this(new DefaultEbmlReader(), 2, SubtitleParser.Factory.UNSUPPORTED, null);
    }

    private void assertInCues(int i10) throws ParserException {
        if (this.inCuesElement) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + i10 + " must be in a Cues", null);
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int i10) throws ParserException {
        if (this.currentTrack != null) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + i10 + " must be in a TrackEntry", null);
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        this.extractorOutput.getClass();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void commitSampleToOutput(com.arflix.tv.player.dvmkv.MatroskaExtractor.Track r19, long r20, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.player.dvmkv.MatroskaExtractor.commitSampleToOutput(com.arflix.tv.player.dvmkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    private byte[] convertLengthDelimitedSampleToAnnexB(byte[] bArr, int i10, int i11, String str) throws ParserException {
        if (i11 <= 0 || i11 > 4) {
            throw ParserException.createForMalformedContainer("Invalid NAL length field size for " + str + ": " + i11, null);
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i10);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i10 + 64);
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.bytesLeft() < i11) {
                throw ParserException.createForMalformedContainer("Truncated NAL length for " + str, null);
            }
            int unsignedByte = 0;
            for (int i12 = 0; i12 < i11; i12++) {
                unsignedByte = (unsignedByte << 8) | parsableByteArray.readUnsignedByte();
            }
            if (unsignedByte < 0 || parsableByteArray.bytesLeft() < unsignedByte) {
                throw ParserException.createForMalformedContainer("Invalid NAL length for " + str + ": " + unsignedByte, null);
            }
            byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
            byteArrayOutputStream.write(bArr2, 0, bArr2.length);
            byteArrayOutputStream.write(parsableByteArray.getData(), parsableByteArray.getPosition(), unsignedByte);
            parsableByteArray.skipBytes(unsignedByte);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i10) {
        return iArr == null ? new int[i10] : iArr.length >= i10 ? iArr : new int[Math.max(iArr.length * 2, i10)];
    }

    private int finishWriteSampleData() {
        int i10 = this.sampleBytesWritten;
        resetWriteSampleData();
        return i10;
    }

    private static byte[] formatSubtitleTimecode(long j10, String str, long j11) {
        ac.b.j(j10 != C.TIME_UNSET);
        int i10 = (int) (j10 / 3600000000L);
        long j12 = j10 - (((long) i10) * 3600000000L);
        int i11 = (int) (j12 / 60000000);
        long j13 = j12 - (((long) i11) * 60000000);
        int i12 = (int) (j13 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf((int) ((j13 - (((long) i12) * 1000000)) / j11))));
    }

    private static int getAnnexBSize(byte[] bArr, int i10, int i11) {
        if (i11 == 4) {
            return i10;
        }
        int i12 = 0;
        int i13 = 0;
        while (true) {
            int i14 = i12 + i11;
            if (i14 > i10) {
                break;
            }
            int i15 = 0;
            for (int i16 = 0; i16 < i11; i16++) {
                i15 = (i15 << 8) | (bArr[i12 + i16] & 255);
            }
            if (i15 < 0 || i14 + i15 > i10) {
                break;
            }
            i13 += i15 + 4;
            i12 += i15 + i11;
        }
        return i13;
    }

    private static boolean isCodecSupported(String str) {
        str.getClass();
        switch (str) {
            case "V_MPEG4/ISO/AP":
            case "V_MPEG4/ISO/SP":
            case "A_MS/ACM":
            case "A_TRUEHD":
            case "A_VORBIS":
            case "A_MPEG/L2":
            case "A_MPEG/L3":
            case "V_MS/VFW/FOURCC":
            case "S_DVBSUB":
            case "V_MPEG4/ISO/ASP":
            case "V_MPEG4/ISO/AVC":
            case "S_VOBSUB":
            case "A_DTS/LOSSLESS":
            case "A_AAC":
            case "A_AC3":
            case "A_DTS":
            case "V_AV1":
            case "V_VP8":
            case "V_VP9":
            case "S_HDMV/PGS":
            case "V_THEORA":
            case "A_DTS/EXPRESS":
            case "A_PCM/FLOAT/IEEE":
            case "A_PCM/INT/BIG":
            case "A_PCM/INT/LIT":
            case "S_TEXT/ASS":
            case "S_TEXT/SSA":
            case "V_MPEGH/ISO/HEVC":
            case "S_TEXT/WEBVTT":
            case "S_TEXT/UTF8":
            case "V_MPEG2":
            case "A_EAC3":
            case "A_FLAC":
            case "A_OPUS":
                return true;
            default:
                return false;
        }
    }

    private static boolean isSampleDtsHd(ExtractorInput extractorInput, int i10) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        if (!extractorInput.peekFully(parsableByteArray.getData(), 0, i10, true)) {
            return false;
        }
        extractorInput.resetPeekPosition();
        int i11 = parsableByteArray.readInt();
        parsableByteArray.setPosition(0);
        if (DtsUtil.getFrameType(i11) != 1 || parsableByteArray.bytesLeft() < 10) {
            return false;
        }
        byte[] bArr = new byte[10];
        parsableByteArray.readBytes(bArr, 0, 10);
        parsableByteArray.setPosition(0);
        int dtsFrameSize = DtsUtil.getDtsFrameSize(bArr);
        if (dtsFrameSize > 0 && parsableByteArray.bytesLeft() >= dtsFrameSize + 4) {
            parsableByteArray.skipBytes(dtsFrameSize);
            if (DtsUtil.getFrameType(parsableByteArray.readInt()) == 2) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new MatroskaExtractor(factory)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$newFactory$1(SubtitleParser.Factory factory, DolbyVisionSampleTransformer dolbyVisionSampleTransformer) {
        return new Extractor[]{new MatroskaExtractor(factory, 0, dolbyVisionSampleTransformer)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$2() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    private void maybeEndTracks() {
        if (this.pendingEndTracks) {
            for (int i10 = 0; i10 < this.tracks.size(); i10++) {
                if (this.tracks.valueAt(i10).waitingForDtsAnalysis) {
                    return;
                }
            }
            ExtractorOutput extractorOutput = this.extractorOutput;
            extractorOutput.getClass();
            extractorOutput.endTracks();
            this.pendingEndTracks = false;
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j10) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j10;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j11 = this.seekPositionAfterBuildingCues;
            if (j11 != -1) {
                positionHolder.position = j11;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new androidx.media3.extractor.mkv.a(factory, 3);
    }

    private void readScratch(ExtractorInput extractorInput, int i10) throws IOException {
        if (this.scratch.limit() >= i10) {
            return;
        }
        if (this.scratch.capacity() < i10) {
            ParsableByteArray parsableByteArray = this.scratch;
            parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i10));
        }
        extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i10 - this.scratch.limit());
        this.scratch.setLimit(i10);
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private long scaleTimecodeToUs(long j10) throws ParserException {
        long j11 = this.timecodeScale;
        if (j11 != C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j10, j11, 1000L);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
    }

    private static void setSubtitleEndTime(String str, long j10, byte[] bArr) {
        byte[] subtitleTimecode;
        int i10;
        str.getClass();
        switch (str) {
            case "S_TEXT/ASS":
            case "S_TEXT/SSA":
                subtitleTimecode = formatSubtitleTimecode(j10, SSA_TIMECODE_FORMAT, 10000L);
                i10 = 21;
                break;
            case "S_TEXT/WEBVTT":
                subtitleTimecode = formatSubtitleTimecode(j10, VTT_TIMECODE_FORMAT, 1000L);
                i10 = 25;
                break;
            case "S_TEXT/UTF8":
                subtitleTimecode = formatSubtitleTimecode(j10, SUBRIP_TIMECODE_FORMAT, 1000L);
                i10 = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(subtitleTimecode, 0, bArr, i10, subtitleTimecode.length);
    }

    private int writeLengthDelimitedSampleAsAnnexB(TrackOutput trackOutput, byte[] bArr, int i10, int i11, String str) throws ParserException {
        if (i11 <= 0 || i11 > 4) {
            throw ParserException.createForMalformedContainer("Invalid NAL length field size for " + str + ": " + i11, null);
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i10);
        int i12 = 0;
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.bytesLeft() < i11) {
                throw ParserException.createForMalformedContainer("Truncated NAL length for " + str, null);
            }
            int unsignedByte = 0;
            for (int i13 = 0; i13 < i11; i13++) {
                unsignedByte = (unsignedByte << 8) | parsableByteArray.readUnsignedByte();
            }
            if (unsignedByte < 0 || parsableByteArray.bytesLeft() < unsignedByte) {
                throw ParserException.createForMalformedContainer("Invalid NAL length for " + str + ": " + unsignedByte, null);
            }
            this.nalStartCode.setPosition(0);
            trackOutput.sampleData(this.nalStartCode, 4);
            trackOutput.sampleData(parsableByteArray, unsignedByte);
            i12 = i12 + 4 + unsignedByte;
        }
        return i12;
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int i10, boolean z) throws IOException {
        boolean z5;
        int i11;
        int iLastTransformedSampleLength;
        byte[] bArrTransformHevcSample;
        int i12;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i10);
            return finishWriteSampleData();
        }
        if (CODEC_ID_ASS.equals(track.codecId) || CODEC_ID_SSA.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i10);
            return finishWriteSampleData();
        }
        if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i10);
            return finishWriteSampleData();
        }
        if (track.waitingForDtsAnalysis) {
            track.format.getClass();
            if (DtsUtil.isSampleDtsHd(extractorInput, i10)) {
                track.format = track.format.buildUpon().setSampleMimeType(MimeTypes.AUDIO_DTS_HD).build();
            }
            track.output.format(track.format);
            track.waitingForDtsAnalysis = false;
            maybeEndTracks();
        }
        TrackOutput trackOutput = track.output;
        if (this.sampleEncodingHandled) {
            z5 = false;
        } else {
            if (track.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.getData(), 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.getData()[0] & 128) == 128) {
                        throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
                    }
                    this.sampleSignalByte = this.scratch.getData()[0];
                    this.sampleSignalByteRead = true;
                }
                byte b10 = this.sampleSignalByte;
                if ((b10 & 1) == 1) {
                    boolean z10 = (b10 & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.getData()[0] = (byte) ((z10 ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                        this.sampleBytesWritten += 8;
                    }
                    if (z10) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.getData(), 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i13 = this.samplePartitionCount * 4;
                        this.scratch.reset(i13);
                        extractorInput.readFully(this.scratch.getData(), 0, i13);
                        this.sampleBytesRead += i13;
                        short s10 = (short) ((this.samplePartitionCount / 2) + 1);
                        int i14 = (s10 * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i14) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i14);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s10);
                        int i15 = 0;
                        int i16 = 0;
                        while (true) {
                            i12 = this.samplePartitionCount;
                            if (i15 >= i12) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i15 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (unsignedIntToInt - i16));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - i16);
                            }
                            i15++;
                            i16 = unsignedIntToInt;
                        }
                        int i17 = (i10 - this.sampleBytesRead) - i16;
                        if (i12 % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i17);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i17);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i14);
                        trackOutput.sampleData(this.encryptionSubsampleData, i14, 1);
                        this.sampleBytesWritten += i14;
                    }
                }
            } else {
                byte[] bArr = track.sampleStrippedBytes;
                if (bArr != null) {
                    this.sampleStrippedBytes.reset(bArr, bArr.length);
                }
            }
            if (track.samplesHaveSupplementalData(z)) {
                this.blockFlags |= 268435456;
                this.supplementalData.reset(0);
                z5 = CODEC_ID_H265.equals(track.codecId) && this.dolbyVisionSampleTransformer != null && track.dolbyVisionProfile == 7;
                if (!z5) {
                    writeSupplementalMainSampleSizePrefix(trackOutput, (this.sampleStrippedBytes.limit() + i10) - this.sampleBytesRead);
                    this.sampleBytesWritten += 4;
                }
            } else {
                z5 = false;
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit = this.sampleStrippedBytes.limit() + i10;
        boolean z11 = CODEC_ID_H265.equals(track.codecId) && this.dolbyVisionSampleTransformer != null && track.dolbyVisionProfile == 7;
        if (z11) {
            try {
                this.dolbyVisionSampleTransformer.onHevcSample(iLimit, track.pendingDolbyVisionBlockAdditionalData, track.dolbyVisionConfigBytes);
            } catch (RuntimeException e5) {
                Log.w(TAG, "DolbyVisionSampleTransformer.onHevcSample failed: " + e5.getMessage());
            }
        }
        if (!z11) {
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    ac.b.s(this.sampleStrippedBytes.limit() == 0);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i18 = this.sampleBytesRead;
                    if (i18 >= iLimit) {
                        break;
                    }
                    int iWriteToOutput = writeToOutput(extractorInput, trackOutput, iLimit - i18);
                    this.sampleBytesRead += iWriteToOutput;
                    this.sampleBytesWritten += iWriteToOutput;
                }
            } else {
                byte[] data = this.nalLength.getData();
                data[0] = 0;
                data[1] = 0;
                data[2] = 0;
                int i19 = track.nalUnitLengthFieldLength;
                int i20 = 4 - i19;
                while (this.sampleBytesRead < iLimit) {
                    int i21 = this.sampleCurrentNalBytesRemaining;
                    if (i21 == 0) {
                        writeToTarget(extractorInput, data, i20, i19);
                        this.sampleBytesRead += i19;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int iWriteToOutput2 = writeToOutput(extractorInput, trackOutput, i21);
                        this.sampleBytesRead += iWriteToOutput2;
                        this.sampleBytesWritten += iWriteToOutput2;
                        this.sampleCurrentNalBytesRemaining -= iWriteToOutput2;
                    }
                }
            }
        } else {
            int i22 = iLimit - this.sampleBytesRead;
            if (this.dolbyVisionSampleBuffer.length < i22) {
                this.dolbyVisionSampleBuffer = new byte[i22];
            }
            byte[] bArr2 = this.dolbyVisionSampleBuffer;
            writeToTarget(extractorInput, bArr2, 0, i22);
            this.sampleBytesRead += i22;
            try {
                i11 = i22;
                try {
                    bArrTransformHevcSample = this.dolbyVisionSampleTransformer.transformHevcSample(bArr2, i11, track.nalUnitLengthFieldLength, track.pendingDolbyVisionBlockAdditionalData, track.dolbyVisionConfigBytes);
                } catch (RuntimeException e6) {
                    e = e6;
                }
            } catch (RuntimeException e10) {
                e = e10;
                i11 = i22;
            }
            if (bArrTransformHevcSample != null) {
                try {
                    iLastTransformedSampleLength = this.dolbyVisionSampleTransformer.lastTransformedSampleLength();
                    bArr2 = bArrTransformHevcSample;
                } catch (RuntimeException e11) {
                    e = e11;
                    bArr2 = bArrTransformHevcSample;
                    Log.w(TAG, "DolbyVisionSampleTransformer.transformHevcSample failed: " + e.getMessage());
                    iLastTransformedSampleLength = i11;
                }
            } else {
                iLastTransformedSampleLength = i11;
            }
            if (z5) {
                writeSupplementalMainSampleSizePrefix(trackOutput, getAnnexBSize(bArr2, iLastTransformedSampleLength, track.nalUnitLengthFieldLength));
                this.sampleBytesWritten += 4;
                this.sampleBytesWritten += writeLengthDelimitedSampleAsAnnexB(trackOutput, bArr2, iLastTransformedSampleLength, track.nalUnitLengthFieldLength, track.codecId);
                trackOutput = trackOutput;
            } else {
                int iWriteLengthDelimitedSampleAsAnnexB = writeLengthDelimitedSampleAsAnnexB(trackOutput, bArr2, iLastTransformedSampleLength, track.nalUnitLengthFieldLength, track.codecId);
                trackOutput = trackOutput;
                this.sampleBytesWritten += iWriteLengthDelimitedSampleAsAnnexB;
            }
        }
        if (CODEC_ID_VORBIS.equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
        return finishWriteSampleData();
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i10) throws IOException {
        int length = bArr.length + i10;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + i10));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i10);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private void writeSupplementalMainSampleSizePrefix(TrackOutput trackOutput, int i10) {
        this.scratch.reset(4);
        this.scratch.getData()[0] = (byte) ((i10 >> 24) & 255);
        this.scratch.getData()[1] = (byte) ((i10 >> 16) & 255);
        this.scratch.getData()[2] = (byte) ((i10 >> 8) & 255);
        this.scratch.getData()[3] = (byte) (i10 & 255);
        trackOutput.sampleData(this.scratch, 4, 2);
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i10) throws IOException {
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i10, false);
        }
        int iMin = Math.min(i10, iBytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, iMin);
        return iMin;
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i10, int i11) throws IOException {
        int iMin = Math.min(i11, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i10 + iMin, i11 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i10, iMin);
        }
    }

    public void binaryElement(int i10, int i11, ExtractorInput extractorInput) throws IOException {
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        long j10;
        int i18;
        int i19;
        int i20;
        int i21 = 2;
        int i22 = 0;
        int i23 = 1;
        if (i10 != ID_BLOCK && i10 != ID_SIMPLE_BLOCK) {
            if (i10 == ID_BLOCK_ADDITIONAL) {
                if (this.blockState != 2) {
                    return;
                }
                handleBlockAdditionalData(this.tracks.get(this.blockTrackNumber), this.blockAdditionalId, extractorInput, i11);
                return;
            }
            if (i10 == ID_BLOCK_ADD_ID_EXTRA_DATA) {
                handleBlockAddIDExtraData(getCurrentTrack(i10), extractorInput, i11);
                return;
            }
            if (i10 == ID_CONTENT_COMPRESSION_SETTINGS) {
                assertInTrackEntry(i10);
                byte[] bArr = new byte[i11];
                this.currentTrack.sampleStrippedBytes = bArr;
                extractorInput.readFully(bArr, 0, i11);
                return;
            }
            if (i10 == ID_CONTENT_ENCRYPTION_KEY_ID) {
                byte[] bArr2 = new byte[i11];
                extractorInput.readFully(bArr2, 0, i11);
                getCurrentTrack(i10).cryptoData = new TrackOutput.CryptoData(1, bArr2, 0, 0);
                return;
            }
            if (i10 == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.getData(), (byte) 0);
                extractorInput.readFully(this.seekEntryIdBytes.getData(), 4 - i11, i11);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i10 == ID_CODEC_PRIVATE) {
                assertInTrackEntry(i10);
                byte[] bArr3 = new byte[i11];
                this.currentTrack.codecPrivate = bArr3;
                extractorInput.readFully(bArr3, 0, i11);
                return;
            }
            if (i10 != ID_PROJECTION_PRIVATE) {
                throw ParserException.createForMalformedContainer("Unexpected id: " + i10, null);
            }
            assertInTrackEntry(i10);
            byte[] bArr4 = new byte[i11];
            this.currentTrack.projectionData = bArr4;
            extractorInput.readFully(bArr4, 0, i11);
            return;
        }
        int i24 = 8;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = C.TIME_UNSET;
            this.blockState = 1;
            this.scratch.reset(0);
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput.skipFully(i11 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        track.assertOutputInitialized();
        if (this.blockState == 1) {
            readScratch(extractorInput, 3);
            int i25 = (this.scratch.getData()[2] & 6) >> 1;
            byte b10 = 255;
            if (i25 == 0) {
                this.blockSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockSampleSizes, 1);
                this.blockSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i11 - this.blockTrackNumberLength) - 3;
            } else {
                readScratch(extractorInput, 4);
                int i26 = (this.scratch.getData()[3] & 255) + 1;
                this.blockSampleCount = i26;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockSampleSizes, i26);
                this.blockSampleSizes = iArrEnsureArrayCapacity2;
                if (i25 == 2) {
                    int i27 = (i11 - this.blockTrackNumberLength) - 4;
                    int i28 = this.blockSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i28, i27 / i28);
                } else {
                    if (i25 != 1) {
                        if (i25 != 3) {
                            throw ParserException.createForMalformedContainer("Unexpected lacing value: " + i25, null);
                        }
                        int i29 = 0;
                        int i30 = 0;
                        int i31 = 4;
                        while (true) {
                            int i32 = this.blockSampleCount;
                            i12 = i22;
                            if (i29 >= i32 - 1) {
                                i13 = i21;
                                i14 = i23;
                                this.blockSampleSizes[i32 - 1] = ((i11 - this.blockTrackNumberLength) - i31) - i30;
                                break;
                            }
                            this.blockSampleSizes[i29] = i12;
                            int i33 = i31 + 1;
                            readScratch(extractorInput, i33);
                            if (this.scratch.getData()[i31] == 0) {
                                throw ParserException.createForMalformedContainer("No valid varint length mask found", null);
                            }
                            int i34 = i12;
                            while (true) {
                                if (i34 >= i24) {
                                    i15 = i21;
                                    i16 = i23;
                                    i17 = i24;
                                    j10 = 0;
                                    break;
                                }
                                i17 = i24;
                                int i35 = i23 << (7 - i34);
                                i15 = i21;
                                if ((this.scratch.getData()[i31] & i35) != 0) {
                                    i33 += i34;
                                    readScratch(extractorInput, i33);
                                    int i36 = i31 + 1;
                                    i16 = i23;
                                    j10 = this.scratch.getData()[i31] & b10 & (~i35);
                                    while (true) {
                                        int i37 = i36;
                                        if (i37 >= i33) {
                                            break;
                                        }
                                        i36 = i37 + 1;
                                        j10 = (j10 << i17) | ((long) (this.scratch.getData()[i37] & b10));
                                        b10 = 255;
                                    }
                                    if (i29 > 0) {
                                        j10 -= (1 << ((i34 * 7) + 6)) - 1;
                                    }
                                } else {
                                    i34++;
                                    i21 = i15;
                                    i24 = i17;
                                    b10 = 255;
                                }
                            }
                            i31 = i33;
                            if (j10 < -2147483648L || j10 > 2147483647L) {
                                break;
                            }
                            int i38 = (int) j10;
                            int[] iArr = this.blockSampleSizes;
                            if (i29 != 0) {
                                i38 += iArr[i29 - 1];
                            }
                            iArr[i29] = i38;
                            i30 += i38;
                            i29++;
                            i22 = i12;
                            i21 = i15;
                            i24 = i17;
                            i23 = i16;
                            b10 = 255;
                        }
                        throw ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
                    }
                    int i39 = 0;
                    int i40 = 0;
                    int i41 = 4;
                    while (true) {
                        i18 = this.blockSampleCount;
                        if (i39 >= i18 - 1) {
                            break;
                        }
                        this.blockSampleSizes[i39] = 0;
                        while (true) {
                            i19 = i41 + 1;
                            readScratch(extractorInput, i19);
                            int i42 = this.scratch.getData()[i41] & 255;
                            int[] iArr2 = this.blockSampleSizes;
                            i20 = iArr2[i39] + i42;
                            iArr2[i39] = i20;
                            if (i42 != 255) {
                                break;
                            } else {
                                i41 = i19;
                            }
                        }
                        i40 += i20;
                        i39++;
                        i41 = i19;
                    }
                    this.blockSampleSizes[i18 - 1] = ((i11 - this.blockTrackNumberLength) - i41) - i40;
                }
            }
            i13 = 2;
            i12 = 0;
            i14 = 1;
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.getData()[i12] << 8) | (this.scratch.getData()[i14] & 255));
            this.blockFlags = (track.type == i14 || (i10 == ID_SIMPLE_BLOCK && (this.scratch.getData()[i13] & 128) == 128)) ? 1 : i12;
            this.blockState = i13;
            this.blockSampleIndex = i12;
        }
        if (i10 == ID_SIMPLE_BLOCK) {
            while (true) {
                int i43 = this.blockSampleIndex;
                if (i43 >= this.blockSampleCount) {
                    this.blockState = 0;
                    return;
                }
                int iWriteSampleData = writeSampleData(extractorInput, track, this.blockSampleSizes[i43], false);
                Track track2 = track;
                commitSampleToOutput(track2, this.blockTimeUs + ((long) ((this.blockSampleIndex * track.defaultSampleDurationNs) / 1000)), this.blockFlags, iWriteSampleData, 0);
                this.blockSampleIndex++;
                track = track2;
            }
        } else {
            while (true) {
                int i44 = this.blockSampleIndex;
                if (i44 >= this.blockSampleCount) {
                    return;
                }
                int[] iArr3 = this.blockSampleSizes;
                iArr3[i44] = writeSampleData(extractorInput, track, iArr3[i44], true);
                this.blockSampleIndex++;
            }
        }
    }

    public void endMasterElement(int i10) throws ParserException {
        int i11;
        assertInitialized();
        if (i10 == ID_BLOCK_GROUP) {
            if (this.blockState != 2) {
                return;
            }
            Track track = this.tracks.get(this.blockTrackNumber);
            track.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i12 = 0;
            for (int i13 = 0; i13 < this.blockSampleCount; i13++) {
                i12 += this.blockSampleSizes[i13];
            }
            int i14 = 0;
            while (i14 < this.blockSampleCount) {
                long j10 = this.blockTimeUs + ((long) ((track.defaultSampleDurationNs * i14) / 1000));
                int i15 = this.blockFlags;
                if (i14 == 0 && !this.blockHasReferenceBlock) {
                    i15 |= 1;
                }
                int i16 = this.blockSampleSizes[i14];
                int i17 = i12 - i16;
                commitSampleToOutput(track, j10, i15, i16, i17);
                i14++;
                i12 = i17;
            }
            this.blockState = 0;
            return;
        }
        if (i10 == ID_TRACK_ENTRY) {
            Track track2 = this.currentTrack;
            track2.getClass();
            String str = track2.codecId;
            if (str == null) {
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
            }
            if (isCodecSupported(str)) {
                track2.initializeFormat(track2.number, this.dolbyVisionSampleTransformer);
                track2.output = this.extractorOutput.track(track2.number, track2.type);
                this.tracks.put(track2.number, track2);
            }
            this.currentTrack = null;
            return;
        }
        if (i10 == ID_CUE_TRACK_POSITIONS) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i10);
            if (this.currentCueTimeUs == C.TIME_UNSET || (i11 = this.currentCueTrackNumber) == -1 || this.currentCueClusterPosition == -1) {
                return;
            }
            List<MatroskaSeekMap.CuePointData> arrayList = this.perTrackCues.get(i11);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.perTrackCues.put(this.currentCueTrackNumber, arrayList);
            }
            arrayList.add(new MatroskaSeekMap.CuePointData(0, this.currentCueTimeUs, this.segmentContentPosition + this.currentCueClusterPosition, this.currentCueRelativePosition));
            return;
        }
        if (i10 == ID_SEEK) {
            int i18 = this.seekEntryId;
            if (i18 != -1) {
                long j11 = this.seekEntryPosition;
                if (j11 != -1) {
                    if (i18 == ID_CUES) {
                        this.cuesContentPosition = j11;
                        return;
                    }
                    return;
                }
            }
            throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
        }
        if (i10 == ID_CONTENT_ENCODING) {
            assertInTrackEntry(i10);
            Track track3 = this.currentTrack;
            if (track3.hasContentEncryption) {
                if (track3.cryptoData == null) {
                    throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
                }
                track3.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                return;
            }
            return;
        }
        if (i10 == ID_CONTENT_ENCODINGS) {
            assertInTrackEntry(i10);
            Track track4 = this.currentTrack;
            if (track4.hasContentEncryption && track4.sampleStrippedBytes != null) {
                throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
            }
            return;
        }
        if (i10 == 357149030) {
            if (this.timecodeScale == C.TIME_UNSET) {
                this.timecodeScale = 1000000L;
            }
            long j12 = this.durationTimecode;
            if (j12 != C.TIME_UNSET) {
                this.durationUs = scaleTimecodeToUs(j12);
                return;
            }
            return;
        }
        if (i10 != ID_TRACKS) {
            if (i10 == ID_CUES && !this.sentSeekMap) {
                int i19 = 0;
                while (true) {
                    if (i19 >= this.perTrackCues.size()) {
                        break;
                    }
                    if (this.perTrackCues.valueAt(i19).isEmpty()) {
                        i19++;
                    } else {
                        if (this.durationUs == C.TIME_UNSET) {
                            break;
                        }
                        for (int i20 = 0; i20 < this.perTrackCues.size(); i20++) {
                            Collections.sort(this.perTrackCues.valueAt(i20));
                        }
                        this.extractorOutput.seekMap(new MatroskaSeekMap(this.perTrackCues, this.durationUs, this.primarySeekTrackNumber, this.segmentContentPosition, this.segmentContentSize));
                    }
                }
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                this.inCuesElement = false;
                for (int i21 = 0; i21 < this.tracks.size(); i21++) {
                    Track trackValueAt = this.tracks.valueAt(i21);
                    trackValueAt.maybeAddThumbnailMetadata(this.perTrackCues, this.durationUs, this.segmentContentPosition, this.segmentContentSize);
                    if (!trackValueAt.waitingForDtsAnalysis) {
                        trackValueAt.assertOutputInitialized();
                        TrackOutput trackOutput = trackValueAt.output;
                        Format format = trackValueAt.format;
                        format.getClass();
                        trackOutput.format(format);
                    }
                }
                maybeEndTracks();
                return;
            }
            return;
        }
        if (this.tracks.size() == 0) {
            throw ParserException.createForMalformedContainer("No valid tracks were found", null);
        }
        boolean z = !this.seekForCuesEnabled || this.cuesContentPosition == -1;
        int i22 = -1;
        int i23 = -1;
        int i24 = -1;
        int i25 = -1;
        for (int i26 = 0; i26 < this.tracks.size(); i26++) {
            Track trackValueAt2 = this.tracks.valueAt(i26);
            int i27 = trackValueAt2.type;
            if (i27 == 2) {
                if (trackValueAt2.flagDefault) {
                    i22 = trackValueAt2.number;
                }
                if (i23 == -1) {
                    i23 = trackValueAt2.number;
                }
            } else if (i27 == 1) {
                if (trackValueAt2.flagDefault) {
                    i24 = trackValueAt2.number;
                }
                if (i25 == -1) {
                    i25 = trackValueAt2.number;
                }
            }
            if (z) {
                trackValueAt2.assertOutputInitialized();
                if (!trackValueAt2.waitingForDtsAnalysis) {
                    TrackOutput trackOutput2 = trackValueAt2.output;
                    Format format2 = trackValueAt2.format;
                    format2.getClass();
                    trackOutput2.format(format2);
                }
            }
        }
        if (i22 != -1) {
            this.primarySeekTrackNumber = i22;
        } else if (i23 != -1) {
            this.primarySeekTrackNumber = i23;
        } else if (i24 != -1) {
            this.primarySeekTrackNumber = i24;
        } else if (i25 != -1) {
            this.primarySeekTrackNumber = i25;
        } else {
            this.primarySeekTrackNumber = this.tracks.size() > 0 ? this.tracks.valueAt(0).number : -1;
        }
        if (z) {
            maybeEndTracks();
        }
    }

    public void floatElement(int i10, double d4) throws ParserException {
        if (i10 == ID_SAMPLING_FREQUENCY) {
            getCurrentTrack(i10).sampleRate = (int) d4;
            return;
        }
        if (i10 == ID_DURATION) {
            this.durationTimecode = (long) d4;
            return;
        }
        switch (i10) {
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
                getCurrentTrack(i10).primaryRChromaticityX = (float) d4;
                break;
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
                getCurrentTrack(i10).primaryRChromaticityY = (float) d4;
                break;
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
                getCurrentTrack(i10).primaryGChromaticityX = (float) d4;
                break;
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
                getCurrentTrack(i10).primaryGChromaticityY = (float) d4;
                break;
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
                getCurrentTrack(i10).primaryBChromaticityX = (float) d4;
                break;
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
                getCurrentTrack(i10).primaryBChromaticityY = (float) d4;
                break;
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
                getCurrentTrack(i10).whitePointChromaticityX = (float) d4;
                break;
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
                getCurrentTrack(i10).whitePointChromaticityY = (float) d4;
                break;
            case ID_LUMNINANCE_MAX /* 21977 */:
                getCurrentTrack(i10).maxMasteringLuminance = (float) d4;
                break;
            case ID_LUMNINANCE_MIN /* 21978 */:
                getCurrentTrack(i10).minMasteringLuminance = (float) d4;
                break;
            default:
                switch (i10) {
                    case ID_PROJECTION_POSE_YAW /* 30323 */:
                        getCurrentTrack(i10).projectionPoseYaw = (float) d4;
                        break;
                    case ID_PROJECTION_POSE_PITCH /* 30324 */:
                        getCurrentTrack(i10).projectionPosePitch = (float) d4;
                        break;
                    case ID_PROJECTION_POSE_ROLL /* 30325 */:
                        getCurrentTrack(i10).projectionPoseRoll = (float) d4;
                        break;
                }
                break;
        }
    }

    public Track getCurrentTrack(int i10) throws ParserException {
        assertInTrackEntry(i10);
        return this.currentTrack;
    }

    public DolbyVisionSampleTransformer getDolbyVisionSampleTransformer() {
        return this.dolbyVisionSampleTransformer;
    }

    public int getElementType(int i10) {
        switch (i10) {
            case ID_TRACK_TYPE /* 131 */:
            case 136:
            case ID_BLOCK_DURATION /* 155 */:
            case ID_CHANNELS /* 159 */:
            case ID_PIXEL_WIDTH /* 176 */:
            case ID_CUE_TIME /* 179 */:
            case ID_PIXEL_HEIGHT /* 186 */:
            case ID_TRACK_NUMBER /* 215 */:
            case ID_TIME_CODE /* 231 */:
            case ID_BLOCK_ADD_ID /* 238 */:
            case 240:
            case ID_CUE_CLUSTER_POSITION /* 241 */:
            case ID_CUE_TRACK /* 247 */:
            case ID_REFERENCE_BLOCK /* 251 */:
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
            case ID_EBML_READ_VERSION /* 17143 */:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
            case ID_SEEK_POSITION /* 21420 */:
            case ID_STEREO_MODE /* 21432 */:
            case ID_DISPLAY_WIDTH /* 21680 */:
            case ID_DISPLAY_UNIT /* 21682 */:
            case ID_DISPLAY_HEIGHT /* 21690 */:
            case ID_FLAG_FORCED /* 21930 */:
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
            case ID_COLOUR_RANGE /* 21945 */:
            case ID_COLOUR_TRANSFER /* 21946 */:
            case ID_COLOUR_PRIMARIES /* 21947 */:
            case ID_MAX_CLL /* 21948 */:
            case ID_MAX_FALL /* 21949 */:
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
            case ID_CODEC_DELAY /* 22186 */:
            case ID_SEEK_PRE_ROLL /* 22203 */:
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
            case ID_DISCARD_PADDING /* 30114 */:
            case ID_PROJECTION_TYPE /* 30321 */:
            case ID_DEFAULT_DURATION /* 2352003 */:
            case ID_TIMECODE_SCALE /* 2807729 */:
                return 2;
            case 134:
            case ID_DOC_TYPE /* 17026 */:
            case ID_NAME /* 21358 */:
            case ID_LANGUAGE /* 2274716 */:
                return 3;
            case ID_BLOCK_GROUP /* 160 */:
            case ID_BLOCK_MORE /* 166 */:
            case ID_TRACK_ENTRY /* 174 */:
            case ID_CUE_TRACK_POSITIONS /* 183 */:
            case ID_CUE_POINT /* 187 */:
            case 224:
            case ID_AUDIO /* 225 */:
            case ID_BLOCK_ADDITION_MAPPING /* 16868 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /* 18407 */:
            case ID_SEEK /* 19899 */:
            case ID_CONTENT_COMPRESSION /* 20532 */:
            case ID_CONTENT_ENCRYPTION /* 20533 */:
            case ID_COLOUR /* 21936 */:
            case ID_MASTERING_METADATA /* 21968 */:
            case ID_CONTENT_ENCODING /* 25152 */:
            case ID_CONTENT_ENCODINGS /* 28032 */:
            case ID_BLOCK_ADDITIONS /* 30113 */:
            case ID_PROJECTION /* 30320 */:
            case ID_SEEK_HEAD /* 290298740 */:
            case 357149030:
            case ID_TRACKS /* 374648427 */:
            case ID_SEGMENT /* 408125543 */:
            case ID_EBML /* 440786851 */:
            case ID_CUES /* 475249515 */:
            case ID_CLUSTER /* 524531317 */:
                return 1;
            case ID_BLOCK /* 161 */:
            case ID_SIMPLE_BLOCK /* 163 */:
            case ID_BLOCK_ADDITIONAL /* 165 */:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /* 16877 */:
            case ID_CONTENT_COMPRESSION_SETTINGS /* 16981 */:
            case ID_CONTENT_ENCRYPTION_KEY_ID /* 18402 */:
            case ID_SEEK_ID /* 21419 */:
            case ID_CODEC_PRIVATE /* 25506 */:
            case ID_PROJECTION_PRIVATE /* 30322 */:
                return 4;
            case ID_SAMPLING_FREQUENCY /* 181 */:
            case ID_DURATION /* 17545 */:
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
            case ID_LUMNINANCE_MAX /* 21977 */:
            case ID_LUMNINANCE_MIN /* 21978 */:
            case ID_PROJECTION_POSE_YAW /* 30323 */:
            case ID_PROJECTION_POSE_PITCH /* 30324 */:
            case ID_PROJECTION_POSE_ROLL /* 30325 */:
                return 5;
            default:
                return 0;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return androidx.media3.extractor.b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return androidx.media3.extractor.b.b(this);
    }

    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i10) throws IOException {
        if (track.blockAddIdType != 1685485123 && track.blockAddIdType != 1685480259) {
            extractorInput.skipFully(i10);
            return;
        }
        byte[] bArr = new byte[i10];
        track.dolbyVisionConfigBytes = bArr;
        extractorInput.readFully(bArr, 0, i10);
    }

    public void handleBlockAdditionalData(Track track, int i10, ExtractorInput extractorInput, int i11) throws IOException {
        if (i10 == 4 && CODEC_ID_VP9.equals(track.codecId)) {
            this.supplementalData.reset(i11);
            extractorInput.readFully(this.supplementalData.getData(), 0, i11);
            return;
        }
        if (!CODEC_ID_H265.equals(track.codecId) || (track.blockAddIdType != 1685485123 && track.blockAddIdType != 1685480259)) {
            extractorInput.skipFully(i11);
            return;
        }
        byte[] bArr = new byte[i11];
        extractorInput.readFully(bArr, 0, i11);
        track.pendingDolbyVisionBlockAdditionalData = bArr;
        DolbyVisionSampleTransformer dolbyVisionSampleTransformer = this.dolbyVisionSampleTransformer;
        if (dolbyVisionSampleTransformer == null || track.dolbyVisionProfile != 7) {
            return;
        }
        try {
            byte[] bArrOnDolbyVisionBlockAdditionalData = dolbyVisionSampleTransformer.onDolbyVisionBlockAdditionalData(bArr, track.blockAddIdType, track.dolbyVisionConfigBytes);
            if (bArrOnDolbyVisionBlockAdditionalData != null) {
                track.pendingDolbyVisionBlockAdditionalData = bArrOnDolbyVisionBlockAdditionalData;
            }
        } catch (RuntimeException e5) {
            Log.w(TAG, "DolbyVisionSampleTransformer.onDolbyVisionBlockAdditionalData failed: " + e5.getMessage());
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
    }

    public void integerElement(int i10, long j10) throws ParserException {
        if (i10 == 240) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i10);
            if (this.currentCueRelativePosition == -1) {
                this.currentCueRelativePosition = j10;
                return;
            }
            return;
        }
        if (i10 == ID_CUE_CLUSTER_POSITION) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i10);
            if (this.currentCueClusterPosition == -1) {
                this.currentCueClusterPosition = j10;
                return;
            }
            return;
        }
        if (i10 == ID_CONTENT_ENCODING_ORDER) {
            if (j10 == 0) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j10 + " not supported", null);
        }
        if (i10 == ID_CONTENT_ENCODING_SCOPE) {
            if (j10 == 1) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingScope " + j10 + " not supported", null);
        }
        switch (i10) {
            case ID_TRACK_TYPE /* 131 */:
                int i11 = (int) j10;
                if (i11 == 1) {
                    getCurrentTrack(i10).type = 2;
                    return;
                }
                if (i11 == 2) {
                    getCurrentTrack(i10).type = 1;
                    return;
                }
                if (i11 == 17) {
                    getCurrentTrack(i10).type = 3;
                    return;
                } else if (i11 != 33) {
                    getCurrentTrack(i10).type = -1;
                    return;
                } else {
                    getCurrentTrack(i10).type = 5;
                    return;
                }
            case 136:
                getCurrentTrack(i10).flagDefault = j10 == 1;
                return;
            case ID_BLOCK_DURATION /* 155 */:
                this.blockDurationUs = scaleTimecodeToUs(j10);
                return;
            case ID_CHANNELS /* 159 */:
                getCurrentTrack(i10).channelCount = (int) j10;
                return;
            case ID_PIXEL_WIDTH /* 176 */:
                getCurrentTrack(i10).width = (int) j10;
                return;
            case ID_CUE_TIME /* 179 */:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(i10);
                this.currentCueTimeUs = scaleTimecodeToUs(j10);
                return;
            case ID_PIXEL_HEIGHT /* 186 */:
                getCurrentTrack(i10).height = (int) j10;
                return;
            case ID_TRACK_NUMBER /* 215 */:
                getCurrentTrack(i10).number = (int) j10;
                return;
            case ID_TIME_CODE /* 231 */:
                this.clusterTimecodeUs = scaleTimecodeToUs(j10);
                return;
            case ID_BLOCK_ADD_ID /* 238 */:
                this.blockAdditionalId = (int) j10;
                return;
            case ID_CUE_TRACK /* 247 */:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(i10);
                this.currentCueTrackNumber = (int) j10;
                return;
            case ID_REFERENCE_BLOCK /* 251 */:
                this.blockHasReferenceBlock = true;
                return;
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
                getCurrentTrack(i10).blockAddIdType = (int) j10;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                if (j10 == 3) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentCompAlgo " + j10 + " not supported", null);
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                if (j10 < 1 || j10 > 2) {
                    throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j10 + " not supported", null);
                }
                return;
            case ID_EBML_READ_VERSION /* 17143 */:
                if (j10 == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("EBMLReadVersion " + j10 + " not supported", null);
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                if (j10 == 5) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentEncAlgo " + j10 + " not supported", null);
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                if (j10 == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j10 + " not supported", null);
            case ID_SEEK_POSITION /* 21420 */:
                this.seekEntryPosition = j10 + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /* 21432 */:
                int i12 = (int) j10;
                assertInTrackEntry(i10);
                if (i12 == 0) {
                    this.currentTrack.stereoMode = 0;
                    return;
                }
                if (i12 == 1) {
                    this.currentTrack.stereoMode = 2;
                    return;
                } else if (i12 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else {
                    if (i12 != 15) {
                        return;
                    }
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /* 21680 */:
                getCurrentTrack(i10).displayWidth = (int) j10;
                return;
            case ID_DISPLAY_UNIT /* 21682 */:
                getCurrentTrack(i10).displayUnit = (int) j10;
                return;
            case ID_DISPLAY_HEIGHT /* 21690 */:
                getCurrentTrack(i10).displayHeight = (int) j10;
                return;
            case ID_FLAG_FORCED /* 21930 */:
                getCurrentTrack(i10).flagForced = j10 == 1;
                return;
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
                assertInTrackEntry(i10);
                Track track = this.currentTrack;
                track.hasColorInfo = true;
                track.bitsPerChannel = (int) j10;
                return;
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
                getCurrentTrack(i10).maxBlockAdditionId = (int) j10;
                return;
            case ID_CODEC_DELAY /* 22186 */:
                getCurrentTrack(i10).codecDelayNs = j10;
                return;
            case ID_SEEK_PRE_ROLL /* 22203 */:
                getCurrentTrack(i10).seekPreRollNs = j10;
                return;
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
                getCurrentTrack(i10).audioBitDepth = (int) j10;
                return;
            case ID_DISCARD_PADDING /* 30114 */:
                this.blockGroupDiscardPaddingNs = j10;
                return;
            case ID_PROJECTION_TYPE /* 30321 */:
                assertInTrackEntry(i10);
                int i13 = (int) j10;
                if (i13 == 0) {
                    this.currentTrack.projectionType = 0;
                    return;
                }
                if (i13 == 1) {
                    this.currentTrack.projectionType = 1;
                    return;
                } else if (i13 == 2) {
                    this.currentTrack.projectionType = 2;
                    return;
                } else {
                    if (i13 != 3) {
                        return;
                    }
                    this.currentTrack.projectionType = 3;
                    return;
                }
            case ID_DEFAULT_DURATION /* 2352003 */:
                getCurrentTrack(i10).defaultSampleDurationNs = (int) j10;
                return;
            case ID_TIMECODE_SCALE /* 2807729 */:
                this.timecodeScale = j10;
                return;
            default:
                switch (i10) {
                    case ID_COLOUR_RANGE /* 21945 */:
                        assertInTrackEntry(i10);
                        int i14 = (int) j10;
                        if (i14 == 1) {
                            this.currentTrack.colorRange = 2;
                            return;
                        } else {
                            if (i14 != 2) {
                                return;
                            }
                            this.currentTrack.colorRange = 1;
                            return;
                        }
                    case ID_COLOUR_TRANSFER /* 21946 */:
                        assertInTrackEntry(i10);
                        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j10);
                        if (iIsoTransferCharacteristicsToColorTransfer != -1) {
                            this.currentTrack.colorTransfer = iIsoTransferCharacteristicsToColorTransfer;
                            return;
                        }
                        return;
                    case ID_COLOUR_PRIMARIES /* 21947 */:
                        assertInTrackEntry(i10);
                        this.currentTrack.hasColorInfo = true;
                        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j10);
                        if (iIsoColorPrimariesToColorSpace != -1) {
                            this.currentTrack.colorSpace = iIsoColorPrimariesToColorSpace;
                            return;
                        }
                        return;
                    case ID_MAX_CLL /* 21948 */:
                        getCurrentTrack(i10).maxContentLuminance = (int) j10;
                        return;
                    case ID_MAX_FALL /* 21949 */:
                        getCurrentTrack(i10).maxFrameAverageLuminance = (int) j10;
                        return;
                    default:
                        return;
                }
        }
    }

    public boolean isLevel1Element(int i10) {
        return i10 == 357149030 || i10 == ID_CLUSTER || i10 == ID_CUES || i10 == ID_TRACKS;
    }

    @Override // androidx.media3.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i10 = 0; i10 < this.tracks.size(); i10++) {
            Track trackValueAt = this.tracks.valueAt(i10);
            trackValueAt.assertOutputInitialized();
            trackValueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    @Override // androidx.media3.extractor.Extractor
    public final void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        this.inCuesElement = false;
        this.currentCueTimeUs = C.TIME_UNSET;
        this.currentCueTrackNumber = -1;
        this.currentCueClusterPosition = -1L;
        this.currentCueRelativePosition = -1L;
        if (!this.sentSeekMap) {
            this.perTrackCues.clear();
        }
        for (int i10 = 0; i10 < this.tracks.size(); i10++) {
            this.tracks.valueAt(i10).reset();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    public void startMasterElement(int i10, long j10, long j11) throws ParserException {
        assertInitialized();
        if (i10 == ID_BLOCK_GROUP) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0L;
            return;
        }
        if (i10 == ID_TRACK_ENTRY) {
            Track track = new Track();
            this.currentTrack = track;
            track.isWebm = this.isWebm;
            return;
        }
        if (i10 == ID_CUE_TRACK_POSITIONS) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i10);
            this.currentCueTrackNumber = -1;
            this.currentCueClusterPosition = -1L;
            this.currentCueRelativePosition = -1L;
            return;
        }
        if (i10 == ID_CUE_POINT) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i10);
            this.currentCueTimeUs = C.TIME_UNSET;
            return;
        }
        if (i10 == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i10 == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(i10).hasContentEncryption = true;
            return;
        }
        if (i10 == ID_MASTERING_METADATA) {
            getCurrentTrack(i10).hasColorInfo = true;
            return;
        }
        if (i10 == ID_SEGMENT) {
            long j12 = this.segmentContentPosition;
            if (j12 != -1 && j12 != j10) {
                throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
            }
            this.segmentContentPosition = j10;
            this.segmentContentSize = j11;
            return;
        }
        if (i10 == ID_CUES) {
            if (this.sentSeekMap) {
                return;
            }
            this.inCuesElement = true;
        } else if (i10 == ID_CLUSTER && !this.sentSeekMap) {
            if (this.seekForCuesEnabled && this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
            }
        }
    }

    public void stringElement(int i10, String str) throws ParserException {
        if (i10 == 134) {
            getCurrentTrack(i10).codecId = str;
            return;
        }
        if (i10 != ID_DOC_TYPE) {
            if (i10 == ID_NAME) {
                getCurrentTrack(i10).name = str;
                return;
            } else {
                if (i10 != ID_LANGUAGE) {
                    return;
                }
                getCurrentTrack(i10).language = str;
                return;
            }
        }
        if (DOC_TYPE_WEBM.equals(str) || DOC_TYPE_MATROSKA.equals(str)) {
            this.isWebm = Objects.equals(str, DOC_TYPE_WEBM);
            return;
        }
        throw ParserException.createForMalformedContainer("DocType " + str + " not supported", null);
    }

    @Deprecated
    public MatroskaExtractor(int i10) {
        this(new DefaultEbmlReader(), i10 | 2, SubtitleParser.Factory.UNSUPPORTED, null);
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory, DolbyVisionSampleTransformer dolbyVisionSampleTransformer) {
        return new androidx.media3.exoplayer.analytics.b(factory, dolbyVisionSampleTransformer, 12);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory) {
        this(new DefaultEbmlReader(), 0, factory, null);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i10) {
        this(new DefaultEbmlReader(), i10, factory, null);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i10, DolbyVisionSampleTransformer dolbyVisionSampleTransformer) {
        this(new DefaultEbmlReader(), i10, factory, dolbyVisionSampleTransformer);
    }

    public MatroskaExtractor(EbmlReader ebmlReader, int i10, SubtitleParser.Factory factory) {
        this(ebmlReader, i10, factory, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MatroskaExtractor(EbmlReader ebmlReader, int i10, SubtitleParser.Factory factory, DolbyVisionSampleTransformer dolbyVisionSampleTransformer) {
        this.dolbyVisionSampleBuffer = new byte[0];
        this.segmentContentPosition = -1L;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.currentCueTimeUs = C.TIME_UNSET;
        this.currentCueTrackNumber = -1;
        this.currentCueClusterPosition = -1L;
        this.currentCueRelativePosition = -1L;
        this.primarySeekTrackNumber = -1;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor(this, 0 == true ? 1 : 0));
        this.subtitleParserFactory = factory;
        this.dolbyVisionSampleTransformer = dolbyVisionSampleTransformer;
        this.perTrackCues = new SparseArray<>();
        this.seekForCuesEnabled = (i10 & 1) == 0;
        this.parseSubtitlesDuringExtraction = (i10 & 2) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
        this.pendingEndTracks = true;
    }
}

package androidx.media3.extractor.mp4;

import a0.c;
import android.util.Pair;
import android.util.SparseArray;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.h;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexMerger;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageEncoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.m;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentedMp4Extractor implements Extractor {
    private static final int EXTRA_TRACKS_BASE_ID = 100;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 32;
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    public static final int FLAG_MERGE_FRAGMENTED_SIDX = 256;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES = 64;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES_H265 = 128;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final int SAMPLE_GROUP_TYPE_seig = 1936025959;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] ceaTrackOutputs;
    private final ChunkIndexMerger chunkIndexMerger;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final EventMessageEncoder eventMessageEncoder;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private boolean haveOutputSeekMapFromMultipleSidx;
    private boolean isSampleDependedOn;
    private h1 lastSniffFailures;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private final ParsableByteArray nalUnitWithoutHeaderBuffer;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private final ReorderingBufferQueue reorderingBufferQueue;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final ParsableByteArray scratch;
    private final byte[] scratchBytes;
    private long seekPositionBeforeSidxProcessing;
    private long segmentIndexEarliestPresentationTimeUs;
    private final Track sideloadedTrack;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new h(29);
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class MetadataSampleInfo {
        public final boolean sampleTimeIsRelative;
        public final long sampleTimeUs;
        public final int size;

        public MetadataSampleInfo(long j10, boolean z, int i10) {
            this.sampleTimeUs = j10;
            this.sampleTimeIsRelative = z;
            this.size = i10;
        }
    }

    public static final class TrackBundle {
        private static final int SINGLE_SUBSAMPLE_ENCRYPTION_DATA_LENGTH = 8;
        private final Format baseFormat;
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        private boolean currentlyInFragment;
        public DefaultSampleValues defaultSampleValues;
        public int firstSampleToOutputIndex;
        public TrackSampleTable moovSampleTable;
        public final TrackOutput output;
        public final TrackFragment fragment = new TrackFragment();
        public final ParsableByteArray scratch = new ParsableByteArray();
        private final ParsableByteArray encryptionSignalByte = new ParsableByteArray(1);
        private final ParsableByteArray defaultInitializationVector = new ParsableByteArray();

        public TrackBundle(TrackOutput trackOutput, TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues, Format format) {
            this.output = trackOutput;
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues;
            this.baseFormat = format;
            reset(trackSampleTable, defaultSampleValues);
        }

        public int getCurrentSampleFlags() {
            int i10 = !this.currentlyInFragment ? this.moovSampleTable.flags[this.currentSampleIndex] : this.fragment.sampleIsSyncFrameTable[this.currentSampleIndex] ? 1 : 0;
            return getEncryptionBoxIfEncrypted() != null ? i10 | 1073741824 : i10;
        }

        public long getCurrentSampleOffset() {
            return !this.currentlyInFragment ? this.moovSampleTable.offsets[this.currentSampleIndex] : this.fragment.trunDataPosition[this.currentTrackRunIndex];
        }

        public long getCurrentSamplePresentationTimeUs() {
            return !this.currentlyInFragment ? this.moovSampleTable.timestampsUs[this.currentSampleIndex] : this.fragment.getSamplePresentationTimeUs(this.currentSampleIndex);
        }

        public int getCurrentSampleSize() {
            return !this.currentlyInFragment ? this.moovSampleTable.sizes[this.currentSampleIndex] : this.fragment.sampleSizeTable[this.currentSampleIndex];
        }

        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            if (!this.currentlyInFragment) {
                return null;
            }
            int i10 = ((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex;
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.fragment.trackEncryptionBox;
            if (sampleDescriptionEncryptionBox == null) {
                sampleDescriptionEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(i10);
            }
            if (sampleDescriptionEncryptionBox == null || !sampleDescriptionEncryptionBox.isEncrypted) {
                return null;
            }
            return sampleDescriptionEncryptionBox;
        }

        public boolean next() {
            this.currentSampleIndex++;
            if (!this.currentlyInFragment) {
                return false;
            }
            int i10 = this.currentSampleInTrackRun + 1;
            this.currentSampleInTrackRun = i10;
            int[] iArr = this.fragment.trunLength;
            int i11 = this.currentTrackRunIndex;
            if (i10 != iArr[i11]) {
                return true;
            }
            this.currentTrackRunIndex = i11 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        }

        public int outputSampleEncryptionData(int i10, int i11) {
            ParsableByteArray parsableByteArray;
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return 0;
            }
            int length = encryptionBoxIfEncrypted.perSampleIvSize;
            if (length != 0) {
                parsableByteArray = this.fragment.sampleEncryptionData;
            } else {
                byte[] bArr = (byte[]) Util.castNonNull(encryptionBoxIfEncrypted.defaultInitializationVector);
                this.defaultInitializationVector.reset(bArr, bArr.length);
                ParsableByteArray parsableByteArray2 = this.defaultInitializationVector;
                length = bArr.length;
                parsableByteArray = parsableByteArray2;
            }
            boolean zSampleHasSubsampleEncryptionTable = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            boolean z = zSampleHasSubsampleEncryptionTable || i11 != 0;
            this.encryptionSignalByte.getData()[0] = (byte) ((z ? 128 : 0) | length);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1, 1);
            this.output.sampleData(parsableByteArray, length, 1);
            if (!z) {
                return length + 1;
            }
            if (!zSampleHasSubsampleEncryptionTable) {
                this.scratch.reset(8);
                byte[] data = this.scratch.getData();
                data[0] = 0;
                data[1] = 1;
                data[2] = (byte) ((i11 >> 8) & 255);
                data[3] = (byte) (i11 & 255);
                data[4] = (byte) ((i10 >> 24) & 255);
                data[5] = (byte) ((i10 >> 16) & 255);
                data[6] = (byte) ((i10 >> 8) & 255);
                data[7] = (byte) (i10 & 255);
                this.output.sampleData(this.scratch, 8, 1);
                return length + 9;
            }
            ParsableByteArray parsableByteArray3 = this.fragment.sampleEncryptionData;
            int unsignedShort = parsableByteArray3.readUnsignedShort();
            parsableByteArray3.skipBytes(-2);
            int i12 = (unsignedShort * 6) + 2;
            if (i11 != 0) {
                this.scratch.reset(i12);
                byte[] data2 = this.scratch.getData();
                parsableByteArray3.readBytes(data2, 0, i12);
                int i13 = (((data2[2] & 255) << 8) | (data2[3] & 255)) + i11;
                data2[2] = (byte) ((i13 >> 8) & 255);
                data2[3] = (byte) (i13 & 255);
                parsableByteArray3 = this.scratch;
            }
            this.output.sampleData(parsableByteArray3, i12, 1);
            return length + 1 + i12;
        }

        public void reset(TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues) {
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues;
            this.output.format(this.baseFormat);
            resetFragmentInfo();
        }

        public void resetFragmentInfo() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
            this.currentlyInFragment = false;
        }

        public void seek(long j10) {
            int i10 = this.currentSampleIndex;
            while (true) {
                TrackFragment trackFragment = this.fragment;
                if (i10 >= trackFragment.sampleCount || trackFragment.getSamplePresentationTimeUs(i10) > j10) {
                    return;
                }
                if (this.fragment.sampleIsSyncFrameTable[i10]) {
                    this.firstSampleToOutputIndex = i10;
                }
                i10++;
            }
        }

        public void skipSampleEncryptionData() {
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return;
            }
            ParsableByteArray parsableByteArray = this.fragment.sampleEncryptionData;
            int i10 = encryptionBoxIfEncrypted.perSampleIvSize;
            if (i10 != 0) {
                parsableByteArray.skipBytes(i10);
            }
            if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex)) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() * 6);
            }
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex);
            this.output.format(this.baseFormat.buildUpon().setDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)).build());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public FragmentedMp4Extractor() {
        SubtitleParser.Factory factory = SubtitleParser.Factory.UNSUPPORTED;
        f1 f1Var = h1.f14020l;
        this(factory, 32, null, null, o3.f14078o, null);
    }

    private boolean canReadWithinGopSampleDependencies(Format format) {
        return Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264) ? (this.flags & 64) != 0 : Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265) && (this.flags & 128) != 0;
    }

    private static int checkNonNegative(int i10) throws ParserException {
        if (i10 >= 0) {
            return i10;
        }
        throw ParserException.createForMalformedContainer("Unexpected negative value: " + i10, null);
    }

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int i10) {
        int i11 = (i10 & 1) != 0 ? 64 : 0;
        return (i10 & 2) != 0 ? i11 | 128 : i11;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> sparseArray, int i10) {
        if (sparseArray.size() == 1) {
            return sparseArray.valueAt(0);
        }
        DefaultSampleValues defaultSampleValues = sparseArray.get(i10);
        defaultSampleValues.getClass();
        return defaultSampleValues;
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Mp4Box.LeafBox> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i10 = 0; i10 < size; i10++) {
            Mp4Box.LeafBox leafBox = list.get(i10);
            if (leafBox.type == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] data = leafBox.data.getData();
                UUID uuid = PsshAtomUtil.parseUuid(data);
                if (uuid == null) {
                    Log.w(TAG, "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(uuid, MimeTypes.VIDEO_MP4, data));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData(arrayList);
    }

    private static TrackBundle getNextTrackBundle(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j10 = Long.MAX_VALUE;
        for (int i10 = 0; i10 < size; i10++) {
            TrackBundle trackBundleValueAt = sparseArray.valueAt(i10);
            if ((trackBundleValueAt.currentlyInFragment || trackBundleValueAt.currentSampleIndex != trackBundleValueAt.moovSampleTable.sampleCount) && (!trackBundleValueAt.currentlyInFragment || trackBundleValueAt.currentTrackRunIndex != trackBundleValueAt.fragment.trunCount)) {
                long currentSampleOffset = trackBundleValueAt.getCurrentSampleOffset();
                if (currentSampleOffset < j10) {
                    trackBundle = trackBundleValueAt;
                    j10 = currentSampleOffset;
                }
            }
        }
        return trackBundle;
    }

    private void initExtraTracks() {
        int i10;
        TrackOutput[] trackOutputArr = new TrackOutput[2];
        this.emsgTrackOutputs = trackOutputArr;
        TrackOutput trackOutput = this.additionalEmsgTrackOutput;
        int i11 = 0;
        if (trackOutput != null) {
            trackOutputArr[0] = trackOutput;
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i12 = 100;
        if ((this.flags & 4) != 0) {
            trackOutputArr[i10] = this.extractorOutput.track(100, 5);
            i12 = 101;
            i10++;
        }
        TrackOutput[] trackOutputArr2 = (TrackOutput[]) Util.nullSafeArrayCopy(this.emsgTrackOutputs, i10);
        this.emsgTrackOutputs = trackOutputArr2;
        for (TrackOutput trackOutput2 : trackOutputArr2) {
            trackOutput2.format(EMSG_FORMAT);
        }
        this.ceaTrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
        while (i11 < this.ceaTrackOutputs.length) {
            TrackOutput trackOutputTrack = this.extractorOutput.track(i12, 3);
            trackOutputTrack.format(this.closedCaptionFormats.get(i11));
            this.ceaTrackOutputs[i11] = trackOutputTrack;
            i11++;
            i12++;
        }
    }

    private static boolean isEdtsListDurationForEntireMediaTimeline(Track track) {
        long[] jArr = track.editListDurations;
        if (jArr != null && jArr.length == 1 && track.editListMediaTimes != null) {
            long j10 = jArr[0];
            if (j10 == 0 || Util.scaleLargeTimestamp(j10, 1000000L, track.movieTimescale) + Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000000L, track.timescale) >= track.durationUs) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(long j10, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j10, parsableByteArray, this.ceaTrackOutputs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new FragmentedMp4Extractor(factory)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new FragmentedMp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 32)};
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new androidx.media3.extractor.mkv.a(factory, 1);
    }

    private void onContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        int i10 = containerBox.type;
        if (i10 == 1836019574) {
            onMoovContainerAtomRead(containerBox);
        } else if (i10 == 1836019558) {
            onMoofContainerAtomRead(containerBox);
        } else {
            if (this.containerAtoms.isEmpty()) {
                return;
            }
            this.containerAtoms.peek().add(containerBox);
        }
    }

    private void onEmsgLeafAtomRead(ParsableByteArray parsableByteArray) {
        String nullTerminatedString;
        String nullTerminatedString2;
        long jScaleLargeTimestamp;
        long jScaleLargeTimestamp2;
        long unsignedInt;
        long jAdjustSampleTimestamp;
        if (this.emsgTrackOutputs.length == 0) {
            return;
        }
        parsableByteArray.setPosition(8);
        int fullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
        if (fullBoxVersion == 0) {
            nullTerminatedString = parsableByteArray.readNullTerminatedString();
            nullTerminatedString.getClass();
            nullTerminatedString2 = parsableByteArray.readNullTerminatedString();
            nullTerminatedString2.getClass();
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000000L, unsignedInt2);
            long j10 = this.segmentIndexEarliestPresentationTimeUs;
            long j11 = j10 != C.TIME_UNSET ? j10 + jScaleLargeTimestamp : -9223372036854775807L;
            jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000L, unsignedInt2);
            unsignedInt = parsableByteArray.readUnsignedInt();
            jAdjustSampleTimestamp = j11;
        } else {
            if (fullBoxVersion != 1) {
                a2.s(fullBoxVersion, "Skipping unsupported emsg version: ", TAG);
                return;
            }
            long unsignedInt3 = parsableByteArray.readUnsignedInt();
            jAdjustSampleTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedLongToLong(), 1000000L, unsignedInt3);
            long jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000L, unsignedInt3);
            long unsignedInt4 = parsableByteArray.readUnsignedInt();
            nullTerminatedString = parsableByteArray.readNullTerminatedString();
            nullTerminatedString.getClass();
            nullTerminatedString2 = parsableByteArray.readNullTerminatedString();
            nullTerminatedString2.getClass();
            jScaleLargeTimestamp2 = jScaleLargeTimestamp3;
            unsignedInt = unsignedInt4;
            jScaleLargeTimestamp = -9223372036854775807L;
        }
        String str = nullTerminatedString;
        String str2 = nullTerminatedString2;
        byte[] bArr = new byte[parsableByteArray.bytesLeft()];
        parsableByteArray.readBytes(bArr, 0, parsableByteArray.bytesLeft());
        ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.eventMessageEncoder.encode(new EventMessage(str, str2, jScaleLargeTimestamp2, unsignedInt, bArr)));
        int iBytesLeft = parsableByteArray2.bytesLeft();
        for (TrackOutput trackOutput : this.emsgTrackOutputs) {
            parsableByteArray2.setPosition(0);
            trackOutput.sampleData(parsableByteArray2, iBytesLeft);
        }
        if (jAdjustSampleTimestamp == C.TIME_UNSET) {
            this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(jScaleLargeTimestamp, true, iBytesLeft));
            this.pendingMetadataSampleBytes += iBytesLeft;
            return;
        }
        if (!this.pendingMetadataSampleInfos.isEmpty()) {
            this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(jAdjustSampleTimestamp, false, iBytesLeft));
            this.pendingMetadataSampleBytes += iBytesLeft;
            return;
        }
        TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
        if (timestampAdjuster != null && !timestampAdjuster.isInitialized()) {
            this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(jAdjustSampleTimestamp, false, iBytesLeft));
            this.pendingMetadataSampleBytes += iBytesLeft;
            return;
        }
        TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
        if (timestampAdjuster2 != null) {
            jAdjustSampleTimestamp = timestampAdjuster2.adjustSampleTimestamp(jAdjustSampleTimestamp);
        }
        long j12 = jAdjustSampleTimestamp;
        for (TrackOutput trackOutput2 : this.emsgTrackOutputs) {
            trackOutput2.sampleMetadata(j12, 1, iBytesLeft, 0, null);
        }
    }

    private void onLeafAtomRead(Mp4Box.LeafBox leafBox, ExtractorInput extractorInput) throws IOException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafBox);
            return;
        }
        int i10 = leafBox.type;
        if (i10 != 1936286840) {
            if (i10 == 1701671783) {
                onEmsgLeafAtomRead(leafBox.data);
                return;
            }
            return;
        }
        Pair<Long, ChunkIndex> sidx = parseSidx(leafBox.data, extractorInput.getPosition());
        this.chunkIndexMerger.add((ChunkIndex) sidx.second);
        if (!this.haveOutputSeekMap) {
            this.segmentIndexEarliestPresentationTimeUs = ((Long) sidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) sidx.second);
            this.haveOutputSeekMap = true;
        } else {
            if ((this.flags & 256) == 0 || this.haveOutputSeekMapFromMultipleSidx || this.chunkIndexMerger.size() <= 1) {
                return;
            }
            this.seekPositionBeforeSidxProcessing = extractorInput.getPosition();
        }
    }

    private void onMoofContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        parseMoof(containerBox, this.trackBundles, this.sideloadedTrack != null, this.flags, this.scratchBytes);
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i10 = 0; i10 < size; i10++) {
                this.trackBundles.valueAt(i10).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
        if (this.pendingSeekTimeUs != C.TIME_UNSET) {
            int size2 = this.trackBundles.size();
            for (int i11 = 0; i11 < size2; i11++) {
                this.trackBundles.valueAt(i11).seek(this.pendingSeekTimeUs);
            }
            this.pendingSeekTimeUs = C.TIME_UNSET;
        }
    }

    private void onMoovContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        long j10;
        boolean z;
        char c10 = 0;
        char c11 = 1;
        ac.b.t(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox.leafChildren);
        Mp4Box.ContainerBox containerBoxOfType = containerBox.getContainerBoxOfType(Mp4Box.TYPE_mvex);
        containerBoxOfType.getClass();
        SparseArray<DefaultSampleValues> sparseArray = new SparseArray<>();
        int size = containerBoxOfType.leafChildren.size();
        long mehd = C.TIME_UNSET;
        for (int i10 = 0; i10 < size; i10++) {
            Mp4Box.LeafBox leafBox = containerBoxOfType.leafChildren.get(i10);
            int i11 = leafBox.type;
            if (i11 == 1953654136) {
                Pair<Integer, DefaultSampleValues> trex = parseTrex(leafBox.data);
                sparseArray.put(((Integer) trex.first).intValue(), (DefaultSampleValues) trex.second);
            } else if (i11 == 1835362404) {
                mehd = parseMehd(leafBox.data);
            }
        }
        Mp4Box.ContainerBox containerBoxOfType2 = containerBox.getContainerBoxOfType(Mp4Box.TYPE_meta);
        Metadata udta = null;
        Metadata mdtaFromMeta = containerBoxOfType2 != null ? BoxParser.parseMdtaFromMeta(containerBoxOfType2) : null;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_udta);
        if (leafBoxOfType != null) {
            udta = BoxParser.parseUdta(leafBoxOfType);
            gaplessInfoHolder.setFromMetadata(udta);
        }
        Metadata metadata = udta;
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd);
        leafBoxOfType2.getClass();
        Metadata metadata2 = new Metadata(BoxParser.parseMvhd(leafBoxOfType2.data));
        if ((this.flags & 16) != 0) {
            j10 = mehd;
            z = true;
        } else {
            j10 = mehd;
            z = false;
        }
        List<TrackSampleTable> traks = BoxParser.parseTraks(containerBox, gaplessInfoHolder, j10, drmInitDataFromAtoms, z, false, new m() { // from class: androidx.media3.extractor.mp4.a
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                return this.f3898a.modifyTrack((Track) obj);
            }
        }, false);
        int size2 = traks.size();
        if (this.trackBundles.size() != 0) {
            ac.b.s(this.trackBundles.size() == size2);
            for (int i12 = 0; i12 < size2; i12++) {
                TrackSampleTable trackSampleTable = traks.get(i12);
                Track track = trackSampleTable.track;
                this.trackBundles.get(track.id).reset(trackSampleTable, getDefaultSampleValues(sparseArray, track.id));
            }
            return;
        }
        String containerMimeType = MimeTypeResolver.getContainerMimeType(traks);
        int i13 = 0;
        while (i13 < size2) {
            TrackSampleTable trackSampleTable2 = traks.get(i13);
            Track track2 = trackSampleTable2.track;
            TrackOutput trackOutputTrack = this.extractorOutput.track(i13, track2.type);
            char c12 = c10;
            char c13 = c11;
            trackOutputTrack.durationUs(track2.durationUs);
            Format.Builder builderBuildUpon = track2.format.buildUpon();
            builderBuildUpon.setContainerMimeType(containerMimeType);
            MetadataUtil.setFormatGaplessInfo(track2.type, gaplessInfoHolder, builderBuildUpon);
            int i14 = track2.type;
            Metadata metadata3 = track2.format.metadata;
            GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
            Metadata[] metadataArr = new Metadata[2];
            metadataArr[c12] = metadata;
            metadataArr[c13] = metadata2;
            MetadataUtil.setFormatMetadata(i14, mdtaFromMeta, builderBuildUpon, metadata3, metadataArr);
            this.trackBundles.put(track2.id, new TrackBundle(trackOutputTrack, trackSampleTable2, getDefaultSampleValues(sparseArray, track2.id), builderBuildUpon.build()));
            this.durationUs = Math.max(this.durationUs, track2.durationUs);
            i13++;
            c10 = c12;
            c11 = c13;
            gaplessInfoHolder = gaplessInfoHolder2;
        }
        this.extractorOutput.endTracks();
    }

    private void outputPendingMetadataSamples(long j10) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo metadataSampleInfoRemoveFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= metadataSampleInfoRemoveFirst.size;
            long jAdjustSampleTimestamp = metadataSampleInfoRemoveFirst.sampleTimeUs;
            if (metadataSampleInfoRemoveFirst.sampleTimeIsRelative) {
                jAdjustSampleTimestamp += j10;
            }
            TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
            if (timestampAdjuster != null) {
                jAdjustSampleTimestamp = timestampAdjuster.adjustSampleTimestamp(jAdjustSampleTimestamp);
            }
            long j11 = jAdjustSampleTimestamp;
            for (TrackOutput trackOutput : this.emsgTrackOutputs) {
                trackOutput.sampleMetadata(j11, 1, metadataSampleInfoRemoveFirst.size, this.pendingMetadataSampleBytes, null);
            }
        }
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i10, byte[] bArr) throws ParserException {
        int size = containerBox.containerChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i11);
            if (containerBox2.type == 1953653094) {
                parseTraf(containerBox2, sparseArray, z, i10, bArr);
            }
        }
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int i10 = parsableByteArray.readInt();
        if ((BoxParser.parseFullBoxFlags(i10) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt == 1) {
            trackFragment.auxiliaryDataPosition += BoxParser.parseFullBoxVersion(i10) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
        } else {
            throw ParserException.createForMalformedContainer("Unexpected saio entry count: " + unsignedIntToInt, null);
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i10;
        int i11 = trackEncryptionBox.perSampleIvSize;
        parsableByteArray.setPosition(8);
        if ((BoxParser.parseFullBoxFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt > trackFragment.sampleCount) {
            StringBuilder sbS = c.s(unsignedIntToInt, "Saiz sample count ", " is greater than fragment sample count");
            sbS.append(trackFragment.sampleCount);
            throw ParserException.createForMalformedContainer(sbS.toString(), null);
        }
        if (unsignedByte == 0) {
            boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
            i10 = 0;
            for (int i12 = 0; i12 < unsignedIntToInt; i12++) {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                i10 += unsignedByte2;
                zArr[i12] = unsignedByte2 > i11;
            }
        } else {
            i10 = unsignedByte * unsignedIntToInt;
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, unsignedByte > i11);
        }
        Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, unsignedIntToInt, trackFragment.sampleCount, false);
        if (i10 > 0) {
            trackFragment.initEncryptionData(i10);
        }
    }

    private static void parseSampleGroups(Mp4Box.ContainerBox containerBox, String str, TrackFragment trackFragment) throws ParserException {
        byte[] bArr = null;
        ParsableByteArray parsableByteArray = null;
        ParsableByteArray parsableByteArray2 = null;
        for (int i10 = 0; i10 < containerBox.leafChildren.size(); i10++) {
            Mp4Box.LeafBox leafBox = containerBox.leafChildren.get(i10);
            ParsableByteArray parsableByteArray3 = leafBox.data;
            int i11 = leafBox.type;
            if (i11 == 1935828848) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray = parsableByteArray3;
                }
            } else if (i11 == 1936158820) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray2 = parsableByteArray3;
                }
            }
        }
        if (parsableByteArray == null || parsableByteArray2 == null) {
            return;
        }
        parsableByteArray.setPosition(8);
        int fullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        if (fullBoxVersion == 1) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.readInt() != 1) {
            throw ParserException.createForUnsupportedContainerFeature("Entry count in sbgp != 1 (unsupported).");
        }
        parsableByteArray2.setPosition(8);
        int fullBoxVersion2 = BoxParser.parseFullBoxVersion(parsableByteArray2.readInt());
        parsableByteArray2.skipBytes(4);
        if (fullBoxVersion2 == 1) {
            if (parsableByteArray2.readUnsignedInt() == 0) {
                throw ParserException.createForUnsupportedContainerFeature("Variable length description in sgpd found (unsupported)");
            }
        } else if (fullBoxVersion2 >= 2) {
            parsableByteArray2.skipBytes(4);
        }
        if (parsableByteArray2.readUnsignedInt() != 1) {
            throw ParserException.createForUnsupportedContainerFeature("Entry count in sgpd != 1 (unsupported).");
        }
        parsableByteArray2.skipBytes(1);
        int unsignedByte = parsableByteArray2.readUnsignedByte();
        int i12 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        int i13 = unsignedByte & 15;
        boolean z = parsableByteArray2.readUnsignedByte() == 1;
        if (z) {
            int unsignedByte2 = parsableByteArray2.readUnsignedByte();
            byte[] bArr2 = new byte[16];
            parsableByteArray2.readBytes(bArr2, 0, 16);
            if (unsignedByte2 == 0) {
                int unsignedByte3 = parsableByteArray2.readUnsignedByte();
                bArr = new byte[unsignedByte3];
                parsableByteArray2.readBytes(bArr, 0, unsignedByte3);
            }
            trackFragment.definesEncryptionData = true;
            trackFragment.trackEncryptionBox = new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i12, i13, bArr);
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j10) throws ParserException {
        long unsignedLongToLong;
        long unsignedLongToLong2;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        if (fullBoxVersion == 0) {
            unsignedLongToLong = parsableByteArray.readUnsignedInt();
            unsignedLongToLong2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            unsignedLongToLong2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j11 = unsignedLongToLong2 + j10;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedLongToLong, 1000000L, unsignedInt);
        parsableByteArray.skipBytes(2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[unsignedShort];
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        long[] jArr3 = new long[unsignedShort];
        long j12 = j11;
        long jScaleLargeTimestamp2 = jScaleLargeTimestamp;
        int i10 = 0;
        while (i10 < unsignedShort) {
            int i11 = parsableByteArray.readInt();
            if ((Integer.MIN_VALUE & i11) != 0) {
                throw ParserException.createForMalformedContainer("Unhandled indirect reference", null);
            }
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            iArr[i10] = i11 & Integer.MAX_VALUE;
            jArr[i10] = j12;
            jArr3[i10] = jScaleLargeTimestamp2;
            unsignedLongToLong += unsignedInt2;
            long[] jArr4 = jArr3;
            jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(unsignedLongToLong, 1000000L, unsignedInt);
            jArr2[i10] = jScaleLargeTimestamp2 - jArr4[i10];
            parsableByteArray.skipBytes(4);
            j12 += (long) iArr[i10];
            i10++;
            jArr3 = jArr4;
        }
        return Pair.create(Long.valueOf(jScaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static TrackBundle parseTfhd(ParsableByteArray parsableByteArray, SparseArray<TrackBundle> sparseArray, boolean z) {
        parsableByteArray.setPosition(8);
        int fullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        TrackBundle trackBundleValueAt = z ? sparseArray.valueAt(0) : sparseArray.get(parsableByteArray.readInt());
        if (trackBundleValueAt == null) {
            return null;
        }
        if ((fullBoxFlags & 1) != 0) {
            long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            TrackFragment trackFragment = trackBundleValueAt.fragment;
            trackFragment.dataPosition = unsignedLongToLong;
            trackFragment.auxiliaryDataPosition = unsignedLongToLong;
        }
        DefaultSampleValues defaultSampleValues = trackBundleValueAt.defaultSampleValues;
        trackBundleValueAt.fragment.header = new DefaultSampleValues((fullBoxFlags & 2) != 0 ? parsableByteArray.readInt() - 1 : defaultSampleValues.sampleDescriptionIndex, (fullBoxFlags & 8) != 0 ? parsableByteArray.readInt() : defaultSampleValues.duration, (fullBoxFlags & 16) != 0 ? parsableByteArray.readInt() : defaultSampleValues.size, (fullBoxFlags & 32) != 0 ? parsableByteArray.readInt() : defaultSampleValues.flags);
        return trackBundleValueAt;
    }

    private static void parseTraf(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i10, byte[] bArr) throws ParserException {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_tfhd);
        leafBoxOfType.getClass();
        TrackBundle tfhd = parseTfhd(leafBoxOfType.data, sparseArray, z);
        if (tfhd == null) {
            return;
        }
        TrackFragment trackFragment = tfhd.fragment;
        long j10 = trackFragment.nextFragmentDecodeTime;
        boolean z5 = trackFragment.nextFragmentDecodeTimeIncludesMoov;
        tfhd.resetFragmentInfo();
        tfhd.currentlyInFragment = true;
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_tfdt);
        if (leafBoxOfType2 == null || (i10 & 2) != 0) {
            trackFragment.nextFragmentDecodeTime = j10;
            trackFragment.nextFragmentDecodeTimeIncludesMoov = z5;
        } else {
            trackFragment.nextFragmentDecodeTime = parseTfdt(leafBoxOfType2.data);
            trackFragment.nextFragmentDecodeTimeIncludesMoov = true;
        }
        parseTruns(containerBox, tfhd, i10);
        Track track = tfhd.moovSampleTable.track;
        DefaultSampleValues defaultSampleValues = trackFragment.header;
        defaultSampleValues.getClass();
        TrackEncryptionBox sampleDescriptionEncryptionBox = track.getSampleDescriptionEncryptionBox(defaultSampleValues.sampleDescriptionIndex);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_saiz);
        if (leafBoxOfType3 != null) {
            sampleDescriptionEncryptionBox.getClass();
            parseSaiz(sampleDescriptionEncryptionBox, leafBoxOfType3.data, trackFragment);
        }
        Mp4Box.LeafBox leafBoxOfType4 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_saio);
        if (leafBoxOfType4 != null) {
            parseSaio(leafBoxOfType4.data, trackFragment);
        }
        Mp4Box.LeafBox leafBoxOfType5 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_senc);
        if (leafBoxOfType5 != null) {
            parseSenc(leafBoxOfType5.data, trackFragment);
        }
        parseSampleGroups(containerBox, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
        int size = containerBox.leafChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            Mp4Box.LeafBox leafBox = containerBox.leafChildren.get(i11);
            if (leafBox.type == 1970628964) {
                parseUuid(leafBox.data, trackFragment, bArr);
            }
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readInt() - 1, parsableByteArray.readInt(), parsableByteArray.readInt(), parsableByteArray.readInt()));
    }

    private static int parseTrun(TrackBundle trackBundle, int i10, int i11, ParsableByteArray parsableByteArray, int i12) throws ParserException {
        boolean z;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        parsableByteArray.setPosition(8);
        int fullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        Track track = trackBundle.moovSampleTable.track;
        TrackFragment trackFragment = trackBundle.fragment;
        DefaultSampleValues defaultSampleValues = (DefaultSampleValues) Util.castNonNull(trackFragment.header);
        trackFragment.trunLength[i10] = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = trackFragment.trunDataPosition;
        long j10 = trackFragment.dataPosition;
        jArr[i10] = j10;
        if ((fullBoxFlags & 1) != 0) {
            jArr[i10] = j10 + ((long) parsableByteArray.readInt());
        }
        boolean z5 = (fullBoxFlags & 4) != 0;
        int i20 = defaultSampleValues.flags;
        if (z5) {
            i20 = parsableByteArray.readInt();
        }
        boolean z10 = (fullBoxFlags & 256) != 0;
        boolean z11 = (fullBoxFlags & 512) != 0;
        boolean z12 = (fullBoxFlags & 1024) != 0;
        boolean z13 = (fullBoxFlags & 2048) != 0;
        long j11 = isEdtsListDurationForEntireMediaTimeline(track) ? ((long[]) Util.castNonNull(track.editListMediaTimes))[0] : 0L;
        int[] iArr = trackFragment.sampleSizeTable;
        long[] jArr2 = trackFragment.samplePresentationTimesUs;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        boolean z14 = z13;
        boolean z15 = track.type == 2 && (i11 & 1) != 0;
        int i21 = i12 + trackFragment.trunLength[i10];
        boolean z16 = z5;
        long j12 = track.timescale;
        long j13 = trackFragment.nextFragmentDecodeTime;
        int i22 = i12;
        while (i22 < i21) {
            if (z10) {
                i13 = parsableByteArray.readInt();
                z = z15;
            } else {
                z = z15;
                i13 = defaultSampleValues.duration;
            }
            int iCheckNonNegative = checkNonNegative(i13);
            if (z11) {
                i15 = parsableByteArray.readInt();
                i14 = i21;
            } else {
                i14 = i21;
                i15 = defaultSampleValues.size;
            }
            int iCheckNonNegative2 = checkNonNegative(i15);
            if (z12) {
                i16 = iCheckNonNegative2;
                i17 = parsableByteArray.readInt();
            } else if (i22 == 0 && z16) {
                i16 = iCheckNonNegative2;
                i17 = i20;
            } else {
                i16 = iCheckNonNegative2;
                i17 = defaultSampleValues.flags;
            }
            if (z14) {
                i18 = i17;
                i19 = parsableByteArray.readInt();
            } else {
                i18 = i17;
                i19 = 0;
            }
            int i23 = i22;
            long jScaleLargeTimestamp = Util.scaleLargeTimestamp((((long) i19) + j13) - j11, 1000000L, j12);
            jArr2[i23] = jScaleLargeTimestamp;
            if (!trackFragment.nextFragmentDecodeTimeIncludesMoov) {
                jArr2[i23] = jScaleLargeTimestamp + trackBundle.moovSampleTable.durationUs;
            }
            iArr[i23] = i16;
            zArr[i23] = ((i18 >> 16) & 1) == 0 && (!z || i23 == 0);
            j13 += (long) iCheckNonNegative;
            i22 = i23 + 1;
            i21 = i14;
            z15 = z;
        }
        int i24 = i21;
        trackFragment.nextFragmentDecodeTime = j13;
        return i24;
    }

    private static void parseTruns(Mp4Box.ContainerBox containerBox, TrackBundle trackBundle, int i10) throws ParserException {
        List<Mp4Box.LeafBox> list = containerBox.leafChildren;
        int size = list.size();
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < size; i13++) {
            Mp4Box.LeafBox leafBox = list.get(i13);
            if (leafBox.type == 1953658222) {
                ParsableByteArray parsableByteArray = leafBox.data;
                parsableByteArray.setPosition(12);
                int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (unsignedIntToInt > 0) {
                    i12 += unsignedIntToInt;
                    i11++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i11, i12);
        int i14 = 0;
        int trun = 0;
        for (int i15 = 0; i15 < size; i15++) {
            Mp4Box.LeafBox leafBox2 = list.get(i15);
            if (leafBox2.type == 1953658222) {
                trun = parseTrun(trackBundle, i14, i10, leafBox2.data, trun);
                i14++;
            }
        }
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private void processAtomEnded(long j10) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j10) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j10 = this.atomSize;
        if (j10 == 1) {
            extractorInput.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j10 == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        long j11 = this.atomSize;
        int i10 = this.atomHeaderBytesRead;
        if (j11 < i10) {
            if (this.atomType != 1718773093 || i10 != 8) {
                throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
            }
            this.atomSize = i10;
        }
        if (this.seekPositionBeforeSidxProcessing != -1) {
            if (this.atomType == 1936286840) {
                this.scratch.reset((int) this.atomSize);
                System.arraycopy(this.atomHeader.getData(), 0, this.scratch.getData(), 0, 8);
                extractorInput.readFully(this.scratch.getData(), 8, (int) (this.atomSize - ((long) this.atomHeaderBytesRead)));
                this.chunkIndexMerger.add((ChunkIndex) parseSidx(new Mp4Box.LeafBox(Mp4Box.TYPE_sidx, this.scratch).data, extractorInput.getPeekPosition()).second);
            } else {
                extractorInput.skipFully((int) (this.atomSize - ((long) i10)), true);
            }
            enterReadingAtomHeaderState();
            return true;
        }
        long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
        int i11 = this.atomType;
        if ((i11 == 1836019558 || i11 == 1835295092) && !this.haveOutputSeekMap) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, position));
            this.haveOutputSeekMap = true;
        }
        if (this.atomType == 1836019558) {
            int size = this.trackBundles.size();
            for (int i12 = 0; i12 < size; i12++) {
                TrackFragment trackFragment = this.trackBundles.valueAt(i12).fragment;
                trackFragment.atomPosition = position;
                trackFragment.auxiliaryDataPosition = position;
                trackFragment.dataPosition = position;
            }
        }
        int i13 = this.atomType;
        if (i13 == 1835295092) {
            this.currentTrackBundle = null;
            this.endOfMdatPosition = position + this.atomSize;
            this.parserState = 2;
            return true;
        }
        if (shouldParseContainerAtom(i13)) {
            long position2 = extractorInput.getPosition();
            long j12 = this.atomSize;
            long j13 = (position2 + j12) - 8;
            if (j12 != this.atomHeaderBytesRead && this.atomType == 1835365473) {
                maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
            }
            this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, j13));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(j13);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            if (this.atomHeaderBytesRead != 8) {
                throw ParserException.createForUnsupportedContainerFeature("Leaf atom defines extended atom size (unsupported).");
            }
            if (this.atomSize > 2147483647L) {
                throw ParserException.createForUnsupportedContainerFeature("Leaf atom with length > 2147483647 (unsupported).");
            }
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
            System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
            this.atomData = parsableByteArray;
            this.parserState = 1;
        } else {
            if (this.atomSize > 2147483647L) {
                throw ParserException.createForUnsupportedContainerFeature("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws IOException {
        int i10 = (int) (this.atomSize - ((long) this.atomHeaderBytesRead));
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.getData(), 8, i10);
            onLeafAtomRead(new Mp4Box.LeafBox(this.atomType, parsableByteArray), extractorInput);
        } else {
            extractorInput.skipFully(i10);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws IOException {
        int size = this.trackBundles.size();
        long j10 = Long.MAX_VALUE;
        TrackBundle trackBundleValueAt = null;
        for (int i10 = 0; i10 < size; i10++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i10).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j11 = trackFragment.auxiliaryDataPosition;
                if (j11 < j10) {
                    trackBundleValueAt = this.trackBundles.valueAt(i10);
                    j10 = j11;
                }
            }
        }
        if (trackBundleValueAt == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j10 - extractorInput.getPosition());
        if (position < 0) {
            throw ParserException.createForMalformedContainer("Offset to encryption data was negative.", null);
        }
        extractorInput.skipFully(position);
        trackBundleValueAt.fragment.fillEncryptionData(extractorInput);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean readSample(androidx.media3.extractor.ExtractorInput r17) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.FragmentedMp4Extractor.readSample(androidx.media3.extractor.ExtractorInput):boolean");
    }

    private static boolean shouldParseContainerAtom(int i10) {
        return i10 == 1836019574 || i10 == 1953653099 || i10 == 1835297121 || i10 == 1835626086 || i10 == 1937007212 || i10 == 1836019558 || i10 == 1953653094 || i10 == 1836475768 || i10 == 1701082227 || i10 == 1835365473;
    }

    private static boolean shouldParseLeafAtom(int i10) {
        return i10 == 1751411826 || i10 == 1835296868 || i10 == 1836476516 || i10 == 1936286840 || i10 == 1937011556 || i10 == 1937011827 || i10 == 1668576371 || i10 == 1937011555 || i10 == 1937011578 || i10 == 1937013298 || i10 == 1937007471 || i10 == 1668232756 || i10 == 1937011571 || i10 == 1952867444 || i10 == 1952868452 || i10 == 1953196132 || i10 == 1953654136 || i10 == 1953658222 || i10 == 1886614376 || i10 == 1935763834 || i10 == 1935763823 || i10 == 1936027235 || i10 == 1970628964 || i10 == 1935828848 || i10 == 1936158820 || i10 == 1701606260 || i10 == 1835362404 || i10 == 1701671783 || i10 == 1969517665 || i10 == 1801812339 || i10 == 1768715124;
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return androidx.media3.extractor.b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = (this.flags & 32) == 0 ? new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory) : extractorOutput;
        enterReadingAtomHeaderState();
        initExtraTracks();
        Track track = this.sideloadedTrack;
        if (track != null) {
            Format.Builder builderBuildUpon = track.format.buildUpon();
            builderBuildUpon.setContainerMimeType(MimeTypeResolver.getContainerMimeType(this.sideloadedTrack.format));
            this.trackBundles.put(0, new TrackBundle(this.extractorOutput.track(0, this.sideloadedTrack.type), new TrackSampleTable(this.sideloadedTrack, new long[0], new int[0], 0, new long[0], new int[0], new int[0], false, 0L, 0), new DefaultSampleValues(0, 0, 0, 0), builderBuildUpon.build()));
            this.extractorOutput.endTracks();
        }
    }

    public Track modifyTrack(Track track) {
        return track;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i10 = this.parserState;
            if (i10 != 0) {
                if (i10 == 1) {
                    readAtomPayload(extractorInput);
                } else if (i10 == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                long j10 = this.seekPositionBeforeSidxProcessing;
                if (j10 == -1) {
                    this.reorderingBufferQueue.flush();
                    return -1;
                }
                positionHolder.position = j10;
                this.seekPositionBeforeSidxProcessing = -1L;
                this.extractorOutput.seekMap(this.chunkIndexMerger.merge());
                this.haveOutputSeekMapFromMultipleSidx = true;
                return 1;
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        int size = this.trackBundles.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.trackBundles.valueAt(i10).resetFragmentInfo();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.reorderingBufferQueue.clear();
        this.pendingSeekTimeUs = j11;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        o3 o3VarS;
        SniffFailure sniffFailureSniffFragmented = Sniffer.sniffFragmented(extractorInput);
        if (sniffFailureSniffFragmented != null) {
            o3VarS = h1.s(sniffFailureSniffFragmented);
        } else {
            f1 f1Var = h1.f14020l;
            o3VarS = o3.f14078o;
        }
        this.lastSniffFailures = o3VarS;
        return sniffFailureSniffFragmented == null;
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i10, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i10 + 8);
        int fullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        if ((fullBoxFlags & 1) != 0) {
            throw ParserException.createForUnsupportedContainerFeature("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (fullBoxFlags & 2) != 0;
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt == 0) {
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, trackFragment.sampleCount, false);
            return;
        }
        if (unsignedIntToInt != trackFragment.sampleCount) {
            StringBuilder sbS = c.s(unsignedIntToInt, "Senc sample count ", " is different from fragment sample count");
            sbS.append(trackFragment.sampleCount);
            throw ParserException.createForMalformedContainer(sbS.toString(), null);
        }
        Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, z);
        trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
        trackFragment.fillEncryptionData(parsableByteArray);
    }

    @Override // androidx.media3.extractor.Extractor
    public h1 getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FragmentedMp4Extractor(SubtitleParser.Factory factory) {
        this(factory, 0, null, null, o3.f14078o, null);
        f1 f1Var = h1.f14020l;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public FragmentedMp4Extractor(int i10) {
        SubtitleParser.Factory factory = SubtitleParser.Factory.UNSUPPORTED;
        int i11 = i10 | 32;
        f1 f1Var = h1.f14020l;
        this(factory, i11, null, null, o3.f14078o, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i10) {
        this(factory, i10, null, null, o3.f14078o, null);
        f1 f1Var = h1.f14020l;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public FragmentedMp4Extractor(int i10, TimestampAdjuster timestampAdjuster) {
        SubtitleParser.Factory factory = SubtitleParser.Factory.UNSUPPORTED;
        int i11 = i10 | 32;
        f1 f1Var = h1.f14020l;
        this(factory, i11, timestampAdjuster, null, o3.f14078o, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public FragmentedMp4Extractor(int i10, TimestampAdjuster timestampAdjuster, Track track) {
        SubtitleParser.Factory factory = SubtitleParser.Factory.UNSUPPORTED;
        int i11 = i10 | 32;
        f1 f1Var = h1.f14020l;
        this(factory, i11, timestampAdjuster, track, o3.f14078o, null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i10, TimestampAdjuster timestampAdjuster, Track track, List<Format> list) {
        this(SubtitleParser.Factory.UNSUPPORTED, i10 | 32, timestampAdjuster, track, list, null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i10, TimestampAdjuster timestampAdjuster, Track track, List<Format> list, TrackOutput trackOutput) {
        this(SubtitleParser.Factory.UNSUPPORTED, i10 | 32, timestampAdjuster, track, list, trackOutput);
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i10, TimestampAdjuster timestampAdjuster, Track track, List<Format> list, TrackOutput trackOutput) {
        this.subtitleParserFactory = factory;
        this.flags = i10;
        this.timestampAdjuster = timestampAdjuster;
        this.sideloadedTrack = track;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.nalUnitWithoutHeaderBuffer = new ParsableByteArray();
        byte[] bArr = new byte[16];
        this.scratchBytes = bArr;
        this.scratch = new ParsableByteArray(bArr);
        this.containerAtoms = new ArrayDeque<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        f1 f1Var = h1.f14020l;
        this.lastSniffFailures = o3.f14078o;
        this.durationUs = C.TIME_UNSET;
        this.pendingSeekTimeUs = C.TIME_UNSET;
        this.segmentIndexEarliestPresentationTimeUs = C.TIME_UNSET;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.emsgTrackOutputs = new TrackOutput[0];
        this.ceaTrackOutputs = new TrackOutput[0];
        this.reorderingBufferQueue = new ReorderingBufferQueue(new a3.b(this, 8));
        this.chunkIndexMerger = new ChunkIndexMerger();
        this.seekPositionBeforeSidxProcessing = -1L;
    }
}

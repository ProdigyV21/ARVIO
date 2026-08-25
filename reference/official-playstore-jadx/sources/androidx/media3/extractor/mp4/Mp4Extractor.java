package androidx.media3.extractor.mp4;

import androidx.compose.foundation.c;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.e;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackAwareSeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.d;
import androidx.media3.extractor.metadata.MotionPhotoMetadata;
import androidx.media3.extractor.metadata.ThumbnailMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
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
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class Mp4Extractor implements Extractor {

    @Deprecated
    public static final ExtractorsFactory FACTORY = new b(0);
    private static final int FILE_TYPE_HEIC = 2;
    private static final int FILE_TYPE_MP4 = 0;
    private static final int FILE_TYPE_QUICKTIME = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 16;
    public static final int FLAG_MARK_FIRST_VIDEO_TRACK_WITH_MAIN_ROLE = 8;
    public static final int FLAG_OMIT_TRACK_SAMPLE_TABLE = 256;
    public static final int FLAG_READ_AUXILIARY_TRACKS = 64;

    @Deprecated
    public static final int FLAG_READ_MOTION_PHOTO_METADATA = 2;
    public static final int FLAG_READ_SEF_DATA = 4;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES = 32;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES_H265 = 128;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL = 10000000;
    private static final int MAX_SYNC_SAMPLES_TO_SCAN_FOR_THUMBNAIL = 20;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private static final int STATE_READING_SEF = 3;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private long axteAtomOffset;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private final int flags;
    private boolean isSampleDependedOn;
    private h1 lastSniffFailures;
    private boolean moovAtomProcessed;
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private final boolean omitTrackSampleTable;
    private int parserState;
    private boolean readingAuxiliaryTracks;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private long sampleOffsetForAuxiliaryTracks;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private boolean seekToAxteAtom;
    private boolean seenFtypAtom;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private final SubtitleParser.Factory subtitleParserFactory;
    private Mp4Track[] tracks;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class Mp4SeekMap implements TrackAwareSeekMap {
        private final long durationUs;
        private final int firstVideoTrackIndex;
        private final Mp4Track[] tracks;

        public Mp4SeekMap(long j10, Mp4Track[] mp4TrackArr, int i10) {
            this.durationUs = j10;
            this.tracks = mp4TrackArr;
            this.firstVideoTrackIndex = i10;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j10) {
            return getSeekPoints(j10, -1);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x008f  */
        @Override // androidx.media3.extractor.TrackAwareSeekMap
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.media3.extractor.SeekMap.SeekPoints getSeekPoints(long r17, int r19) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                r3 = r19
                androidx.media3.extractor.mp4.Mp4Extractor$Mp4Track[] r4 = r0.tracks
                int r5 = r4.length
                if (r5 != 0) goto L13
                androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
                androidx.media3.extractor.SeekPoint r2 = androidx.media3.extractor.SeekPoint.START
                r1.<init>(r2)
                return r1
            L13:
                r5 = -1
                if (r3 == r5) goto L18
                r6 = r3
                goto L1a
            L18:
                int r6 = r0.firstVideoTrackIndex
            L1a:
                r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                r9 = -1
                if (r6 == r5) goto L58
                r4 = r4[r6]
                androidx.media3.extractor.mp4.TrackSampleTable r4 = r4.sampleTable
                int r6 = androidx.media3.extractor.mp4.Mp4Extractor.access$000(r4, r1)
                if (r6 != r5) goto L35
                androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
                androidx.media3.extractor.SeekPoint r2 = androidx.media3.extractor.SeekPoint.START
                r1.<init>(r2)
                return r1
            L35:
                long[] r11 = r4.timestampsUs
                r12 = r11[r6]
                long[] r11 = r4.offsets
                r14 = r11[r6]
                int r11 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
                if (r11 >= 0) goto L5e
                int r11 = r4.sampleCount
                int r11 = r11 + (-1)
                if (r6 >= r11) goto L5e
                int r1 = r4.getIndexOfLaterOrEqualSynchronizationSample(r1)
                if (r1 == r5) goto L5e
                if (r1 == r6) goto L5e
                long[] r2 = r4.timestampsUs
                r9 = r2[r1]
                long[] r2 = r4.offsets
                r1 = r2[r1]
                goto L60
            L58:
                r14 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r12 = r1
            L5e:
                r1 = r9
                r9 = r7
            L60:
                if (r3 != r5) goto L80
                r3 = 0
            L63:
                androidx.media3.extractor.mp4.Mp4Extractor$Mp4Track[] r4 = r0.tracks
                int r5 = r4.length
                if (r3 >= r5) goto L80
                int r5 = r0.firstVideoTrackIndex
                if (r3 == r5) goto L7d
                r4 = r4[r3]
                androidx.media3.extractor.mp4.TrackSampleTable r4 = r4.sampleTable
                long r5 = androidx.media3.extractor.mp4.Mp4Extractor.access$100(r4, r12, r14)
                int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r11 == 0) goto L7c
                long r1 = androidx.media3.extractor.mp4.Mp4Extractor.access$100(r4, r9, r1)
            L7c:
                r14 = r5
            L7d:
                int r3 = r3 + 1
                goto L63
            L80:
                androidx.media3.extractor.SeekPoint r3 = new androidx.media3.extractor.SeekPoint
                r3.<init>(r12, r14)
                int r4 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r4 != 0) goto L8f
                androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
                r1.<init>(r3)
                return r1
            L8f:
                androidx.media3.extractor.SeekPoint r4 = new androidx.media3.extractor.SeekPoint
                r4.<init>(r9, r1)
                androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
                r1.<init>(r3, r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Mp4Extractor.Mp4SeekMap.getSeekPoints(long, int):androidx.media3.extractor.SeekMap$SeekPoints");
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public boolean isSeekable(int i10) {
            return true;
        }
    }

    public static final class Mp4Track {
        public Format pendingFormat;
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track track, TrackSampleTable trackSampleTable, TrackOutput trackOutput) {
            this.track = track;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput;
            this.trueHdSampleRechunker = MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType) ? new TrueHdSampleRechunker() : null;
        }
    }

    @Deprecated
    public Mp4Extractor() {
        this(SubtitleParser.Factory.UNSUPPORTED, 16);
    }

    private static int brandToFileType(int i10) {
        if (i10 != 1751476579) {
            return i10 != 1903435808 ? 0 : 1;
        }
        return 2;
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i10 = 0; i10 < mp4TrackArr.length; i10++) {
            jArr[i10] = new long[mp4TrackArr[i10].sampleTable.sampleCount];
            jArr2[i10] = mp4TrackArr[i10].sampleTable.timestampsUs[0];
        }
        long j10 = 0;
        int i11 = 0;
        while (i11 < mp4TrackArr.length) {
            long j11 = Long.MAX_VALUE;
            int i12 = -1;
            for (int i13 = 0; i13 < mp4TrackArr.length; i13++) {
                if (!zArr[i13]) {
                    long j12 = jArr2[i13];
                    if (j12 <= j11) {
                        i12 = i13;
                        j11 = j12;
                    }
                }
            }
            int i14 = iArr[i12];
            long[] jArr3 = jArr[i12];
            jArr3[i14] = j10;
            TrackSampleTable trackSampleTable = mp4TrackArr[i12].sampleTable;
            j10 += (long) trackSampleTable.sizes[i14];
            int i15 = i14 + 1;
            iArr[i12] = i15;
            if (i15 < jArr3.length) {
                jArr2[i12] = trackSampleTable.timestampsUs[i15];
            } else {
                zArr[i12] = true;
                i11++;
            }
        }
        return jArr;
    }

    private boolean canReadWithinGopSampleDependencies(Format format) {
        return Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264) ? (this.flags & 32) != 0 : Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265) && (this.flags & 128) != 0;
    }

    public static int codecsToParseWithinGopSampleDependenciesAsFlags(int i10) {
        int i11 = (i10 & 1) != 0 ? 32 : 0;
        return (i10 & 2) != 0 ? i11 | 128 : i11;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private static long findBestThumbnailPresentationTimeUs(TrackSampleTable trackSampleTable, long j10) {
        int i10;
        if (!MimeTypes.isVideo(trackSampleTable.track.format.sampleMimeType)) {
            return C.TIME_UNSET;
        }
        int iMin = Math.min(trackSampleTable.hasOnlySyncSamples ? trackSampleTable.sampleCount : trackSampleTable.syncSampleIndices.length, 20);
        ac.b.s(j10 != C.TIME_UNSET);
        long jMin = Math.min(j10, MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL);
        int i11 = -1;
        int i12 = 0;
        for (int i13 = 0; i13 < iMin; i13++) {
            int i14 = trackSampleTable.hasOnlySyncSamples ? i13 : trackSampleTable.syncSampleIndices[i13];
            long j11 = trackSampleTable.timestampsUs[i14];
            if (j11 > jMin) {
                break;
            }
            if (j11 >= 0 && (i10 = trackSampleTable.sizes[i14]) > i12) {
                i11 = i14;
                i12 = i10;
            }
        }
        return i11 == -1 ? C.TIME_UNSET : trackSampleTable.timestampsUs[i11];
    }

    private List<Integer> getAuxiliaryTrackTypesForAuxiliaryTracks(Metadata metadata) {
        MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) metadata.getFirstMatchingEntry(MdtaMetadataEntry.class, new d(5));
        mdtaMetadataEntry.getClass();
        List<Integer> auxiliaryTrackTypesFromMap = mdtaMetadataEntry.getAuxiliaryTrackTypesFromMap();
        ArrayList arrayList = new ArrayList(auxiliaryTrackTypesFromMap.size());
        for (int i10 = 0; i10 < auxiliaryTrackTypesFromMap.size(); i10++) {
            int iIntValue = auxiliaryTrackTypesFromMap.get(i10).intValue();
            int i11 = 1;
            if (iIntValue != 0) {
                if (iIntValue != 1) {
                    i11 = 3;
                    if (iIntValue != 2) {
                        i11 = iIntValue != 3 ? 0 : 4;
                    }
                } else {
                    i11 = 2;
                }
            }
            arrayList.add(Integer.valueOf(i11));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j10) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j10);
        return indexOfEarlierOrEqualSynchronizationSample == -1 ? trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j10) : indexOfEarlierOrEqualSynchronizationSample;
    }

    private int getTrackIndexOfNextReadSample(long j10) {
        int i10 = -1;
        int i11 = -1;
        int i12 = 0;
        long j11 = Long.MAX_VALUE;
        boolean z = true;
        long j12 = Long.MAX_VALUE;
        boolean z5 = true;
        long j13 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i12 >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i12];
            int i13 = mp4Track.sampleIndex;
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            if (i13 != trackSampleTable.sampleCount) {
                long j14 = trackSampleTable.offsets[i13];
                long[][] jArr = this.accumulatedSampleSizes;
                jArr.getClass();
                long j15 = jArr[i12][i13];
                long j16 = j14 - j10;
                boolean z10 = j16 < 0 || j16 >= 262144;
                if ((!z10 && z5) || (z10 == z5 && j16 < j13)) {
                    z5 = z10;
                    j12 = j15;
                    i11 = i12;
                    j13 = j16;
                }
                if (j15 < j11) {
                    z = z10;
                    j11 = j15;
                    i10 = i12;
                }
            }
            i12++;
        }
        return (j11 == Long.MAX_VALUE || !z || j12 < j11 + MAXIMUM_READ_AHEAD_BYTES_STREAM) ? i11 : i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getAuxiliaryTrackTypesForAuxiliaryTracks$5(MdtaMetadataEntry mdtaMetadataEntry) {
        return mdtaMetadataEntry.key.equals(MdtaMetadataEntry.KEY_AUXILIARY_TRACKS_MAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$maybeSetDefaultSampleOffsetForAuxiliaryTracks$4(MdtaMetadataEntry mdtaMetadataEntry) {
        return mdtaMetadataEntry.key.equals(MdtaMetadataEntry.KEY_AUXILIARY_TRACKS_INTERLEAVED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new Mp4Extractor(factory)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Track lambda$processMoovAtom$2(Track track) {
        return track;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$shouldSeekToAxteAtom$3(MdtaMetadataEntry mdtaMetadataEntry) {
        return mdtaMetadataEntry.key.equals(MdtaMetadataEntry.KEY_AUXILIARY_TRACKS_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 16)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j10, long j11) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j10);
        return synchronizationSampleIndex == -1 ? j11 : Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j11);
    }

    private void maybeSetDefaultSampleOffsetForAuxiliaryTracks(Metadata metadata) {
        MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) metadata.getFirstMatchingEntry(MdtaMetadataEntry.class, new d(3));
        if (mdtaMetadataEntry == null || mdtaMetadataEntry.value[0] != 0) {
            return;
        }
        this.sampleOffsetForAuxiliaryTracks = this.axteAtomOffset + 16;
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new androidx.media3.extractor.mkv.a(factory, 2);
    }

    private void processAtomEnded(long j10) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j10) {
            Mp4Box.ContainerBox containerBoxPop = this.containerAtoms.pop();
            if (containerBoxPop.type == 1836019574) {
                processMoovAtom(containerBoxPop);
                this.containerAtoms.clear();
                this.moovAtomProcessed = true;
                if (!this.seekToAxteAtom && !this.omitTrackSampleTable) {
                    this.parserState = 2;
                }
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(containerBoxPop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processEndOfStreamReadingAtomHeader() {
        if (this.fileType != 2 || (this.flags & 2) == 0) {
            return;
        }
        TrackOutput trackOutputTrack = this.extractorOutput.track(0, 4);
        MotionPhotoMetadata motionPhotoMetadata = this.motionPhotoMetadata;
        trackOutputTrack.format(new Format.Builder().setMetadata(motionPhotoMetadata == null ? null : new Metadata(motionPhotoMetadata)).build());
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
    }

    private static int processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int iBrandToFileType = brandToFileType(parsableByteArray.readInt());
        if (iBrandToFileType != 0) {
            return iBrandToFileType;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            int iBrandToFileType2 = brandToFileType(parsableByteArray.readInt());
            if (iBrandToFileType2 != 0) {
                return iBrandToFileType2;
            }
        }
        return 0;
    }

    private void processMoovAtom(Mp4Box.ContainerBox containerBox) throws ParserException {
        List<Integer> list;
        Metadata metadata;
        Metadata metadata2;
        int i10;
        List<TrackSampleTable> list2;
        int i11;
        Metadata metadata3;
        boolean z;
        Mp4Box.ContainerBox containerBoxOfType = containerBox.getContainerBoxOfType(Mp4Box.TYPE_meta);
        List<Integer> arrayList = new ArrayList<>();
        boolean z5 = true;
        if (containerBoxOfType != null) {
            Metadata mdtaFromMeta = BoxParser.parseMdtaFromMeta(containerBoxOfType);
            if (this.readingAuxiliaryTracks) {
                mdtaFromMeta.getClass();
                maybeSetDefaultSampleOffsetForAuxiliaryTracks(mdtaFromMeta);
                arrayList = getAuxiliaryTrackTypesForAuxiliaryTracks(mdtaFromMeta);
            } else if (shouldSeekToAxteAtom(mdtaFromMeta)) {
                this.seekToAxteAtom = true;
                return;
            }
            metadata = mdtaFromMeta;
            list = arrayList;
        } else {
            list = arrayList;
            metadata = null;
        }
        ArrayList arrayList2 = new ArrayList();
        int i12 = 0;
        boolean z10 = this.fileType == 1;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_udta);
        if (leafBoxOfType != null) {
            Metadata udta = BoxParser.parseUdta(leafBoxOfType);
            gaplessInfoHolder.setFromMetadata(udta);
            metadata2 = udta;
        } else {
            metadata2 = null;
        }
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd);
        leafBoxOfType2.getClass();
        Metadata metadata4 = new Metadata(BoxParser.parseMvhd(leafBoxOfType2.data));
        List<TrackSampleTable> traks = BoxParser.parseTraks(containerBox, gaplessInfoHolder, C.TIME_UNSET, null, (this.flags & 1) != 0, z10, new e(18), this.omitTrackSampleTable);
        if (this.readingAuxiliaryTracks) {
            boolean z11 = list.size() == traks.size();
            Locale locale = Locale.US;
            ac.b.t(z11, c.s("The number of auxiliary track types from metadata (", list.size(), ") is not same as the number of auxiliary tracks (", traks.size(), ")"));
        }
        String containerMimeType = MimeTypeResolver.getContainerMimeType(traks);
        int i13 = 0;
        int i14 = 0;
        long j10 = C.TIME_UNSET;
        int size = -1;
        while (i13 < traks.size()) {
            TrackSampleTable trackSampleTable = traks.get(i13);
            int i15 = i12;
            if (trackSampleTable.sampleCount == 0) {
                list2 = traks;
                i10 = i14;
                z = z5;
            } else {
                Track track = trackSampleTable.track;
                i10 = i14 + 1;
                list2 = traks;
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i14, track.type));
                GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
                long j11 = track.durationUs;
                if (j11 == C.TIME_UNSET) {
                    j11 = trackSampleTable.durationUs;
                }
                mp4Track.trackOutput.durationUs(j11);
                long jMax = Math.max(j10, j11);
                int i16 = MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType) ? trackSampleTable.maximumSize * 16 : trackSampleTable.maximumSize + 30;
                Format.Builder builderBuildUpon = track.format.buildUpon();
                builderBuildUpon.setMaxInputSize(i16);
                if (track.type == 2) {
                    int i17 = track.format.roleFlags;
                    i11 = 2;
                    if ((this.flags & 8) != 0) {
                        i17 |= size == -1 ? 1 : 2;
                    }
                    if (this.readingAuxiliaryTracks) {
                        i17 |= 32768;
                        builderBuildUpon.setAuxiliaryTrackType(list.get(i13).intValue());
                    }
                    builderBuildUpon.setRoleFlags(i17);
                } else {
                    i11 = 2;
                }
                long jFindBestThumbnailPresentationTimeUs = findBestThumbnailPresentationTimeUs(trackSampleTable, j11);
                if (jFindBestThumbnailPresentationTimeUs != C.TIME_UNSET) {
                    Metadata.Entry[] entryArr = new Metadata.Entry[1];
                    entryArr[i15] = new ThumbnailMetadata(jFindBestThumbnailPresentationTimeUs);
                    metadata3 = new Metadata(entryArr);
                } else {
                    metadata3 = null;
                }
                gaplessInfoHolder = gaplessInfoHolder2;
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder, builderBuildUpon);
                int i18 = track.type;
                Metadata metadata5 = track.format.metadata;
                Metadata[] metadataArr = new Metadata[4];
                metadataArr[i15] = this.slowMotionMetadataEntries.isEmpty() ? null : new Metadata(this.slowMotionMetadataEntries);
                z = true;
                metadataArr[1] = metadata2;
                metadataArr[i11] = metadata4;
                metadataArr[3] = metadata3;
                MetadataUtil.setFormatMetadata(i18, metadata, builderBuildUpon, metadata5, metadataArr);
                builderBuildUpon.setContainerMimeType(containerMimeType);
                if (Objects.equals(track.format.sampleMimeType, MimeTypes.AUDIO_MPEG)) {
                    mp4Track.pendingFormat = builderBuildUpon.build();
                } else {
                    mp4Track.trackOutput.format(builderBuildUpon.build());
                }
                if (track.type == i11 && size == -1) {
                    size = arrayList2.size();
                }
                arrayList2.add(mp4Track);
                j10 = jMax;
            }
            i13++;
            i12 = i15;
            z5 = z;
            i14 = i10;
            traks = list2;
        }
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList2.toArray(new Mp4Track[i12]);
        this.tracks = mp4TrackArr;
        this.accumulatedSampleSizes = !this.omitTrackSampleTable ? calculateAccumulatedSampleSizes(mp4TrackArr) : null;
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new Mp4SeekMap(j10, this.tracks, size));
    }

    private void processUnparsedAtom(long j10) {
        if (this.atomType == 1836086884) {
            int i10 = this.atomHeaderBytesRead;
            this.motionPhotoMetadata = new MotionPhotoMetadata(0L, j10, C.TIME_UNSET, j10 + ((long) i10), this.atomSize - ((long) i10));
        }
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        Mp4Box.ContainerBox containerBoxPeek;
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                processEndOfStreamReadingAtomHeader();
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
            if (length == -1 && (containerBoxPeek = this.containerAtoms.peek()) != null) {
                length = containerBoxPeek.endPosition;
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
        if (shouldParseContainerAtom(this.atomType)) {
            long position = extractorInput.getPosition();
            long j12 = this.atomSize;
            int i11 = this.atomHeaderBytesRead;
            long j13 = (position + j12) - ((long) i11);
            if (j12 != i11 && this.atomType == 1835365473) {
                maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
            }
            this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, j13));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(j13);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            ac.b.s(this.atomHeaderBytesRead == 8);
            ac.b.s(this.atomSize <= 2147483647L);
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
            System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
            this.atomData = parsableByteArray;
            this.parserState = 1;
        } else {
            processUnparsedAtom(extractorInput.getPosition() - ((long) this.atomHeaderBytesRead));
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean readAtomPayload(androidx.media3.extractor.ExtractorInput r10, androidx.media3.extractor.PositionHolder r11) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.atomSize
            int r2 = r9.atomHeaderBytesRead
            long r2 = (long) r2
            long r0 = r0 - r2
            long r2 = r10.getPosition()
            long r2 = r2 + r0
            androidx.media3.common.util.ParsableByteArray r4 = r9.atomData
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L46
            byte[] r7 = r4.getData()
            int r8 = r9.atomHeaderBytesRead
            int r0 = (int) r0
            r10.readFully(r7, r8, r0)
            int r10 = r9.atomType
            r0 = 1718909296(0x66747970, float:2.8862439E23)
            if (r10 != r0) goto L2b
            r9.seenFtypAtom = r5
            int r10 = processFtypAtom(r4)
            r9.fileType = r10
            goto L5e
        L2b:
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L5e
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            java.lang.Object r10 = r10.peek()
            androidx.media3.container.Mp4Box$ContainerBox r10 = (androidx.media3.container.Mp4Box.ContainerBox) r10
            androidx.media3.container.Mp4Box$LeafBox r0 = new androidx.media3.container.Mp4Box$LeafBox
            int r1 = r9.atomType
            r0.<init>(r1, r4)
            r10.add(r0)
            goto L5e
        L46:
            boolean r4 = r9.seenFtypAtom
            if (r4 != 0) goto L53
            int r4 = r9.atomType
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            if (r4 != r7) goto L53
            r9.fileType = r5
        L53:
            r7 = 262144(0x40000, double:1.295163E-318)
            int r4 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r4 >= 0) goto L60
            int r0 = (int) r0
            r10.skipFully(r0)
        L5e:
            r10 = r6
            goto L68
        L60:
            long r7 = r10.getPosition()
            long r7 = r7 + r0
            r11.position = r7
            r10 = r5
        L68:
            r9.processAtomEnded(r2)
            boolean r0 = r9.seekToAxteAtom
            if (r0 == 0) goto L78
            r9.readingAuxiliaryTracks = r5
            long r0 = r9.axteAtomOffset
            r11.position = r0
            r9.seekToAxteAtom = r6
            r10 = r5
        L78:
            if (r10 == 0) goto L80
            int r10 = r9.parserState
            r11 = 2
            if (r10 == r11) goto L80
            return r5
        L80:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Mp4Extractor.readAtomPayload(androidx.media3.extractor.ExtractorInput, androidx.media3.extractor.PositionHolder):boolean");
    }

    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean, int] */
    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        TrackOutput.CryptoData cryptoData;
        ?? r1;
        int iNumberOfBytesInNalUnitHeader;
        long position = extractorInput.getPosition();
        if (this.sampleTrackIndex == -1) {
            int trackIndexOfNextReadSample = getTrackIndexOfNextReadSample(position);
            this.sampleTrackIndex = trackIndexOfNextReadSample;
            if (trackIndexOfNextReadSample == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i10 = mp4Track.sampleIndex;
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        long j10 = trackSampleTable.offsets[i10] + this.sampleOffsetForAuxiliaryTracks;
        int i11 = trackSampleTable.sizes[i10];
        TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
        long j11 = (j10 - position) + ((long) this.sampleBytesRead);
        if (j11 < 0 || j11 >= 262144) {
            positionHolder.position = j10;
            return 1;
        }
        if (mp4Track.track.sampleTransformation == 1) {
            j11 += 8;
            i11 -= 8;
        }
        extractorInput.skipFully((int) j11);
        if (!canReadWithinGopSampleDependencies(mp4Track.track.format)) {
            this.isSampleDependedOn = true;
        }
        Track track = mp4Track.track;
        if (track.nalUnitLengthFieldLength == 0) {
            if (MimeTypes.AUDIO_AC4.equals(track.format.sampleMimeType)) {
                if (this.sampleBytesWritten == 0) {
                    Ac4Util.getAc4SampleHeader(i11, this.scratch);
                    trackOutput.sampleData(this.scratch, 7);
                    this.sampleBytesWritten += 7;
                }
                i11 += 7;
                cryptoData = null;
            } else if (mp4Track.pendingFormat == null || !Objects.equals(mp4Track.track.format.sampleMimeType, MimeTypes.AUDIO_MPEG)) {
                cryptoData = null;
                if (trueHdSampleRechunker != null) {
                    trueHdSampleRechunker.startSample(extractorInput);
                }
            } else {
                Format formatBuild = mp4Track.pendingFormat;
                this.scratch.reset(4);
                extractorInput.peekFully(this.scratch.getData(), 0, 4);
                extractorInput.resetPeekPosition();
                MpegAudioUtil.Header header = new MpegAudioUtil.Header();
                TrackOutput trackOutput2 = mp4Track.trackOutput;
                if (header.setForHeaderData(this.scratch.readInt()) && !Objects.equals(formatBuild.sampleMimeType, header.mimeType)) {
                    Format.Builder builderBuildUpon = formatBuild.buildUpon();
                    String str = header.mimeType;
                    str.getClass();
                    formatBuild = builderBuildUpon.setSampleMimeType(str).build();
                }
                trackOutput2.format(formatBuild);
                cryptoData = null;
                mp4Track.pendingFormat = null;
            }
            while (true) {
                int i12 = this.sampleBytesWritten;
                if (i12 >= i11) {
                    break;
                }
                int iSampleData = trackOutput.sampleData((DataReader) extractorInput, i11 - i12, false);
                this.sampleBytesRead += iSampleData;
                this.sampleBytesWritten += iSampleData;
                this.sampleCurrentNalBytesRemaining -= iSampleData;
            }
        } else {
            byte[] data = this.nalPrefix.getData();
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int i13 = 4 - mp4Track.track.nalUnitLengthFieldLength;
            i11 += i13;
            while (this.sampleBytesWritten < i11) {
                int i14 = this.sampleCurrentNalBytesRemaining;
                if (i14 == 0) {
                    Track track2 = mp4Track.track;
                    int i15 = track2.nalUnitLengthFieldLength;
                    if (this.isSampleDependedOn || NalUnitUtil.numberOfBytesInNalUnitHeader(track2.format) + i15 > mp4Track.sampleTable.sizes[i10] - this.sampleBytesRead) {
                        iNumberOfBytesInNalUnitHeader = 0;
                    } else {
                        iNumberOfBytesInNalUnitHeader = NalUnitUtil.numberOfBytesInNalUnitHeader(mp4Track.track.format);
                        i15 = mp4Track.track.nalUnitLengthFieldLength + iNumberOfBytesInNalUnitHeader;
                    }
                    extractorInput.readFully(data, i13, i15);
                    this.sampleBytesRead += i15;
                    this.nalPrefix.setPosition(0);
                    int i16 = this.nalPrefix.readInt();
                    if (i16 < 0) {
                        throw ParserException.createForMalformedContainer("Invalid NAL length", null);
                    }
                    this.sampleCurrentNalBytesRemaining = i16 - iNumberOfBytesInNalUnitHeader;
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    if (iNumberOfBytesInNalUnitHeader > 0) {
                        trackOutput.sampleData(this.nalPrefix, iNumberOfBytesInNalUnitHeader);
                        this.sampleBytesWritten += iNumberOfBytesInNalUnitHeader;
                        if (NalUnitUtil.isDependedOn(data, 4, iNumberOfBytesInNalUnitHeader, mp4Track.track.format)) {
                            this.isSampleDependedOn = true;
                        }
                    }
                } else {
                    int iSampleData2 = trackOutput.sampleData((DataReader) extractorInput, i14, false);
                    this.sampleBytesRead += iSampleData2;
                    this.sampleBytesWritten += iSampleData2;
                    this.sampleCurrentNalBytesRemaining -= iSampleData2;
                }
            }
            cryptoData = null;
        }
        int i17 = i11;
        TrackSampleTable trackSampleTable2 = mp4Track.sampleTable;
        long j12 = trackSampleTable2.timestampsUs[i10];
        int i18 = trackSampleTable2.flags[i10];
        if (!this.isSampleDependedOn) {
            i18 |= C.BUFFER_FLAG_NOT_DEPENDED_ON;
        }
        int i19 = i18;
        if (trueHdSampleRechunker != null) {
            boolean z = false;
            trueHdSampleRechunker.sampleMetadata(trackOutput, j12, i19, i17, 0, null);
            r1 = z;
            if (i10 + 1 == mp4Track.sampleTable.sampleCount) {
                trueHdSampleRechunker.outputPendingSampleMetadata(trackOutput, cryptoData);
                r1 = z;
            }
        } else {
            r1 = 0;
            trackOutput.sampleMetadata(j12, i19, i17, 0, null);
        }
        mp4Track.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = r1;
        this.sampleBytesWritten = r1;
        this.sampleCurrentNalBytesRemaining = r1;
        this.isSampleDependedOn = r1;
        return r1;
    }

    private int readSefData(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i10 = this.sefReader.read(extractorInput, positionHolder, this.slowMotionMetadataEntries);
        if (i10 == 1 && positionHolder.position == 0) {
            enterReadingAtomHeaderState();
        }
        return i10;
    }

    private static boolean shouldParseContainerAtom(int i10) {
        return i10 == 1836019574 || i10 == 1953653099 || i10 == 1835297121 || i10 == 1835626086 || i10 == 1937007212 || i10 == 1701082227 || i10 == 1835365473 || i10 == 1635284069;
    }

    private static boolean shouldParseLeafAtom(int i10) {
        return i10 == 1835296868 || i10 == 1836476516 || i10 == 1751411826 || i10 == 1937011556 || i10 == 1937011827 || i10 == 1937011571 || i10 == 1668576371 || i10 == 1701606260 || i10 == 1937011555 || i10 == 1937011578 || i10 == 1937013298 || i10 == 1937007471 || i10 == 1668232756 || i10 == 1953196132 || i10 == 1718909296 || i10 == 1969517665 || i10 == 1801812339 || i10 == 1768715124;
    }

    private boolean shouldSeekToAxteAtom(Metadata metadata) {
        MdtaMetadataEntry mdtaMetadataEntry;
        if (metadata == null || (this.flags & 64) == 0 || (mdtaMetadataEntry = (MdtaMetadataEntry) metadata.getFirstMatchingEntry(MdtaMetadataEntry.class, new d(4))) == null) {
            return false;
        }
        long unsignedLongToLong = new ParsableByteArray(mdtaMetadataEntry.value).readUnsignedLongToLong();
        if (unsignedLongToLong <= 0) {
            return false;
        }
        this.axteAtomOffset = unsignedLongToLong;
        return true;
    }

    private void updateSampleIndex(Mp4Track mp4Track, long j10) {
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j10);
        if (indexOfEarlierOrEqualSynchronizationSample == -1) {
            indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j10);
        }
        mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
    }

    public long[] getSampleTimestampsUs(int i10) {
        Mp4Track[] mp4TrackArr = this.tracks;
        return mp4TrackArr.length <= i10 ? new long[0] : mp4TrackArr[i10].sampleTable.timestampsUs;
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return androidx.media3.extractor.b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        if ((this.flags & 16) == 0) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (this.omitTrackSampleTable && this.moovAtomProcessed) {
            return -1;
        }
        while (true) {
            int i10 = this.parserState;
            if (i10 != 0) {
                if (i10 != 1) {
                    if (i10 == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    if (i10 == 3) {
                        return readSefData(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                }
                if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.isSampleDependedOn = false;
        this.moovAtomProcessed = false;
        if (j10 == 0) {
            if (this.parserState != 3) {
                enterReadingAtomHeaderState();
                return;
            } else {
                this.sefReader.reset();
                this.slowMotionMetadataEntries.clear();
                return;
            }
        }
        for (Mp4Track mp4Track : this.tracks) {
            updateSampleIndex(mp4Track, j11);
            TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        o3 o3VarS;
        SniffFailure sniffFailureSniffUnfragmented = Sniffer.sniffUnfragmented(extractorInput, (this.flags & 2) != 0);
        if (sniffFailureSniffUnfragmented != null) {
            o3VarS = h1.s(sniffFailureSniffUnfragmented);
        } else {
            f1 f1Var = h1.f14020l;
            o3VarS = o3.f14078o;
        }
        this.lastSniffFailures = o3VarS;
        return sniffFailureSniffUnfragmented == null;
    }

    public Mp4Extractor(SubtitleParser.Factory factory) {
        this(factory, 0);
    }

    @Override // androidx.media3.extractor.Extractor
    public h1 getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    @Deprecated
    public Mp4Extractor(int i10) {
        this(SubtitleParser.Factory.UNSUPPORTED, i10);
    }

    public Mp4Extractor(SubtitleParser.Factory factory, int i10) {
        this.subtitleParserFactory = factory;
        this.flags = i10;
        this.omitTrackSampleTable = (i10 & 256) != 0;
        f1 f1Var = h1.f14020l;
        this.lastSniffFailures = o3.f14078o;
        this.parserState = (i10 & 4) != 0 ? 3 : 0;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(6);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
    }
}

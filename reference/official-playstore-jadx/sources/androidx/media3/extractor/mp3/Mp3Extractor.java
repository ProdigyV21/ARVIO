package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.h;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.Id3Peeker;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.b;
import androidx.media3.extractor.d;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.mp3.Seeker;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import java.util.List;
import k2.c;
import m2.f0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 8;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int FLAG_ENABLE_INDEX_SEEKING = 4;
    private static final int MAX_SEARCH_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_INFO = 1231971951;
    private static final int SEEK_HEADER_UNSET = 0;
    private static final int SEEK_HEADER_VBRI = 1447187017;
    private static final int SEEK_HEADER_XING = 1483304551;
    private static final String TAG = "Mp3Extractor";
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private long endPositionOfLastSampleRead;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;
    public static final ExtractorsFactory FACTORY = new h(27);
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = new h(28);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public Mp3Extractor() {
        this(0);
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        this.realTrackOutput.getClass();
        Util.castNonNull(this.extractorOutput);
    }

    @RequiresNonNull({"realTrackOutput"})
    private Seeker computeSeeker(ExtractorInput extractorInput) throws IOException {
        Seeker seekerMaybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
        MlltSeeker mlltSeekerMaybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        if (mlltSeekerMaybeHandleSeekMetadata != null) {
            seekerMaybeReadSeekFrame = mlltSeekerMaybeHandleSeekMetadata;
        } else if (seekerMaybeReadSeekFrame == null) {
            seekerMaybeReadSeekFrame = null;
        }
        if (seekerMaybeReadSeekFrame == null) {
            seekerMaybeReadSeekFrame = getConstantBitrateSeeker(extractorInput, (this.flags & 2) != 0);
        }
        if ((this.flags & 4) != 0 && !seekerMaybeReadSeekFrame.isSeekable()) {
            seekerMaybeReadSeekFrame = new IndexSeeker(seekerMaybeReadSeekFrame.getDurationUs(), extractorInput.getPosition(), seekerMaybeReadSeekFrame.getDataEndPosition());
        }
        if (shouldFallbackToConstantBitrateSeeking(seekerMaybeReadSeekFrame) && seekerMaybeReadSeekFrame.getDurationUs() != C.TIME_UNSET && (seekerMaybeReadSeekFrame.getDataEndPosition() != -1 || extractorInput.getLength() != -1)) {
            long dataStartPosition = seekerMaybeReadSeekFrame.getDataStartPosition() != -1 ? seekerMaybeReadSeekFrame.getDataStartPosition() : 0L;
            long dataEndPosition = seekerMaybeReadSeekFrame.getDataEndPosition() != -1 ? seekerMaybeReadSeekFrame.getDataEndPosition() : extractorInput.getLength();
            seekerMaybeReadSeekFrame = new ConstantBitrateSeeker(dataEndPosition, dataStartPosition, f0.J(Util.scaleLargeValue(dataEndPosition - dataStartPosition, 8000000L, seekerMaybeReadSeekFrame.getDurationUs(), RoundingMode.HALF_UP)), -1, false);
        } else if (shouldFallbackToConstantBitrateSeeking(seekerMaybeReadSeekFrame)) {
            seekerMaybeReadSeekFrame = getConstantBitrateSeeker(extractorInput, (this.flags & 2) != 0);
        }
        this.realTrackOutput.durationUs(seekerMaybeReadSeekFrame.getDurationUs());
        return seekerMaybeReadSeekFrame;
    }

    private long computeTimeUs(long j10) {
        return ((j10 * 1000000) / ((long) this.synchronizedHeader.sampleRate)) + this.basisTimeUs;
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput, boolean z) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, z);
    }

    private static long getId3TlenUs(Metadata metadata) {
        TextInformationFrame textInformationFrame;
        return (metadata == null || (textInformationFrame = (TextInformationFrame) metadata.getFirstMatchingEntry(TextInformationFrame.class, new d(2))) == null) ? C.TIME_UNSET : Util.msToUs(Long.parseLong((String) textInformationFrame.values.get(0)));
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i10) {
        if (parsableByteArray.limit() >= i10 + 4) {
            parsableByteArray.setPosition(i10);
            int i11 = parsableByteArray.readInt();
            if (i11 == SEEK_HEADER_XING || i11 == SEEK_HEADER_INFO) {
                return i11;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        if (parsableByteArray.readInt() == SEEK_HEADER_VBRI) {
            return SEEK_HEADER_VBRI;
        }
        return 0;
    }

    private static boolean headersMatch(int i10, long j10) {
        return ((long) (i10 & MPEG_AUDIO_HEADER_MASK)) == (j10 & (-128000));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getId3TlenUs$2(TextInformationFrame textInformationFrame) {
        return textInformationFrame.id.equals("TLEN");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$1(int i10, int i11, int i12, int i13, int i14) {
        if (i11 == 67 && i12 == 79 && i13 == 77 && (i14 == 77 || i10 == 2)) {
            return true;
        }
        if (i11 == 77 && i12 == 76 && i13 == 76) {
            return i14 == 84 || i10 == 2;
        }
        return false;
    }

    private static MlltSeeker maybeHandleSeekMetadata(Metadata metadata, long j10) {
        MlltFrame mlltFrame;
        if (metadata == null || (mlltFrame = (MlltFrame) metadata.getFirstEntryOfType(MlltFrame.class)) == null) {
            return null;
        }
        return MlltSeeker.create(j10, mlltFrame, getId3TlenUs(metadata));
    }

    private Seeker maybeReadSeekFrame(ExtractorInput extractorInput) throws IOException {
        int i10;
        int i11;
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.getData(), 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header header = this.synchronizedHeader;
        int i12 = 21;
        if ((header.version & 1) != 0) {
            if (header.channels != 1) {
                i12 = 36;
            }
        } else if (header.channels == 1) {
            i12 = 13;
        }
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i12);
        if (seekFrameHeader != SEEK_HEADER_INFO) {
            if (seekFrameHeader == SEEK_HEADER_VBRI) {
                VbriSeeker vbriSeekerCreate = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
                extractorInput.skipFully(this.synchronizedHeader.frameSize);
                return vbriSeekerCreate;
            }
            if (seekFrameHeader != SEEK_HEADER_XING) {
                extractorInput.resetPeekPosition();
                return null;
            }
        }
        XingFrame xingFrame = XingFrame.parse(this.synchronizedHeader, parsableByteArray);
        if (!this.gaplessInfoHolder.hasGaplessInfo() && (i10 = xingFrame.encoderDelay) != -1 && (i11 = xingFrame.encoderPadding) != -1) {
            GaplessInfoHolder gaplessInfoHolder = this.gaplessInfoHolder;
            gaplessInfoHolder.encoderDelay = i10;
            gaplessInfoHolder.encoderPadding = i11;
        }
        long position = extractorInput.getPosition();
        if (extractorInput.getLength() != -1 && xingFrame.dataSize != -1 && extractorInput.getLength() != xingFrame.dataSize + position) {
            Log.i(TAG, "Data size mismatch between stream (" + extractorInput.getLength() + ") and Xing frame (" + (xingFrame.dataSize + position) + "), using Xing value.");
        }
        extractorInput.skipFully(this.synchronizedHeader.frameSize);
        return seekFrameHeader == SEEK_HEADER_XING ? XingSeeker.create(xingFrame, position) : getConstantBitrateSeeker(position, xingFrame, extractorInput.getLength());
    }

    private void maybeUpdateCbrDurationToLastSample() {
        Seeker seeker = this.seeker;
        if ((seeker instanceof ConstantBitrateSeeker) && seeker.isSeekable()) {
            long j10 = this.endPositionOfLastSampleRead;
            if (j10 == -1 || j10 == this.seeker.getDataEndPosition()) {
                return;
            }
            this.seeker = ((ConstantBitrateSeeker) this.seeker).copyWithNewDataEndPosition(this.endPositionOfLastSampleRead);
            ExtractorOutput extractorOutput = this.extractorOutput;
            extractorOutput.getClass();
            extractorOutput.seekMap(this.seeker);
            TrackOutput trackOutput = this.realTrackOutput;
            trackOutput.getClass();
            trackOutput.durationUs(this.seeker.getDurationUs());
        }
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) throws IOException {
        Seeker seeker = this.seeker;
        if (seeker != null) {
            long dataEndPosition = seeker.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.getData(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private int readInternal(ExtractorInput extractorInput) throws IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            Seeker seekerComputeSeeker = computeSeeker(extractorInput);
            this.seeker = seekerComputeSeeker;
            this.extractorOutput.seekMap(seekerComputeSeeker);
            Format.Builder metadata = new Format.Builder().setContainerMimeType(MimeTypes.AUDIO_MPEG).setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding).setMetadata((this.flags & 8) != 0 ? null : this.metadata);
            if (this.seeker.getAverageBitrate() != -2147483647) {
                metadata.setAverageBitrate(this.seeker.getAverageBitrate());
            }
            this.currentTrackOutput.format(metadata.build());
            this.firstSamplePosition = extractorInput.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long position = extractorInput.getPosition();
            long j10 = this.firstSamplePosition;
            if (position < j10) {
                extractorInput.skipFully((int) (j10 - position));
            }
        }
        return readSample(extractorInput);
    }

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int i10 = this.scratch.readInt();
            if (!headersMatch(i10, this.synchronizedHeaderData) || MpegAudioUtil.getFrameSize(i10) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            this.synchronizedHeader.setForHeaderData(i10);
            if (this.basisTimeUs == C.TIME_UNSET) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != C.TIME_UNSET) {
                    this.basisTimeUs = (this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L)) + this.basisTimeUs;
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
            long position = extractorInput.getPosition();
            MpegAudioUtil.Header header = this.synchronizedHeader;
            this.endPositionOfLastSampleRead = position + ((long) header.frameSize);
            Seeker seeker = this.seeker;
            if (seeker instanceof IndexSeeker) {
                IndexSeeker indexSeeker = (IndexSeeker) seeker;
                indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + ((long) header.samplesPerFrame)), this.endPositionOfLastSampleRead);
                if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                    this.isSeekInProgress = false;
                    this.currentTrackOutput = this.realTrackOutput;
                }
            }
        }
        int iSampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        int i11 = this.sampleBytesRemaining - iSampleData;
        this.sampleBytesRemaining = i11;
        if (i11 > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean shouldFallbackToConstantBitrateSeeking(Seeker seeker) {
        return (seeker.isSeekable() || (seeker instanceof ConstantBitrateSeeker) || (this.flags & 1) == 0) ? false : true;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) throws IOException {
        int peekPosition;
        int i10;
        int frameSize;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            Metadata metadataPeekId3Data = this.id3Peeker.peekId3Data(extractorInput, (this.flags & 8) == 0 ? null : REQUIRED_ID3_FRAME_PREDICATE, 131072);
            this.metadata = metadataPeekId3Data;
            if (metadataPeekId3Data != null) {
                this.gaplessInfoHolder.setFromMetadata(metadataPeekId3Data);
            }
            peekPosition = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(peekPosition);
            }
            i10 = 0;
        } else {
            peekPosition = 0;
            i10 = 0;
        }
        int i11 = i10;
        int i12 = i11;
        while (true) {
            if (!peekEndOfStreamOrHeader(extractorInput)) {
                this.scratch.setPosition(0);
                int i13 = this.scratch.readInt();
                if ((i10 == 0 || headersMatch(i13, i10)) && (frameSize = MpegAudioUtil.getFrameSize(i13)) != -1) {
                    i11++;
                    if (i11 != 1) {
                        if (i11 == 4) {
                            break;
                        }
                    } else {
                        this.synchronizedHeader.setForHeaderData(i13);
                        i10 = i13;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                } else {
                    int i14 = i12 + 1;
                    if (i12 == 131072) {
                        if (z) {
                            return false;
                        }
                        maybeUpdateCbrDurationToLastSample();
                        throw new EOFException();
                    }
                    if (z) {
                        extractorInput.resetPeekPosition();
                        extractorInput.advancePeekPosition(peekPosition + i14);
                    } else {
                        extractorInput.skipFully(1);
                    }
                    i11 = 0;
                    i12 = i14;
                    i10 = 0;
                }
            } else if (i11 <= 0) {
                maybeUpdateCbrDurationToLastSample();
                throw new EOFException();
            }
        }
        if (z) {
            extractorInput.skipFully(peekPosition + i12);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i10;
        return true;
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        TrackOutput trackOutputTrack = extractorOutput.track(0, 1);
        this.realTrackOutput = trackOutputTrack;
        this.currentTrackOutput = trackOutputTrack;
        this.extractorOutput.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int internal = readInternal(extractorInput);
        if (internal == -1 && (this.seeker instanceof IndexSeeker)) {
            long jComputeTimeUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != jComputeTimeUs) {
                ((IndexSeeker) this.seeker).setDurationUs(jComputeTimeUs);
                this.extractorOutput.seekMap(this.seeker);
                this.realTrackOutput.durationUs(this.seeker.getDurationUs());
            }
        }
        return internal;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C.TIME_UNSET;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
        this.endPositionOfLastSampleRead = -1L;
        this.seekTimeUs = j11;
        Seeker seeker = this.seeker;
        if (!(seeker instanceof IndexSeeker) || ((IndexSeeker) seeker).isTimeUsInIndex(j11)) {
            return;
        }
        this.isSeekInProgress = true;
        this.currentTrackOutput = this.skippingTrackOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return synchronize(extractorInput, true);
    }

    public Mp3Extractor(int i10) {
        this(i10, C.TIME_UNSET);
    }

    public Mp3Extractor(int i10, long j10) {
        this.flags = (i10 & 2) != 0 ? i10 | 1 : i10;
        this.forcedFirstSampleTimestampUs = j10;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C.TIME_UNSET;
        this.id3Peeker = new Id3Peeker();
        DiscardingTrackOutput discardingTrackOutput = new DiscardingTrackOutput();
        this.skippingTrackOutput = discardingTrackOutput;
        this.currentTrackOutput = discardingTrackOutput;
        this.endPositionOfLastSampleRead = -1L;
    }

    private Seeker getConstantBitrateSeeker(long j10, XingFrame xingFrame, long j11) {
        long j12;
        long j13;
        long jComputeDurationUs = xingFrame.computeDurationUs();
        if (jComputeDurationUs == C.TIME_UNSET) {
            return null;
        }
        long j14 = xingFrame.dataSize;
        if (j14 != -1) {
            long j15 = j10 + j14;
            j12 = j14 - ((long) xingFrame.header.frameSize);
            j13 = j15;
        } else {
            if (j11 == -1) {
                return null;
            }
            j12 = (j11 - j10) - ((long) xingFrame.header.frameSize);
            j13 = j11;
        }
        long j16 = j12;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        return new ConstantBitrateSeeker(j13, j10 + ((long) xingFrame.header.frameSize), f0.f(Util.scaleLargeValue(j16, 8000000L, jComputeDurationUs, roundingMode)), f0.f(c.i(j16, xingFrame.frameCount, roundingMode)), false);
    }
}

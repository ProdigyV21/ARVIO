package androidx.media3.extractor.ogg;

import ac.b;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
final class DefaultOggSeeker implements OggSeeker {
    private static final int DEFAULT_OFFSET = 30000;
    private static final int MATCH_BYTE_RANGE = 100000;
    private static final int MATCH_RANGE = 72000;
    private static final int STATE_IDLE = 4;
    private static final int STATE_READ_LAST_PAGE = 1;
    private static final int STATE_SEEK = 2;
    private static final int STATE_SEEK_TO_END = 0;
    private static final int STATE_SKIP = 3;
    private long end;
    private long endGranule;
    private final OggPageHeader pageHeader;
    private final long payloadEndPosition;
    private final long payloadStartPosition;
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    public final class OggSeekMap implements SeekMap {
        private OggSeekMap() {
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j10) {
            long jConvertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j10);
            return new SeekMap.SeekPoints(new SeekPoint(j10, Util.constrainValue((BigInteger.valueOf(jConvertTimeToGranule).multiply(BigInteger.valueOf(DefaultOggSeeker.this.payloadEndPosition - DefaultOggSeeker.this.payloadStartPosition)).divide(BigInteger.valueOf(DefaultOggSeeker.this.totalGranules)).longValue() + DefaultOggSeeker.this.payloadStartPosition) - 30000, DefaultOggSeeker.this.payloadStartPosition, DefaultOggSeeker.this.payloadEndPosition - 1)));
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(StreamReader streamReader, long j10, long j11, long j12, long j13, boolean z) {
        b.j(j10 >= 0 && j11 > j10);
        this.streamReader = streamReader;
        this.payloadStartPosition = j10;
        this.payloadEndPosition = j11;
        if (j12 == j11 - j10 || z) {
            this.totalGranules = j13;
            this.state = 4;
        } else {
            this.state = 0;
        }
        this.pageHeader = new OggPageHeader();
    }

    private long getNextSeekPosition(ExtractorInput extractorInput) throws IOException {
        if (this.start == this.end) {
            return -1L;
        }
        long position = extractorInput.getPosition();
        if (!this.pageHeader.skipToNextPage(extractorInput, this.end)) {
            long j10 = this.start;
            if (j10 != position) {
                return j10;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput, false);
        extractorInput.resetPeekPosition();
        long j11 = this.targetGranule;
        OggPageHeader oggPageHeader = this.pageHeader;
        long j12 = oggPageHeader.granulePosition;
        long j13 = j11 - j12;
        int i10 = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (0 <= j13 && j13 < 72000) {
            return -1L;
        }
        if (j13 < 0) {
            this.end = position;
            this.endGranule = j12;
        } else {
            this.start = extractorInput.getPosition() + ((long) i10);
            this.startGranule = this.pageHeader.granulePosition;
        }
        long j14 = this.end;
        long j15 = this.start;
        if (j14 - j15 < SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) {
            this.end = j15;
            return j15;
        }
        long position2 = extractorInput.getPosition() - (((long) i10) * (j13 <= 0 ? 2L : 1L));
        long j16 = this.end;
        long j17 = this.start;
        return Util.constrainValue((((j16 - j17) * j13) / (this.endGranule - this.startGranule)) + position2, j17, j16 - 1);
    }

    private void skipToPageOfTargetGranule(ExtractorInput extractorInput) throws IOException {
        while (true) {
            this.pageHeader.skipToNextPage(extractorInput);
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition > this.targetGranule) {
                extractorInput.resetPeekPosition();
                return;
            } else {
                extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
                this.start = extractorInput.getPosition();
                this.startGranule = this.pageHeader.granulePosition;
            }
        }
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public long read(ExtractorInput extractorInput) throws IOException {
        int i10 = this.state;
        if (i10 == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j10 = this.payloadEndPosition - 65307;
            if (j10 > position) {
                return j10;
            }
        } else if (i10 != 1) {
            if (i10 == 2) {
                long nextSeekPosition = getNextSeekPosition(extractorInput);
                if (nextSeekPosition != -1) {
                    return nextSeekPosition;
                }
                this.state = 3;
            } else if (i10 != 3) {
                if (i10 == 4) {
                    return -1L;
                }
                throw new IllegalStateException();
            }
            skipToPageOfTargetGranule(extractorInput);
            this.state = 4;
            return -(this.startGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 4;
        return this.positionBeforeSeekToEnd;
    }

    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws IOException {
        this.pageHeader.reset();
        if (!this.pageHeader.skipToNextPage(extractorInput)) {
            throw new EOFException();
        }
        this.pageHeader.populate(extractorInput, false);
        OggPageHeader oggPageHeader = this.pageHeader;
        extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
        long j10 = this.pageHeader.granulePosition;
        while (true) {
            OggPageHeader oggPageHeader2 = this.pageHeader;
            if ((oggPageHeader2.type & 4) == 4 || !oggPageHeader2.skipToNextPage(extractorInput) || extractorInput.getPosition() >= this.payloadEndPosition || !this.pageHeader.populate(extractorInput, true)) {
                break;
            }
            OggPageHeader oggPageHeader3 = this.pageHeader;
            if (!ExtractorUtil.skipFullyQuietly(extractorInput, oggPageHeader3.headerSize + oggPageHeader3.bodySize)) {
                break;
            }
            j10 = this.pageHeader.granulePosition;
        }
        return j10;
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public void startSeek(long j10) {
        this.targetGranule = Util.constrainValue(j10, 0L, this.totalGranules - 1);
        this.state = 2;
        this.start = this.payloadStartPosition;
        this.end = this.payloadEndPosition;
        this.startGranule = 0L;
        this.endGranule = this.totalGranules;
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }
}

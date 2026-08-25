package androidx.media3.extractor.heif;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.StartOffsetExtractorInput;
import androidx.media3.extractor.StartOffsetExtractorOutput;
import androidx.media3.extractor.b;
import androidx.media3.extractor.metadata.MotionPhotoMetadata;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class HeicMotionPhotoExtractor implements Extractor {
    private static final int STATE_ENDED = 4;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_MOTION_PHOTO_VIDEO = 3;
    private static final int STATE_SNIFFING_MOTION_PHOTO_VIDEO = 2;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private ExtractorOutput extractorOutput;
    private ExtractorInput lastExtractorInput;
    private MotionPhotoMetadata motionPhotoMetadata;
    private Mp4Extractor mp4Extractor;
    private StartOffsetExtractorInput mp4ExtractorStartOffsetExtractorInput;
    private final ParsableByteArray scratch = new ParsableByteArray(16);
    private long mp4StartPosition = -1;
    private int state = 0;

    private void endReading() {
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        this.state = 4;
    }

    private void outputImageTrack(MotionPhotoMetadata motionPhotoMetadata) {
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        extractorOutput.track(1024, 4).format(new Format.Builder().setContainerMimeType("image/heic").setMetadata(new Metadata(motionPhotoMetadata)).build());
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.scratch.getData(), 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.scratch.setPosition(0);
            this.atomSize = this.scratch.readUnsignedInt();
            this.atomType = this.scratch.readInt();
        }
        if (this.atomSize == 1) {
            extractorInput.readFully(this.scratch.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.scratch.readUnsignedLongToLong();
        }
        if (this.atomType == 1836086884) {
            long position = extractorInput.getPosition();
            this.mp4StartPosition = position;
            int i10 = this.atomHeaderBytesRead;
            MotionPhotoMetadata motionPhotoMetadata = new MotionPhotoMetadata(0L, position - ((long) i10), C.TIME_UNSET, position, this.atomSize - ((long) i10));
            this.motionPhotoMetadata = motionPhotoMetadata;
            outputImageTrack(motionPhotoMetadata);
            this.state = 2;
        } else {
            this.state = 1;
        }
        return true;
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws IOException {
        extractorInput.skipFully((int) (this.atomSize - ((long) this.atomHeaderBytesRead)));
        this.atomHeaderBytesRead = 0;
        this.state = 0;
    }

    private int readMotionPhotoVideo(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (this.mp4ExtractorStartOffsetExtractorInput == null || extractorInput != this.lastExtractorInput) {
            this.lastExtractorInput = extractorInput;
            this.mp4ExtractorStartOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        }
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        mp4Extractor.getClass();
        int i10 = mp4Extractor.read(this.mp4ExtractorStartOffsetExtractorInput, positionHolder);
        if (i10 == 1) {
            positionHolder.position += this.mp4StartPosition;
        }
        return i10;
    }

    private void sniffMotionPhotoVideo(ExtractorInput extractorInput) throws IOException {
        if (this.mp4Extractor == null) {
            this.mp4Extractor = new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 8);
        }
        StartOffsetExtractorInput startOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        this.mp4ExtractorStartOffsetExtractorInput = startOffsetExtractorInput;
        if (!this.mp4Extractor.sniff(startOffsetExtractorInput)) {
            endReading();
            return;
        }
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        long j10 = this.mp4StartPosition;
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        mp4Extractor.init(new StartOffsetExtractorOutput(j10, extractorOutput));
        this.state = 3;
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
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i10 = this.state;
            if (i10 != 0) {
                if (i10 == 1) {
                    readAtomPayload(extractorInput);
                } else {
                    if (i10 != 2) {
                        if (i10 == 3) {
                            return readMotionPhotoVideo(extractorInput, positionHolder);
                        }
                        if (i10 == 4) {
                            return -1;
                        }
                        throw new IllegalStateException();
                    }
                    sniffMotionPhotoVideo(extractorInput);
                }
            } else if (!readAtomHeader(extractorInput)) {
                endReading();
                return -1;
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        if (mp4Extractor != null) {
            mp4Extractor.release();
            this.mp4Extractor = null;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        if (j10 != 0) {
            if (this.state == 3) {
                Mp4Extractor mp4Extractor = this.mp4Extractor;
                mp4Extractor.getClass();
                mp4Extractor.seek(j10, j11);
                return;
            }
            return;
        }
        this.state = 0;
        this.atomHeaderBytesRead = 0;
        this.mp4StartPosition = -1L;
        Mp4Extractor mp4Extractor2 = this.mp4Extractor;
        if (mp4Extractor2 != null) {
            mp4Extractor2.release();
            this.mp4Extractor = null;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return HeifSniffer.sniff(extractorInput, true);
    }
}

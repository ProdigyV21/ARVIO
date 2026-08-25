package androidx.media3.extractor.jpeg;

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
import j$.util.Objects;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class JpegMotionPhotoExtractor implements Extractor {
    private static final String HEADER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/";
    private static final int MARKER_APP1 = 65505;
    private static final int MARKER_SIZE = 2;
    private static final int MARKER_SOI = 65496;
    private static final int MARKER_SOS = 65498;
    private static final int SEGMENT_LENGTH_SIZE = 2;
    private static final int STATE_ENDED = 6;
    private static final int STATE_READING_MARKER = 0;
    private static final int STATE_READING_MOTION_PHOTO_VIDEO = 5;
    private static final int STATE_READING_SEGMENT = 2;
    private static final int STATE_READING_SEGMENT_LENGTH = 1;
    private static final int STATE_SNIFFING_MOTION_PHOTO_VIDEO = 4;
    private ExtractorOutput extractorOutput;
    private ExtractorInput lastExtractorInput;
    private int marker;
    private MotionPhotoMetadata motionPhotoMetadata;
    private Mp4Extractor mp4Extractor;
    private StartOffsetExtractorInput mp4ExtractorStartOffsetExtractorInput;
    private int segmentLength;
    private int state;
    private final ParsableByteArray scratch = new ParsableByteArray(2);
    private long mp4StartPosition = -1;

    private void endReading() {
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        this.state = 6;
    }

    private static MotionPhotoMetadata getMotionPhotoMetadata(String str, long j10) throws IOException {
        MotionPhotoDescription motionPhotoDescription;
        if (j10 == -1 || (motionPhotoDescription = XmpMotionPhotoDescriptionParser.parse(str)) == null) {
            return null;
        }
        return motionPhotoDescription.getMotionPhotoMetadata(j10);
    }

    private boolean isMotionPhotoXmp(ParsableByteArray parsableByteArray) {
        if (Objects.equals(parsableByteArray.readNullTerminatedString(), HEADER_XMP_APP1)) {
            return XmpMotionPhotoDescriptionParser.isMotionPhotoXmp(parsableByteArray.readNullTerminatedString());
        }
        return false;
    }

    private void outputImageTrack(MotionPhotoMetadata motionPhotoMetadata) {
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        extractorOutput.track(1024, 4).format(new Format.Builder().setContainerMimeType("image/jpeg").setMetadata(new Metadata(motionPhotoMetadata)).build());
    }

    private int peekMarker(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        return this.scratch.readUnsignedShort();
    }

    private int peekSegmentLength(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        return this.scratch.readUnsignedShort() - 2;
    }

    private void readMarker(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.readFully(this.scratch.getData(), 0, 2);
        int unsignedShort = this.scratch.readUnsignedShort();
        this.marker = unsignedShort;
        if (unsignedShort == MARKER_SOS) {
            if (this.mp4StartPosition != -1) {
                this.state = 4;
                return;
            } else {
                endReading();
                return;
            }
        }
        if ((unsignedShort < 65488 || unsignedShort > 65497) && unsignedShort != 65281) {
            this.state = 1;
        }
    }

    private void readSegment(ExtractorInput extractorInput) throws IOException {
        String nullTerminatedString;
        if (this.marker == MARKER_APP1) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(this.segmentLength);
            extractorInput.readFully(parsableByteArray.getData(), 0, this.segmentLength);
            if (this.motionPhotoMetadata == null && HEADER_XMP_APP1.equals(parsableByteArray.readNullTerminatedString()) && (nullTerminatedString = parsableByteArray.readNullTerminatedString()) != null) {
                MotionPhotoMetadata motionPhotoMetadata = getMotionPhotoMetadata(nullTerminatedString, extractorInput.getLength());
                this.motionPhotoMetadata = motionPhotoMetadata;
                if (motionPhotoMetadata != null) {
                    this.mp4StartPosition = motionPhotoMetadata.videoStartPosition;
                }
            }
        } else {
            extractorInput.skipFully(this.segmentLength);
        }
        this.state = 0;
    }

    private void readSegmentLength(ExtractorInput extractorInput) throws IOException {
        this.segmentLength = peekSegmentLength(extractorInput);
        extractorInput.skipFully(2);
        this.state = 2;
    }

    private void sniffMotionPhotoVideo(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.peekFully(this.scratch.getData(), 0, 1, true)) {
            endReading();
            return;
        }
        extractorInput.resetPeekPosition();
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
        startReadingMotionPhoto();
    }

    private void startReadingMotionPhoto() {
        MotionPhotoMetadata motionPhotoMetadata = this.motionPhotoMetadata;
        motionPhotoMetadata.getClass();
        outputImageTrack(motionPhotoMetadata);
        this.state = 5;
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
        int i10 = this.state;
        if (i10 == 0) {
            readMarker(extractorInput);
            return 0;
        }
        if (i10 == 1) {
            readSegmentLength(extractorInput);
            return 0;
        }
        if (i10 == 2) {
            readSegment(extractorInput);
            return 0;
        }
        if (i10 == 4) {
            long position = extractorInput.getPosition();
            long j10 = this.mp4StartPosition;
            if (position != j10) {
                positionHolder.position = j10;
                return 1;
            }
            sniffMotionPhotoVideo(extractorInput);
            return 0;
        }
        if (i10 != 5) {
            if (i10 == 6) {
                return -1;
            }
            throw new IllegalStateException();
        }
        if (this.mp4ExtractorStartOffsetExtractorInput == null || extractorInput != this.lastExtractorInput) {
            this.lastExtractorInput = extractorInput;
            this.mp4ExtractorStartOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        }
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        mp4Extractor.getClass();
        int i11 = mp4Extractor.read(this.mp4ExtractorStartOffsetExtractorInput, positionHolder);
        if (i11 == 1) {
            positionHolder.position += this.mp4StartPosition;
        }
        return i11;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        if (mp4Extractor != null) {
            mp4Extractor.release();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        if (j10 == 0) {
            this.state = 0;
            this.mp4Extractor = null;
        } else if (this.state == 5) {
            Mp4Extractor mp4Extractor = this.mp4Extractor;
            mp4Extractor.getClass();
            mp4Extractor.seek(j10, j11);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
    
        return false;
     */
    @Override // androidx.media3.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean sniff(androidx.media3.extractor.ExtractorInput r5) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4.peekMarker(r5)
            r1 = 65496(0xffd8, float:9.178E-41)
            r2 = 0
            if (r0 == r1) goto Lb
            return r2
        Lb:
            int r0 = r4.peekMarker(r5)
            r4.marker = r0
            r1 = 65498(0xffda, float:9.1782E-41)
            if (r0 != r1) goto L17
            goto L1d
        L17:
            int r0 = r4.peekSegmentLength(r5)
            if (r0 >= 0) goto L1e
        L1d:
            return r2
        L1e:
            int r1 = r4.marker
            r3 = 65505(0xffe1, float:9.1792E-41)
            if (r1 == r3) goto L29
            r5.advancePeekPosition(r0)
            goto Lb
        L29:
            androidx.media3.common.util.ParsableByteArray r1 = r4.scratch
            r1.reset(r0)
            androidx.media3.common.util.ParsableByteArray r1 = r4.scratch
            byte[] r1 = r1.getData()
            r5.peekFully(r1, r2, r0)
            androidx.media3.common.util.ParsableByteArray r0 = r4.scratch
            boolean r0 = r4.isMotionPhotoXmp(r0)
            if (r0 == 0) goto Lb
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.jpeg.JpegMotionPhotoExtractor.sniff(androidx.media3.extractor.ExtractorInput):boolean");
    }
}

package androidx.media3.extractor.flv;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.h;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.b;
import com.arflix.tv.ui.theme.AnimationConstants;
import java.io.IOException;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class FlvExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new h(24);
    private static final int FLV_HEADER_SIZE = 9;
    private static final int FLV_TAG = 4607062;
    private static final int FLV_TAG_HEADER_SIZE = 11;
    private static final int STATE_READING_FLV_HEADER = 1;
    private static final int STATE_READING_TAG_DATA = 4;
    private static final int STATE_READING_TAG_HEADER = 3;
    private static final int STATE_SKIPPING_TO_TAG_HEADER = 2;
    private static final int TAG_TYPE_AUDIO = 8;
    private static final int TAG_TYPE_SCRIPT_DATA = 18;
    private static final int TAG_TYPE_VIDEO = 9;
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private long mediaTagTimestampOffsetUs;
    private boolean outputFirstSample;
    private boolean outputSeekMap;
    private int tagDataSize;
    private long tagTimestampUs;
    private int tagType;
    private VideoTagPayloadReader videoReader;
    private final ParsableByteArray scratch = new ParsableByteArray(4);
    private final ParsableByteArray headerBuffer = new ParsableByteArray(9);
    private final ParsableByteArray tagHeaderBuffer = new ParsableByteArray(11);
    private final ParsableByteArray tagData = new ParsableByteArray();
    private final ScriptTagPayloadReader metadataReader = new ScriptTagPayloadReader();
    private int state = 1;

    @RequiresNonNull({"extractorOutput"})
    private void ensureReadyForMediaOutput() {
        if (this.outputSeekMap) {
            return;
        }
        this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        this.outputSeekMap = true;
    }

    private long getCurrentTimestampUs() {
        if (this.outputFirstSample) {
            return this.mediaTagTimestampOffsetUs + this.tagTimestampUs;
        }
        if (this.metadataReader.getDurationUs() == C.TIME_UNSET) {
            return 0L;
        }
        return this.tagTimestampUs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FlvExtractor()};
    }

    private ParsableByteArray prepareTagData(ExtractorInput extractorInput) throws IOException {
        if (this.tagDataSize > this.tagData.capacity()) {
            ParsableByteArray parsableByteArray = this.tagData;
            parsableByteArray.reset(new byte[Math.max(parsableByteArray.capacity() * 2, this.tagDataSize)], 0);
        } else {
            this.tagData.setPosition(0);
        }
        this.tagData.setLimit(this.tagDataSize);
        extractorInput.readFully(this.tagData.getData(), 0, this.tagDataSize);
        return this.tagData;
    }

    @RequiresNonNull({"extractorOutput"})
    private boolean readFlvHeader(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.readFully(this.headerBuffer.getData(), 0, 9, true)) {
            return false;
        }
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int unsignedByte = this.headerBuffer.readUnsignedByte();
        boolean z = (unsignedByte & 4) != 0;
        boolean z5 = (unsignedByte & 1) != 0;
        if (z && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
        }
        if (z5 && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9, 2));
        }
        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = this.headerBuffer.readInt() - 5;
        this.state = 2;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"extractorOutput"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean readTagData(androidx.media3.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.getCurrentTimestampUs()
            int r2 = r9.tagType
            r3 = 8
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 1
            if (r2 != r3) goto L23
            androidx.media3.extractor.flv.AudioTagPayloadReader r3 = r9.audioReader
            if (r3 == 0) goto L23
            r9.ensureReadyForMediaOutput()
            androidx.media3.extractor.flv.AudioTagPayloadReader r2 = r9.audioReader
            androidx.media3.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
        L21:
            r0 = r6
            goto L75
        L23:
            r3 = 9
            if (r2 != r3) goto L39
            androidx.media3.extractor.flv.VideoTagPayloadReader r3 = r9.videoReader
            if (r3 == 0) goto L39
            r9.ensureReadyForMediaOutput()
            androidx.media3.extractor.flv.VideoTagPayloadReader r2 = r9.videoReader
            androidx.media3.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            goto L21
        L39:
            r3 = 18
            if (r2 != r3) goto L6e
            boolean r2 = r9.outputSeekMap
            if (r2 != 0) goto L6e
            androidx.media3.extractor.flv.ScriptTagPayloadReader r2 = r9.metadataReader
            androidx.media3.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            androidx.media3.extractor.flv.ScriptTagPayloadReader r0 = r9.metadataReader
            long r0 = r0.getDurationUs()
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            androidx.media3.extractor.ExtractorOutput r2 = r9.extractorOutput
            androidx.media3.extractor.IndexSeekMap r3 = new androidx.media3.extractor.IndexSeekMap
            androidx.media3.extractor.flv.ScriptTagPayloadReader r7 = r9.metadataReader
            long[] r7 = r7.getKeyFrameTagPositions()
            androidx.media3.extractor.flv.ScriptTagPayloadReader r8 = r9.metadataReader
            long[] r8 = r8.getKeyFrameTimesUs()
            r3.<init>(r7, r8, r0)
            r2.seekMap(r3)
            r9.outputSeekMap = r6
            goto L21
        L6e:
            int r0 = r9.tagDataSize
            r10.skipFully(r0)
            r10 = 0
            r0 = r10
        L75:
            boolean r1 = r9.outputFirstSample
            if (r1 != 0) goto L8f
            if (r10 == 0) goto L8f
            r9.outputFirstSample = r6
            androidx.media3.extractor.flv.ScriptTagPayloadReader r10 = r9.metadataReader
            long r1 = r10.getDurationUs()
            int r10 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r10 != 0) goto L8b
            long r1 = r9.tagTimestampUs
            long r1 = -r1
            goto L8d
        L8b:
            r1 = 0
        L8d:
            r9.mediaTagTimestampOffsetUs = r1
        L8f:
            r10 = 4
            r9.bytesToNextTagHeader = r10
            r10 = 2
            r9.state = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.flv.FlvExtractor.readTagData(androidx.media3.extractor.ExtractorInput):boolean");
    }

    private boolean readTagHeader(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.readFully(this.tagHeaderBuffer.getData(), 0, 11, true)) {
            return false;
        }
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (((long) (this.tagHeaderBuffer.readUnsignedByte() << 24)) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return true;
    }

    private void skipToTagHeader(ExtractorInput extractorInput) throws IOException {
        extractorInput.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
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
        this.extractorOutput.getClass();
        while (true) {
            int i10 = this.state;
            if (i10 != 1) {
                if (i10 == 2) {
                    skipToTagHeader(extractorInput);
                } else if (i10 != 3) {
                    if (i10 != 4) {
                        throw new IllegalStateException();
                    }
                    if (readTagData(extractorInput)) {
                        return 0;
                    }
                } else if (!readTagHeader(extractorInput)) {
                    return -1;
                }
            } else if (!readFlvHeader(extractorInput)) {
                return -1;
            }
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        if (j10 == 0) {
            this.state = 1;
            this.outputFirstSample = false;
        } else {
            this.state = 3;
        }
        this.bytesToNextTagHeader = 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != FLV_TAG) {
            return false;
        }
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & AnimationConstants.DURATION_IMAGE_CROSSFADE) != 0) {
            return false;
        }
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        int i10 = this.scratch.readInt();
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i10);
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        return this.scratch.readInt() == 0;
    }
}

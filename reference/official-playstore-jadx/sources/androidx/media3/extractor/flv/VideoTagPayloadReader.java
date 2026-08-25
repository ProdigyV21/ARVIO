package androidx.media3.extractor.flv;

import a0.c;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;

/* JADX INFO: loaded from: classes3.dex */
final class VideoTagPayloadReader extends TagPayloadReader {
    private static final int AVC_PACKET_TYPE_AVC_NALU = 1;
    private static final int AVC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    private static final int VIDEO_CODEC_AVC = 7;
    private static final int VIDEO_FRAME_KEYFRAME = 1;
    private static final int VIDEO_FRAME_VIDEO_INFO = 5;
    private int frameType;
    private boolean hasOutputFormat;
    private boolean hasOutputKeyframe;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int nalUnitLengthFieldLength;

    public VideoTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i10 = (unsignedByte >> 4) & 15;
        int i11 = unsignedByte & 15;
        if (i11 != 7) {
            throw new TagPayloadReader.UnsupportedFormatException(c.i(i11, "Video format not supported: "));
        }
        this.frameType = i10;
        return i10 != 5;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray parsableByteArray, long j10) throws ParserException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        long int24 = (((long) parsableByteArray.readInt24()) * 1000) + j10;
        if (unsignedByte == 0 && !this.hasOutputFormat) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray(new byte[parsableByteArray.bytesLeft()]);
            parsableByteArray.readBytes(parsableByteArray2.getData(), 0, parsableByteArray.bytesLeft());
            AvcConfig avcConfig = AvcConfig.parse(parsableByteArray2);
            this.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
            this.output.format(new Format.Builder().setContainerMimeType(MimeTypes.VIDEO_FLV).setSampleMimeType(MimeTypes.VIDEO_H264).setCodecs(avcConfig.codecs).setWidth(avcConfig.width).setHeight(avcConfig.height).setPixelWidthHeightRatio(avcConfig.pixelWidthHeightRatio).setInitializationData(avcConfig.initializationData).build());
            this.hasOutputFormat = true;
            return false;
        }
        if (unsignedByte != 1 || !this.hasOutputFormat) {
            return false;
        }
        int i10 = this.frameType == 1 ? 1 : 0;
        if (!this.hasOutputKeyframe && i10 == 0) {
            return false;
        }
        byte[] data = this.nalLength.getData();
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        int i11 = 4 - this.nalUnitLengthFieldLength;
        int i12 = 0;
        while (parsableByteArray.bytesLeft() > 0) {
            parsableByteArray.readBytes(this.nalLength.getData(), i11, this.nalUnitLengthFieldLength);
            this.nalLength.setPosition(0);
            int unsignedIntToInt = this.nalLength.readUnsignedIntToInt();
            this.nalStartCode.setPosition(0);
            this.output.sampleData(this.nalStartCode, 4);
            this.output.sampleData(parsableByteArray, unsignedIntToInt);
            i12 = i12 + 4 + unsignedIntToInt;
        }
        this.output.sampleMetadata(int24, i10, i12, 0, null);
        this.hasOutputKeyframe = true;
        return true;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public void seek() {
        this.hasOutputKeyframe = false;
    }
}

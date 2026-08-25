package androidx.media3.extractor.avi;

import androidx.fragment.app.a2;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.collect.h1;

/* JADX INFO: loaded from: classes3.dex */
final class StreamFormatChunk implements AviChunk {
    private static final String TAG = "StreamFormatChunk";
    public final Format format;

    public StreamFormatChunk(Format format) {
        this.format = format;
    }

    private static String getMimeTypeFromCompression(int i10) {
        switch (i10) {
            case 808802372:
            case 877677894:
            case 1145656883:
            case 1145656920:
            case 1482049860:
            case 1684633208:
            case 2021026148:
                return MimeTypes.VIDEO_MP4V;
            case 826496577:
            case 828601953:
            case 875967048:
                return MimeTypes.VIDEO_H264;
            case 842289229:
                return MimeTypes.VIDEO_MP42;
            case 859066445:
                return MimeTypes.VIDEO_MP43;
            case 1196444237:
            case 1735420525:
                return MimeTypes.VIDEO_MJPEG;
            default:
                return null;
        }
    }

    private static String getMimeTypeFromTag(int i10) {
        if (i10 == 1) {
            return MimeTypes.AUDIO_RAW;
        }
        if (i10 == 85) {
            return MimeTypes.AUDIO_MPEG;
        }
        if (i10 == 255) {
            return MimeTypes.AUDIO_AAC;
        }
        if (i10 == 8192) {
            return MimeTypes.AUDIO_AC3;
        }
        if (i10 != 8193) {
            return null;
        }
        return MimeTypes.AUDIO_DTS;
    }

    private static AviChunk parseBitmapInfoHeader(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int littleEndianInt3 = parsableByteArray.readLittleEndianInt();
        String mimeTypeFromCompression = getMimeTypeFromCompression(littleEndianInt3);
        if (mimeTypeFromCompression == null) {
            a2.s(littleEndianInt3, "Ignoring track with unsupported compression ", TAG);
            return null;
        }
        Format.Builder builder = new Format.Builder();
        builder.setWidth(littleEndianInt).setHeight(littleEndianInt2).setSampleMimeType(mimeTypeFromCompression);
        return new StreamFormatChunk(builder.build());
    }

    public static AviChunk parseFrom(int i10, ParsableByteArray parsableByteArray) {
        if (i10 == 2) {
            return parseBitmapInfoHeader(parsableByteArray);
        }
        if (i10 == 1) {
            return parseWaveFormatEx(parsableByteArray);
        }
        Log.w(TAG, "Ignoring strf box for unsupported track type: " + Util.getTrackTypeString(i10));
        return null;
    }

    private static AviChunk parseWaveFormatEx(ParsableByteArray parsableByteArray) {
        int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        String mimeTypeFromTag = getMimeTypeFromTag(littleEndianUnsignedShort);
        if (mimeTypeFromTag == null) {
            a2.s(littleEndianUnsignedShort, "Ignoring track with unsupported format tag ", TAG);
            return null;
        }
        int littleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(6);
        int pcmEncoding = Util.getPcmEncoding(parsableByteArray.readLittleEndianUnsignedShort());
        int littleEndianUnsignedShort3 = parsableByteArray.bytesLeft() > 0 ? parsableByteArray.readLittleEndianUnsignedShort() : 0;
        Format.Builder builder = new Format.Builder();
        builder.setSampleMimeType(mimeTypeFromTag).setChannelCount(littleEndianUnsignedShort2).setSampleRate(littleEndianInt);
        if (mimeTypeFromTag.equals(MimeTypes.AUDIO_RAW) && pcmEncoding != 0) {
            builder.setPcmEncoding(pcmEncoding);
        }
        if (mimeTypeFromTag.equals(MimeTypes.AUDIO_AAC) && littleEndianUnsignedShort3 > 0) {
            byte[] bArr = new byte[littleEndianUnsignedShort3];
            parsableByteArray.readBytes(bArr, 0, littleEndianUnsignedShort3);
            builder.setInitializationData(h1.s(bArr));
        }
        return new StreamFormatChunk(builder.build());
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return AviExtractor.FOURCC_strf;
    }
}

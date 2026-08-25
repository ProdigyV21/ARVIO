package androidx.media3.extractor;

import android.util.Base64;
import androidx.fragment.app.a2;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.vorbis.VorbisComment;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.collect.h1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class VorbisUtil {
    private static final String TAG = "VorbisUtil";

    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String str, String[] strArr, int i10) {
            this.vendor = str;
            this.comments = strArr;
            this.length = i10;
        }
    }

    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean z, int i10, int i11, int i12) {
            this.blockFlag = z;
            this.windowType = i10;
            this.transformType = i11;
            this.mapping = i12;
        }
    }

    public static final class VorbisIdHeader {
        public final int bitrateMaximum;
        public final int bitrateMinimum;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final int sampleRate;
        public final int version;

        public VorbisIdHeader(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, boolean z, byte[] bArr) {
            this.version = i10;
            this.channels = i11;
            this.sampleRate = i12;
            this.bitrateMaximum = i13;
            this.bitrateNominal = i14;
            this.bitrateMinimum = i15;
            this.blockSize0 = i16;
            this.blockSize1 = i17;
            this.framingFlag = z;
            this.data = bArr;
        }
    }

    private VorbisUtil() {
    }

    public static int[] getVorbisToAndroidChannelLayoutMapping(int i10) {
        if (i10 == 3) {
            return new int[]{0, 2, 1};
        }
        if (i10 == 5) {
            return new int[]{0, 2, 1, 3, 4};
        }
        if (i10 == 6) {
            return new int[]{0, 2, 1, 5, 3, 4};
        }
        if (i10 == 7) {
            return new int[]{0, 2, 1, 6, 5, 3, 4};
        }
        if (i10 != 8) {
            return null;
        }
        return new int[]{0, 2, 1, 7, 5, 6, 3, 4};
    }

    public static int iLog(int i10) {
        int i11 = 0;
        while (i10 > 0) {
            i11++;
            i10 >>>= 1;
        }
        return i11;
    }

    private static long mapType1QuantValues(long j10, long j11) {
        return (long) Math.floor(Math.pow(j10, 1.0d / j11));
    }

    public static Metadata parseVorbisComments(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            String str = list.get(i10);
            String[] strArrSplitAtFirst = Util.splitAtFirst(str, "=");
            if (strArrSplitAtFirst.length != 2) {
                a2.w("Failed to parse Vorbis comment: ", str, TAG);
            } else if (strArrSplitAtFirst[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(PictureFrame.fromPictureBlock(new ParsableByteArray(Base64.decode(strArrSplitAtFirst[1], 0))));
                } catch (RuntimeException e5) {
                    Log.w(TAG, "Failed to parse vorbis picture", e5);
                }
            } else {
                arrayList.add(new VorbisComment(strArrSplitAtFirst[0], strArrSplitAtFirst[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static h1 parseVorbisCsdFromEsdsInitializationData(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.skipBytes(1);
        int i10 = 0;
        while (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 255) {
            i10 += 255;
            parsableByteArray.skipBytes(1);
        }
        int unsignedByte = parsableByteArray.readUnsignedByte() + i10;
        int i11 = 0;
        while (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 255) {
            i11 += 255;
            parsableByteArray.skipBytes(1);
        }
        int unsignedByte2 = parsableByteArray.readUnsignedByte() + i11;
        byte[] bArr2 = new byte[unsignedByte];
        int position = parsableByteArray.getPosition();
        System.arraycopy(bArr, position, bArr2, 0, unsignedByte);
        int i12 = position + unsignedByte + unsignedByte2;
        int length = bArr.length - i12;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, i12, bArr3, 0, length);
        return h1.t(bArr2, bArr3);
    }

    private static void readFloors(VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i10 = 0; i10 < bits; i10++) {
            int bits2 = vorbisBitArray.readBits(16);
            if (bits2 == 0) {
                vorbisBitArray.skipBits(8);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(6);
                vorbisBitArray.skipBits(8);
                int bits3 = vorbisBitArray.readBits(4) + 1;
                for (int i11 = 0; i11 < bits3; i11++) {
                    vorbisBitArray.skipBits(8);
                }
            } else {
                if (bits2 != 1) {
                    throw ParserException.createForMalformedContainer("floor type greater than 1 not decodable: " + bits2, null);
                }
                int bits4 = vorbisBitArray.readBits(5);
                int[] iArr = new int[bits4];
                int i12 = -1;
                for (int i13 = 0; i13 < bits4; i13++) {
                    int bits5 = vorbisBitArray.readBits(4);
                    iArr[i13] = bits5;
                    if (bits5 > i12) {
                        i12 = bits5;
                    }
                }
                int i14 = i12 + 1;
                int[] iArr2 = new int[i14];
                for (int i15 = 0; i15 < i14; i15++) {
                    iArr2[i15] = vorbisBitArray.readBits(3) + 1;
                    int bits6 = vorbisBitArray.readBits(2);
                    if (bits6 > 0) {
                        vorbisBitArray.skipBits(8);
                    }
                    for (int i16 = 0; i16 < (1 << bits6); i16++) {
                        vorbisBitArray.skipBits(8);
                    }
                }
                vorbisBitArray.skipBits(2);
                int bits7 = vorbisBitArray.readBits(4);
                int i17 = 0;
                int i18 = 0;
                for (int i19 = 0; i19 < bits4; i19++) {
                    i17 += iArr2[iArr[i19]];
                    while (i18 < i17) {
                        vorbisBitArray.skipBits(bits7);
                        i18++;
                    }
                }
            }
        }
    }

    private static void readMappings(int i10, VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i11 = 0; i11 < bits; i11++) {
            int bits2 = vorbisBitArray.readBits(16);
            if (bits2 != 0) {
                Log.e(TAG, "mapping type other than 0 not supported: " + bits2);
            } else {
                int bits3 = vorbisBitArray.readBit() ? vorbisBitArray.readBits(4) + 1 : 1;
                if (vorbisBitArray.readBit()) {
                    int bits4 = vorbisBitArray.readBits(8) + 1;
                    for (int i12 = 0; i12 < bits4; i12++) {
                        int i13 = i10 - 1;
                        vorbisBitArray.skipBits(iLog(i13));
                        vorbisBitArray.skipBits(iLog(i13));
                    }
                }
                if (vorbisBitArray.readBits(2) != 0) {
                    throw ParserException.createForMalformedContainer("to reserved bits must be zero after mapping coupling steps", null);
                }
                if (bits3 > 1) {
                    for (int i14 = 0; i14 < i10; i14++) {
                        vorbisBitArray.skipBits(4);
                    }
                }
                for (int i15 = 0; i15 < bits3; i15++) {
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                }
            }
        }
    }

    private static Mode[] readModes(VorbisBitArray vorbisBitArray) {
        int bits = vorbisBitArray.readBits(6) + 1;
        Mode[] modeArr = new Mode[bits];
        for (int i10 = 0; i10 < bits; i10++) {
            modeArr[i10] = new Mode(vorbisBitArray.readBit(), vorbisBitArray.readBits(16), vorbisBitArray.readBits(16), vorbisBitArray.readBits(8));
        }
        return modeArr;
    }

    private static void readResidues(VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i10 = 0; i10 < bits; i10++) {
            if (vorbisBitArray.readBits(16) > 2) {
                throw ParserException.createForMalformedContainer("residueType greater than 2 is not decodable", null);
            }
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            int bits2 = vorbisBitArray.readBits(6) + 1;
            vorbisBitArray.skipBits(8);
            int[] iArr = new int[bits2];
            for (int i11 = 0; i11 < bits2; i11++) {
                iArr[i11] = ((vorbisBitArray.readBit() ? vorbisBitArray.readBits(5) : 0) * 8) + vorbisBitArray.readBits(3);
            }
            for (int i12 = 0; i12 < bits2; i12++) {
                for (int i13 = 0; i13 < 8; i13++) {
                    if ((iArr[i12] & (1 << i13)) != 0) {
                        vorbisBitArray.skipBits(8);
                    }
                }
            }
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray) throws ParserException {
        return readVorbisCommentHeader(parsableByteArray, true, true);
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray parsableByteArray) throws ParserException {
        verifyVorbisHeaderCapturePattern(1, parsableByteArray, false);
        int littleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int littleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        if (littleEndianInt <= 0) {
            littleEndianInt = -1;
        }
        int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
        if (littleEndianInt2 <= 0) {
            littleEndianInt2 = -1;
        }
        int littleEndianInt3 = parsableByteArray.readLittleEndianInt();
        if (littleEndianInt3 <= 0) {
            littleEndianInt3 = -1;
        }
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        return new VorbisIdHeader(littleEndianUnsignedIntToInt, unsignedByte, littleEndianUnsignedIntToInt2, littleEndianInt, littleEndianInt2, littleEndianInt3, (int) Math.pow(2.0d, unsignedByte2 & 15), (int) Math.pow(2.0d, (unsignedByte2 & PsExtractor.VIDEO_STREAM_MASK) >> 4), (parsableByteArray.readUnsignedByte() & 1) > 0, Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit()));
    }

    public static Mode[] readVorbisModes(ParsableByteArray parsableByteArray, int i10) throws ParserException {
        verifyVorbisHeaderCapturePattern(5, parsableByteArray, false);
        int unsignedByte = parsableByteArray.readUnsignedByte() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray.getData());
        vorbisBitArray.skipBits(parsableByteArray.getPosition() * 8);
        for (int i11 = 0; i11 < unsignedByte; i11++) {
            skipBook(vorbisBitArray);
        }
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i12 = 0; i12 < bits; i12++) {
            if (vorbisBitArray.readBits(16) != 0) {
                throw ParserException.createForMalformedContainer("placeholder of time domain transforms not zeroed out", null);
            }
        }
        readFloors(vorbisBitArray);
        readResidues(vorbisBitArray);
        readMappings(i10, vorbisBitArray);
        Mode[] modes = readModes(vorbisBitArray);
        if (vorbisBitArray.readBit()) {
            return modes;
        }
        throw ParserException.createForMalformedContainer("framing bit after modes not set as expected", null);
    }

    private static void skipBook(VorbisBitArray vorbisBitArray) throws ParserException {
        if (vorbisBitArray.readBits(24) != 5653314) {
            throw ParserException.createForMalformedContainer("expected code book to start with [0x56, 0x43, 0x42] at " + vorbisBitArray.getPosition(), null);
        }
        int bits = vorbisBitArray.readBits(16);
        int bits2 = vorbisBitArray.readBits(24);
        int bits3 = 0;
        if (vorbisBitArray.readBit()) {
            vorbisBitArray.skipBits(5);
            while (bits3 < bits2) {
                bits3 += vorbisBitArray.readBits(iLog(bits2 - bits3));
            }
        } else {
            boolean bit = vorbisBitArray.readBit();
            while (bits3 < bits2) {
                if (!bit) {
                    vorbisBitArray.skipBits(5);
                } else if (vorbisBitArray.readBit()) {
                    vorbisBitArray.skipBits(5);
                }
                bits3++;
            }
        }
        int bits4 = vorbisBitArray.readBits(4);
        if (bits4 > 2) {
            throw ParserException.createForMalformedContainer("lookup type greater than 2 not decodable: " + bits4, null);
        }
        if (bits4 == 1 || bits4 == 2) {
            vorbisBitArray.skipBits(32);
            vorbisBitArray.skipBits(32);
            int bits5 = vorbisBitArray.readBits(4) + 1;
            vorbisBitArray.skipBits(1);
            vorbisBitArray.skipBits((int) ((bits4 == 1 ? bits != 0 ? mapType1QuantValues(bits2, bits) : 0L : ((long) bits) * ((long) bits2)) * ((long) bits5)));
        }
    }

    public static boolean verifyVorbisHeaderCapturePattern(int i10, ParsableByteArray parsableByteArray, boolean z) throws ParserException {
        if (parsableByteArray.bytesLeft() < 7) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("too short header: " + parsableByteArray.bytesLeft(), null);
        }
        if (parsableByteArray.readUnsignedByte() != i10) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected header type " + Integer.toHexString(i10), null);
        }
        if (parsableByteArray.readUnsignedByte() == 118 && parsableByteArray.readUnsignedByte() == 111 && parsableByteArray.readUnsignedByte() == 114 && parsableByteArray.readUnsignedByte() == 98 && parsableByteArray.readUnsignedByte() == 105 && parsableByteArray.readUnsignedByte() == 115) {
            return true;
        }
        if (z) {
            return false;
        }
        throw ParserException.createForMalformedContainer("expected characters 'vorbis'", null);
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray, boolean z, boolean z5) throws ParserException {
        if (z) {
            verifyVorbisHeaderCapturePattern(3, parsableByteArray, false);
        }
        String string = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
        int length = string.length();
        long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        String[] strArr = new String[(int) littleEndianUnsignedInt];
        int length2 = length + 15;
        for (int i10 = 0; i10 < littleEndianUnsignedInt; i10++) {
            String string2 = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
            strArr[i10] = string2;
            length2 = length2 + 4 + string2.length();
        }
        if (z5 && (parsableByteArray.readUnsignedByte() & 1) == 0) {
            throw ParserException.createForMalformedContainer("framing bit expected to be set", null);
        }
        return new CommentHeader(string, strArr, length2 + 1);
    }
}

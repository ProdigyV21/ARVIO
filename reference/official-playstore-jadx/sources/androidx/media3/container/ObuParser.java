package androidx.media3.container;

import ac.b;
import androidx.media3.common.util.ParsableBitArray;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class ObuParser {
    public static final int OBU_FRAME = 6;
    public static final int OBU_FRAME_HEADER = 3;
    public static final int OBU_METADATA = 5;
    public static final int OBU_PADDING = 15;
    public static final int OBU_SEQUENCE_HEADER = 1;
    public static final int OBU_TEMPORAL_DELIMITER = 2;

    public static final class FrameHeader {
        private static final int FRAME_TYPE_INTRA_ONLY_FRAME = 2;
        private static final int FRAME_TYPE_KEY_FRAME = 0;
        private static final int FRAME_TYPE_SWITCH_FRAME = 3;
        private static final int PROBE_BYTES = 4;
        private final boolean isDependedOn;

        private FrameHeader(SequenceHeader sequenceHeader, Obu obu) throws NotYetImplementedException {
            int i10 = obu.type;
            b.j(i10 == 6 || i10 == 3);
            byte[] bArr = new byte[Math.min(4, obu.payload.remaining())];
            obu.payload.asReadOnlyBuffer().get(bArr);
            ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
            ObuParser.throwWhenFeatureRequired(sequenceHeader.reducedStillPictureHeader);
            if (parsableBitArray.readBit()) {
                this.isDependedOn = false;
                return;
            }
            int bits = parsableBitArray.readBits(2);
            boolean bit = parsableBitArray.readBit();
            ObuParser.throwWhenFeatureRequired(sequenceHeader.decoderModelInfoPresentFlag);
            if (!bit) {
                this.isDependedOn = true;
                return;
            }
            boolean bit2 = (bits == 3 || bits == 0) ? true : parsableBitArray.readBit();
            parsableBitArray.skipBit();
            ObuParser.throwWhenFeatureRequired(!sequenceHeader.seqForceScreenContentTools);
            if (parsableBitArray.readBit()) {
                ObuParser.throwWhenFeatureRequired(!sequenceHeader.seqForceIntegerMv);
                parsableBitArray.skipBit();
            }
            ObuParser.throwWhenFeatureRequired(sequenceHeader.frameIdNumbersPresentFlag);
            if (bits != 3) {
                parsableBitArray.skipBit();
            }
            parsableBitArray.skipBits(sequenceHeader.orderHintBits);
            if (bits != 2 && bits != 0 && !bit2) {
                parsableBitArray.skipBits(3);
            }
            this.isDependedOn = ((bits == 3 || bits == 0) ? 255 : parsableBitArray.readBits(8)) != 0;
        }

        public static FrameHeader parse(SequenceHeader sequenceHeader, Obu obu) {
            try {
                return new FrameHeader(sequenceHeader, obu);
            } catch (NotYetImplementedException unused) {
                return null;
            }
        }

        public boolean isDependedOn() {
            return this.isDependedOn;
        }
    }

    public static class NotYetImplementedException extends Exception {
        private NotYetImplementedException() {
        }
    }

    public static final class Obu {
        public final ByteBuffer payload;
        public final int type;

        private Obu(int i10, ByteBuffer byteBuffer) {
            this.type = i10;
            this.payload = byteBuffer;
        }
    }

    public static final class SequenceHeader {
        public final int chromaSamplePosition;
        public final byte colorPrimaries;
        public final boolean decoderModelInfoPresentFlag;
        public final boolean frameIdNumbersPresentFlag;
        public final boolean highBitdepth;
        public final int initialDisplayDelayMinus1;
        public final boolean initialDisplayDelayPresentFlag;
        public final byte matrixCoefficients;
        public final boolean monochrome;
        public final int orderHintBits;
        public final boolean reducedStillPictureHeader;
        public final boolean seqForceIntegerMv;
        public final boolean seqForceScreenContentTools;
        public final int seqLevelIdx0;
        public final int seqProfile;
        public final int seqTier0;
        public final boolean subsamplingX;
        public final boolean subsamplingY;
        public final byte transferCharacteristics;
        public final boolean twelveBit;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v4, types: [int] */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6 */
        private SequenceHeader(Obu obu) throws NotYetImplementedException {
            int bits;
            int bits2;
            boolean bit;
            ?? r82;
            b.j(obu.type == 1);
            byte[] bArr = new byte[obu.payload.remaining()];
            obu.payload.asReadOnlyBuffer().get(bArr);
            ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
            this.seqProfile = parsableBitArray.readBits(3);
            parsableBitArray.skipBit();
            boolean bit2 = parsableBitArray.readBit();
            this.reducedStillPictureHeader = bit2;
            if (bit2) {
                bits2 = parsableBitArray.readBits(5);
                this.decoderModelInfoPresentFlag = false;
                this.initialDisplayDelayPresentFlag = false;
                r82 = 0;
                bits = 0;
            } else {
                if (parsableBitArray.readBit()) {
                    skipTimingInfo(parsableBitArray);
                    boolean bit3 = parsableBitArray.readBit();
                    this.decoderModelInfoPresentFlag = bit3;
                    if (bit3) {
                        parsableBitArray.skipBits(47);
                    }
                } else {
                    this.decoderModelInfoPresentFlag = false;
                }
                this.initialDisplayDelayPresentFlag = parsableBitArray.readBit();
                int bits3 = parsableBitArray.readBits(5);
                int bits4 = 0;
                int i10 = 0;
                boolean z = false;
                bits = 0;
                while (i10 <= bits3) {
                    parsableBitArray.skipBits(12);
                    if (i10 == 0) {
                        bits4 = parsableBitArray.readBits(5);
                        bit = z;
                        if (bits4 > 7) {
                            bit = parsableBitArray.readBit();
                        }
                    } else {
                        bit = z;
                        if (parsableBitArray.readBits(5) > 7) {
                            parsableBitArray.skipBit();
                            bit = z;
                        }
                    }
                    if (this.decoderModelInfoPresentFlag) {
                        parsableBitArray.skipBit();
                    }
                    if (this.initialDisplayDelayPresentFlag && parsableBitArray.readBit()) {
                        if (i10 == 0) {
                            bits = parsableBitArray.readBits(4);
                        } else {
                            parsableBitArray.skipBits(4);
                        }
                    }
                    i10++;
                    z = bit;
                }
                bits2 = bits4;
                r82 = z;
            }
            int bits5 = parsableBitArray.readBits(4);
            int bits6 = parsableBitArray.readBits(4);
            parsableBitArray.skipBits(bits5 + 1);
            parsableBitArray.skipBits(bits6 + 1);
            if (this.reducedStillPictureHeader) {
                this.frameIdNumbersPresentFlag = false;
            } else {
                this.frameIdNumbersPresentFlag = parsableBitArray.readBit();
            }
            if (this.frameIdNumbersPresentFlag) {
                parsableBitArray.skipBits(4);
                parsableBitArray.skipBits(3);
            }
            parsableBitArray.skipBits(3);
            if (this.reducedStillPictureHeader) {
                this.seqForceIntegerMv = true;
                this.seqForceScreenContentTools = true;
                this.orderHintBits = 0;
            } else {
                parsableBitArray.skipBits(4);
                boolean bit4 = parsableBitArray.readBit();
                if (bit4) {
                    parsableBitArray.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    this.seqForceScreenContentTools = true;
                } else {
                    this.seqForceScreenContentTools = parsableBitArray.readBit();
                }
                if (!this.seqForceScreenContentTools || parsableBitArray.readBit()) {
                    this.seqForceIntegerMv = true;
                } else {
                    this.seqForceIntegerMv = parsableBitArray.readBit();
                }
                if (bit4) {
                    this.orderHintBits = parsableBitArray.readBits(3) + 1;
                } else {
                    this.orderHintBits = 0;
                }
            }
            this.seqLevelIdx0 = bits2;
            this.seqTier0 = r82;
            this.initialDisplayDelayMinus1 = bits;
            parsableBitArray.skipBits(3);
            boolean bit5 = parsableBitArray.readBit();
            this.highBitdepth = bit5;
            if (this.seqProfile == 2 && bit5) {
                this.twelveBit = parsableBitArray.readBit();
            } else {
                this.twelveBit = false;
            }
            if (this.seqProfile != 1) {
                this.monochrome = parsableBitArray.readBit();
            } else {
                this.monochrome = false;
            }
            if (parsableBitArray.readBit()) {
                this.colorPrimaries = (byte) parsableBitArray.readBits(8);
                this.transferCharacteristics = (byte) parsableBitArray.readBits(8);
                this.matrixCoefficients = (byte) parsableBitArray.readBits(8);
            } else {
                this.colorPrimaries = (byte) 0;
                this.transferCharacteristics = (byte) 0;
                this.matrixCoefficients = (byte) 0;
            }
            if (this.monochrome) {
                parsableBitArray.skipBit();
                this.subsamplingX = false;
                this.subsamplingY = false;
                this.chromaSamplePosition = 0;
            } else if (this.colorPrimaries == 1 && this.transferCharacteristics == 13 && this.matrixCoefficients == 0) {
                this.subsamplingX = false;
                this.subsamplingY = false;
                this.chromaSamplePosition = 0;
            } else {
                parsableBitArray.skipBit();
                int i11 = this.seqProfile;
                if (i11 == 0) {
                    this.subsamplingX = true;
                    this.subsamplingY = true;
                } else if (i11 == 1) {
                    this.subsamplingX = false;
                    this.subsamplingY = false;
                } else if (this.twelveBit) {
                    boolean bit6 = parsableBitArray.readBit();
                    this.subsamplingX = bit6;
                    if (bit6) {
                        this.subsamplingY = parsableBitArray.readBit();
                    } else {
                        this.subsamplingY = false;
                    }
                } else {
                    this.subsamplingX = true;
                    this.subsamplingY = false;
                }
                if (this.subsamplingX && this.subsamplingY) {
                    this.chromaSamplePosition = parsableBitArray.readBits(2);
                } else {
                    this.chromaSamplePosition = 0;
                }
            }
            parsableBitArray.skipBit();
        }

        public static SequenceHeader parse(Obu obu) {
            try {
                return new SequenceHeader(obu);
            } catch (NotYetImplementedException unused) {
                return null;
            }
        }

        private static void skipTimingInfo(ParsableBitArray parsableBitArray) {
            parsableBitArray.skipBits(64);
            if (parsableBitArray.readBit()) {
                ObuParser.skipUvlc(parsableBitArray);
            }
        }
    }

    private ObuParser() {
    }

    private static int leb128(ByteBuffer byteBuffer) {
        int i10 = 0;
        for (int i11 = 0; i11 < 8; i11++) {
            byte b10 = byteBuffer.get();
            i10 |= (b10 & 127) << (i11 * 7);
            if ((b10 & 128) == 0) {
                return i10;
            }
        }
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void skipUvlc(ParsableBitArray parsableBitArray) {
        int i10 = 0;
        while (!parsableBitArray.readBit()) {
            i10++;
        }
        if (i10 < 32) {
            parsableBitArray.skipBits(i10);
        }
    }

    public static List<Obu> split(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        ArrayList arrayList = new ArrayList();
        while (byteBufferAsReadOnlyBuffer.hasRemaining()) {
            try {
                byte b10 = byteBufferAsReadOnlyBuffer.get();
                int i10 = (b10 >> 3) & 15;
                if (((b10 >> 2) & 1) != 0) {
                    byteBufferAsReadOnlyBuffer.get();
                }
                int iLeb128 = ((b10 >> 1) & 1) != 0 ? leb128(byteBufferAsReadOnlyBuffer) : byteBufferAsReadOnlyBuffer.remaining();
                if (byteBufferAsReadOnlyBuffer.position() + iLeb128 > byteBufferAsReadOnlyBuffer.limit()) {
                    break;
                }
                ByteBuffer byteBufferDuplicate = byteBufferAsReadOnlyBuffer.duplicate();
                byteBufferDuplicate.limit(byteBufferAsReadOnlyBuffer.position() + iLeb128);
                arrayList.add(new Obu(i10, byteBufferDuplicate));
                byteBufferAsReadOnlyBuffer.position(byteBufferAsReadOnlyBuffer.position() + iLeb128);
            } catch (BufferUnderflowException unused) {
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void throwWhenFeatureRequired(boolean z) throws NotYetImplementedException {
        if (z) {
            throw new NotYetImplementedException();
        }
    }
}

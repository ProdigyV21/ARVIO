package androidx.media3.container;

import ac.b;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.CodecSpecificDataUtil;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.math.c;
import j$.util.Objects;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    public static final int H264_NAL_UNIT_TYPE_AUD = 9;
    public static final int H264_NAL_UNIT_TYPE_IDR = 5;
    public static final int H264_NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int H264_NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int H264_NAL_UNIT_TYPE_PPS = 8;
    public static final int H264_NAL_UNIT_TYPE_PREFIX = 14;
    public static final int H264_NAL_UNIT_TYPE_SEI = 6;
    public static final int H264_NAL_UNIT_TYPE_SPS = 7;
    public static final int H264_NAL_UNIT_TYPE_UNSPECIFIED = 24;
    public static final int H265_NAL_UNIT_TYPE_AUD = 35;
    public static final int H265_NAL_UNIT_TYPE_BLA_W_LP = 16;
    public static final int H265_NAL_UNIT_TYPE_CRA = 21;
    public static final int H265_NAL_UNIT_TYPE_PPS = 34;
    public static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int H265_NAL_UNIT_TYPE_RASL_R = 9;
    public static final int H265_NAL_UNIT_TYPE_SPS = 33;
    public static final int H265_NAL_UNIT_TYPE_SUFFIX_SEI = 40;
    public static final int H265_NAL_UNIT_TYPE_UNSPECIFIED = 48;
    public static final int H265_NAL_UNIT_TYPE_VPS = 32;
    private static final int INVALID_ID = -1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_AUD = 9;

    @Deprecated
    public static final int NAL_UNIT_TYPE_IDR = 5;

    @Deprecated
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PPS = 8;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PREFIX = 14;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SEI = 6;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int i10, int i11) {
            this.layerIdInVps = i10;
            this.viewId = i11;
        }
    }

    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int i10, int i11, int i12) {
            this.nalUnitType = i10;
            this.layerId = i11;
            this.temporalId = i12;
        }
    }

    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int i10, boolean z, int i11, int i12, int[] iArr, int i13) {
            this.generalProfileSpace = i10;
            this.generalTierFlag = z;
            this.generalProfileIdc = i11;
            this.generalProfileCompatibilityFlags = i12;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i13;
        }
    }

    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final h1 profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> list, int[] iArr) {
            this.profileTierLevels = h1.n(list);
            this.indices = iArr;
        }
    }

    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int i10, int i11, int i12, int i13, int i14) {
            this.chromaFormatIdc = i10;
            this.bitDepthLumaMinus8 = i11;
            this.bitDepthChromaMinus8 = i12;
            this.width = i13;
            this.height = i14;
        }
    }

    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final h1 repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> list, int[] iArr) {
            this.repFormats = h1.n(list);
            this.indices = iArr;
        }
    }

    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
            this.precRefDisplayWidth = i10;
            this.precRefViewingDist = i11;
            this.numRefDisplays = i12;
            this.leftViewId = i13;
            this.rightViewId = i14;
            this.exponentRefDisplayWidth = i15;
            this.mantissaRefDisplayWidth = i16;
            this.exponentRefViewingDist = i17;
            this.mantissaRefViewingDist = i18;
        }
    }

    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int decodedHeight;
        public final int decodedWidth;
        public final int height;
        public final int maxNumReorderPics;
        public final int maxSubLayersMinus1;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader h265NalHeader, int i10, H265ProfileTierLevel h265ProfileTierLevel, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, float f10, int i19, int i20, int i21, int i22) {
            this.nalHeader = h265NalHeader;
            this.maxSubLayersMinus1 = i10;
            this.profileTierLevel = h265ProfileTierLevel;
            this.chromaFormatIdc = i11;
            this.bitDepthLumaMinus8 = i12;
            this.bitDepthChromaMinus8 = i13;
            this.seqParameterSetId = i14;
            this.width = i15;
            this.height = i16;
            this.pixelWidthHeightRatio = f10;
            this.maxNumReorderPics = i19;
            this.colorSpace = i20;
            this.colorRange = i21;
            this.colorTransfer = i22;
            this.decodedWidth = i17;
            this.decodedHeight = i18;
        }
    }

    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int i10, int i11, int i12) {
            this.colorSpace = i10;
            this.colorRange = i11;
            this.colorTransfer = i12;
        }
    }

    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final h1 videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> list, int[] iArr) {
            this.videoSignalInfos = h1.n(list);
            this.indices = iArr;
        }
    }

    public static final class H265VpsData {
        public final h1 layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader h265NalHeader, List<H265LayerInfo> list, H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices, H265RepFormatsAndIndices h265RepFormatsAndIndices, H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices) {
            h1 h1VarN;
            this.nalHeader = h265NalHeader;
            if (list != null) {
                h1VarN = h1.n(list);
            } else {
                f1 f1Var = h1.f14020l;
                h1VarN = o3.f14078o;
            }
            this.layerInfos = h1VarN;
            this.profileTierLevelsAndIndices = h265ProfileTierLevelsAndIndices;
            this.repFormatsAndIndices = h265RepFormatsAndIndices;
            this.videoSignalInfosAndIndices = h265VideoSignalInfosAndIndices;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i10, int i11, boolean z) {
            this.picParameterSetId = i10;
            this.seqParameterSetId = i11;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i10, int i11, int i12, int i13, int i14, int i15, int i16, float f10, int i17, int i18, boolean z, boolean z5, int i19, int i20, int i21, boolean z10, int i22, int i23, int i24, int i25) {
            this.profileIdc = i10;
            this.constraintsFlagsAndReservedZero2Bits = i11;
            this.levelIdc = i12;
            this.seqParameterSetId = i13;
            this.maxNumRefFrames = i14;
            this.width = i15;
            this.height = i16;
            this.pixelWidthHeightRatio = f10;
            this.bitDepthLumaMinus8 = i17;
            this.bitDepthChromaMinus8 = i18;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z5;
            this.frameNumLength = i19;
            this.picOrderCountType = i20;
            this.picOrderCntLsbLength = i21;
            this.deltaPicOrderAlwaysZeroFlag = z10;
            this.colorSpace = i22;
            this.colorRange = i23;
            this.colorTransfer = i24;
            this.maxNumReorderFrames = i25;
        }
    }

    private NalUnitUtil() {
    }

    private static int applyConformanceWindowToHeight(int i10, int i11, int i12, int i13) {
        return i10 - ((i12 + i13) * (i11 == 1 ? 2 : 1));
    }

    private static int applyConformanceWindowToWidth(int i10, int i11, int i12, int i13) {
        int i14 = 2;
        if (i11 != 1 && i11 != 2) {
            i14 = 1;
        }
        return i10 - ((i12 + i13) * i14);
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(4);
        int bits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        H265ProfileTierLevel h265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, bits, null);
        return CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc);
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = i10 + 1;
            if (i12 >= iPosition) {
                byteBuffer.clear();
                return;
            }
            int i13 = byteBuffer.get(i10) & 255;
            if (i11 == 3) {
                if (i13 == 1 && (byteBuffer.get(i12) & 31) == 7) {
                    ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                    byteBufferDuplicate.position(i10 - 3);
                    byteBufferDuplicate.limit(iPosition);
                    byteBuffer.position(0);
                    byteBuffer.put(byteBufferDuplicate);
                    return;
                }
            } else if (i13 == 0) {
                i11++;
            }
            if (i13 != 0) {
                i11 = 0;
            }
            i10 = i12;
        }
    }

    public static int findNalUnit(byte[] bArr, int i10, int i11, boolean[] zArr) {
        int i12 = i11 - i10;
        b.s(i12 >= 0);
        if (i12 == 0) {
            return i11;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i10 - 3;
        }
        if (i12 > 1 && zArr[1] && bArr[i10] == 1) {
            clearPrefixFlags(zArr);
            return i10 - 2;
        }
        if (i12 > 2 && zArr[2] && bArr[i10] == 0 && bArr[i10 + 1] == 1) {
            clearPrefixFlags(zArr);
            return i10 - 1;
        }
        int i13 = i11 - 1;
        int i14 = i10 + 2;
        while (i14 < i13) {
            byte b10 = bArr[i14];
            if ((b10 & 254) == 0) {
                int i15 = i14 - 2;
                if (bArr[i15] == 0 && bArr[i14 - 1] == 0 && b10 == 1) {
                    clearPrefixFlags(zArr);
                    return i15;
                }
                i14 -= 2;
            }
            i14 += 3;
        }
        zArr[0] = i12 <= 2 ? !(i12 != 2 ? !(zArr[1] && bArr[i13] == 1) : !(zArr[2] && bArr[i11 + (-2)] == 0 && bArr[i13] == 1)) : bArr[i11 + (-3)] == 0 && bArr[i11 + (-2)] == 0 && bArr[i13] == 1;
        zArr[1] = i12 <= 1 ? zArr[2] && bArr[i13] == 0 : bArr[i11 + (-2)] == 0 && bArr[i13] == 0;
        zArr[2] = bArr[i13] == 0;
        return i11;
    }

    private static h1 findNalUnitPositions(byte[] bArr) {
        boolean[] zArr = new boolean[3];
        e1 e1VarK = h1.k();
        int i10 = 0;
        while (i10 < bArr.length) {
            int iFindNalUnit = findNalUnit(bArr, i10, bArr.length, zArr);
            if (iFindNalUnit != bArr.length) {
                e1VarK.c(Integer.valueOf(iFindNalUnit));
            }
            i10 = iFindNalUnit + 3;
        }
        return e1VarK.f();
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i10, int i11) {
        while (i10 < i11 - 2) {
            if (bArr[i10] == 0 && bArr[i10 + 1] == 0 && bArr[i10 + 2] == 3) {
                return i10;
            }
            i10++;
        }
        return i11;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            byte[] bArr = list.get(i10);
            int length = bArr.length;
            if (length > 3) {
                h1 h1VarFindNalUnitPositions = findNalUnitPositions(bArr);
                for (int i11 = 0; i11 < h1VarFindNalUnitPositions.size(); i11++) {
                    if (((Integer) h1VarFindNalUnitPositions.get(i11)).intValue() + 3 < length) {
                        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, ((Integer) h1VarFindNalUnitPositions.get(i11)).intValue() + 3, length);
                        H265NalHeader h265NalHeader = parseH265NalHeader(parsableNalUnitBitArray);
                        if (h265NalHeader.nalUnitType == 33 && h265NalHeader.layerId == 0) {
                            return createCodecStringFromH265SpsPalyoad(parsableNalUnitBitArray);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static int getH265NalUnitType(byte[] bArr, int i10) {
        return (bArr[i10 + 3] & 126) >> 1;
    }

    private static String getNalStructureMimeType(Format format) {
        String str;
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_DOLBY_VISION) && (str = format.codecs) != null) {
            if (str.startsWith("dva1") || format.codecs.startsWith("dvav")) {
                return MimeTypes.VIDEO_H264;
            }
            if (format.codecs.startsWith("dvh1") || format.codecs.startsWith("dvhe")) {
                return MimeTypes.VIDEO_H265;
            }
        }
        return format.sampleMimeType;
    }

    public static int getNalUnitType(byte[] bArr, int i10) {
        return bArr[i10 + 3] & 31;
    }

    public static boolean isDependedOn(byte[] bArr, int i10, int i11, Format format) {
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264)) {
            return isH264NalUnitDependedOn(bArr[i10]);
        }
        if (Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265)) {
            return isH265NalUnitDependedOn(bArr, i10, i11, format);
        }
        return true;
    }

    public static boolean isH264NalUnitDependedOn(byte b10) {
        if (((b10 & 96) >> 5) != 0) {
            return true;
        }
        int i10 = b10 & 31;
        return (i10 == 1 || i10 == 9 || i10 == 14) ? false : true;
    }

    private static boolean isH265NalUnitDependedOn(byte[] bArr, int i10, int i11, Format format) {
        H265NalHeader h265NalHeader = parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i10, i11 + i10));
        int i12 = h265NalHeader.nalUnitType;
        if (i12 == 35) {
            return false;
        }
        return (i12 <= 14 && i12 % 2 == 0 && h265NalHeader.temporalId == format.maxSubLayers - 1) ? false : true;
    }

    @Deprecated
    public static boolean isNalUnitSei(String str, byte b10) {
        return (MimeTypes.VIDEO_H264.equals(str) && (b10 & 31) == 6) || (MimeTypes.VIDEO_H265.equals(str) && ((b10 & 126) >> 1) == 39);
    }

    public static int numberOfBytesInNalUnitHeader(Format format) {
        String nalStructureMimeType = getNalStructureMimeType(format);
        if (Objects.equals(nalStructureMimeType, MimeTypes.VIDEO_H264)) {
            return 1;
        }
        return Objects.equals(nalStructureMimeType, MimeTypes.VIDEO_H265) ? 2 : 0;
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBit();
        return new H265NalHeader(parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(3) - 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.container.NalUnitUtil.H265ProfileTierLevel parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray r19, boolean r20, int r21, androidx.media3.container.NalUnitUtil.H265ProfileTierLevel r22) {
        /*
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = 6
            int[] r4 = new int[r3]
            r5 = 2
            r6 = 8
            r7 = 0
            if (r20 == 0) goto L42
            int r2 = r0.readBits(r5)
            boolean r8 = r0.readBit()
            r9 = 5
            int r9 = r0.readBits(r9)
            r10 = r7
            r11 = r10
        L1e:
            r12 = 32
            if (r10 >= r12) goto L2e
            boolean r12 = r0.readBit()
            if (r12 == 0) goto L2b
            r12 = 1
            int r12 = r12 << r10
            r11 = r11 | r12
        L2b:
            int r10 = r10 + 1
            goto L1e
        L2e:
            r10 = r7
        L2f:
            if (r10 >= r3) goto L3a
            int r12 = r0.readBits(r6)
            r4[r10] = r12
            int r10 = r10 + 1
            goto L2f
        L3a:
            r13 = r2
        L3b:
            r17 = r4
            r14 = r8
            r15 = r9
            r16 = r11
            goto L57
        L42:
            if (r2 == 0) goto L50
            int r3 = r2.generalProfileSpace
            boolean r8 = r2.generalTierFlag
            int r9 = r2.generalProfileIdc
            int r11 = r2.generalProfileCompatibilityFlags
            int[] r4 = r2.constraintBytes
            r13 = r3
            goto L3b
        L50:
            r17 = r4
            r13 = r7
            r14 = r13
            r15 = r14
            r16 = r15
        L57:
            int r18 = r0.readBits(r6)
            r2 = r7
        L5c:
            if (r7 >= r1) goto L71
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L66
            int r2 = r2 + 88
        L66:
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L6e
            int r2 = r2 + 8
        L6e:
            int r7 = r7 + 1
            goto L5c
        L71:
            r0.skipBits(r2)
            if (r1 <= 0) goto L7b
            int r6 = r6 - r1
            int r6 = r6 * r5
            r0.skipBits(r6)
        L7b:
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r12 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevel
            r12.<init>(r13, r14, r15, r16, r17, r18)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray, boolean, int, androidx.media3.container.NalUnitUtil$H265ProfileTierLevel):androidx.media3.container.NalUnitUtil$H265ProfileTierLevel");
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i10;
        int i11;
        int bits;
        int bits2 = parsableNalUnitBitArray.readBits(16);
        int bits3 = parsableNalUnitBitArray.readBits(16);
        if (parsableNalUnitBitArray.readBit()) {
            int bits4 = parsableNalUnitBitArray.readBits(2);
            if (bits4 == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int bits5 = parsableNalUnitBitArray.readBits(4);
            bits = parsableNalUnitBitArray.readBits(4);
            i11 = bits5;
            i10 = bits4;
        } else {
            i10 = 0;
            i11 = 0;
            bits = 0;
        }
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            bits2 = applyConformanceWindowToWidth(bits2, i10, unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2);
            bits3 = applyConformanceWindowToHeight(bits3, i10, unsignedExpGolombCodedInt3, unsignedExpGolombCodedInt4);
        }
        return new H265RepFormat(i10, i11, bits, bits2, bits3);
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i10) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i11 = unsignedExpGolombCodedInt + 1;
        e1 e1VarM = h1.m(i11);
        int[] iArr = new int[i10];
        for (int i12 = 0; i12 < i11; i12++) {
            e1VarM.c(parseH265RepFormat(parsableNalUnitBitArray));
        }
        int i13 = 1;
        if (i11 <= 1 || !parsableNalUnitBitArray.readBit()) {
            while (i13 < i10) {
                iArr[i13] = Math.min(i13, unsignedExpGolombCodedInt);
                i13++;
            }
        } else {
            RoundingMode roundingMode = RoundingMode.CEILING;
            int iC = c.c(i11);
            while (i13 < i10) {
                iArr[i13] = parsableNalUnitBitArray.readBits(iC);
                i13++;
            }
        }
        return new H265RepFormatsAndIndices(e1VarM.f(), iArr);
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] bArr, int i10, int i11) {
        byte b10;
        int i12 = i10 + 2;
        int i13 = i11 - 1;
        while (true) {
            b10 = bArr[i13];
            if (b10 != 0 || i13 <= i12) {
                break;
            }
            i13--;
        }
        if (b10 != 0 && i13 > i12) {
            ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i12, i13 + 1);
            while (parsableNalUnitBitArray.canReadBits(16)) {
                int bits = parsableNalUnitBitArray.readBits(8);
                int i14 = 0;
                while (bits == 255) {
                    i14 += 255;
                    bits = parsableNalUnitBitArray.readBits(8);
                }
                int i15 = i14 + bits;
                int bits2 = parsableNalUnitBitArray.readBits(8);
                int i16 = 0;
                while (bits2 == 255) {
                    i16 += 255;
                    bits2 = parsableNalUnitBitArray.readBits(8);
                }
                int i17 = i16 + bits2;
                if (i17 == 0 || !parsableNalUnitBitArray.canReadBits(i17)) {
                    break;
                }
                if (i15 == 176) {
                    int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    boolean bit = parsableNalUnitBitArray.readBit();
                    int unsignedExpGolombCodedInt2 = bit ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
                    int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int unsignedExpGolombCodedInt4 = -1;
                    int unsignedExpGolombCodedInt5 = -1;
                    int bits3 = -1;
                    int bits4 = -1;
                    int i18 = -1;
                    int bits5 = -1;
                    for (int i19 = 0; i19 <= unsignedExpGolombCodedInt3; i19++) {
                        unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        bits3 = parsableNalUnitBitArray.readBits(6);
                        if (bits3 == 63) {
                            return null;
                        }
                        bits4 = parsableNalUnitBitArray.readBits(bits3 == 0 ? Math.max(0, unsignedExpGolombCodedInt - 30) : Math.max(0, (bits3 + unsignedExpGolombCodedInt) - 31));
                        if (bit) {
                            int bits6 = parsableNalUnitBitArray.readBits(6);
                            if (bits6 == 63) {
                                return null;
                            }
                            i18 = bits6;
                            bits5 = parsableNalUnitBitArray.readBits(bits6 == 0 ? Math.max(0, unsignedExpGolombCodedInt2 - 30) : Math.max(0, (bits6 + unsignedExpGolombCodedInt2) - 31));
                        }
                        if (parsableNalUnitBitArray.readBit()) {
                            parsableNalUnitBitArray.skipBits(10);
                        }
                    }
                    return new H265Sei3dRefDisplayInfoData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, unsignedExpGolombCodedInt3 + 1, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, bits3, bits4, i18, bits5);
                }
                parsableNalUnitBitArray.skipBits(i17 * 8);
            }
        }
        return null;
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i10, int i11, H265VpsData h265VpsData) {
        return parseH265SpsNalUnitPayload(bArr, i10 + 2, i11, parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i10, i11)), h265VpsData);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.container.NalUnitUtil.H265SpsData parseH265SpsNalUnitPayload(byte[] r20, int r21, int r22, androidx.media3.container.NalUnitUtil.H265NalHeader r23, androidx.media3.container.NalUnitUtil.H265VpsData r24) {
        /*
            Method dump skipped, instruction units count: 617
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265SpsNalUnitPayload(byte[], int, int, androidx.media3.container.NalUnitUtil$H265NalHeader, androidx.media3.container.NalUnitUtil$H265VpsData):androidx.media3.container.NalUnitUtil$H265SpsData");
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(3);
        int i10 = parsableNalUnitBitArray.readBit() ? 1 : 2;
        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(parsableNalUnitBitArray.readBits(8));
        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(parsableNalUnitBitArray.readBits(8));
        parsableNalUnitBitArray.skipBits(8);
        return new H265VideoSignalInfo(iIsoColorPrimariesToColorSpace, i10, iIsoTransferCharacteristicsToColorTransfer);
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i10, int i11, int[] iArr) {
        if (!parsableNalUnitBitArray.readBit() ? parsableNalUnitBitArray.readBit() : true) {
            parsableNalUnitBitArray.skipBit();
        }
        boolean bit = parsableNalUnitBitArray.readBit();
        boolean bit2 = parsableNalUnitBitArray.readBit();
        if (bit || bit2) {
            for (int i12 = 0; i12 < i11; i12++) {
                for (int i13 = 0; i13 < iArr[i12]; i13++) {
                    boolean bit3 = bit ? parsableNalUnitBitArray.readBit() : false;
                    boolean bit4 = bit2 ? parsableNalUnitBitArray.readBit() : false;
                    if (bit3) {
                        parsableNalUnitBitArray.skipBits(32);
                    }
                    if (bit4) {
                        parsableNalUnitBitArray.skipBits(18);
                    }
                }
            }
        }
        boolean bit5 = parsableNalUnitBitArray.readBit();
        int bits = bit5 ? parsableNalUnitBitArray.readBits(4) + 1 : i10;
        e1 e1VarM = h1.m(bits);
        int[] iArr2 = new int[i10];
        for (int i14 = 0; i14 < bits; i14++) {
            e1VarM.c(parseH265VideoSignalInfo(parsableNalUnitBitArray));
        }
        if (bit5 && bits > 1) {
            for (int i15 = 0; i15 < i10; i15++) {
                iArr2[i15] = parsableNalUnitBitArray.readBits(4);
            }
        }
        return new H265VideoSignalInfosAndIndices(e1VarM.f(), iArr2);
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] bArr, int i10, int i11) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i10, i11);
        return parseH265VpsNalUnitPayload(parsableNalUnitBitArray, parseH265NalHeader(parsableNalUnitBitArray));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static H265VpsData parseH265VpsNalUnitPayload(ParsableNalUnitBitArray parsableNalUnitBitArray, H265NalHeader h265NalHeader) {
        int[] iArr;
        int i10;
        int i11;
        int[] iArr2;
        H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices;
        int i12;
        int i13;
        int i14;
        int[] iArr3;
        o3 o3Var;
        int i15;
        boolean[][] zArr;
        int[] iArr4;
        int i16;
        int i17;
        parsableNalUnitBitArray.skipBits(4);
        boolean bit = parsableNalUnitBitArray.readBit();
        boolean bit2 = parsableNalUnitBitArray.readBit();
        int bits = parsableNalUnitBitArray.readBits(6);
        int i18 = bits + 1;
        int bits2 = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBits(17);
        H265ProfileTierLevel h265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, bits2, null);
        boolean z = false;
        for (int i19 = parsableNalUnitBitArray.readBit() ? 0 : bits2; i19 <= bits2; i19++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        int bits3 = parsableNalUnitBitArray.readBits(6);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices = new H265ProfileTierLevelsAndIndices(h1.s(h265ProfileTierLevel), new int[1]);
        Object[] objArr = i18 >= 2 && unsignedExpGolombCodedInt >= 2;
        Object[] objArr2 = bit && bit2;
        int i20 = bits3 + 1;
        Object[] objArr3 = i20 >= i18;
        if (objArr != true || objArr2 != true || objArr3 != true) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        Class cls = Integer.TYPE;
        int[][] iArr5 = (int[][]) Array.newInstance((Class<?>) cls, unsignedExpGolombCodedInt, i20);
        int i21 = 1;
        int[] iArr6 = new int[unsignedExpGolombCodedInt];
        int[] iArr7 = new int[unsignedExpGolombCodedInt];
        iArr5[0][0] = 0;
        iArr6[0] = 1;
        iArr7[0] = 0;
        for (int i22 = 1; i22 < unsignedExpGolombCodedInt; i22++) {
            int i23 = 0;
            for (int i24 = 0; i24 <= bits3; i24++) {
                if (parsableNalUnitBitArray.readBit()) {
                    iArr5[i22][i23] = i24;
                    iArr7[i22] = i24;
                    i23++;
                }
                iArr6[i22] = i23;
            }
        }
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(64);
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i25 = 0;
            while (i25 < unsignedExpGolombCodedInt2) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                if (i25 == 0 || parsableNalUnitBitArray.readBit()) {
                    z = true;
                }
                skipH265HrdParameters(parsableNalUnitBitArray, z, bits2);
                i25++;
                z = false;
            }
        }
        if (!parsableNalUnitBitArray.readBit()) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        parsableNalUnitBitArray.byteAlign();
        H265ProfileTierLevel h265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, false, bits2, h265ProfileTierLevel);
        boolean bit3 = parsableNalUnitBitArray.readBit();
        int i26 = 6;
        boolean[] zArr2 = new boolean[16];
        int i27 = 0;
        for (int i28 = 0; i28 < 16; i28++) {
            boolean bit4 = parsableNalUnitBitArray.readBit();
            zArr2[i28] = bit4;
            if (bit4) {
                i27++;
            }
        }
        if (i27 == 0 || !zArr2[1]) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr8 = new int[i27];
        for (int i29 = 0; i29 < i27 - (bit3 ? 1 : 0); i29++) {
            iArr8[i29] = parsableNalUnitBitArray.readBits(3);
        }
        int[] iArr9 = new int[i27 + 1];
        if (bit3) {
            int i30 = 1;
            while (i30 < i27) {
                int[] iArr10 = iArr9;
                for (int i31 = 0; i31 < i30; i31++) {
                    iArr10[i30] = iArr8[i31] + 1 + iArr10[i30];
                }
                i30++;
                iArr9 = iArr10;
            }
            iArr = iArr9;
            iArr[i27] = 6;
        } else {
            iArr = iArr9;
        }
        int[][] iArr11 = (int[][]) Array.newInstance((Class<?>) cls, i18, i27);
        int[] iArr12 = new int[i18];
        iArr12[0] = 0;
        boolean bit5 = parsableNalUnitBitArray.readBit();
        int i32 = 1;
        while (i32 < i18) {
            if (bit5) {
                i17 = i32;
                iArr12[i17] = parsableNalUnitBitArray.readBits(i26);
            } else {
                i17 = i32;
                iArr12[i17] = i17;
            }
            if (bit3) {
                for (int i33 = 0; i33 < i27; i33++) {
                    iArr11[i17][i33] = (iArr12[i17] & ((1 << iArr[r33]) - 1)) >> iArr[i33];
                }
            } else {
                int i34 = 0;
                while (i34 < i27) {
                    int i35 = i34;
                    iArr11[i17][i35] = parsableNalUnitBitArray.readBits(iArr8[i34] + 1);
                    i34 = i35 + 1;
                }
            }
            i32 = i17 + 1;
            i26 = 6;
        }
        int[] iArr13 = new int[i20];
        int i36 = 1;
        int i37 = 0;
        while (i37 < i18) {
            iArr13[iArr12[i37]] = -1;
            int[] iArr14 = iArr13;
            int i38 = 0;
            int i39 = 0;
            while (i38 < 16) {
                if (zArr2[i38]) {
                    if (i38 == i21) {
                        iArr14[iArr12[i37]] = iArr11[i37][i39];
                    }
                    i39++;
                }
                i38++;
                i21 = 1;
            }
            if (i37 > 0) {
                int i40 = 0;
                while (true) {
                    if (i40 >= i37) {
                        i36++;
                        break;
                    }
                    int i41 = i40;
                    if (iArr14[iArr12[i37]] == iArr14[iArr12[i40]]) {
                        break;
                    }
                    i40 = i41 + 1;
                }
            }
            i37++;
            iArr13 = iArr14;
            i21 = 1;
        }
        int[] iArr15 = iArr13;
        int bits4 = parsableNalUnitBitArray.readBits(4);
        if (i36 < 2 || bits4 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr16 = new int[i36];
        for (int i42 = 0; i42 < i36; i42++) {
            iArr16[i42] = parsableNalUnitBitArray.readBits(bits4);
        }
        int[] iArr17 = new int[i20];
        int i43 = 0;
        while (i43 < i18) {
            int[] iArr18 = iArr17;
            iArr18[Math.min(iArr12[i43], bits3)] = i43;
            i43++;
            iArr17 = iArr18;
        }
        int[] iArr19 = iArr17;
        e1 e1VarK = h1.k();
        int i44 = 0;
        while (i44 <= bits3) {
            int i45 = i36;
            int[] iArr20 = iArr7;
            int iMin = Math.min(iArr15[i44], i45 - 1);
            e1VarK.c(new H265LayerInfo(iArr19[i44], iMin >= 0 ? iArr16[iMin] : -1));
            i44++;
            i36 = i45;
            iArr7 = iArr20;
            iArr16 = iArr16;
        }
        int[] iArr21 = iArr7;
        o3 o3VarF = e1VarK.f();
        if (((H265LayerInfo) o3VarF.get(0)).viewId == -1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int i46 = 1;
        while (true) {
            if (i46 > bits3) {
                i10 = -1;
                i11 = -1;
                break;
            }
            i10 = -1;
            if (((H265LayerInfo) o3VarF.get(i46)).viewId != -1) {
                i11 = i46;
                break;
            }
            i46++;
        }
        if (i11 == i10) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        Class cls2 = Boolean.TYPE;
        boolean[][] zArr3 = (boolean[][]) Array.newInstance((Class<?>) cls2, i18, i18);
        boolean[][] zArr4 = (boolean[][]) Array.newInstance((Class<?>) cls2, i18, i18);
        int i47 = 1;
        while (i47 < i18) {
            boolean[][] zArr5 = zArr4;
            for (int i48 = 0; i48 < i47; i48++) {
                boolean[] zArr6 = zArr3[i47];
                boolean[] zArr7 = zArr5[i47];
                boolean bit6 = parsableNalUnitBitArray.readBit();
                zArr7[i48] = bit6;
                zArr6[i48] = bit6;
            }
            i47++;
            zArr4 = zArr5;
        }
        boolean[][] zArr8 = zArr4;
        for (int i49 = 1; i49 < i18; i49++) {
            int i50 = 0;
            while (i50 < bits) {
                int[] iArr22 = iArr12;
                int i51 = 0;
                while (true) {
                    if (i51 < i49) {
                        boolean[] zArr9 = zArr8[i49];
                        if (zArr9[i51] && zArr8[i51][i50]) {
                            zArr9[i50] = true;
                            break;
                        }
                        i51++;
                    }
                }
                i50++;
                iArr12 = iArr22;
            }
        }
        int[] iArr23 = iArr12;
        int[] iArr24 = new int[i20];
        for (int i52 = 0; i52 < i18; i52++) {
            int i53 = 0;
            for (int i54 = 0; i54 < i52; i54++) {
                i53 += zArr3[i52][i54] ? 1 : 0;
            }
            iArr24[iArr23[i52]] = i53;
        }
        int i55 = 0;
        for (int i56 = 0; i56 < i18; i56++) {
            if (iArr24[iArr23[i56]] == 0) {
                i55++;
            }
        }
        if (i55 > 1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr25 = new int[i18];
        int[] iArr26 = new int[unsignedExpGolombCodedInt];
        if (parsableNalUnitBitArray.readBit()) {
            iArr2 = iArr24;
            int i57 = 0;
            while (i57 < i18) {
                int i58 = i57;
                iArr25[i58] = parsableNalUnitBitArray.readBits(3);
                i57 = i58 + 1;
            }
        } else {
            iArr2 = iArr24;
            Arrays.fill(iArr25, 0, i18, bits2);
        }
        int i59 = 0;
        while (i59 < unsignedExpGolombCodedInt) {
            int i60 = i59;
            boolean[][] zArr10 = zArr3;
            int[] iArr27 = iArr25;
            int iMax = 0;
            for (int i61 = 0; i61 < iArr6[i60]; i61++) {
                iMax = Math.max(iMax, iArr27[((H265LayerInfo) o3VarF.get(iArr5[i60][i61])).layerIdInVps]);
            }
            iArr26[i60] = iMax + 1;
            i59 = i60 + 1;
            iArr25 = iArr27;
            zArr3 = zArr10;
        }
        boolean[][] zArr11 = zArr3;
        if (parsableNalUnitBitArray.readBit()) {
            int i62 = 0;
            while (i62 < bits) {
                int i63 = i62 + 1;
                int i64 = i63;
                while (i64 < i18) {
                    if (zArr11[i64][i62]) {
                        i16 = i62;
                        parsableNalUnitBitArray.skipBits(3);
                    } else {
                        i16 = i62;
                    }
                    i64++;
                    i62 = i16;
                }
                i62 = i63;
            }
        }
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        e1 e1VarK2 = h1.k();
        e1VarK2.c(h265ProfileTierLevel);
        if (unsignedExpGolombCodedInt3 > 1) {
            e1VarK2.c(h265ProfileTierLevel2);
            for (int i65 = 2; i65 < unsignedExpGolombCodedInt3; i65++) {
                h265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, parsableNalUnitBitArray.readBit(), bits2, h265ProfileTierLevel2);
                e1VarK2.c(h265ProfileTierLevel2);
            }
        }
        o3 o3VarF2 = e1VarK2.f();
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + unsignedExpGolombCodedInt;
        if (unsignedExpGolombCodedInt4 > unsignedExpGolombCodedInt) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int bits5 = parsableNalUnitBitArray.readBits(2);
        boolean[][] zArr12 = (boolean[][]) Array.newInstance((Class<?>) cls2, unsignedExpGolombCodedInt4, i20);
        int[] iArr28 = new int[unsignedExpGolombCodedInt4];
        int i66 = 0;
        int[] iArr29 = new int[unsignedExpGolombCodedInt4];
        int i67 = 0;
        while (i67 < unsignedExpGolombCodedInt) {
            iArr28[i67] = i66;
            iArr29[i67] = iArr21[i67];
            if (bits5 == 0) {
                i15 = i67;
                zArr = zArr12;
                o3Var = o3VarF2;
                iArr4 = iArr28;
                Arrays.fill(zArr12[i15], i66, iArr6[i15], true);
                iArr4[i15] = iArr6[i15];
            } else {
                o3Var = o3VarF2;
                i15 = i67;
                zArr = zArr12;
                iArr4 = iArr28;
                if (bits5 == 1) {
                    int i68 = iArr21[i15];
                    for (int i69 = 0; i69 < iArr6[i15]; i69++) {
                        zArr[i15][i69] = iArr5[i15][i69] == i68;
                    }
                    iArr4[i15] = 1;
                } else {
                    i66 = 0;
                    zArr[0][0] = true;
                    iArr4[0] = 1;
                    i67 = i15 + 1;
                    zArr12 = zArr;
                    iArr28 = iArr4;
                    o3VarF2 = o3Var;
                }
            }
            i66 = 0;
            i67 = i15 + 1;
            zArr12 = zArr;
            iArr28 = iArr4;
            o3VarF2 = o3Var;
        }
        o3 o3Var2 = o3VarF2;
        boolean[][] zArr13 = zArr12;
        int[] iArr30 = iArr28;
        int[] iArr31 = new int[i20];
        int i70 = 2;
        int[] iArr32 = new int[2];
        iArr32[1] = i20;
        iArr32[i66] = unsignedExpGolombCodedInt4;
        boolean[][] zArr14 = (boolean[][]) Array.newInstance((Class<?>) cls2, iArr32);
        int i71 = 1;
        int i72 = 0;
        while (i71 < unsignedExpGolombCodedInt4) {
            if (bits5 == i70) {
                for (int i73 = 0; i73 < iArr6[i71]; i73++) {
                    zArr13[i71][i73] = parsableNalUnitBitArray.readBit();
                    int i74 = iArr30[i71];
                    boolean z5 = zArr13[i71][i73];
                    iArr30[i71] = i74 + (z5 ? 1 : 0);
                    if (z5) {
                        iArr29[i71] = iArr5[i71][i73];
                    }
                }
            }
            if (i72 == 0) {
                i12 = 0;
                if (iArr5[i71][0] == 0 && zArr13[i71][0]) {
                    for (int i75 = 1; i75 < iArr6[i71]; i75++) {
                        if (iArr5[i71][i75] == i11 && zArr13[i71][i11]) {
                            i72 = i71;
                        }
                    }
                }
            } else {
                i12 = 0;
            }
            int i76 = i12;
            while (i76 < iArr6[i71]) {
                if (unsignedExpGolombCodedInt3 > 1) {
                    zArr14[i71][i76] = zArr13[i71][i76];
                    i14 = i11;
                    iArr3 = iArr31;
                    RoundingMode roundingMode = RoundingMode.CEILING;
                    int iC = c.c(unsignedExpGolombCodedInt3);
                    if (!zArr14[i71][i76]) {
                        int i77 = ((H265LayerInfo) o3VarF.get(iArr5[i71][i76])).layerIdInVps;
                        i13 = unsignedExpGolombCodedInt3;
                        int i78 = i12;
                        while (true) {
                            if (i78 >= i76) {
                                break;
                            }
                            int i79 = i78;
                            if (zArr8[i77][((H265LayerInfo) o3VarF.get(iArr5[i71][i79])).layerIdInVps]) {
                                zArr14[i71][i76] = true;
                                break;
                            }
                            i78 = i79 + 1;
                        }
                    } else {
                        i13 = unsignedExpGolombCodedInt3;
                    }
                    if (zArr14[i71][i76]) {
                        if (i72 <= 0 || i71 != i72) {
                            parsableNalUnitBitArray.skipBits(iC);
                        } else {
                            iArr3[i76] = parsableNalUnitBitArray.readBits(iC);
                        }
                    }
                } else {
                    i13 = unsignedExpGolombCodedInt3;
                    i14 = i11;
                    iArr3 = iArr31;
                }
                i76++;
                i11 = i14;
                iArr31 = iArr3;
                unsignedExpGolombCodedInt3 = i13;
            }
            int i80 = unsignedExpGolombCodedInt3;
            int i81 = i11;
            int[] iArr33 = iArr31;
            if (iArr30[i71] == 1 && iArr2[iArr29[i71]] > 0) {
                parsableNalUnitBitArray.skipBit();
            }
            i71++;
            i11 = i81;
            iArr31 = iArr33;
            unsignedExpGolombCodedInt3 = i80;
            i70 = 2;
        }
        int[] iArr34 = iArr31;
        if (i72 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        H265RepFormatsAndIndices h265RepFormatsAndIndices = parseH265RepFormatsAndIndices(parsableNalUnitBitArray, i18);
        parsableNalUnitBitArray.skipBits(2);
        for (int i82 = 1; i82 < i18; i82++) {
            if (iArr2[iArr23[i82]] == 0) {
                parsableNalUnitBitArray.skipBit();
            }
        }
        skipH265DpbSize(parsableNalUnitBitArray, unsignedExpGolombCodedInt4, iArr26, iArr6, zArr14);
        skipToH265VuiPresentFlagAfterDpbSize(parsableNalUnitBitArray, i18, zArr11);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.byteAlign();
            h265VideoSignalInfosAndIndices = parseH265VideoSignalInfosAndIndices(parsableNalUnitBitArray, i18, unsignedExpGolombCodedInt, iArr26);
        } else {
            h265VideoSignalInfosAndIndices = null;
        }
        return new H265VpsData(h265NalHeader, o3VarF, new H265ProfileTierLevelsAndIndices(o3Var2, iArr34), h265RepFormatsAndIndices, h265VideoSignalInfosAndIndices);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i10, int i11) {
        return parsePpsNalUnitPayload(bArr, i10 + 1, i11);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i10, int i11) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i10, i11);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i10, int i11) {
        return parseSpsNalUnitPayload(bArr, i10 + 1, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.container.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 597
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):androidx.media3.container.NalUnitUtil$SpsData");
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i10, int[] iArr, int[] iArr2, boolean[][] zArr) {
        for (int i11 = 1; i11 < i10; i11++) {
            boolean bit = parsableNalUnitBitArray.readBit();
            int i12 = 0;
            while (i12 < iArr[i11]) {
                if ((i12 <= 0 || !bit) ? i12 == 0 : parsableNalUnitBitArray.readBit()) {
                    for (int i13 = 0; i13 < iArr2[i11]; i13++) {
                        if (zArr[i11][i13]) {
                            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        }
                    }
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                i12++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    private static void skipH265HrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i10) {
        ?? r92;
        ?? r1;
        boolean bit;
        boolean bit2;
        if (z) {
            boolean bit3 = parsableNalUnitBitArray.readBit();
            boolean bit4 = parsableNalUnitBitArray.readBit();
            if (bit3 || bit4) {
                bit = parsableNalUnitBitArray.readBit();
                if (bit) {
                    parsableNalUnitBitArray.skipBits(19);
                }
                parsableNalUnitBitArray.skipBits(8);
                if (bit) {
                    parsableNalUnitBitArray.skipBits(4);
                }
                parsableNalUnitBitArray.skipBits(15);
                r1 = bit4;
                r92 = bit3;
            } else {
                bit = false;
                r1 = bit4;
                r92 = bit3;
            }
        } else {
            r92 = 0;
            r1 = 0;
            bit = false;
        }
        for (int i11 = 0; i11 <= i10; i11++) {
            boolean bit5 = parsableNalUnitBitArray.readBit();
            if (!bit5) {
                bit5 = parsableNalUnitBitArray.readBit();
            }
            if (bit5) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                bit2 = false;
            } else {
                bit2 = parsableNalUnitBitArray.readBit();
            }
            int unsignedExpGolombCodedInt = !bit2 ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
            int i12 = r92 + r1;
            for (int i13 = 0; i13 < i12; i13++) {
                for (int i14 = 0; i14 <= unsignedExpGolombCodedInt; i14++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (bit) {
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    }
                    parsableNalUnitBitArray.skipBit();
                }
            }
        }
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i10 = 0; i10 < 4; i10++) {
            int i11 = 0;
            while (i11 < 6) {
                int i12 = 1;
                if (parsableNalUnitBitArray.readBit()) {
                    int iMin = Math.min(64, 1 << ((i10 << 1) + 4));
                    if (i10 > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i13 = 0; i13 < iMin; i13++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                } else {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                if (i10 == 3) {
                    i12 = 3;
                }
                i11 += i12;
            }
        }
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] iArr = new int[0];
        int[] iArrCopyOf = new int[0];
        int i10 = -1;
        int i11 = -1;
        for (int i12 = 0; i12 < unsignedExpGolombCodedInt; i12++) {
            if (i12 == 0 || !parsableNalUnitBitArray.readBit()) {
                int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] iArr2 = new int[unsignedExpGolombCodedInt2];
                int i13 = 0;
                while (i13 < unsignedExpGolombCodedInt2) {
                    iArr2[i13] = (i13 > 0 ? iArr2[i13 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i13++;
                }
                int[] iArr3 = new int[unsignedExpGolombCodedInt3];
                int i14 = 0;
                while (i14 < unsignedExpGolombCodedInt3) {
                    iArr3[i14] = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1 + (i14 > 0 ? iArr3[i14 - 1] : 0);
                    parsableNalUnitBitArray.skipBit();
                    i14++;
                }
                i10 = unsignedExpGolombCodedInt2;
                iArr = iArr2;
                i11 = unsignedExpGolombCodedInt3;
                iArrCopyOf = iArr3;
            } else {
                int i15 = i10 + i11;
                int unsignedExpGolombCodedInt4 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int i16 = i15 + 1;
                boolean[] zArr = new boolean[i16];
                for (int i17 = 0; i17 <= i15; i17++) {
                    if (parsableNalUnitBitArray.readBit()) {
                        zArr[i17] = true;
                    } else {
                        zArr[i17] = parsableNalUnitBitArray.readBit();
                    }
                }
                int[] iArr4 = new int[i16];
                int[] iArr5 = new int[i16];
                int i18 = 0;
                for (int i19 = i11 - 1; i19 >= 0; i19--) {
                    int i20 = iArrCopyOf[i19] + unsignedExpGolombCodedInt4;
                    if (i20 < 0 && zArr[i10 + i19]) {
                        iArr4[i18] = i20;
                        i18++;
                    }
                }
                if (unsignedExpGolombCodedInt4 < 0 && zArr[i15]) {
                    iArr4[i18] = unsignedExpGolombCodedInt4;
                    i18++;
                }
                for (int i21 = 0; i21 < i10; i21++) {
                    int i22 = iArr[i21] + unsignedExpGolombCodedInt4;
                    if (i22 < 0 && zArr[i21]) {
                        iArr4[i18] = i22;
                        i18++;
                    }
                }
                int[] iArrCopyOf2 = Arrays.copyOf(iArr4, i18);
                int i23 = 0;
                for (int i24 = i10 - 1; i24 >= 0; i24--) {
                    int i25 = iArr[i24] + unsignedExpGolombCodedInt4;
                    if (i25 > 0 && zArr[i24]) {
                        iArr5[i23] = i25;
                        i23++;
                    }
                }
                if (unsignedExpGolombCodedInt4 > 0 && zArr[i15]) {
                    iArr5[i23] = unsignedExpGolombCodedInt4;
                    i23++;
                }
                for (int i26 = 0; i26 < i11; i26++) {
                    int i27 = iArrCopyOf[i26] + unsignedExpGolombCodedInt4;
                    if (i27 > 0 && zArr[i10 + i26]) {
                        iArr5[i23] = i27;
                        i23++;
                    }
                }
                iArrCopyOf = Arrays.copyOf(iArr5, i23);
                iArr = iArrCopyOf2;
                i10 = i18;
                i11 = i23;
            }
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        parsableNalUnitBitArray.skipBits(8);
        for (int i10 = 0; i10 < unsignedExpGolombCodedInt; i10++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBits(20);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i10) {
        int signedExpGolombCodedInt = 8;
        int i11 = 8;
        for (int i12 = 0; i12 < i10; i12++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i11) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i11 = signedExpGolombCodedInt;
            }
        }
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i10, boolean[][] zArr) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 2;
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt);
        } else {
            for (int i11 = 1; i11 < i10; i11++) {
                for (int i12 = 0; i12 < i11; i12++) {
                    if (zArr[i11][i12]) {
                        parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt);
                    }
                }
            }
        }
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i13 = 1; i13 <= unsignedExpGolombCodedInt2; i13++) {
            parsableNalUnitBitArray.skipBits(8);
        }
    }

    public static int unescapeStream(byte[] bArr, int i10) {
        int i11;
        synchronized (scratchEscapePositionsLock) {
            int iFindNextUnescapeIndex = 0;
            int i12 = 0;
            while (iFindNextUnescapeIndex < i10) {
                try {
                    iFindNextUnescapeIndex = findNextUnescapeIndex(bArr, iFindNextUnescapeIndex, i10);
                    if (iFindNextUnescapeIndex < i10) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i12) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i12] = iFindNextUnescapeIndex;
                        iFindNextUnescapeIndex += 3;
                        i12++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i11 = i10 - i12;
            int i13 = 0;
            int i14 = 0;
            for (int i15 = 0; i15 < i12; i15++) {
                int i16 = scratchEscapePositions[i15] - i14;
                System.arraycopy(bArr, i14, bArr, i13, i16);
                int i17 = i13 + i16;
                int i18 = i17 + 1;
                bArr[i17] = 0;
                i13 = i17 + 2;
                bArr[i18] = 0;
                i14 += i16 + 3;
            }
            System.arraycopy(bArr, i14, bArr, i13, i11 - i13);
        }
        return i11;
    }

    public static boolean isNalUnitSei(Format format, byte b10) {
        String nalStructureMimeType = getNalStructureMimeType(format);
        return (Objects.equals(nalStructureMimeType, MimeTypes.VIDEO_H264) && (b10 & 31) == 6) || (Objects.equals(nalStructureMimeType, MimeTypes.VIDEO_H265) && ((b10 & 126) >> 1) == 39);
    }
}

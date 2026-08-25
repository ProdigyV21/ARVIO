package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int decodedHeight;
    public final int decodedWidth;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int maxSubLayers;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    private HevcConfig(List<byte[]> list, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, float f10, int i22, String str, NalUnitUtil.H265VpsData h265VpsData) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i10;
        this.maxSubLayers = i11;
        this.width = i12;
        this.height = i13;
        this.decodedWidth = i14;
        this.decodedHeight = i15;
        this.bitdepthLuma = i16;
        this.bitdepthChroma = i17;
        this.colorSpace = i18;
        this.colorRange = i19;
        this.colorTransfer = i20;
        this.stereoMode = i21;
        this.pixelWidthHeightRatio = f10;
        this.maxNumReorderPics = i22;
        this.codecs = str;
        this.vpsData = h265VpsData;
    }

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        return parseImpl(parsableByteArray, false, null);
    }

    private static HevcConfig parseImpl(ParsableByteArray parsableByteArray, boolean z, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        boolean z5;
        int i10;
        NalUnitUtil.H265Sei3dRefDisplayInfoData h265Sei3dRefDisplayInfo;
        int i11;
        try {
            if (z) {
                parsableByteArray.skipBytes(4);
            } else {
                parsableByteArray.skipBytes(21);
            }
            int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (true) {
                z5 = true;
                if (i13 >= unsignedByte2) {
                    break;
                }
                parsableByteArray.skipBytes(1);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                for (int i15 = 0; i15 < unsignedShort; i15++) {
                    int unsignedShort2 = parsableByteArray.readUnsignedShort();
                    i14 += unsignedShort2 + 4;
                    parsableByteArray.skipBytes(unsignedShort2);
                }
                i13++;
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i14];
            NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
            int i16 = -1;
            int i17 = -1;
            int i18 = -1;
            int i19 = -1;
            int i20 = -1;
            int i21 = -1;
            int i22 = -1;
            int i23 = -1;
            int i24 = -1;
            int i25 = -1;
            int i26 = -1;
            int i27 = -1;
            float f10 = 1.0f;
            String strBuildHevcCodecString = null;
            int i28 = 0;
            int i29 = 0;
            while (i28 < unsignedByte2) {
                int unsignedByte3 = parsableByteArray.readUnsignedByte() & 63;
                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                int i30 = i12;
                NalUnitUtil.H265VpsData h265VpsNalUnit = h265VpsData2;
                while (i30 < unsignedShort3) {
                    int unsignedShort4 = parsableByteArray.readUnsignedShort();
                    boolean z10 = z5;
                    byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
                    int i31 = unsignedByte;
                    System.arraycopy(bArr2, i12, bArr, i29, bArr2.length);
                    int length = i29 + bArr2.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                    if (unsignedByte3 == 32 && i30 == 0) {
                        h265VpsNalUnit = NalUnitUtil.parseH265VpsNalUnit(bArr, length, length + unsignedShort4);
                        i10 = unsignedByte2;
                    } else {
                        if (unsignedByte3 == 33 && i30 == 0) {
                            NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + unsignedShort4, h265VpsNalUnit);
                            i16 = h265SpsNalUnit.maxSubLayersMinus1 + 1;
                            i17 = h265SpsNalUnit.width;
                            int i32 = h265SpsNalUnit.height;
                            int i33 = h265SpsNalUnit.decodedWidth;
                            i10 = unsignedByte2;
                            int i34 = h265SpsNalUnit.decodedHeight;
                            i21 = h265SpsNalUnit.bitDepthLumaMinus8 + 8;
                            i22 = h265SpsNalUnit.bitDepthChromaMinus8 + 8;
                            int i35 = h265SpsNalUnit.colorSpace;
                            int i36 = h265SpsNalUnit.colorRange;
                            int i37 = h265SpsNalUnit.colorTransfer;
                            float f11 = h265SpsNalUnit.pixelWidthHeightRatio;
                            int i38 = h265SpsNalUnit.maxNumReorderPics;
                            NalUnitUtil.H265ProfileTierLevel h265ProfileTierLevel = h265SpsNalUnit.profileTierLevel;
                            if (h265ProfileTierLevel != null) {
                                i11 = i38;
                                strBuildHevcCodecString = CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc);
                            } else {
                                i11 = i38;
                            }
                            f10 = f11;
                            i27 = i11;
                            i24 = i36;
                            i25 = i37;
                            i20 = i34;
                            i23 = i35;
                            i19 = i33;
                            i18 = i32;
                        } else {
                            i10 = unsignedByte2;
                            if (unsignedByte3 == 39 && i30 == 0 && (h265Sei3dRefDisplayInfo = NalUnitUtil.parseH265Sei3dRefDisplayInfo(bArr, length, length + unsignedShort4)) != null && h265VpsNalUnit != null) {
                                i12 = 0;
                                i26 = h265Sei3dRefDisplayInfo.leftViewId == ((NalUnitUtil.H265LayerInfo) h265VpsNalUnit.layerInfos.get(0)).viewId ? 4 : 5;
                            }
                        }
                        i12 = 0;
                    }
                    i29 = length + unsignedShort4;
                    parsableByteArray.skipBytes(unsignedShort4);
                    i30++;
                    z5 = z10;
                    unsignedByte = i31;
                    unsignedByte2 = i10;
                }
                i28++;
                h265VpsData2 = h265VpsNalUnit;
            }
            return new HevcConfig(i14 == 0 ? Collections.EMPTY_LIST : Collections.singletonList(bArr), unsignedByte + 1, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, f10, i27, strBuildHevcCodecString, h265VpsData2);
        } catch (ArrayIndexOutOfBoundsException e5) {
            throw ParserException.createForMalformedContainer("Error parsing".concat(z ? "L-HEVC config" : "HEVC config"), e5);
        }
    }

    public static HevcConfig parseLayered(ParsableByteArray parsableByteArray, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        return parseImpl(parsableByteArray, true, h265VpsData);
    }
}

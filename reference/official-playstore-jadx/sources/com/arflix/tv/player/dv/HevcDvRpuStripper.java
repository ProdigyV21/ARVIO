package com.arflix.tv.player.dv;

import java.io.ByteArrayOutputStream;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0018\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005J \u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J \u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/player/dv/HevcDvRpuStripper;", "", "<init>", "()V", "NAL_TYPE_DV_RPU", "", "NAL_TYPE_DV_EL", "stripRpuLengthDelimited", "", "sample", "sampleLen", "nalLengthFieldLength", "stripRpuAnnexB", "findStartCode", "data", "from", "limit", "startCodeLength", "offset", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HevcDvRpuStripper {
    public static final int $stable = 0;
    public static final HevcDvRpuStripper INSTANCE = new HevcDvRpuStripper();
    private static final int NAL_TYPE_DV_EL = 63;
    private static final int NAL_TYPE_DV_RPU = 62;

    private HevcDvRpuStripper() {
    }

    private final int findStartCode(byte[] data, int from, int limit) {
        byte b10;
        int i10;
        while (true) {
            int i11 = from + 2;
            if (i11 >= limit) {
                return -1;
            }
            if (data[from] == 0 && data[from + 1] == 0 && ((b10 = data[i11]) == 1 || ((i10 = from + 3) < limit && b10 == 0 && data[i10] == 1))) {
                break;
            }
            from++;
        }
        return from;
    }

    private final int startCodeLength(byte[] data, int offset, int limit) {
        int i10 = offset + 3;
        return (i10 < limit && data[offset] == 0 && data[offset + 1] == 0 && data[offset + 2] == 0 && data[i10] == 1) ? 4 : 3;
    }

    public final byte[] stripRpuAnnexB(byte[] sample, int sampleLen) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(sampleLen);
        int i10 = 0;
        boolean z = false;
        while (true) {
            if (i10 >= sampleLen) {
                break;
            }
            int iFindStartCode = findStartCode(sample, i10, sampleLen);
            if (iFindStartCode < 0) {
                byteArrayOutputStream.write(sample, i10, sampleLen - i10);
                break;
            }
            int iStartCodeLength = startCodeLength(sample, iFindStartCode, sampleLen) + iFindStartCode;
            int iFindStartCode2 = findStartCode(sample, iStartCodeLength + 2, sampleLen);
            if (iFindStartCode2 < 0) {
                iFindStartCode2 = sampleLen;
            }
            if (iFindStartCode > i10) {
                byteArrayOutputStream.write(sample, i10, iFindStartCode - i10);
            }
            if (iStartCodeLength < iFindStartCode2) {
                byte b10 = sample[iStartCodeLength];
                int i11 = (b10 >>> 1) & 63;
                int i12 = iStartCodeLength + 1;
                int i13 = i12 < iFindStartCode2 ? ((b10 & 1) << 5) | ((sample[i12] >>> 3) & 31) : 0;
                if (i11 == NAL_TYPE_DV_RPU || i11 == 63 || i13 > 0) {
                    z = true;
                } else {
                    byteArrayOutputStream.write(sample, iFindStartCode, iFindStartCode2 - iFindStartCode);
                }
            }
            i10 = iFindStartCode2;
        }
        if (z) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public final byte[] stripRpuLengthDelimited(byte[] sample, int sampleLen, int nalLengthFieldLength) {
        if (sampleLen < nalLengthFieldLength) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(sampleLen);
        int i10 = 0;
        boolean z = false;
        while (true) {
            int i11 = i10 + nalLengthFieldLength;
            if (i11 > sampleLen) {
                if (z) {
                    return byteArrayOutputStream.toByteArray();
                }
                return null;
            }
            int i12 = 0;
            for (int i13 = 0; i13 < nalLengthFieldLength; i13++) {
                i12 = (i12 << 8) | (sample[i10 + i13] & 255);
            }
            if (i12 <= 0 || (i10 = i11 + i12) > sampleLen) {
                break;
            }
            byte b10 = sample[i11];
            int i14 = (b10 >>> 1) & 63;
            int i15 = i11 + 1;
            int i16 = i15 < sampleLen ? ((b10 & 1) << 5) | ((sample[i15] & 248) >>> 3) : 0;
            boolean z5 = i14 == NAL_TYPE_DV_RPU;
            boolean z10 = i16 > 0;
            if (z5 || z10) {
                z = true;
            } else {
                for (int i17 = nalLengthFieldLength - 1; -1 < i17; i17--) {
                    byteArrayOutputStream.write((i12 >>> (i17 * 8)) & 255);
                }
                byteArrayOutputStream.write(sample, i11, i12);
            }
        }
        return null;
    }
}

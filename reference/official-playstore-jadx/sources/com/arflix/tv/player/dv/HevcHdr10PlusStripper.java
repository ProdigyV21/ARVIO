package com.arflix.tv.player.dv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0005J\"\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J \u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\tH\u0002J \u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/player/dv/HevcHdr10PlusStripper;", "", "<init>", "()V", "NAL_TYPE_PREFIX_SEI", "", "NAL_TYPE_SUFFIX_SEI", "SEI_PAYLOAD_TYPE_USER_DATA_REGISTERED", "HDR10_PLUS_T35_SIGNATURE", "", "stripHdr10PlusLengthDelimited", "sample", "sampleLen", "nalLengthFieldLength", "stripHdr10PlusAnnexB", "filterSeiNal", "data", "nalOffset", "nalSize", "findStartCode", "from", "limit", "matchesSignature", "", "offset", "sig", "startCodeLength", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HevcHdr10PlusStripper {
    private static final int NAL_TYPE_PREFIX_SEI = 39;
    private static final int NAL_TYPE_SUFFIX_SEI = 40;
    private static final int SEI_PAYLOAD_TYPE_USER_DATA_REGISTERED = 4;
    public static final HevcHdr10PlusStripper INSTANCE = new HevcHdr10PlusStripper();
    private static final byte[] HDR10_PLUS_T35_SIGNATURE = {-75, 0, 60, 0, 1};
    public static final int $stable = 8;

    private HevcHdr10PlusStripper() {
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final byte[] filterSeiNal(byte[] r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.player.dv.HevcHdr10PlusStripper.filterSeiNal(byte[], int, int):byte[]");
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

    private final boolean matchesSignature(byte[] data, int offset, byte[] sig) {
        if (sig.length + offset > data.length) {
            return false;
        }
        int length = sig.length;
        for (int i10 = 0; i10 < length; i10++) {
            if (data[offset + i10] != sig[i10]) {
                return false;
            }
        }
        return true;
    }

    private final int startCodeLength(byte[] data, int offset, int limit) {
        int i10 = offset + 3;
        return (i10 < limit && data[offset] == 0 && data[offset + 1] == 0 && data[offset + 2] == 0 && data[i10] == 1) ? 4 : 3;
    }

    public final byte[] stripHdr10PlusAnnexB(byte[] sample, int sampleLen) throws IOException {
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
            int iStartCodeLength = startCodeLength(sample, iFindStartCode, sampleLen);
            int i11 = iFindStartCode + iStartCodeLength;
            int iFindStartCode2 = findStartCode(sample, i11 + 2, sampleLen);
            if (iFindStartCode2 < 0) {
                iFindStartCode2 = sampleLen;
            }
            if (iFindStartCode > i10) {
                byteArrayOutputStream.write(sample, i10, iFindStartCode - i10);
            }
            if (i11 < iFindStartCode2) {
                int i12 = iFindStartCode2 - i11;
                int i13 = (sample[i11] >>> 1) & 63;
                if (i13 == 39 || i13 == 40) {
                    byte[] bArrFilterSeiNal = filterSeiNal(sample, i11, i12);
                    if (bArrFilterSeiNal == null) {
                        byteArrayOutputStream.write(sample, iFindStartCode, iFindStartCode2 - iFindStartCode);
                    } else {
                        if (!(bArrFilterSeiNal.length == 0)) {
                            byteArrayOutputStream.write(sample, iFindStartCode, iStartCodeLength);
                            byteArrayOutputStream.write(bArrFilterSeiNal);
                        }
                        z = true;
                    }
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

    public final byte[] stripHdr10PlusLengthDelimited(byte[] sample, int sampleLen, int nalLengthFieldLength) throws IOException {
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
            int i14 = (sample[i11] >>> 1) & 63;
            if (i14 == 39 || i14 == 40) {
                byte[] bArrFilterSeiNal = filterSeiNal(sample, i11, i12);
                if (bArrFilterSeiNal == null) {
                    for (int i15 = nalLengthFieldLength - 1; -1 < i15; i15--) {
                        byteArrayOutputStream.write((i12 >>> (i15 * 8)) & 255);
                    }
                    byteArrayOutputStream.write(sample, i11, i12);
                } else {
                    if (!(bArrFilterSeiNal.length == 0)) {
                        for (int i16 = nalLengthFieldLength - 1; -1 < i16; i16--) {
                            byteArrayOutputStream.write((bArrFilterSeiNal.length >>> (i16 * 8)) & 255);
                        }
                        byteArrayOutputStream.write(bArrFilterSeiNal);
                    }
                    z = true;
                }
            } else {
                for (int i17 = nalLengthFieldLength - 1; -1 < i17; i17--) {
                    byteArrayOutputStream.write((i12 >>> (i17 * 8)) & 255);
                }
                byteArrayOutputStream.write(sample, i11, i12);
            }
        }
        return stripHdr10PlusAnnexB(sample, sampleLen);
    }
}

package androidx.datastore.preferences.protobuf;

import androidx.media3.extractor.ts.PsExtractor;

/* JADX INFO: loaded from: classes3.dex */
public final class q3 extends x1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2511c;

    public static int m(long j10, byte[] bArr, int i10, int i11) {
        if (i11 == 0) {
            x1 x1Var = s3.f2528a;
            if (i10 > -12) {
                return -1;
            }
            return i10;
        }
        if (i11 == 1) {
            return s3.c(i10, p3.f(bArr, j10));
        }
        if (i11 == 2) {
            return s3.d(i10, p3.f(bArr, j10), p3.f(bArr, j10 + 1));
        }
        throw new AssertionError();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ff  */
    @Override // androidx.datastore.preferences.protobuf.x1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String e(int r17, int r18, byte[] r19) throws androidx.datastore.preferences.protobuf.InvalidProtocolBufferException {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.q3.e(int, int, byte[]):java.lang.String");
    }

    @Override // androidx.datastore.preferences.protobuf.x1
    public final int f(String str, byte[] bArr, int i10, int i11) {
        int i12;
        int i13;
        char cCharAt;
        long j10;
        long j11;
        long j12;
        int i14;
        char cCharAt2;
        switch (this.f2511c) {
            case 0:
                int length = str.length();
                int i15 = i11 + i10;
                int i16 = 0;
                while (i16 < length && (i13 = i16 + i10) < i15 && (cCharAt = str.charAt(i16)) < 128) {
                    bArr[i13] = (byte) cCharAt;
                    i16++;
                }
                if (i16 == length) {
                    return i10 + length;
                }
                int i17 = i10 + i16;
                while (i16 < length) {
                    char cCharAt3 = str.charAt(i16);
                    if (cCharAt3 < 128 && i17 < i15) {
                        bArr[i17] = (byte) cCharAt3;
                        i17++;
                    } else if (cCharAt3 < 2048 && i17 <= i15 - 2) {
                        int i18 = i17 + 1;
                        bArr[i17] = (byte) ((cCharAt3 >>> 6) | 960);
                        i17 += 2;
                        bArr[i18] = (byte) ((cCharAt3 & '?') | 128);
                    } else {
                        if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || i17 > i15 - 3) {
                            if (i17 > i15 - 4) {
                                if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i12 = i16 + 1) == str.length() || !Character.isSurrogatePair(cCharAt3, str.charAt(i12)))) {
                                    throw new r3(i16, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt3 + " at index " + i17);
                            }
                            int i19 = i16 + 1;
                            if (i19 != str.length()) {
                                char cCharAt4 = str.charAt(i19);
                                if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                    int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                    bArr[i17] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                    bArr[i17 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i20 = i17 + 3;
                                    bArr[i17 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i17 += 4;
                                    bArr[i20] = (byte) ((codePoint & 63) | 128);
                                    i16 = i19;
                                } else {
                                    i16 = i19;
                                }
                            }
                            throw new r3(i16 - 1, length);
                        }
                        bArr[i17] = (byte) ((cCharAt3 >>> '\f') | 480);
                        int i21 = i17 + 2;
                        bArr[i17 + 1] = (byte) (((cCharAt3 >>> 6) & 63) | 128);
                        i17 += 3;
                        bArr[i21] = (byte) ((cCharAt3 & '?') | 128);
                    }
                    i16++;
                }
                return i17;
            default:
                long j13 = i10;
                long j14 = ((long) i11) + j13;
                int length2 = str.length();
                if (length2 > i11 || bArr.length - i11 < i10) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(length2 - 1) + " at index " + (i10 + i11));
                }
                int i22 = 0;
                while (true) {
                    j10 = 1;
                    if (i22 < length2 && (cCharAt2 = str.charAt(i22)) < 128) {
                        p3.j(bArr, j13, (byte) cCharAt2);
                        i22++;
                        j13 = 1 + j13;
                    }
                }
                if (i22 != length2) {
                    while (i22 < length2) {
                        char cCharAt5 = str.charAt(i22);
                        if (cCharAt5 < 128 && j13 < j14) {
                            p3.j(bArr, j13, (byte) cCharAt5);
                            j12 = j14;
                            j11 = j10;
                            j13 += j10;
                        } else if (cCharAt5 >= 2048 || j13 > j14 - 2) {
                            j11 = j10;
                            if ((cCharAt5 >= 55296 && 57343 >= cCharAt5) || j13 > j14 - 3) {
                                j12 = j14;
                                if (j13 > j12 - 4) {
                                    if (55296 <= cCharAt5 && cCharAt5 <= 57343 && ((i14 = i22 + 1) == length2 || !Character.isSurrogatePair(cCharAt5, str.charAt(i14)))) {
                                        throw new r3(i22, length2);
                                    }
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt5 + " at index " + j13);
                                }
                                int i23 = i22 + 1;
                                if (i23 != length2) {
                                    char cCharAt6 = str.charAt(i23);
                                    if (Character.isSurrogatePair(cCharAt5, cCharAt6)) {
                                        int codePoint2 = Character.toCodePoint(cCharAt5, cCharAt6);
                                        p3.j(bArr, j13, (byte) ((codePoint2 >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                        p3.j(bArr, j13 + j11, (byte) (((codePoint2 >>> 12) & 63) | 128));
                                        long j15 = j13 + 3;
                                        p3.j(bArr, j13 + 2, (byte) (((codePoint2 >>> 6) & 63) | 128));
                                        j13 += 4;
                                        p3.j(bArr, j15, (byte) ((codePoint2 & 63) | 128));
                                        i22 = i23;
                                    } else {
                                        i22 = i23;
                                    }
                                }
                                throw new r3(i22 - 1, length2);
                            }
                            p3.j(bArr, j13, (byte) ((cCharAt5 >>> '\f') | 480));
                            j12 = j14;
                            long j16 = j13 + 2;
                            p3.j(bArr, j13 + j11, (byte) (((cCharAt5 >>> 6) & 63) | 128));
                            j13 += 3;
                            p3.j(bArr, j16, (byte) ((cCharAt5 & '?') | 128));
                        } else {
                            j11 = j10;
                            long j17 = j13 + j11;
                            p3.j(bArr, j13, (byte) ((cCharAt5 >>> 6) | 960));
                            j13 += 2;
                            p3.j(bArr, j17, (byte) ((cCharAt5 & '?') | 128));
                            j12 = j14;
                        }
                        i22++;
                        j10 = j11;
                        j14 = j12;
                    }
                }
                return (int) j13;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.x1
    public final int i(int i10, int i11, byte[] bArr) {
        int i12;
        long j10;
        byte b10;
        int i13;
        int i14 = i10;
        byte b11 = -96;
        byte b12 = -62;
        switch (this.f2511c) {
            case 0:
                while (i14 < i11 && bArr[i14] >= 0) {
                    i14++;
                }
                if (i14 < i11) {
                    while (i14 < i11) {
                        int i15 = i14 + 1;
                        byte b13 = bArr[i14];
                        if (b13 >= 0) {
                            i14 = i15;
                        } else if (b13 < -32) {
                            if (i15 >= i11) {
                                return b13;
                            }
                            if (b13 < -62) {
                                return -1;
                            }
                            i14 += 2;
                            if (bArr[i15] > -65) {
                                return -1;
                            }
                        } else if (b13 < -16) {
                            if (i15 >= i11 - 1) {
                                return s3.a(i15, i11, bArr);
                            }
                            int i16 = i14 + 2;
                            byte b14 = bArr[i15];
                            if (b14 > -65) {
                                return -1;
                            }
                            if (b13 == -32 && b14 < -96) {
                                return -1;
                            }
                            if (b13 == -19 && b14 >= -96) {
                                return -1;
                            }
                            i14 += 3;
                            if (bArr[i16] > -65) {
                                return -1;
                            }
                        } else {
                            if (i15 >= i11 - 2) {
                                return s3.a(i15, i11, bArr);
                            }
                            int i17 = i14 + 2;
                            byte b15 = bArr[i15];
                            if (b15 > -65 || (((b15 + 112) + (b13 << 28)) >> 30) != 0) {
                                return -1;
                            }
                            int i18 = i14 + 3;
                            if (bArr[i17] > -65) {
                                return -1;
                            }
                            i14 += 4;
                            if (bArr[i18] > -65) {
                                return -1;
                            }
                        }
                    }
                }
                return 0;
            default:
                int i19 = 2;
                if ((i14 | i11 | (bArr.length - i11)) < 0) {
                    throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i14), Integer.valueOf(i11)));
                }
                byte b16 = 0;
                long j11 = i14;
                int i20 = (int) (((long) i11) - j11);
                if (i20 < 16) {
                    i12 = 0;
                    j10 = j11;
                } else {
                    i12 = 0;
                    j10 = j11;
                    long j12 = j10;
                    while (true) {
                        if (i12 < i20) {
                            long j13 = j12 + 1;
                            if (p3.f(bArr, j12) >= 0) {
                                i12++;
                                j12 = j13;
                            }
                        } else {
                            i12 = i20;
                        }
                    }
                }
                int i21 = i20 - i12;
                long j14 = j10 + ((long) i12);
                while (true) {
                    byte bF = b16;
                    while (true) {
                        if (i21 > 0) {
                            long j15 = j14 + 1;
                            bF = p3.f(bArr, j14);
                            if (bF >= 0) {
                                i21--;
                                j14 = j15;
                            } else {
                                j14 = j15;
                            }
                        }
                    }
                    if (i21 == 0) {
                        return b16;
                    }
                    int i22 = i21 - 1;
                    if (bF < -32) {
                        if (i22 == 0) {
                            return bF;
                        }
                        i21 -= 2;
                        if (bF < b12) {
                            return -1;
                        }
                        long j16 = j14 + 1;
                        if (p3.f(bArr, j14) > -65) {
                            return -1;
                        }
                        b10 = b16;
                        i13 = i19;
                        j14 = j16;
                    } else if (bF >= -16) {
                        b10 = b16;
                        i13 = i19;
                        if (i22 < 3) {
                            return m(j14, bArr, bF, i22);
                        }
                        i21 -= 4;
                        long j17 = j14 + 1;
                        byte bF2 = p3.f(bArr, j14);
                        if (bF2 > -65 || (((bF2 + 112) + (bF << 28)) >> 30) != 0) {
                            return -1;
                        }
                        long j18 = j14 + 2;
                        if (p3.f(bArr, j17) > -65) {
                            return -1;
                        }
                        j14 += 3;
                        if (p3.f(bArr, j18) > -65) {
                            return -1;
                        }
                    } else {
                        if (i22 < i19) {
                            return m(j14, bArr, bF, i22);
                        }
                        i21 -= 3;
                        i13 = i19;
                        long j19 = j14 + 1;
                        byte bF3 = p3.f(bArr, j14);
                        if (bF3 > -65) {
                            return -1;
                        }
                        if (bF == -32 && bF3 < b11) {
                            return -1;
                        }
                        b10 = b16;
                        if (bF == -19 && bF3 >= b11) {
                            return -1;
                        }
                        j14 += 2;
                        if (p3.f(bArr, j19) > -65) {
                            return -1;
                        }
                    }
                    i19 = i13;
                    b16 = b10;
                    b11 = -96;
                    b12 = -62;
                }
                break;
        }
    }
}

package kotlin.reflect.jvm.internal.impl.protobuf;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j0 f19806a = new j0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k0 f19807b = new k0();

    public static int a(int i10, int i11) {
        if (i10 > -12 || i11 > -65) {
            return -1;
        }
        return i10 ^ (i11 << 8);
    }

    public static int b(int i10, int i11, byte[] bArr) {
        byte b10 = bArr[i10 - 1];
        int i12 = i11 - i10;
        if (i12 == 0) {
            if (b10 > -12) {
                return -1;
            }
            return b10;
        }
        if (i12 == 1) {
            return a(b10, bArr[i10]);
        }
        if (i12 != 2) {
            throw new AssertionError();
        }
        byte b11 = bArr[i10];
        byte b12 = bArr[i10 + 1];
        if (b10 > -12 || b11 > -65 || b12 > -65) {
            return -1;
        }
        return (b12 << 16) ^ ((b11 << 8) ^ b10);
    }

    public static int c(int i10, int i11, byte[] bArr) {
        while (i10 < i11 && bArr[i10] >= 0) {
            i10++;
        }
        if (i10 >= i11) {
            return 0;
        }
        while (i10 < i11) {
            int i12 = i10 + 1;
            byte b10 = bArr[i10];
            if (b10 >= 0) {
                i10 = i12;
            } else if (b10 < -32) {
                if (i12 >= i11) {
                    return b10;
                }
                if (b10 < -62) {
                    return -1;
                }
                i10 += 2;
                if (bArr[i12] > -65) {
                    return -1;
                }
            } else if (b10 < -16) {
                if (i12 >= i11 - 1) {
                    return b(i12, i11, bArr);
                }
                int i13 = i10 + 2;
                byte b11 = bArr[i12];
                if (b11 > -65) {
                    return -1;
                }
                if (b10 == -32 && b11 < -96) {
                    return -1;
                }
                if (b10 == -19 && b11 >= -96) {
                    return -1;
                }
                i10 += 3;
                if (bArr[i13] > -65) {
                    return -1;
                }
            } else {
                if (i12 >= i11 - 2) {
                    return b(i12, i11, bArr);
                }
                int i14 = i10 + 2;
                byte b12 = bArr[i12];
                if (b12 > -65) {
                    return -1;
                }
                if ((((b12 + 112) + (b10 << 28)) >> 30) != 0) {
                    return -1;
                }
                int i15 = i10 + 3;
                if (bArr[i14] > -65) {
                    return -1;
                }
                i10 += 4;
                if (bArr[i15] > -65) {
                    return -1;
                }
            }
        }
        return 0;
    }
}

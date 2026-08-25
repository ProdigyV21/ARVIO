package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public abstract class v3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m1 f13137a;

    static {
        if (u3.f13132e && u3.f13131d) {
            int i10 = r1.f13108a;
        }
        f13137a = new m1();
    }

    public static /* bridge */ /* synthetic */ int a(int i10, int i11, byte[] bArr) {
        int i12 = i11 - i10;
        byte b10 = bArr[i10 - 1];
        if (i12 == 0) {
            if (b10 > -12) {
                return -1;
            }
            return b10;
        }
        if (i12 == 1) {
            byte b11 = bArr[i10];
            if (b10 > -12 || b11 > -65) {
                return -1;
            }
            return (b11 << 8) ^ b10;
        }
        if (i12 != 2) {
            throw new AssertionError();
        }
        byte b12 = bArr[i10];
        byte b13 = bArr[i10 + 1];
        if (b10 > -12 || b12 > -65 || b13 > -65) {
            return -1;
        }
        return (b13 << 16) ^ ((b12 << 8) ^ b10);
    }
}

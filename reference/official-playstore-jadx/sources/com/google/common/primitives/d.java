package com.google.common.primitives;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f14183a;

    static {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i10 = 0; i10 < 10; i10++) {
            bArr[i10 + 48] = (byte) i10;
        }
        for (int i11 = 0; i11 < 26; i11++) {
            byte b10 = (byte) (i11 + 10);
            bArr[i11 + 65] = b10;
            bArr[i11 + 97] = b10;
        }
        f14183a = bArr;
    }
}

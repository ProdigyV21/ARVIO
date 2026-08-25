package com.google.android.gms.internal.cast;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ya {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f13698a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f13699b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f13699b = bArr;
        ByteBuffer.wrap(bArr);
    }
}

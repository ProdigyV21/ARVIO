package com.google.android.gms.internal.auth;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f13047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f13048b;

    static {
        Charset.forName("US-ASCII");
        f13047a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f13048b = bArr;
        ByteBuffer.wrap(bArr);
    }
}

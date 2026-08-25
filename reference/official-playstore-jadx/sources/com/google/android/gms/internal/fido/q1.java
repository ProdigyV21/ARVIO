package com.google.android.gms.internal.fido;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q1 implements Comparable {
    public static int i(byte b10) {
        return (b10 >> 5) & 7;
    }

    public static q1 j(byte... bArr) {
        bArr.getClass();
        s1 s1Var = new s1(new ByteArrayInputStream(Arrays.copyOf(bArr, bArr.length)));
        try {
            return b.k(s1Var);
        } finally {
            try {
                s1Var.close();
            } catch (IOException unused) {
            }
        }
    }

    public abstract int a();

    public int d() {
        return 0;
    }

    public final q1 f(Class cls) throws zzho {
        if (cls.isInstance(this)) {
            return (q1) cls.cast(this);
        }
        throw new zzho(androidx.compose.material3.d.n("Expected a ", cls.getName(), " value, but got ", getClass().getName()));
    }
}

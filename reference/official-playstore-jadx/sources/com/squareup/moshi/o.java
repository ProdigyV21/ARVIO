package com.squareup.moshi;

import androidx.fragment.app.a2;
import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class o implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14442i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f14443l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String[] f14444m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f14445n;

    public final String i() {
        return e0.c(this.f14442i, this.f14443l, this.f14444m, this.f14445n);
    }

    public final void j(int i10) {
        int i11 = this.f14442i;
        int[] iArr = this.f14443l;
        if (i11 == iArr.length) {
            if (i11 == 256) {
                throw new JsonDataException("Nesting too deep at " + i());
            }
            this.f14443l = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f14444m;
            this.f14444m = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f14445n;
            this.f14445n = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f14443l;
        int i12 = this.f14442i;
        this.f14442i = i12 + 1;
        iArr3[i12] = i10;
    }

    public final void k(String str) {
        StringBuilder sbP = a2.p(str, " at path ");
        sbP.append(i());
        throw new JsonEncodingException(sbP.toString());
    }
}

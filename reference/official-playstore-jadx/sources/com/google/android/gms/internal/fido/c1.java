package com.google.android.gms.internal.fido;

import java.util.Comparator;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class c1 implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c1 f13767i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ c1[] f13768l;

    static {
        c1 c1Var = new c1("INSTANCE", 0);
        f13767i = c1Var;
        f13768l = new c1[]{c1Var};
    }

    public static c1[] values() {
        return (c1[]) f13768l.clone();
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = (byte[]) obj2;
        int iMin = Math.min(bArr.length, bArr2.length);
        for (int i10 = 0; i10 < iMin; i10++) {
            int i11 = (bArr[i10] & 255) - (bArr2[i10] & 255);
            if (i11 != 0) {
                return i11;
            }
        }
        return bArr.length - bArr2.length;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
    }
}

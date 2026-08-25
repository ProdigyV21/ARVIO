package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class zzxo extends IOException {
    public zzxo() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public zzxo(long j10, long j11, int i10, IndexOutOfBoundsException indexOutOfBoundsException) {
        Locale locale = Locale.US;
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "Pos: ", ", limit: ");
        sbR.append(j11);
        sbR.append(", len: ");
        sbR.append(i10);
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(sbR.toString()), indexOutOfBoundsException);
    }

    public zzxo(IndexOutOfBoundsException indexOutOfBoundsException) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", indexOutOfBoundsException);
    }
}

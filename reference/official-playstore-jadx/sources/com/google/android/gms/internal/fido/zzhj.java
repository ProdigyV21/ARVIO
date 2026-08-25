package com.google.android.gms.internal.fido;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzhj extends IOException {
    public zzhj(Exception exc) {
        super("Error in decoding CborValue from bytes", exc);
    }
}

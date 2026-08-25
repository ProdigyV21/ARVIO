package com.google.common.io;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends OutputStream {
    public final String toString() {
        return "ByteStreams.nullOutputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i10) {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        bArr.getClass();
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i10, int i11) {
        bArr.getClass();
        ac.b.r(i10, i11 + i10, bArr.length);
    }
}

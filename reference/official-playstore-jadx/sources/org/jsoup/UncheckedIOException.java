package org.jsoup;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class UncheckedIOException extends java.io.UncheckedIOException {
    public UncheckedIOException(IOException iOException) {
        super(iOException);
    }

    public IOException ioException() {
        return getCause();
    }

    public UncheckedIOException(String str) {
        super(new IOException(str));
    }
}

package io.sentry.util;

import java.io.Writer;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends Writer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f17959i = 0;

    public static int i(char c10) {
        if (c10 <= 127) {
            return 1;
        }
        return (c10 > 2047 && !Character.isSurrogate(c10)) ? 3 : 2;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            this.f17959i += (long) i(cArr[i12]);
        }
    }

    @Override // java.io.Writer
    public final void write(int i10) {
        this.f17959i += (long) i((char) i10);
    }

    @Override // java.io.Writer
    public final void write(String str, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            this.f17959i += (long) i(str.charAt(i12));
        }
    }
}

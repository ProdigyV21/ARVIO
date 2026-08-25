package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: loaded from: classes3.dex */
public final class x1 extends Writer {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final StringBuilder f2868l = new StringBuilder(128);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f2867i = "FragmentManager";

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        i();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        i();
    }

    public final void i() {
        StringBuilder sb2 = this.f2868l;
        if (sb2.length() > 0) {
            Log.d(this.f2867i, sb2.toString());
            sb2.delete(0, sb2.length());
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i10, int i11) {
        for (int i12 = 0; i12 < i11; i12++) {
            char c10 = cArr[i10 + i12];
            if (c10 == '\n') {
                i();
            } else {
                this.f2868l.append(c10);
            }
        }
    }
}

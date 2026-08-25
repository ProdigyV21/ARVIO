package org.fusesource.jansi.internal;

import ac.b;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CLibrary {
    static {
        if (b.K()) {
            init();
        }
    }

    private static native void init();

    public static native int isatty(int i10);
}

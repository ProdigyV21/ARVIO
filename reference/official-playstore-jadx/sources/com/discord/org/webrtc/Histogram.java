package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
class Histogram {
    private final long handle;

    private Histogram(long j10) {
        this.handle = j10;
    }

    public static Histogram createCounts(String str, int i10, int i11, int i12) {
        return new Histogram(nativeCreateCounts(str, i10, i11, i12));
    }

    public static Histogram createEnumeration(String str, int i10) {
        return new Histogram(nativeCreateEnumeration(str, i10));
    }

    private static native void nativeAddSample(long j10, int i10);

    private static native long nativeCreateCounts(String str, int i10, int i11, int i12);

    private static native long nativeCreateEnumeration(String str, int i10);

    public void addSample(int i10) {
        nativeAddSample(this.handle, i10);
    }
}

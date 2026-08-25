package androidx.media3.exoplayer;

import androidx.media3.exoplayer.RendererCapabilities;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class t0 {
    public static int c(int i10) {
        return e(i10, 0, 0, 0);
    }

    public static int d(int i10, int i11, int i12) {
        return g(i10, i11, i12, 0, 128, 0);
    }

    public static int e(int i10, int i11, int i12, int i13) {
        return g(i10, i11, i12, 0, 128, i13);
    }

    public static int f(int i10, int i11, int i12, int i13, int i14) {
        return g(i10, i11, i12, i13, i14, 0);
    }

    public static int g(int i10, int i11, int i12, int i13, int i14, int i15) {
        return i10 | i11 | i12 | i13 | i14 | i15;
    }

    public static int h(int i10) {
        return i10 & 24;
    }

    public static int i(int i10) {
        return i10 & RendererCapabilities.AUDIO_OFFLOAD_SUPPORT_MASK;
    }

    public static int j(int i10) {
        return i10 & RendererCapabilities.DECODER_SUPPORT_MASK;
    }

    public static int k(int i10) {
        return i10 & 7;
    }

    public static int l(int i10) {
        return i10 & 64;
    }

    public static int m(int i10) {
        return i10 & 32;
    }

    public static boolean n(int i10, boolean z) {
        int iK = k(i10);
        if (iK != 4) {
            return z && iK == 3;
        }
        return true;
    }

    public static void a(RendererCapabilities rendererCapabilities) {
    }

    public static void b(RendererCapabilities rendererCapabilities, RendererCapabilities.Listener listener) {
    }
}

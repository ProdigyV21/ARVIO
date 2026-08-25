package androidx.media3.exoplayer;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class s0 {
    public static long b(Renderer renderer, long j10, long j11) {
        if (renderer.getState() == 1) {
            return (renderer.isReady() || renderer.isEnded()) ? 1000000L : 10000L;
        }
        return 10000L;
    }

    public static boolean e(Renderer renderer, long j10) {
        return false;
    }

    public static void a(Renderer renderer) {
    }

    public static void c(Renderer renderer) {
    }

    public static void d(Renderer renderer, float f10, float f11) {
    }
}

package androidx.media3.exoplayer.drm;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class e {
    public static boolean a(DrmSession drmSession) {
        return false;
    }

    public static void b(DrmSession drmSession, DrmSession drmSession2) {
        if (drmSession == drmSession2) {
            return;
        }
        if (drmSession2 != null) {
            drmSession2.acquire(null);
        }
        if (drmSession != null) {
            drmSession.release(null);
        }
    }
}

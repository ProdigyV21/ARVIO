package androidx.media3.common.util;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class BackgroundExecutor {
    private static Executor staticInstance;

    private BackgroundExecutor() {
    }

    public static synchronized Executor get() {
        try {
            if (staticInstance == null) {
                staticInstance = Util.newSingleThreadExecutor("ExoPlayer:BackgroundExecutor");
            }
        } catch (Throwable th) {
            throw th;
        }
        return staticInstance;
    }

    public static synchronized void set(Executor executor) {
        staticInstance = executor;
    }
}

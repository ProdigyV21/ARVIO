package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Surface;
import androidx.media3.common.util.EGLSurfaceTexture;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaceholderSurface extends Surface {
    private static final String TAG = "PlaceholderSurface";
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final PlaceholderSurfaceThread thread;
    private boolean threadReleased;

    public static class PlaceholderSurfaceThread extends HandlerThread implements Handler.Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 2;
        private EGLSurfaceTexture eglSurfaceTexture;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private PlaceholderSurface surface;

        public PlaceholderSurfaceThread() {
            super("ExoPlayer:PlaceholderSurface");
        }

        private void initInternal(int i10) throws GlUtil.GlException {
            this.eglSurfaceTexture.getClass();
            this.eglSurfaceTexture.init(i10);
            this.surface = new PlaceholderSurface(this, this.eglSurfaceTexture.getSurfaceTexture(), i10 != 0);
        }

        private void releaseInternal() {
            this.eglSurfaceTexture.getClass();
            this.eglSurfaceTexture.release();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i10 = message.what;
            try {
                if (i10 != 1) {
                    if (i10 != 2) {
                        return true;
                    }
                    try {
                        releaseInternal();
                    } finally {
                        try {
                        } finally {
                        }
                    }
                    return true;
                }
                try {
                    initInternal(message.arg1);
                    synchronized (this) {
                        notify();
                    }
                } catch (GlUtil.GlException e5) {
                    Log.e(PlaceholderSurface.TAG, "Failed to initialize placeholder surface", e5);
                    this.initException = new IllegalStateException(e5);
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e6) {
                    Log.e(PlaceholderSurface.TAG, "Failed to initialize placeholder surface", e6);
                    this.initError = e6;
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e10) {
                    Log.e(PlaceholderSurface.TAG, "Failed to initialize placeholder surface", e10);
                    this.initException = e10;
                    synchronized (this) {
                        notify();
                    }
                }
                return true;
            } catch (Throwable th) {
                synchronized (this) {
                    notify();
                    throw th;
                }
            }
        }

        public PlaceholderSurface init(int i10) {
            boolean z;
            start();
            Handler handler = new Handler(getLooper(), this);
            this.handler = handler;
            this.eglSurfaceTexture = new EGLSurfaceTexture(handler);
            synchronized (this) {
                z = false;
                this.handler.obtainMessage(1, i10, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            RuntimeException runtimeException = this.initException;
            if (runtimeException != null) {
                throw runtimeException;
            }
            Error error = this.initError;
            if (error != null) {
                throw error;
            }
            PlaceholderSurface placeholderSurface = this.surface;
            placeholderSurface.getClass();
            return placeholderSurface;
        }

        public void release() {
            this.handler.getClass();
            this.handler.sendEmptyMessage(2);
        }
    }

    private static int getSecureMode(Context context) {
        try {
            if (GlUtil.isProtectedContentExtensionSupported(context)) {
                return GlUtil.isSurfacelessContextExtensionSupported() ? 1 : 2;
            }
            return 0;
        } catch (GlUtil.GlException e5) {
            Log.e(TAG, "Failed to determine secure mode due to GL error: " + e5.getMessage());
            return 0;
        }
    }

    public static synchronized boolean isSecureSupported(Context context) {
        try {
            if (!secureModeInitialized) {
                secureMode = getSecureMode(context);
                secureModeInitialized = true;
            }
        } catch (Throwable th) {
            throw th;
        }
        return secureMode != 0;
    }

    public static PlaceholderSurface newInstance(Context context, boolean z) {
        ac.b.s(!z || isSecureSupported(context));
        return new PlaceholderSurfaceThread().init(z ? secureMode : 0);
    }

    @Deprecated
    public static PlaceholderSurface newInstanceV17(Context context, boolean z) {
        return newInstance(context, z);
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        synchronized (this.thread) {
            try {
                if (!this.threadReleased) {
                    this.thread.release();
                    this.threadReleased = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private PlaceholderSurface(PlaceholderSurfaceThread placeholderSurfaceThread, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.thread = placeholderSurfaceThread;
        this.secure = z;
    }
}

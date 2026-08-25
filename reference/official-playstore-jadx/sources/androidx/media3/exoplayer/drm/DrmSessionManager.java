package androidx.media3.exoplayer.drm;

import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* JADX INFO: loaded from: classes3.dex */
public interface DrmSessionManager {
    public static final DrmSessionManager DRM_UNSUPPORTED = new DrmSessionManager() { // from class: androidx.media3.exoplayer.drm.DrmSessionManager.1
        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            if (format.drmInitData == null) {
                return null;
            }
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1), PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED));
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public int getCryptoType(Format format) {
            return format.drmInitData != null ? 1 : 0;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public final /* synthetic */ DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            return h.a(this, eventDispatcher, format);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public final /* synthetic */ void prepare() {
            h.b(this);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public final /* synthetic */ void release() {
            h.c(this);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public void setPlayer(Looper looper, PlayerId playerId) {
        }
    };

    public interface DrmSessionReference {
        public static final DrmSessionReference EMPTY = new a(3);

        void release();
    }

    DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    int getCryptoType(Format format);

    DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    void prepare();

    void release();

    void setPlayer(Looper looper, PlayerId playerId);
}

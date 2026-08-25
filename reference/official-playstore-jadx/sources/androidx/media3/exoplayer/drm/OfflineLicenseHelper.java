package androidx.media3.exoplayer.drm;

import android.net.Uri;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.util.concurrent.q1;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public final class OfflineLicenseHelper {
    private static final Format FORMAT_WITH_EMPTY_DRM_INIT_DATA = new Format.Builder().setDrmInitData(new DrmInitData(new DrmInitData.SchemeData[0])).build();
    private final ConditionVariable drmListenerConditionVariable;
    private final DefaultDrmSessionManager drmSessionManager;
    private final DrmSessionEventListener.EventDispatcher eventDispatcher;
    private final Handler handler;
    private final HandlerThread handlerThread;

    public OfflineLicenseHelper(DefaultDrmSessionManager defaultDrmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = defaultDrmSessionManager;
        this.eventDispatcher = eventDispatcher;
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:OfflineLicenseHelper");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
        this.drmListenerConditionVariable = new ConditionVariable();
        eventDispatcher.addEventListener(new Handler(handlerThread.getLooper()), new DrmSessionEventListener() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper.1
            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public final /* synthetic */ void onDrmKeysLoaded(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
                f.a(this, i10, mediaPeriodId);
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysRemoved(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysRestored(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public final /* synthetic */ void onDrmSessionAcquired(int i10, MediaSource.MediaPeriodId mediaPeriodId, int i11) {
                f.e(this, i10, mediaPeriodId, i11);
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmSessionManagerError(int i10, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public final /* synthetic */ void onDrmSessionReleased(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
                f.g(this, i10, mediaPeriodId);
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysLoaded(int i10, MediaSource.MediaPeriodId mediaPeriodId, KeyRequestInfo keyRequestInfo) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }
        });
    }

    private DrmSession acquireFirstSessionOnHandlerThread(final int i10, final byte[] bArr, final Format format) throws DrmSession.DrmSessionException {
        format.drmInitData.getClass();
        final q1 q1Var = new q1();
        this.drmListenerConditionVariable.close();
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f3616i.lambda$acquireFirstSessionOnHandlerThread$2(i10, bArr, q1Var, format);
            }
        });
        try {
            DrmSession drmSession = (DrmSession) q1Var.get();
            this.drmListenerConditionVariable.block();
            q1 q1Var2 = new q1();
            this.handler.post(new n(drmSession, this, q1Var2));
            try {
                if (q1Var2.get() == null) {
                    return drmSession;
                }
                throw ((DrmSession.DrmSessionException) q1Var2.get());
            } catch (InterruptedException | ExecutionException e5) {
                throw new IllegalStateException(e5);
            }
        } catch (InterruptedException | ExecutionException e6) {
            throw new IllegalStateException(e6);
        }
    }

    private byte[] acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(int i10, byte[] bArr, Format format) throws DrmSession.DrmSessionException {
        DrmSession drmSessionAcquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(i10, bArr, format);
        q1 q1Var = new q1();
        this.handler.post(new n(this, q1Var, drmSessionAcquireFirstSessionOnHandlerThread, 2));
        try {
            try {
                byte[] bArr2 = (byte[]) q1Var.get();
                bArr2.getClass();
                return bArr2;
            } finally {
                releaseManagerOnHandlerThread();
            }
        } catch (InterruptedException | ExecutionException e5) {
            throw new IllegalStateException(e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$acquireFirstSessionOnHandlerThread$2(int i10, byte[] bArr, q1 q1Var, Format format) {
        try {
            DefaultDrmSessionManager defaultDrmSessionManager = this.drmSessionManager;
            Looper looperMyLooper = Looper.myLooper();
            looperMyLooper.getClass();
            defaultDrmSessionManager.setPlayer(looperMyLooper, PlayerId.UNSET);
            this.drmSessionManager.prepare();
            try {
                this.drmSessionManager.setMode(i10, bArr);
                DrmSession drmSessionAcquireSession = this.drmSessionManager.acquireSession(this.eventDispatcher, format);
                drmSessionAcquireSession.getClass();
                q1Var.set(drmSessionAcquireSession);
            } finally {
            }
        } catch (Throwable th) {
            q1Var.setException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acquireFirstSessionOnHandlerThread$3(DrmSession drmSession, q1 q1Var) {
        try {
            DrmSession.DrmSessionException error = drmSession.getError();
            if (drmSession.getState() == 1) {
                drmSession.release(this.eventDispatcher);
                this.drmSessionManager.release();
            }
            q1Var.set(error);
        } catch (Throwable th) {
            q1Var.setException(th);
            drmSession.release(this.eventDispatcher);
            this.drmSessionManager.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1(q1 q1Var, DrmSession drmSession) {
        try {
            q1Var.set(drmSession.getOfflineLicenseKeySetId());
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$getLicenseDurationRemainingSec$0(q1 q1Var, DrmSession drmSession) {
        try {
            Pair<Long, Long> licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(drmSession);
            licenseDurationRemainingSec.getClass();
            q1Var.set(licenseDurationRemainingSec);
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseManagerOnHandlerThread$4(q1 q1Var) {
        try {
            this.drmSessionManager.release();
            q1Var.set(null);
        } catch (Throwable th) {
            q1Var.setException(th);
        }
    }

    public static OfflineLicenseHelper newWidevineInstance(MediaItem.DrmConfiguration drmConfiguration, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        ac.b.j(drmConfiguration.scheme.equals(C.WIDEVINE_UUID));
        Uri uri = drmConfiguration.licenseUri;
        uri.getClass();
        return newWidevineInstance(uri.toString(), drmConfiguration.forceDefaultLicenseUri, drmConfiguration.licenseRequestHeaders, factory, null, eventDispatcher);
    }

    private void releaseManagerOnHandlerThread() {
        q1 q1Var = new q1();
        this.handler.post(new c(this, q1Var, 1));
        try {
            q1Var.get();
        } catch (InterruptedException | ExecutionException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public synchronized byte[] downloadLicense(Format format) throws DrmSession.DrmSessionException {
        ac.b.j(format.drmInitData != null);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, null, format);
    }

    public synchronized Pair<Long, Long> getLicenseDurationRemainingSec(byte[] bArr) throws DrmSession.DrmSessionException {
        q1 q1Var;
        bArr.getClass();
        try {
            DrmSession drmSessionAcquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(1, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
            q1Var = new q1();
            this.handler.post(new n(this, q1Var, drmSessionAcquireFirstSessionOnHandlerThread, 0));
            try {
                try {
                } finally {
                    releaseManagerOnHandlerThread();
                }
            } catch (InterruptedException | ExecutionException e5) {
                throw new IllegalStateException(e5);
            }
        } catch (DrmSession.DrmSessionException e6) {
            if (e6.getCause() instanceof KeysExpiredException) {
                return Pair.create(0L, 0L);
            }
            throw e6;
        }
        return (Pair) q1Var.get();
    }

    public void release() {
        this.handlerThread.quit();
    }

    public synchronized void releaseLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        bArr.getClass();
        acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(3, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public synchronized byte[] renewLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        bArr.getClass();
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(str, false, factory, eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(str, z, factory, null, eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, Map<String, String> map, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(str, z, null, factory, map, eventDispatcher);
    }

    private static OfflineLicenseHelper newWidevineInstance(String str, boolean z, Map<String, String> map, DataSource.Factory factory, Map<String, String> map2, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(str, z, factory);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpMediaDrmCallback.setKeyRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return new OfflineLicenseHelper(new DefaultDrmSessionManager.Builder().setKeyRequestParameters(map2).build(httpMediaDrmCallback), eventDispatcher);
    }
}

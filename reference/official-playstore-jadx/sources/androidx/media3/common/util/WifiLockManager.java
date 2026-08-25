package androidx.media3.common.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Looper;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class WifiLockManager {
    private static final String TAG = "WifiLockManager";
    private static final int UNREACTIVE_WIFILOCK_HANDLER_RELEASE_DELAY_MS = 1000;
    private static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    private boolean enabled;
    private final HandlerWrapper mainHandler;
    private boolean stayAwake;
    private final HandlerWrapper wifiLockHandler;
    private final WifiLockManagerInternal wifiLockManagerInternal;

    public static final class WifiLockManagerInternal {
        private final Context applicationContext;
        private WifiManager.WifiLock wifiLock;

        public WifiLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public static /* synthetic */ void access$100(WifiLockManagerInternal wifiLockManagerInternal) {
            wifiLockManagerInternal.forceReleaseWifiLock();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void forceReleaseWifiLock() {
            WifiManager.WifiLock wifiLock = this.wifiLock;
            if (wifiLock != null) {
                wifiLock.release();
            }
        }

        public void updateWifiLock(boolean z, boolean z5) {
            if (z && this.wifiLock == null) {
                if (this.applicationContext.checkSelfPermission("android.permission.WAKE_LOCK") != 0) {
                    Log.w(WifiLockManager.TAG, "WAKE_LOCK permission not granted, can't acquire wake lock for playback");
                    return;
                }
                WifiManager wifiManager = (WifiManager) this.applicationContext.getApplicationContext().getSystemService("wifi");
                if (wifiManager == null) {
                    Log.w(WifiLockManager.TAG, "WifiManager is null, therefore not creating the WifiLock.");
                    return;
                } else {
                    WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager.createWifiLock(3, WifiLockManager.WIFI_LOCK_TAG);
                    this.wifiLock = wifiLockCreateWifiLock;
                    wifiLockCreateWifiLock.setReferenceCounted(false);
                }
            }
            if (this.wifiLock == null) {
                return;
            }
            if (WifiLockManager.shouldAcquireWifilock(z, z5)) {
                this.wifiLock.acquire();
            } else {
                this.wifiLock.release();
            }
        }
    }

    public WifiLockManager(Context context, Looper looper, Clock clock) {
        this.wifiLockManagerInternal = new WifiLockManagerInternal(context.getApplicationContext());
        this.wifiLockHandler = clock.createHandler(looper, null);
        this.mainHandler = clock.createHandler(Looper.getMainLooper(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postUpdateWifiLock$0(boolean z, boolean z5) {
        this.wifiLockManagerInternal.updateWifiLock(z, z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postUpdateWifiLock$2(Runnable runnable, boolean z, boolean z5) {
        this.mainHandler.removeCallbacks(runnable);
        this.wifiLockManagerInternal.updateWifiLock(z, z5);
    }

    private void postUpdateWifiLock(boolean z, boolean z5) {
        if (shouldAcquireWifilock(z, z5)) {
            this.wifiLockHandler.post(new j(this, z, z5, 1));
            return;
        }
        WifiLockManagerInternal wifiLockManagerInternal = this.wifiLockManagerInternal;
        Objects.requireNonNull(wifiLockManagerInternal);
        e eVar = new e(wifiLockManagerInternal, 2);
        this.mainHandler.postDelayed(eVar, 1000L);
        this.wifiLockHandler.post(new k(this, eVar, z, z5, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldAcquireWifilock(boolean z, boolean z5) {
        return z && z5;
    }

    public void setEnabled(boolean z) {
        if (this.enabled == z) {
            return;
        }
        this.enabled = z;
        postUpdateWifiLock(z, this.stayAwake);
    }

    public void setStayAwake(boolean z) {
        if (this.stayAwake == z) {
            return;
        }
        this.stayAwake = z;
        if (this.enabled) {
            postUpdateWifiLock(true, z);
        }
    }
}

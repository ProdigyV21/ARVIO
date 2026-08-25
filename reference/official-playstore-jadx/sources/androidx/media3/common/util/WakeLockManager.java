package androidx.media3.common.util;

import android.content.Context;
import android.os.Looper;
import android.os.PowerManager;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class WakeLockManager {
    private static final String TAG = "WakeLockManager";
    private static final int UNREACTIVE_WAKELOCK_HANDLER_RELEASE_DELAY_MS = 1000;
    private static final String WAKE_LOCK_TAG = "ExoPlayer:WakeLockManager";
    private boolean enabled;
    private final HandlerWrapper mainHandler;
    private boolean stayAwake;
    private final HandlerWrapper wakeLockHandler;
    private final WakeLockManagerInternal wakeLockManagerInternal;

    public static final class WakeLockManagerInternal {
        private final Context applicationContext;
        private PowerManager.WakeLock wakeLock;

        public WakeLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public static /* synthetic */ void access$200(WakeLockManagerInternal wakeLockManagerInternal) {
            wakeLockManagerInternal.forceReleaseWakeLock();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void forceReleaseWakeLock() {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            if (wakeLock != null) {
                wakeLock.release();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void updateWakeLock(boolean z, boolean z5) {
            if (z) {
                if (this.wakeLock == null) {
                    if (this.applicationContext.checkSelfPermission("android.permission.WAKE_LOCK") != 0) {
                        Log.w(WakeLockManager.TAG, "WAKE_LOCK permission not granted, can't acquire wake lock for playback");
                        return;
                    }
                    PowerManager powerManager = (PowerManager) this.applicationContext.getSystemService("power");
                    if (powerManager == null) {
                        Log.w(WakeLockManager.TAG, "PowerManager is null, therefore not creating the WakeLock.");
                        return;
                    } else {
                        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, WakeLockManager.WAKE_LOCK_TAG);
                        this.wakeLock = wakeLockNewWakeLock;
                        wakeLockNewWakeLock.setReferenceCounted(false);
                    }
                }
            }
            if (this.wakeLock == null) {
                return;
            }
            if (WakeLockManager.shouldAcquireWakelock(z, z5)) {
                this.wakeLock.acquire();
            } else {
                this.wakeLock.release();
            }
        }
    }

    public WakeLockManager(Context context, Looper looper, Clock clock) {
        this.wakeLockManagerInternal = new WakeLockManagerInternal(context.getApplicationContext());
        this.wakeLockHandler = clock.createHandler(looper, null);
        this.mainHandler = clock.createHandler(Looper.getMainLooper(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postUpdateWakeLock$0(boolean z, boolean z5) {
        this.wakeLockManagerInternal.updateWakeLock(z, z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postUpdateWakeLock$2(Runnable runnable, boolean z, boolean z5) {
        this.mainHandler.removeCallbacks(runnable);
        this.wakeLockManagerInternal.updateWakeLock(z, z5);
    }

    private void postUpdateWakeLock(boolean z, boolean z5) {
        if (shouldAcquireWakelock(z, z5)) {
            this.wakeLockHandler.post(new j(this, z, z5, 0));
            return;
        }
        WakeLockManagerInternal wakeLockManagerInternal = this.wakeLockManagerInternal;
        Objects.requireNonNull(wakeLockManagerInternal);
        e eVar = new e(wakeLockManagerInternal, 1);
        this.mainHandler.postDelayed(eVar, 1000L);
        this.wakeLockHandler.post(new k(this, eVar, z, z5, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldAcquireWakelock(boolean z, boolean z5) {
        return z && z5;
    }

    public void setEnabled(boolean z) {
        if (this.enabled == z) {
            return;
        }
        this.enabled = z;
        postUpdateWakeLock(z, this.stayAwake);
    }

    public void setStayAwake(boolean z) {
        if (this.stayAwake == z) {
            return;
        }
        this.stayAwake = z;
        if (this.enabled) {
            postUpdateWakeLock(true, z);
        }
    }
}

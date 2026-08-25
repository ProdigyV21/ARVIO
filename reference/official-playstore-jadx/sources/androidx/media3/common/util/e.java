package androidx.media3.common.util;

import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.WakeLockManager;
import androidx.media3.common.util.WifiLockManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3414i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3415l;

    public /* synthetic */ e(Object obj, int i10) {
        this.f3414i = i10;
        this.f3415l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3414i) {
            case 0:
                ((NetworkTypeObserver.ListenerHolder) this.f3415l).lambda$callOnNetworkTypeChanged$0();
                break;
            case 1:
                WakeLockManager.WakeLockManagerInternal.access$200((WakeLockManager.WakeLockManagerInternal) this.f3415l);
                break;
            default:
                WifiLockManager.WifiLockManagerInternal.access$100((WifiLockManager.WifiLockManagerInternal) this.f3415l);
                break;
        }
    }
}

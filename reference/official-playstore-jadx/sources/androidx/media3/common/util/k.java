package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3426i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f3427l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3428m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3429n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Runnable f3430o;

    public /* synthetic */ k(Object obj, Runnable runnable, boolean z, boolean z5, int i10) {
        this.f3426i = i10;
        this.f3429n = obj;
        this.f3430o = runnable;
        this.f3427l = z;
        this.f3428m = z5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3426i) {
            case 0:
                ((WakeLockManager) this.f3429n).lambda$postUpdateWakeLock$2((e) this.f3430o, this.f3427l, this.f3428m);
                break;
            default:
                ((WifiLockManager) this.f3429n).lambda$postUpdateWifiLock$2((e) this.f3430o, this.f3427l, this.f3428m);
                break;
        }
    }
}

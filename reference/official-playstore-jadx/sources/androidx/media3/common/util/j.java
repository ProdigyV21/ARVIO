package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3422i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f3423l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3424m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3425n;

    public /* synthetic */ j(Object obj, boolean z, boolean z5, int i10) {
        this.f3422i = i10;
        this.f3425n = obj;
        this.f3423l = z;
        this.f3424m = z5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3422i) {
            case 0:
                ((WakeLockManager) this.f3425n).lambda$postUpdateWakeLock$0(this.f3423l, this.f3424m);
                break;
            default:
                ((WifiLockManager) this.f3425n).lambda$postUpdateWifiLock$0(this.f3423l, this.f3424m);
                break;
        }
    }
}

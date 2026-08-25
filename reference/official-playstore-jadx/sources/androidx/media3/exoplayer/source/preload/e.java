package androidx.media3.exoplayer.source.preload;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3791i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3792l;

    public /* synthetic */ e(Object obj, int i10) {
        this.f3791i = i10;
        this.f3792l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        switch (this.f3791i) {
            case 0:
                ((DefaultPreloadManager) this.f3792l).lambda$releasePreloadUtils$2();
                break;
            default:
                ((PreCacheHelper) this.f3792l).lambda$stop$1();
                break;
        }
    }
}

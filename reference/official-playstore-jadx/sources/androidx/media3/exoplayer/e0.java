package androidx.media3.exoplayer;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3621i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f3622l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3623m;

    public /* synthetic */ e0(Object obj, int i10, int i11) {
        this.f3621i = i11;
        this.f3623m = obj;
        this.f3622l = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3621i) {
            case 0:
                ((ExoPlayerImplInternal) this.f3623m).lambda$setScrubbingModeEnabledInternal$2(this.f3622l);
                break;
            default:
                ((StreamVolumeManager) this.f3623m).lambda$new$0(this.f3622l);
                break;
        }
    }
}

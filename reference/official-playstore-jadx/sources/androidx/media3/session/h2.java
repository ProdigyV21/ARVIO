package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4010i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionImpl f4011l;

    public /* synthetic */ h2(MediaSessionImpl mediaSessionImpl, int i10) {
        this.f4010i = i10;
        this.f4011l = mediaSessionImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4010i) {
            case 0:
                this.f4011l.lambda$onNotificationRefreshRequired$20();
                break;
            case 1:
                this.f4011l.notifyPeriodicSessionPositionInfoChangesOnHandler();
                break;
            case 2:
                this.f4011l.schedulePeriodicSessionPositionInfoChanges();
                break;
            default:
                this.f4011l.lambda$release$2();
                break;
        }
    }
}

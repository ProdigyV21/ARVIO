package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3408i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ BackgroundThreadStateHandler f3409l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3410m;

    public /* synthetic */ a(BackgroundThreadStateHandler backgroundThreadStateHandler, Object obj, int i10) {
        this.f3408i = i10;
        this.f3409l = backgroundThreadStateHandler;
        this.f3410m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3408i) {
            case 0:
                this.f3409l.lambda$setStateInBackground$2(this.f3410m);
                break;
            default:
                this.f3409l.lambda$updateStateAsync$0(this.f3410m);
                break;
        }
    }
}

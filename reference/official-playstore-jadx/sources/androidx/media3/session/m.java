package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4071i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerHolder f4072l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaController f4073m;

    public /* synthetic */ m(MediaControllerHolder mediaControllerHolder, MediaController mediaController, int i10) {
        this.f4071i = i10;
        this.f4072l = mediaControllerHolder;
        this.f4073m = mediaController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4071i) {
            case 0:
                this.f4072l.setController(this.f4073m);
                break;
            default:
                this.f4072l.lambda$setController$0(this.f4073m);
                break;
        }
    }
}

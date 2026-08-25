package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4074i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4075l;

    public /* synthetic */ m0(MediaControllerImplBase mediaControllerImplBase, int i10) {
        this.f4074i = i10;
        this.f4075l = mediaControllerImplBase;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4074i) {
            case 0:
                this.f4075l.lambda$setFutureResult$106();
                break;
            default:
                this.f4075l.lambda$release$4();
                break;
        }
    }
}

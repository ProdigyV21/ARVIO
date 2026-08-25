package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3963i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplLegacy f3964l;

    public /* synthetic */ e1(MediaControllerImplLegacy mediaControllerImplLegacy, int i10) {
        this.f3963i = i10;
        this.f3964l = mediaControllerImplLegacy;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3963i) {
            case 0:
                this.f3964l.lambda$connectToSession$2();
                break;
            default:
                this.f3964l.lambda$connectToService$3();
                break;
        }
    }
}

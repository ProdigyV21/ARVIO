package androidx.fragment.app;

/* JADX INFO: loaded from: classes3.dex */
public final class s implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2833i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c0 f2834l;

    public /* synthetic */ s(c0 c0Var, int i10) {
        this.f2833i = i10;
        this.f2834l = c0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2833i) {
            case 0:
                this.f2834l.startPostponedEnterTransition();
                break;
            default:
                this.f2834l.callStartTransitionListener(false);
                break;
        }
    }
}

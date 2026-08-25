package androidx.compose.ui.platform;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1932i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1933l;

    public /* synthetic */ e(Object obj, int i10) {
        this.f1932i = i10;
        this.f1933l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1932i) {
            case 0:
                ((r7.a) this.f1933l).invoke();
                break;
            case 1:
                AndroidComposeView.sendHoverExitEvent$lambda$5((AndroidComposeView) this.f1933l);
                break;
            default:
                AndroidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker$lambda$46((AndroidComposeViewAccessibilityDelegateCompat) this.f1933l);
                break;
        }
    }
}

package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1555i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f1556l;

    public /* synthetic */ e(ActionBarOverlayLayout actionBarOverlayLayout, int i10) {
        this.f1555i = i10;
        this.f1556l = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1555i) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1556l;
                actionBarOverlayLayout.k();
                actionBarOverlayLayout.G = actionBarOverlayLayout.f1427n.animate().translationY(0.0f).setListener(actionBarOverlayLayout.H);
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1556l;
                actionBarOverlayLayout2.k();
                actionBarOverlayLayout2.G = actionBarOverlayLayout2.f1427n.animate().translationY(-actionBarOverlayLayout2.f1427n.getHeight()).setListener(actionBarOverlayLayout2.H);
                break;
        }
    }
}

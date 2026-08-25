package androidx.media3.ui;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4246i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlayerControlViewLayoutManager f4247l;

    public /* synthetic */ f(PlayerControlViewLayoutManager playerControlViewLayoutManager, int i10) {
        this.f4246i = i10;
        this.f4247l = playerControlViewLayoutManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4246i) {
            case 0:
                this.f4247l.updateLayoutForSizeChange();
                break;
            case 1:
                this.f4247l.onLayoutWidthChanged();
                break;
            case 2:
                this.f4247l.showAllBars();
                break;
            case 3:
                this.f4247l.hideAllBars();
                break;
            case 4:
                this.f4247l.hideProgressBar();
                break;
            case 5:
                this.f4247l.hideMainBar();
                break;
            default:
                this.f4247l.hideController();
                break;
        }
    }
}

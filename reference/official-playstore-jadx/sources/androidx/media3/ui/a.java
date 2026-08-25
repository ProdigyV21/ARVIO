package androidx.media3.ui;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4234i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ View f4235l;

    public /* synthetic */ a(View view, int i10) {
        this.f4234i = i10;
        this.f4235l = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4234i) {
            case 0:
                ((DefaultTimeBar) this.f4235l).lambda$new$0();
                break;
            case 1:
                ((PlayerControlView) this.f4235l).updateProgress();
                break;
            default:
                ((PlayerView) this.f4235l).invalidate();
                break;
        }
    }
}

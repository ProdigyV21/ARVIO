package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.IntroductoryOverlay;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13434i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n0 f13435l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Activity f13436m;

    public /* synthetic */ m0(n0 n0Var, Activity activity, int i10) {
        this.f13434i = i10;
        this.f13435l = n0Var;
        this.f13436m = activity;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        switch (this.f13434i) {
            case 0:
                o0 o0Var = this.f13435l.f13482c;
                if (o0Var.f13502p) {
                    ((ViewGroup) this.f13436m.getWindow().getDecorView()).removeView(o0Var);
                    IntroductoryOverlay.OnOverlayDismissedListener onOverlayDismissedListener = o0Var.f13499m;
                    if (onOverlayDismissedListener != null) {
                        onOverlayDismissedListener.onOverlayDismissed();
                    }
                    o0Var.a();
                }
                break;
            default:
                o0 o0Var2 = this.f13435l.f13482c;
                if (o0Var2.f13502p) {
                    ((ViewGroup) this.f13436m.getWindow().getDecorView()).removeView(o0Var2);
                    IntroductoryOverlay.OnOverlayDismissedListener onOverlayDismissedListener2 = o0Var2.f13499m;
                    if (onOverlayDismissedListener2 != null) {
                        onOverlayDismissedListener2.onOverlayDismissed();
                    }
                    o0Var2.a();
                }
                break;
        }
    }
}

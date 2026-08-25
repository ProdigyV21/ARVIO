package androidx.recyclerview.widget;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5000i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f5001l;

    public /* synthetic */ n(Object obj, int i10) {
        this.f5000i = i10;
        this.f5001l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i10 = this.f5000i;
        Object obj = this.f5001l;
        switch (i10) {
            case 0:
                r rVar = (r) obj;
                ValueAnimator valueAnimator = rVar.z;
                int i11 = rVar.A;
                if (i11 == 1) {
                    valueAnimator.cancel();
                } else if (i11 != 2) {
                }
                rVar.A = 3;
                valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
                valueAnimator.setDuration(500);
                valueAnimator.start();
                break;
            default:
                ((StaggeredGridLayoutManager) obj).M0();
                break;
        }
    }
}

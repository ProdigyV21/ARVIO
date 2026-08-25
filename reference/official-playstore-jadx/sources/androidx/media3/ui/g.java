package androidx.media3.ui;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4249b;

    public /* synthetic */ g(Object obj, int i10) {
        this.f4248a = i10;
        this.f4249b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f4248a) {
            case 0:
                ((PlayerControlViewLayoutManager) this.f4249b).lambda$new$0(valueAnimator);
                break;
            case 1:
                ((PlayerControlViewLayoutManager) this.f4249b).lambda$new$1(valueAnimator);
                break;
            case 2:
                ((PlayerControlViewLayoutManager) this.f4249b).lambda$new$2(valueAnimator);
                break;
            case 3:
                ((PlayerControlViewLayoutManager) this.f4249b).lambda$new$3(valueAnimator);
                break;
            default:
                ((DefaultTimeBar) this.f4249b).lambda$new$1(valueAnimator);
                break;
        }
    }
}

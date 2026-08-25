package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
class SlideKitkat extends Visibility {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f2909n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final d f2910o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final c f2911p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final d f2912q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f2915i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final DecelerateInterpolator f2907l = new DecelerateInterpolator();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AccelerateInterpolator f2908m = new AccelerateInterpolator();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final c f2913r = new c(2);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final c f2914s = new c(3);

    public interface a {
        Property c();

        float d(View view);

        float e(View view);
    }

    static {
        int i10 = 0;
        f2909n = new c(i10);
        f2910o = new d(i10);
        int i11 = 1;
        f2911p = new c(i11);
        f2912q = new d(i11);
    }

    public SlideKitkat(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k1.a.f19459i);
        int i10 = typedArrayObtainStyledAttributes.getInt(3, 80);
        if (i10 == 3) {
            this.f2915i = f2909n;
        } else if (i10 == 5) {
            this.f2915i = f2911p;
        } else if (i10 == 48) {
            this.f2915i = f2910o;
        } else if (i10 == 80) {
            this.f2915i = f2912q;
        } else if (i10 == 8388611) {
            this.f2915i = f2913r;
        } else {
            if (i10 != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.f2915i = f2914s;
        }
        long j10 = typedArrayObtainStyledAttributes.getInt(1, -1);
        if (j10 >= 0) {
            setDuration(j10);
        }
        long j11 = typedArrayObtainStyledAttributes.getInt(2, -1);
        if (j11 > 0) {
            setStartDelay(j11);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static ObjectAnimator a(View view, Property property, float f10, float f11, float f12, TimeInterpolator timeInterpolator, int i10) {
        float[] fArr = (float[]) view.getTag(R.id.lb_slide_transition_value);
        if (fArr != null) {
            f10 = View.TRANSLATION_Y == property ? fArr[1] : fArr[0];
            view.setTag(R.id.lb_slide_transition_value, null);
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, f10, f11);
        g gVar = new g(view, property, f12, f11, i10);
        objectAnimatorOfFloat.addListener(gVar);
        objectAnimatorOfFloat.addPauseListener(gVar);
        objectAnimatorOfFloat.setInterpolator(timeInterpolator);
        return objectAnimatorOfFloat;
    }

    @Override // android.transition.Visibility
    public final Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i10, TransitionValues transitionValues2, int i11) {
        View view = transitionValues2 != null ? transitionValues2.view : null;
        if (view == null) {
            return null;
        }
        float fE = this.f2915i.e(view);
        return a(view, this.f2915i.c(), this.f2915i.d(view), fE, fE, f2907l, 0);
    }

    @Override // android.transition.Visibility
    public final Animator onDisappear(ViewGroup viewGroup, TransitionValues transitionValues, int i10, TransitionValues transitionValues2, int i11) {
        View view = transitionValues != null ? transitionValues.view : null;
        if (view == null) {
            return null;
        }
        float fE = this.f2915i.e(view);
        return a(view, this.f2915i.c(), fE, this.f2915i.d(view), fE, f2908m, 4);
    }
}

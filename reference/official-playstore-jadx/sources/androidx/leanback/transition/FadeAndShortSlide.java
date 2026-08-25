package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes3.dex */
public class FadeAndShortSlide extends Visibility {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final DecelerateInterpolator f2898n = new DecelerateInterpolator();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f2899o = new a(0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f2900p = new a(1);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final a f2901q = new a(2);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f2902r = new a(3);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a f2903s = new a(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xc.d f2904i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Visibility f2905l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f2906m;

    public FadeAndShortSlide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2905l = new Fade();
        this.f2906m = -1.0f;
        b bVar = new b(this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k1.a.f19459i);
        int i10 = typedArrayObtainStyledAttributes.getInt(3, 8388611);
        if (i10 == 48) {
            this.f2904i = f2903s;
        } else if (i10 == 80) {
            this.f2904i = f2902r;
        } else if (i10 == 112) {
            this.f2904i = bVar;
        } else if (i10 == 8388611) {
            this.f2904i = f2899o;
        } else if (i10 == 8388613) {
            this.f2904i = f2900p;
        } else {
            if (i10 != 8388615) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.f2904i = f2901q;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final float a(ViewGroup viewGroup) {
        float f10 = this.f2906m;
        return f10 >= 0.0f ? f10 : viewGroup.getWidth() / 4;
    }

    @Override // android.transition.Transition
    public final Transition addListener(Transition.TransitionListener transitionListener) {
        this.f2905l.addListener(transitionListener);
        return super.addListener(transitionListener);
    }

    public final float b(ViewGroup viewGroup) {
        float f10 = this.f2906m;
        return f10 >= 0.0f ? f10 : viewGroup.getHeight() / 4;
    }

    @Override // android.transition.Visibility, android.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        this.f2905l.captureEndValues(transitionValues);
        super.captureEndValues(transitionValues);
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:fadeAndShortSlideTransition:screenPosition", iArr);
    }

    @Override // android.transition.Visibility, android.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        this.f2905l.captureStartValues(transitionValues);
        super.captureStartValues(transitionValues);
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:fadeAndShortSlideTransition:screenPosition", iArr);
    }

    @Override // android.transition.Visibility
    public final Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null || viewGroup == view) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.values.get("android:fadeAndShortSlideTransition:screenPosition");
        int i10 = iArr[0];
        int i11 = iArr[1];
        float translationX = view.getTranslationX();
        ObjectAnimator objectAnimatorH = a.a.H(view, transitionValues2, i10, i11, this.f2904i.k0(this, viewGroup, view, iArr), this.f2904i.l0(this, viewGroup, view, iArr), translationX, view.getTranslationY(), f2898n, this);
        Animator animatorOnAppear = this.f2905l.onAppear(viewGroup, view, transitionValues, transitionValues2);
        if (objectAnimatorH == null) {
            return animatorOnAppear;
        }
        if (animatorOnAppear == null) {
            return objectAnimatorH;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorH).with(animatorOnAppear);
        return animatorSet;
    }

    @Override // android.transition.Visibility
    public final Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || viewGroup == view) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.values.get("android:fadeAndShortSlideTransition:screenPosition");
        ObjectAnimator objectAnimatorH = a.a.H(view, transitionValues, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.f2904i.k0(this, viewGroup, view, iArr), this.f2904i.l0(this, viewGroup, view, iArr), f2898n, this);
        Animator animatorOnDisappear = this.f2905l.onDisappear(viewGroup, view, transitionValues, transitionValues2);
        if (objectAnimatorH == null) {
            return animatorOnDisappear;
        }
        if (animatorOnDisappear == null) {
            return objectAnimatorH;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorH).with(animatorOnDisappear);
        return animatorSet;
    }

    @Override // android.transition.Transition
    public final Transition removeListener(Transition.TransitionListener transitionListener) {
        this.f2905l.removeListener(transitionListener);
        return super.removeListener(transitionListener);
    }

    @Override // android.transition.Transition
    public final void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        this.f2905l.setEpicenterCallback(epicenterCallback);
        super.setEpicenterCallback(epicenterCallback);
    }

    @Override // android.transition.Transition
    public final Transition clone() {
        FadeAndShortSlide fadeAndShortSlide = (FadeAndShortSlide) super.clone();
        fadeAndShortSlide.f2905l = (Visibility) this.f2905l.clone();
        return fadeAndShortSlide;
    }
}

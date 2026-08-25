package l;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class c extends qb.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ObjectAnimator f20005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20006b;

    public c(AnimationDrawable animationDrawable, boolean z, boolean z5) {
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        int i10 = z ? numberOfFrames - 1 : 0;
        int i11 = z ? 0 : numberOfFrames - 1;
        d dVar = new d();
        int numberOfFrames2 = animationDrawable.getNumberOfFrames();
        dVar.f20008b = numberOfFrames2;
        int[] iArr = dVar.f20007a;
        if (iArr == null || iArr.length < numberOfFrames2) {
            dVar.f20007a = new int[numberOfFrames2];
        }
        int[] iArr2 = dVar.f20007a;
        int i12 = 0;
        for (int i13 = 0; i13 < numberOfFrames2; i13++) {
            int duration = animationDrawable.getDuration(z ? (numberOfFrames2 - i13) - 1 : i13);
            iArr2[i13] = duration;
            i12 += duration;
        }
        dVar.f20009c = i12;
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i10, i11);
        m.a.a(objectAnimatorOfInt, true);
        objectAnimatorOfInt.setDuration(dVar.f20009c);
        objectAnimatorOfInt.setInterpolator(dVar);
        this.f20006b = z5;
        this.f20005a = objectAnimatorOfInt;
    }

    @Override // qb.d
    public final void O() {
        this.f20005a.reverse();
    }

    @Override // qb.d
    public final void T() {
        this.f20005a.start();
    }

    @Override // qb.d
    public final void W() {
        this.f20005a.cancel();
    }

    @Override // qb.d
    public final boolean d() {
        return this.f20006b;
    }
}

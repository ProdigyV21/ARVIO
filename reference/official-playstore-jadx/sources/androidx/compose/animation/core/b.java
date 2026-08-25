package androidx.compose.animation.core;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Easing {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1874a;

    public /* synthetic */ b(int i10) {
        this.f1874a = i10;
    }

    @Override // androidx.compose.animation.core.Easing
    public final float transform(float f10) {
        switch (this.f1874a) {
            case 0:
                return EasingFunctionsKt.EaseInElastic$lambda$0(f10);
            case 1:
                return EasingFunctionsKt.EaseOutElastic$lambda$1(f10);
            case 2:
                return EasingFunctionsKt.EaseInOutElastic$lambda$2(f10);
            case 3:
                return EasingFunctionsKt.EaseOutBounce$lambda$3(f10);
            case 4:
                return EasingFunctionsKt.EaseInBounce$lambda$4(f10);
            case 5:
                return EasingFunctionsKt.EaseInOutBounce$lambda$5(f10);
            default:
                return EasingKt.LinearEasing$lambda$0(f10);
        }
    }
}

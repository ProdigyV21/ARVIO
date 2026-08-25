package androidx.tv.material3;

import android.view.accessibility.AccessibilityManager;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/AnimatedContentScope;", "", "activeItemIndex", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedContentScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CarouselKt$Carousel$5$2 extends kotlin.jvm.internal.r implements r7.r<AnimatedContentScope, Integer, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ AccessibilityManager f5568i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5569l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.r f5570m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f5571n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableState f5572o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableState f5573p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarouselKt$Carousel$5$2(AccessibilityManager accessibilityManager, int i10, r7.r rVar, FocusRequester focusRequester, MutableState mutableState, MutableState mutableState2) {
        super(4);
        this.f5568i = accessibilityManager;
        this.f5569l = i10;
        this.f5570m = rVar;
        this.f5571n = focusRequester;
        this.f5572o = mutableState;
        this.f5573p = mutableState2;
    }

    @Override // r7.r
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((AnimatedContentScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(AnimatedContentScope animatedContentScope, int i10, Composer composer, int i11) {
        AnimatedContentScope animatedContentScope2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-921634605, i11, -1, "androidx.tv.material3.Carousel.<anonymous>.<anonymous> (Carousel.kt:178)");
        }
        composer.startReplaceableGroup(898006182);
        boolean zChangedInstance = composer.changedInstance(this.f5568i) | composer.changedInstance(animatedContentScope);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            animatedContentScope2 = animatedContentScope;
            e0 e0Var = new e0(this.f5568i, this.f5571n, animatedContentScope2, this.f5572o, this.f5573p, null);
            composer.updateRememberedValue(e0Var);
            objRememberedValue = e0Var;
        } else {
            animatedContentScope2 = animatedContentScope;
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(x6.t0.f22605a, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue, composer, 6);
        int i12 = this.f5569l;
        if (i12 > 0) {
            if (i10 >= i12) {
                i10 = 0;
            }
            this.f5570m.invoke(animatedContentScope2, Integer.valueOf(i10), composer, Integer.valueOf(i11 & 14));
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}

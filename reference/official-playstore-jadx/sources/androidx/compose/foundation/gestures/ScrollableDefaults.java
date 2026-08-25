package androidx.compose.foundation.gestures;

import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.AndroidOverscroll_androidKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\r\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableDefaults;", "", "()V", "bringIntoViewSpec", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/FlingBehavior;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/OverscrollEffect;", "reverseDirection", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseScrolling", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScrollableDefaults {
    public static final int $stable = 0;
    public static final ScrollableDefaults INSTANCE = new ScrollableDefaults();

    private ScrollableDefaults() {
    }

    public final BringIntoViewSpec bringIntoViewSpec() {
        return BringIntoViewSpec.INSTANCE.getDefaultBringIntoViewSpec$foundation_release();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FlingBehavior flingBehavior(Composer composer, int i10) {
        composer.startReplaceableGroup(1107739818);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1107739818, i10, -1, "androidx.compose.foundation.gestures.ScrollableDefaults.flingBehavior (Scrollable.kt:538)");
        }
        DecayAnimationSpec decayAnimationSpecRememberSplineBasedDecay = SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay(composer, 0);
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(decayAnimationSpecRememberSplineBasedDecay);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DefaultFlingBehavior(decayAnimationSpecRememberSplineBasedDecay, null, 2, 0 == true ? 1 : 0);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        DefaultFlingBehavior defaultFlingBehavior = (DefaultFlingBehavior) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultFlingBehavior;
    }

    public final OverscrollEffect overscrollEffect(Composer composer, int i10) {
        composer.startReplaceableGroup(1809802212);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1809802212, i10, -1, "androidx.compose.foundation.gestures.ScrollableDefaults.overscrollEffect (Scrollable.kt:551)");
        }
        OverscrollEffect overscrollEffectRememberOverscrollEffect = AndroidOverscroll_androidKt.rememberOverscrollEffect(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return overscrollEffectRememberOverscrollEffect;
    }

    public final boolean reverseDirection(LayoutDirection layoutDirection, Orientation orientation, boolean reverseScrolling) {
        return (layoutDirection != LayoutDirection.Rtl || orientation == Orientation.Vertical) ? !reverseScrolling : reverseScrolling;
    }
}

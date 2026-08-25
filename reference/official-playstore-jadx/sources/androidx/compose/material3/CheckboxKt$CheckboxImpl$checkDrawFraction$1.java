package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/ui/state/ToggleableState;", "invoke", "(Landroidx/compose/animation/core/Transition$Segment;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/FiniteAnimationSpec;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CheckboxKt$CheckboxImpl$checkDrawFraction$1 extends r implements q<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>> {
    public static final CheckboxKt$CheckboxImpl$checkDrawFraction$1 INSTANCE = new CheckboxKt$CheckboxImpl$checkDrawFraction$1();

    public CheckboxKt$CheckboxImpl$checkDrawFraction$1() {
        super(3);
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((Transition.Segment<ToggleableState>) obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer, int i10) {
        composer.startReplaceableGroup(1373301606);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1373301606, i10, -1, "androidx.compose.material3.CheckboxImpl.<anonymous> (Checkbox.kt:267)");
        }
        ToggleableState initialState = segment.getInitialState();
        ToggleableState toggleableState = ToggleableState.Off;
        FiniteAnimationSpec<Float> finiteAnimationSpecTween$default = initialState == toggleableState ? AnimationSpecKt.tween$default(100, 0, null, 6, null) : segment.getTargetState() == toggleableState ? AnimationSpecKt.snap(100) : AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return finiteAnimationSpecTween$default;
    }
}

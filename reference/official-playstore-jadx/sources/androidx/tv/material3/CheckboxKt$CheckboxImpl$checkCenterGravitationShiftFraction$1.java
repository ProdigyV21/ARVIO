package androidx.tv.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/ui/state/ToggleableState;", "invoke", "(Landroidx/compose/animation/core/Transition$Segment;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/FiniteAnimationSpec;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 extends kotlin.jvm.internal.r implements r7.q<Transition.Segment<ToggleableState>, Composer, Integer, FiniteAnimationSpec<Float>> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1 f5590i = new CheckboxKt$CheckboxImpl$checkCenterGravitationShiftFraction$1(3);

    @Override // r7.q
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((Transition.Segment<ToggleableState>) obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<ToggleableState> segment, Composer composer, int i10) {
        composer.startReplaceableGroup(-1742268475);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1742268475, i10, -1, "androidx.tv.material3.CheckboxImpl.<anonymous> (Checkbox.kt:270)");
        }
        ToggleableState initialState = segment.getInitialState();
        ToggleableState toggleableState = ToggleableState.Off;
        FiniteAnimationSpec<Float> finiteAnimationSpecSnap$default = initialState == toggleableState ? AnimationSpecKt.snap$default(0, 1, null) : segment.getTargetState() == toggleableState ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return finiteAnimationSpecSnap$default;
    }
}

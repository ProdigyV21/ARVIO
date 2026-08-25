package androidx.tv.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\n²\u0006\f\u0010\b\u001a\u00020\u00078\nX\u008a\u0084\u0002²\u0006\f\u0010\t\u001a\u00020\u00018\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "", "scale", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "tvSurfaceScale", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/interaction/Interaction;", "interaction", "animatedScale", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurfaceScaleKt {
    public static final Modifier tvSurfaceScale(Modifier modifier, float f10, MutableInteractionSource mutableInteractionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(1720087802);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1720087802, i10, -1, "androidx.tv.material3.tvSurfaceScale (SurfaceScale.kt:36)");
        }
        Interaction interaction = (Interaction) SnapshotStateKt.collectAsState(mutableInteractionSource.getInteractions(), new FocusInteraction.Focus(), null, composer, FocusInteraction.Focus.$stable << 3, 2).getValue();
        int i11 = 300;
        if (!(interaction instanceof FocusInteraction.Focus)) {
            if (interaction instanceof FocusInteraction.Unfocus) {
                i11 = 500;
            } else if (interaction instanceof PressInteraction.Press) {
                i11 = MenuKt.InTransitionDuration;
            }
        }
        State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f10, AnimationSpecKt.tween$default(i11, 0, j2.k.f19124a, 2, null), 0.0f, "tv-surface-scale", null, composer, ((i10 >> 3) & 14) | 3072, 20);
        Modifier modifierM3633graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(modifier, stateAnimateFloatAsState.getValue().floatValue(), stateAnimateFloatAsState.getValue().floatValue(), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131068, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierM3633graphicsLayerAp8cVGQ$default;
    }
}

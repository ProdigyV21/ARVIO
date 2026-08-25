package com.arflix.tv.ui.components;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t²\u0006\n\u0010\n\u001a\u00020\u0005X\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/ui/components/ShimmerState;", "", "<init>", "()V", "cachedTranslation", "", "getShimmerBrush", "Landroidx/compose/ui/graphics/Brush;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Brush;", "app_playRelease", "translateAnim"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ShimmerState {
    private static float cachedTranslation;
    public static final ShimmerState INSTANCE = new ShimmerState();
    public static final int $stable = 8;

    private ShimmerState() {
    }

    private static final float getShimmerBrush$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    public final Brush getShimmerBrush(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-206066806, i10, -1, "com.arflix.tv.ui.components.ShimmerState.getShimmerBrush (SkeletonLoader.kt:46)");
        }
        State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("globalShimmer", composer, 6, 0), 0.0f, 1000.0f, AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1200, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "shimmerTranslate", composer, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
        Brush brushM3429linearGradientmHitzGk$default = Brush.Companion.m3429linearGradientmHitzGk$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(ColorKt.Color(4279571744L)), Color.m3462boximpl(ColorKt.Color(4280229674L)), Color.m3462boximpl(ColorKt.Color(4279571744L))), OffsetKt.Offset(getShimmerBrush$lambda$0(stateAnimateFloat) - 500.0f, 0.0f), OffsetKt.Offset(getShimmerBrush$lambda$0(stateAnimateFloat), 0.0f), 0, 8, (Object) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return brushM3429linearGradientmHitzGk$default;
    }
}

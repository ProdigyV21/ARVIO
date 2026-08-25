package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/material3/TooltipState;", "Landroidx/compose/material3/BasicTooltipState;", "transition", "Landroidx/compose/animation/core/MutableTransitionState;", "", "getTransition", "()Landroidx/compose/animation/core/MutableTransitionState;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TooltipState extends BasicTooltipState {
    MutableTransitionState<Boolean> getTransition();
}

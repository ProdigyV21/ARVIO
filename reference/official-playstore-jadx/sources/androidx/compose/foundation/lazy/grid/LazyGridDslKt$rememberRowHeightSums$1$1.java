package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/LazyGridSlots;", "Landroidx/compose/ui/unit/Density;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/unit/Density;J)Landroidx/compose/foundation/lazy/grid/LazyGridSlots;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridDslKt$rememberRowHeightSums$1$1 extends r implements p<Density, Constraints, LazyGridSlots> {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ GridCells $rows;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridDslKt$rememberRowHeightSums$1$1(PaddingValues paddingValues, GridCells gridCells, Arrangement.Vertical vertical) {
        super(2);
        this.$contentPadding = paddingValues;
        this.$rows = gridCells;
        this.$verticalArrangement = vertical;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m658invoke0kLqBqw((Density) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final LazyGridSlots m658invoke0kLqBqw(Density density, long j10) {
        if (Constraints.m5645getMaxHeightimpl(j10) == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("LazyHorizontalGrid's height should be bound by parent.");
        }
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10) - density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(this.$contentPadding.getBottom() + this.$contentPadding.getTop()));
        GridCells gridCells = this.$rows;
        Arrangement.Vertical vertical = this.$verticalArrangement;
        int[] iArrB1 = x.b1(gridCells.calculateCrossAxisCellSizes(density, iM5645getMaxHeightimpl, density.mo279roundToPx0680j_4(vertical.getSpacing())));
        int[] iArr = new int[iArrB1.length];
        vertical.arrange(density, iM5645getMaxHeightimpl, iArrB1, iArr);
        return new LazyGridSlots(iArrB1, iArr);
    }
}

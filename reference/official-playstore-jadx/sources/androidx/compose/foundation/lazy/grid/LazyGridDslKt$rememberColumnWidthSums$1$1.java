package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/LazyGridSlots;", "Landroidx/compose/ui/unit/Density;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/unit/Density;J)Landroidx/compose/foundation/lazy/grid/LazyGridSlots;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridDslKt$rememberColumnWidthSums$1$1 extends r implements p<Density, Constraints, LazyGridSlots> {
    final /* synthetic */ GridCells $columns;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridDslKt$rememberColumnWidthSums$1$1(PaddingValues paddingValues, GridCells gridCells, Arrangement.Horizontal horizontal) {
        super(2);
        this.$contentPadding = paddingValues;
        this.$columns = gridCells;
        this.$horizontalArrangement = horizontal;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m657invoke0kLqBqw((Density) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final LazyGridSlots m657invoke0kLqBqw(Density density, long j10) {
        if (Constraints.m5646getMaxWidthimpl(j10) == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("LazyVerticalGrid's width should be bound by parent.");
        }
        PaddingValues paddingValues = this.$contentPadding;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10) - density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(PaddingKt.calculateEndPadding(this.$contentPadding, layoutDirection) + PaddingKt.calculateStartPadding(paddingValues, layoutDirection)));
        GridCells gridCells = this.$columns;
        Arrangement.Horizontal horizontal = this.$horizontalArrangement;
        int[] iArrB1 = x.b1(gridCells.calculateCrossAxisCellSizes(density, iM5646getMaxWidthimpl, density.mo279roundToPx0680j_4(horizontal.getSpacing())));
        int[] iArr = new int[iArrB1.length];
        horizontal.arrange(density, iM5646getMaxWidthimpl, iArrB1, layoutDirection, iArr);
        return new LazyGridSlots(iArrB1, iArr);
    }
}

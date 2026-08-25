package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/layout/SimplePlacementScope;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "parentWidth", "", "parentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(ILandroidx/compose/ui/unit/LayoutDirection;)V", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "getParentWidth", "()I", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SimplePlacementScope extends Placeable.PlacementScope {
    private final LayoutDirection parentLayoutDirection;
    private final int parentWidth;

    public SimplePlacementScope(int i10, LayoutDirection layoutDirection) {
        this.parentWidth = i10;
        this.parentLayoutDirection = layoutDirection;
    }

    @Override // androidx.compose.ui.layout.Placeable.PlacementScope
    public LayoutDirection getParentLayoutDirection() {
        return this.parentLayoutDirection;
    }

    @Override // androidx.compose.ui.layout.Placeable.PlacementScope
    public int getParentWidth() {
        return this.parentWidth;
    }
}

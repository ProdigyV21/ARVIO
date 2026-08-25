package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\u0003*\u00020\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\bR*\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0006R\u0018\u0010\u0010\u001a\u00020\u0003*\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/layout/LookaheadScopeImpl;", "Landroidx/compose/ui/layout/LookaheadScope;", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "scopeCoordinates", "<init>", "(Lr7/a;)V", "toLookaheadCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/layout/LayoutCoordinates;", "Lr7/a;", "getScopeCoordinates", "()Lr7/a;", "setScopeCoordinates", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "lookaheadScopeCoordinates", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LookaheadScopeImpl implements LookaheadScope {
    public static final int $stable = 8;
    private r7.a<? extends LayoutCoordinates> scopeCoordinates;

    /* JADX WARN: Multi-variable type inference failed */
    public LookaheadScopeImpl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope placementScope) {
        return (LayoutCoordinates) this.scopeCoordinates.invoke();
    }

    public final r7.a<LayoutCoordinates> getScopeCoordinates() {
        return this.scopeCoordinates;
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    /* JADX INFO: renamed from: localLookaheadPositionOf-dBAh8RU */
    public final /* synthetic */ long mo4635localLookaheadPositionOfdBAh8RU(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2) {
        return f.a(this, layoutCoordinates, layoutCoordinates2);
    }

    public final void setScopeCoordinates(r7.a<? extends LayoutCoordinates> aVar) {
        this.scopeCoordinates = aVar;
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates2 = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        if (lookaheadLayoutCoordinates2 != null) {
            return lookaheadLayoutCoordinates2;
        }
        NodeCoordinator nodeCoordinator = (NodeCoordinator) layoutCoordinates;
        LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
        return (lookaheadDelegate == null || (lookaheadLayoutCoordinates = lookaheadDelegate.getLookaheadLayoutCoordinates()) == null) ? nodeCoordinator : lookaheadLayoutCoordinates;
    }

    public LookaheadScopeImpl(r7.a<? extends LayoutCoordinates> aVar) {
        this.scopeCoordinates = aVar;
    }

    public /* synthetic */ LookaheadScopeImpl(r7.a aVar, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : aVar);
    }
}

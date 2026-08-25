package androidx.compose.ui.node;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.node.Owner;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class e {
    static {
        Owner.Companion companion = Owner.INSTANCE;
    }

    public static Placeable.PlacementScope a(Owner owner) {
        return PlaceableKt.PlacementScope(owner);
    }

    public static /* synthetic */ void b(Owner owner, LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: forceMeasureTheSubtree");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        owner.forceMeasureTheSubtree(layoutNode, z);
    }

    public static /* synthetic */ void f(Owner owner, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: measureAndLayout");
        }
        if ((i10 & 1) != 0) {
            z = true;
        }
        owner.measureAndLayout(z);
    }

    public static /* synthetic */ void g(Owner owner, LayoutNode layoutNode, boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onRequestMeasure");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            z5 = false;
        }
        if ((i10 & 8) != 0) {
            z10 = true;
        }
        owner.onRequestMeasure(layoutNode, z, z5, z10);
    }

    public static /* synthetic */ void h(Owner owner, LayoutNode layoutNode, boolean z, boolean z5, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onRequestRelayout");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            z5 = false;
        }
        owner.onRequestRelayout(layoutNode, z, z5);
    }

    public static /* synthetic */ void c() {
    }

    public static /* synthetic */ void d() {
    }

    public static /* synthetic */ void e() {
    }
}

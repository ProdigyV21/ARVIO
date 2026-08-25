package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Placeable;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static long a(LookaheadScope lookaheadScope, LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2) {
        return lookaheadScope.toLookaheadCoordinates(layoutCoordinates).mo4639localPositionOfR5De75A(lookaheadScope.toLookaheadCoordinates(layoutCoordinates2), Offset.INSTANCE.m3252getZeroF1C5BW0());
    }

    public static /* synthetic */ void b(Placeable.PlacementScope placementScope) {
    }
}

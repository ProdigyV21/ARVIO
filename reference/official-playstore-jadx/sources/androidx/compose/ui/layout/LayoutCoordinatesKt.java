package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0002\u001a\u000f\u0010\u0006\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\t\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\n\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b¨\u0006\u000b"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRootCoordinates", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayoutCoordinatesKt {
    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectC;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectC = c.c(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m5844getWidthimpl(layoutCoordinates.mo4638getSizeYbymL2g()), IntSize.m5843getHeightimpl(layoutCoordinates.mo4638getSizeYbymL2g())) : rectC;
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return c.c(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        Rect rectBoundsInRoot = boundsInRoot(layoutCoordinates);
        float fM5844getWidthimpl = IntSize.m5844getWidthimpl(layoutCoordinatesFindRootCoordinates.mo4638getSizeYbymL2g());
        float fM5843getHeightimpl = IntSize.m5843getHeightimpl(layoutCoordinatesFindRootCoordinates.mo4638getSizeYbymL2g());
        float fM = qb.d.m(rectBoundsInRoot.getLeft(), 0.0f, fM5844getWidthimpl);
        float fM2 = qb.d.m(rectBoundsInRoot.getTop(), 0.0f, fM5843getHeightimpl);
        float fM3 = qb.d.m(rectBoundsInRoot.getRight(), 0.0f, fM5844getWidthimpl);
        float fM4 = qb.d.m(rectBoundsInRoot.getBottom(), 0.0f, fM5843getHeightimpl);
        if (fM == fM3 || fM2 == fM4) {
            return Rect.INSTANCE.getZero();
        }
        long jMo4641localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo4641localToWindowMKHz9U(OffsetKt.Offset(fM, fM2));
        long jMo4641localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo4641localToWindowMKHz9U(OffsetKt.Offset(fM3, fM2));
        long jMo4641localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo4641localToWindowMKHz9U(OffsetKt.Offset(fM3, fM4));
        long jMo4641localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo4641localToWindowMKHz9U(OffsetKt.Offset(fM, fM4));
        return new Rect(r0.y(Offset.m3236getXimpl(jMo4641localToWindowMKHz9U), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U2), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U4), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U3)), r0.y(Offset.m3237getYimpl(jMo4641localToWindowMKHz9U), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U2), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U4), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U3)), r0.x(Offset.m3236getXimpl(jMo4641localToWindowMKHz9U), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U2), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U4), Offset.m3236getXimpl(jMo4641localToWindowMKHz9U3)), r0.x(Offset.m3237getYimpl(jMo4641localToWindowMKHz9U), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U2), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U4), Offset.m3237getYimpl(jMo4641localToWindowMKHz9U3)));
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator == null) {
                return nodeCoordinator3;
            }
            wrappedBy = nodeCoordinator.getWrappedBy();
        }
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m3252getZeroF1C5BW0()) : Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4640localToRootMKHz9U(Offset.INSTANCE.m3252getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4641localToWindowMKHz9U(Offset.INSTANCE.m3252getZeroF1C5BW0());
    }
}

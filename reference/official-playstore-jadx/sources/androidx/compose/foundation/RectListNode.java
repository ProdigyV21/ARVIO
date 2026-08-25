package androidx.compose.foundation;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001d\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0017H&¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001b\u001a\u00020\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u0017H&¢\u0006\u0004\b\u001b\u0010\u001cR0\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010\tR\u0018\u0010!\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010&\u001a\u00020#8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Landroidx/compose/foundation/RectListNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "rect", "<init>", "(Lr7/l;)V", "Landroid/graphics/Rect;", "newRect", "Lx6/t0;", "replaceRect", "(Landroid/graphics/Rect;)V", "layoutCoordinates", "calcBounds", "(Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/geometry/Rect;)Landroid/graphics/Rect;", "coordinates", "onGloballyPositioned", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "onDetach", "()V", "Landroidx/compose/runtime/collection/MutableVector;", "currentRects", "()Landroidx/compose/runtime/collection/MutableVector;", "rects", "updateRects", "(Landroidx/compose/runtime/collection/MutableVector;)V", "Lr7/l;", "getRect", "()Lr7/l;", "setRect", "androidRect", "Landroid/graphics/Rect;", "Landroid/view/View;", "getView", "()Landroid/view/View;", "view", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RectListNode extends Modifier.Node implements GlobalPositionAwareModifierNode, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private Rect androidRect;
    private l<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> rect;

    public RectListNode(l<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> lVar) {
        this.rect = lVar;
    }

    private final Rect calcBounds(LayoutCoordinates layoutCoordinates, androidx.compose.ui.geometry.Rect rect) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates);
        long jMo4639localPositionOfR5De75A = layoutCoordinatesFindRootCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, rect.m3271getTopLeftF1C5BW0());
        long jMo4639localPositionOfR5De75A2 = layoutCoordinatesFindRootCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, rect.m3272getTopRightF1C5BW0());
        long jMo4639localPositionOfR5De75A3 = layoutCoordinatesFindRootCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, rect.m3264getBottomLeftF1C5BW0());
        long jMo4639localPositionOfR5De75A4 = layoutCoordinatesFindRootCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, rect.m3265getBottomRightF1C5BW0());
        return new Rect(t7.a.M(r0.y(Offset.m3236getXimpl(jMo4639localPositionOfR5De75A), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A2), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A3), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A4))), t7.a.M(r0.y(Offset.m3237getYimpl(jMo4639localPositionOfR5De75A), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A2), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A3), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A4))), t7.a.M(r0.x(Offset.m3236getXimpl(jMo4639localPositionOfR5De75A), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A2), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A3), Offset.m3236getXimpl(jMo4639localPositionOfR5De75A4))), t7.a.M(r0.x(Offset.m3237getYimpl(jMo4639localPositionOfR5De75A), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A2), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A3), Offset.m3237getYimpl(jMo4639localPositionOfR5De75A4))));
    }

    private final void replaceRect(Rect newRect) {
        MutableVector<Rect> mutableVectorCurrentRects = currentRects();
        Rect rect = this.androidRect;
        if (rect != null) {
            mutableVectorCurrentRects.remove(rect);
        }
        if (newRect != null && !newRect.isEmpty()) {
            mutableVectorCurrentRects.add(newRect);
        }
        updateRects(mutableVectorCurrentRects);
        this.androidRect = newRect;
    }

    public abstract MutableVector<Rect> currentRects();

    public l<LayoutCoordinates, androidx.compose.ui.geometry.Rect> getRect() {
        return this.rect;
    }

    public final View getView() {
        return (View) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AndroidCompositionLocals_androidKt.getLocalView());
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        replaceRect(null);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        Rect rectCalcBounds;
        if (getRect() == null) {
            androidx.compose.ui.geometry.Rect rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(coordinates);
            rectCalcBounds = new Rect(t7.a.M(rectBoundsInRoot.getLeft()), t7.a.M(rectBoundsInRoot.getTop()), t7.a.M(rectBoundsInRoot.getRight()), t7.a.M(rectBoundsInRoot.getBottom()));
        } else {
            rectCalcBounds = calcBounds(coordinates, (androidx.compose.ui.geometry.Rect) getRect().invoke(coordinates));
        }
        replaceRect(rectCalcBounds);
    }

    public void setRect(l<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> lVar) {
        this.rect = lVar;
    }

    public abstract void updateRects(MutableVector<Rect> rects);
}

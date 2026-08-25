package androidx.compose.foundation;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.List;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/PreferKeepClearNode;", "Landroidx/compose/foundation/RectListNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "rect", "<init>", "(Lr7/l;)V", "Landroidx/compose/runtime/collection/MutableVector;", "Landroid/graphics/Rect;", "currentRects", "()Landroidx/compose/runtime/collection/MutableVector;", "rects", "Lx6/t0;", "updateRects", "(Landroidx/compose/runtime/collection/MutableVector;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PreferKeepClearNode extends RectListNode {
    public PreferKeepClearNode(l<? super LayoutCoordinates, Rect> lVar) {
        super(lVar);
    }

    @Override // androidx.compose.foundation.RectListNode
    public MutableVector<android.graphics.Rect> currentRects() {
        MutableVector<android.graphics.Rect> mutableVector = new MutableVector<>(new android.graphics.Rect[16], 0);
        mutableVector.addAll(mutableVector.getSize(), (List<? extends android.graphics.Rect>) getView().getPreferKeepClearRects());
        return mutableVector;
    }

    @Override // androidx.compose.foundation.RectListNode
    public void updateRects(MutableVector<android.graphics.Rect> rects) {
        getView().setPreferKeepClearRects(rects.asMutableList());
    }
}

package androidx.compose.ui.node;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH&ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0011H&ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015H&ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0013J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H&¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\bH&¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\bH&¢\u0006\u0004\b\u001f\u0010\u001eJ\u000f\u0010 \u001a\u00020\bH&¢\u0006\u0004\b \u0010\u001eJ\"\u0010%\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\rH&ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001f\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020&2\u0006\u0010\"\u001a\u00020\rH&¢\u0006\u0004\b(\u0010)J1\u0010.\u001a\u00020\b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\b0*2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0,H&¢\u0006\u0004\b.\u0010/J\u001a\u00104\u001a\u00020\b2\u0006\u00101\u001a\u000200H&ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001a\u00106\u001a\u00020\b2\u0006\u00101\u001a\u000200H&ø\u0001\u0000¢\u0006\u0004\b5\u00103ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u00067À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/OwnedLayer;", "", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "scope", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/Density;", "density", "Lx6/t0;", "updateLayerProperties", "(Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/geometry/Offset;", "position", "", "isInLayer-k-4lQ0M", "(J)Z", "isInLayer", "Landroidx/compose/ui/unit/IntOffset;", "move--gyyYBs", "(J)V", "move", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "resize-ozmzZPI", "resize", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "drawLayer", "(Landroidx/compose/ui/graphics/Canvas;)V", "updateDisplayList", "()V", "invalidate", "destroy", "point", "inverse", "mapOffset-8S9VItk", "(JZ)J", "mapOffset", "Landroidx/compose/ui/geometry/MutableRect;", "rect", "mapBounds", "(Landroidx/compose/ui/geometry/MutableRect;Z)V", "Lkotlin/Function1;", "drawBlock", "Lkotlin/Function0;", "invalidateParentLayer", "reuseLayer", "(Lr7/l;Lr7/a;)V", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transform-58bKbWc", "([F)V", "transform", "inverseTransform-58bKbWc", "inverseTransform", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface OwnedLayer {
    void destroy();

    void drawLayer(Canvas canvas);

    void invalidate();

    /* JADX INFO: renamed from: inverseTransform-58bKbWc, reason: not valid java name */
    void mo4890inverseTransform58bKbWc(float[] matrix);

    /* JADX INFO: renamed from: isInLayer-k-4lQ0M, reason: not valid java name */
    boolean mo4891isInLayerk4lQ0M(long position);

    void mapBounds(MutableRect rect, boolean inverse);

    /* JADX INFO: renamed from: mapOffset-8S9VItk, reason: not valid java name */
    long mo4892mapOffset8S9VItk(long point, boolean inverse);

    /* JADX INFO: renamed from: move--gyyYBs, reason: not valid java name */
    void mo4893movegyyYBs(long position);

    /* JADX INFO: renamed from: resize-ozmzZPI, reason: not valid java name */
    void mo4894resizeozmzZPI(long size);

    void reuseLayer(l<? super Canvas, t0> drawBlock, r7.a<t0> invalidateParentLayer);

    /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
    void mo4895transform58bKbWc(float[] matrix);

    void updateDisplayList();

    void updateLayerProperties(ReusableGraphicsLayerScope scope, LayoutDirection layoutDirection, Density density);
}

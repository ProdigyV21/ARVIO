package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001aF\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"shadow", "Landroidx/compose/ui/Modifier;", "elevation", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "clip", "", "shadow-ziNgDLE", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;Z)Landroidx/compose/ui/Modifier;", "ambientColor", "Landroidx/compose/ui/graphics/Color;", "spotColor", "shadow-s4CzXII", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;ZJJ)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShadowKt {
    /* JADX INFO: renamed from: shadow-s4CzXII, reason: not valid java name */
    public static final Modifier m3145shadows4CzXII(Modifier modifier, float f10, Shape shape, boolean z, long j10, long j11) {
        if (Dp.m5677compareTo0680j_4(f10, Dp.m5678constructorimpl(0)) > 0 || z) {
            return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ShadowKt$shadows4CzXII$$inlined$debugInspectorInfo$1(f10, shape, z, j10, j11) : InspectableValueKt.getNoInspectorInfo(), GraphicsLayerModifierKt.graphicsLayer(Modifier.INSTANCE, new ShadowKt$shadow$2$1(f10, shape, z, j10, j11)));
        }
        return modifier;
    }

    /* JADX INFO: renamed from: shadow-s4CzXII$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3146shadows4CzXII$default(Modifier modifier, float f10, Shape shape, boolean z, long j10, long j11, int i10, Object obj) {
        boolean z5;
        Shape rectangleShape = (i10 & 2) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        if ((i10 & 4) != 0) {
            z5 = false;
            if (Dp.m5677compareTo0680j_4(f10, Dp.m5678constructorimpl(0)) > 0) {
                z5 = true;
            }
        } else {
            z5 = z;
        }
        return m3145shadows4CzXII(modifier, f10, rectangleShape, z5, (i10 & 8) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j10, (i10 & 16) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j11);
    }

    @e
    /* JADX INFO: renamed from: shadow-ziNgDLE, reason: not valid java name */
    public static final /* synthetic */ Modifier m3147shadowziNgDLE(Modifier modifier, float f10, Shape shape, boolean z) {
        return m3145shadows4CzXII(modifier, f10, shape, z, GraphicsLayerScopeKt.getDefaultShadowColor(), GraphicsLayerScopeKt.getDefaultShadowColor());
    }

    /* JADX INFO: renamed from: shadow-ziNgDLE$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3148shadowziNgDLE$default(Modifier modifier, float f10, Shape shape, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        if ((i10 & 4) != 0) {
            z = false;
            if (Dp.m5677compareTo0680j_4(f10, Dp.m5678constructorimpl(0)) > 0) {
                z = true;
            }
        }
        return m3147shadowziNgDLE(modifier, f10, shape, z);
    }
}

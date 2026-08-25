package androidx.tv.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lj2/h;", "Landroidx/compose/ui/graphics/Shape;", "toShape", "(Lj2/h;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShapesKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ProvidableCompositionLocal f5710a = CompositionLocalKt.staticCompositionLocalOf(i1.f6092m);

    public static final CornerBasedShape a(CornerBasedShape cornerBasedShape) {
        float f10 = (float) 0.0d;
        return CornerBasedShape.copy$default(cornerBasedShape, null, null, CornerSizeKt.m790CornerSize0680j_4(Dp.m5678constructorimpl(f10)), CornerSizeKt.m790CornerSize0680j_4(Dp.m5678constructorimpl(f10)), 3, null);
    }

    public static final Shape toShape(j2.h hVar, Composer composer, int i10) {
        Shape shapeA;
        composer.startReplaceableGroup(526987372);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(526987372, i10, -1, "androidx.tv.material3.toShape (Shapes.kt:173)");
        }
        d3 shapes = MaterialTheme.f5670a.getShapes(composer, 6);
        switch (hVar.ordinal()) {
            case 0:
                shapeA = shapes.f5916e;
                break;
            case 1:
                shapeA = a(shapes.f5916e);
                break;
            case 2:
                shapeA = shapes.f5912a;
                break;
            case 3:
                shapeA = a(shapes.f5912a);
                break;
            case 4:
                shapeA = RoundedCornerShapeKt.getCircleShape();
                break;
            case 5:
                shapeA = shapes.f5915d;
                break;
            case 6:
                float f10 = (float) 0.0d;
                shapeA = CornerBasedShape.copy$default(shapes.f5915d, CornerSizeKt.m790CornerSize0680j_4(Dp.m5678constructorimpl(f10)), null, null, CornerSizeKt.m790CornerSize0680j_4(Dp.m5678constructorimpl(f10)), 6, null);
                break;
            case 7:
                shapeA = a(shapes.f5915d);
                break;
            case 8:
                shapeA = shapes.f5914c;
                break;
            case 9:
                shapeA = RectangleShapeKt.getRectangleShape();
                break;
            case 10:
                shapeA = shapes.f5913b;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return shapeA;
    }
}

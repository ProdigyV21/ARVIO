package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"blur", "Landroidx/compose/ui/Modifier;", "radius", "Landroidx/compose/ui/unit/Dp;", "edgeTreatment", "Landroidx/compose/ui/draw/BlurredEdgeTreatment;", "blur-F8QBwvs", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "radiusX", "radiusY", "blur-1fqS-gw", "(Landroidx/compose/ui/Modifier;FFLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlurKt {
    /* JADX INFO: renamed from: blur-1fqS-gw, reason: not valid java name */
    public static final Modifier m3125blur1fqSgw(Modifier modifier, float f10, float f11, Shape shape) {
        int iM3824getDecal3opZhB0;
        boolean z;
        if (shape != null) {
            iM3824getDecal3opZhB0 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            z = true;
        } else {
            iM3824getDecal3opZhB0 = TileMode.INSTANCE.m3824getDecal3opZhB0();
            z = false;
        }
        float f12 = 0;
        return ((Dp.m5677compareTo0680j_4(f10, Dp.m5678constructorimpl(f12)) <= 0 || Dp.m5677compareTo0680j_4(f11, Dp.m5678constructorimpl(f12)) <= 0) && !z) ? modifier : GraphicsLayerModifierKt.graphicsLayer(modifier, new BlurKt$blur$1(f10, f11, iM3824getDecal3opZhB0, shape, z));
    }

    /* JADX INFO: renamed from: blur-1fqS-gw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3126blur1fqSgw$default(Modifier modifier, float f10, float f11, BlurredEdgeTreatment blurredEdgeTreatment, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m3129boximpl(BlurredEdgeTreatment.INSTANCE.m3136getRectangleGoahg());
        }
        return m3125blur1fqSgw(modifier, f10, f11, blurredEdgeTreatment.m3135unboximpl());
    }

    /* JADX INFO: renamed from: blur-F8QBwvs, reason: not valid java name */
    public static final Modifier m3127blurF8QBwvs(Modifier modifier, float f10, Shape shape) {
        return m3125blur1fqSgw(modifier, f10, f10, shape);
    }

    /* JADX INFO: renamed from: blur-F8QBwvs$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3128blurF8QBwvs$default(Modifier modifier, float f10, BlurredEdgeTreatment blurredEdgeTreatment, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            blurredEdgeTreatment = BlurredEdgeTreatment.m3129boximpl(BlurredEdgeTreatment.INSTANCE.m3136getRectangleGoahg());
        }
        return m3127blurF8QBwvs(modifier, f10, blurredEdgeTreatment.m3135unboximpl());
    }
}

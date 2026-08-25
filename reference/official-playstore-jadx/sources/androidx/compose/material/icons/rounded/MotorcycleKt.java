package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motorcycle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Motorcycle", "Landroidx/compose/material/icons/Icons$Rounded;", "getMotorcycle", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotorcycleKt {
    private static ImageVector _motorcycle;

    public static final ImageVector getMotorcycle(Icons.Rounded rounded) {
        ImageVector imageVector = _motorcycle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Motorcycle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.44f, 9.03f, 17.31f, 6.9f);
        pathBuilderJ.lineToRelative(-1.6f, -1.6f);
        pathBuilderJ.curveToRelative(-0.19f, -0.19f, -0.45f, -0.3f, -0.71f, -0.3f);
        pathBuilderJ.horizontalLineToRelative(-3.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(2.59f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        pathBuilderJ.lineTo(5.0f, 9.0f);
        pathBuilderJ.curveToRelative(-2.8f, 0.0f, -5.0f, 2.2f, -5.0f, 5.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.2f, 5.0f, 5.0f, 5.0f);
        pathBuilderJ.curveToRelative(2.46f, 0.0f, 4.45f, -1.69f, 4.9f, -4.0f);
        pathBuilderJ.horizontalLineToRelative(0.82f);
        pathBuilderJ.curveToRelative(0.53f, 0.0f, 1.04f, -0.21f, 1.41f, -0.59f);
        pathBuilderJ.lineToRelative(2.18f, -2.18f);
        pathBuilderJ.curveToRelative(-0.2f, 0.54f, -0.31f, 1.14f, -0.31f, 1.77f);
        pathBuilderJ.curveToRelative(0.0f, 2.8f, 2.2f, 5.0f, 5.0f, 5.0f);
        pathBuilderJ.reflectiveCurveToRelative(5.0f, -2.2f, 5.0f, -5.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.65f, -1.97f, -4.77f, -4.56f, -4.97f);
        a.g(pathBuilderJ, 5.0f, 15.0f, 2.82f);
        pathBuilderJ.curveTo(7.4f, 16.15f, 6.28f, 17.0f, 5.0f, 17.0f);
        pathBuilderJ.curveToRelative(-1.63f, 0.0f, -3.0f, -1.37f, -3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.37f, -3.0f, 3.0f, -3.0f);
        pathBuilderJ.curveToRelative(1.28f, 0.0f, 2.4f, 0.85f, 2.82f, 2.0f);
        pathBuilderJ.lineTo(5.0f, 13.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.0f, 17.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -1.34f, 3.0f, -3.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _motorcycle = imageVectorBuild;
        return imageVectorBuild;
    }
}

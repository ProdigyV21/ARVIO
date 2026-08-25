package androidx.compose.material.icons.filled;

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
import v.b;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_precisionManufacturing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PrecisionManufacturing", "Landroidx/compose/material/icons/Icons$Filled;", "getPrecisionManufacturing", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrecisionManufacturingKt {
    private static ImageVector _precisionManufacturing;

    public static final ImageVector getPrecisionManufacturing(Icons.Filled filled) {
        ImageVector imageVector = _precisionManufacturing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PrecisionManufacturing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.93f, 8.21f, -3.6f, 1.68f);
        pathBuilderR.lineTo(14.0f, 7.7f);
        pathBuilderR.verticalLineTo(6.3f);
        pathBuilderR.lineToRelative(2.33f, -2.19f);
        pathBuilderR.lineToRelative(3.6f, 1.68f);
        pathBuilderR.curveToRelative(0.38f, 0.18f, 0.82f, 0.01f, 1.0f, -0.36f);
        pathBuilderR.curveToRelative(0.18f, -0.38f, 0.01f, -0.82f, -0.36f, -1.0f);
        pathBuilderR.lineTo(16.65f, 2.6f);
        pathBuilderR.curveToRelative(-0.38f, -0.18f, -0.83f, -0.1f, -1.13f, 0.2f);
        pathBuilderR.lineToRelative(-1.74f, 1.6f);
        pathBuilderR.curveTo(13.6f, 4.16f, 13.32f, 4.0f, 13.0f, 4.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(1.0f);
        pathBuilderR.horizontalLineTo(8.82f);
        pathBuilderR.curveTo(8.34f, 4.65f, 6.98f, 3.73f, 5.4f, 4.07f);
        pathBuilderR.curveTo(4.24f, 4.32f, 3.25f, 5.32f, 3.04f, 6.5f);
        pathBuilderR.curveTo(2.82f, 7.82f, 3.5f, 8.97f, 4.52f, 9.58f);
        b.m(pathBuilderR, 7.08f, 18.0f, 4.0f, 3.0f);
        e.g(pathBuilderR, 13.0f, -3.0f, -3.62f);
        pathBuilderR.lineTo(8.41f, 8.77f);
        pathBuilderR.curveTo(8.58f, 8.53f, 8.72f, 8.28f, 8.82f, 8.0f);
        pathBuilderR.horizontalLineTo(12.0f);
        pathBuilderR.verticalLineToRelative(1.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.curveToRelative(0.32f, 0.0f, 0.6f, -0.16f, 0.78f, -0.4f);
        pathBuilderR.lineToRelative(1.74f, 1.6f);
        pathBuilderR.curveToRelative(0.3f, 0.3f, 0.75f, 0.38f, 1.13f, 0.2f);
        pathBuilderR.lineToRelative(3.92f, -1.83f);
        pathBuilderR.curveToRelative(0.38f, -0.18f, 0.54f, -0.62f, 0.36f, -1.0f);
        pathBuilderR.curveTo(20.75f, 8.2f, 20.31f, 8.03f, 19.93f, 8.21f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.0f, 8.0f);
        pathBuilderR.curveTo(5.45f, 8.0f, 5.0f, 7.55f, 5.0f, 7.0f);
        pathBuilderR.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 6.55f, 8.0f, 6.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _precisionManufacturing = imageVectorBuild;
        return imageVectorBuild;
    }
}

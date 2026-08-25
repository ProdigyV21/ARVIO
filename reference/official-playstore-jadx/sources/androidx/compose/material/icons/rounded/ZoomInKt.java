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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_zoomIn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ZoomIn", "Landroidx/compose/material/icons/Icons$Rounded;", "getZoomIn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ZoomInKt {
    private static ImageVector _zoomIn;

    public static final ImageVector getZoomIn(Icons.Rounded rounded) {
        ImageVector imageVector = _zoomIn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ZoomIn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(15.5f, 14.0f, -0.79f, -0.28f, -0.27f);
        pathBuilderG.curveToRelative(1.2f, -1.4f, 1.82f, -3.31f, 1.48f, -5.34f);
        pathBuilderG.curveToRelative(-0.47f, -2.78f, -2.79f, -5.0f, -5.59f, -5.34f);
        pathBuilderG.curveToRelative(-4.23f, -0.52f, -7.78f, 3.04f, -7.27f, 7.27f);
        pathBuilderG.curveToRelative(0.34f, 2.8f, 2.56f, 5.12f, 5.34f, 5.59f);
        pathBuilderG.curveToRelative(2.03f, 0.34f, 3.94f, -0.28f, 5.34f, -1.48f);
        pathBuilderG.lineToRelative(0.27f, 0.28f);
        pathBuilderG.verticalLineToRelative(0.79f);
        pathBuilderG.lineToRelative(4.26f, 4.25f);
        pathBuilderG.curveToRelative(0.41f, 0.41f, 1.07f, 0.41f, 1.48f, 0.0f);
        pathBuilderG.lineToRelative(0.01f, -0.01f);
        pathBuilderG.curveToRelative(0.41f, -0.41f, 0.41f, -1.07f, 0.0f, -1.48f);
        c.z(pathBuilderG, 15.5f, 14.0f, 9.5f, 14.0f);
        pathBuilderG.curveTo(7.01f, 14.0f, 5.0f, 11.99f, 5.0f, 9.5f);
        pathBuilderG.reflectiveCurveTo(7.01f, 5.0f, 9.5f, 5.0f);
        pathBuilderG.reflectiveCurveTo(14.0f, 7.01f, 14.0f, 9.5f);
        pathBuilderG.reflectiveCurveTo(11.99f, 14.0f, 9.5f, 14.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(9.5f, 7.0f);
        pathBuilderG.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderG.lineTo(9.0f, 9.0f);
        pathBuilderG.lineTo(7.5f, 9.0f);
        pathBuilderG.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderG.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderG.lineTo(9.0f, 10.0f);
        pathBuilderG.verticalLineToRelative(1.5f);
        pathBuilderG.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderG.reflectiveCurveToRelative(0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderG.lineTo(10.0f, 10.0f);
        pathBuilderG.horizontalLineToRelative(1.5f);
        pathBuilderG.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderG.reflectiveCurveToRelative(-0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderG.lineTo(10.0f, 9.0f);
        pathBuilderG.lineTo(10.0f, 7.5f);
        pathBuilderG.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _zoomIn = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_elderly", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Elderly", "Landroidx/compose/material/icons/Icons$Rounded;", "getElderly", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ElderlyKt {
    private static ImageVector _elderly;

    public static final ImageVector getElderly(Icons.Rounded rounded) {
        ImageVector imageVector = _elderly;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Elderly", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.5f, 3.5f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(11.5f, 4.6f, 11.5f, 3.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.53f, 11.0f);
        pathBuilderA.curveToRelative(-1.57f, 0.01f, -2.94f, -0.9f, -3.6f, -2.21f);
        pathBuilderA.lineToRelative(-0.79f, -1.67f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.17f, -0.35f, -0.44f, -0.65f, -0.8f, -0.85f);
        pathBuilderA.curveToRelative(-0.62f, -0.36f, -1.35f, -0.34f, -1.94f, -0.03f);
        pathBuilderA.lineToRelative(0.0f, -0.01f);
        pathBuilderA.lineTo(7.01f, 8.73f);
        pathBuilderA.curveTo(6.39f, 9.08f, 6.0f, 9.74f, 6.0f, 10.46f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.54f);
        pathBuilderA.lineToRelative(1.5f, -0.85f);
        pathBuilderA.curveTo(9.18f, 10.71f, 9.0f, 11.85f, 9.0f, 13.0f);
        pathBuilderA.verticalLineToRelative(5.33f);
        pathBuilderA.lineTo(7.0f, 21.0f);
        pathBuilderA.curveToRelative(-0.33f, 0.44f, -0.24f, 1.07f, 0.2f, 1.4f);
        pathBuilderA.curveToRelative(0.44f, 0.33f, 1.07f, 0.24f, 1.4f, -0.2f);
        pathBuilderA.lineToRelative(2.04f, -2.72f);
        pathBuilderA.curveToRelative(0.23f, -0.31f, 0.37f, -0.69f, 0.4f, -1.08f);
        pathBuilderA.lineToRelative(0.18f, -2.94f);
        pathBuilderA.lineTo(13.0f, 18.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.87f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.13f, -0.81f, -0.36f, -1.15f);
        pathBuilderA.lineToRelative(-1.6f, -2.29f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.01f);
        pathBuilderA.curveToRelative(-0.11f, -1.16f, 0.07f, -2.32f, 0.46f, -3.4f);
        pathBuilderA.curveToRelative(0.81f, 1.23f, 2.05f, 2.14f, 3.51f, 2.52f);
        pathBuilderA.verticalLineToRelative(0.03f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveTo(18.0f, 13.28f, 18.0f, 13.0f);
        pathBuilderA.verticalLineToRelative(-0.16f);
        pathBuilderA.verticalLineTo(12.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineToRelative(-10.0f);
        pathBuilderA.curveTo(20.0f, 11.68f, 19.34f, 10.99f, 18.53f, 11.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _elderly = imageVectorBuild;
        return imageVectorBuild;
    }
}

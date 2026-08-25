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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensors", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sensors", "Landroidx/compose/material/icons/Icons$Rounded;", "getSensors", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorsKt {
    private static ImageVector _sensors;

    public static final ImageVector getSensors(Icons.Rounded rounded) {
        ImageVector imageVector = _sensors;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Sensors", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.54f, 8.54f);
        pathBuilderA.curveToRelative(0.35f, 0.35f, 0.37f, 0.88f, 0.1f, 1.29f);
        pathBuilderA.curveTo(8.24f, 10.45f, 8.0f, 11.2f, 8.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.8f, 0.24f, 1.55f, 0.64f, 2.17f);
        pathBuilderA.curveToRelative(0.27f, 0.41f, 0.24f, 0.95f, -0.11f, 1.29f);
        pathBuilderA.curveToRelative(-0.43f, 0.43f, -1.17f, 0.4f, -1.51f, -0.11f);
        pathBuilderA.curveTo(6.38f, 14.4f, 6.0f, 13.24f, 6.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.21f, 0.36f, -2.33f, 0.97f, -3.28f);
        pathBuilderA.curveTo(7.33f, 8.18f, 8.08f, 8.08f, 8.54f, 8.54f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.46f, 15.46f);
        pathBuilderA.curveToRelative(0.43f, 0.43f, 1.17f, 0.4f, 1.51f, -0.11f);
        pathBuilderA.curveTo(17.62f, 14.4f, 18.0f, 13.24f, 18.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.24f, -0.38f, -2.4f, -1.03f, -3.36f);
        pathBuilderA.curveToRelative(-0.34f, -0.5f, -1.08f, -0.54f, -1.51f, -0.11f);
        pathBuilderA.curveToRelative(-0.35f, 0.35f, -0.37f, 0.88f, -0.11f, 1.29f);
        pathBuilderA.curveTo(15.76f, 10.45f, 16.0f, 11.2f, 16.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.8f, -0.24f, 1.55f, -0.64f, 2.17f);
        pathBuilderA.curveTo(15.09f, 14.58f, 15.12f, 15.12f, 15.46f, 15.46f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 10.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(13.1f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.32f, 18.32f);
        pathBuilderA.curveToRelative(0.42f, 0.42f, 1.12f, 0.39f, 1.5f, -0.08f);
        pathBuilderA.curveTo(21.18f, 16.53f, 22.0f, 14.36f, 22.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.82f, -4.53f, -2.18f, -6.24f);
        pathBuilderA.curveToRelative(-0.37f, -0.47f, -1.07f, -0.5f, -1.5f, -0.08f);
        pathBuilderA.curveToRelative(-0.36f, 0.36f, -0.4f, 0.92f, -0.08f, 1.32f);
        pathBuilderA.curveToRelative(1.1f, 1.37f, 1.76f, 3.11f, 1.76f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.66f, 3.63f, -1.76f, 5.0f);
        pathBuilderA.curveTo(17.92f, 17.39f, 17.96f, 17.96f, 18.32f, 18.32f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.68f, 5.68f);
        pathBuilderA.curveToRelative(-0.42f, -0.42f, -1.12f, -0.39f, -1.5f, 0.08f);
        pathBuilderA.curveTo(2.82f, 7.47f, 2.0f, 9.64f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.82f, 4.53f, 2.18f, 6.24f);
        pathBuilderA.curveToRelative(0.37f, 0.47f, 1.07f, 0.5f, 1.5f, 0.08f);
        pathBuilderA.curveToRelative(0.36f, -0.36f, 0.4f, -0.92f, 0.08f, -1.32f);
        pathBuilderA.curveTo(4.66f, 15.63f, 4.0f, 13.89f, 4.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.66f, -3.63f, 1.76f, -5.0f);
        pathBuilderA.curveTo(6.08f, 6.61f, 6.04f, 6.04f, 5.68f, 5.68f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensors = imageVectorBuild;
        return imageVectorBuild;
    }
}

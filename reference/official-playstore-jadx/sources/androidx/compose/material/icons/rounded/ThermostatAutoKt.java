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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thermostatAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThermostatAuto", "Landroidx/compose/material/icons/Icons$Rounded;", "getThermostatAuto", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThermostatAutoKt {
    private static ImageVector _thermostatAuto;

    public static final ImageVector getThermostatAuto(Icons.Rounded rounded) {
        ImageVector imageVector = _thermostatAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ThermostatAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(11.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(5.0f, 4.34f, 5.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(-1.21f, 0.91f, -2.0f, 2.37f, -2.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.12f, 0.38f, 2.14f, 1.0f, 2.97f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.horizontalLineToRelative(0.02f);
        pathBuilderA.curveToRelative(0.91f, 1.21f, 2.35f, 2.0f, 3.98f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(3.06f, -0.79f, 3.98f, -2.0f);
        pathBuilderA.horizontalLineTo(12.0f);
        pathBuilderA.verticalLineToRelative(-0.03f);
        pathBuilderA.curveToRelative(0.62f, -0.83f, 1.0f, -1.85f, 1.0f, -2.97f);
        pathBuilderA.curveTo(13.0f, 14.37f, 12.21f, 12.91f, 11.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, -0.94f, 0.45f, -1.84f, 1.2f, -2.4f);
        pathBuilderA.lineTo(7.0f, 13.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.lineToRelative(0.8f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.57f, 1.2f, 1.46f, 1.2f, 2.4f);
        a.x(pathBuilderA, 5.0f, 17.81f, 4.0f);
        pathBuilderA.lineTo(17.81f, 4.0f);
        pathBuilderA.curveToRelative(-0.48f, 0.0f, -0.92f, 0.3f, -1.09f, 0.75f);
        pathBuilderA.lineTo(14.0f, 12.02f);
        pathBuilderA.curveTo(13.82f, 12.49f, 14.17f, 13.0f, 14.67f, 13.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.31f, 0.0f, 0.58f, -0.19f, 0.68f, -0.48f);
        pathBuilderA.lineTo(16.0f, 10.7f);
        pathBuilderA.horizontalLineToRelative(3.63f);
        pathBuilderA.lineToRelative(0.64f, 1.82f);
        pathBuilderA.curveToRelative(0.1f, 0.29f, 0.38f, 0.48f, 0.68f, 0.48f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.51f, 0.0f, 0.86f, -0.51f, 0.68f, -0.98f);
        pathBuilderA.lineTo(18.9f, 4.75f);
        pathBuilderA.curveTo(18.73f, 4.3f, 18.3f, 4.0f, 17.81f, 4.0f);
        a.z(pathBuilderA, 16.47f, 9.39f, 1.31f, -3.72f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderA, 0.08f, 1.31f, 3.72f, 16.47f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thermostatAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}

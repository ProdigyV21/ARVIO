package androidx.compose.material.icons.twotone;

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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thermostatAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThermostatAuto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getThermostatAuto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThermostatAutoKt {
    private static ImageVector _thermostatAuto;

    public static final ImageVector getThermostatAuto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _thermostatAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ThermostatAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(9.8f, 13.6f, 9.0f, 13.0f, 6.0f);
        pathBuilderN.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderN.reflectiveCurveTo(7.0f, 5.45f, 7.0f, 6.0f);
        pathBuilderN.verticalLineToRelative(7.0f);
        pathBuilderN.lineToRelative(-0.8f, 0.6f);
        pathBuilderN.curveTo(5.45f, 14.16f, 5.0f, 15.06f, 5.0f, 16.0f);
        pathBuilderN.horizontalLineToRelative(6.0f);
        pathBuilderN.curveTo(11.0f, 15.06f, 10.55f, 14.17f, 9.8f, 13.6f);
        pathBuilderN.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
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
        a.v(pathBuilderA, 5.0f, 18.62f, 4.0f, -1.61f);
        pathBuilderA.lineToRelative(-3.38f, 9.0f);
        c.A(pathBuilderA, 1.56f, 16.0f, 10.7f, 3.63f);
        pathBuilderA.lineToRelative(0.8f, 2.3f);
        a.w(pathBuilderA, 22.0f, 18.62f, 4.0f);
        pathBuilderA.moveTo(16.47f, 9.39f);
        pathBuilderA.lineToRelative(1.31f, -3.72f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderA, 0.08f, 1.31f, 3.72f, 16.47f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thermostatAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carCrash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarCrash", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCarCrash", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarCrashKt {
    private static ImageVector _carCrash;

    public static final ImageVector getCarCrash(Icons.TwoTone twoTone) {
        ImageVector imageVector = _carCrash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CarCrash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.91f, 13.0f);
        pathBuilderA.curveToRelative(0.06f, 0.16f, 0.09f, 0.33f, 0.09f, 0.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(15.0f, 14.33f, 15.0f, 13.5f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, 0.15f, -0.74f, 0.39f, -1.0f);
        pathBuilderA.curveToRelative(-0.86f, -0.35f, -1.63f, -0.86f, -2.29f, -1.5f);
        b.q(pathBuilderA, 5.0f, 5.0f, 14.0f, -3.07f);
        pathBuilderA.curveTo(18.67f, 12.98f, 18.39f, 13.0f, 17.91f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.5f, 15.0f);
        pathBuilderA.curveTo(6.67f, 15.0f, 6.0f, 14.33f, 6.0f, 13.5f);
        pathBuilderA.reflectiveCurveTo(6.67f, 12.0f, 7.5f, 12.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 12.67f, 9.0f, 13.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 8.33f, 15.0f, 7.5f, 15.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(18.0f, 1.0f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA2.reflectiveCurveTo(20.76f, 1.0f, 18.0f, 1.0f);
        a.u(pathBuilderA2, 18.5f, 7.0f, -1.0f, 3.0f);
        c.D(pathBuilderA2, 1.0f, 7.0f, 18.5f, 8.0f);
        a0.b.A(pathBuilderA2, 1.0f, -1.0f, 8.0f, 18.5f);
        pathBuilderA2.moveTo(6.0f, 13.5f);
        pathBuilderA2.curveTo(6.0f, 12.67f, 6.67f, 12.0f, 7.5f, 12.0f);
        pathBuilderA2.reflectiveCurveTo(9.0f, 12.67f, 9.0f, 13.5f);
        pathBuilderA2.reflectiveCurveTo(8.33f, 15.0f, 7.5f, 15.0f);
        pathBuilderA2.reflectiveCurveTo(6.0f, 14.33f, 6.0f, 13.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(19.0f, 12.93f);
        pathBuilderA2.curveToRelative(0.65f, -0.09f, 1.34f, -0.28f, 2.0f, -0.6f);
        pathBuilderA2.horizontalLineToRelative(0.0f);
        pathBuilderA2.verticalLineTo(19.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA2.horizontalLineToRelative(-1.0f);
        pathBuilderA2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        y.a.g(pathBuilderA2, -1.0f, 6.0f, 1.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA2.horizontalLineTo(4.0f);
        pathBuilderA2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA2.verticalLineToRelative(-8.0f);
        pathBuilderA2.lineToRelative(2.08f, -5.99f);
        pathBuilderA2.curveTo(5.29f, 4.42f, 5.84f, 4.0f, 6.5f, 4.0f);
        pathBuilderA2.lineToRelative(4.79f, 0.0f);
        pathBuilderA2.curveTo(11.1f, 4.63f, 11.0f, 5.31f, 11.0f, 6.0f);
        pathBuilderA2.horizontalLineTo(6.85f);
        pathBuilderA2.lineTo(5.81f, 9.0f);
        pathBuilderA2.horizontalLineToRelative(5.86f);
        pathBuilderA2.verticalLineToRelative(0.0f);
        pathBuilderA2.curveToRelative(0.36f, 0.75f, 0.84f, 1.43f, 1.43f, 2.0f);
        a.h(pathBuilderA2, 5.0f, 11.0f, 5.0f, 14.0f);
        c.z(pathBuilderA2, 19.0f, 12.93f, 17.91f, 13.0f);
        pathBuilderA2.curveToRelative(-0.89f, -0.01f, -1.74f, -0.19f, -2.53f, -0.51f);
        pathBuilderA2.curveTo(15.15f, 12.76f, 15.0f, 13.11f, 15.0f, 13.5f);
        pathBuilderA2.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA2.curveTo(18.0f, 13.32f, 17.97f, 13.16f, 17.91f, 13.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _carCrash = imageVectorBuild;
        return imageVectorBuild;
    }
}

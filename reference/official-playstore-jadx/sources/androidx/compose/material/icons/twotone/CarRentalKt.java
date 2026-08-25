package androidx.compose.material.icons.twotone;

import a0.a;
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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carRental", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarRental", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCarRental", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarRentalKt {
    private static ImageVector _carRental;

    public static final ImageVector getCarRental(Icons.TwoTone twoTone) {
        ImageVector imageVector = _carRental;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CarRental", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(7.0f, 15.01f, 18.0f, 10.0f, -2.99f);
        v.a.v(pathBuilderA, 15.0f, 7.0f, 15.01f);
        pathBuilderA.moveTo(15.0f, 15.5f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(14.45f, 15.5f, 15.0f, 15.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 15.5f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 8.45f, 15.5f, 9.0f, 15.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = v.a.b(9.0f, 16.5f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = v.a.b(15.0f, 16.5f, -1.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(17.25f, 9.6f);
        pathBuilderA2.curveToRelative(-0.02f, -0.02f, -0.03f, -0.04f, -0.05f, -0.07f);
        pathBuilderA2.curveTo(16.82f, 9.01f, 16.28f, 9.0f, 16.28f, 9.0f);
        pathBuilderA2.horizontalLineTo(7.72f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, -0.54f, 0.01f, -0.92f, 0.54f);
        pathBuilderA2.curveTo(6.78f, 9.56f, 6.77f, 9.58f, 6.75f, 9.6f);
        pathBuilderA2.curveTo(6.68f, 9.71f, 6.61f, 9.84f, 6.56f, 10.0f);
        pathBuilderA2.curveTo(6.34f, 10.66f, 5.82f, 12.22f, 5.0f, 14.69f);
        pathBuilderA2.verticalLineToRelative(6.5f);
        pathBuilderA2.curveTo(5.0f, 21.64f, 5.35f, 22.0f, 5.78f, 22.0f);
        pathBuilderA2.horizontalLineToRelative(0.44f);
        pathBuilderA2.curveTo(6.65f, 22.0f, 7.0f, 21.64f, 7.0f, 21.19f);
        w.a.j(pathBuilderA2, 20.0f, 10.0f, 1.19f);
        pathBuilderA2.curveToRelative(0.0f, 0.45f, 0.34f, 0.81f, 0.78f, 0.81f);
        pathBuilderA2.horizontalLineToRelative(0.44f);
        pathBuilderA2.curveToRelative(0.43f, 0.0f, 0.78f, -0.36f, 0.78f, -0.81f);
        pathBuilderA2.verticalLineToRelative(-6.5f);
        pathBuilderA2.curveToRelative(-0.82f, -2.46f, -1.34f, -4.03f, -1.56f, -4.69f);
        pathBuilderA2.curveTo(17.39f, 9.84f, 17.32f, 9.71f, 17.25f, 9.6f);
        v.a.g(pathBuilderA2, 8.33f, 11.0f, 7.34f);
        pathBuilderA2.lineToRelative(0.23f, 0.69f);
        pathBuilderA2.lineTo(16.33f, 13.0f);
        w.a.w(pathBuilderA2, 7.67f, 8.33f, 11.0f);
        f.g(pathBuilderA2, 17.0f, 15.01f, 18.0f, 7.0f);
        pathBuilderA2.verticalLineToRelative(-2.99f);
        pathBuilderA2.verticalLineTo(15.0f);
        pathBuilderA2.horizontalLineToRelative(10.0f);
        pathBuilderA2.verticalLineTo(15.01f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(10.83f, 3.0f);
        pathBuilderA3.curveTo(10.41f, 1.83f, 9.3f, 1.0f, 8.0f, 1.0f);
        pathBuilderA3.curveTo(6.34f, 1.0f, 5.0f, 2.34f, 5.0f, 4.0f);
        pathBuilderA3.curveToRelative(0.0f, 1.65f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA3.curveToRelative(1.3f, 0.0f, 2.41f, -0.84f, 2.83f, -2.0f);
        a.e(pathBuilderA3, 16.0f, 2.0f, 2.0f, 5.0f);
        w.a.o(pathBuilderA3, 1.0f, 3.0f, 10.83f);
        pathBuilderA3.moveTo(8.0f, 5.0f);
        pathBuilderA3.curveTo(7.45f, 5.0f, 7.0f, 4.55f, 7.0f, 4.0f);
        pathBuilderA3.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA3.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA3, 8.55f, 5.0f, 8.0f, 5.0f), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _carRental = imageVectorBuild;
        return imageVectorBuild;
    }
}

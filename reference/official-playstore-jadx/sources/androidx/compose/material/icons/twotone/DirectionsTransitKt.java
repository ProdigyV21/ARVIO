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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsTransit", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsTransit", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDirectionsTransit", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsTransitKt {
    private static ImageVector _directionsTransit;

    public static final ImageVector getDirectionsTransit(Icons.TwoTone twoTone) {
        ImageVector imageVector = _directionsTransit;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DirectionsTransit", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-3.51f, 0.0f, -4.96f, 0.48f, -5.57f, 1.0f);
        pathBuilderA.horizontalLineToRelative(11.23f);
        pathBuilderA.curveToRelative(-0.54f, -0.54f, -1.95f, -1.0f, -5.66f, -1.0f);
        a.g(pathBuilderA, 7.5f, 17.0f, 9.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.lineTo(18.0f, 12.0f);
        pathBuilderA.lineTo(6.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(3.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 13.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 13.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 16.0f, 8.5f, 16.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 15.33f, 7.0f, 14.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 7.67f, 13.0f, 8.5f, 13.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(4.0f, 6.0f, 9.5f);
        pathBuilderI.curveTo(4.0f, 17.43f, 5.57f, 19.0f, 7.5f, 19.0f);
        a.h(pathBuilderI, 6.0f, 20.5f, 0.5f, 12.0f);
        pathBuilderI.verticalLineToRelative(-0.5f);
        pathBuilderI.lineTo(16.5f, 19.0f);
        pathBuilderI.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderI.lineTo(20.0f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, -3.5f, -3.58f, -4.0f, -8.0f, -4.0f);
        pathBuilderI.curveToRelative(-4.0f, 0.0f, -8.0f, 0.5f, -8.0f, 4.0f);
        a.g(pathBuilderI, 18.0f, 10.0f, -5.0f);
        b.g(pathBuilderI, 13.0f, 7.0f, 5.0f, 3.0f);
        pathBuilderI.moveTo(12.0f, 4.0f);
        pathBuilderI.curveToRelative(3.71f, 0.0f, 5.12f, 0.46f, 5.66f, 1.0f);
        pathBuilderI.lineTo(6.43f, 5.0f);
        pathBuilderI.curveToRelative(0.61f, -0.52f, 2.06f, -1.0f, 5.57f, -1.0f);
        b.w(pathBuilderI, 6.0f, 7.0f, 5.0f, 3.0f);
        b.y(pathBuilderI, 6.0f, 10.0f, 6.0f, 7.0f);
        a.C(pathBuilderI, 6.0f, 12.0f, 12.0f, 3.5f);
        pathBuilderI.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderI.horizontalLineToRelative(-9.0f);
        pathBuilderI.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderI, 6.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(8.5f, 14.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(15.5f, 14.5f, -1.5f, 0.0f);
        pathBuilderB2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsTransit = imageVectorBuild;
        return imageVectorBuild;
    }
}

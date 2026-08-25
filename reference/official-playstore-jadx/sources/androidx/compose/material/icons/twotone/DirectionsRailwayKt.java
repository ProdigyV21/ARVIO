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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsRailway", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsRailway", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDirectionsRailway", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsRailwayKt {
    private static ImageVector _directionsRailway;

    public static final ImageVector getDirectionsRailway(Icons.TwoTone twoTone) {
        ImageVector imageVector = _directionsRailway;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DirectionsRailway", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 15.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(9.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.lineTo(18.0f, 12.0f);
        d.f(pathBuilderA, 6.0f, 12.0f, 3.5f);
        pathBuilderA.moveTo(12.0f, 12.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 3.0f);
        pathBuilderA.curveTo(6.0f, 3.0f, 6.0f, 4.2f, 6.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.8f, 0.0f, -2.0f, -6.0f, -2.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 15.5f, 20.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -3.5f, -3.58f, -4.0f, -8.0f, -4.0f);
        pathBuilderJ.reflectiveCurveToRelative(-8.0f, 0.5f, -8.0f, 4.0f);
        pathBuilderJ.verticalLineToRelative(10.5f);
        pathBuilderJ.curveTo(4.0f, 17.43f, 5.57f, 19.0f, 7.5f, 19.0f);
        a.h(pathBuilderJ, 6.0f, 20.5f, 0.5f, 12.0f);
        pathBuilderJ.verticalLineToRelative(-0.5f);
        pathBuilderJ.lineTo(16.5f, 19.0f);
        pathBuilderJ.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(18.0f, 15.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderJ.horizontalLineToRelative(-9.0f);
        pathBuilderJ.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        b.g(pathBuilderJ, 6.0f, 12.0f, 12.0f, 3.5f);
        pathBuilderJ.moveTo(18.0f, 10.0f);
        pathBuilderJ.lineTo(6.0f, 10.0f);
        b.g(pathBuilderJ, 6.0f, 7.0f, 12.0f, 3.0f);
        pathBuilderJ.moveTo(6.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.8f, 0.0f, -2.0f, 6.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(6.0f, 1.2f, 6.0f, 2.0f);
        c.z(pathBuilderJ, 6.0f, 5.0f, 12.0f, 16.5f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, 0.9f, 2.0f, 2.0f, 2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsRailway = imageVectorBuild;
        return imageVectorBuild;
    }
}

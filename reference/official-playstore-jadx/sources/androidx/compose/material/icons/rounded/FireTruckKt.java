package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fireTruck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FireTruck", "Landroidx/compose/material/icons/Icons$Rounded;", "getFireTruck", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FireTruckKt {
    private static ImageVector _fireTruck;

    public static final ImageVector getFireTruck(Icons.Rounded rounded) {
        ImageVector imageVector = _fireTruck;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FireTruck", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.9f, 10.69f, -1.44f, -4.32f);
        pathBuilderR.curveTo(21.18f, 5.55f, 20.42f, 5.0f, 19.56f, 5.0f);
        pathBuilderR.horizontalLineTo(19.0f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(-1.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(1.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.horizontalLineTo(3.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(3.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(1.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.horizontalLineToRelative(1.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-4.68f);
        pathBuilderR.curveTo(23.0f, 11.11f, 22.97f, 10.9f, 22.9f, 10.69f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.0f, 19.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.reflectiveCurveTo(7.55f, 19.0f, 7.0f, 19.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 19.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.reflectiveCurveTo(17.55f, 19.0f, 17.0f, 19.0f);
        b.t(pathBuilderR, 14.0f, 11.0f, 7.0f, 5.56f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderR, 1.33f, 4.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = v.b.h(10.25f, 8.5f, 10.0f, -2.0f, 0.25f);
        pathBuilderH.curveTo(10.66f, 6.5f, 11.0f, 6.16f, 11.0f, 5.75f);
        pathBuilderH.verticalLineToRelative(0.0f);
        pathBuilderH.curveTo(11.0f, 5.34f, 10.66f, 5.0f, 10.25f, 5.0f);
        pathBuilderH.horizontalLineToRelative(-8.5f);
        pathBuilderH.curveTo(1.34f, 5.0f, 1.0f, 5.34f, 1.0f, 5.75f);
        pathBuilderH.verticalLineToRelative(0.0f);
        pathBuilderH.curveTo(1.0f, 6.16f, 1.34f, 6.5f, 1.75f, 6.5f);
        a0.a.w(pathBuilderH, 2.0f, 2.0f, 1.75f);
        pathBuilderH.curveTo(1.34f, 8.5f, 1.0f, 8.84f, 1.0f, 9.25f);
        pathBuilderH.verticalLineToRelative(0.0f);
        pathBuilderH.curveTo(1.0f, 9.66f, 1.34f, 10.0f, 1.75f, 10.0f);
        pathBuilderH.horizontalLineToRelative(8.5f);
        pathBuilderH.curveTo(10.66f, 10.0f, 11.0f, 9.66f, 11.0f, 9.25f);
        pathBuilderH.verticalLineToRelative(0.0f);
        pathBuilderH.curveTo(11.0f, 8.84f, 10.66f, 8.5f, 10.25f, 8.5f);
        c.C(pathBuilderH, 5.25f, 8.5f, 3.5f, -2.0f);
        c.D(pathBuilderH, 1.75f, 8.5f, 8.5f, 8.5f);
        pathBuilderH.horizontalLineTo(6.75f);
        pathBuilderH.verticalLineToRelative(-2.0f);
        pathBuilderH.horizontalLineTo(8.5f);
        pathBuilderH.verticalLineTo(8.5f);
        pathBuilderH.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderH.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fireTruck = imageVectorBuild;
        return imageVectorBuild;
    }
}

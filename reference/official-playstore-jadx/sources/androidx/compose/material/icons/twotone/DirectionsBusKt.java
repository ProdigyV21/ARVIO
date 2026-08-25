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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBus", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDirectionsBus", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBusKt {
    private static ImageVector _directionsBus;

    public static final ImageVector getDirectionsBus(Icons.TwoTone twoTone) {
        ImageVector imageVector = _directionsBus;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DirectionsBus", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.37f, 17.0f, 0.29f, -0.27f);
        pathBuilderR.curveToRelative(0.13f, -0.11f, 0.34f, -0.36f, 0.34f, -0.73f);
        d.r(pathBuilderR, -4.0f, 6.0f, 12.0f, 4.0f);
        pathBuilderR.curveToRelative(0.0f, 0.37f, 0.21f, 0.62f, 0.34f, 0.73f);
        y.a.k(pathBuilderR, 0.29f, 0.27f, 10.74f);
        pathBuilderR.moveTo(8.5f, 16.0f);
        pathBuilderR.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderR.reflectiveCurveTo(7.67f, 13.0f, 8.5f, 13.0f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderR.reflectiveCurveTo(9.33f, 16.0f, 8.5f, 16.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(14.0f, 14.5f);
        pathBuilderR.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderR.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderR.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 4.0f);
        pathBuilderR.curveToRelative(-3.69f, 0.0f, -5.11f, 0.46f, -5.66f, 0.99f);
        pathBuilderR.horizontalLineToRelative(11.31f);
        pathBuilderR.curveTo(17.11f, 4.46f, 15.69f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(17.0f, 21.0f, 1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.78f);
        pathBuilderA.curveToRelative(0.61f, -0.55f, 1.0f, -1.34f, 1.0f, -2.22f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -3.5f, -3.58f, -4.0f, -8.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(-8.0f, 0.5f, -8.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 0.88f, 0.39f, 1.67f, 1.0f, 2.22f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderA, -1.0f, 8.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 4.0f);
        pathBuilderA.curveToRelative(3.69f, 0.0f, 5.11f, 0.46f, 5.66f, 0.99f);
        pathBuilderA.horizontalLineTo(6.34f);
        pathBuilderA.curveTo(6.89f, 4.46f, 8.31f, 4.0f, 12.0f, 4.0f);
        a.u(pathBuilderA, 6.0f, 6.99f, 12.0f, 10.0f);
        c.l(pathBuilderA, 6.0f, 6.99f, 8.0f, 17.0f);
        pathBuilderA.horizontalLineTo(6.63f);
        pathBuilderA.lineToRelative(-0.29f, -0.27f);
        pathBuilderA.curveTo(6.21f, 16.62f, 6.0f, 16.37f, 6.0f, 16.0f);
        e.l(pathBuilderA, -4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.37f, -0.21f, 0.62f, -0.34f, 0.73f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderA, -0.29f, 0.27f, 8.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
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
        _directionsBus = imageVectorBuild;
        return imageVectorBuild;
    }
}

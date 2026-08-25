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
import v.b;
import v.c;
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_subway", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Subway", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSubway", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SubwayKt {
    private static ImageVector _subway;

    public static final ImageVector getSubway(Icons.TwoTone twoTone) {
        ImageVector imageVector = _subway;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Subway", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(10.67f, 18.5f, 9.17f, 20.0f, 5.66f);
        b.C(pathBuilderT, -1.5f, -1.5f, 17.07f, 4.66f);
        pathBuilderT.curveTo(15.59f, 4.08f, 13.68f, 4.0f, 12.0f, 4.0f);
        pathBuilderT.reflectiveCurveToRelative(-3.59f, 0.08f, -5.07f, 0.66f);
        pathBuilderT.curveTo(5.01f, 5.41f, 4.0f, 6.86f, 4.0f, 8.86f);
        b.A(pathBuilderT, 4.0f, 20.0f, 3.5f, -0.38f);
        pathBuilderT.lineToRelative(1.15f, -1.16f);
        pathBuilderT.curveTo(7.16f, 18.29f, 6.0f, 17.04f, 6.0f, 15.5f);
        pathBuilderT.lineTo(6.0f, 9.0f);
        pathBuilderT.curveToRelative(0.0f, -2.63f, 3.0f, -3.0f, 6.0f, -3.0f);
        pathBuilderT.reflectiveCurveToRelative(6.0f, 0.37f, 6.0f, 3.0f);
        pathBuilderT.verticalLineToRelative(6.5f);
        pathBuilderT.curveToRelative(0.0f, 1.54f, -1.16f, 2.79f, -2.65f, 2.96f);
        pathBuilderT.lineToRelative(1.15f, 1.16f);
        pathBuilderT.lineTo(16.5f, 20.0f);
        pathBuilderT.lineTo(20.0f, 20.0f);
        pathBuilderT.lineTo(20.0f, 8.86f);
        pathBuilderT.curveToRelative(0.0f, -2.0f, -1.01f, -3.45f, -2.93f, -4.2f);
        pathBuilderT.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderT.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.8f, 2.8f);
        pathBuilderA.curveTo(16.0f, 2.09f, 13.86f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.0f, 0.09f, -5.8f, 0.8f);
        pathBuilderA.curveTo(3.53f, 3.84f, 2.0f, 6.05f, 2.0f, 8.86f);
        pathBuilderA.lineTo(2.0f, 22.0f);
        pathBuilderA.horizontalLineToRelative(20.0f);
        pathBuilderA.lineTo(22.0f, 8.86f);
        pathBuilderA.curveToRelative(0.0f, -2.81f, -1.53f, -5.02f, -4.2f, -6.06f);
        v.a.z(pathBuilderA, 9.17f, 20.0f, 1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(2.66f);
        d.C(pathBuilderA, 1.5f, 1.5f, 9.17f, 20.0f);
        pathBuilderA.moveTo(7.01f, 14.0f);
        b.A(pathBuilderA, 7.01f, 9.0f, 10.0f, 5.0f);
        f.x(pathBuilderA, -10.0f, 16.5f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 15.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        b.w(pathBuilderA, 20.0f, 20.0f, -3.5f, -0.38f);
        pathBuilderA.lineToRelative(-1.15f, -1.16f);
        pathBuilderA.curveToRelative(1.49f, -0.17f, 2.65f, -1.42f, 2.65f, -2.96f);
        pathBuilderA.lineTo(18.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -2.63f, -3.0f, -3.0f, -6.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, 0.37f, -6.0f, 3.0f);
        pathBuilderA.verticalLineToRelative(6.5f);
        pathBuilderA.curveToRelative(0.0f, 1.54f, 1.16f, 2.79f, 2.65f, 2.96f);
        pathBuilderA.lineTo(7.5f, 19.62f);
        pathBuilderA.lineTo(7.5f, 20.0f);
        pathBuilderA.lineTo(4.0f, 20.0f);
        pathBuilderA.lineTo(4.0f, 8.86f);
        pathBuilderA.curveToRelative(0.0f, -2.0f, 1.01f, -3.45f, 2.93f, -4.2f);
        pathBuilderA.curveTo(8.41f, 4.08f, 10.32f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(3.59f, 0.08f, 5.07f, 0.66f);
        pathBuilderA.curveToRelative(1.92f, 0.75f, 2.93f, 2.2f, 2.93f, 4.2f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _subway = imageVectorBuild;
        return imageVectorBuild;
    }
}

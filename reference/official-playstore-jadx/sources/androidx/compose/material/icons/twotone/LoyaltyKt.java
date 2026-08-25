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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_loyalty", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Loyalty", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLoyalty", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LoyaltyKt {
    private static ImageVector _loyalty;

    public static final ImageVector getLoyalty(Icons.TwoTone twoTone) {
        ImageVector imageVector = _loyalty;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Loyalty", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(11.0f, 4.0f, 4.0f, 4.0f, 7.0f);
        pathBuilderS.lineToRelative(9.0f, 9.01f);
        d.l(pathBuilderS, 20.0f, 13.0f, -9.0f, -9.0f);
        pathBuilderS.moveTo(6.5f, 8.0f);
        pathBuilderS.curveTo(5.67f, 8.0f, 5.0f, 7.33f, 5.0f, 6.5f);
        pathBuilderS.reflectiveCurveTo(5.67f, 5.0f, 6.5f, 5.0f);
        pathBuilderS.reflectiveCurveTo(8.0f, 5.67f, 8.0f, 6.5f);
        pathBuilderS.reflectiveCurveTo(7.33f, 8.0f, 6.5f, 8.0f);
        a.z(pathBuilderS, 13.0f, 11.7f, 0.6f, -0.6f);
        pathBuilderS.curveToRelative(0.37f, -0.37f, 0.89f, -0.6f, 1.45f, -0.6f);
        pathBuilderS.curveToRelative(1.13f, 0.0f, 2.05f, 0.92f, 2.05f, 2.05f);
        pathBuilderS.curveToRelative(0.0f, 0.57f, -0.23f, 1.08f, -0.6f, 1.45f);
        pathBuilderS.lineTo(13.0f, 17.5f);
        pathBuilderS.lineTo(9.5f, 14.0f);
        pathBuilderS.curveToRelative(-0.37f, -0.38f, -0.6f, -0.89f, -0.6f, -1.45f);
        pathBuilderS.curveToRelative(0.0f, -1.13f, 0.92f, -2.05f, 2.05f, -2.05f);
        pathBuilderS.curveToRelative(0.57f, 0.0f, 1.08f, 0.23f, 1.45f, 0.61f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderS, 0.6f, 0.59f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.41f, 11.58f, -9.0f, -9.0f);
        pathBuilderR.curveTo(12.05f, 2.22f, 11.55f, 2.0f, 11.0f, 2.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.22f, 1.05f, 0.59f, 1.42f);
        pathBuilderR.lineToRelative(9.0f, 9.0f);
        pathBuilderR.curveToRelative(0.36f, 0.36f, 0.86f, 0.58f, 1.41f, 0.58f);
        pathBuilderR.reflectiveCurveToRelative(1.05f, -0.22f, 1.41f, -0.59f);
        pathBuilderR.lineToRelative(7.0f, -7.0f);
        pathBuilderR.curveToRelative(0.37f, -0.36f, 0.59f, -0.86f, 0.59f, -1.41f);
        pathBuilderR.reflectiveCurveToRelative(-0.23f, -1.06f, -0.59f, -1.42f);
        a.p(pathBuilderR, 13.0f, 20.01f, 4.0f, 11.0f);
        w.a.j(pathBuilderR, 4.0f, 7.0f, -0.01f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 9.0f, 9.0f, -7.0f, 7.02f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(6.5f, 6.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.9f, 12.55f);
        pathBuilderA.curveToRelative(0.0f, 0.57f, 0.23f, 1.07f, 0.6f, 1.45f);
        pathBuilderA.lineToRelative(3.5f, 3.5f);
        pathBuilderA.lineToRelative(3.5f, -3.5f);
        pathBuilderA.curveToRelative(0.37f, -0.37f, 0.6f, -0.89f, 0.6f, -1.45f);
        pathBuilderA.curveToRelative(0.0f, -1.13f, -0.92f, -2.05f, -2.05f, -2.05f);
        pathBuilderA.curveToRelative(-0.57f, 0.0f, -1.08f, 0.23f, -1.45f, 0.6f);
        pathBuilderA.lineToRelative(-0.6f, 0.6f);
        pathBuilderA.lineToRelative(-0.6f, -0.59f);
        pathBuilderA.curveToRelative(-0.37f, -0.38f, -0.89f, -0.61f, -1.45f, -0.61f);
        pathBuilderA.curveToRelative(-1.13f, 0.0f, -2.05f, 0.92f, -2.05f, 2.05f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _loyalty = imageVectorBuild;
        return imageVectorBuild;
    }
}

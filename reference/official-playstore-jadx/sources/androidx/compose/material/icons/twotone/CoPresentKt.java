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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_coPresent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CoPresent", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCoPresent", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoPresentKt {
    private static ImageVector _coPresent;

    public static final ImageVector getCoPresent(Icons.TwoTone twoTone) {
        ImageVector imageVector = _coPresent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CoPresent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(9.0f, 10.0f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.48f, 18.34f);
        pathBuilderA.curveTo(13.29f, 17.73f, 11.37f, 17.0f, 9.0f, 17.0f);
        pathBuilderA.curveToRelative(-2.37f, 0.0f, -4.29f, 0.73f, -5.48f, 1.34f);
        pathBuilderA.curveTo(2.9f, 18.66f, 3.0f, 19.28f, 3.0f, 20.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveTo(15.0f, 19.29f, 15.11f, 18.66f, 14.48f, 18.34f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 3.0f, 3.0f);
        pathBuilderQ.curveTo(1.9f, 3.0f, 1.0f, 3.9f, 1.0f, 5.0f);
        a.y(pathBuilderQ, 8.0f, 2.0f, 5.0f, 18.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(23.0f, 3.9f, 22.1f, 3.0f, 21.0f, 3.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(13.0f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA2.reflectiveCurveToRelative(-4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA2.reflectiveCurveTo(13.0f, 12.21f, 13.0f, 10.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(7.0f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 7.0f, 11.1f, 7.0f, 10.0f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(15.39f, 16.56f);
        pathBuilderA3.curveTo(13.71f, 15.7f, 11.53f, 15.0f, 9.0f, 15.0f);
        pathBuilderA3.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA3.curveTo(1.61f, 17.07f, 1.0f, 18.1f, 1.0f, 19.22f);
        w.a.j(pathBuilderA3, 22.0f, 16.0f, -2.78f);
        pathBuilderA3.curveTo(17.0f, 18.1f, 16.39f, 17.07f, 15.39f, 16.56f);
        w.a.r(pathBuilderA3, 15.0f, 20.0f, 3.0f);
        pathBuilderA3.curveToRelative(0.0f, -0.72f, -0.1f, -1.34f, 0.52f, -1.66f);
        pathBuilderA3.curveTo(4.71f, 17.73f, 6.63f, 17.0f, 9.0f, 17.0f);
        pathBuilderA3.curveToRelative(2.37f, 0.0f, 4.29f, 0.73f, 5.48f, 1.34f);
        pathBuilderA3.curveTo(15.11f, 18.66f, 15.0f, 19.29f, 15.0f, 20.0f);
        pathBuilderA3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _coPresent = imageVectorBuild;
        return imageVectorBuild;
    }
}

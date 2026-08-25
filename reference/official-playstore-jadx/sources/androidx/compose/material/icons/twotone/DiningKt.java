package androidx.compose.material.icons.twotone;

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
import v.c;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DiningKt {
    private static ImageVector _dining;

    public static final ImageVector getDining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _dining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Dining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 20.0f, 16.0f, 4.0f, 4.0f);
        f.r(pathBuilderS, 20.0f, 14.75f, 6.0f);
        pathBuilderS.curveToRelative(1.38f, 0.0f, 2.5f, 1.53f, 2.5f, 3.41f);
        pathBuilderS.curveToRelative(0.0f, 1.48f, -0.7f, 2.71f, -1.67f, 3.18f);
        b.m(pathBuilderS, -0.08f, 0.03f, 19.0f, 14.0f);
        pathBuilderS.verticalLineToRelative(-6.38f);
        pathBuilderS.lineToRelative(-0.08f, -0.04f);
        pathBuilderS.curveToRelative(-0.97f, -0.47f, -1.67f, -1.7f, -1.67f, -3.18f);
        pathBuilderS.curveTo(12.25f, 7.52f, 13.38f, 6.0f, 14.75f, 6.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(6.5f, 6.5f);
        pathBuilderS.curveTo(6.5f, 6.22f, 6.72f, 6.0f, 7.0f, 6.0f);
        pathBuilderS.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        c.i(pathBuilderS, 9.0f, 0.75f, 6.5f);
        pathBuilderS.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderS.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        b.y(pathBuilderS, 9.0f, 10.0f, 6.5f);
        pathBuilderS.curveTo(10.0f, 6.22f, 10.23f, 6.0f, 10.5f, 6.0f);
        pathBuilderS.curveTo(10.78f, 6.0f, 11.0f, 6.22f, 11.0f, 6.5f);
        pathBuilderS.verticalLineToRelative(3.8f);
        pathBuilderS.curveToRelative(0.0f, 0.93f, -0.64f, 1.71f, -1.5f, 1.93f);
        a0.a.s(pathBuilderS, 19.0f, 8.0f, -6.77f);
        pathBuilderS.curveToRelative(-0.86f, -0.22f, -1.5f, -1.0f, -1.5f, -1.93f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderS, 6.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        f.o(pathBuilderQ, 20.0f, 20.0f, 4.0f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 16.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a0.a.a(8.0f, 12.23f, 19.0f, 1.5f, -6.77f);
        pathBuilderA.curveToRelative(0.86f, -0.22f, 1.5f, -1.0f, 1.5f, -1.93f);
        pathBuilderA.verticalLineTo(6.5f);
        pathBuilderA.curveTo(11.0f, 6.22f, 10.78f, 6.0f, 10.5f, 6.0f);
        pathBuilderA.curveTo(10.23f, 6.0f, 10.0f, 6.22f, 10.0f, 6.5f);
        b.y(pathBuilderA, 9.0f, 9.25f, 6.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        b.y(pathBuilderA, 9.0f, 7.5f, 6.5f);
        pathBuilderA.curveTo(7.5f, 6.22f, 7.28f, 6.0f, 7.0f, 6.0f);
        pathBuilderA.reflectiveCurveTo(6.5f, 6.22f, 6.5f, 6.5f);
        pathBuilderA.verticalLineToRelative(3.8f);
        pathBuilderA.curveTo(6.5f, 11.23f, 7.14f, 12.01f, 8.0f, 12.23f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = w.a.n(13.92f, 12.58f, 14.0f, 12.62f, 19.0f);
        f.y(pathBuilderN, 1.5f, -6.38f, 0.08f, -0.03f);
        pathBuilderN.curveToRelative(0.97f, -0.47f, 1.67f, -1.7f, 1.67f, -3.18f);
        pathBuilderN.curveToRelative(0.0f, -1.88f, -1.12f, -3.41f, -2.5f, -3.41f);
        pathBuilderN.curveToRelative(-1.37f, 0.0f, -2.5f, 1.52f, -2.5f, 3.4f);
        pathBuilderN.curveTo(12.25f, 10.88f, 12.95f, 12.11f, 13.92f, 12.58f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dining = imageVectorBuild;
        return imageVectorBuild;
    }
}

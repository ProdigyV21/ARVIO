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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_breakfastDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BreakfastDining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBreakfastDining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BreakfastDiningKt {
    private static ImageVector _breakfastDining;

    public static final ImageVector getBreakfastDining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _breakfastDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.BreakfastDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 5.0f, 6.0f);
        pathBuilderQ.curveTo(4.9f, 5.0f, 4.0f, 5.9f, 4.0f, 7.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.71f, 0.38f, 1.35f, 1.01f, 1.73f);
        b.n(pathBuilderQ, 6.0f, 9.31f, 19.0f, 12.0f);
        pathBuilderQ.verticalLineTo(9.3f);
        pathBuilderQ.lineToRelative(1.0f, -0.58f);
        pathBuilderQ.curveToRelative(0.63f, -0.36f, 1.0f, -1.01f, 1.0f, -1.72f);
        pathBuilderQ.curveTo(20.0f, 5.9f, 19.1f, 5.0f, 18.0f, 5.0f);
        a.z(pathBuilderQ, 15.71f, 13.7f, -3.0f, 3.0f);
        pathBuilderQ.curveTo(12.51f, 16.9f, 12.26f, 17.0f, 12.0f, 17.0f);
        pathBuilderQ.reflectiveCurveToRelative(-0.51f, -0.1f, -0.71f, -0.29f);
        pathBuilderQ.lineToRelative(-3.0f, -3.0f);
        pathBuilderQ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderQ.lineToRelative(3.0f, -3.0f);
        pathBuilderQ.curveTo(11.49f, 9.1f, 11.74f, 9.0f, 12.0f, 9.0f);
        pathBuilderQ.reflectiveCurveToRelative(0.51f, 0.1f, 0.71f, 0.29f);
        pathBuilderQ.lineToRelative(3.0f, 3.0f);
        pathBuilderQ.curveTo(16.1f, 12.68f, 16.1f, 13.31f, 15.71f, 13.7f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ2 = a.q(18.0f, 3.0f, 6.0f);
        pathBuilderQ2.curveTo(3.79f, 3.0f, 2.0f, 4.79f, 2.0f, 7.0f);
        pathBuilderQ2.curveToRelative(0.0f, 1.48f, 0.81f, 2.75f, 2.0f, 3.45f);
        pathBuilderQ2.verticalLineTo(19.0f);
        pathBuilderQ2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ2.horizontalLineToRelative(12.0f);
        pathBuilderQ2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ2.verticalLineToRelative(-8.55f);
        pathBuilderQ2.curveToRelative(1.19f, -0.69f, 2.0f, -1.97f, 2.0f, -3.45f);
        pathBuilderQ2.curveTo(22.0f, 4.79f, 20.21f, 3.0f, 18.0f, 3.0f);
        a.p(pathBuilderQ2, 19.0f, 8.72f, 18.0f, 9.3f);
        a0.b.y(pathBuilderQ2, 19.0f, 6.0f, 9.31f);
        pathBuilderQ2.lineTo(5.01f, 8.73f);
        pathBuilderQ2.curveTo(4.38f, 8.35f, 4.0f, 7.71f, 4.0f, 7.0f);
        pathBuilderQ2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderQ2.horizontalLineToRelative(12.0f);
        pathBuilderQ2.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderQ2.curveTo(20.0f, 7.71f, 19.62f, 8.36f, 19.0f, 8.72f);
        pathBuilderQ2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.71f, 9.29f);
        pathBuilderA.curveTo(12.51f, 9.1f, 12.26f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.51f, 0.1f, -0.71f, 0.29f);
        pathBuilderA.lineToRelative(-3.0f, 3.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(3.0f, 3.0f);
        pathBuilderA.curveTo(11.49f, 16.9f, 11.74f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.reflectiveCurveToRelative(0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilderA.lineToRelative(3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        c.z(pathBuilderA, 12.71f, 9.29f, 12.0f, 14.58f);
        pathBuilderA.lineTo(10.41f, 13.0f);
        pathBuilderA.lineTo(12.0f, 11.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 13.59f, 13.0f, 12.0f, 14.58f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _breakfastDining = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Swipe", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSwipe", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeKt {
    private static ImageVector _swipe;

    public static final ImageVector getSwipe(Icons.TwoTone twoTone) {
        ImageVector imageVector = _swipe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Swipe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.13f, 3.87f);
        pathBuilderA.curveTo(18.69f, 2.17f, 15.6f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(5.31f, 2.17f, 3.87f, 3.87f);
        a.h(pathBuilderA, 2.0f, 2.0f, 5.0f, 5.0f);
        pathBuilderA.lineTo(4.93f, 4.93f);
        pathBuilderA.curveToRelative(1.0f, -1.29f, 3.7f, -2.43f, 7.07f, -2.43f);
        pathBuilderA.reflectiveCurveToRelative(6.07f, 1.14f, 7.07f, 2.43f);
        e.B(pathBuilderA, 17.0f, 7.0f, 5.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.13f, 3.87f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = e.a(12.0f, 13.68f, 7.5f);
        pathBuilderA2.curveTo(12.0f, 7.22f, 11.78f, 7.0f, 11.5f, 7.0f);
        pathBuilderA2.reflectiveCurveTo(11.0f, 7.22f, 11.0f, 7.5f);
        pathBuilderA2.verticalLineToRelative(10.61f);
        pathBuilderA2.lineToRelative(-4.17f, -0.89f);
        pathBuilderA2.lineToRelative(3.7f, 3.78f);
        pathBuilderA2.horizontalLineToRelative(6.55f);
        pathBuilderA2.lineTo(18.0f, 15.56f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderA2, -4.24f, -1.89f, 12.0f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.89f, 13.77f, -3.8f, -1.67f);
        pathBuilderR.curveTo(14.96f, 12.04f, 14.81f, 12.0f, 14.65f, 12.0f);
        pathBuilderR.horizontalLineTo(14.0f);
        pathBuilderR.verticalLineTo(7.63f);
        pathBuilderR.curveToRelative(0.0f, -1.32f, -0.96f, -2.5f, -2.27f, -2.62f);
        pathBuilderR.curveTo(10.25f, 4.88f, 9.0f, 6.05f, 9.0f, 7.5f);
        pathBuilderR.verticalLineToRelative(8.15f);
        pathBuilderR.lineToRelative(-1.87f, -0.4f);
        pathBuilderR.curveToRelative(-0.19f, -0.03f, -1.02f, -0.15f, -1.73f, 0.56f);
        pathBuilderR.lineTo(4.0f, 17.22f);
        pathBuilderR.lineToRelative(5.12f, 5.19f);
        pathBuilderR.curveTo(9.49f, 22.79f, 10.0f, 23.0f, 10.53f, 23.0f);
        pathBuilderR.horizontalLineToRelative(6.55f);
        pathBuilderR.curveToRelative(0.98f, 0.0f, 1.81f, -0.7f, 1.97f, -1.67f);
        pathBuilderR.lineToRelative(0.92f, -5.44f);
        pathBuilderR.curveTo(20.12f, 15.03f, 19.68f, 14.17f, 18.89f, 13.77f);
        a.g(pathBuilderR, 17.08f, 21.0f, -6.55f);
        pathBuilderR.lineToRelative(-3.7f, -3.78f);
        pathBuilderR.lineTo(11.0f, 18.11f);
        pathBuilderR.verticalLineTo(7.5f);
        pathBuilderR.curveTo(11.0f, 7.22f, 11.22f, 7.0f, 11.5f, 7.0f);
        pathBuilderR.reflectiveCurveTo(12.0f, 7.22f, 12.0f, 7.5f);
        c.w(pathBuilderR, 6.18f, 1.76f, 18.0f, 15.56f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 17.08f, 21.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipe = imageVectorBuild;
        return imageVectorBuild;
    }
}

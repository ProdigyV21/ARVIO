package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Swipe", "Landroidx/compose/material/icons/Icons$Rounded;", "getSwipe", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeKt {
    private static ImageVector _swipe;

    public static final ImageVector getSwipe(Icons.Rounded rounded) {
        ImageVector imageVector = _swipe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Swipe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.15f, 2.85f, -1.02f, 1.02f);
        pathBuilderR.curveTo(18.69f, 2.17f, 15.6f, 1.0f, 12.0f, 1.0f);
        pathBuilderR.reflectiveCurveTo(5.31f, 2.17f, 3.87f, 3.87f);
        pathBuilderR.lineTo(2.85f, 2.85f);
        pathBuilderR.curveTo(2.54f, 2.54f, 2.0f, 2.76f, 2.0f, 3.21f);
        pathBuilderR.verticalLineTo(6.5f);
        pathBuilderR.curveTo(2.0f, 6.78f, 2.22f, 7.0f, 2.5f, 7.0f);
        pathBuilderR.horizontalLineToRelative(3.29f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderR.lineTo(4.93f, 4.93f);
        pathBuilderR.curveToRelative(1.0f, -1.29f, 3.7f, -2.43f, 7.07f, -2.43f);
        pathBuilderR.reflectiveCurveToRelative(6.07f, 1.14f, 7.07f, 2.43f);
        pathBuilderR.lineToRelative(-1.22f, 1.22f);
        pathBuilderR.curveTo(17.54f, 6.46f, 17.76f, 7.0f, 18.21f, 7.0f);
        pathBuilderR.horizontalLineToRelative(3.29f);
        pathBuilderR.curveTo(21.78f, 7.0f, 22.0f, 6.78f, 22.0f, 6.5f);
        pathBuilderR.verticalLineTo(3.21f);
        pathBuilderR.curveTo(22.0f, 2.76f, 21.46f, 2.54f, 21.15f, 2.85f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.5f, 12.71f);
        pathBuilderA.curveToRelative(-0.28f, -0.14f, -0.58f, -0.21f, -0.89f, -0.21f);
        pathBuilderA.horizontalLineTo(13.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveTo(13.0f, 5.67f, 12.33f, 5.0f, 11.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(10.0f, 5.67f, 10.0f, 6.5f);
        pathBuilderA.verticalLineToRelative(10.74f);
        pathBuilderA.lineToRelative(-3.44f, -0.72f);
        pathBuilderA.curveToRelative(-0.37f, -0.08f, -0.76f, 0.04f, -1.03f, 0.31f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.43f, 0.44f, -0.43f, 1.14f, 0.01f, 1.58f);
        pathBuilderA.lineToRelative(4.01f, 4.01f);
        pathBuilderA.curveTo(9.92f, 22.79f, 10.43f, 23.0f, 10.96f, 23.0f);
        pathBuilderA.horizontalLineToRelative(6.41f);
        pathBuilderA.curveToRelative(1.0f, 0.0f, 1.84f, -0.73f, 1.98f, -1.72f);
        pathBuilderA.lineToRelative(0.63f, -4.46f);
        pathBuilderA.curveToRelative(0.12f, -0.85f, -0.32f, -1.69f, -1.09f, -2.07f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 14.5f, 12.71f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipe = imageVectorBuild;
        return imageVectorBuild;
    }
}

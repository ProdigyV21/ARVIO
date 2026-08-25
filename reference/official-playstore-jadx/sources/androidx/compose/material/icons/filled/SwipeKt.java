package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Swipe", "Landroidx/compose/material/icons/Icons$Filled;", "getSwipe", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeKt {
    private static ImageVector _swipe;

    public static final ImageVector getSwipe(Icons.Filled filled) {
        ImageVector imageVector = _swipe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Swipe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.89f, 14.75f, -4.09f, -2.04f);
        pathBuilderR.curveToRelative(-0.28f, -0.14f, -0.58f, -0.21f, -0.89f, -0.21f);
        pathBuilderR.horizontalLineTo(13.0f);
        pathBuilderR.verticalLineToRelative(-6.0f);
        pathBuilderR.curveTo(13.0f, 5.67f, 12.33f, 5.0f, 11.5f, 5.0f);
        pathBuilderR.reflectiveCurveTo(10.0f, 5.67f, 10.0f, 6.5f);
        pathBuilderR.verticalLineToRelative(10.74f);
        pathBuilderR.lineTo(6.75f, 16.5f);
        pathBuilderR.curveToRelative(-0.33f, -0.07f, -0.68f, 0.03f, -0.92f, 0.28f);
        pathBuilderR.lineTo(5.0f, 17.62f);
        pathBuilderR.lineToRelative(4.54f, 4.79f);
        pathBuilderR.curveTo(9.92f, 22.79f, 10.68f, 23.0f, 11.21f, 23.0f);
        pathBuilderR.horizontalLineToRelative(6.16f);
        pathBuilderR.curveToRelative(1.0f, 0.0f, 1.84f, -0.73f, 1.98f, -1.72f);
        pathBuilderR.lineToRelative(0.63f, -4.46f);
        pathBuilderR.curveTo(20.1f, 15.97f, 19.66f, 15.14f, 18.89f, 14.75f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.13f, 3.87f);
        pathBuilderA.curveTo(18.69f, 2.17f, 15.6f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(5.31f, 2.17f, 3.87f, 3.87f);
        a.h(pathBuilderA, 2.0f, 2.0f, 5.0f, 5.0f);
        pathBuilderA.lineTo(4.93f, 4.93f);
        pathBuilderA.curveToRelative(1.0f, -1.29f, 3.7f, -2.43f, 7.07f, -2.43f);
        pathBuilderA.reflectiveCurveToRelative(6.07f, 1.14f, 7.07f, 2.43f);
        e.B(pathBuilderA, 17.0f, 7.0f, 5.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.13f, 3.87f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipe = imageVectorBuild;
        return imageVectorBuild;
    }
}

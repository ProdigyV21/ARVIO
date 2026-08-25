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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeLeft", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeLeft", "Landroidx/compose/material/icons/Icons$Filled;", "getSwipeLeft", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeLeftKt {
    private static ImageVector _swipeLeft;

    public static final ImageVector getSwipeLeft(Icons.Filled filled) {
        ImageVector imageVector = _swipeLeft;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SwipeLeft", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.98f, 16.82f, -0.63f, 4.46f);
        pathBuilderR.curveTo(19.21f, 22.27f, 18.36f, 23.0f, 17.37f, 23.0f);
        pathBuilderR.horizontalLineToRelative(-6.16f);
        pathBuilderR.curveToRelative(-0.53f, 0.0f, -1.29f, -0.21f, -1.66f, -0.59f);
        pathBuilderR.lineTo(5.0f, 17.62f);
        pathBuilderR.lineToRelative(0.83f, -0.84f);
        pathBuilderR.curveToRelative(0.24f, -0.24f, 0.58f, -0.35f, 0.92f, -0.28f);
        pathBuilderR.lineTo(10.0f, 17.24f);
        pathBuilderR.verticalLineTo(6.5f);
        pathBuilderR.curveTo(10.0f, 5.67f, 10.67f, 5.0f, 11.5f, 5.0f);
        pathBuilderR.reflectiveCurveTo(13.0f, 5.67f, 13.0f, 6.5f);
        pathBuilderR.verticalLineToRelative(6.0f);
        pathBuilderR.horizontalLineToRelative(0.91f);
        pathBuilderR.curveToRelative(0.31f, 0.0f, 0.62f, 0.07f, 0.89f, 0.21f);
        pathBuilderR.lineToRelative(4.09f, 2.04f);
        pathBuilderR.curveTo(19.66f, 15.14f, 20.1f, 15.97f, 19.98f, 16.82f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 2.5f);
        pathBuilderR.curveToRelative(4.74f, 0.0f, 7.67f, 2.52f, 8.43f, 4.5f);
        pathBuilderR.horizontalLineTo(22.0f);
        pathBuilderR.curveToRelative(-0.73f, -2.88f, -4.51f, -6.0f, -10.0f, -6.0f);
        pathBuilderR.curveTo(8.78f, 1.0f, 5.82f, 2.13f, 3.5f, 4.02f);
        c.o(pathBuilderR, 2.0f, 2.0f, 5.0f, 5.0f);
        pathBuilderR.verticalLineTo(5.5f);
        pathBuilderR.horizontalLineTo(4.09f);
        pathBuilderR.curveTo(6.21f, 3.64f, 8.97f, 2.5f, 12.0f, 2.5f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeLeft = imageVectorBuild;
        return imageVectorBuild;
    }
}

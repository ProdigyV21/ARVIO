package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeRight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeRight", "Landroidx/compose/material/icons/Icons$Outlined;", "getSwipeRight", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeRightKt {
    private static ImageVector _swipeRight;

    public static final ImageVector getSwipeRight(Icons.Outlined outlined) {
        ImageVector imageVector = _swipeRight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SwipeRight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.89f, 13.77f, -3.8f, -1.67f);
        pathBuilderR.curveTo(14.96f, 12.04f, 14.81f, 12.0f, 14.65f, 12.0f);
        pathBuilderR.horizontalLineTo(14.0f);
        pathBuilderR.verticalLineTo(7.5f);
        pathBuilderR.curveTo(14.0f, 6.12f, 12.88f, 5.0f, 11.5f, 5.0f);
        pathBuilderR.reflectiveCurveTo(9.0f, 6.12f, 9.0f, 7.5f);
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
        c.z(pathBuilderR, 17.08f, 21.0f, 12.0f, 2.5f);
        pathBuilderR.curveTo(7.26f, 2.5f, 4.33f, 5.02f, 3.57f, 7.0f);
        pathBuilderR.horizontalLineTo(2.0f);
        pathBuilderR.curveToRelative(0.73f, -2.88f, 4.51f, -6.0f, 10.0f, -6.0f);
        pathBuilderR.curveToRelative(3.22f, 0.0f, 6.18f, 1.13f, 8.5f, 3.02f);
        c.o(pathBuilderR, 2.0f, 22.0f, 5.0f, -5.0f);
        pathBuilderR.verticalLineTo(5.5f);
        pathBuilderR.horizontalLineToRelative(2.91f);
        pathBuilderR.curveTo(17.79f, 3.64f, 15.03f, 2.5f, 12.0f, 2.5f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeRight = imageVectorBuild;
        return imageVectorBuild;
    }
}

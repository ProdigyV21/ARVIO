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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeRight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeRight", "Landroidx/compose/material/icons/Icons$Rounded;", "getSwipeRight", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeRightKt {
    private static ImageVector _swipeRight;

    public static final ImageVector getSwipeRight(Icons.Rounded rounded) {
        ImageVector imageVector = _swipeRight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SwipeRight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 1.0f);
        pathBuilderA.curveTo(7.19f, 1.0f, 3.7f, 3.39f, 2.41f, 5.92f);
        pathBuilderA.curveTo(2.16f, 6.41f, 2.53f, 7.0f, 3.09f, 7.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.54f, -0.15f, 0.66f, -0.4f);
        pathBuilderA.curveTo(4.73f, 4.69f, 7.58f, 2.5f, 12.0f, 2.5f);
        pathBuilderA.curveToRelative(3.03f, 0.0f, 5.79f, 1.14f, 7.91f, 3.0f);
        pathBuilderA.horizontalLineToRelative(-2.16f);
        pathBuilderA.curveTo(17.34f, 5.5f, 17.0f, 5.84f, 17.0f, 6.25f);
        pathBuilderA.reflectiveCurveTo(17.34f, 7.0f, 17.75f, 7.0f);
        pathBuilderA.horizontalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(2.75f);
        pathBuilderA.curveTo(22.0f, 2.34f, 21.66f, 2.0f, 21.25f, 2.0f);
        pathBuilderA.reflectiveCurveTo(20.5f, 2.34f, 20.5f, 2.75f);
        pathBuilderA.verticalLineToRelative(1.27f);
        pathBuilderA.curveTo(18.18f, 2.13f, 15.22f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.2f, 17.43f);
        pathBuilderA.curveToRelative(0.0f, -0.65f, 0.6f, -1.13f, 1.24f, -0.99f);
        pathBuilderA.lineTo(10.0f, 17.24f);
        pathBuilderA.verticalLineTo(6.5f);
        pathBuilderA.curveTo(10.0f, 5.67f, 10.67f, 5.0f, 11.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(13.0f, 5.67f, 13.0f, 6.5f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.horizontalLineToRelative(0.91f);
        pathBuilderA.curveToRelative(0.31f, 0.0f, 0.62f, 0.07f, 0.89f, 0.21f);
        pathBuilderA.lineToRelative(4.09f, 2.04f);
        pathBuilderA.curveToRelative(0.77f, 0.38f, 1.21f, 1.22f, 1.09f, 2.07f);
        pathBuilderA.lineToRelative(-0.63f, 4.46f);
        pathBuilderA.curveTo(19.21f, 22.27f, 18.36f, 23.0f, 17.37f, 23.0f);
        pathBuilderA.horizontalLineToRelative(-6.16f);
        pathBuilderA.curveToRelative(-0.53f, 0.0f, -1.29f, -0.21f, -1.66f, -0.59f);
        pathBuilderA.lineToRelative(-4.07f, -4.29f);
        pathBuilderA.curveTo(5.3f, 17.94f, 5.2f, 17.69f, 5.2f, 17.43f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeRight = imageVectorBuild;
        return imageVectorBuild;
    }
}

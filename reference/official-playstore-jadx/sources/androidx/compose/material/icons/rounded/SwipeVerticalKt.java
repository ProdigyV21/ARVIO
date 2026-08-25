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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeVertical", "Landroidx/compose/material/icons/Icons$Rounded;", "getSwipeVertical", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeVerticalKt {
    private static ImageVector _swipeVertical;

    public static final ImageVector getSwipeVertical(Icons.Rounded rounded) {
        ImageVector imageVector = _swipeVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SwipeVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(0.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 3.22f, 1.13f, 6.18f, 3.02f, 8.5f);
        pathBuilderA.horizontalLineTo(1.75f);
        pathBuilderA.curveTo(1.34f, 20.5f, 1.0f, 20.84f, 1.0f, 21.25f);
        pathBuilderA.reflectiveCurveTo(1.34f, 22.0f, 1.75f, 22.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.25f);
        pathBuilderA.curveTo(6.0f, 17.34f, 5.66f, 17.0f, 5.25f, 17.0f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderA.verticalLineToRelative(2.16f);
        pathBuilderA.curveToRelative(-1.86f, -2.11f, -3.0f, -4.88f, -3.0f, -7.91f);
        pathBuilderA.reflectiveCurveToRelative(1.14f, -5.79f, 3.0f, -7.91f);
        pathBuilderA.verticalLineToRelative(2.16f);
        pathBuilderA.curveTo(4.5f, 6.66f, 4.84f, 7.0f, 5.25f, 7.0f);
        pathBuilderA.curveTo(5.66f, 7.0f, 6.0f, 6.66f, 6.0f, 6.25f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(1.75f);
        pathBuilderA.curveTo(1.34f, 2.0f, 1.0f, 2.34f, 1.0f, 2.75f);
        pathBuilderA.reflectiveCurveTo(1.34f, 3.5f, 1.75f, 3.5f);
        pathBuilderA.horizontalLineToRelative(1.27f);
        pathBuilderA.curveTo(1.13f, 5.82f, 0.0f, 8.78f, 0.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.83f, 19.1f);
        pathBuilderA.curveToRelative(-0.26f, -0.6f, 0.09f, -1.28f, 0.73f, -1.41f);
        pathBuilderA.lineToRelative(3.58f, -0.71f);
        pathBuilderA.lineTo(8.79f, 7.17f);
        pathBuilderA.curveToRelative(-0.34f, -0.76f, 0.0f, -1.64f, 0.76f, -1.98f);
        pathBuilderA.curveToRelative(0.76f, -0.34f, 1.64f, 0.0f, 1.98f, 0.76f);
        pathBuilderA.lineToRelative(2.43f, 5.49f);
        pathBuilderA.lineToRelative(0.84f, -0.37f);
        pathBuilderA.curveToRelative(0.28f, -0.13f, 0.59f, -0.18f, 0.9f, -0.17f);
        pathBuilderA.lineToRelative(4.56f, 0.21f);
        pathBuilderA.curveToRelative(0.86f, 0.04f, 1.6f, 0.63f, 1.83f, 1.45f);
        pathBuilderA.lineToRelative(1.23f, 4.33f);
        pathBuilderA.curveToRelative(0.27f, 0.96f, -0.2f, 1.97f, -1.11f, 2.37f);
        pathBuilderA.lineToRelative(-5.63f, 2.49f);
        pathBuilderA.curveToRelative(-0.48f, 0.21f, -1.26f, 0.33f, -1.76f, 0.14f);
        pathBuilderA.lineToRelative(-5.45f, -2.27f);
        pathBuilderA.curveTo(9.13f, 19.53f, 8.93f, 19.34f, 8.83f, 19.1f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}

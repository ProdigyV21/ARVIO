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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeVertical", "Landroidx/compose/material/icons/Icons$Filled;", "getSwipeVertical", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeVerticalKt {
    private static ImageVector _swipeVertical;

    public static final ImageVector getSwipeVertical(Icons.Filled filled) {
        ImageVector imageVector = _swipeVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SwipeVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(1.0f, 3.5f, 2.02f);
        pathBuilderA.curveTo(1.13f, 5.82f, 0.0f, 8.78f, 0.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.13f, 6.18f, 3.02f, 8.5f);
        y.a.t(pathBuilderA, 1.0f, 22.0f, 5.0f, -5.0f);
        pathBuilderA.horizontalLineTo(4.5f);
        pathBuilderA.verticalLineToRelative(2.91f);
        pathBuilderA.curveToRelative(-1.86f, -2.11f, -3.0f, -4.88f, -3.0f, -7.91f);
        pathBuilderA.reflectiveCurveToRelative(1.14f, -5.79f, 3.0f, -7.91f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.verticalLineTo(2.0f);
        pathBuilderA.horizontalLineTo(1.0f);
        f.r(pathBuilderA, 3.5f, 13.85f, 11.62f);
        pathBuilderA.lineToRelative(-2.68f, -5.37f);
        pathBuilderA.curveToRelative(-0.37f, -0.74f, -1.27f, -1.04f, -2.01f, -0.67f);
        pathBuilderA.curveTo(8.41f, 5.96f, 8.11f, 6.86f, 8.48f, 7.6f);
        pathBuilderA.lineToRelative(4.81f, 9.6f);
        pathBuilderA.lineTo(10.05f, 18.0f);
        pathBuilderA.curveToRelative(-0.33f, 0.09f, -0.59f, 0.33f, -0.7f, 0.66f);
        pathBuilderA.lineTo(9.0f, 19.78f);
        pathBuilderA.lineToRelative(6.19f, 2.25f);
        pathBuilderA.curveToRelative(0.5f, 0.17f, 1.28f, 0.02f, 1.75f, -0.22f);
        pathBuilderA.lineToRelative(5.51f, -2.75f);
        pathBuilderA.curveToRelative(0.89f, -0.45f, 1.32f, -1.48f, 1.0f, -2.42f);
        pathBuilderA.lineToRelative(-1.43f, -4.27f);
        pathBuilderA.curveToRelative(-0.27f, -0.82f, -1.04f, -1.37f, -1.9f, -1.37f);
        pathBuilderA.horizontalLineToRelative(-4.56f);
        pathBuilderA.curveToRelative(-0.31f, 0.0f, -0.62f, 0.07f, -0.89f, 0.21f);
        pathBuilderA.lineTo(13.85f, 11.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}

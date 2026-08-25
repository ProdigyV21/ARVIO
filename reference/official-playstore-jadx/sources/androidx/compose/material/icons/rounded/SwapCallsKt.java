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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swapCalls", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwapCalls", "Landroidx/compose/material/icons/Icons$Rounded;", "getSwapCalls", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwapCallsKt {
    private static ImageVector _swapCalls;

    public static final ImageVector getSwapCalls(Icons.Rounded rounded) {
        ImageVector imageVector = _swapCalls;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SwapCalls", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.65f, 4.35f, -2.79f, 2.79f);
        pathBuilderR.curveToRelative(-0.32f, 0.32f, -0.1f, 0.86f, 0.35f, 0.86f);
        pathBuilderR.horizontalLineTo(17.0f);
        pathBuilderR.verticalLineToRelative(6.88f);
        pathBuilderR.curveToRelative(0.0f, 1.0f, -0.67f, 1.93f, -1.66f, 2.09f);
        pathBuilderR.curveToRelative(-1.25f, 0.21f, -2.34f, -0.76f, -2.34f, -1.97f);
        pathBuilderR.verticalLineTo(8.17f);
        pathBuilderR.curveToRelative(0.0f, -2.09f, -1.53f, -3.95f, -3.61f, -4.15f);
        pathBuilderR.curveTo(7.01f, 3.79f, 5.0f, 5.66f, 5.0f, 8.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.horizontalLineTo(3.21f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderR.lineToRelative(2.79f, 2.79f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderR.lineToRelative(2.79f, -2.79f);
        pathBuilderR.curveToRelative(0.31f, -0.31f, 0.09f, -0.85f, -0.36f, -0.85f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.verticalLineTo(8.12f);
        pathBuilderR.curveToRelative(0.0f, -1.0f, 0.67f, -1.93f, 1.66f, -2.09f);
        pathBuilderR.curveTo(9.91f, 5.82f, 11.0f, 6.79f, 11.0f, 8.0f);
        pathBuilderR.verticalLineToRelative(6.83f);
        pathBuilderR.curveToRelative(0.0f, 2.09f, 1.53f, 3.95f, 3.61f, 4.15f);
        pathBuilderR.curveTo(16.99f, 19.21f, 19.0f, 17.34f, 19.0f, 15.0f);
        pathBuilderR.verticalLineTo(8.0f);
        pathBuilderR.horizontalLineToRelative(1.79f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderR.lineToRelative(-2.79f, -2.79f);
        pathBuilderR.curveToRelative(-0.19f, -0.2f, -0.51f, -0.2f, -0.7f, -0.01f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swapCalls = imageVectorBuild;
        return imageVectorBuild;
    }
}

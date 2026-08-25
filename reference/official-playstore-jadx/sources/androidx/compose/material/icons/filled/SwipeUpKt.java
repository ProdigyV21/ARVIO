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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeUp", "Landroidx/compose/material/icons/Icons$Filled;", "getSwipeUp", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeUpKt {
    private static ImageVector _swipeUp;

    public static final ImageVector getSwipeUp(Icons.Filled filled) {
        ImageVector imageVector = _swipeUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SwipeUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.06f, 5.56f, 1.0f, 4.5f);
        pathBuilderJ.lineTo(4.5f, 1.0f);
        pathBuilderJ.lineTo(8.0f, 4.5f);
        pathBuilderJ.lineTo(6.94f, 5.56f);
        pathBuilderJ.lineTo(5.32f, 3.94f);
        pathBuilderJ.curveTo(5.11f, 4.76f, 5.0f, 5.62f, 5.0f, 6.5f);
        pathBuilderJ.curveToRelative(0.0f, 2.42f, 0.82f, 4.65f, 2.2f, 6.43f);
        pathBuilderJ.lineTo(6.13f, 14.0f);
        pathBuilderJ.curveTo(4.49f, 11.95f, 3.5f, 9.34f, 3.5f, 6.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.92f, 0.1f, -1.82f, 0.3f, -2.68f);
        c.z(pathBuilderJ, 2.06f, 5.56f, 13.85f, 11.62f);
        pathBuilderJ.lineToRelative(-2.68f, -5.37f);
        pathBuilderJ.curveToRelative(-0.37f, -0.74f, -1.27f, -1.04f, -2.01f, -0.67f);
        pathBuilderJ.curveTo(8.41f, 5.96f, 8.11f, 6.86f, 8.48f, 7.6f);
        pathBuilderJ.lineToRelative(4.81f, 9.6f);
        pathBuilderJ.lineTo(10.05f, 18.0f);
        pathBuilderJ.curveToRelative(-0.33f, 0.09f, -0.59f, 0.33f, -0.7f, 0.66f);
        pathBuilderJ.lineTo(9.0f, 19.78f);
        pathBuilderJ.lineToRelative(6.19f, 2.25f);
        pathBuilderJ.curveToRelative(0.5f, 0.17f, 1.28f, 0.02f, 1.75f, -0.22f);
        pathBuilderJ.lineToRelative(5.51f, -2.75f);
        pathBuilderJ.curveToRelative(0.89f, -0.45f, 1.32f, -1.48f, 1.0f, -2.42f);
        pathBuilderJ.lineToRelative(-1.43f, -4.27f);
        pathBuilderJ.curveToRelative(-0.27f, -0.82f, -1.04f, -1.37f, -1.9f, -1.37f);
        pathBuilderJ.horizontalLineToRelative(-4.56f);
        pathBuilderJ.curveToRelative(-0.31f, 0.0f, -0.62f, 0.07f, -0.89f, 0.21f);
        pathBuilderJ.lineTo(13.85f, 11.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeUp = imageVectorBuild;
        return imageVectorBuild;
    }
}

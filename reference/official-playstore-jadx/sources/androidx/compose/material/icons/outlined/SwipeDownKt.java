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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeDown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeDown", "Landroidx/compose/material/icons/Icons$Outlined;", "getSwipeDown", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeDownKt {
    private static ImageVector _swipeDown;

    public static final ImageVector getSwipeDown(Icons.Outlined outlined) {
        ImageVector imageVector = _swipeDown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SwipeDown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.22f, 10.0f, -4.15f, 0.01f);
        pathBuilderR.curveToRelative(-0.16f, -0.01f, -0.31f, 0.02f, -0.45f, 0.08f);
        pathBuilderR.lineToRelative(-0.59f, 0.26f);
        pathBuilderR.lineTo(13.2f, 6.25f);
        pathBuilderR.curveToRelative(-0.56f, -1.26f, -2.04f, -1.83f, -3.3f, -1.27f);
        pathBuilderR.reflectiveCurveToRelative(-1.83f, 2.04f, -1.27f, 3.3f);
        pathBuilderR.lineToRelative(3.3f, 7.45f);
        pathBuilderR.lineToRelative(-1.87f, 0.39f);
        pathBuilderR.curveToRelative(-0.19f, 0.05f, -0.99f, 0.27f, -1.36f, 1.21f);
        pathBuilderR.lineTo(8.0f, 19.19f);
        pathBuilderR.lineToRelative(6.78f, 2.67f);
        pathBuilderR.curveToRelative(0.49f, 0.19f, 1.05f, 0.18f, 1.53f, -0.04f);
        pathBuilderR.lineToRelative(5.99f, -2.65f);
        pathBuilderR.curveToRelative(0.89f, -0.4f, 1.37f, -1.38f, 1.13f, -2.32f);
        pathBuilderR.lineToRelative(-1.36f, -5.34f);
        pathBuilderR.curveTo(21.85f, 10.65f, 21.1f, 10.04f, 20.22f, 10.0f);
        a.p(pathBuilderR, 21.49f, 17.34f, 15.5f, 20.0f);
        pathBuilderR.lineToRelative(-4.92f, -1.96f);
        pathBuilderR.lineToRelative(4.18f, -0.88f);
        pathBuilderR.lineToRelative(-4.3f, -9.7f);
        pathBuilderR.curveToRelative(-0.11f, -0.25f, 0.0f, -0.55f, 0.25f, -0.66f);
        pathBuilderR.curveToRelative(0.25f, -0.11f, 0.55f, 0.0f, 0.66f, 0.25f);
        pathBuilderR.lineToRelative(2.5f, 5.65f);
        pathBuilderR.lineToRelative(1.61f, -0.71f);
        b.y(pathBuilderR, 20.13f, 12.0f, 21.49f, 17.34f);
        pathBuilderR.moveTo(3.8f, 12.18f);
        pathBuilderR.curveToRelative(-0.2f, -0.86f, -0.3f, -1.76f, -0.3f, -2.68f);
        pathBuilderR.curveToRelative(0.0f, -2.84f, 0.99f, -5.45f, 2.63f, -7.5f);
        pathBuilderR.lineTo(7.2f, 3.07f);
        pathBuilderR.curveTo(5.82f, 4.85f, 5.0f, 7.08f, 5.0f, 9.5f);
        pathBuilderR.curveToRelative(0.0f, 0.88f, 0.11f, 1.74f, 0.32f, 2.56f);
        pathBuilderR.lineToRelative(1.62f, -1.62f);
        pathBuilderR.lineTo(8.0f, 11.5f);
        pathBuilderR.lineTo(4.5f, 15.0f);
        pathBuilderR.lineTo(1.0f, 11.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.06f, -1.06f, 3.8f, 12.18f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeDown = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.twotone;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeUp", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSwipeUp", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeUpKt {
    private static ImageVector _swipeUp;

    public static final ImageVector getSwipeUp(Icons.TwoTone twoTone) {
        ImageVector imageVector = _swipeUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SwipeUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.49f, 17.34f, 15.5f, 20.0f);
        pathBuilderJ.lineToRelative(-4.92f, -1.96f);
        pathBuilderJ.lineToRelative(4.18f, -0.88f);
        pathBuilderJ.lineToRelative(-4.3f, -9.7f);
        pathBuilderJ.curveToRelative(-0.11f, -0.25f, 0.0f, -0.55f, 0.25f, -0.66f);
        pathBuilderJ.curveToRelative(0.25f, -0.11f, 0.55f, 0.0f, 0.66f, 0.25f);
        pathBuilderJ.lineToRelative(2.5f, 5.65f);
        pathBuilderJ.lineToRelative(1.61f, -0.71f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 20.13f, 12.0f, 21.49f, 17.34f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
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
        pathBuilderR.moveTo(2.06f, 5.56f);
        pathBuilderR.lineTo(1.0f, 4.5f);
        pathBuilderR.lineTo(4.5f, 1.0f);
        pathBuilderR.lineTo(8.0f, 4.5f);
        pathBuilderR.lineTo(6.94f, 5.56f);
        pathBuilderR.lineTo(5.32f, 3.94f);
        pathBuilderR.curveTo(5.11f, 4.76f, 5.0f, 5.62f, 5.0f, 6.5f);
        pathBuilderR.curveToRelative(0.0f, 2.42f, 0.82f, 4.65f, 2.2f, 6.43f);
        pathBuilderR.lineTo(6.13f, 14.0f);
        pathBuilderR.curveTo(4.49f, 11.95f, 3.5f, 9.34f, 3.5f, 6.5f);
        pathBuilderR.curveToRelative(0.0f, -0.92f, 0.1f, -1.82f, 0.3f, -2.68f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 2.06f, 5.56f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeUp = imageVectorBuild;
        return imageVectorBuild;
    }
}

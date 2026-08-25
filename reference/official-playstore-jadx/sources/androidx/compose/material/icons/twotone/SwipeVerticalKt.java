package androidx.compose.material.icons.twotone;

import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeVertical", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSwipeVertical", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeVerticalKt {
    private static ImageVector _swipeVertical;

    public static final ImageVector getSwipeVertical(Icons.TwoTone twoTone) {
        ImageVector imageVector = _swipeVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SwipeVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(1.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(3.5f);
        pathBuilder.horizontalLineTo(6.0f);
        pathBuilder.verticalLineToRelative(1.5f);
        b.y(pathBuilder, 7.0f, 4.5f, 4.09f);
        pathBuilder.curveToRelative(-1.86f, 2.11f, -3.0f, 4.88f, -3.0f, 7.91f);
        pathBuilder.reflectiveCurveToRelative(1.14f, 5.79f, 3.0f, 7.91f);
        pathBuilder.verticalLineTo(17.0f);
        pathBuilder.horizontalLineTo(6.0f);
        pathBuilder.verticalLineToRelative(3.5f);
        pathBuilder.verticalLineTo(22.0f);
        pathBuilder.horizontalLineTo(4.5f);
        pathBuilder.horizontalLineTo(1.0f);
        pathBuilder.verticalLineToRelative(-1.5f);
        pathBuilder.horizontalLineToRelative(2.02f);
        pathBuilder.curveTo(1.13f, 18.18f, 0.0f, 15.22f, 0.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(1.13f, -6.18f, 3.02f, -8.5f);
        c.l(pathBuilder, 1.0f, 2.0f, 20.22f, 10.0f);
        pathBuilder.lineToRelative(-4.15f, 0.01f);
        pathBuilder.curveToRelative(-0.16f, -0.01f, -0.31f, 0.02f, -0.45f, 0.08f);
        pathBuilder.lineToRelative(-0.59f, 0.26f);
        pathBuilder.lineTo(13.2f, 6.25f);
        pathBuilder.curveToRelative(-0.56f, -1.26f, -2.04f, -1.83f, -3.3f, -1.27f);
        pathBuilder.reflectiveCurveToRelative(-1.83f, 2.04f, -1.27f, 3.3f);
        pathBuilder.lineToRelative(3.3f, 7.45f);
        pathBuilder.lineToRelative(-1.87f, 0.39f);
        pathBuilder.curveToRelative(-0.19f, 0.05f, -0.99f, 0.27f, -1.36f, 1.21f);
        pathBuilder.lineTo(8.0f, 19.19f);
        pathBuilder.lineToRelative(6.78f, 2.67f);
        pathBuilder.curveToRelative(0.49f, 0.19f, 1.05f, 0.18f, 1.53f, -0.04f);
        pathBuilder.lineToRelative(5.99f, -2.65f);
        pathBuilder.curveToRelative(0.89f, -0.4f, 1.37f, -1.38f, 1.13f, -2.32f);
        pathBuilder.lineToRelative(-1.36f, -5.34f);
        pathBuilder.curveTo(21.85f, 10.65f, 21.1f, 10.04f, 20.22f, 10.0f);
        a.p(pathBuilder, 21.49f, 17.34f, 15.5f, 20.0f);
        pathBuilder.lineToRelative(-4.92f, -1.96f);
        pathBuilder.lineToRelative(4.18f, -0.88f);
        pathBuilder.lineToRelative(-4.3f, -9.7f);
        pathBuilder.curveToRelative(-0.11f, -0.25f, 0.0f, -0.55f, 0.25f, -0.66f);
        pathBuilder.curveToRelative(0.25f, -0.11f, 0.55f, 0.0f, 0.66f, 0.25f);
        pathBuilder.lineToRelative(2.5f, 5.65f);
        pathBuilder.lineToRelative(1.61f, -0.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilder, 20.13f, 12.0f, 21.49f, 17.34f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}

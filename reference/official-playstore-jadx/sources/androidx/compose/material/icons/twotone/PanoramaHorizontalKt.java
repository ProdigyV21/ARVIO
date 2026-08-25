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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaHorizontal", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaHorizontal", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPanoramaHorizontal", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaHorizontalKt {
    private static ImageVector _panoramaHorizontal;

    public static final ImageVector getPanoramaHorizontal(Icons.TwoTone twoTone) {
        ImageVector imageVector = _panoramaHorizontal;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PanoramaHorizontal", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(4.0f, 6.54f, 10.91f);
        pathBuilderI.curveToRelative(2.6f, -0.77f, 5.28f, -1.16f, 8.0f, -1.16f);
        pathBuilderI.reflectiveCurveToRelative(5.4f, 0.39f, 8.0f, 1.16f);
        pathBuilderI.verticalLineTo(6.54f);
        pathBuilderI.curveToRelative(-2.6f, 0.78f, -5.28f, 1.17f, -8.0f, 1.16f);
        pathBuilderI.curveToRelative(-2.72f, 0.0f, -5.4f, -0.39f, -8.0f, -1.16f);
        pathBuilderI.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.43f, 4.0f);
        pathBuilderA.curveToRelative(-0.1f, 0.0f, -0.2f, 0.02f, -0.31f, 0.06f);
        pathBuilderA.curveTo(18.18f, 5.16f, 15.09f, 5.7f, 12.0f, 5.7f);
        pathBuilderA.reflectiveCurveToRelative(-6.18f, -0.55f, -9.12f, -1.64f);
        pathBuilderA.curveTo(2.77f, 4.02f, 2.66f, 4.0f, 2.57f, 4.0f);
        pathBuilderA.curveToRelative(-0.34f, 0.0f, -0.57f, 0.23f, -0.57f, 0.63f);
        pathBuilderA.verticalLineToRelative(14.75f);
        pathBuilderA.curveToRelative(0.0f, 0.39f, 0.23f, 0.62f, 0.57f, 0.62f);
        pathBuilderA.curveToRelative(0.1f, 0.0f, 0.2f, -0.02f, 0.31f, -0.06f);
        pathBuilderA.curveToRelative(2.94f, -1.1f, 6.03f, -1.64f, 9.12f, -1.64f);
        pathBuilderA.reflectiveCurveToRelative(6.18f, 0.55f, 9.12f, 1.64f);
        pathBuilderA.curveToRelative(0.11f, 0.04f, 0.21f, 0.06f, 0.31f, 0.06f);
        pathBuilderA.curveToRelative(0.33f, 0.0f, 0.57f, -0.23f, 0.57f, -0.63f);
        pathBuilderA.verticalLineTo(4.63f);
        pathBuilderA.curveToRelative(0.0f, -0.4f, -0.24f, -0.63f, -0.57f, -0.63f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.0f, 17.45f);
        pathBuilderA.curveToRelative(-2.6f, -0.77f, -5.28f, -1.16f, -8.0f, -1.16f);
        pathBuilderA.reflectiveCurveToRelative(-5.4f, 0.39f, -8.0f, 1.16f);
        pathBuilderA.verticalLineTo(6.54f);
        pathBuilderA.curveToRelative(2.6f, 0.77f, 5.28f, 1.16f, 8.0f, 1.16f);
        pathBuilderA.curveToRelative(2.72f, 0.01f, 5.4f, -0.38f, 8.0f, -1.16f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderA, 10.91f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaHorizontal = imageVectorBuild;
        return imageVectorBuild;
    }
}

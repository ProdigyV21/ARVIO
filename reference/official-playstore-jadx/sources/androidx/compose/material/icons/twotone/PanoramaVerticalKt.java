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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaVertical", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPanoramaVertical", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaVerticalKt {
    private static ImageVector _panoramaVertical;

    public static final ImageVector getPanoramaVertical(Icons.TwoTone twoTone) {
        ImageVector imageVector = _panoramaVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PanoramaVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.54f, 4.0f);
        pathBuilderA.curveToRelative(0.77f, 2.6f, 1.16f, 5.28f, 1.16f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 2.72f, -0.39f, 5.4f, -1.16f, 8.0f);
        pathBuilderA.horizontalLineToRelative(10.91f);
        pathBuilderA.curveToRelative(-0.77f, -2.6f, -1.16f, -5.28f, -1.16f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -2.72f, 0.39f, -5.4f, 1.16f, -8.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 6.54f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.94f, 21.12f);
        pathBuilderA2.curveToRelative(-1.1f, -2.94f, -1.64f, -6.03f, -1.64f, -9.12f);
        pathBuilderA2.curveToRelative(0.0f, -3.09f, 0.55f, -6.18f, 1.64f, -9.12f);
        pathBuilderA2.curveToRelative(0.04f, -0.11f, 0.06f, -0.22f, 0.06f, -0.31f);
        pathBuilderA2.curveToRelative(0.0f, -0.34f, -0.23f, -0.57f, -0.63f, -0.57f);
        pathBuilderA2.horizontalLineTo(4.63f);
        pathBuilderA2.curveToRelative(-0.4f, 0.0f, -0.63f, 0.23f, -0.63f, 0.57f);
        pathBuilderA2.curveToRelative(0.0f, 0.1f, 0.02f, 0.2f, 0.06f, 0.31f);
        pathBuilderA2.curveTo(5.16f, 5.82f, 5.71f, 8.91f, 5.71f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.09f, -0.55f, 6.18f, -1.64f, 9.12f);
        pathBuilderA2.curveToRelative(-0.05f, 0.11f, -0.07f, 0.22f, -0.07f, 0.31f);
        pathBuilderA2.curveToRelative(0.0f, 0.33f, 0.23f, 0.57f, 0.63f, 0.57f);
        pathBuilderA2.horizontalLineToRelative(14.75f);
        pathBuilderA2.curveToRelative(0.39f, 0.0f, 0.63f, -0.24f, 0.63f, -0.57f);
        pathBuilderA2.curveToRelative(-0.01f, -0.1f, -0.03f, -0.2f, -0.07f, -0.31f);
        w.a.r(pathBuilderA2, 17.45f, 20.0f, 6.54f);
        pathBuilderA2.curveToRelative(0.77f, -2.6f, 1.16f, -5.28f, 1.16f, -8.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.72f, -0.39f, -5.4f, -1.16f, -8.0f);
        pathBuilderA2.horizontalLineToRelative(10.91f);
        pathBuilderA2.curveToRelative(-0.77f, 2.6f, -1.16f, 5.28f, -1.16f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.72f, 0.39f, 5.4f, 1.16f, 8.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}

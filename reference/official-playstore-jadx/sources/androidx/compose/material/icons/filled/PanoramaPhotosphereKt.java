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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaPhotosphere", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaPhotosphere", "Landroidx/compose/material/icons/Icons$Filled;", "getPanoramaPhotosphere", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaPhotosphereKt {
    private static ImageVector _panoramaPhotosphere;

    public static final ImageVector getPanoramaPhotosphere(Icons.Filled filled) {
        ImageVector imageVector = _panoramaPhotosphere;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PanoramaPhotosphere", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(21.4f, 11.32f, 2.93f);
        pathBuilderI.curveToRelative(-0.1f, 0.05f, -2.17f, 0.85f, -3.33f, 1.17f);
        pathBuilderI.curveToRelative(-0.94f, 0.26f, -3.84f, 0.73f, -6.07f, 0.73f);
        pathBuilderI.curveToRelative(-3.7f, 0.0f, -7.0f, -0.7f, -9.16f, -1.8f);
        pathBuilderI.curveToRelative(-0.08f, -0.04f, -0.16f, -0.06f, -0.24f, -0.1f);
        pathBuilderI.lineTo(2.6f, 9.76f);
        pathBuilderI.curveToRelative(6.02f, -2.84f, 12.6f, -2.92f, 18.8f, 0.0f);
        b.f(pathBuilderI, 1.56f, 12.01f, 20.2f);
        pathBuilderI.curveToRelative(-2.5f, 0.0f, -4.87f, -1.15f, -6.41f, -3.12f);
        pathBuilderI.curveToRelative(4.19f, 1.22f, 8.57f, 1.23f, 12.82f, -0.01f);
        pathBuilderI.curveToRelative(-1.54f, 1.97f, -3.9f, 3.13f, -6.41f, 3.13f);
        pathBuilderI.close();
        pathBuilderI.moveTo(12.0f, 3.8f);
        pathBuilderI.curveToRelative(2.6f, 0.0f, 4.91f, 1.23f, 6.41f, 3.12f);
        pathBuilderI.curveToRelative(-4.1f, -1.19f, -8.48f, -1.26f, -12.83f, 0.01f);
        pathBuilderI.curveTo(7.08f, 5.03f, 9.4f, 3.8f, 12.0f, 3.8f);
        pathBuilderI.close();
        pathBuilderI.moveTo(22.49f, 8.51f);
        pathBuilderI.curveToRelative(-0.47f, -0.23f, -0.93f, -0.44f, -1.4f, -0.64f);
        pathBuilderI.curveTo(19.52f, 4.41f, 16.05f, 2.0f, 12.0f, 2.0f);
        pathBuilderI.reflectiveCurveTo(4.47f, 4.41f, 2.9f, 7.88f);
        pathBuilderI.curveToRelative(-0.47f, 0.2f, -0.93f, 0.41f, -1.4f, 0.63f);
        pathBuilderI.curveToRelative(-0.31f, 0.15f, -0.5f, 0.48f, -0.5f, 0.83f);
        pathBuilderI.verticalLineToRelative(5.32f);
        pathBuilderI.curveToRelative(0.0f, 0.35f, 0.19f, 0.68f, 0.51f, 0.83f);
        pathBuilderI.curveToRelative(0.47f, 0.23f, 0.93f, 0.44f, 1.39f, 0.64f);
        pathBuilderI.curveToRelative(3.55f, 7.83f, 14.65f, 7.82f, 18.2f, 0.0f);
        pathBuilderI.curveToRelative(0.47f, -0.2f, 0.93f, -0.41f, 1.39f, -0.63f);
        pathBuilderI.curveToRelative(0.31f, -0.17f, 0.51f, -0.49f, 0.51f, -0.84f);
        pathBuilderI.lineTo(23.0f, 9.34f);
        pathBuilderI.curveToRelative(0.0f, -0.35f, -0.19f, -0.68f, -0.51f, -0.83f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaPhotosphere = imageVectorBuild;
        return imageVectorBuild;
    }
}

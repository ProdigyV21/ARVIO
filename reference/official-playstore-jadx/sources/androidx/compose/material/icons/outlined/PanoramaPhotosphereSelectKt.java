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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaPhotosphereSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaPhotosphereSelect", "Landroidx/compose/material/icons/Icons$Outlined;", "getPanoramaPhotosphereSelect", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaPhotosphereSelectKt {
    private static ImageVector _panoramaPhotosphereSelect;

    public static final ImageVector getPanoramaPhotosphereSelect(Icons.Outlined outlined) {
        ImageVector imageVector = _panoramaPhotosphereSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PanoramaPhotosphereSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.95f, 8.15f);
        pathBuilderA.curveToRelative(-0.29f, -0.16f, -0.61f, -0.31f, -0.93f, -0.46f);
        pathBuilderA.curveTo(19.4f, 4.33f, 15.98f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveTo(8.02f, 2.0f, 4.6f, 4.33f, 2.99f, 7.68f);
        pathBuilderA.curveToRelative(-0.33f, 0.15f, -0.64f, 0.3f, -0.93f, 0.46f);
        pathBuilderA.curveTo(1.41f, 8.5f, 1.0f, 9.17f, 1.0f, 9.91f);
        pathBuilderA.verticalLineToRelative(4.18f);
        pathBuilderA.curveToRelative(0.0f, 0.74f, 0.41f, 1.41f, 1.05f, 1.77f);
        pathBuilderA.curveToRelative(0.29f, 0.16f, 0.61f, 0.31f, 0.93f, 0.46f);
        pathBuilderA.curveTo(4.6f, 19.67f, 8.02f, 22.0f, 12.0f, 22.0f);
        pathBuilderA.curveToRelative(3.98f, 0.0f, 7.4f, -2.33f, 9.01f, -5.68f);
        pathBuilderA.curveToRelative(0.33f, -0.15f, 0.64f, -0.3f, 0.93f, -0.46f);
        pathBuilderA.curveTo(22.59f, 15.5f, 23.0f, 14.83f, 23.0f, 14.09f);
        pathBuilderA.verticalLineTo(9.91f);
        pathBuilderA.curveTo(23.0f, 9.17f, 22.59f, 8.5f, 21.95f, 8.15f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 4.0f);
        pathBuilderA.curveToRelative(2.37f, 0.0f, 4.49f, 1.04f, 5.95f, 2.68f);
        pathBuilderA.curveTo(16.17f, 6.25f, 14.15f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveTo(9.85f, 6.0f, 7.83f, 6.25f, 6.05f, 6.68f);
        pathBuilderA.curveTo(7.51f, 5.04f, 9.63f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-2.37f, 0.0f, -4.49f, -1.04f, -5.95f, -2.68f);
        pathBuilderA.curveTo(7.83f, 17.75f, 9.85f, 18.0f, 12.0f, 18.0f);
        pathBuilderA.reflectiveCurveToRelative(4.17f, -0.25f, 5.95f, -0.68f);
        pathBuilderA.curveTo(16.49f, 18.96f, 14.37f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaPhotosphereSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaVertical", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaVertical", "Landroidx/compose/material/icons/Icons$Sharp;", "getPanoramaVertical", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaVerticalKt {
    private static ImageVector _panoramaVertical;

    public static final ImageVector getPanoramaVertical(Icons.Sharp sharp) {
        ImageVector imageVector = _panoramaVertical;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PanoramaVertical", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.46f, 4.0f);
        pathBuilderA.curveToRelative(-0.77f, 2.6f, -1.16f, 5.28f, -1.16f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 2.72f, 0.39f, 5.41f, 1.16f, 8.0f);
        pathBuilderA.horizontalLineTo(6.55f);
        pathBuilderA.curveToRelative(0.77f, -2.6f, 1.16f, -5.28f, 1.16f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -2.72f, -0.39f, -5.41f, -1.16f, -8.0f);
        pathBuilderA.horizontalLineToRelative(10.91f);
        pathBuilderA.moveToRelative(2.78f, -2.0f);
        pathBuilderA.horizontalLineTo(3.77f);
        pathBuilderA.reflectiveCurveToRelative(0.26f, 0.77f, 0.3f, 0.88f);
        pathBuilderA.curveTo(5.16f, 5.82f, 5.71f, 8.91f, 5.71f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.55f, 6.18f, -1.64f, 9.12f);
        pathBuilderA.curveToRelative(-0.04f, 0.11f, -0.3f, 0.88f, -0.3f, 0.88f);
        pathBuilderA.horizontalLineToRelative(16.47f);
        pathBuilderA.reflectiveCurveToRelative(-0.26f, -0.77f, -0.3f, -0.88f);
        pathBuilderA.curveToRelative(-1.09f, -2.94f, -1.64f, -6.03f, -1.64f, -9.12f);
        pathBuilderA.reflectiveCurveToRelative(0.55f, -6.18f, 1.64f, -9.12f);
        pathBuilderA.curveToRelative(0.04f, -0.11f, 0.3f, -0.88f, 0.3f, -0.88f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaVertical = imageVectorBuild;
        return imageVectorBuild;
    }
}

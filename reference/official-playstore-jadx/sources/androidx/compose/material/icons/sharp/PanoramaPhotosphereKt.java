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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaPhotosphere", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaPhotosphere", "Landroidx/compose/material/icons/Icons$Sharp;", "getPanoramaPhotosphere", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaPhotosphereKt {
    private static ImageVector _panoramaPhotosphere;

    public static final ImageVector getPanoramaPhotosphere(Icons.Sharp sharp) {
        ImageVector imageVector = _panoramaPhotosphere;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PanoramaPhotosphere", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.0f, 8.84f);
        pathBuilderA.curveToRelative(-0.54f, -0.43f, -1.23f, -0.81f, -1.99f, -1.15f);
        pathBuilderA.curveTo(19.4f, 4.33f, 15.98f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveTo(8.02f, 2.0f, 4.6f, 4.33f, 2.99f, 7.68f);
        pathBuilderA.curveTo(2.23f, 8.03f, 1.54f, 8.4f, 1.0f, 8.84f);
        pathBuilderA.verticalLineToRelative(6.33f);
        pathBuilderA.curveToRelative(0.54f, 0.43f, 1.23f, 0.81f, 1.99f, 1.15f);
        pathBuilderA.curveTo(4.6f, 19.67f, 8.02f, 22.0f, 12.0f, 22.0f);
        pathBuilderA.curveToRelative(3.98f, 0.0f, 7.4f, -2.33f, 9.01f, -5.68f);
        pathBuilderA.curveToRelative(0.76f, -0.34f, 1.45f, -0.72f, 1.99f, -1.15f);
        f.r(pathBuilderA, 8.84f, 12.0f, 4.0f);
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
        _panoramaPhotosphere = imageVectorBuild;
        return imageVectorBuild;
    }
}

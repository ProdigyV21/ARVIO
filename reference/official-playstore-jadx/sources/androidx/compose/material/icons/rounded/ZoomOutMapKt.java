package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_zoomOutMap", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ZoomOutMap", "Landroidx/compose/material/icons/Icons$Rounded;", "getZoomOutMap", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ZoomOutMapKt {
    private static ImageVector _zoomOutMap;

    public static final ImageVector getZoomOutMap(Icons.Rounded rounded) {
        ImageVector imageVector = _zoomOutMap;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ZoomOutMap", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.85f, 3.85f, 17.3f, 5.3f);
        pathBuilderJ.lineToRelative(-2.18f, 2.16f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.42f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.42f, 0.0f);
        pathBuilderJ.lineTo(18.7f, 6.7f);
        pathBuilderJ.lineToRelative(1.45f, 1.45f);
        pathBuilderJ.curveTo(20.46f, 8.46f, 21.0f, 8.24f, 21.0f, 7.79f);
        pathBuilderJ.verticalLineTo(3.5f);
        pathBuilderJ.curveTo(21.0f, 3.22f, 20.78f, 3.0f, 20.5f, 3.0f);
        pathBuilderJ.horizontalLineToRelative(-4.29f);
        pathBuilderJ.curveTo(15.76f, 3.0f, 15.54f, 3.54f, 15.85f, 3.85f);
        a.p(pathBuilderJ, 3.85f, 8.15f, 5.3f, 6.7f);
        pathBuilderJ.lineToRelative(2.16f, 2.18f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.42f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.42f);
        pathBuilderJ.lineTo(6.7f, 5.3f);
        pathBuilderJ.lineToRelative(1.45f, -1.45f);
        pathBuilderJ.curveTo(8.46f, 3.54f, 8.24f, 3.0f, 7.79f, 3.0f);
        pathBuilderJ.horizontalLineTo(3.5f);
        pathBuilderJ.curveTo(3.22f, 3.0f, 3.0f, 3.22f, 3.0f, 3.5f);
        pathBuilderJ.verticalLineToRelative(4.29f);
        pathBuilderJ.curveTo(3.0f, 8.24f, 3.54f, 8.46f, 3.85f, 8.15f);
        a.p(pathBuilderJ, 8.15f, 20.15f, 6.7f, 18.7f);
        pathBuilderJ.lineToRelative(2.18f, -2.16f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.42f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.42f, 0.0f);
        pathBuilderJ.lineTo(5.3f, 17.3f);
        pathBuilderJ.lineToRelative(-1.45f, -1.45f);
        pathBuilderJ.curveTo(3.54f, 15.54f, 3.0f, 15.76f, 3.0f, 16.21f);
        pathBuilderJ.verticalLineToRelative(4.29f);
        pathBuilderJ.curveTo(3.0f, 20.78f, 3.22f, 21.0f, 3.5f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(4.29f);
        pathBuilderJ.curveTo(8.24f, 21.0f, 8.46f, 20.46f, 8.15f, 20.15f);
        a.p(pathBuilderJ, 20.15f, 15.85f, 18.7f, 17.3f);
        pathBuilderJ.lineToRelative(-2.16f, -2.18f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.42f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.42f);
        pathBuilderJ.lineToRelative(2.18f, 2.16f);
        pathBuilderJ.lineToRelative(-1.45f, 1.45f);
        pathBuilderJ.curveTo(15.54f, 20.46f, 15.76f, 21.0f, 16.21f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(4.29f);
        pathBuilderJ.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderJ.verticalLineToRelative(-4.29f);
        pathBuilderJ.curveTo(21.0f, 15.76f, 20.46f, 15.54f, 20.15f, 15.85f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _zoomOutMap = imageVectorBuild;
        return imageVectorBuild;
    }
}

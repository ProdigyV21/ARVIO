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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_zoomOut", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ZoomOut", "Landroidx/compose/material/icons/Icons$Outlined;", "getZoomOut", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ZoomOutKt {
    private static ImageVector _zoomOut;

    public static final ImageVector getZoomOut(Icons.Outlined outlined) {
        ImageVector imageVector = _zoomOut;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ZoomOut", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(15.5f, 14.0f, -0.79f, -0.28f, -0.27f);
        pathBuilderG.curveTo(15.41f, 12.59f, 16.0f, 11.11f, 16.0f, 9.5f);
        pathBuilderG.curveTo(16.0f, 5.91f, 13.09f, 3.0f, 9.5f, 3.0f);
        pathBuilderG.reflectiveCurveTo(3.0f, 5.91f, 3.0f, 9.5f);
        pathBuilderG.reflectiveCurveTo(5.91f, 16.0f, 9.5f, 16.0f);
        pathBuilderG.curveToRelative(1.61f, 0.0f, 3.09f, -0.59f, 4.23f, -1.57f);
        pathBuilderG.lineToRelative(0.27f, 0.28f);
        pathBuilderG.verticalLineToRelative(0.79f);
        pathBuilderG.lineToRelative(5.0f, 4.99f);
        d.l(pathBuilderG, 20.49f, 19.0f, -4.99f, -5.0f);
        pathBuilderG.moveTo(9.5f, 14.0f);
        pathBuilderG.curveTo(7.01f, 14.0f, 5.0f, 11.99f, 5.0f, 9.5f);
        pathBuilderG.reflectiveCurveTo(7.01f, 5.0f, 9.5f, 5.0f);
        pathBuilderG.reflectiveCurveTo(14.0f, 7.01f, 14.0f, 9.5f);
        pathBuilderG.reflectiveCurveTo(11.99f, 14.0f, 9.5f, 14.0f);
        b.w(pathBuilderG, 7.0f, 9.0f, 5.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderG, 7.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _zoomOut = imageVectorBuild;
        return imageVectorBuild;
    }
}

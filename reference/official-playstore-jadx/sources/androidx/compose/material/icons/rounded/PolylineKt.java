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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_polyline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Polyline", "Landroidx/compose/material/icons/Icons$Rounded;", "getPolyline", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PolylineKt {
    private static ImageVector _polyline;

    public static final ImageVector getPolyline(Icons.Rounded rounded) {
        ImageVector imageVector = _polyline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Polyline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = d.n(10.04f, 6.85f, 7.3f, 10.0f, 4.5f);
        pathBuilderN.curveTo(3.67f, 10.0f, 3.0f, 10.67f, 3.0f, 11.5f);
        pathBuilderN.verticalLineToRelative(3.0f);
        pathBuilderN.curveTo(3.0f, 15.33f, 3.67f, 16.0f, 4.5f, 16.0f);
        pathBuilderN.horizontalLineToRelative(3.0f);
        pathBuilderN.curveToRelative(0.14f, 0.0f, 0.27f, -0.02f, 0.39f, -0.05f);
        pathBuilderN.lineTo(15.0f, 19.5f);
        pathBuilderN.verticalLineToRelative(1.0f);
        pathBuilderN.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderN.horizontalLineToRelative(3.0f);
        pathBuilderN.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderN.verticalLineToRelative(-3.0f);
        pathBuilderN.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderN.horizontalLineToRelative(-3.0f);
        pathBuilderN.curveToRelative(-0.75f, 0.0f, -1.37f, 0.55f, -1.48f, 1.27f);
        pathBuilderN.lineTo(9.0f, 14.26f);
        pathBuilderN.verticalLineTo(11.5f);
        pathBuilderN.curveToRelative(0.0f, -0.12f, -0.01f, -0.24f, -0.04f, -0.36f);
        pathBuilderN.lineTo(11.7f, 8.0f);
        pathBuilderN.horizontalLineToRelative(2.8f);
        pathBuilderN.curveTo(15.33f, 8.0f, 16.0f, 7.33f, 16.0f, 6.5f);
        pathBuilderN.verticalLineToRelative(-3.0f);
        pathBuilderN.curveTo(16.0f, 2.67f, 15.33f, 2.0f, 14.5f, 2.0f);
        pathBuilderN.horizontalLineToRelative(-3.0f);
        pathBuilderN.curveTo(10.67f, 2.0f, 10.0f, 2.67f, 10.0f, 3.5f);
        pathBuilderN.verticalLineToRelative(3.0f);
        pathBuilderN.curveTo(10.0f, 6.62f, 10.01f, 6.74f, 10.04f, 6.85f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _polyline = imageVectorBuild;
        return imageVectorBuild;
    }
}

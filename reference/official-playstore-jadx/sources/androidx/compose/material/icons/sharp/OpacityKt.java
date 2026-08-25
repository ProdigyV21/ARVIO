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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_opacity", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Opacity", "Landroidx/compose/material/icons/Icons$Sharp;", "getOpacity", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OpacityKt {
    private static ImageVector _opacity;

    public static final ImageVector getOpacity(Icons.Sharp sharp) {
        ImageVector imageVector = _opacity;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Opacity", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.66f, 8.0f, 12.0f, 2.35f);
        pathBuilderJ.lineTo(6.34f, 8.0f);
        pathBuilderJ.curveTo(4.78f, 9.56f, 4.0f, 11.64f, 4.0f, 13.64f);
        pathBuilderJ.reflectiveCurveToRelative(0.78f, 4.11f, 2.34f, 5.67f);
        pathBuilderJ.reflectiveCurveToRelative(3.61f, 2.35f, 5.66f, 2.35f);
        pathBuilderJ.reflectiveCurveToRelative(4.1f, -0.79f, 5.66f, -2.35f);
        pathBuilderJ.reflectiveCurveTo(20.0f, 15.64f, 20.0f, 13.64f);
        pathBuilderJ.reflectiveCurveTo(19.22f, 9.56f, 17.66f, 8.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(6.0f, 14.0f);
        pathBuilderJ.curveToRelative(0.01f, -2.0f, 0.62f, -3.27f, 1.76f, -4.4f);
        pathBuilderJ.lineTo(12.0f, 5.27f);
        pathBuilderJ.lineToRelative(4.24f, 4.38f);
        pathBuilderJ.curveTo(17.38f, 10.77f, 17.99f, 12.0f, 18.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderJ, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _opacity = imageVectorBuild;
        return imageVectorBuild;
    }
}

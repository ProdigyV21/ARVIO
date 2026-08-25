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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_micExternalOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MicExternalOff", "Landroidx/compose/material/icons/Icons$Filled;", "getMicExternalOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicExternalOffKt {
    private static ImageVector _micExternalOff;

    public static final ImageVector getMicExternalOff(Icons.Filled filled) {
        ImageVector imageVector = _micExternalOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MicExternalOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineTo(5.17f, 8.0f);
        pathBuilderJ.lineTo(4.0f, 8.0f);
        pathBuilderJ.lineToRelative(1.0f, 10.0f);
        pathBuilderJ.horizontalLineToRelative(1.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderJ.verticalLineToRelative(-1.17f);
        b.D(pathBuilderJ, 5.78f, 5.78f, 1.41f, -1.42f);
        pathBuilderJ.moveTo(12.0f, 18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(1.0f);
        pathBuilderJ.lineToRelative(0.56f, -5.61f);
        b.y(pathBuilderJ, 12.0f, 14.83f, 12.0f, 18.0f);
        pathBuilderJ.moveTo(14.0f, 6.0f);
        pathBuilderJ.verticalLineToRelative(5.17f);
        pathBuilderJ.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ.lineTo(12.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderJ.verticalLineToRelative(11.17f);
        pathBuilderJ.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ.lineTo(18.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(10.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.62f, -0.2f, 1.18f, -0.52f, 1.66f);
        pathBuilderJ.lineTo(5.33f, 2.51f);
        pathBuilderJ.curveTo(5.81f, 2.19f, 6.38f, 2.0f, 7.0f, 2.0f);
        pathBuilderJ.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _micExternalOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

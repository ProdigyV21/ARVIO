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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbShade", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbShade", "Landroidx/compose/material/icons/Icons$Rounded;", "getWbShade", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbShadeKt {
    private static ImageVector _wbShade;

    public static final ImageVector getWbShade(Icons.Rounded rounded) {
        ImageVector imageVector = _wbShade;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WbShade", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.0f, 14.13f, 14.0f, 14.13f);
        pathBuilderJ.curveToRelative(0.0f, 0.23f, 0.09f, 0.46f, 0.26f, 0.63f);
        pathBuilderJ.lineToRelative(4.98f, 4.98f);
        pathBuilderJ.curveToRelative(0.17f, 0.17f, 0.39f, 0.26f, 0.62f, 0.26f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.79f, 0.0f, 1.18f, -0.95f, 0.62f, -1.51f);
        pathBuilderJ.lineToRelative(-4.98f, -4.98f);
        pathBuilderJ.curveTo(14.95f, 12.95f, 14.0f, 13.35f, 14.0f, 14.13f);
        a.g(pathBuilderJ, 15.0f, 20.0f, 2.0f);
        pathBuilderJ.lineToRelative(-3.0f, -3.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveTo(14.0f, 19.55f, 14.45f, 20.0f, 15.0f, 20.0f);
        a.p(pathBuilderJ, 7.65f, 4.35f, 2.85f, 9.15f);
        pathBuilderJ.curveTo(2.54f, 9.46f, 2.76f, 10.0f, 3.21f, 10.0f);
        pathBuilderJ.horizontalLineTo(4.0f);
        pathBuilderJ.verticalLineToRelative(9.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(6.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-9.0f);
        pathBuilderJ.horizontalLineToRelative(0.79f);
        pathBuilderJ.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderJ.lineTo(8.35f, 4.35f);
        pathBuilderJ.curveTo(8.16f, 4.16f, 7.84f, 4.16f, 7.65f, 4.35f);
        c.C(pathBuilderJ, 9.0f, 14.0f, 7.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderJ, 2.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbShade = imageVectorBuild;
        return imageVectorBuild;
    }
}

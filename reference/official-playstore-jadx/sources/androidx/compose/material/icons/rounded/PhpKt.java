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
import v.b;
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_php", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Php", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhpKt {
    private static ImageVector _php;

    public static final ImageVector getPhp(Icons.Rounded rounded) {
        ImageVector imageVector = _php;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Php", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(6.5f, 10.5f, -2.0f, 1.0f, 2.0f);
        a.D(pathBuilderK, 10.5f, 20.0f, 10.5f, -2.0f);
        a.t(pathBuilderK, 1.0f, 2.0f, 10.5f);
        a.C(pathBuilderK, 13.0f, 12.5f, -2.0f, 1.75f);
        pathBuilderK.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderK.reflectiveCurveTo(9.5f, 14.66f, 9.5f, 14.25f);
        pathBuilderK.verticalLineToRelative(-4.5f);
        pathBuilderK.curveTo(9.5f, 9.34f, 9.84f, 9.0f, 10.25f, 9.0f);
        pathBuilderK.reflectiveCurveTo(11.0f, 9.34f, 11.0f, 9.75f);
        c.i(pathBuilderK, 11.0f, 2.0f, 9.75f);
        pathBuilderK.curveTo(13.0f, 9.34f, 13.34f, 9.0f, 13.75f, 9.0f);
        pathBuilderK.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderK.verticalLineToRelative(4.5f);
        pathBuilderK.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderK.reflectiveCurveTo(13.0f, 14.66f, 13.0f, 14.25f);
        f.r(pathBuilderK, 12.5f, 18.0f, 14.25f);
        pathBuilderK.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderK.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderK.verticalLineTo(10.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderK.horizontalLineTo(20.0f);
        pathBuilderK.curveToRelative(0.83f, 0.0f, 1.5f, 0.68f, 1.5f, 1.5f);
        pathBuilderK.verticalLineToRelative(1.0f);
        pathBuilderK.curveToRelative(0.0f, 0.82f, -0.67f, 1.5f, -1.5f, 1.5f);
        c.D(pathBuilderK, -2.0f, 14.25f, 3.0f, 10.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderK.horizontalLineToRelative(2.5f);
        pathBuilderK.curveTo(7.33f, 9.0f, 8.0f, 9.68f, 8.0f, 10.5f);
        pathBuilderK.verticalLineToRelative(1.0f);
        pathBuilderK.curveTo(8.0f, 12.32f, 7.33f, 13.0f, 6.5f, 13.0f);
        pathBuilderK.horizontalLineToRelative(-2.0f);
        pathBuilderK.verticalLineToRelative(1.25f);
        pathBuilderK.curveTo(4.5f, 14.66f, 4.16f, 15.0f, 3.75f, 15.0f);
        pathBuilderK.reflectiveCurveTo(3.0f, 14.66f, 3.0f, 14.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderK, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _php = imageVectorBuild;
        return imageVectorBuild;
    }
}

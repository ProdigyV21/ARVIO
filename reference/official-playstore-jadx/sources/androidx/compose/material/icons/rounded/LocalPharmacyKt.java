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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPharmacy", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPharmacy", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalPharmacy", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPharmacyKt {
    private static ImageVector _localPharmacy;

    public static final ImageVector getLocalPharmacy(Icons.Rounded rounded) {
        ImageVector imageVector = _localPharmacy;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalPharmacy", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(18.89f, 5.0f, -0.53f, 0.71f, -1.97f);
        pathBuilderG.curveToRelative(0.24f, -0.65f, -0.1f, -1.37f, -0.75f, -1.6f);
        pathBuilderG.curveToRelative(-0.65f, -0.24f, -1.37f, 0.1f, -1.61f, 0.75f);
        pathBuilderG.lineTo(15.69f, 5.0f);
        pathBuilderG.horizontalLineTo(5.1f);
        pathBuilderG.curveTo(3.73f, 5.0f, 2.77f, 6.34f, 3.2f, 7.63f);
        pathBuilderG.lineTo(5.0f, 13.0f);
        pathBuilderG.lineToRelative(-1.79f, 5.37f);
        pathBuilderG.curveTo(2.77f, 19.66f, 3.74f, 21.0f, 5.1f, 21.0f);
        pathBuilderG.horizontalLineToRelative(13.78f);
        pathBuilderG.curveToRelative(1.36f, 0.0f, 2.33f, -1.34f, 1.9f, -2.63f);
        pathBuilderG.lineTo(19.0f, 13.0f);
        pathBuilderG.lineToRelative(1.78f, -5.37f);
        pathBuilderG.curveTo(21.21f, 6.34f, 20.25f, 5.0f, 18.89f, 5.0f);
        b.w(pathBuilderG, 15.0f, 14.0f, -2.0f, 2.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderG.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderG.verticalLineToRelative(-2.0f);
        pathBuilderG.horizontalLineTo(9.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderG.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderG.horizontalLineToRelative(2.0f);
        pathBuilderG.verticalLineToRelative(-2.0f);
        pathBuilderG.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderG.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderG.verticalLineToRelative(2.0f);
        pathBuilderG.horizontalLineToRelative(2.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderG, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localPharmacy = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_powerSettingsNew", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PowerSettingsNew", "Landroidx/compose/material/icons/Icons$Outlined;", "getPowerSettingsNew", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PowerSettingsNewKt {
    private static ImageVector _powerSettingsNew;

    public static final ImageVector getPowerSettingsNew(Icons.Outlined outlined) {
        ImageVector imageVector = _powerSettingsNew;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PowerSettingsNew", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(13.0f, 3.0f, -2.0f, 10.0f, 2.0f);
        c.z(pathBuilderK, 13.0f, 3.0f, 17.83f, 5.17f);
        pathBuilderK.lineToRelative(-1.42f, 1.42f);
        pathBuilderK.curveTo(17.99f, 7.86f, 19.0f, 9.81f, 19.0f, 12.0f);
        pathBuilderK.curveToRelative(0.0f, 3.87f, -3.13f, 7.0f, -7.0f, 7.0f);
        pathBuilderK.reflectiveCurveToRelative(-7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderK.curveToRelative(0.0f, -2.19f, 1.01f, -4.14f, 2.58f, -5.42f);
        pathBuilderK.lineTo(6.17f, 5.17f);
        pathBuilderK.curveTo(4.23f, 6.82f, 3.0f, 9.26f, 3.0f, 12.0f);
        pathBuilderK.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderK.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderK.curveToRelative(0.0f, -2.74f, -1.23f, -5.18f, -3.17f, -6.83f);
        pathBuilderK.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderK.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _powerSettingsNew = imageVectorBuild;
        return imageVectorBuild;
    }
}

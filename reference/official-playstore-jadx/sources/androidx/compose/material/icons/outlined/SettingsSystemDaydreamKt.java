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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsSystemDaydream", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsSystemDaydream", "Landroidx/compose/material/icons/Icons$Outlined;", "getSettingsSystemDaydream", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsSystemDaydreamKt {
    private static ImageVector _settingsSystemDaydream;

    public static final ImageVector getSettingsSystemDaydream(Icons.Outlined outlined) {
        ImageVector imageVector = _settingsSystemDaydream;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SettingsSystemDaydream", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.5f, 17.0f, 9.0f, 17.0f);
        pathBuilderJ.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.93f, 1.36f, -3.56f, 3.22f, -3.92f);
        pathBuilderJ.curveTo(9.04f, 7.8f, 10.47f, 7.0f, 12.0f, 7.0f);
        pathBuilderJ.curveToRelative(1.95f, 0.0f, 3.66f, 1.28f, 4.26f, 3.09f);
        pathBuilderJ.curveToRelative(1.58f, 0.36f, 2.74f, 1.75f, 2.74f, 3.41f);
        pathBuilderJ.curveToRelative(0.0f, 1.93f, -1.57f, 3.5f, -3.5f, 3.5f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(8.74f, 11.02f);
        pathBuilderJ.curveTo(7.74f, 11.15f, 7.0f, 11.99f, 7.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(6.5f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(-0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderJ.horizontalLineToRelative(-0.87f);
        pathBuilderJ.lineToRelative(-0.17f, -0.86f);
        pathBuilderJ.curveTo(14.29f, 9.92f, 13.23f, 9.0f, 12.0f, 9.0f);
        pathBuilderJ.curveToRelative(-0.96f, 0.0f, -1.84f, 0.57f, -2.26f, 1.45f);
        y.a.k(pathBuilderJ, -0.27f, 0.57f, -0.73f);
        pathBuilderJ.moveTo(21.0f, 3.0f);
        pathBuilderJ.lineTo(3.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(23.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 21.0f, 19.01f, 3.0f, 19.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderJ, 3.0f, 4.99f, 18.0f, 14.02f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsSystemDaydream = imageVectorBuild;
        return imageVectorBuild;
    }
}

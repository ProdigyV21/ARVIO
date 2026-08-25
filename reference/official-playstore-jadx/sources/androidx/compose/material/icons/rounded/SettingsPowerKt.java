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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsPower", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsPower", "Landroidx/compose/material/icons/Icons$Rounded;", "getSettingsPower", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsPowerKt {
    private static ImageVector _settingsPower;

    public static final ImageVector getSettingsPower(Icons.Rounded rounded) {
        ImageVector imageVector = _settingsPower;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SettingsPower", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(8.0f, 24.0f, 8.0f, 24.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveTo(7.0f, 23.55f, 7.45f, 24.0f, 8.0f, 24.0f);
        a.p(pathBuilderJ, 12.0f, 24.0f, 12.0f, 24.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveTo(11.0f, 23.55f, 11.45f, 24.0f, 12.0f, 24.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 2.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineTo(3.0f);
        pathBuilderJ.curveTo(13.0f, 2.45f, 12.55f, 2.0f, 12.0f, 2.0f);
        a.z(pathBuilderJ, 15.94f, 5.06f, -0.02f, 0.02f);
        pathBuilderJ.curveTo(15.51f, 5.49f, 15.56f, 6.16f, 16.0f, 6.54f);
        pathBuilderJ.curveToRelative(1.51f, 1.34f, 2.33f, 3.43f, 1.88f, 5.7f);
        pathBuilderJ.curveToRelative(-0.46f, 2.28f, -2.29f, 4.14f, -4.56f, 4.62f);
        pathBuilderJ.curveTo(9.43f, 17.69f, 6.0f, 14.74f, 6.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.78f, 0.78f, -3.37f, 2.01f, -4.47f);
        pathBuilderJ.curveToRelative(0.43f, -0.39f, 0.47f, -1.04f, 0.07f, -1.45f);
        pathBuilderJ.lineTo(8.06f, 5.06f);
        pathBuilderJ.curveTo(7.69f, 4.69f, 7.1f, 4.67f, 6.7f, 5.02f);
        pathBuilderJ.curveToRelative(-2.01f, 1.77f, -3.12f, 4.53f, -2.56f, 7.52f);
        pathBuilderJ.curveToRelative(0.59f, 3.15f, 3.11f, 5.7f, 6.26f, 6.31f);
        pathBuilderJ.curveToRelative(5.12f, 0.99f, 9.6f, -2.9f, 9.6f, -7.85f);
        pathBuilderJ.curveToRelative(0.0f, -2.38f, -1.05f, -4.52f, -2.71f, -5.99f);
        pathBuilderJ.curveTo(16.9f, 4.67f, 16.31f, 4.69f, 15.94f, 5.06f);
        a.p(pathBuilderJ, 16.0f, 24.0f, 16.0f, 24.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveTo(15.0f, 23.55f, 15.45f, 24.0f, 16.0f, 24.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsPower = imageVectorBuild;
        return imageVectorBuild;
    }
}

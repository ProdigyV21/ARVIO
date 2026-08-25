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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_batteryChargingFull", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BatteryChargingFull", "Landroidx/compose/material/icons/Icons$Rounded;", "getBatteryChargingFull", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BatteryChargingFullKt {
    private static ImageVector _batteryChargingFull;

    public static final ImageVector getBatteryChargingFull(Icons.Rounded rounded) {
        ImageVector imageVector = _batteryChargingFull;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BatteryChargingFull", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.67f, 4.0f, 14.0f, 4.0f);
        pathBuilderJ.lineTo(14.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(1.0f);
        pathBuilderJ.lineTo(8.33f, 4.0f);
        pathBuilderJ.curveTo(7.6f, 4.0f, 7.0f, 4.6f, 7.0f, 5.33f);
        pathBuilderJ.verticalLineToRelative(15.33f);
        pathBuilderJ.curveTo(7.0f, 21.4f, 7.6f, 22.0f, 8.34f, 22.0f);
        pathBuilderJ.horizontalLineToRelative(7.32f);
        pathBuilderJ.curveToRelative(0.74f, 0.0f, 1.34f, -0.6f, 1.34f, -1.33f);
        pathBuilderJ.lineTo(17.0f, 5.33f);
        pathBuilderJ.curveTo(17.0f, 4.6f, 16.4f, 4.0f, 15.67f, 4.0f);
        a.z(pathBuilderJ, 14.61f, 13.24f, -2.67f, 5.0f);
        pathBuilderJ.curveToRelative(-0.24f, 0.45f, -0.94f, 0.28f, -0.94f, -0.24f);
        pathBuilderJ.verticalLineToRelative(-3.5f);
        pathBuilderJ.lineTo(9.83f, 14.5f);
        pathBuilderJ.curveToRelative(-0.38f, 0.0f, -0.62f, -0.4f, -0.44f, -0.74f);
        pathBuilderJ.lineToRelative(2.67f, -5.0f);
        pathBuilderJ.curveToRelative(0.24f, -0.45f, 0.94f, -0.28f, 0.94f, 0.24f);
        pathBuilderJ.verticalLineToRelative(3.5f);
        pathBuilderJ.horizontalLineToRelative(1.17f);
        pathBuilderJ.curveToRelative(0.37f, 0.0f, 0.62f, 0.4f, 0.44f, 0.74f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _batteryChargingFull = imageVectorBuild;
        return imageVectorBuild;
    }
}

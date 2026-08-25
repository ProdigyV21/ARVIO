package androidx.compose.material.icons.automirrored.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_batteryUnknown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BatteryUnknown", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getBatteryUnknown", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BatteryUnknownKt {
    private static ImageVector _batteryUnknown;

    public static final ImageVector getBatteryUnknown(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _batteryUnknown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.BatteryUnknown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.67f, 4.0f, 14.0f, 4.0f);
        b.A(pathBuilderJ, 14.0f, 2.0f, -4.0f, 2.0f);
        pathBuilderJ.lineTo(8.33f, 4.0f);
        pathBuilderJ.curveTo(7.6f, 4.0f, 7.0f, 4.6f, 7.0f, 5.33f);
        pathBuilderJ.verticalLineToRelative(15.33f);
        pathBuilderJ.curveTo(7.0f, 21.4f, 7.6f, 22.0f, 8.33f, 22.0f);
        pathBuilderJ.horizontalLineToRelative(7.33f);
        pathBuilderJ.curveToRelative(0.74f, 0.0f, 1.34f, -0.6f, 1.34f, -1.33f);
        pathBuilderJ.lineTo(17.0f, 5.33f);
        pathBuilderJ.curveTo(17.0f, 4.6f, 16.4f, 4.0f, 15.67f, 4.0f);
        b.w(pathBuilderJ, 13.0f, 18.0f, -2.0f, -2.0f);
        b.B(pathBuilderJ, 2.0f, 2.0f, 14.3f, 12.69f);
        pathBuilderJ.reflectiveCurveToRelative(-0.38f, 0.42f, -0.67f, 0.71f);
        pathBuilderJ.curveToRelative(-0.48f, 0.48f, -0.83f, 1.15f, -0.83f, 1.6f);
        pathBuilderJ.horizontalLineToRelative(-1.6f);
        pathBuilderJ.curveToRelative(0.0f, -0.83f, 0.46f, -1.52f, 0.93f, -2.0f);
        pathBuilderJ.lineToRelative(0.93f, -0.94f);
        pathBuilderJ.curveToRelative(0.27f, -0.27f, 0.44f, -0.65f, 0.44f, -1.06f);
        pathBuilderJ.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderJ.lineTo(9.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.66f, -0.27f, 1.26f, -0.7f, 1.69f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _batteryUnknown = imageVectorBuild;
        return imageVectorBuild;
    }
}

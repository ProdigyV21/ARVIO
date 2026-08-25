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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deviceUnknown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeviceUnknown", "Landroidx/compose/material/icons/Icons$Filled;", "getDeviceUnknown", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeviceUnknownKt {
    private static ImageVector _deviceUnknown;

    public static final ImageVector getDeviceUnknown(Icons.Filled filled) {
        ImageVector imageVector = _deviceUnknown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DeviceUnknown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.0f, 1.0f, 7.0f, 1.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(10.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(19.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 17.0f, 19.0f, 7.0f, 19.0f);
        b.g(pathBuilderJ, 7.0f, 5.0f, 10.0f, 14.0f);
        pathBuilderJ.moveTo(12.0f, 6.72f);
        pathBuilderJ.curveToRelative(-1.96f, 0.0f, -3.5f, 1.52f, -3.5f, 3.47f);
        pathBuilderJ.horizontalLineToRelative(1.75f);
        pathBuilderJ.curveToRelative(0.0f, -0.93f, 0.82f, -1.75f, 1.75f, -1.75f);
        pathBuilderJ.reflectiveCurveToRelative(1.75f, 0.82f, 1.75f, 1.75f);
        pathBuilderJ.curveToRelative(0.0f, 1.75f, -2.63f, 1.57f, -2.63f, 4.45f);
        pathBuilderJ.horizontalLineToRelative(1.76f);
        pathBuilderJ.curveToRelative(0.0f, -1.96f, 2.62f, -2.19f, 2.62f, -4.45f);
        pathBuilderJ.curveToRelative(0.0f, -1.96f, -1.54f, -3.47f, -3.5f, -3.47f);
        b.w(pathBuilderJ, 11.12f, 15.52f, 1.76f, 1.76f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ, -1.76f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deviceUnknown = imageVectorBuild;
        return imageVectorBuild;
    }
}

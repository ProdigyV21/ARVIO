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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deviceUnknown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeviceUnknown", "Landroidx/compose/material/icons/Icons$Rounded;", "getDeviceUnknown", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeviceUnknownKt {
    private static ImageVector _deviceUnknown;

    public static final ImageVector getDeviceUnknown(Icons.Rounded rounded) {
        ImageVector imageVector = _deviceUnknown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DeviceUnknown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        a.C(pathBuilderJ, 11.0f, 16.0f, 2.0f, 2.0f);
        e.r(pathBuilderJ, -2.0f, 9.52f, 10.19f, 0.13f);
        pathBuilderJ.curveToRelative(0.33f, 0.0f, 0.59f, -0.23f, 0.7f, -0.54f);
        pathBuilderJ.curveToRelative(0.24f, -0.69f, 0.91f, -1.21f, 1.66f, -1.21f);
        pathBuilderJ.curveToRelative(0.93f, 0.0f, 1.75f, 0.82f, 1.75f, 1.75f);
        pathBuilderJ.curveToRelative(0.0f, 1.32f, -1.49f, 1.55f, -2.23f, 2.82f);
        pathBuilderJ.horizontalLineToRelative(-0.01f);
        pathBuilderJ.curveToRelative(-0.08f, 0.14f, -0.14f, 0.29f, -0.2f, 0.45f);
        pathBuilderJ.curveToRelative(-0.01f, 0.02f, -0.02f, 0.03f, -0.02f, 0.05f);
        pathBuilderJ.curveToRelative(-0.01f, 0.02f, -0.01f, 0.04f, -0.01f, 0.05f);
        pathBuilderJ.curveToRelative(-0.1f, 0.31f, -0.16f, 0.66f, -0.16f, 1.08f);
        pathBuilderJ.horizontalLineToRelative(1.76f);
        pathBuilderJ.curveToRelative(0.0f, -0.25f, 0.04f, -0.47f, 0.12f, -0.67f);
        pathBuilderJ.curveToRelative(0.54f, -1.47f, 2.77f, -1.86f, 2.48f, -4.18f);
        pathBuilderJ.curveToRelative(-0.19f, -1.55f, -1.43f, -2.84f, -2.98f, -3.04f);
        pathBuilderJ.curveToRelative(-1.77f, -0.23f, -3.29f, 0.78f, -3.81f, 2.3f);
        pathBuilderJ.curveToRelative(-0.2f, 0.56f, 0.23f, 1.14f, 0.82f, 1.14f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deviceUnknown = imageVectorBuild;
        return imageVectorBuild;
    }
}

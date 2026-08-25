package androidx.compose.material.icons.twotone;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deviceUnknown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeviceUnknown", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDeviceUnknown", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeviceUnknownKt {
    private static ImageVector _deviceUnknown;

    public static final ImageVector getDeviceUnknown(Icons.TwoTone twoTone) {
        ImageVector imageVector = _deviceUnknown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DeviceUnknown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(7.0f, 19.0f, 10.0f, 17.0f, 5.0f);
        d.f(pathBuilderI, 7.0f, 5.0f, 14.0f);
        a.C(pathBuilderI, 13.0f, 18.0f, -2.0f, -2.0f);
        b.B(pathBuilderI, 2.0f, 2.0f, 12.0f, 6.72f);
        pathBuilderI.curveToRelative(1.96f, 0.0f, 3.5f, 1.51f, 3.5f, 3.47f);
        pathBuilderI.curveToRelative(0.0f, 2.26f, -2.62f, 2.49f, -2.62f, 4.45f);
        pathBuilderI.horizontalLineToRelative(-1.76f);
        pathBuilderI.curveToRelative(0.0f, -2.88f, 2.63f, -2.7f, 2.63f, -4.45f);
        pathBuilderI.curveToRelative(0.0f, -0.93f, -0.82f, -1.75f, -1.75f, -1.75f);
        pathBuilderI.reflectiveCurveToRelative(-1.75f, 0.82f, -1.75f, 1.75f);
        pathBuilderI.lineTo(8.5f, 10.19f);
        pathBuilderI.curveToRelative(0.0f, -1.95f, 1.54f, -3.47f, 3.5f, -3.47f);
        pathBuilderI.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(11.0f, 16.0f, 2.0f, 2.0f, -2.0f);
        pathBuilderB.moveTo(17.0f, 1.0f);
        pathBuilderB.lineTo(7.0f, 1.0f);
        pathBuilderB.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(18.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(10.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderB.lineTo(19.0f, 3.0f);
        pathBuilderB.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderB, 17.0f, 19.0f, 7.0f, 19.0f);
        b.g(pathBuilderB, 7.0f, 5.0f, 10.0f, 14.0f);
        pathBuilderB.moveTo(12.0f, 8.44f);
        pathBuilderB.curveToRelative(0.93f, 0.0f, 1.75f, 0.82f, 1.75f, 1.75f);
        pathBuilderB.curveToRelative(0.0f, 1.75f, -2.63f, 1.57f, -2.63f, 4.45f);
        pathBuilderB.horizontalLineToRelative(1.76f);
        pathBuilderB.curveToRelative(0.0f, -1.96f, 2.62f, -2.19f, 2.62f, -4.45f);
        pathBuilderB.curveToRelative(0.0f, -1.96f, -1.54f, -3.47f, -3.5f, -3.47f);
        pathBuilderB.reflectiveCurveToRelative(-3.5f, 1.52f, -3.5f, 3.47f);
        pathBuilderB.horizontalLineToRelative(1.75f);
        pathBuilderB.curveToRelative(0.0f, -0.93f, 0.82f, -1.75f, 1.75f, -1.75f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deviceUnknown = imageVectorBuild;
        return imageVectorBuild;
    }
}

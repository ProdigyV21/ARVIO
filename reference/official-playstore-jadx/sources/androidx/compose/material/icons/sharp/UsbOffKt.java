package androidx.compose.material.icons.sharp;

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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_usbOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "UsbOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getUsbOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UsbOffKt {
    private static ImageVector _usbOff;

    public static final ImageVector getUsbOff(Icons.Sharp sharp) {
        ImageVector imageVector = _usbOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.UsbOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(15.0f, 8.0f, 4.0f, 4.0f, -1.0f);
        pathBuilderK.verticalLineToRelative(2.0f);
        pathBuilderK.curveToRelative(0.0f, 0.34f, -0.08f, 0.66f, -0.23f, 0.94f);
        b.n(pathBuilderK, 16.0f, 13.17f, 12.0f, -1.0f);
        f.r(pathBuilderK, 8.0f, 11.0f, 8.17f);
        b.o(pathBuilderK, 2.0f, 2.0f, 6.0f, 2.0f);
        pathBuilderK.lineToRelative(-3.0f, -4.0f);
        pathBuilderK.lineTo(9.0f, 6.0f);
        pathBuilderK.horizontalLineToRelative(2.0f);
        pathBuilderK.verticalLineTo(8.17f);
        pathBuilderK.close();
        pathBuilderK.moveTo(13.0f, 16.0f);
        pathBuilderK.verticalLineToRelative(2.28f);
        pathBuilderK.curveToRelative(0.6f, 0.34f, 1.0f, 0.98f, 1.0f, 1.72f);
        pathBuilderK.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderK.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderK.curveToRelative(0.0f, -0.74f, 0.4f, -1.37f, 1.0f, -1.72f);
        pathBuilderK.verticalLineTo(16.0f);
        pathBuilderK.horizontalLineTo(8.0f);
        pathBuilderK.curveToRelative(-1.11f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderK.verticalLineToRelative(-2.28f);
        pathBuilderK.curveTo(5.4f, 11.38f, 5.0f, 10.74f, 5.0f, 10.0f);
        pathBuilderK.curveToRelative(0.0f, -0.59f, 0.26f, -1.13f, 0.68f, -1.49f);
        pathBuilderK.lineTo(1.39f, 4.22f);
        pathBuilderK.lineToRelative(1.41f, -1.41f);
        pathBuilderK.lineToRelative(18.38f, 18.38f);
        pathBuilderK.lineToRelative(-1.41f, 1.41f);
        w.a.e(pathBuilderK, 13.17f, 16.0f, 13.0f);
        pathBuilderK.moveTo(11.0f, 14.0f);
        pathBuilderK.verticalLineToRelative(-0.17f);
        pathBuilderK.lineToRelative(-2.51f, -2.51f);
        pathBuilderK.curveToRelative(-0.14f, 0.16f, -0.31f, 0.29f, -0.49f, 0.4f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderK, 14.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _usbOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

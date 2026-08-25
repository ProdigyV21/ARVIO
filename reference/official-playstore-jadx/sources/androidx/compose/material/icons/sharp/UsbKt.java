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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_usb", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Usb", "Landroidx/compose/material/icons/Icons$Sharp;", "getUsb", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UsbKt {
    private static ImageVector _usb;

    public static final ImageVector getUsb(Icons.Sharp sharp) {
        ImageVector imageVector = _usb;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Usb", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(15.0f, 7.0f, 4.0f, 1.0f, 2.0f);
        a0.a.d(pathBuilderU, -3.0f, 5.0f, 2.0f);
        pathBuilderU.lineToRelative(-3.0f, -4.0f);
        a0.a.m(pathBuilderU, -3.0f, 4.0f, 2.0f, 8.0f);
        pathBuilderU.horizontalLineTo(8.0f);
        pathBuilderU.verticalLineToRelative(-2.07f);
        pathBuilderU.curveToRelative(0.7f, -0.37f, 1.2f, -1.08f, 1.2f, -1.93f);
        pathBuilderU.curveToRelative(0.0f, -1.21f, -0.99f, -2.2f, -2.2f, -2.2f);
        pathBuilderU.reflectiveCurveTo(4.8f, 7.79f, 4.8f, 9.0f);
        pathBuilderU.curveToRelative(0.0f, 0.85f, 0.5f, 1.56f, 1.2f, 1.93f);
        pathBuilderU.verticalLineTo(13.0f);
        pathBuilderU.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(3.0f);
        pathBuilderU.verticalLineToRelative(3.05f);
        pathBuilderU.curveToRelative(-0.71f, 0.37f, -1.2f, 1.1f, -1.2f, 1.95f);
        pathBuilderU.curveToRelative(0.0f, 1.22f, 0.99f, 2.2f, 2.2f, 2.2f);
        pathBuilderU.reflectiveCurveToRelative(2.2f, -0.98f, 2.2f, -2.2f);
        pathBuilderU.curveToRelative(0.0f, -0.85f, -0.49f, -1.58f, -1.2f, -1.95f);
        pathBuilderU.verticalLineTo(15.0f);
        pathBuilderU.horizontalLineToRelative(3.0f);
        pathBuilderU.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderU.verticalLineToRelative(-2.0f);
        pathBuilderU.horizontalLineToRelative(1.0f);
        pathBuilderU.verticalLineTo(7.0f);
        pathBuilderU.horizontalLineToRelative(-4.0f);
        pathBuilderU.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _usb = imageVectorBuild;
        return imageVectorBuild;
    }
}

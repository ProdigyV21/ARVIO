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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiPassword", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiPassword", "Landroidx/compose/material/icons/Icons$Filled;", "getWifiPassword", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiPasswordKt {
    private static ImageVector _wifiPassword;

    public static final ImageVector getWifiPassword(Icons.Filled filled) {
        ImageVector imageVector = _wifiPassword;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.WifiPassword", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(23.0f, 19.0f, -1.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(1.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(3.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(4.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-3.0f);
        pathBuilderI.curveTo(24.0f, 19.45f, 23.55f, 19.0f, 23.0f, 19.0f);
        b.w(pathBuilderI, 22.0f, 19.0f, -2.0f, -1.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderI, 19.0f, 24.0f, 8.98f);
        pathBuilderI.lineToRelative(-2.12f, 2.13f);
        pathBuilderI.curveTo(19.35f, 8.57f, 15.85f, 7.0f, 12.0f, 7.0f);
        pathBuilderI.reflectiveCurveToRelative(-7.35f, 1.57f, -9.88f, 4.11f);
        pathBuilderI.lineTo(0.0f, 8.98f);
        pathBuilderI.curveTo(3.07f, 5.9f, 7.31f, 4.0f, 12.0f, 4.0f);
        pathBuilderI.reflectiveCurveTo(20.93f, 5.9f, 24.0f, 8.98f);
        pathBuilderI.close();
        pathBuilderI.moveTo(12.0f, 10.0f);
        pathBuilderI.curveToRelative(3.03f, 0.0f, 5.78f, 1.23f, 7.76f, 3.22f);
        pathBuilderI.lineToRelative(-2.12f, 2.12f);
        pathBuilderI.curveTo(16.2f, 13.9f, 14.2f, 13.0f, 12.0f, 13.0f);
        pathBuilderI.curveToRelative(-2.2f, 0.0f, -4.2f, 0.9f, -5.64f, 2.35f);
        pathBuilderI.lineToRelative(-2.12f, -2.12f);
        pathBuilderI.curveTo(6.22f, 11.23f, 8.97f, 10.0f, 12.0f, 10.0f);
        a.p(pathBuilderI, 15.53f, 17.46f, 12.0f, 21.0f);
        pathBuilderI.lineToRelative(-3.53f, -3.54f);
        pathBuilderI.curveTo(9.37f, 16.56f, 10.62f, 16.0f, 12.0f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderI, 14.63f, 16.56f, 15.53f, 17.46f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiPassword = imageVectorBuild;
        return imageVectorBuild;
    }
}

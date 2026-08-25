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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiPassword", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiPassword", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWifiPassword", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiPasswordKt {
    private static ImageVector _wifiPassword;

    public static final ImageVector getWifiPassword(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wifiPassword;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WifiPassword", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(24.0f, 8.98f, -2.12f, 2.13f);
        pathBuilderR.curveTo(19.35f, 8.57f, 15.85f, 7.0f, 12.0f, 7.0f);
        pathBuilderR.reflectiveCurveToRelative(-7.35f, 1.57f, -9.88f, 4.11f);
        pathBuilderR.lineTo(0.0f, 8.98f);
        pathBuilderR.curveTo(3.07f, 5.9f, 7.31f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.reflectiveCurveTo(20.93f, 5.9f, 24.0f, 8.98f);
        d.k(pathBuilderR, 24.0f, 20.0f, 3.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-3.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-1.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(1.0f);
        pathBuilderR.curveTo(23.55f, 19.0f, 24.0f, 19.45f, 24.0f, 20.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(22.0f, 18.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        a.t(pathBuilderR, 1.0f, 2.0f, 18.0f);
        pathBuilderR.moveTo(4.24f, 13.22f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.curveTo(7.8f, 13.9f, 9.8f, 13.0f, 12.0f, 13.0f);
        pathBuilderR.curveToRelative(2.2f, 0.0f, 4.2f, 0.9f, 5.64f, 2.35f);
        pathBuilderR.lineToRelative(2.12f, -2.12f);
        pathBuilderR.curveTo(17.78f, 11.23f, 15.03f, 10.0f, 12.0f, 10.0f);
        pathBuilderR.curveTo(8.97f, 10.0f, 6.22f, 11.23f, 4.24f, 13.22f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 16.0f);
        pathBuilderR.curveToRelative(-1.38f, 0.0f, -2.63f, 0.56f, -3.53f, 1.46f);
        pathBuilderR.lineTo(12.0f, 21.0f);
        pathBuilderR.lineToRelative(3.53f, -3.54f);
        pathBuilderR.curveTo(14.63f, 16.56f, 13.38f, 16.0f, 12.0f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiPassword = imageVectorBuild;
        return imageVectorBuild;
    }
}

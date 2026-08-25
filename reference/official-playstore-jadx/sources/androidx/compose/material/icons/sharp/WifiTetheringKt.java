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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiTethering", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiTethering", "Landroidx/compose/material/icons/Icons$Sharp;", "getWifiTethering", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiTetheringKt {
    private static ImageVector _wifiTethering;

    public static final ImageVector getWifiTethering(Icons.Sharp sharp) {
        ImageVector imageVector = _wifiTethering;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WifiTethering", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -3.31f, -2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, 2.22f, 1.21f, 4.15f, 3.0f, 5.19f);
        pathBuilderA.lineToRelative(1.0f, -1.74f);
        pathBuilderA.curveToRelative(-1.19f, -0.7f, -2.0f, -1.97f, -2.0f, -3.45f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.48f, -0.81f, 2.75f, -2.0f, 3.45f);
        pathBuilderA.lineToRelative(1.0f, 1.74f);
        pathBuilderA.curveToRelative(1.79f, -1.04f, 3.0f, -2.97f, 3.0f, -5.19f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 3.0f);
        pathBuilderA.curveTo(6.48f, 3.0f, 2.0f, 7.48f, 2.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 3.7f, 2.01f, 6.92f, 4.99f, 8.65f);
        pathBuilderA.lineToRelative(1.0f, -1.73f);
        pathBuilderA.curveTo(5.61f, 18.53f, 4.0f, 15.96f, 4.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -4.42f, 3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, 3.58f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 2.96f, -1.61f, 5.53f, -4.0f, 6.92f);
        pathBuilderA.lineToRelative(1.0f, 1.73f);
        pathBuilderA.curveToRelative(2.99f, -1.73f, 5.0f, -4.95f, 5.0f, -8.65f);
        pathBuilderA.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiTethering = imageVectorBuild;
        return imageVectorBuild;
    }
}

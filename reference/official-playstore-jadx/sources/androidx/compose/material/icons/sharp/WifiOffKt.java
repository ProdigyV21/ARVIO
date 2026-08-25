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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getWifiOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiOffKt {
    private static ImageVector _wifiOff;

    public static final ImageVector getWifiOff(Icons.Sharp sharp) {
        ImageVector imageVector = _wifiOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WifiOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.0f, 11.0f, 2.0f, -2.0f);
        pathBuilderR.curveToRelative(-3.73f, -3.73f, -8.87f, -5.15f, -13.7f, -4.31f);
        pathBuilderR.lineToRelative(2.58f, 2.58f);
        pathBuilderR.curveToRelative(3.3f, -0.02f, 6.61f, 1.22f, 9.12f, 3.73f);
        a.z(pathBuilderR, 9.0f, 17.0f, 3.0f, 3.0f);
        pathBuilderR.lineToRelative(3.0f, -3.0f);
        pathBuilderR.curveToRelative(-1.65f, -1.66f, -4.34f, -1.66f, -6.0f, 0.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(19.0f, 13.0f);
        pathBuilderR.curveToRelative(-1.08f, -1.08f, -2.36f, -1.85f, -3.72f, -2.33f);
        b.D(pathBuilderR, 3.02f, 3.02f, 0.7f, -0.69f);
        pathBuilderR.moveTo(3.41f, 1.64f);
        pathBuilderR.lineTo(2.0f, 3.05f);
        pathBuilderR.lineTo(5.05f, 6.1f);
        pathBuilderR.curveTo(3.59f, 6.83f, 2.22f, 7.79f, 1.0f, 9.0f);
        pathBuilderR.lineToRelative(2.0f, 2.0f);
        pathBuilderR.curveToRelative(1.23f, -1.23f, 2.65f, -2.16f, 4.17f, -2.78f);
        pathBuilderR.lineToRelative(2.24f, 2.24f);
        pathBuilderR.curveTo(7.79f, 10.89f, 6.27f, 11.74f, 5.0f, 13.0f);
        pathBuilderR.lineToRelative(2.0f, 2.0f);
        pathBuilderR.curveToRelative(1.35f, -1.35f, 3.11f, -2.04f, 4.89f, -2.06f);
        pathBuilderR.lineToRelative(7.08f, 7.08f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.41f, -1.41f, 3.41f, 1.64f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

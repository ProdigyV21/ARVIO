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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vpnKeyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VpnKeyOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getVpnKeyOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VpnKeyOffKt {
    private static ImageVector _vpnKeyOff;

    public static final ImageVector getVpnKeyOff(Icons.Sharp sharp) {
        ImageVector imageVector = _vpnKeyOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VpnKeyOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(20.83f, 18.0f, 21.0f, -4.0f, 2.0f);
        pathBuilderH.verticalLineToRelative(-4.0f);
        pathBuilderH.horizontalLineTo(12.83f);
        pathBuilderH.lineTo(20.83f, 18.0f);
        pathBuilderH.close();
        pathBuilderH.moveTo(19.78f, 22.61f);
        pathBuilderH.lineToRelative(1.41f, -1.41f);
        pathBuilderH.lineTo(2.81f, 2.81f);
        pathBuilderH.lineTo(1.39f, 4.22f);
        pathBuilderH.lineToRelative(2.59f, 2.59f);
        pathBuilderH.curveTo(2.2f, 7.85f, 1.0f, 9.79f, 1.0f, 12.0f);
        pathBuilderH.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderH.curveToRelative(2.21f, 0.0f, 4.15f, -1.2f, 5.18f, -2.99f);
        c.z(pathBuilderH, 19.78f, 22.61f, 8.99f, 11.82f);
        pathBuilderH.curveTo(9.0f, 11.88f, 9.0f, 11.94f, 9.0f, 12.0f);
        pathBuilderH.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderH.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderH.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderH.curveToRelative(0.06f, 0.0f, 0.12f, 0.0f, 0.18f, 0.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderH, 8.99f, 11.82f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vpnKeyOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.outlined;

import a0.b;
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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vpnKeyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VpnKeyOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getVpnKeyOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VpnKeyOffKt {
    private static ImageVector _vpnKeyOff;

    public static final ImageVector getVpnKeyOff(Icons.Outlined outlined) {
        ImageVector imageVector = _vpnKeyOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.VpnKeyOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderJ.lineToRelative(2.59f, 2.59f);
        pathBuilderJ.curveTo(2.2f, 7.85f, 1.0f, 9.79f, 1.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderJ.curveToRelative(2.22f, 0.0f, 4.15f, -1.21f, 5.19f, -3.0f);
        pathBuilderJ.lineToRelative(7.59f, 7.61f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 2.81f, 2.81f);
        pathBuilderJ.moveTo(7.0f, 16.0f);
        pathBuilderJ.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.67f, 1.02f, -3.1f, 2.47f, -3.7f);
        pathBuilderJ.lineToRelative(1.71f, 1.71f);
        pathBuilderJ.curveTo(7.12f, 10.0f, 7.06f, 10.0f, 7.0f, 10.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.06f, 0.0f, -0.12f, -0.01f, -0.18f);
        pathBuilderJ.lineToRelative(1.74f, 1.74f);
        pathBuilderJ.curveTo(10.22f, 14.48f, 9.14f, 16.0f, 7.0f, 16.0f);
        b.t(pathBuilderJ, 17.0f, 14.17f, 13.0f, -1.17f);
        c.z(pathBuilderJ, 17.0f, 14.17f, 13.83f, 11.0f);
        v.b.q(pathBuilderJ, 21.0f, 2.0f, -2.0f, 3.0f);
        v.b.u(pathBuilderJ, 2.0f, 2.0f, -3.0f, 2.0f);
        pathBuilderJ.verticalLineTo(9.0f);
        pathBuilderJ.horizontalLineTo(11.83f);
        pathBuilderJ.lineTo(13.83f, 11.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vpnKeyOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vpnLock", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VpnLock", "Landroidx/compose/material/icons/Icons$TwoTone;", "getVpnLock", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VpnLockKt {
    private static ImageVector _vpnLock;

    public static final ImageVector getVpnLock(Icons.TwoTone twoTone) {
        ImageVector imageVector = _vpnLock;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.VpnLock", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(12.0f, 8.0f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a.h(pathBuilderA, 7.0f, 11.0f, 2.0f, 6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.64f, 0.58f, 1.9f, 1.39f);
        pathBuilderA.curveTo(18.2f, 16.97f, 19.0f, 15.08f, 19.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -0.34f, -0.04f, -0.67f, -0.08f, -1.0f);
        pathBuilderA.lineTo(17.0f, 12.0f);
        pathBuilderA.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.lineTo(14.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        d.k(pathBuilderA, 8.0f, 17.0f, -1.0f);
        pathBuilderA.lineToRelative(-4.79f, -4.79f);
        pathBuilderA.curveTo(3.08f, 11.79f, 3.0f, 12.38f, 3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.08f, 3.05f, 7.44f, 7.0f, 7.93f);
        pathBuilderA.lineTo(10.0f, 19.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(18.92f, 12.0f);
        pathBuilderA2.curveToRelative(0.04f, 0.33f, 0.08f, 0.66f, 0.08f, 1.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.08f, -0.8f, 3.97f, -2.1f, 5.39f);
        pathBuilderA2.curveToRelative(-0.26f, -0.81f, -1.0f, -1.39f, -1.9f, -1.39f);
        pathBuilderA2.horizontalLineToRelative(-1.0f);
        pathBuilderA2.verticalLineToRelative(-3.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.h(pathBuilderA2, 7.0f, 13.0f, -2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.lineTo(10.0f, 8.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.lineTo(14.0f, 3.46f);
        pathBuilderA2.curveToRelative(-0.95f, -0.3f, -1.95f, -0.46f, -3.0f, -0.46f);
        pathBuilderA2.curveTo(5.48f, 3.0f, 1.0f, 7.48f, 1.0f, 13.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.34f, -0.02f, -0.67f, -0.05f, -1.0f);
        f.x(pathBuilderA2, -2.03f, 10.0f, 20.93f);
        pathBuilderA2.curveToRelative(-3.95f, -0.49f, -7.0f, -3.85f, -7.0f, -7.93f);
        pathBuilderA2.curveToRelative(0.0f, -0.62f, 0.08f, -1.21f, 0.21f, -1.79f);
        pathBuilderA2.lineTo(8.0f, 16.0f);
        pathBuilderA2.verticalLineToRelative(1.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        e.q(pathBuilderA2, 1.93f, 22.0f, 4.0f, -0.5f);
        pathBuilderA2.curveTo(22.0f, 2.12f, 20.88f, 1.0f, 19.5f, 1.0f);
        pathBuilderA2.reflectiveCurveTo(17.0f, 2.12f, 17.0f, 3.5f);
        pathBuilderA2.lineTo(17.0f, 4.0f);
        pathBuilderA2.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA2.verticalLineToRelative(4.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA2.horizontalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.lineTo(23.0f, 5.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        b.w(pathBuilderA2, 21.0f, 4.0f, -3.0f, -0.5f);
        pathBuilderA2.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 21.0f, 4.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vpnLock = imageVectorBuild;
        return imageVectorBuild;
    }
}

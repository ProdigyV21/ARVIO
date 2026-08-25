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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vpnKey", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VpnKey", "Landroidx/compose/material/icons/Icons$TwoTone;", "getVpnKey", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VpnKeyKt {
    private static ImageVector _vpnKey;

    public static final ImageVector getVpnKey(Icons.TwoTone twoTone) {
        ImageVector imageVector = _vpnKey;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.VpnKey", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.71f, 10.33f);
        pathBuilderA.curveTo(11.01f, 8.34f, 9.11f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(2.11f, 0.0f, 4.01f, -1.34f, 4.71f, -3.33f);
        e.h(pathBuilderA, 0.23f, -0.67f, 18.0f, 4.0f);
        a.o(pathBuilderA, 2.0f, -4.0f, 2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(11.94f);
        pathBuilderA.lineToRelative(-0.23f, -0.67f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.0f, 15.0f);
        pathBuilderA.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -1.35f, 3.0f, -3.0f, 3.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(7.0f, 5.0f);
        pathBuilderA2.curveToRelative(-3.86f, 0.0f, -7.0f, 3.14f, -7.0f, 7.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.14f, 7.0f, 7.0f, 7.0f);
        pathBuilderA2.curveToRelative(2.72f, 0.0f, 5.17f, -1.58f, 6.32f, -4.0f);
        a.h(pathBuilderA2, 16.0f, 15.0f, 4.0f, 6.0f);
        c.w(pathBuilderA2, -4.0f, 2.0f, 24.0f, 9.0f);
        pathBuilderA2.lineTo(13.32f, 9.0f);
        pathBuilderA2.curveTo(12.17f, 6.58f, 9.72f, 5.0f, 7.0f, 5.0f);
        b.w(pathBuilderA2, 22.0f, 13.0f, -2.0f, 4.0f);
        e.g(pathBuilderA2, -2.0f, -4.0f, -6.06f);
        pathBuilderA2.lineToRelative(-0.23f, 0.67f);
        pathBuilderA2.curveTo(11.01f, 15.66f, 9.11f, 17.0f, 7.0f, 17.0f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA2.curveToRelative(2.11f, 0.0f, 4.01f, 1.34f, 4.71f, 3.33f);
        pathBuilderA2.lineToRelative(0.23f, 0.67f);
        d.f(pathBuilderA2, 22.0f, 11.0f, 2.0f);
        pathBuilderA2.moveTo(7.0f, 9.0f);
        pathBuilderA2.curveToRelative(-1.65f, 0.0f, -3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA2.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(7.0f, 13.0f);
        pathBuilderA2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA2, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vpnKey = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_devicesOther", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DevicesOther", "Landroidx/compose/material/icons/Icons$Sharp;", "getDevicesOther", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DevicesOtherKt {
    private static ImageVector _devicesOther;

    public static final ImageVector getDevicesOther(Icons.Sharp sharp) {
        ImageVector imageVector = _devicesOther;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DevicesOther", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(3.0f, 6.0f, 18.0f, 21.0f, 4.0f);
        a.h(pathBuilderI, 1.0f, 4.0f, 16.0f, 6.0f);
        pathBuilderI.verticalLineToRelative(-2.0f);
        b.y(pathBuilderI, 3.0f, 18.0f, 3.0f, 6.0f);
        pathBuilderI.moveTo(13.0f, 12.0f);
        pathBuilderI.lineTo(9.0f, 12.0f);
        pathBuilderI.verticalLineToRelative(1.78f);
        pathBuilderI.curveToRelative(-0.61f, 0.55f, -1.0f, 1.33f, -1.0f, 2.22f);
        pathBuilderI.curveToRelative(0.0f, 0.89f, 0.39f, 1.67f, 1.0f, 2.22f);
        b.A(pathBuilderI, 9.0f, 20.0f, 4.0f, -1.78f);
        pathBuilderI.curveToRelative(0.61f, -0.55f, 1.0f, -1.34f, 1.0f, -2.22f);
        pathBuilderI.reflectiveCurveToRelative(-0.39f, -1.67f, -1.0f, -2.22f);
        c.z(pathBuilderI, 13.0f, 12.0f, 11.0f, 17.5f);
        pathBuilderI.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderI.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderI.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderI.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        b.w(pathBuilderI, 23.0f, 8.0f, -8.0f, 12.0f);
        d.q(pathBuilderI, 8.0f, 23.0f, 8.0f);
        a.C(pathBuilderI, 21.0f, 18.0f, -4.0f, -8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderI, 4.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _devicesOther = imageVectorBuild;
        return imageVectorBuild;
    }
}

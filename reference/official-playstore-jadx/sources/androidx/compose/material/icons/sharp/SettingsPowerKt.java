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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsPower", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsPower", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettingsPower", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsPowerKt {
    private static ImageVector _settingsPower;

    public static final ImageVector getSettingsPower(Icons.Sharp sharp) {
        ImageVector imageVector = _settingsPower;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SettingsPower", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(7.0f, 24.0f, 2.0f, -2.0f);
        d.f(pathBuilderA, 7.0f, 22.0f, 2.0f);
        a.C(pathBuilderA, 11.0f, 24.0f, 2.0f, -2.0f);
        b.B(pathBuilderA, -2.0f, 2.0f, 13.0f, 2.0f);
        e.g(pathBuilderA, -2.0f, 10.0f, 2.0f);
        c.z(pathBuilderA, 13.0f, 2.0f, 16.56f, 4.44f);
        pathBuilderA.lineToRelative(-1.45f, 1.45f);
        pathBuilderA.curveTo(16.84f, 6.94f, 18.0f, 8.83f, 18.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA.curveToRelative(0.0f, -2.17f, 1.16f, -4.06f, 2.88f, -5.12f);
        pathBuilderA.lineTo(7.44f, 4.44f);
        pathBuilderA.curveTo(5.36f, 5.88f, 4.0f, 8.28f, 4.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -2.72f, -1.36f, -5.12f, -3.44f, -6.56f);
        b.w(pathBuilderA, 15.0f, 24.0f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderA, -2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsPower = imageVectorBuild;
        return imageVectorBuild;
    }
}

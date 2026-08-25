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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsBrightness", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsBrightness", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettingsBrightness", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsBrightnessKt {
    private static ImageVector _settingsBrightness;

    public static final ImageVector getSettingsBrightness(Icons.Sharp sharp) {
        ImageVector imageVector = _settingsBrightness;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SettingsBrightness", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(23.0f, 3.0f, 1.0f, 3.0f, 18.0f);
        d.q(pathBuilderS, 22.0f, 23.0f, 3.0f);
        pathBuilderS.moveTo(21.0f, 19.01f);
        pathBuilderS.lineTo(3.0f, 19.01f);
        b.g(pathBuilderS, 3.0f, 4.99f, 18.0f, 14.02f);
        pathBuilderS.moveTo(8.0f, 16.0f);
        pathBuilderS.horizontalLineToRelative(2.5f);
        pathBuilderS.lineToRelative(1.5f, 1.5f);
        pathBuilderS.lineToRelative(1.5f, -1.5f);
        pathBuilderS.lineTo(16.0f, 16.0f);
        pathBuilderS.verticalLineToRelative(-2.5f);
        pathBuilderS.lineToRelative(1.5f, -1.5f);
        pathBuilderS.lineToRelative(-1.5f, -1.5f);
        pathBuilderS.lineTo(16.0f, 8.0f);
        pathBuilderS.horizontalLineToRelative(-2.5f);
        pathBuilderS.lineTo(12.0f, 6.5f);
        pathBuilderS.lineTo(10.5f, 8.0f);
        pathBuilderS.lineTo(8.0f, 8.0f);
        pathBuilderS.verticalLineToRelative(2.5f);
        pathBuilderS.lineTo(6.5f, 12.0f);
        b.y(pathBuilderS, 8.0f, 13.5f, 8.0f, 16.0f);
        pathBuilderS.moveTo(12.0f, 9.0f);
        pathBuilderS.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 12.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsBrightness = imageVectorBuild;
        return imageVectorBuild;
    }
}

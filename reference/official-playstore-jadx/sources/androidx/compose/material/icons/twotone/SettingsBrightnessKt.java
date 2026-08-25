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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsBrightness", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsBrightness", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSettingsBrightness", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsBrightnessKt {
    private static ImageVector _settingsBrightness;

    public static final ImageVector getSettingsBrightness(Icons.TwoTone twoTone) {
        ImageVector imageVector = _settingsBrightness;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SettingsBrightness", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(3.0f, 19.01f, 18.0f, 21.0f, 4.99f);
        d.f(pathBuilderI, 3.0f, 4.99f, 14.02f);
        pathBuilderI.moveTo(8.0f, 10.5f);
        pathBuilderI.lineTo(8.0f, 8.0f);
        pathBuilderI.horizontalLineToRelative(2.5f);
        pathBuilderI.lineTo(12.0f, 6.5f);
        pathBuilderI.lineTo(13.5f, 8.0f);
        pathBuilderI.lineTo(16.0f, 8.0f);
        pathBuilderI.verticalLineToRelative(2.5f);
        pathBuilderI.lineToRelative(1.5f, 1.5f);
        pathBuilderI.lineToRelative(-1.5f, 1.5f);
        pathBuilderI.lineTo(16.0f, 16.0f);
        pathBuilderI.horizontalLineToRelative(-2.5f);
        pathBuilderI.lineTo(12.0f, 17.5f);
        pathBuilderI.lineTo(10.5f, 16.0f);
        pathBuilderI.lineTo(8.0f, 16.0f);
        pathBuilderI.verticalLineToRelative(-2.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderI, 6.5f, 12.0f, 8.0f, 10.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(8.0f, 16.0f, 2.5f, 1.5f, 1.5f);
        pathBuilderG.lineToRelative(1.5f, -1.5f);
        pathBuilderG.lineTo(16.0f, 16.0f);
        pathBuilderG.verticalLineToRelative(-2.5f);
        pathBuilderG.lineToRelative(1.5f, -1.5f);
        pathBuilderG.lineToRelative(-1.5f, -1.5f);
        pathBuilderG.lineTo(16.0f, 8.0f);
        pathBuilderG.horizontalLineToRelative(-2.5f);
        pathBuilderG.lineTo(12.0f, 6.5f);
        pathBuilderG.lineTo(10.5f, 8.0f);
        pathBuilderG.lineTo(8.0f, 8.0f);
        pathBuilderG.verticalLineToRelative(2.5f);
        pathBuilderG.lineTo(6.5f, 12.0f);
        b.y(pathBuilderG, 8.0f, 13.5f, 8.0f, 16.0f);
        pathBuilderG.moveTo(12.0f, 9.0f);
        pathBuilderG.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderG.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        c.z(pathBuilderG, 12.0f, 9.0f, 21.0f, 3.0f);
        pathBuilderG.lineTo(3.0f, 3.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(14.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(18.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(23.0f, 5.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderG, 21.0f, 19.01f, 3.0f, 19.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderG, 3.0f, 4.99f, 18.0f, 14.02f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsBrightness = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.filled;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsInputAntenna", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsInputAntenna", "Landroidx/compose/material/icons/Icons$Filled;", "getSettingsInputAntenna", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsInputAntennaKt {
    private static ImageVector _settingsInputAntenna;

    public static final ImageVector getSettingsInputAntenna(Icons.Filled filled) {
        ImageVector imageVector = _settingsInputAntenna;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SettingsInputAntenna", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 5.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, 3.13f, -7.0f, 7.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, 2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 14.29f);
        pathBuilderA.curveToRelative(0.88f, -0.39f, 1.5f, -1.26f, 1.5f, -2.29f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA.reflectiveCurveTo(9.5f, 10.62f, 9.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.02f, 0.62f, 1.9f, 1.5f, 2.29f);
        pathBuilderA.verticalLineToRelative(3.3f);
        pathBuilderA.lineTo(7.59f, 21.0f);
        pathBuilderA.lineTo(9.0f, 22.41f);
        pathBuilderA.lineToRelative(3.0f, -3.0f);
        pathBuilderA.lineToRelative(3.0f, 3.0f);
        pathBuilderA.lineTo(16.41f, 21.0f);
        d.f(pathBuilderA, 13.0f, 17.59f, -3.3f);
        pathBuilderA.moveTo(12.0f, 1.0f);
        pathBuilderA.curveTo(5.93f, 1.0f, 1.0f, 5.93f, 1.0f, 12.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, 4.03f, -9.0f, 9.0f, -9.0f);
        pathBuilderA.reflectiveCurveToRelative(9.0f, 4.03f, 9.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -6.07f, -4.93f, -11.0f, -11.0f, -11.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsInputAntenna = imageVectorBuild;
        return imageVectorBuild;
    }
}

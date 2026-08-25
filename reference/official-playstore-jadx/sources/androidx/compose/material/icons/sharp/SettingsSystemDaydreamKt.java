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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsSystemDaydream", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsSystemDaydream", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettingsSystemDaydream", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsSystemDaydreamKt {
    private static ImageVector _settingsSystemDaydream;

    public static final ImageVector getSettingsSystemDaydream(Icons.Sharp sharp) {
        ImageVector imageVector = _settingsSystemDaydream;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SettingsSystemDaydream", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(9.0f, 16.0f, 6.5f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveTo(16.88f, 11.0f, 15.5f, 11.0f);
        pathBuilderA.horizontalLineToRelative(-0.05f);
        pathBuilderA.curveToRelative(-0.24f, -1.69f, -1.69f, -3.0f, -3.45f, -3.0f);
        pathBuilderA.curveToRelative(-1.4f, 0.0f, -2.6f, 0.83f, -3.16f, 2.02f);
        pathBuilderA.horizontalLineToRelative(-0.16f);
        pathBuilderA.curveTo(7.17f, 10.18f, 6.0f, 11.45f, 6.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        a.p(pathBuilderA, 23.0f, 3.0f, 1.0f, 3.0f);
        w.a.z(pathBuilderA, 18.0f, 22.0f, 23.0f, 3.0f);
        pathBuilderA.moveTo(21.0f, 19.01f);
        pathBuilderA.lineTo(3.0f, 19.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderA, 3.0f, 4.99f, 18.0f, 14.02f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsSystemDaydream = imageVectorBuild;
        return imageVectorBuild;
    }
}

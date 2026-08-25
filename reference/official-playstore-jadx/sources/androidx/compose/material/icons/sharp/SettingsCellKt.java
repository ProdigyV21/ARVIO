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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsCell", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsCell", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettingsCell", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsCellKt {
    private static ImageVector _settingsCell;

    public static final ImageVector getSettingsCell(Icons.Sharp sharp) {
        ImageVector imageVector = _settingsCell;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SettingsCell", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(7.0f, 24.0f, 2.0f, -2.0f);
        d.f(pathBuilderA, 7.0f, 22.0f, 2.0f);
        a.C(pathBuilderA, 11.0f, 24.0f, 2.0f, -2.0f);
        b.B(pathBuilderA, -2.0f, 2.0f, 15.0f, 24.0f);
        y.a.r(pathBuilderA, 2.0f, -2.0f, -2.0f, 2.0f);
        c.m(pathBuilderA, 6.0f, 0.0f, 20.0f, 12.0f);
        b.y(pathBuilderA, 18.0f, 0.0f, 6.0f, 0.0f);
        pathBuilderA.moveTo(16.0f, 16.0f);
        pathBuilderA.lineTo(8.0f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderA, 8.0f, 4.0f, 8.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsCell = imageVectorBuild;
        return imageVectorBuild;
    }
}

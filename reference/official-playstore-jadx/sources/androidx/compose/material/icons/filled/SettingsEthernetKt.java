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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsEthernet", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsEthernet", "Landroidx/compose/material/icons/Icons$Filled;", "getSettingsEthernet", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsEthernetKt {
    private static ImageVector _settingsEthernet;

    public static final ImageVector getSettingsEthernet(Icons.Filled filled) {
        ImageVector imageVector = _settingsEthernet;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SettingsEthernet", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.77f, 6.76f, 6.23f, 5.48f);
        pathBuilderJ.lineTo(0.82f, 12.0f);
        pathBuilderJ.lineToRelative(5.41f, 6.52f);
        pathBuilderJ.lineToRelative(1.54f, -1.28f);
        d.l(pathBuilderJ, 3.42f, 12.0f, 4.35f, -5.24f);
        a.C(pathBuilderJ, 7.0f, 13.0f, 2.0f, -2.0f);
        d.f(pathBuilderJ, 7.0f, 11.0f, 2.0f);
        a.C(pathBuilderJ, 17.0f, 11.0f, -2.0f, 2.0f);
        b.B(pathBuilderJ, 2.0f, -2.0f, 11.0f, 13.0f);
        y.a.r(pathBuilderJ, 2.0f, -2.0f, -2.0f, 2.0f);
        pathBuilderJ.moveTo(17.77f, 5.48f);
        pathBuilderJ.lineToRelative(-1.54f, 1.28f);
        pathBuilderJ.lineTo(20.58f, 12.0f);
        pathBuilderJ.lineToRelative(-4.35f, 5.24f);
        pathBuilderJ.lineToRelative(1.54f, 1.28f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 23.18f, 12.0f, -5.41f, -6.52f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsEthernet = imageVectorBuild;
        return imageVectorBuild;
    }
}

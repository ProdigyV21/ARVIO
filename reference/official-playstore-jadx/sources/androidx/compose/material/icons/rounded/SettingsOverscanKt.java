package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsOverscan", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsOverscan", "Landroidx/compose/material/icons/Icons$Rounded;", "getSettingsOverscan", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsOverscanKt {
    private static ImageVector _settingsOverscan;

    public static final ImageVector getSettingsOverscan(Icons.Rounded rounded) {
        ImageVector imageVector = _settingsOverscan;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SettingsOverscan", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(12.01f, 7.0f, 10.0f, 9.0f, 4.0f);
        c.z(pathBuilderT, 12.01f, 7.0f, 17.0f, 10.0f);
        pathBuilderT.verticalLineToRelative(4.0f);
        d.C(pathBuilderT, 2.0f, -1.99f, 17.0f, 10.0f);
        pathBuilderT.moveTo(7.0f, 10.0f);
        pathBuilderT.lineToRelative(-2.0f, 2.01f);
        b.s(pathBuilderT, 7.0f, 14.0f, 10.0f);
        pathBuilderT.moveTo(14.0f, 15.0f);
        pathBuilderT.horizontalLineToRelative(-4.0f);
        d.C(pathBuilderT, 2.01f, 2.0f, 14.0f, 15.0f);
        pathBuilderT.moveTo(20.0f, 4.0f);
        pathBuilderT.horizontalLineTo(4.0f);
        pathBuilderT.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderT.verticalLineToRelative(12.0f);
        pathBuilderT.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderT.horizontalLineToRelative(16.0f);
        pathBuilderT.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderT.verticalLineTo(6.0f);
        pathBuilderT.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderT, 20.0f, 18.01f, 4.0f, 5.99f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderT, 16.0f, 18.01f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsOverscan = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightnessAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BrightnessAuto", "Landroidx/compose/material/icons/Icons$Sharp;", "getBrightnessAuto", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrightnessAutoKt {
    private static ImageVector _brightnessAuto;

    public static final ImageVector getBrightnessAuto(Icons.Sharp sharp) {
        ImageVector imageVector = _brightnessAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.BrightnessAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(10.85f, 12.65f, 2.3f, 12.0f, 9.0f);
        b.C(pathBuilderI, -1.15f, 3.65f, 20.0f, 8.69f);
        pathBuilderI.verticalLineTo(4.0f);
        pathBuilderI.horizontalLineToRelative(-4.69f);
        pathBuilderI.lineTo(12.0f, 0.69f);
        b.m(pathBuilderI, 8.69f, 4.0f, 4.0f, 4.69f);
        pathBuilderI.lineTo(0.69f, 12.0f);
        b.n(pathBuilderI, 4.0f, 15.31f, 20.0f, 4.69f);
        pathBuilderI.lineTo(12.0f, 23.31f);
        b.m(pathBuilderI, 15.31f, 20.0f, 20.0f, -4.69f);
        b.y(pathBuilderI, 23.31f, 12.0f, 20.0f, 8.69f);
        pathBuilderI.moveTo(14.3f, 16.0f);
        pathBuilderI.lineToRelative(-0.7f, -2.0f);
        a.n(pathBuilderI, -3.2f, -0.7f, 2.0f, 7.8f);
        pathBuilderI.lineTo(11.0f, 7.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.lineToRelative(3.2f, 9.0f);
        pathBuilderI.horizontalLineToRelative(-1.9f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightnessAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}

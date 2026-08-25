package androidx.compose.material.icons.outlined;

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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightnessAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BrightnessAuto", "Landroidx/compose/material/icons/Icons$Outlined;", "getBrightnessAuto", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrightnessAutoKt {
    private static ImageVector _brightnessAuto;

    public static final ImageVector getBrightnessAuto(Icons.Outlined outlined) {
        ImageVector imageVector = _brightnessAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.BrightnessAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(11.0f, 7.0f, -3.2f, 9.0f, 1.9f);
        pathBuilderC.lineToRelative(0.7f, -2.0f);
        e.C(pathBuilderC, 3.2f, 0.7f, 2.0f, 1.9f);
        f.n(pathBuilderC, 13.0f, 7.0f, -2.0f);
        pathBuilderC.moveTo(10.85f, 12.65f);
        pathBuilderC.lineTo(12.0f, 9.0f);
        a.k(pathBuilderC, 1.15f, 3.65f, -2.3f);
        pathBuilderC.moveTo(20.0f, 8.69f);
        pathBuilderC.lineTo(20.0f, 4.0f);
        pathBuilderC.horizontalLineToRelative(-4.69f);
        pathBuilderC.lineTo(12.0f, 0.69f);
        pathBuilderC.lineTo(8.69f, 4.0f);
        pathBuilderC.lineTo(4.0f, 4.0f);
        pathBuilderC.verticalLineToRelative(4.69f);
        pathBuilderC.lineTo(0.69f, 12.0f);
        pathBuilderC.lineTo(4.0f, 15.31f);
        pathBuilderC.lineTo(4.0f, 20.0f);
        pathBuilderC.horizontalLineToRelative(4.69f);
        pathBuilderC.lineTo(12.0f, 23.31f);
        pathBuilderC.lineTo(15.31f, 20.0f);
        pathBuilderC.lineTo(20.0f, 20.0f);
        pathBuilderC.verticalLineToRelative(-4.69f);
        b.y(pathBuilderC, 23.31f, 12.0f, 20.0f, 8.69f);
        pathBuilderC.moveTo(18.0f, 14.48f);
        pathBuilderC.lineTo(18.0f, 18.0f);
        pathBuilderC.horizontalLineToRelative(-3.52f);
        pathBuilderC.lineTo(12.0f, 20.48f);
        pathBuilderC.lineTo(9.52f, 18.0f);
        pathBuilderC.lineTo(6.0f, 18.0f);
        pathBuilderC.verticalLineToRelative(-3.52f);
        pathBuilderC.lineTo(3.52f, 12.0f);
        pathBuilderC.lineTo(6.0f, 9.52f);
        pathBuilderC.lineTo(6.0f, 6.0f);
        pathBuilderC.horizontalLineToRelative(3.52f);
        pathBuilderC.lineTo(12.0f, 3.52f);
        pathBuilderC.lineTo(14.48f, 6.0f);
        pathBuilderC.lineTo(18.0f, 6.0f);
        pathBuilderC.verticalLineToRelative(3.52f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderC, 20.48f, 12.0f, 18.0f, 14.48f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightnessAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightness7", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Brightness7", "Landroidx/compose/material/icons/Icons$Sharp;", "getBrightness7", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Brightness7Kt {
    private static ImageVector _brightness7;

    public static final ImageVector getBrightness7(Icons.Sharp sharp) {
        ImageVector imageVector = _brightness7;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Brightness7", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(20.0f, 8.69f, 20.0f, 4.0f, -4.69f);
        pathBuilderT.lineTo(12.0f, 0.69f);
        pathBuilderT.lineTo(8.69f, 4.0f);
        pathBuilderT.lineTo(4.0f, 4.0f);
        pathBuilderT.verticalLineToRelative(4.69f);
        pathBuilderT.lineTo(0.69f, 12.0f);
        pathBuilderT.lineTo(4.0f, 15.31f);
        pathBuilderT.lineTo(4.0f, 20.0f);
        pathBuilderT.horizontalLineToRelative(4.69f);
        pathBuilderT.lineTo(12.0f, 23.31f);
        pathBuilderT.lineTo(15.31f, 20.0f);
        pathBuilderT.lineTo(20.0f, 20.0f);
        pathBuilderT.verticalLineToRelative(-4.69f);
        b.y(pathBuilderT, 23.31f, 12.0f, 20.0f, 8.69f);
        pathBuilderT.moveTo(12.0f, 18.0f);
        pathBuilderT.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderT.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderT.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderT.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderT.close();
        pathBuilderT.moveTo(12.0f, 8.0f);
        pathBuilderT.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderT.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderT.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderT, -1.79f, -4.0f, -4.0f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightness7 = imageVectorBuild;
        return imageVectorBuild;
    }
}

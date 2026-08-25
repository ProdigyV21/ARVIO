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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_numbers", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Numbers", "Landroidx/compose/material/icons/Icons$Rounded;", "getNumbers", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NumbersKt {
    private static ImageVector _numbers;

    public static final ImageVector getNumbers(Icons.Rounded rounded) {
        ImageVector imageVector = _numbers;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Numbers", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.68f, 9.27f, 0.01f, -0.06f);
        pathBuilderR.curveTo(20.85f, 8.59f, 20.39f, 8.0f, 19.76f, 8.0f);
        pathBuilderR.horizontalLineTo(17.0f);
        pathBuilderR.lineToRelative(0.7f, -2.79f);
        pathBuilderR.curveTo(17.85f, 4.59f, 17.39f, 4.0f, 16.76f, 4.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.83f, 0.3f, -0.94f, 0.73f);
        pathBuilderR.lineTo(15.0f, 8.0f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.lineToRelative(0.7f, -2.79f);
        pathBuilderR.curveTo(11.85f, 4.59f, 11.39f, 4.0f, 10.76f, 4.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.83f, 0.3f, -0.94f, 0.73f);
        pathBuilderR.lineTo(9.0f, 8.0f);
        pathBuilderR.horizontalLineTo(5.76f);
        pathBuilderR.curveTo(5.31f, 8.0f, 4.92f, 8.3f, 4.82f, 8.73f);
        pathBuilderR.lineTo(4.8f, 8.79f);
        pathBuilderR.curveTo(4.65f, 9.41f, 5.11f, 10.0f, 5.74f, 10.0f);
        b.j(pathBuilderR, 8.5f, -1.0f, 4.0f, 4.26f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.83f, 0.3f, -0.94f, 0.73f);
        pathBuilderR.lineTo(3.3f, 14.79f);
        pathBuilderR.curveTo(3.15f, 15.41f, 3.61f, 16.0f, 4.24f, 16.0f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.lineToRelative(-0.7f, 2.79f);
        pathBuilderR.curveTo(6.15f, 19.41f, 6.61f, 20.0f, 7.24f, 20.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.83f, -0.3f, 0.94f, -0.73f);
        pathBuilderR.lineTo(9.0f, 16.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.lineToRelative(-0.7f, 2.79f);
        pathBuilderR.curveTo(12.15f, 19.41f, 12.61f, 20.0f, 13.24f, 20.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.83f, -0.3f, 0.94f, -0.73f);
        pathBuilderR.lineTo(15.0f, 16.0f);
        pathBuilderR.horizontalLineToRelative(3.24f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.83f, -0.3f, 0.94f, -0.73f);
        pathBuilderR.lineToRelative(0.01f, -0.06f);
        pathBuilderR.curveToRelative(0.15f, -0.61f, -0.31f, -1.21f, -0.94f, -1.21f);
        b.D(pathBuilderR, 15.5f, 1.0f, -4.0f, 3.24f);
        pathBuilderR.curveTo(20.19f, 10.0f, 20.58f, 9.7f, 20.68f, 9.27f);
        a.g(pathBuilderR, 13.5f, 14.0f, -4.0f);
        pathBuilderR.lineToRelative(1.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderR, 4.0f, 13.5f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _numbers = imageVectorBuild;
        return imageVectorBuild;
    }
}

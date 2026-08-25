package androidx.compose.material.icons.rounded;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hourglassBottom", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HourglassBottom", "Landroidx/compose/material/icons/Icons$Rounded;", "getHourglassBottom", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HourglassBottomKt {
    private static ImageVector _hourglassBottom;

    public static final ImageVector getHourglassBottom(Icons.Rounded rounded) {
        ImageVector imageVector = _hourglassBottom;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HourglassBottom", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 22.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineToRelative(-0.01f, -3.18f);
        pathBuilderA.curveToRelative(0.0f, -0.53f, -0.21f, -1.03f, -0.58f, -1.41f);
        pathBuilderA.lineTo(14.0f, 12.0f);
        pathBuilderA.lineToRelative(3.41f, -3.43f);
        pathBuilderA.curveToRelative(0.37f, -0.37f, 0.58f, -0.88f, 0.58f, -1.41f);
        pathBuilderA.lineTo(18.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(6.9f, 2.0f, 6.0f, 2.9f, 6.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(3.16f);
        pathBuilderA.curveTo(6.0f, 7.69f, 6.21f, 8.2f, 6.58f, 8.58f);
        pathBuilderA.lineTo(10.0f, 12.0f);
        pathBuilderA.lineToRelative(-3.41f, 3.4f);
        pathBuilderA.curveTo(6.21f, 15.78f, 6.0f, 16.29f, 6.0f, 16.82f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.t(pathBuilderA, 16.0f, 8.0f, 7.09f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(2.09f);
        pathBuilderA.curveToRelative(0.0f, 0.27f, -0.11f, 0.52f, -0.29f, 0.71f);
        pathBuilderA.lineTo(12.0f, 11.5f);
        pathBuilderA.lineTo(8.29f, 7.79f);
        pathBuilderA.curveTo(8.11f, 7.61f, 8.0f, 7.35f, 8.0f, 7.09f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hourglassBottom = imageVectorBuild;
        return imageVectorBuild;
    }
}

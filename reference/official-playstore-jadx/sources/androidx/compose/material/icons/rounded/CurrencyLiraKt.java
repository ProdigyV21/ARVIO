package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_currencyLira", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CurrencyLira", "Landroidx/compose/material/icons/Icons$Rounded;", "getCurrencyLira", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CurrencyLiraKt {
    private static ImageVector _currencyLira;

    public static final ImageVector getCurrencyLira(Icons.Rounded rounded) {
        ImageVector imageVector = _currencyLira;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CurrencyLira", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.0f, 15.84f, -1.47f, 0.92f);
        pathBuilderR.curveTo(6.86f, 17.18f, 6.0f, 16.7f, 6.0f, 15.91f);
        pathBuilderR.curveToRelative(0.0f, -0.34f, 0.18f, -0.66f, 0.47f, -0.85f);
        pathBuilderR.lineTo(9.0f, 13.48f);
        pathBuilderR.verticalLineToRelative(-2.36f);
        pathBuilderR.lineToRelative(-1.47f, 0.92f);
        pathBuilderR.curveTo(6.86f, 12.46f, 6.0f, 11.98f, 6.0f, 11.19f);
        pathBuilderR.curveToRelative(0.0f, -0.34f, 0.18f, -0.66f, 0.47f, -0.85f);
        pathBuilderR.lineTo(9.0f, 8.76f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(3.51f);
        pathBuilderR.lineToRelative(2.47f, -1.55f);
        pathBuilderR.curveTo(14.14f, 5.54f, 15.0f, 6.02f, 15.0f, 6.81f);
        pathBuilderR.curveToRelative(0.0f, 0.34f, -0.18f, 0.66f, -0.47f, 0.85f);
        pathBuilderR.lineTo(11.0f, 9.87f);
        pathBuilderR.lineToRelative(0.01f, 2.35f);
        pathBuilderR.lineToRelative(2.46f, -1.54f);
        pathBuilderR.curveToRelative(0.67f, -0.42f, 1.53f, 0.06f, 1.53f, 0.85f);
        pathBuilderR.curveToRelative(0.0f, 0.34f, -0.18f, 0.66f, -0.47f, 0.85f);
        pathBuilderR.lineTo(11.0f, 14.59f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(2.47f, 0.0f, 4.52f, -1.79f, 4.93f, -4.15f);
        pathBuilderR.curveToRelative(0.08f, -0.49f, 0.49f, -0.85f, 0.98f, -0.85f);
        pathBuilderR.curveToRelative(0.61f, 0.0f, 1.09f, 0.54f, 1.0f, 1.14f);
        pathBuilderR.curveTo(17.37f, 18.46f, 14.48f, 21.0f, 11.0f, 21.0f);
        pathBuilderR.horizontalLineToRelative(-1.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderR, 15.84f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _currencyLira = imageVectorBuild;
        return imageVectorBuild;
    }
}

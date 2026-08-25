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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_currencyExchange", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CurrencyExchange", "Landroidx/compose/material/icons/Icons$Rounded;", "getCurrencyExchange", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CurrencyExchangeKt {
    private static ImageVector _currencyExchange;

    public static final ImageVector getCurrencyExchange(Icons.Rounded rounded) {
        ImageVector imageVector = _currencyExchange;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CurrencyExchange", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 23.0f);
        pathBuilderA.curveToRelative(5.7f, 0.0f, 10.39f, -4.34f, 10.95f, -9.9f);
        pathBuilderA.curveToRelative(0.06f, -0.59f, -0.41f, -1.1f, -1.0f, -1.1f);
        pathBuilderA.curveToRelative(-0.51f, 0.0f, -0.94f, 0.38f, -0.99f, 0.88f);
        pathBuilderA.curveTo(20.52f, 17.44f, 16.67f, 21.0f, 12.0f, 21.0f);
        pathBuilderA.curveToRelative(-3.12f, 0.0f, -5.87f, -1.59f, -7.48f, -4.0f);
        pathBuilderA.lineTo(6.0f, 17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.lineToRelative(0.0f, -1.67f);
        pathBuilderA.curveTo(4.99f, 21.15f, 8.28f, 23.0f, 12.0f, 23.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 1.0f);
        pathBuilderA.curveTo(6.3f, 1.0f, 1.61f, 5.34f, 1.05f, 10.9f);
        pathBuilderA.curveTo(1.0f, 11.49f, 1.46f, 12.0f, 2.05f, 12.0f);
        pathBuilderA.curveToRelative(0.51f, 0.0f, 0.94f, -0.38f, 0.99f, -0.88f);
        pathBuilderA.curveTo(3.48f, 6.56f, 7.33f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.curveToRelative(3.12f, 0.0f, 5.87f, 1.59f, 7.48f, 4.0f);
        pathBuilderA.lineTo(18.0f, 7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.lineToRelative(0.0f, 1.67f);
        pathBuilderA.curveTo(19.01f, 2.85f, 15.72f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.12f, 5.88f);
        pathBuilderA.curveTo(11.12f, 5.39f, 11.52f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(0.88f, 0.39f, 0.88f, 0.88f);
        pathBuilderA.lineToRelative(0.0f, 0.37f);
        pathBuilderA.curveToRelative(1.07f, 0.19f, 1.75f, 0.76f, 2.16f, 1.3f);
        pathBuilderA.curveToRelative(0.34f, 0.44f, 0.16f, 1.08f, -0.36f, 1.3f);
        pathBuilderA.curveTo(14.32f, 9.0f, 13.9f, 8.88f, 13.66f, 8.57f);
        pathBuilderA.curveToRelative(-0.28f, -0.38f, -0.78f, -0.77f, -1.6f, -0.77f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.81f, 0.37f, -1.81f, 1.39f);
        pathBuilderA.curveToRelative(0.0f, 0.95f, 0.86f, 1.31f, 2.64f, 1.9f);
        pathBuilderA.curveToRelative(2.4f, 0.83f, 3.01f, 2.05f, 3.01f, 3.45f);
        pathBuilderA.curveToRelative(0.0f, 2.62f, -2.5f, 3.13f, -3.02f, 3.22f);
        pathBuilderA.lineToRelative(0.0f, 0.37f);
        pathBuilderA.curveToRelative(0.0f, 0.48f, -0.39f, 0.88f, -0.88f, 0.88f);
        pathBuilderA.reflectiveCurveToRelative(-0.88f, -0.39f, -0.88f, -0.88f);
        pathBuilderA.lineToRelative(0.0f, -0.42f);
        pathBuilderA.curveToRelative(-0.63f, -0.15f, -1.93f, -0.61f, -2.69f, -2.1f);
        pathBuilderA.curveToRelative(-0.23f, -0.44f, 0.03f, -1.02f, 0.49f, -1.2f);
        pathBuilderA.curveToRelative(0.41f, -0.16f, 0.9f, -0.01f, 1.11f, 0.38f);
        pathBuilderA.curveToRelative(0.32f, 0.61f, 0.95f, 1.37f, 2.12f, 1.37f);
        pathBuilderA.curveToRelative(0.93f, 0.0f, 1.98f, -0.48f, 1.98f, -1.61f);
        pathBuilderA.curveToRelative(0.0f, -0.96f, -0.7f, -1.46f, -2.28f, -2.03f);
        pathBuilderA.curveToRelative(-1.1f, -0.39f, -3.35f, -1.03f, -3.35f, -3.31f);
        pathBuilderA.curveToRelative(0.0f, -0.1f, 0.01f, -2.4f, 2.62f, -2.96f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 11.12f, 5.88f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _currencyExchange = imageVectorBuild;
        return imageVectorBuild;
    }
}

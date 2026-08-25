package androidx.compose.material.icons.sharp;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shoppingCartCheckout", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShoppingCartCheckout", "Landroidx/compose/material/icons/Icons$Sharp;", "getShoppingCartCheckout", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShoppingCartCheckoutKt {
    private static ImageVector _shoppingCartCheckout;

    public static final ImageVector getShoppingCartCheckout(Icons.Sharp sharp) {
        ImageVector imageVector = _shoppingCartCheckout;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ShoppingCartCheckout", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.0f, 18.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderA.reflectiveCurveTo(5.9f, 22.0f, 7.0f, 22.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(8.1f, 18.0f, 7.0f, 18.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 18.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(18.1f, 18.0f, 17.0f, 18.0f);
        a.g(pathBuilderA, 8.1f, 13.0f, 8.66f);
        pathBuilderA.lineTo(21.0f, 4.96f);
        pathBuilderA.lineTo(19.25f, 4.0f);
        pathBuilderA.lineToRelative(-3.7f, 7.0f);
        b.q(pathBuilderA, 8.53f, 4.27f, 2.0f, 1.0f);
        d.D(pathBuilderA, 2.0f, 2.0f, 3.6f, 7.59f);
        v.b.m(pathBuilderA, 3.61f, 17.0f, 19.0f, -2.0f);
        w.a.w(pathBuilderA, 7.0f, 8.1f, 13.0f);
        pathBuilderA.moveTo(12.0f, 2.0f);
        pathBuilderA.lineToRelative(4.0f, 4.0f);
        pathBuilderA.lineToRelative(-4.0f, 4.0f);
        pathBuilderA.lineToRelative(-1.41f, -1.41f);
        pathBuilderA.lineTo(12.17f, 7.0f);
        pathBuilderA.lineTo(8.0f, 7.0f);
        pathBuilderA.lineToRelative(0.0f, -2.0f);
        pathBuilderA.lineToRelative(4.17f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderA, -1.59f, -1.59f, 12.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shoppingCartCheckout = imageVectorBuild;
        return imageVectorBuild;
    }
}

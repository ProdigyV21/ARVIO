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
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addShoppingCart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddShoppingCart", "Landroidx/compose/material/icons/Icons$Outlined;", "getAddShoppingCart", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddShoppingCartKt {
    private static ImageVector _addShoppingCart;

    public static final ImageVector getAddShoppingCart(Icons.Outlined outlined) {
        ImageVector imageVector = _addShoppingCart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AddShoppingCart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(11.0f, 9.0f, 2.0f, 13.0f, 6.0f);
        c.A(pathBuilderI, 3.0f, 16.0f, 4.0f, -3.0f);
        b.A(pathBuilderI, 13.0f, 1.0f, -2.0f, 3.0f);
        a.h(pathBuilderI, 8.0f, 4.0f, 2.0f, 3.0f);
        b.f(pathBuilderI, 3.0f, 7.0f, 18.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderI.reflectiveCurveTo(5.9f, 22.0f, 7.0f, 22.0f);
        pathBuilderI.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(17.0f, 18.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderI.reflectiveCurveToRelative(0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderI.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        a.g(pathBuilderI, 8.1f, 13.0f, 7.45f);
        pathBuilderI.curveToRelative(0.75f, 0.0f, 1.41f, -0.41f, 1.75f, -1.03f);
        pathBuilderI.lineToRelative(3.86f, -7.01f);
        pathBuilderI.lineTo(19.42f, 4.0f);
        pathBuilderI.lineToRelative(-3.87f, 7.0f);
        pathBuilderI.lineTo(8.53f, 11.0f);
        pathBuilderI.lineTo(4.27f, 2.0f);
        a.h(pathBuilderI, 1.0f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.lineToRelative(3.6f, 7.59f);
        pathBuilderI.lineToRelative(-1.35f, 2.44f);
        pathBuilderI.curveTo(4.52f, 15.37f, 5.48f, 17.0f, 7.0f, 17.0f);
        c.q(pathBuilderI, 12.0f, -2.0f, 7.0f, 15.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderI, 1.1f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addShoppingCart = imageVectorBuild;
        return imageVectorBuild;
    }
}

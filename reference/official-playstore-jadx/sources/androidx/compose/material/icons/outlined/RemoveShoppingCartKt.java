package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_removeShoppingCart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RemoveShoppingCart", "Landroidx/compose/material/icons/Icons$Outlined;", "getRemoveShoppingCart", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RemoveShoppingCartKt {
    private static ImageVector _removeShoppingCart;

    public static final ImageVector getRemoveShoppingCart(Icons.Outlined outlined) {
        ImageVector imageVector = _removeShoppingCart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RemoveShoppingCart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(1.41f, 1.13f, 0.0f, 2.54f);
        pathBuilderJ.lineToRelative(4.39f, 4.39f);
        pathBuilderJ.lineToRelative(2.21f, 4.66f);
        pathBuilderJ.lineToRelative(-1.35f, 2.45f);
        pathBuilderJ.curveToRelative(-0.16f, 0.28f, -0.25f, 0.61f, -0.25f, 0.96f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(7.46f);
        pathBuilderJ.lineToRelative(1.38f, 1.38f);
        pathBuilderJ.curveToRelative(-0.5f, 0.36f, -0.83f, 0.95f, -0.83f, 1.62f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderJ.curveToRelative(0.67f, 0.0f, 1.26f, -0.33f, 1.62f, -0.84f);
        pathBuilderJ.lineTo(21.46f, 24.0f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 1.41f, 1.13f);
        pathBuilderJ.moveTo(7.0f, 15.0f);
        pathBuilderJ.lineToRelative(1.1f, -2.0f);
        b.B(pathBuilderJ, 2.36f, 2.0f, 2.0f, 7.0f);
        pathBuilderJ.moveTo(20.0f, 4.0f);
        b.D(pathBuilderJ, 7.12f, 2.0f, 2.0f, 9.19f);
        pathBuilderJ.lineToRelative(-2.76f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(-1.44f);
        pathBuilderJ.lineToRelative(1.94f, 1.94f);
        pathBuilderJ.curveToRelative(0.54f, -0.14f, 0.99f, -0.49f, 1.25f, -0.97f);
        pathBuilderJ.lineToRelative(3.58f, -6.49f);
        pathBuilderJ.curveTo(21.25f, 4.82f, 20.76f, 4.0f, 20.0f, 4.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(7.0f, 18.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.reflectiveCurveTo(5.9f, 22.0f, 7.0f, 22.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -0.9f, -2.0f, -2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _removeShoppingCart = imageVectorBuild;
        return imageVectorBuild;
    }
}

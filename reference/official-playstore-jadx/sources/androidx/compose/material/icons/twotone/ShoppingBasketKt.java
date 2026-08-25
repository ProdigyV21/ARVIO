package androidx.compose.material.icons.twotone;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shoppingBasket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShoppingBasket", "Landroidx/compose/material/icons/Icons$TwoTone;", "getShoppingBasket", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShoppingBasketKt {
    private static ImageVector _shoppingBasket;

    public static final ImageVector getShoppingBasket(Icons.TwoTone twoTone) {
        ImageVector imageVector = _shoppingBasket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ShoppingBasket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(3.31f, 11.0f, 2.2f, 8.01f);
        pathBuilderR.lineTo(18.5f, 19.0f);
        f.v(pathBuilderR, 2.2f, -8.0f, 3.31f);
        pathBuilderR.moveTo(12.0f, 17.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, -0.9f, 2.0f, -2.0f, 2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(22.0f, 9.0f, -4.79f, -4.38f, -6.56f);
        pathBuilderG.curveToRelative(-0.19f, -0.28f, -0.51f, -0.42f, -0.83f, -0.42f);
        pathBuilderG.reflectiveCurveToRelative(-0.64f, 0.14f, -0.83f, 0.43f);
        pathBuilderG.lineTo(6.79f, 9.0f);
        pathBuilderG.horizontalLineTo(2.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderG.curveToRelative(0.0f, 0.09f, 0.01f, 0.18f, 0.04f, 0.27f);
        pathBuilderG.lineToRelative(2.54f, 9.27f);
        pathBuilderG.curveToRelative(0.23f, 0.84f, 1.0f, 1.46f, 1.92f, 1.46f);
        pathBuilderG.horizontalLineToRelative(13.0f);
        pathBuilderG.curveToRelative(0.92f, 0.0f, 1.69f, -0.62f, 1.93f, -1.46f);
        pathBuilderG.lineToRelative(2.54f, -9.27f);
        pathBuilderG.lineTo(23.0f, 10.0f);
        pathBuilderG.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.p(pathBuilderG, 12.0f, 4.8f, 14.8f, 9.0f);
        w.a.w(pathBuilderG, 9.2f, 12.0f, 4.8f);
        pathBuilderG.moveTo(18.5f, 19.0f);
        pathBuilderG.lineToRelative(-12.99f, 0.01f);
        pathBuilderG.lineTo(3.31f, 11.0f);
        pathBuilderG.horizontalLineTo(20.7f);
        pathBuilderG.lineToRelative(-2.2f, 8.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(12.0f, 13.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderG, -0.9f, -2.0f, -2.0f, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shoppingBasket = imageVectorBuild;
        return imageVectorBuild;
    }
}

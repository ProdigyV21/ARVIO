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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_restaurantMenu", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RestaurantMenu", "Landroidx/compose/material/icons/Icons$Sharp;", "getRestaurantMenu", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RestaurantMenuKt {
    private static ImageVector _restaurantMenu;

    public static final ImageVector getRestaurantMenu(Icons.Sharp sharp) {
        ImageVector imageVector = _restaurantMenu;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.RestaurantMenu", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.1f, 13.34f, 2.83f, -2.83f);
        pathBuilderR.lineTo(3.91f, 3.5f);
        pathBuilderR.curveToRelative(-1.56f, 1.56f, -1.56f, 4.09f, 0.0f, 5.66f);
        b.C(pathBuilderR, 4.19f, 4.18f, 14.88f, 11.53f);
        pathBuilderR.curveToRelative(1.53f, 0.71f, 3.68f, 0.21f, 5.27f, -1.38f);
        pathBuilderR.curveToRelative(1.91f, -1.91f, 2.28f, -4.65f, 0.81f, -6.12f);
        pathBuilderR.curveToRelative(-1.46f, -1.46f, -4.2f, -1.1f, -6.12f, 0.81f);
        pathBuilderR.curveToRelative(-1.59f, 1.59f, -2.09f, 3.74f, -1.38f, 5.27f);
        pathBuilderR.lineTo(3.7f, 19.87f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineTo(12.0f, 14.41f);
        pathBuilderR.lineToRelative(6.88f, 6.88f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderR, 13.41f, 13.0f, 1.47f, -1.47f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _restaurantMenu = imageVectorBuild;
        return imageVectorBuild;
    }
}

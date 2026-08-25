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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_discount", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Discount", "Landroidx/compose/material/icons/Icons$Rounded;", "getDiscount", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DiscountKt {
    private static ImageVector _discount;

    public static final ImageVector getDiscount(Icons.Rounded rounded) {
        ImageVector imageVector = _discount;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Discount", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.79f, 21.0f, 3.0f, 11.21f, 2.0f);
        pathBuilderS.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.59f, 1.41f);
        pathBuilderS.lineToRelative(7.79f, 7.79f);
        pathBuilderS.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderS.lineToRelative(6.21f, -6.21f);
        pathBuilderS.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 12.79f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.38f, 17.41f);
        pathBuilderA.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderA.lineToRelative(6.21f, -6.21f);
        pathBuilderA.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderA.lineToRelative(-7.79f, -7.79f);
        pathBuilderA.curveTo(12.25f, 0.21f, 11.74f, 0.0f, 11.21f, 0.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 0.0f, 3.0f, 0.9f, 3.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(6.21f);
        pathBuilderA.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.59f, 1.41f);
        c.z(pathBuilderA, 11.38f, 17.41f, 7.25f, 3.0f);
        pathBuilderA.curveTo(7.94f, 3.0f, 8.5f, 3.56f, 8.5f, 4.25f);
        pathBuilderA.reflectiveCurveTo(7.94f, 5.5f, 7.25f, 5.5f);
        pathBuilderA.reflectiveCurveTo(6.0f, 4.94f, 6.0f, 4.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 6.56f, 3.0f, 7.25f, 3.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _discount = imageVectorBuild;
        return imageVectorBuild;
    }
}

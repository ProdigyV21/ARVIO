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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_priceChange", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PriceChange", "Landroidx/compose/material/icons/Icons$Outlined;", "getPriceChange", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PriceChangeKt {
    private static ImageVector _priceChange;

    public static final ImageVector getPriceChange(Icons.Outlined outlined) {
        ImageVector imageVector = _priceChange;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PriceChange", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(8.0f, 17.0f, 2.0f, -1.0f, 1.0f);
        pathBuilderK.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderK.verticalLineToRelative(-3.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a0.a.e(pathBuilderK, 8.0f, -1.0f, 4.0f, 8.0f);
        w.a.B(pathBuilderK, -2.0f, 7.0f, 8.0f, 1.0f);
        pathBuilderK.horizontalLineTo(7.0f);
        pathBuilderK.curveTo(6.45f, 8.0f, 6.0f, 8.45f, 6.0f, 9.0f);
        pathBuilderK.verticalLineToRelative(3.0f);
        pathBuilderK.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a0.a.y(pathBuilderK, 3.0f, 1.0f, 6.0f, 2.0f);
        c.D(pathBuilderK, 2.0f, 17.0f, 20.0f, 4.0f);
        pathBuilderK.horizontalLineTo(4.0f);
        pathBuilderK.curveTo(2.89f, 4.0f, 2.01f, 4.89f, 2.01f, 6.0f);
        pathBuilderK.lineTo(2.0f, 18.0f);
        pathBuilderK.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderK.horizontalLineToRelative(16.0f);
        pathBuilderK.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderK.verticalLineTo(6.0f);
        pathBuilderK.curveTo(22.0f, 4.89f, 21.11f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderK, 20.0f, 18.0f, 4.0f, 6.0f);
        c.D(pathBuilderK, 16.0f, 18.0f, 14.0f, 10.0f);
        pathBuilderK.lineToRelative(2.0f, -2.0f);
        pathBuilderK.lineToRelative(2.0f, 2.0f);
        pathBuilderK.moveTo(18.0f, 14.25f);
        pathBuilderK.lineToRelative(-2.0f, 2.0f);
        pathBuilderK.lineToRelative(-2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderK.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _priceChange = imageVectorBuild;
        return imageVectorBuild;
    }
}

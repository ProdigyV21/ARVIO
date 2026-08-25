package androidx.compose.material.icons.filled;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_borderStyle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BorderStyle", "Landroidx/compose/material/icons/Icons$Filled;", "getBorderStyle", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BorderStyleKt {
    private static ImageVector _borderStyle;

    public static final ImageVector getBorderStyle(Icons.Filled filled) {
        ImageVector imageVector = _borderStyle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BorderStyle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(15.0f, 21.0f, 2.0f, -2.0f, -2.0f);
        c.r(pathBuilderK, 2.0f, 19.0f, 21.0f, 2.0f);
        b.l(pathBuilderK, -2.0f, -2.0f, 2.0f);
        a.C(pathBuilderK, 7.0f, 21.0f, 2.0f, -2.0f);
        d.f(pathBuilderK, 7.0f, 19.0f, 2.0f);
        a.C(pathBuilderK, 11.0f, 21.0f, 2.0f, -2.0f);
        b.B(pathBuilderK, -2.0f, 2.0f, 19.0f, 17.0f);
        y.a.r(pathBuilderK, 2.0f, -2.0f, -2.0f, 2.0f);
        a.C(pathBuilderK, 19.0f, 13.0f, 2.0f, -2.0f);
        b.B(pathBuilderK, -2.0f, 2.0f, 3.0f, 3.0f);
        c.w(pathBuilderK, 18.0f, 2.0f, 5.0f, 5.0f);
        pathBuilderK.horizontalLineToRelative(16.0f);
        b.y(pathBuilderK, 21.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderK.moveTo(19.0f, 9.0f);
        c.A(pathBuilderK, 2.0f, 21.0f, 7.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderK, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _borderStyle = imageVectorBuild;
        return imageVectorBuild;
    }
}

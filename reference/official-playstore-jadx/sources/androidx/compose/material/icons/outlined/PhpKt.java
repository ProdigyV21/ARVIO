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
import v.b;
import v.c;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_php", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Php", "Landroidx/compose/material/icons/Icons$Outlined;", "getPhp", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhpKt {
    private static ImageVector _php;

    public static final ImageVector getPhp(Icons.Outlined outlined) {
        ImageVector imageVector = _php;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Php", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(13.0f, 9.0f, 1.5f, 6.0f, 13.0f);
        c.p(pathBuilderH, -2.5f, -2.0f, 15.0f, 9.5f);
        c.o(pathBuilderH, 9.0f, 11.0f, 2.0f, 2.0f);
        a.k(pathBuilderH, 9.0f, 8.0f, 10.5f, 1.0f);
        pathBuilderH.curveTo(8.0f, 12.3f, 7.3f, 13.0f, 6.5f, 13.0f);
        d.y(pathBuilderH, -2.0f, 2.0f, 3.0f, 9.0f);
        pathBuilderH.horizontalLineToRelative(3.5f);
        pathBuilderH.curveTo(7.3f, 9.0f, 8.0f, 9.7f, 8.0f, 10.5f);
        b.w(pathBuilderH, 6.5f, 10.5f, -2.0f, 1.0f);
        c.D(pathBuilderH, 2.0f, 10.5f, 21.5f, 10.5f);
        pathBuilderH.verticalLineToRelative(1.0f);
        pathBuilderH.curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f);
        a0.a.j(pathBuilderH, -2.0f, 2.0f, -1.5f, 9.0f);
        pathBuilderH.horizontalLineTo(20.0f);
        pathBuilderH.curveTo(20.8f, 9.0f, 21.5f, 9.7f, 21.5f, 10.5f);
        b.w(pathBuilderH, 20.0f, 10.5f, -2.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderH, 2.0f, 10.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _php = imageVectorBuild;
        return imageVectorBuild;
    }
}

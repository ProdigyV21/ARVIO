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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__10mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_10mp", "Landroidx/compose/material/icons/Icons$Sharp;", "get_10mp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _10mpKt {
    private static ImageVector __10mp;

    public static final ImageVector get_10mp(Icons.Sharp sharp) {
        ImageVector imageVector = __10mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._10mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(13.5f, 7.0f, 15.0f, 3.0f, -1.5f);
        a.B(pathBuilderH, 7.0f, 21.0f, 3.0f, 3.0f);
        a.t(pathBuilderH, 18.0f, 18.0f, 3.0f);
        c.B(pathBuilderH, 12.5f, 18.5f, 11.0f, 14.0f);
        a0.a.y(pathBuilderH, -1.0f, 3.0f, 8.5f, -3.0f);
        a0.a.y(pathBuilderH, -1.0f, 4.5f, 6.0f, -6.0f);
        c.D(pathBuilderH, 6.5f, 18.5f, 15.0f, 18.5f);
        d.y(pathBuilderH, -1.5f, -6.0f, 18.0f, 17.0f);
        c.D(pathBuilderH, -3.0f, 18.5f, 10.0f, 5.5f);
        d.B(pathBuilderH, 6.0f, 8.5f, 7.0f, 7.0f);
        c.n(pathBuilderH, 5.5f, 10.0f, 16.5f, 5.5f);
        pathBuilderH.verticalLineToRelative(6.0f);
        pathBuilderH.horizontalLineTo(12.0f);
        pathBuilderH.verticalLineToRelative(-6.0f);
        pathBuilderH.horizontalLineTo(16.5f);
        pathBuilderH.close();
        a.C(pathBuilderH, 15.0f, 14.0f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderH, 15.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __10mp = imageVectorBuild;
        return imageVectorBuild;
    }
}

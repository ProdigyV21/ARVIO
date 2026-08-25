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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__9kPlus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_9kPlus", "Landroidx/compose/material/icons/Icons$Sharp;", "get_9kPlus", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _9kPlusKt {
    private static ImageVector __9kPlus;

    public static final ImageVector get_9kPlus(Icons.Sharp sharp) {
        ImageVector imageVector = __9kPlus;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._9kPlus", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(7.5f, 10.0f, 1.0f, 1.5f, -1.0f);
        a.B(pathBuilderK, 10.0f, 21.0f, 3.0f, 3.0f);
        a.t(pathBuilderK, 18.0f, 18.0f, 3.0f);
        c.u(pathBuilderK, 10.0f, 9.0f, 6.0f, 6.0f);
        f.u(pathBuilderK, -1.5f, 2.5f, -1.0f, 6.0f);
        c.n(pathBuilderK, 9.0f, 10.0f, 16.0f, 15.0f);
        e.n(pathBuilderK, -1.75f, -1.75f, -2.25f, 15.0f);
        y.a.t(pathBuilderK, 11.0f, 9.0f, 1.5f, 2.25f);
        pathBuilderK.lineTo(14.25f, 9.0f);
        pathBuilderK.horizontalLineTo(16.0f);
        d.C(pathBuilderK, -2.25f, 3.0f, 16.0f, 15.0f);
        e.m(pathBuilderK, 19.0f, 12.5f, -1.5f, 14.0f);
        a0.a.y(pathBuilderK, -1.0f, -1.5f, 15.0f, -1.0f);
        w.a.C(pathBuilderK, 1.5f, 10.0f, 1.0f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderK, 19.0f, 12.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __9kPlus = imageVectorBuild;
        return imageVectorBuild;
    }
}

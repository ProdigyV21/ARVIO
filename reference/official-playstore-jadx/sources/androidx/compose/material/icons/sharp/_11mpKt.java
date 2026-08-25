package androidx.compose.material.icons.sharp;

import a0.c;
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
import v.d;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__11mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_11mp", "Landroidx/compose/material/icons/Icons$Sharp;", "get_11mp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _11mpKt {
    private static ImageVector __11mp;

    public static final ImageVector get_11mp(Icons.Sharp sharp) {
        ImageVector imageVector = __11mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._11mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(3.0f, 3.0f, 18.0f, 18.0f, 3.0f);
        a.v(pathBuilderQ, 3.0f, 8.0f, 5.5f, 3.0f);
        d.B(pathBuilderQ, 6.0f, 9.5f, 7.0f, 8.0f);
        v.a.B(pathBuilderQ, 5.5f, 12.5f, 18.5f, 11.0f);
        e.x(pathBuilderQ, 14.0f, -1.0f, 3.0f, 8.5f);
        f.u(pathBuilderQ, -3.0f, -1.0f, 4.5f, 6.0f);
        v.a.t(pathBuilderQ, -6.0f, 6.5f, 18.5f);
        v.a.C(pathBuilderQ, 13.0f, 5.5f, 3.0f, 6.0f);
        c.D(pathBuilderQ, -1.5f, 7.0f, 13.0f, 5.5f);
        v.a.C(pathBuilderQ, 18.0f, 17.0f, -3.0f, 1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderQ, -1.5f, -6.0f, 18.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(15.0f, 14.0f, 1.5f, 1.5f, -1.5f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __11mp = imageVectorBuild;
        return imageVectorBuild;
    }
}

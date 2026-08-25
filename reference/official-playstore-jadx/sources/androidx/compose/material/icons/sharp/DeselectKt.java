package androidx.compose.material.icons.sharp;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deselect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Deselect", "Landroidx/compose/material/icons/Icons$Sharp;", "getDeselect", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeselectKt {
    private static ImageVector _deselect;

    public static final ImageVector getDeselect(Icons.Sharp sharp) {
        ImageVector imageVector = _deselect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Deselect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(3.0f, 13.0f, 2.0f, -2.0f, 3.0f);
        a.D(pathBuilderH, 13.0f, 7.0f, 21.0f, 2.0f);
        d.u(pathBuilderH, -2.0f, 7.0f, 21.0f);
        a.C(pathBuilderH, 13.0f, 3.0f, -2.0f, 2.0f);
        c.D(pathBuilderH, 2.0f, 3.0f, 19.0f, 3.0f);
        b.A(pathBuilderH, 2.0f, 2.0f, 3.0f, 19.0f);
        c.u(pathBuilderH, 5.0f, 21.0f, -2.0f, 3.0f);
        e.y(pathBuilderH, 2.0f, 5.0f, 3.0f, 17.0f);
        d.z(pathBuilderH, 2.0f, -2.0f, 3.0f, 17.0f);
        a.C(pathBuilderH, 11.0f, 21.0f, 2.0f, -2.0f);
        c.D(pathBuilderH, -2.0f, 21.0f, 19.0f, 13.0f);
        f.w(pathBuilderH, 2.0f, -2.0f, -2.0f, 13.0f);
        e.m(pathBuilderH, 19.0f, 9.0f, 2.0f, 7.0f);
        c.D(pathBuilderH, -2.0f, 9.0f, 15.0f, 5.0f);
        f.A(pathBuilderH, 2.0f, 3.0f, -2.0f, 5.0f);
        pathBuilderH.moveTo(7.83f, 5.0f);
        v.b.n(pathBuilderH, 7.0f, 4.17f, 3.0f, 2.0f);
        e.y(pathBuilderH, 2.0f, 7.83f, 19.83f, 17.0f);
        v.b.n(pathBuilderH, 19.0f, 16.17f, 15.0f, 2.0f);
        e.y(pathBuilderH, 2.0f, 19.83f, 21.19f, 21.19f);
        pathBuilderH.lineTo(2.81f, 2.81f);
        pathBuilderH.lineTo(1.39f, 4.22f);
        v.b.m(pathBuilderH, 4.17f, 7.0f, 3.0f, 2.0f);
        y.a.o(pathBuilderH, 2.0f, 7.83f, 2.0f, 2.0f);
        b.l(pathBuilderH, 17.0f, 7.17f, 2.0f, 2.0f);
        v.b.q(pathBuilderH, 15.0f, 2.0f, 2.0f, -1.17f);
        d.C(pathBuilderH, 2.78f, 2.78f, 21.19f, 21.19f);
        pathBuilderH.moveTo(9.0f, 15.0f);
        pathBuilderH.verticalLineToRelative(-3.17f);
        pathBuilderH.lineTo(12.17f, 15.0f);
        pathBuilderH.horizontalLineTo(9.0f);
        pathBuilderH.close();
        e.o(pathBuilderH, 15.0f, 12.17f, 9.0f, -3.17f);
        e.h(pathBuilderH, -2.0f, -2.0f, 17.0f, 7.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderH, 15.0f, 12.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deselect = imageVectorBuild;
        return imageVectorBuild;
    }
}

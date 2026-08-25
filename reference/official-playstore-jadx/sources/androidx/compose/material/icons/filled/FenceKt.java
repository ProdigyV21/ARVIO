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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fence", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fence", "Landroidx/compose/material/icons/Icons$Filled;", "getFence", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FenceKt {
    private static ImageVector _fence;

    public static final ImageVector getFence(Icons.Filled filled) {
        ImageVector imageVector = _fence;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Fence", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 12.0f, -2.0f, -2.0f, 7.0f);
        pathBuilderQ.lineToRelative(-3.0f, -3.0f);
        pathBuilderQ.lineToRelative(-2.0f, 2.0f);
        pathBuilderQ.lineToRelative(-2.0f, -2.0f);
        pathBuilderQ.lineToRelative(-2.0f, 2.0f);
        pathBuilderQ.lineTo(8.0f, 4.0f);
        e.z(pathBuilderQ, 5.0f, 7.0f, 3.0f, 3.0f);
        f.u(pathBuilderQ, 2.0f, 2.0f, 2.0f, 3.0f);
        b.z(pathBuilderQ, 2.0f, 2.0f, 4.0f, 14.0f);
        b.z(pathBuilderQ, -4.0f, 2.0f, -2.0f, -2.0f);
        e.y(pathBuilderQ, -2.0f, 21.0f, 16.0f, 6.83f);
        b.o(pathBuilderQ, 1.0f, 1.0f, 10.0f, -2.0f);
        pathBuilderQ.verticalLineTo(7.83f);
        d.C(pathBuilderQ, 0.41f, -0.41f, 16.0f, 6.83f);
        pathBuilderQ.moveTo(12.0f, 6.83f);
        pathBuilderQ.lineToRelative(0.59f, 0.59f);
        b.n(pathBuilderQ, 13.0f, 7.83f, 10.0f, -2.0f);
        pathBuilderQ.verticalLineTo(7.83f);
        d.C(pathBuilderQ, 0.41f, -0.41f, 12.0f, 6.83f);
        c.m(pathBuilderQ, 11.0f, 14.0f, -2.0f, 2.0f);
        e.y(pathBuilderQ, 2.0f, 11.0f, 13.0f, 16.0f);
        a0.b.f(pathBuilderQ, 2.0f, -2.0f, -2.0f, 13.0f);
        pathBuilderQ.moveTo(7.0f, 7.83f);
        pathBuilderQ.lineToRelative(1.0f, -1.0f);
        pathBuilderQ.lineToRelative(0.59f, 0.59f);
        c.f(pathBuilderQ, 9.0f, 7.83f, 10.0f, 7.0f);
        v.a.D(pathBuilderQ, 7.83f, 7.0f, 12.0f, 2.0f);
        d.u(pathBuilderQ, 2.0f, 7.0f, 12.0f);
        v.a.C(pathBuilderQ, 7.0f, 16.0f, 2.0f, 2.0f);
        c.l(pathBuilderQ, 7.0f, 16.0f, 17.0f, 18.0f);
        f.w(pathBuilderQ, -2.0f, -2.0f, 2.0f, 18.0f);
        v.a.C(pathBuilderQ, 17.0f, 14.0f, -2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 2.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fence = imageVectorBuild;
        return imageVectorBuild;
    }
}

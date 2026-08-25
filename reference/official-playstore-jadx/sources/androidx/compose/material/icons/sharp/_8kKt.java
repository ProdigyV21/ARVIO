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
import v.c;
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__8k", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_8k", "Landroidx/compose/material/icons/Icons$Sharp;", "get_8k", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _8kKt {
    private static ImageVector __8k;

    public static final ImageVector get_8k(Icons.Sharp sharp) {
        ImageVector imageVector = __8k;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._8k", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(8.0f, 12.5f, 1.5f, 14.0f, 8.0f);
        v.a.D(pathBuilderS, 12.5f, 8.0f, 10.0f, 1.5f);
        d.u(pathBuilderS, 1.5f, 8.0f, 10.0f);
        v.a.A(pathBuilderS, 21.0f, 3.0f, 3.0f, 18.0f);
        c.D(pathBuilderS, 18.0f, 3.0f, 11.0f, 10.0f);
        pathBuilderS.verticalLineToRelative(4.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderS.horizontalLineTo(7.5f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.verticalLineToRelative(-4.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderS.horizontalLineTo(10.0f);
        pathBuilderS.curveTo(10.55f, 9.0f, 11.0f, 9.45f, 11.0f, 10.0f);
        v.a.g(pathBuilderS, 18.0f, 15.0f, -1.75f);
        b.m(pathBuilderS, -1.75f, -2.25f, 15.0f, 13.0f);
        w.a.j(pathBuilderS, 9.0f, 1.5f, 2.25f);
        pathBuilderS.lineTo(16.25f, 9.0f);
        pathBuilderS.horizontalLineTo(18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderS, -2.25f, 3.0f, 18.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __8k = imageVectorBuild;
        return imageVectorBuild;
    }
}

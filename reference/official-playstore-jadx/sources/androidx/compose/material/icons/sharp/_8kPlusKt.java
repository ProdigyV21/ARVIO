package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__8kPlus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_8kPlus", "Landroidx/compose/material/icons/Icons$Sharp;", "get_8kPlus", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _8kPlusKt {
    private static ImageVector __8kPlus;

    public static final ImageVector get_8kPlus(Icons.Sharp sharp) {
        ImageVector imageVector = __8kPlus;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._8kPlus", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(7.5f, 12.5f, 1.0f, 14.0f, -1.0f);
        v.a.D(pathBuilderU, 12.5f, 7.5f, 10.0f, 1.0f);
        v.a.t(pathBuilderU, 1.5f, -1.0f, 10.0f);
        v.a.A(pathBuilderU, 21.0f, 3.0f, 3.0f, 18.0f);
        c.D(pathBuilderU, 18.0f, 3.0f, 10.0f, 14.0f);
        pathBuilderU.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderU.horizontalLineTo(7.0f);
        pathBuilderU.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderU.verticalLineToRelative(-4.0f);
        pathBuilderU.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderU.horizontalLineToRelative(2.0f);
        pathBuilderU.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        v.a.D(pathBuilderU, 14.0f, 16.0f, 15.0f, -1.75f);
        b.m(pathBuilderU, -1.75f, -2.25f, 15.0f, 11.0f);
        w.a.j(pathBuilderU, 9.0f, 1.5f, 2.25f);
        pathBuilderU.lineTo(14.25f, 9.0f);
        pathBuilderU.horizontalLineTo(16.0f);
        d.C(pathBuilderU, -2.25f, 3.0f, 16.0f, 15.0f);
        e.m(pathBuilderU, 19.0f, 12.5f, -1.5f, 14.0f);
        a.y(pathBuilderU, -1.0f, -1.5f, 15.0f, -1.0f);
        w.a.C(pathBuilderU, 1.5f, 10.0f, 1.0f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.m(pathBuilderU, 19.0f, 12.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __8kPlus = imageVectorBuild;
        return imageVectorBuild;
    }
}

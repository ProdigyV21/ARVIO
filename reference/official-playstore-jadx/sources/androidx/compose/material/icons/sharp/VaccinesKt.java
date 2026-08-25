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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vaccines", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Vaccines", "Landroidx/compose/material/icons/Icons$Sharp;", "getVaccines", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VaccinesKt {
    private static ImageVector _vaccines;

    public static final ImageVector getVaccines(Icons.Sharp sharp) {
        ImageVector imageVector = _vaccines;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Vaccines", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(12.0f, 5.5f, 8.0f, 4.0f, 1.5f);
        e.x(pathBuilderC, 2.0f, -5.0f, 2.0f, 6.0f);
        y.a.g(pathBuilderC, 1.5f, 2.0f, 2.0f);
        pathBuilderC.curveToRelative(0.0f, 0.0f, 0.45f, 0.0f, 1.0f, 0.0f);
        w.a.j(pathBuilderC, 17.0f, 3.0f, 4.0f);
        b.o(pathBuilderC, 2.0f, 1.5f, 17.0f, 3.0f);
        pathBuilderC.verticalLineTo(7.5f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        a.B(pathBuilderC, 5.5f, 9.0f, 9.0f, 6.5f);
        d.B(pathBuilderC, 1.5f, 9.0f, 12.0f, 6.5f);
        pathBuilderC.verticalLineToRelative(1.5f);
        pathBuilderC.horizontalLineTo(9.0f);
        pathBuilderC.lineTo(9.0f, 15.0f);
        w.a.l(pathBuilderC, 5.0f, 7.5f, 4.0f, 9.0f);
        e.o(pathBuilderC, 19.5f, 10.5f, 10.0f, 1.0f);
        a0.b.l(pathBuilderC, 8.0f, -7.0f, -0.01f, 2.0f);
        pathBuilderC.horizontalLineToRelative(1.01f);
        pathBuilderC.verticalLineToRelative(0.5f);
        pathBuilderC.curveToRelative(0.0f, 0.5f, -1.5f, 1.16f, -1.5f, 3.0f);
        w.a.j(pathBuilderC, 22.0f, 8.0f, -8.5f);
        pathBuilderC.curveTo(21.0f, 11.66f, 19.5f, 11.0f, 19.5f, 10.5f);
        a0.b.t(pathBuilderC, 16.5f, 10.5f, 10.0f, 1.0f);
        pathBuilderC.verticalLineToRelative(0.5f);
        pathBuilderC.curveToRelative(0.0f, 1.6f, 1.5f, 2.0f, 1.5f, 3.0f);
        pathBuilderC.verticalLineTo(14.0f);
        pathBuilderC.horizontalLineToRelative(-4.0f);
        pathBuilderC.curveToRelative(0.0f, -0.21f, 0.0f, -0.39f, 0.0f, -0.5f);
        pathBuilderC.curveTo(15.0f, 12.5f, 16.5f, 12.1f, 16.5f, 10.5f);
        pathBuilderC.close();
        pathBuilderC.moveTo(15.0f, 20.0f);
        pathBuilderC.curveToRelative(0.0f, 0.0f, 0.0f, -0.63f, 0.0f, -1.5f);
        pathBuilderC.horizontalLineToRelative(4.0f);
        pathBuilderC.verticalLineTo(20.0f);
        pathBuilderC.horizontalLineTo(15.0f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vaccines = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.b;
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tune", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tune", "Landroidx/compose/material/icons/Icons$Sharp;", "getTune", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TuneKt {
    private static ImageVector _tune;

    public static final ImageVector getTune(Icons.Sharp sharp) {
        ImageVector imageVector = _tune;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Tune", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(3.0f, 17.0f, 2.0f, 6.0f, -2.0f);
        c.z(pathBuilderU, 3.0f, 17.0f, 3.0f, 5.0f);
        c.w(pathBuilderU, 2.0f, 10.0f, 13.0f, 5.0f);
        c.z(pathBuilderU, 3.0f, 5.0f, 13.0f, 21.0f);
        b.z(pathBuilderU, -2.0f, 8.0f, -2.0f, -8.0f);
        a0.a.B(pathBuilderU, -2.0f, -2.0f, 6.0f, 2.0f);
        pathBuilderU.moveTo(7.0f, 9.0f);
        d.r(pathBuilderU, 2.0f, 3.0f, 11.0f, 2.0f);
        e.g(pathBuilderU, 4.0f, 2.0f, 2.0f);
        b.y(pathBuilderU, 9.0f, 9.0f, 7.0f, 9.0f);
        pathBuilderU.moveTo(21.0f, 13.0f);
        d.r(pathBuilderU, -2.0f, 11.0f, 11.0f, 2.0f);
        e.r(pathBuilderU, 10.0f, 15.0f, 9.0f, 2.0f);
        pathBuilderU.lineTo(17.0f, 7.0f);
        c.A(pathBuilderU, 4.0f, 21.0f, 5.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderU, 17.0f, 3.0f, -2.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tune = imageVectorBuild;
        return imageVectorBuild;
    }
}

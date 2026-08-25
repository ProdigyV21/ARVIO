package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ramenDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RamenDining", "Landroidx/compose/material/icons/Icons$Sharp;", "getRamenDining", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RamenDiningKt {
    private static ImageVector _ramenDining;

    public static final ImageVector getRamenDining(Icons.Sharp sharp) {
        ImageVector imageVector = _ramenDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.RamenDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a.f(22.0f, 3.51f, 2.0f, 4.0f, 3.99f);
        pathBuilderF.verticalLineTo(12.0f);
        pathBuilderF.horizontalLineTo(2.0f);
        pathBuilderF.curveToRelative(0.0f, 3.69f, 2.47f, 6.86f, 6.0f, 8.25f);
        w.a.j(pathBuilderF, 22.0f, 8.0f, -1.75f);
        pathBuilderF.curveToRelative(3.53f, -1.39f, 6.0f, -4.56f, 6.0f, -8.25f);
        a.x(pathBuilderF, 10.5f, 8.0f, 22.0f, 6.5f);
        pathBuilderF.horizontalLineTo(10.5f);
        pathBuilderF.verticalLineTo(4.78f);
        pathBuilderF.lineTo(22.0f, 3.51f);
        pathBuilderF.close();
        e.o(pathBuilderF, 6.5f, 5.22f, 6.5f, -1.0f);
        c.x(pathBuilderF, 5.34f, 6.5f, 5.22f);
        v.a.C(pathBuilderF, 5.5f, 8.0f, 1.0f, 4.0f);
        c.D(pathBuilderF, -1.0f, 8.0f, 9.0f, 12.0f);
        w.a.l(pathBuilderF, 8.0f, 8.0f, 1.0f, 12.0f);
        c.B(pathBuilderF, 9.0f, 6.5f, 8.0f, 5.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderF, 1.0f, -0.11f, 6.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ramenDining = imageVectorBuild;
        return imageVectorBuild;
    }
}

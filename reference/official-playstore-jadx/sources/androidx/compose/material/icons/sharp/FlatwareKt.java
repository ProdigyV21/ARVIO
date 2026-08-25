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
import v.c;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flatware", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flatware", "Landroidx/compose/material/icons/Icons$Sharp;", "getFlatware", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlatwareKt {
    private static ImageVector _flatware;

    public static final ImageVector getFlatware(Icons.Sharp sharp) {
        ImageVector imageVector = _flatware;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Flatware", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 7.08f);
        pathBuilderA.curveToRelative(0.0f, 1.77f, -0.84f, 3.25f, -2.0f, 3.82f);
        c.i(pathBuilderA, 21.0f, -2.0f, 10.9f);
        pathBuilderA.curveToRelative(-1.16f, -0.57f, -2.0f, -2.05f, -2.0f, -3.82f);
        pathBuilderA.curveTo(10.01f, 4.83f, 11.35f, 3.0f, 13.0f, 3.0f);
        pathBuilderA.curveTo(14.66f, 3.0f, 16.0f, 4.83f, 16.0f, 7.08f);
        c.y(pathBuilderA, 17.0f, 3.0f, 18.0f, 2.0f);
        f.t(pathBuilderA, -8.0f, 2.0f, 7.0f);
        pathBuilderA.curveTo(21.0f, 5.24f, 19.76f, 3.0f, 17.0f, 3.0f);
        c.C(pathBuilderA, 8.28f, 3.0f, 7.56f, 4.0f);
        pathBuilderA.horizontalLineTo(6.72f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.lineTo(6.0f, 3.0f);
        e.z(pathBuilderA, 5.28f, 3.0f, 4.0f, 4.44f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.lineTo(3.72f, 3.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.9f, 0.0f, 2.0f, 0.0f);
        f.t(pathBuilderA, 10.0f, 2.0f, 11.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.0f, 2.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.c(pathBuilderA, 3.0f, 8.28f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flatware = imageVectorBuild;
        return imageVectorBuild;
    }
}

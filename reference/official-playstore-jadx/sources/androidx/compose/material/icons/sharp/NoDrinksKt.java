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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noDrinks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoDrinks", "Landroidx/compose/material/icons/Icons$Sharp;", "getNoDrinks", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoDrinksKt {
    private static ImageVector _noDrinks;

    public static final ImageVector getNoDrinks(Icons.Sharp sharp) {
        ImageVector imageVector = _noDrinks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NoDrinks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineToRelative(8.23f, 8.23f);
        e.z(pathBuilderJ, 11.0f, 14.0f, 5.0f, 6.0f);
        e.l(pathBuilderJ, 2.0f, 12.0f, -0.17f);
        d.C(pathBuilderJ, 1.78f, 1.78f, 21.19f, 21.19f);
        pathBuilderJ.moveTo(13.0f, 19.0f);
        pathBuilderJ.verticalLineToRelative(-3.17f);
        pathBuilderJ.lineTo(16.17f, 19.0f);
        pathBuilderJ.horizontalLineTo(13.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(7.83f, 5.0f);
        e.h(pathBuilderJ, -2.0f, -2.0f, 21.0f, 2.0f);
        pathBuilderJ.lineToRelative(-6.2f, 6.97f);
        pathBuilderJ.lineTo(9.83f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderJ, 6.74f, 1.78f, -2.0f, 7.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noDrinks = imageVectorBuild;
        return imageVectorBuild;
    }
}

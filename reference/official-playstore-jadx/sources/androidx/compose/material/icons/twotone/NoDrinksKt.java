package androidx.compose.material.icons.twotone;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noDrinks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoDrinks", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNoDrinks", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoDrinksKt {
    private static ImageVector _noDrinks;

    public static final ImageVector getNoDrinks(Icons.TwoTone twoTone) {
        ImageVector imageVector = _noDrinks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NoDrinks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(14.77f, 9.0f, -2.94f, 0.0f), 1.55f, 1.56f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
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
        pathBuilderJ.lineToRelative(-1.42f, -1.42f);
        pathBuilderJ.lineTo(14.77f, 9.0f);
        e.C(pathBuilderJ, -2.94f, -2.0f, -2.0f, 6.74f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderJ, 1.78f, -2.0f, 7.83f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noDrinks = imageVectorBuild;
        return imageVectorBuild;
    }
}

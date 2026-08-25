package androidx.compose.material.icons.filled;

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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noDrinks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoDrinks", "Landroidx/compose/material/icons/Icons$Filled;", "getNoDrinks", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoDrinksKt {
    private static ImageVector _noDrinks;

    public static final ImageVector getNoDrinks(Icons.Filled filled) {
        ImageVector imageVector = _noDrinks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoDrinks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(5.83f, 3.0f, 21.0f, 2.0f);
        pathBuilderA.lineToRelative(-6.2f, 6.97f);
        pathBuilderA.lineTo(9.83f, 7.0f);
        a.n(pathBuilderA, 6.74f, 1.78f, -2.0f, 7.83f);
        c.z(pathBuilderA, 5.83f, 3.0f, 19.78f, 22.61f);
        c.f(pathBuilderA, 18.0f, 20.83f, 21.0f, 6.0f);
        e.l(pathBuilderA, -2.0f, 5.0f, -5.0f);
        pathBuilderA.lineToRelative(-1.37f, -1.54f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineTo(3.0f, 3.0f);
        d.C(pathBuilderA, 18.19f, 18.19f, 19.78f, 22.61f);
        pathBuilderA.moveTo(16.17f, 19.0f);
        pathBuilderA.lineTo(13.0f, 15.83f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.horizontalLineTo(16.17f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noDrinks = imageVectorBuild;
        return imageVectorBuild;
    }
}

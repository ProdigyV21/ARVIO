package androidx.compose.material.icons.outlined;

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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_setMeal", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SetMeal", "Landroidx/compose/material/icons/Icons$Outlined;", "getSetMeal", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SetMealKt {
    private static ImageVector _setMeal;

    public static final ImageVector getSetMeal(Icons.Outlined outlined) {
        ImageVector imageVector = _setMeal;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SetMeal", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.05f, 17.56f, 3.08f, 18.5f);
        pathBuilderJ.lineTo(3.0f, 17.0f);
        d.C(pathBuilderJ, 17.98f, -0.94f, 21.05f, 17.56f);
        a.A(pathBuilderJ, 21.0f, 19.48f, 3.0f, 1.5f);
        c.D(pathBuilderJ, 18.0f, 19.48f, 23.0f, 13.0f);
        pathBuilderJ.verticalLineTo(4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.horizontalLineTo(3.0f);
        pathBuilderJ.curveTo(1.9f, 2.0f, 1.0f, 2.9f, 1.0f, 4.0f);
        pathBuilderJ.verticalLineToRelative(9.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(18.0f);
        pathBuilderJ.curveTo(22.1f, 15.0f, 23.0f, 14.1f, 23.0f, 13.0f);
        f.o(pathBuilderJ, 21.0f, 13.0f, 3.0f, 4.0f);
        c.D(pathBuilderJ, 18.0f, 13.0f, 20.0f, 6.0f);
        pathBuilderJ.curveToRelative(-1.68f, 0.0f, -3.04f, 0.98f, -3.21f, 2.23f);
        pathBuilderJ.curveTo(16.15f, 7.5f, 14.06f, 5.5f, 10.25f, 5.5f);
        pathBuilderJ.curveToRelative(-4.67f, 0.0f, -6.75f, 3.0f, -6.75f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.08f, 3.0f, 6.75f, 3.0f);
        pathBuilderJ.curveToRelative(3.81f, 0.0f, 5.9f, -2.0f, 6.54f, -2.73f);
        pathBuilderJ.curveTo(16.96f, 10.02f, 18.32f, 11.0f, 20.0f, 11.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderJ, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _setMeal = imageVectorBuild;
        return imageVectorBuild;
    }
}

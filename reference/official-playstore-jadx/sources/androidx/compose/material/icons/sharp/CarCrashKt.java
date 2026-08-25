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
import v.a;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carCrash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarCrash", "Landroidx/compose/material/icons/Icons$Sharp;", "getCarCrash", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarCrashKt {
    private static ImageVector _carCrash;

    public static final ImageVector getCarCrash(Icons.Sharp sharp) {
        ImageVector imageVector = _carCrash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CarCrash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 1.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(20.76f, 1.0f, 18.0f, 1.0f);
        a.u(pathBuilderA, 18.5f, 7.0f, -1.0f, 3.0f);
        c.D(pathBuilderA, 1.0f, 7.0f, 18.5f, 8.0f);
        b.A(pathBuilderA, 1.0f, -1.0f, 8.0f, 18.5f);
        pathBuilderA.moveTo(17.91f, 13.0f);
        pathBuilderA.curveToRelative(0.06f, 0.16f, 0.09f, 0.33f, 0.09f, 0.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(15.0f, 14.33f, 15.0f, 13.5f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, 0.15f, -0.74f, 0.39f, -1.01f);
        pathBuilderA.curveToRelative(-1.63f, -0.66f, -2.96f, -1.91f, -3.71f, -3.49f);
        b.j(pathBuilderA, 5.81f, 1.04f, -3.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, 0.1f, -1.37f, 0.29f, -2.0f);
        pathBuilderA.horizontalLineTo(5.41f);
        pathBuilderA.lineTo(3.0f, 11.0f);
        pathBuilderA.verticalLineToRelative(9.0f);
        a.o(pathBuilderA, 3.0f, -2.0f, 12.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.verticalLineToRelative(-7.68f);
        pathBuilderA.curveTo(19.95f, 12.83f, 18.84f, 13.01f, 17.91f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.5f, 15.0f);
        pathBuilderA.curveTo(6.67f, 15.0f, 6.0f, 14.33f, 6.0f, 13.5f);
        pathBuilderA.reflectiveCurveTo(6.67f, 12.0f, 7.5f, 12.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 12.67f, 9.0f, 13.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 8.33f, 15.0f, 7.5f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _carCrash = imageVectorBuild;
        return imageVectorBuild;
    }
}

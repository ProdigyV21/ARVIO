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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carRental", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarRental", "Landroidx/compose/material/icons/Icons$Sharp;", "getCarRental", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarRentalKt {
    private static ImageVector _carRental;

    public static final ImageVector getCarRental(Icons.Sharp sharp) {
        ImageVector imageVector = _carRental;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CarRental", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.83f, 3.0f);
        pathBuilderA.curveTo(10.41f, 1.83f, 9.3f, 1.0f, 8.0f, 1.0f);
        pathBuilderA.curveTo(6.34f, 1.0f, 5.0f, 2.34f, 5.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.65f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(1.3f, 0.0f, 2.41f, -0.84f, 2.83f, -2.0f);
        a.e(pathBuilderA, 16.0f, 2.0f, 2.0f, 5.0f);
        w.a.o(pathBuilderA, 1.0f, 3.0f, 10.83f);
        pathBuilderA.moveTo(8.0f, 5.0f);
        pathBuilderA.curveTo(7.45f, 5.0f, 7.0f, 4.55f, 7.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(8.55f, 5.0f, 8.0f, 5.0f);
        w.a.r(pathBuilderA, 17.11f, 9.0f, 6.89f);
        b.n(pathBuilderA, 5.0f, 14.69f, 22.0f, 2.0f);
        b.z(pathBuilderA, -2.0f, 10.0f, 2.0f, 2.0f);
        y.a.n(pathBuilderA, -7.31f, 17.11f, 9.0f);
        pathBuilderA.moveTo(9.0f, 17.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(9.55f, 17.5f, 9.0f, 17.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 17.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(15.55f, 17.5f, 15.0f, 17.5f);
        v.a.z(pathBuilderA, 7.67f, 13.0f, 0.66f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.r(pathBuilderA, 7.34f, 0.66f, 2.0f, 7.67f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _carRental = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_apartment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Apartment", "Landroidx/compose/material/icons/Icons$Rounded;", "getApartment", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApartmentKt {
    private static ImageVector _apartment;

    public static final ImageVector getApartment(Icons.Rounded rounded) {
        ImageVector imageVector = _apartment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Apartment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(17.0f, 11.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.curveTo(7.9f, 3.0f, 7.0f, 3.9f, 7.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 7.0f, 3.0f, 7.9f, 3.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderA, -3.0f, 2.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.s(pathBuilderA, 17.0f, 7.0f, 19.0f, 5.0f);
        v.a.t(pathBuilderA, -2.0f, 2.0f, 19.0f);
        v.a.A(pathBuilderA, 7.0f, 15.0f, 5.0f, -2.0f);
        c.D(pathBuilderA, 2.0f, 15.0f, 7.0f, 11.0f);
        a.l(pathBuilderA, 5.0f, 9.0f, 2.0f, 11.0f);
        v.a.A(pathBuilderA, 11.0f, 15.0f, 9.0f, -2.0f);
        c.D(pathBuilderA, 2.0f, 15.0f, 11.0f, 11.0f);
        a.l(pathBuilderA, 9.0f, 9.0f, 2.0f, 11.0f);
        c.B(pathBuilderA, 11.0f, 7.0f, 9.0f, 5.0f);
        c.D(pathBuilderA, 2.0f, 7.0f, 15.0f, 15.0f);
        f.w(pathBuilderA, -2.0f, -2.0f, 2.0f, 15.0f);
        e.m(pathBuilderA, 15.0f, 11.0f, -2.0f, 9.0f);
        c.D(pathBuilderA, 2.0f, 11.0f, 15.0f, 7.0f);
        f.A(pathBuilderA, -2.0f, 5.0f, 2.0f, 7.0f);
        v.a.C(pathBuilderA, 19.0f, 19.0f, -2.0f, -2.0f);
        c.D(pathBuilderA, 2.0f, 19.0f, 19.0f, 15.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderA, -2.0f, -2.0f, 2.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _apartment = imageVectorBuild;
        return imageVectorBuild;
    }
}

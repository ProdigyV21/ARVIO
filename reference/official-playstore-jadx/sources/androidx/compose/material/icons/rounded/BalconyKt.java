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
import v.a;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_balcony", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Balcony", "Landroidx/compose/material/icons/Icons$Rounded;", "getBalcony", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BalconyKt {
    private static ImageVector _balcony;

    public static final ImageVector getBalcony(Icons.Rounded rounded) {
        ImageVector imageVector = _balcony;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Balcony", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(20.0f, 14.27f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(4.27f);
        pathBuilderA.curveTo(3.4f, 14.61f, 3.0f, 15.26f, 3.0f, 16.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveTo(21.0f, 15.26f, 20.6f, 14.61f, 20.0f, 14.27f);
        c.C(pathBuilderA, 7.0f, 20.0f, 5.0f, -4.0f);
        c.D(pathBuilderA, 2.0f, 20.0f, 11.0f, 20.0f);
        c.k(pathBuilderA, 9.0f, -4.0f, 2.0f, 20.0f);
        a.A(pathBuilderA, 11.0f, 14.0f, 6.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -2.97f, 2.16f, -5.44f, 5.0f, -5.92f);
        f.r(pathBuilderA, 14.0f, 13.0f, 4.08f);
        pathBuilderA.curveToRelative(2.84f, 0.48f, 5.0f, 2.94f, 5.0f, 5.92f);
        a.t(pathBuilderA, 4.0f, -5.0f, 4.08f);
        a.C(pathBuilderA, 15.0f, 20.0f, -2.0f, -4.0f);
        c.D(pathBuilderA, 2.0f, 20.0f, 19.0f, 20.0f);
        f.w(pathBuilderA, -2.0f, -4.0f, 2.0f, 20.0f);
        pathBuilderA.moveTo(8.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 11.55f, 8.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 16.0f, 10.45f, 16.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _balcony = imageVectorBuild;
        return imageVectorBuild;
    }
}

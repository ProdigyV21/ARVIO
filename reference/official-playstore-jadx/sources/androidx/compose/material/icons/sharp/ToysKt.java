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
import v.b;
import v.c;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_toys", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Toys", "Landroidx/compose/material/icons/Icons$Sharp;", "getToys", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ToysKt {
    private static ImageVector _toys;

    public static final ImageVector getToys(Icons.Sharp sharp) {
        ImageVector imageVector = _toys;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Toys", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(18.72f, 10.0f, -2.0f, -6.0f, 7.28f);
        pathBuilderB.lineTo(5.81f, 8.4f);
        pathBuilderB.lineTo(4.41f, 7.0f);
        pathBuilderB.lineToRelative(1.0f, -1.0f);
        pathBuilderB.lineTo(4.0f, 4.59f);
        pathBuilderB.lineTo(0.59f, 8.0f);
        pathBuilderB.lineTo(2.0f, 9.41f);
        pathBuilderB.lineToRelative(1.0f, -1.0f);
        b.m(pathBuilderB, 4.59f, 10.0f, 2.0f, 8.0f);
        pathBuilderB.horizontalLineToRelative(2.18f);
        pathBuilderB.curveTo(4.59f, 19.16f, 5.7f, 20.0f, 7.0f, 20.0f);
        pathBuilderB.curveToRelative(1.3f, 0.0f, 2.4f, -0.84f, 2.82f, -2.0f);
        pathBuilderB.horizontalLineToRelative(4.37f);
        pathBuilderB.curveToRelative(0.41f, 1.16f, 1.51f, 2.0f, 2.82f, 2.0f);
        pathBuilderB.curveToRelative(1.3f, 0.0f, 2.41f, -0.84f, 2.82f, -2.0f);
        a0.b.e(pathBuilderB, 22.0f, -8.0f, 18.72f);
        pathBuilderB.moveTo(7.0f, 18.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderB.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderB.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderB.reflectiveCurveTo(7.55f, 18.0f, 7.0f, 18.0f);
        w.a.r(pathBuilderB, 11.0f, 10.0f, 7.41f);
        pathBuilderB.lineTo(7.39f, 9.98f);
        pathBuilderB.lineTo(8.72f, 6.0f);
        pathBuilderB.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        c.l(pathBuilderB, 11.0f, 10.0f, 13.0f, 10.0f);
        a0.b.l(pathBuilderB, 6.0f, 2.28f, 1.33f, 4.0f);
        v.a.x(pathBuilderB, 13.0f, 17.0f, 18.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderB.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderB.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderB, 17.55f, 18.0f, 17.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _toys = imageVectorBuild;
        return imageVectorBuild;
    }
}

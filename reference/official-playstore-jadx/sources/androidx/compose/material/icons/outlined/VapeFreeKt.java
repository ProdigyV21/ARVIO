package androidx.compose.material.icons.outlined;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vapeFree", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VapeFree", "Landroidx/compose/material/icons/Icons$Outlined;", "getVapeFree", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VapeFreeKt {
    private static ImageVector _vapeFree;

    public static final ImageVector getVapeFree(Icons.Outlined outlined) {
        ImageVector imageVector = _vapeFree;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.VapeFree", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(2.0f, 16.5f, 1.0f);
        pathBuilderA.curveToRelative(1.33f, 0.0f, 2.71f, -0.18f, 4.0f, -0.5f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(-1.29f, -0.32f, -2.67f, -0.5f, -4.0f, -0.5f);
        c.l(pathBuilderA, 2.0f, 16.5f, 16.17f, 19.0f);
        a.n(pathBuilderA, 8.0f, -3.0f, 5.17f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineToRelative(18.38f, 18.38f);
        d.C(pathBuilderA, -1.41f, 1.41f, 16.17f, 19.0f);
        a.A(pathBuilderA, 18.83f, 16.0f, 22.0f, 3.0f);
        d.q(pathBuilderA, -0.17f, 18.83f, 16.0f);
        pathBuilderA.moveTo(11.0f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveTo(10.0f, 17.22f, 10.0f, 17.5f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveTo(11.0f, 17.78f, 11.0f, 17.5f);
        b.t(pathBuilderA, 22.0f, 12.76f, 15.0f, -1.5f);
        pathBuilderA.verticalLineToRelative(-2.23f);
        pathBuilderA.curveToRelative(0.0f, -2.24f, -1.76f, -4.07f, -4.0f, -4.07f);
        pathBuilderA.verticalLineTo(7.2f);
        pathBuilderA.curveToRelative(1.02f, 0.0f, 1.85f, -0.83f, 1.85f, -1.85f);
        pathBuilderA.reflectiveCurveTo(17.52f, 3.5f, 16.5f, 3.5f);
        pathBuilderA.verticalLineTo(2.0f);
        pathBuilderA.curveToRelative(1.85f, 0.0f, 3.35f, 1.5f, 3.35f, 3.35f);
        pathBuilderA.curveToRelative(0.0f, 0.93f, -0.38f, 1.77f, -1.0f, 2.38f);
        pathBuilderA.curveTo(20.72f, 8.62f, 22.0f, 10.54f, 22.0f, 12.76f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.15f, 8.32f);
        pathBuilderA.curveToRelative(0.0f, -0.01f, 0.0f, -0.01f, 0.0f, -0.02f);
        pathBuilderA.curveToRelative(0.0f, -1.85f, 1.5f, -3.35f, 3.35f, -3.35f);
        pathBuilderA.verticalLineToRelative(1.5f);
        pathBuilderA.curveToRelative(-1.02f, 0.0f, -1.85f, 0.73f, -1.85f, 1.75f);
        pathBuilderA.reflectiveCurveToRelative(0.83f, 2.0f, 1.85f, 2.0f);
        pathBuilderA.horizontalLineToRelative(1.53f);
        pathBuilderA.curveToRelative(1.87f, 0.0f, 3.47f, 1.35f, 3.47f, 3.16f);
        a0.a.s(pathBuilderA, 15.0f, 18.0f, -1.3f);
        pathBuilderA.curveToRelative(0.0f, -1.31f, -0.92f, -2.05f, -1.97f, -2.05f);
        pathBuilderA.horizontalLineTo(14.5f);
        pathBuilderA.curveToRelative(-0.01f, 0.0f, -0.01f, 0.0f, -0.02f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 11.15f, 8.32f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vapeFree = imageVectorBuild;
        return imageVectorBuild;
    }
}

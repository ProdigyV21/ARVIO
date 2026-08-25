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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrEnhancedSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrEnhancedSelect", "Landroidx/compose/material/icons/Icons$Outlined;", "getHdrEnhancedSelect", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrEnhancedSelectKt {
    private static ImageVector _hdrEnhancedSelect;

    public static final ImageVector getHdrEnhancedSelect(Icons.Outlined outlined) {
        ImageVector imageVector = _hdrEnhancedSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.HdrEnhancedSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA.reflectiveCurveTo(8.69f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 4.0f);
        pathBuilderA.curveTo(9.79f, 4.0f, 8.0f, 5.79f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveTo(14.21f, 4.0f, 12.0f, 4.0f);
        a.u(pathBuilderA, 13.0f, 11.0f, -2.0f, 9.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineTo(5.0f);
        a.o(pathBuilderA, 2.0f, 2.0f, 2.0f, 2.0f);
        c.D(pathBuilderA, -2.0f, 11.0f, 24.0f, 20.0f);
        a.o(pathBuilderA, -2.0f, 2.0f, -1.5f, -2.0f);
        a.o(pathBuilderA, -2.0f, -1.5f, 2.0f, -2.0f);
        c.k(pathBuilderA, 22.0f, 2.0f, 2.0f, 20.0f);
        pathBuilderA.moveTo(18.0f, 18.5f);
        pathBuilderA.curveToRelative(0.0f, 0.6f, -0.4f, 1.1f, -0.9f, 1.4f);
        pathBuilderA.lineTo(18.0f, 22.0f);
        e.C(pathBuilderA, -1.5f, -0.9f, -2.0f, -1.1f);
        a0.a.A(pathBuilderA, 2.0f, 13.0f, -6.0f, 3.5f);
        pathBuilderA.curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f);
        w.a.k(pathBuilderA, 18.5f, 16.5f, 18.5f, -1.0f);
        y.a.q(pathBuilderA, -2.0f, 1.0f, 16.5f);
        c.u(pathBuilderA, 3.5f, 18.0f, -2.0f, 5.0f);
        a0.a.A(pathBuilderA, 6.0f, 3.5f, -2.5f, -2.0f);
        c.o(pathBuilderA, 22.0f, 0.0f, -6.0f, 1.5f);
        e.y(pathBuilderA, 2.0f, 3.5f, 10.0f, 16.0f);
        pathBuilderA.curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f);
        b.e(pathBuilderA, 6.5f, -6.0f, 10.0f);
        c.u(pathBuilderA, 10.0f, 20.5f, -3.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderA, 3.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrEnhancedSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.filled;

import a0.a;
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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_productionQuantityLimits", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ProductionQuantityLimits", "Landroidx/compose/material/icons/Icons$Filled;", "getProductionQuantityLimits", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ProductionQuantityLimitsKt {
    private static ImageVector _productionQuantityLimits;

    public static final ImageVector getProductionQuantityLimits(Icons.Filled filled) {
        ImageVector imageVector = _productionQuantityLimits;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ProductionQuantityLimits", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(13.0f, 10.0f, -2.0f, 8.0f, 2.0f);
        v.a.D(pathBuilderU, 10.0f, 13.0f, 6.0f, -2.0f);
        f.k(pathBuilderU, 1.0f, 2.0f, 6.0f);
        pathBuilderU.moveTo(7.0f, 18.0f);
        pathBuilderU.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderU.reflectiveCurveTo(5.9f, 22.0f, 7.0f, 22.0f);
        pathBuilderU.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.reflectiveCurveTo(8.1f, 18.0f, 7.0f, 18.0f);
        pathBuilderU.close();
        pathBuilderU.moveTo(17.0f, 18.0f);
        pathBuilderU.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderU.reflectiveCurveToRelative(0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderU.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.reflectiveCurveTo(18.1f, 18.0f, 17.0f, 18.0f);
        v.a.g(pathBuilderU, 8.1f, 13.0f, 7.45f);
        pathBuilderU.curveToRelative(0.75f, 0.0f, 1.41f, -0.41f, 1.75f, -1.03f);
        pathBuilderU.lineTo(21.0f, 4.96f);
        pathBuilderU.lineTo(19.25f, 4.0f);
        pathBuilderU.lineToRelative(-3.7f, 7.0f);
        b.q(pathBuilderU, 8.53f, 4.27f, 2.0f, 1.0f);
        d.D(pathBuilderU, 2.0f, 2.0f, 3.6f, 7.59f);
        pathBuilderU.lineToRelative(-1.35f, 2.44f);
        pathBuilderU.curveTo(4.52f, 15.37f, 5.48f, 17.0f, 7.0f, 17.0f);
        a.i(pathBuilderU, 12.0f, -2.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderU, 8.1f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _productionQuantityLimits = imageVectorBuild;
        return imageVectorBuild;
    }
}

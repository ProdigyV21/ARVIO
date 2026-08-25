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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pointOfSale", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PointOfSale", "Landroidx/compose/material/icons/Icons$Outlined;", "getPointOfSale", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointOfSaleKt {
    private static ImageVector _pointOfSale;

    public static final ImageVector getPointOfSale(Icons.Outlined outlined) {
        ImageVector imageVector = _pointOfSale;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PointOfSale", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.0f, 2.0f, 7.0f);
        pathBuilderQ.curveTo(5.9f, 2.0f, 5.0f, 2.9f, 5.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(19.0f, 2.9f, 18.1f, 2.0f, 17.0f, 2.0f);
        f.o(pathBuilderQ, 17.0f, 6.0f, 7.0f, 4.0f);
        c.D(pathBuilderQ, 10.0f, 6.0f, 20.0f, 22.0f);
        pathBuilderQ.horizontalLineTo(4.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        e.l(pathBuilderQ, -1.0f, 20.0f, 1.0f);
        pathBuilderQ.curveTo(22.0f, 21.1f, 21.1f, 22.0f, 20.0f, 22.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(18.53f, 10.19f);
        pathBuilderQ.curveTo(18.21f, 9.47f, 17.49f, 9.0f, 16.7f, 9.0f);
        pathBuilderQ.horizontalLineTo(7.3f);
        pathBuilderQ.curveToRelative(-0.79f, 0.0f, -1.51f, 0.47f, -1.83f, 1.19f);
        pathBuilderQ.lineTo(2.0f, 18.0f);
        d.q(pathBuilderQ, 20.0f, 18.53f, 10.19f);
        pathBuilderQ.moveTo(9.5f, 16.0f);
        pathBuilderQ.horizontalLineToRelative(-1.0f);
        pathBuilderQ.curveTo(8.22f, 16.0f, 8.0f, 15.78f, 8.0f, 15.5f);
        pathBuilderQ.curveTo(8.0f, 15.22f, 8.22f, 15.0f, 8.5f, 15.0f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(10.0f, 15.78f, 9.78f, 16.0f, 9.5f, 16.0f);
        a.g(pathBuilderQ, 9.5f, 14.0f, -1.0f);
        pathBuilderQ.curveTo(8.22f, 14.0f, 8.0f, 13.78f, 8.0f, 13.5f);
        pathBuilderQ.curveTo(8.0f, 13.22f, 8.22f, 13.0f, 8.5f, 13.0f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(10.0f, 13.78f, 9.78f, 14.0f, 9.5f, 14.0f);
        a.g(pathBuilderQ, 9.5f, 12.0f, -1.0f);
        pathBuilderQ.curveTo(8.22f, 12.0f, 8.0f, 11.78f, 8.0f, 11.5f);
        pathBuilderQ.curveTo(8.0f, 11.22f, 8.22f, 11.0f, 8.5f, 11.0f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(10.0f, 11.78f, 9.78f, 12.0f, 9.5f, 12.0f);
        a.g(pathBuilderQ, 12.5f, 16.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(13.0f, 15.78f, 12.78f, 16.0f, 12.5f, 16.0f);
        a.g(pathBuilderQ, 12.5f, 14.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(13.0f, 13.78f, 12.78f, 14.0f, 12.5f, 14.0f);
        a.g(pathBuilderQ, 12.5f, 12.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(13.0f, 11.78f, 12.78f, 12.0f, 12.5f, 12.0f);
        a.g(pathBuilderQ, 15.5f, 16.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(16.0f, 15.78f, 15.78f, 16.0f, 15.5f, 16.0f);
        a.g(pathBuilderQ, 15.5f, 14.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(16.0f, 13.78f, 15.78f, 14.0f, 15.5f, 14.0f);
        a.g(pathBuilderQ, 15.5f, 12.0f, -1.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderQ.curveTo(16.0f, 11.78f, 15.78f, 12.0f, 15.5f, 12.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pointOfSale = imageVectorBuild;
        return imageVectorBuild;
    }
}

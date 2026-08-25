package androidx.compose.material.icons.filled;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsTransitFilled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsTransitFilled", "Landroidx/compose/material/icons/Icons$Filled;", "getDirectionsTransitFilled", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsTransitFilledKt {
    private static ImageVector _directionsTransitFilled;

    public static final ImageVector getDirectionsTransitFilled(Icons.Filled filled) {
        ImageVector imageVector = _directionsTransitFilled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DirectionsTransitFilled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(8.0f, 2.0f, 4.0f, 2.5f, 4.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(9.5f);
        pathBuilderA.curveTo(4.0f, 17.43f, 5.57f, 19.0f, 7.5f, 19.0f);
        a.h(pathBuilderA, 6.0f, 20.0f, 1.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.lineToRelative(-1.5f, -1.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveTo(20.0f, 2.5f, 16.42f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 16.0f);
        pathBuilderA.curveTo(7.67f, 16.0f, 7.0f, 15.33f, 7.0f, 14.5f);
        pathBuilderA.reflectiveCurveTo(7.67f, 13.0f, 8.5f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 16.0f, 8.5f, 16.0f);
        f.o(pathBuilderA, 11.0f, 10.0f, 6.0f, 7.0f);
        c.D(pathBuilderA, 5.0f, 10.0f, 15.5f, 16.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(16.33f, 16.0f, 15.5f, 16.0f);
        a.u(pathBuilderA, 18.0f, 10.0f, -5.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 5.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsTransitFilled = imageVectorBuild;
        return imageVectorBuild;
    }
}

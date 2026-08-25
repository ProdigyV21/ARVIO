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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBusFilled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBusFilled", "Landroidx/compose/material/icons/Icons$Rounded;", "getDirectionsBusFilled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBusFilledKt {
    private static ImageVector _directionsBusFilled;

    public static final ImageVector getDirectionsBusFilled(Icons.Rounded rounded) {
        ImageVector imageVector = _directionsBusFilled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DirectionsBusFilled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(8.0f, 2.0f, 4.0f, 2.5f, 4.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(9.5f);
        pathBuilderA.curveToRelative(0.0f, 0.95f, 0.38f, 1.81f, 1.0f, 2.44f);
        pathBuilderA.verticalLineToRelative(1.56f);
        pathBuilderA.curveTo(5.0f, 20.33f, 5.67f, 21.0f, 6.5f, 21.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(7.33f, 21.0f, 8.0f, 20.33f, 8.0f, 19.5f);
        a.j(pathBuilderA, 19.0f, 8.0f, 0.5f);
        pathBuilderA.curveToRelative(0.0f, 0.82f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.82f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-1.56f);
        pathBuilderA.curveToRelative(0.62f, -0.63f, 1.0f, -1.49f, 1.0f, -2.44f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveTo(20.0f, 2.5f, 16.42f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 16.0f);
        pathBuilderA.curveTo(7.67f, 16.0f, 7.0f, 15.33f, 7.0f, 14.5f);
        pathBuilderA.reflectiveCurveTo(7.67f, 13.0f, 8.5f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 16.0f, 8.5f, 16.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 16.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(16.33f, 16.0f, 15.5f, 16.0f);
        f.o(pathBuilderA, 18.0f, 10.0f, 6.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 12.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBusFilled = imageVectorBuild;
        return imageVectorBuild;
    }
}

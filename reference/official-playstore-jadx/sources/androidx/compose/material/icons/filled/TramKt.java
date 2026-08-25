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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tram", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tram", "Landroidx/compose/material/icons/Icons$Filled;", "getTram", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TramKt {
    private static ImageVector _tram;

    public static final ImageVector getTram(Icons.Filled filled) {
        ImageVector imageVector = _tram;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Tram", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 16.94f, 19.0f, 8.5f);
        pathBuilderJ.curveToRelative(0.0f, -2.79f, -2.61f, -3.4f, -6.01f, -3.49f);
        pathBuilderJ.lineToRelative(0.76f, -1.51f);
        pathBuilderJ.lineTo(17.0f, 3.5f);
        pathBuilderJ.lineTo(17.0f, 2.0f);
        a.h(pathBuilderJ, 7.0f, 2.0f, 1.5f, 4.75f);
        pathBuilderJ.lineToRelative(-0.76f, 1.52f);
        pathBuilderJ.curveTo(7.86f, 5.11f, 5.0f, 5.73f, 5.0f, 8.5f);
        pathBuilderJ.verticalLineToRelative(8.44f);
        pathBuilderJ.curveToRelative(0.0f, 1.45f, 1.19f, 2.66f, 2.59f, 2.97f);
        a.h(pathBuilderJ, 6.0f, 21.5f, 0.5f, 2.23f);
        pathBuilderJ.lineToRelative(2.0f, -2.0f);
        pathBuilderJ.lineTo(14.0f, 20.0f);
        a0.a.m(pathBuilderJ, 2.0f, 2.0f, 2.0f, -0.5f);
        pathBuilderJ.lineTo(16.5f, 20.0f);
        pathBuilderJ.horizontalLineToRelative(-0.08f);
        pathBuilderJ.curveToRelative(1.69f, 0.0f, 2.58f, -1.37f, 2.58f, -3.06f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 18.5f);
        pathBuilderJ.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderJ.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        a.p(pathBuilderJ, 17.0f, 14.0f, 7.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderJ, 7.0f, 9.0f, 10.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tram = imageVectorBuild;
        return imageVectorBuild;
    }
}

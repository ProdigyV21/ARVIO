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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsBaseball", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsBaseball", "Landroidx/compose/material/icons/Icons$Outlined;", "getSportsBaseball", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsBaseballKt {
    private static ImageVector _sportsBaseball;

    public static final ImageVector getSportsBaseball(Icons.Outlined outlined) {
        ImageVector imageVector = _sportsBaseball;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SportsBaseball", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.61f, 16.78f);
        pathBuilderA.curveTo(4.6f, 15.45f, 4.0f, 13.8f, 4.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.6f, -3.45f, 1.61f, -4.78f);
        pathBuilderA.curveTo(7.06f, 8.31f, 8.0f, 10.05f, 8.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(7.06f, 15.69f, 5.61f, 16.78f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.89f, 0.0f, -3.63f, -0.66f, -5.0f, -1.76f);
        pathBuilderA.curveToRelative(1.83f, -1.47f, 3.0f, -3.71f, 3.0f, -6.24f);
        pathBuilderA.reflectiveCurveTo(8.83f, 7.23f, 7.0f, 5.76f);
        pathBuilderA.curveTo(8.37f, 4.66f, 10.11f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(3.63f, 0.66f, 5.0f, 1.76f);
        pathBuilderA.curveToRelative(-1.83f, 1.47f, -3.0f, 3.71f, -3.0f, 6.24f);
        pathBuilderA.reflectiveCurveToRelative(1.17f, 4.77f, 3.0f, 6.24f);
        pathBuilderA.curveTo(15.63f, 19.34f, 13.89f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.39f, 16.78f);
        pathBuilderA.curveTo(16.94f, 15.69f, 16.0f, 13.95f, 16.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.94f, -3.69f, 2.39f, -4.78f);
        pathBuilderA.curveTo(19.4f, 8.55f, 20.0f, 10.2f, 20.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 19.4f, 15.45f, 18.39f, 16.78f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsBaseball = imageVectorBuild;
        return imageVectorBuild;
    }
}

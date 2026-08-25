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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_queryStats", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QueryStats", "Landroidx/compose/material/icons/Icons$Sharp;", "getQueryStats", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QueryStatsKt {
    private static ImageVector _queryStats;

    public static final ImageVector getQueryStats(Icons.Sharp sharp) {
        ImageVector imageVector = _queryStats;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.QueryStats", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.88f, 18.47f);
        pathBuilderA.curveToRelative(0.44f, -0.7f, 0.7f, -1.51f, 0.7f, -2.39f);
        pathBuilderA.curveToRelative(0.0f, -2.49f, -2.01f, -4.5f, -4.5f, -4.5f);
        pathBuilderA.reflectiveCurveToRelative(-4.5f, 2.01f, -4.5f, 4.5f);
        pathBuilderA.reflectiveCurveToRelative(2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilderA.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderA.lineTo(21.58f, 23.0f);
        b.y(pathBuilderA, 23.0f, 21.58f, 19.88f, 18.47f);
        pathBuilderA.moveTo(16.08f, 18.58f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.curveTo(18.58f, 17.46f, 17.46f, 18.58f, 16.08f, 18.58f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.72f, 10.08f);
        pathBuilderA.curveToRelative(-0.74f, 0.02f, -1.45f, 0.18f, -2.1f, 0.45f);
        pathBuilderA.lineToRelative(-0.55f, -0.83f);
        pathBuilderA.lineToRelative(-3.8f, 6.18f);
        pathBuilderA.lineToRelative(-3.01f, -3.52f);
        pathBuilderA.lineToRelative(-3.63f, 5.81f);
        pathBuilderA.lineTo(1.0f, 17.0f);
        pathBuilderA.lineToRelative(5.0f, -8.0f);
        pathBuilderA.lineToRelative(3.0f, 3.5f);
        pathBuilderA.lineTo(13.0f, 6.0f);
        pathBuilderA.curveTo(13.0f, 6.0f, 15.72f, 10.08f, 15.72f, 10.08f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.31f, 10.58f);
        pathBuilderA.curveToRelative(-0.64f, -0.28f, -1.33f, -0.45f, -2.05f, -0.49f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 5.12f, -8.09f, 5.12f, -8.09f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 23.0f, 3.18f, 18.31f, 10.58f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _queryStats = imageVectorBuild;
        return imageVectorBuild;
    }
}

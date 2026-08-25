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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_queryStats", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QueryStats", "Landroidx/compose/material/icons/Icons$Rounded;", "getQueryStats", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QueryStatsKt {
    private static ImageVector _queryStats;

    public static final ImageVector getQueryStats(Icons.Rounded rounded) {
        ImageVector imageVector = _queryStats;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.QueryStats", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.88f, 18.47f);
        pathBuilderA.curveToRelative(0.48f, -0.77f, 0.75f, -1.67f, 0.69f, -2.66f);
        pathBuilderA.curveToRelative(-0.13f, -2.15f, -1.84f, -3.97f, -3.97f, -4.2f);
        pathBuilderA.curveToRelative(-2.72f, -0.3f, -5.02f, 1.81f, -5.02f, 4.47f);
        pathBuilderA.curveToRelative(0.0f, 2.49f, 2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilderA.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderA.lineToRelative(2.41f, 2.41f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.42f, 0.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.42f);
        c.z(pathBuilderA, 19.88f, 18.47f, 16.08f, 18.58f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.curveTo(18.58f, 17.46f, 17.46f, 18.58f, 16.08f, 18.58f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.72f, 10.08f);
        pathBuilderA.curveToRelative(-0.74f, 0.02f, -1.45f, 0.18f, -2.1f, 0.45f);
        pathBuilderA.lineToRelative(-0.55f, -0.83f);
        pathBuilderA.lineToRelative(-3.08f, 5.01f);
        pathBuilderA.curveToRelative(-0.36f, 0.58f, -1.17f, 0.64f, -1.61f, 0.13f);
        pathBuilderA.lineToRelative(-2.12f, -2.47f);
        pathBuilderA.lineToRelative(-3.06f, 4.9f);
        pathBuilderA.curveToRelative(-0.31f, 0.49f, -0.97f, 0.62f, -1.44f, 0.28f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.42f, -0.31f, -0.54f, -0.89f, -0.26f, -1.34f);
        pathBuilderA.lineToRelative(3.78f, -6.05f);
        pathBuilderA.curveToRelative(0.36f, -0.57f, 1.17f, -0.63f, 1.61f, -0.12f);
        pathBuilderA.lineTo(9.0f, 12.5f);
        pathBuilderA.lineToRelative(3.18f, -5.17f);
        pathBuilderA.curveToRelative(0.38f, -0.62f, 1.28f, -0.64f, 1.68f, -0.03f);
        c.z(pathBuilderA, 15.72f, 10.08f, 18.31f, 10.58f);
        pathBuilderA.curveToRelative(-0.64f, -0.28f, -1.33f, -0.45f, -2.05f, -0.49f);
        pathBuilderA.lineTo(20.8f, 2.9f);
        pathBuilderA.curveToRelative(0.31f, -0.49f, 0.97f, -0.61f, 1.43f, -0.27f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.43f, 0.31f, 0.54f, 0.9f, 0.26f, 1.34f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 18.31f, 10.58f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _queryStats = imageVectorBuild;
        return imageVectorBuild;
    }
}

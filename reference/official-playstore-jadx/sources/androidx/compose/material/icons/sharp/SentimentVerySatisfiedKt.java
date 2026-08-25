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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sentimentVerySatisfied", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SentimentVerySatisfied", "Landroidx/compose/material/icons/Icons$Sharp;", "getSentimentVerySatisfied", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SentimentVerySatisfiedKt {
    private static ImageVector _sentimentVerySatisfied;

    public static final ImageVector getSentimentVerySatisfied(Icons.Sharp sharp) {
        ImageVector imageVector = _sentimentVerySatisfied;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SentimentVerySatisfied", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(8.88f, 9.94f, 9.94f, 11.0f);
        pathBuilderJ.lineTo(11.0f, 9.94f);
        pathBuilderJ.lineTo(8.88f, 7.82f);
        b.y(pathBuilderJ, 6.76f, 9.94f, 7.82f, 11.0f);
        pathBuilderJ.moveTo(12.0f, 17.5f);
        pathBuilderJ.curveToRelative(2.33f, 0.0f, 4.31f, -1.46f, 5.11f, -3.5f);
        pathBuilderJ.lineTo(6.89f, 14.0f);
        pathBuilderJ.curveToRelative(0.8f, 2.04f, 2.78f, 3.5f, 5.11f, 3.5f);
        a.p(pathBuilderJ, 13.0f, 9.94f, 14.06f, 11.0f);
        pathBuilderJ.lineToRelative(1.06f, -1.06f);
        pathBuilderJ.lineTo(16.18f, 11.0f);
        b.D(pathBuilderJ, 1.06f, -1.06f, -2.12f, -2.12f);
        pathBuilderJ.moveTo(11.99f, 2.0f);
        pathBuilderJ.curveTo(6.47f, 2.0f, 2.0f, 6.47f, 2.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderJ.reflectiveCurveTo(22.0f, 17.53f, 22.0f, 12.0f);
        pathBuilderJ.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-4.42f, 0.0f, -8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderJ.reflectiveCurveToRelative(8.0f, 3.58f, 8.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -3.58f, 8.0f, -8.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sentimentVerySatisfied = imageVectorBuild;
        return imageVectorBuild;
    }
}

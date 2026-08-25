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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sentimentVerySatisfied", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SentimentVerySatisfied", "Landroidx/compose/material/icons/Icons$Rounded;", "getSentimentVerySatisfied", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SentimentVerySatisfiedKt {
    private static ImageVector _sentimentVerySatisfied;

    public static final ImageVector getSentimentVerySatisfied(Icons.Rounded rounded) {
        ImageVector imageVector = _sentimentVerySatisfied;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SentimentVerySatisfied", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.88f, 9.94f, 0.53f, 0.53f);
        pathBuilderR.curveToRelative(0.29f, 0.29f, 0.77f, 0.29f, 1.06f, 0.0f);
        pathBuilderR.curveToRelative(0.29f, -0.29f, 0.29f, -0.77f, 0.0f, -1.06f);
        pathBuilderR.lineToRelative(-0.88f, -0.88f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.lineToRelative(-0.89f, 0.88f);
        pathBuilderR.curveToRelative(-0.29f, 0.29f, -0.29f, 0.77f, 0.0f, 1.06f);
        pathBuilderR.curveToRelative(0.29f, 0.29f, 0.77f, 0.29f, 1.06f, 0.0f);
        b.C(pathBuilderR, 0.53f, -0.53f, 12.0f, 17.5f);
        pathBuilderR.curveToRelative(2.03f, 0.0f, 3.8f, -1.11f, 4.75f, -2.75f);
        pathBuilderR.curveToRelative(0.19f, -0.33f, -0.05f, -0.75f, -0.44f, -0.75f);
        pathBuilderR.lineTo(7.69f, 14.0f);
        pathBuilderR.curveToRelative(-0.38f, 0.0f, -0.63f, 0.42f, -0.44f, 0.75f);
        pathBuilderR.curveToRelative(0.95f, 1.64f, 2.72f, 2.75f, 4.75f, 2.75f);
        pathBuilderR.close();
        pathBuilderR.moveTo(13.53f, 10.47f);
        pathBuilderR.curveToRelative(0.29f, 0.29f, 0.77f, 0.29f, 1.06f, 0.0f);
        pathBuilderR.lineToRelative(0.53f, -0.53f);
        pathBuilderR.lineToRelative(0.53f, 0.53f);
        pathBuilderR.curveToRelative(0.29f, 0.29f, 0.77f, 0.29f, 1.06f, 0.0f);
        pathBuilderR.curveToRelative(0.29f, -0.29f, 0.29f, -0.77f, 0.0f, -1.06f);
        pathBuilderR.lineToRelative(-0.88f, -0.88f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.lineToRelative(-0.88f, 0.88f);
        pathBuilderR.curveToRelative(-0.3f, 0.29f, -0.3f, 0.77f, -0.01f, 1.06f);
        pathBuilderR.close();
        pathBuilderR.moveTo(11.99f, 2.0f);
        pathBuilderR.curveTo(6.47f, 2.0f, 2.0f, 6.47f, 2.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderR.reflectiveCurveTo(22.0f, 17.53f, 22.0f, 12.0f);
        pathBuilderR.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 20.0f);
        pathBuilderR.curveToRelative(-4.42f, 0.0f, -8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderR.reflectiveCurveToRelative(3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderR.reflectiveCurveToRelative(8.0f, 3.58f, 8.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, -3.58f, 8.0f, -8.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sentimentVerySatisfied = imageVectorBuild;
        return imageVectorBuild;
    }
}

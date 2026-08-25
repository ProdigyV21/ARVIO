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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sentimentVeryDissatisfied", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SentimentVeryDissatisfied", "Landroidx/compose/material/icons/Icons$Outlined;", "getSentimentVeryDissatisfied", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SentimentVeryDissatisfiedKt {
    private static ImageVector _sentimentVeryDissatisfied;

    public static final ImageVector getSentimentVeryDissatisfied(Icons.Outlined outlined) {
        ImageVector imageVector = _sentimentVeryDissatisfied;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SentimentVeryDissatisfied", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 13.5f);
        pathBuilderA.curveToRelative(-2.33f, 0.0f, -4.31f, 1.46f, -5.11f, 3.5f);
        pathBuilderA.horizontalLineToRelative(10.22f);
        pathBuilderA.curveToRelative(-0.8f, -2.04f, -2.78f, -3.5f, -5.11f, -3.5f);
        a.z(pathBuilderA, 7.82f, 12.0f, 1.06f, -1.06f);
        pathBuilderA.lineTo(9.94f, 12.0f);
        pathBuilderA.lineTo(11.0f, 10.94f);
        pathBuilderA.lineTo(9.94f, 9.88f);
        pathBuilderA.lineTo(11.0f, 8.82f);
        pathBuilderA.lineTo(9.94f, 7.76f);
        pathBuilderA.lineTo(8.88f, 8.82f);
        pathBuilderA.lineTo(7.82f, 7.76f);
        pathBuilderA.lineTo(6.76f, 8.82f);
        b.D(pathBuilderA, 1.06f, 1.06f, -1.06f, 1.06f);
        pathBuilderA.moveTo(11.99f, 2.0f);
        pathBuilderA.curveTo(6.47f, 2.0f, 2.0f, 6.47f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderA.reflectiveCurveTo(22.0f, 17.53f, 22.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-4.42f, 0.0f, -8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, 3.58f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.58f, 8.0f, -8.0f, 8.0f);
        a.z(pathBuilderA, 16.18f, 7.76f, -1.06f, 1.06f);
        pathBuilderA.lineToRelative(-1.06f, -1.06f);
        pathBuilderA.lineTo(13.0f, 8.82f);
        pathBuilderA.lineToRelative(1.06f, 1.06f);
        pathBuilderA.lineTo(13.0f, 10.94f);
        pathBuilderA.lineTo(14.06f, 12.0f);
        pathBuilderA.lineToRelative(1.06f, -1.06f);
        pathBuilderA.lineTo(16.18f, 12.0f);
        pathBuilderA.lineToRelative(1.06f, -1.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -1.06f, -1.06f, 1.06f, -1.06f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sentimentVeryDissatisfied = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_score", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Score", "Landroidx/compose/material/icons/Icons$Rounded;", "getScore", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScoreKt {
    private static ImageVector _score;

    public static final ImageVector getScore(Icons.Rounded rounded) {
        ImageVector imageVector = _score;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Score", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 3.0f, 5.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(21.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 5.75f);
        pathBuilderJ.curveToRelative(0.0f, -0.41f, 0.34f, -0.75f, 0.75f, -0.75f);
        pathBuilderJ.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderJ.lineTo(13.5f, 8.0f);
        pathBuilderJ.lineToRelative(1.79f, -2.69f);
        pathBuilderJ.curveToRelative(0.13f, -0.19f, 0.35f, -0.31f, 0.59f, -0.31f);
        pathBuilderJ.curveToRelative(0.56f, 0.0f, 0.9f, 0.63f, 0.59f, 1.1f);
        pathBuilderJ.lineTo(15.2f, 8.0f);
        pathBuilderJ.lineToRelative(1.27f, 1.9f);
        pathBuilderJ.curveToRelative(0.31f, 0.47f, -0.02f, 1.1f, -0.59f, 1.1f);
        pathBuilderJ.curveToRelative(-0.24f, 0.0f, -0.46f, -0.12f, -0.59f, -0.31f);
        pathBuilderJ.lineTo(13.5f, 8.0f);
        pathBuilderJ.verticalLineToRelative(2.25f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderJ.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        b.f(pathBuilderJ, -4.5f, 7.0f, 8.25f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(1.5f);
        pathBuilderJ.lineTo(9.5f, 6.5f);
        pathBuilderJ.lineTo(7.75f, 6.5f);
        pathBuilderJ.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderJ.reflectiveCurveTo(7.34f, 5.0f, 7.75f, 5.0f);
        pathBuilderJ.lineTo(10.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(1.75f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a.h(pathBuilderJ, 8.5f, 8.75f, 0.75f, 1.75f);
        pathBuilderJ.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderJ.reflectiveCurveToRelative(-0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderJ.lineTo(8.0f, 11.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        c.z(pathBuilderJ, 7.0f, 8.25f, 18.74f, 13.26f);
        pathBuilderJ.lineToRelative(-5.03f, 5.03f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(9.0f, 15.0f);
        pathBuilderJ.lineToRelative(-2.49f, 2.49f);
        pathBuilderJ.curveToRelative(-0.56f, 0.56f, -1.51f, 0.16f, -1.51f, -0.62f);
        pathBuilderJ.curveToRelative(0.0f, -0.23f, 0.09f, -0.46f, 0.26f, -0.62f);
        pathBuilderJ.lineToRelative(3.03f, -3.03f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(13.0f, 16.5f);
        pathBuilderJ.lineToRelative(4.49f, -4.49f);
        pathBuilderJ.curveToRelative(0.56f, -0.56f, 1.51f, -0.16f, 1.51f, 0.62f);
        pathBuilderJ.curveToRelative(0.0f, 0.24f, -0.09f, 0.46f, -0.26f, 0.63f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _score = imageVectorBuild;
        return imageVectorBuild;
    }
}

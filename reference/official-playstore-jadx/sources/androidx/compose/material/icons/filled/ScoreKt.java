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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_score", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Score", "Landroidx/compose/material/icons/Icons$Filled;", "getScore", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScoreKt {
    private static ImageVector _score;

    public static final ImageVector getScore(Icons.Filled filled) {
        ImageVector imageVector = _score;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Score", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        b.w(pathBuilderJ, 12.0f, 5.0f, 1.5f, 3.0f);
        pathBuilderJ.lineToRelative(2.0f, -3.0f);
        pathBuilderJ.horizontalLineToRelative(1.7f);
        pathBuilderJ.lineToRelative(-2.0f, 3.0f);
        pathBuilderJ.lineToRelative(2.0f, 3.0f);
        a0.b.v(pathBuilderJ, -1.7f, -2.0f, -3.0f, 3.0f);
        b.y(pathBuilderJ, 12.0f, 11.0f, 12.0f, 5.0f);
        pathBuilderJ.moveTo(7.0f, 7.25f);
        pathBuilderJ.horizontalLineToRelative(2.5f);
        pathBuilderJ.lineTo(9.5f, 6.5f);
        pathBuilderJ.lineTo(7.0f, 6.5f);
        b.A(pathBuilderJ, 7.0f, 5.0f, 4.0f, 3.75f);
        pathBuilderJ.lineTo(8.5f, 8.75f);
        pathBuilderJ.verticalLineToRelative(0.75f);
        pathBuilderJ.lineTo(11.0f, 9.5f);
        pathBuilderJ.lineTo(11.0f, 11.0f);
        b.y(pathBuilderJ, 7.0f, 11.0f, 7.0f, 7.25f);
        pathBuilderJ.moveTo(19.0f, 13.0f);
        pathBuilderJ.lineToRelative(-6.0f, 6.0f);
        pathBuilderJ.lineToRelative(-4.0f, -4.0f);
        pathBuilderJ.lineToRelative(-4.0f, 4.0f);
        pathBuilderJ.verticalLineToRelative(-2.5f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.lineToRelative(4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 6.0f, -6.0f, 19.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _score = imageVectorBuild;
        return imageVectorBuild;
    }
}

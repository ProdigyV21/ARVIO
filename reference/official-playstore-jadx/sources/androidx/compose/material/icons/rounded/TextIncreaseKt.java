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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textIncrease", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextIncrease", "Landroidx/compose/material/icons/Icons$Rounded;", "getTextIncrease", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextIncreaseKt {
    private static ImageVector _textIncrease;

    public static final ImageVector getTextIncrease(Icons.Rounded rounded) {
        ImageVector imageVector = _textIncrease;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TextIncrease", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.61f, 19.0f, 2.61f, 19.0f);
        pathBuilderJ.curveToRelative(0.48f, 0.0f, 0.91f, -0.3f, 1.06f, -0.75f);
        pathBuilderJ.lineToRelative(1.01f, -2.83f);
        pathBuilderJ.horizontalLineToRelative(5.65f);
        pathBuilderJ.lineToRelative(0.99f, 2.82f);
        pathBuilderJ.curveTo(11.48f, 18.7f, 11.91f, 19.0f, 12.39f, 19.0f);
        pathBuilderJ.curveToRelative(0.79f, 0.0f, 1.33f, -0.79f, 1.05f, -1.52f);
        pathBuilderJ.lineTo(9.19f, 6.17f);
        pathBuilderJ.curveTo(8.93f, 5.47f, 8.25f, 5.0f, 7.5f, 5.0f);
        pathBuilderJ.reflectiveCurveTo(6.07f, 5.47f, 5.81f, 6.17f);
        pathBuilderJ.lineTo(1.56f, 17.48f);
        pathBuilderJ.curveTo(1.28f, 18.21f, 1.83f, 19.0f, 2.61f, 19.0f);
        a.g(pathBuilderJ, 7.44f, 7.6f, 0.12f);
        pathBuilderJ.lineToRelative(2.03f, 5.79f);
        w.a.w(pathBuilderJ, 5.41f, 7.44f, 7.6f);
        pathBuilderJ.moveTo(15.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.verticalLineTo(9.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.curveTo(15.45f, 13.0f, 15.0f, 12.55f, 15.0f, 12.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textIncrease = imageVectorBuild;
        return imageVectorBuild;
    }
}

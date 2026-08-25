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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatUnderlined", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatUnderlined", "Landroidx/compose/material/icons/Icons$Rounded;", "getFormatUnderlined", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatUnderlinedKt {
    private static ImageVector _formatUnderlined;

    public static final ImageVector getFormatUnderlined(Icons.Rounded rounded) {
        ImageVector imageVector = _formatUnderlined;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FormatUnderlined", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.79f, 16.95f);
        pathBuilderA.curveToRelative(3.03f, -0.39f, 5.21f, -3.11f, 5.21f, -6.16f);
        pathBuilderA.verticalLineTo(4.25f);
        pathBuilderA.curveTo(18.0f, 3.56f, 17.44f, 3.0f, 16.75f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.25f, 0.56f, -1.25f, 1.25f);
        pathBuilderA.verticalLineToRelative(6.65f);
        pathBuilderA.curveToRelative(0.0f, 1.67f, -1.13f, 3.19f, -2.77f, 3.52f);
        pathBuilderA.curveToRelative(-2.25f, 0.47f, -4.23f, -1.25f, -4.23f, -3.42f);
        pathBuilderA.verticalLineTo(4.25f);
        pathBuilderA.curveTo(8.5f, 3.56f, 7.94f, 3.0f, 7.25f, 3.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 3.56f, 6.0f, 4.25f);
        pathBuilderA.verticalLineTo(11.0f);
        pathBuilderA.curveToRelative(0.0f, 3.57f, 3.13f, 6.42f, 6.79f, 5.95f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatUnderlined = imageVectorBuild;
        return imageVectorBuild;
    }
}

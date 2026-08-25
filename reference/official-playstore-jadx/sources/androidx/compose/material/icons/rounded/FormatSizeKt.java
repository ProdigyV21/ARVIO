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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatSize", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatSize", "Landroidx/compose/material/icons/Icons$Rounded;", "getFormatSize", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatSizeKt {
    private static ImageVector _formatSize;

    public static final ImageVector getFormatSize(Icons.Rounded rounded) {
        ImageVector imageVector = _formatSize;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FormatSize", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 5.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineTo(14.0f);
        pathBuilderA.verticalLineToRelative(10.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.horizontalLineToRelative(3.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(21.33f, 4.0f, 20.5f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-10.0f);
        pathBuilderA.curveTo(9.67f, 4.0f, 9.0f, 4.67f, 9.0f, 5.5f);
        c.C(pathBuilderA, 4.5f, 12.0f, 6.0f, 5.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.0f, 18.33f, 9.0f, 17.5f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.horizontalLineToRelative(1.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(11.33f, 9.0f, 10.5f, 9.0f);
        pathBuilderA.horizontalLineToRelative(-6.0f);
        pathBuilderA.curveTo(3.67f, 9.0f, 3.0f, 9.67f, 3.0f, 10.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 3.67f, 12.0f, 4.5f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatSize = imageVectorBuild;
        return imageVectorBuild;
    }
}

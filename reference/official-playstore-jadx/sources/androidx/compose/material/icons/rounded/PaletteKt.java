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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_palette", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Palette", "Landroidx/compose/material/icons/Icons$Rounded;", "getPalette", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PaletteKt {
    private static ImageVector _palette;

    public static final ImageVector getPalette(Icons.Rounded rounded) {
        ImageVector imageVector = _palette;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Palette", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.49f, 2.0f, 2.0f, 6.49f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.49f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -0.61f, -0.23f, -1.2f, -0.64f, -1.67f);
        pathBuilderA.curveToRelative(-0.08f, -0.1f, -0.13f, -0.21f, -0.13f, -0.33f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.horizontalLineTo(16.0f);
        pathBuilderA.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderA.curveTo(22.0f, 6.04f, 17.51f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.5f, 13.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.curveTo(19.0f, 12.33f, 18.33f, 13.0f, 17.5f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.5f, 9.0f);
        pathBuilderA.curveTo(13.67f, 9.0f, 13.0f, 8.33f, 13.0f, 7.5f);
        pathBuilderA.curveTo(13.0f, 6.67f, 13.67f, 6.0f, 14.5f, 6.0f);
        pathBuilderA.reflectiveCurveTo(16.0f, 6.67f, 16.0f, 7.5f);
        pathBuilderA.curveTo(16.0f, 8.33f, 15.33f, 9.0f, 14.5f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 11.5f);
        pathBuilderA.curveTo(5.0f, 10.67f, 5.67f, 10.0f, 6.5f, 10.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 10.67f, 8.0f, 11.5f);
        pathBuilderA.curveTo(8.0f, 12.33f, 7.33f, 13.0f, 6.5f, 13.0f);
        pathBuilderA.reflectiveCurveTo(5.0f, 12.33f, 5.0f, 11.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 7.5f);
        pathBuilderA.curveTo(11.0f, 8.33f, 10.33f, 9.0f, 9.5f, 9.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 8.33f, 8.0f, 7.5f);
        pathBuilderA.curveTo(8.0f, 6.67f, 8.67f, 6.0f, 9.5f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 11.0f, 6.67f, 11.0f, 7.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _palette = imageVectorBuild;
        return imageVectorBuild;
    }
}

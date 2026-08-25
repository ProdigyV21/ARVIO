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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatColorReset", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatColorReset", "Landroidx/compose/material/icons/Icons$Filled;", "getFormatColorReset", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatColorResetKt {
    private static ImageVector _formatColorReset;

    public static final ImageVector getFormatColorReset(Icons.Filled filled) {
        ImageVector imageVector = _formatColorReset;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FormatColorReset", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, -4.0f, -6.0f, -10.8f, -6.0f, -10.8f);
        pathBuilderA.reflectiveCurveToRelative(-1.33f, 1.51f, -2.73f, 3.52f);
        pathBuilderA.lineToRelative(8.59f, 8.59f);
        pathBuilderA.curveToRelative(0.09f, -0.42f, 0.14f, -0.86f, 0.14f, -1.31f);
        a.p(pathBuilderA, 17.12f, 17.12f, 12.5f, 12.5f);
        pathBuilderA.lineTo(5.27f, 5.27f);
        pathBuilderA.lineTo(4.0f, 6.55f);
        pathBuilderA.lineToRelative(3.32f, 3.32f);
        pathBuilderA.curveTo(6.55f, 11.32f, 6.0f, 12.79f, 6.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.curveToRelative(1.52f, 0.0f, 2.9f, -0.57f, 3.96f, -1.5f);
        pathBuilderA.lineToRelative(2.63f, 2.63f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 1.27f, -1.27f, -2.74f, -2.74f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatColorReset = imageVectorBuild;
        return imageVectorBuild;
    }
}

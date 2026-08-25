package androidx.compose.material.icons.automirrored.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_redo", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Redo", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getRedo", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RedoKt {
    private static ImageVector _redo;

    public static final ImageVector getRedo(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _redo;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.Redo", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.4f, 10.6f);
        pathBuilderA.curveTo(16.55f, 8.99f, 14.15f, 8.0f, 11.5f, 8.0f);
        pathBuilderA.curveToRelative(-4.16f, 0.0f, -7.74f, 2.42f, -9.44f, 5.93f);
        pathBuilderA.curveToRelative(-0.32f, 0.67f, 0.04f, 1.47f, 0.75f, 1.71f);
        pathBuilderA.curveToRelative(0.59f, 0.2f, 1.23f, -0.08f, 1.5f, -0.64f);
        pathBuilderA.curveToRelative(1.3f, -2.66f, 4.03f, -4.5f, 7.19f, -4.5f);
        pathBuilderA.curveToRelative(1.95f, 0.0f, 3.73f, 0.72f, 5.12f, 1.88f);
        pathBuilderA.lineToRelative(-1.91f, 1.91f);
        pathBuilderA.curveToRelative(-0.63f, 0.63f, -0.19f, 1.71f, 0.7f, 1.71f);
        pathBuilderA.horizontalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(9.41f);
        pathBuilderA.curveToRelative(0.0f, -0.89f, -1.08f, -1.34f, -1.71f, -0.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -1.89f, 1.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _redo = imageVectorBuild;
        return imageVectorBuild;
    }
}

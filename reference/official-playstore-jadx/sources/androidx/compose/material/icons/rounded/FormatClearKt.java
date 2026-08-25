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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatClear", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatClear", "Landroidx/compose/material/icons/Icons$Rounded;", "getFormatClear", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatClearKt {
    private static ImageVector _formatClear;

    public static final ImageVector getFormatClear(Icons.Rounded rounded) {
        ImageVector imageVector = _formatClear;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FormatClear", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.5f, 8.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(19.33f, 5.0f, 18.5f, 5.0f);
        pathBuilderA.lineTo(6.39f, 5.0f);
        pathBuilderA.lineToRelative(3.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(1.83f);
        pathBuilderA.lineToRelative(-0.55f, 1.28f);
        pathBuilderA.lineToRelative(2.09f, 2.09f);
        f.n(pathBuilderA, 14.21f, 8.0f, 4.29f);
        pathBuilderA.moveTo(17.44f, 18.88f);
        pathBuilderA.lineTo(4.12f, 5.56f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(6.26f, 6.26f);
        pathBuilderA.lineToRelative(-1.65f, 3.84f);
        pathBuilderA.curveToRelative(-0.39f, 0.92f, 0.28f, 1.93f, 1.27f, 1.93f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.05f, -0.33f, 1.27f, -0.84f);
        pathBuilderA.lineToRelative(1.21f, -2.83f);
        pathBuilderA.lineToRelative(4.95f, 4.95f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.4f, -0.38f, 0.4f, -1.01f, 0.01f, -1.4f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatClear = imageVectorBuild;
        return imageVectorBuild;
    }
}

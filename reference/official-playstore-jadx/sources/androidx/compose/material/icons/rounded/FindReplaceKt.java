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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_findReplace", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FindReplace", "Landroidx/compose/material/icons/Icons$Rounded;", "getFindReplace", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FindReplaceKt {
    private static ImageVector _findReplace;

    public static final ImageVector getFindReplace(Icons.Rounded rounded) {
        ImageVector imageVector = _findReplace;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FindReplace", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.0f, 6.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.63f, 0.56f, 3.54f, 1.46f);
        pathBuilderA.lineToRelative(-1.69f, 1.69f);
        pathBuilderA.curveToRelative(-0.31f, 0.31f, -0.09f, 0.85f, 0.36f, 0.85f);
        pathBuilderA.horizontalLineToRelative(4.29f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.lineTo(18.0f, 5.21f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderA.lineToRelative(-1.2f, 1.2f);
        pathBuilderA.curveTo(14.68f, 4.78f, 12.93f, 4.0f, 11.0f, 4.0f);
        pathBuilderA.curveTo(7.96f, 4.0f, 5.38f, 5.94f, 4.42f, 8.64f);
        pathBuilderA.curveToRelative(-0.24f, 0.66f, 0.23f, 1.36f, 0.93f, 1.36f);
        pathBuilderA.curveToRelative(0.42f, 0.0f, 0.79f, -0.26f, 0.93f, -0.66f);
        pathBuilderA.curveTo(6.96f, 7.4f, 8.82f, 6.0f, 11.0f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.64f, 15.14f);
        pathBuilderA.curveToRelative(0.4f, -0.54f, 0.72f, -1.15f, 0.95f, -1.8f);
        pathBuilderA.curveToRelative(0.23f, -0.65f, -0.25f, -1.34f, -0.94f, -1.34f);
        pathBuilderA.curveToRelative(-0.42f, 0.0f, -0.79f, 0.26f, -0.93f, 0.66f);
        pathBuilderA.curveTo(15.04f, 14.6f, 13.18f, 16.0f, 11.0f, 16.0f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.63f, -0.56f, -3.54f, -1.46f);
        pathBuilderA.lineToRelative(1.69f, -1.69f);
        pathBuilderA.curveToRelative(0.31f, -0.31f, 0.09f, -0.85f, -0.36f, -0.85f);
        pathBuilderA.lineTo(4.5f, 12.0f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(4.29f);
        pathBuilderA.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderA.lineToRelative(1.2f, -1.2f);
        pathBuilderA.curveTo(7.32f, 17.22f, 9.07f, 18.0f, 11.0f, 18.0f);
        pathBuilderA.curveToRelative(1.55f, 0.0f, 2.98f, -0.51f, 4.14f, -1.36f);
        pathBuilderA.lineToRelative(4.11f, 4.11f);
        pathBuilderA.curveToRelative(0.41f, 0.41f, 1.08f, 0.41f, 1.49f, 0.0f);
        pathBuilderA.curveToRelative(0.41f, -0.41f, 0.41f, -1.08f, 0.0f, -1.49f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -4.1f, -4.12f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _findReplace = imageVectorBuild;
        return imageVectorBuild;
    }
}

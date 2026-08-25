package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatColorReset", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatColorReset", "Landroidx/compose/material/icons/Icons$Outlined;", "getFormatColorReset", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatColorResetKt {
    private static ImageVector _formatColorReset;

    public static final ImageVector getFormatColorReset(Icons.Outlined outlined) {
        ImageVector imageVector = _formatColorReset;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FormatColorReset", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.36f);
        pathBuilderA.curveToRelative(1.53f, 2.0f, 3.08f, 4.43f, 3.71f, 6.24f);
        pathBuilderA.lineToRelative(2.23f, 2.23f);
        pathBuilderA.curveToRelative(0.03f, -0.27f, 0.06f, -0.55f, 0.06f, -0.83f);
        pathBuilderA.curveToRelative(0.0f, -3.98f, -6.0f, -10.8f, -6.0f, -10.8f);
        pathBuilderA.reflectiveCurveToRelative(-1.18f, 1.35f, -2.5f, 3.19f);
        pathBuilderA.lineToRelative(1.44f, 1.44f);
        pathBuilderA.curveToRelative(0.34f, -0.51f, 0.7f, -1.0f, 1.06f, -1.47f);
        a.p(pathBuilderA, 5.41f, 5.14f, 4.0f, 6.55f);
        pathBuilderA.lineToRelative(3.32f, 3.32f);
        pathBuilderA.curveTo(6.55f, 11.33f, 6.0f, 12.79f, 6.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.curveToRelative(1.52f, 0.0f, 2.9f, -0.57f, 3.95f, -1.5f);
        pathBuilderA.lineToRelative(2.63f, 2.63f);
        b.y(pathBuilderA, 20.0f, 19.72f, 5.41f, 5.14f);
        pathBuilderA.moveTo(12.0f, 18.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, 0.32f, -1.62f, 0.81f, -2.64f);
        pathBuilderA.lineToRelative(5.72f, 5.72f);
        pathBuilderA.curveToRelative(-0.7f, 0.56f, -1.57f, 0.92f, -2.53f, 0.92f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatColorReset = imageVectorBuild;
        return imageVectorBuild;
    }
}

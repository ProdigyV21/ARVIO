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
import v.a;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rotateRight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RotateRight", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getRotateRight", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RotateRightKt {
    private static ImageVector _rotateRight;

    public static final ImageVector getRotateRight(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _rotateRight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.RotateRight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.83f, 4.83f, 12.7f, 2.7f);
        pathBuilderJ.curveToRelative(-0.62f, -0.62f, -1.7f, -0.18f, -1.7f, 0.71f);
        pathBuilderJ.verticalLineToRelative(0.66f);
        pathBuilderJ.curveTo(7.06f, 4.56f, 4.0f, 7.92f, 4.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.64f, 2.43f, 6.71f, 5.77f, 7.68f);
        pathBuilderJ.curveToRelative(0.62f, 0.18f, 1.23f, -0.32f, 1.23f, -0.96f);
        pathBuilderJ.verticalLineToRelative(-0.03f);
        pathBuilderJ.curveToRelative(0.0f, -0.43f, -0.27f, -0.82f, -0.68f, -0.94f);
        pathBuilderJ.curveTo(7.82f, 17.03f, 6.0f, 14.73f, 6.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.97f, 2.16f, -5.43f, 5.0f, -5.91f);
        pathBuilderJ.verticalLineToRelative(1.53f);
        pathBuilderJ.curveToRelative(0.0f, 0.89f, 1.07f, 1.33f, 1.7f, 0.71f);
        pathBuilderJ.lineToRelative(2.13f, -2.08f);
        pathBuilderJ.curveToRelative(0.4f, -0.38f, 0.4f, -1.02f, 0.0f, -1.42f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.67f, 9.76f);
        pathBuilderJ.curveToRelative(-0.16f, -0.55f, -0.38f, -1.08f, -0.66f, -1.59f);
        pathBuilderJ.curveToRelative(-0.31f, -0.57f, -1.1f, -0.66f, -1.56f, -0.2f);
        pathBuilderJ.lineToRelative(-0.01f, 0.01f);
        pathBuilderJ.curveToRelative(-0.31f, 0.31f, -0.38f, 0.78f, -0.17f, 1.16f);
        pathBuilderJ.curveToRelative(0.2f, 0.37f, 0.36f, 0.76f, 0.48f, 1.16f);
        pathBuilderJ.curveToRelative(0.12f, 0.42f, 0.51f, 0.7f, 0.94f, 0.7f);
        pathBuilderJ.horizontalLineToRelative(0.02f);
        pathBuilderJ.curveToRelative(0.65f, 0.0f, 1.15f, -0.62f, 0.96f, -1.24f);
        d.k(pathBuilderJ, 13.0f, 18.68f, 0.02f);
        pathBuilderJ.curveToRelative(0.0f, 0.65f, 0.62f, 1.14f, 1.24f, 0.96f);
        pathBuilderJ.curveToRelative(0.55f, -0.16f, 1.08f, -0.38f, 1.59f, -0.66f);
        pathBuilderJ.curveToRelative(0.57f, -0.31f, 0.66f, -1.1f, 0.2f, -1.56f);
        pathBuilderJ.lineToRelative(-0.02f, -0.02f);
        pathBuilderJ.curveToRelative(-0.31f, -0.31f, -0.78f, -0.38f, -1.16f, -0.17f);
        pathBuilderJ.curveToRelative(-0.37f, 0.21f, -0.76f, 0.37f, -1.16f, 0.49f);
        pathBuilderJ.curveToRelative(-0.41f, 0.12f, -0.69f, 0.51f, -0.69f, 0.94f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(17.44f, 16.03f);
        pathBuilderJ.curveToRelative(0.46f, 0.46f, 1.25f, 0.37f, 1.56f, -0.2f);
        pathBuilderJ.curveToRelative(0.28f, -0.51f, 0.5f, -1.04f, 0.67f, -1.59f);
        pathBuilderJ.curveToRelative(0.18f, -0.62f, -0.31f, -1.24f, -0.96f, -1.24f);
        pathBuilderJ.horizontalLineToRelative(-0.02f);
        pathBuilderJ.curveToRelative(-0.44f, 0.0f, -0.82f, 0.28f, -0.94f, 0.7f);
        pathBuilderJ.curveToRelative(-0.12f, 0.4f, -0.28f, 0.79f, -0.48f, 1.17f);
        pathBuilderJ.curveToRelative(-0.21f, 0.38f, -0.13f, 0.86f, 0.17f, 1.16f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rotateRight = imageVectorBuild;
        return imageVectorBuild;
    }
}

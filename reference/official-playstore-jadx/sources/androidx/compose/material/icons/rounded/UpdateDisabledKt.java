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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_updateDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "UpdateDisabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getUpdateDisabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UpdateDisabledKt {
    private static ImageVector _updateDisabled;

    public static final ImageVector getUpdateDisabled(Icons.Rounded rounded) {
        ImageVector imageVector = _updateDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.UpdateDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.49f, 20.49f, 3.51f, 3.51f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(2.31f, 2.31f);
        pathBuilderJ.curveTo(3.57f, 8.56f, 3.05f, 10.09f, 3.0f, 11.74f);
        pathBuilderJ.curveTo(2.86f, 16.83f, 6.94f, 21.0f, 12.0f, 21.0f);
        pathBuilderJ.curveToRelative(1.76f, 0.0f, 3.39f, -0.52f, 4.78f, -1.39f);
        pathBuilderJ.lineToRelative(2.29f, 2.29f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveTo(20.88f, 21.51f, 20.88f, 20.88f, 20.49f, 20.49f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(10.72f, 18.89f);
        pathBuilderJ.curveToRelative(-2.78f, -0.49f, -5.04f, -2.71f, -5.58f, -5.47f);
        pathBuilderJ.curveToRelative(-0.34f, -1.72f, -0.03f, -3.36f, 0.72f, -4.73f);
        pathBuilderJ.lineToRelative(9.46f, 9.46f);
        pathBuilderJ.curveTo(13.98f, 18.87f, 12.4f, 19.18f, 10.72f, 18.89f);
        d.k(pathBuilderJ, 13.0f, 8.0f, 2.17f);
        pathBuilderJ.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(8.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveTo(13.0f, 7.45f, 13.0f, 8.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.72f, 14.23f);
        pathBuilderJ.curveToRelative(-0.23f, 0.92f, -0.61f, 1.77f, -1.1f, 2.55f);
        pathBuilderJ.lineToRelative(-1.47f, -1.47f);
        pathBuilderJ.curveToRelative(0.27f, -0.5f, 0.49f, -1.03f, 0.63f, -1.59f);
        pathBuilderJ.curveTo(18.89f, 13.3f, 19.29f, 13.0f, 19.73f, 13.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveTo(20.38f, 13.0f, 20.88f, 13.61f, 20.72f, 14.23f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(7.24f, 4.41f);
        pathBuilderJ.curveToRelative(1.46f, -0.93f, 3.18f, -1.45f, 5.0f, -1.41f);
        pathBuilderJ.curveToRelative(2.65f, 0.07f, 5.0f, 1.28f, 6.6f, 3.16f);
        pathBuilderJ.lineToRelative(1.31f, -1.31f);
        pathBuilderJ.curveTo(20.46f, 4.54f, 21.0f, 4.76f, 21.0f, 5.21f);
        pathBuilderJ.verticalLineTo(9.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderJ.horizontalLineToRelative(-4.29f);
        pathBuilderJ.curveToRelative(-0.45f, 0.0f, -0.67f, -0.54f, -0.35f, -0.85f);
        pathBuilderJ.lineToRelative(1.55f, -1.55f);
        pathBuilderJ.curveTo(16.12f, 6.02f, 14.18f, 5.0f, 12.0f, 5.0f);
        pathBuilderJ.curveToRelative(-1.2f, 0.0f, -2.33f, 0.31f, -3.32f, 0.85f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 7.24f, 4.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _updateDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_freeCancellation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FreeCancellation", "Landroidx/compose/material/icons/Icons$Rounded;", "getFreeCancellation", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FreeCancellationKt {
    private static ImageVector _freeCancellation;

    public static final ImageVector getFreeCancellation(Icons.Rounded rounded) {
        ImageVector imageVector = _freeCancellation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FreeCancellation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(10.79f, 20.0f, 5.0f, 10.0f, 14.0f);
        pathBuilderC.verticalLineToRelative(2.96f);
        pathBuilderC.curveToRelative(0.0f, 0.89f, 1.08f, 1.34f, 1.71f, 0.71f);
        pathBuilderC.lineToRelative(0.0f, 0.0f);
        pathBuilderC.curveToRelative(0.19f, -0.19f, 0.29f, -0.44f, 0.29f, -0.71f);
        pathBuilderC.verticalLineTo(6.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderC.horizontalLineToRelative(-1.0f);
        pathBuilderC.verticalLineTo(3.0f);
        pathBuilderC.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderC.horizontalLineToRelative(0.0f);
        pathBuilderC.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        w.a.y(pathBuilderC, 1.0f, 8.0f, 3.0f);
        pathBuilderC.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderC.horizontalLineToRelative(0.0f);
        pathBuilderC.curveTo(6.45f, 2.0f, 6.0f, 2.45f, 6.0f, 3.0f);
        pathBuilderC.verticalLineToRelative(1.0f);
        pathBuilderC.horizontalLineTo(5.0f);
        pathBuilderC.curveTo(3.89f, 4.0f, 3.01f, 4.9f, 3.01f, 6.0f);
        pathBuilderC.lineTo(3.0f, 20.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(5.79f);
        pathBuilderC.curveToRelative(0.89f, 0.0f, 1.34f, -1.08f, 0.71f, -1.71f);
        pathBuilderC.lineToRelative(0.0f, 0.0f);
        pathBuilderC.curveTo(11.31f, 20.11f, 11.06f, 20.0f, 10.79f, 20.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(13.71f, 18.26f);
        pathBuilderC.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderC.lineToRelative(1.41f, 1.41f);
        pathBuilderC.lineToRelative(3.54f, -3.54f);
        pathBuilderC.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderC.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderC.lineToRelative(-4.24f, 4.24f);
        pathBuilderC.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderC.lineToRelative(-2.12f, -2.12f);
        pathBuilderC.curveTo(13.32f, 19.28f, 13.32f, 18.65f, 13.71f, 18.26f);
        pathBuilderC.close();
        pathBuilderC.moveTo(11.29f, 16.29f);
        pathBuilderC.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderC.lineTo(9.0f, 15.41f);
        pathBuilderC.lineToRelative(-0.88f, 0.88f);
        pathBuilderC.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderC.reflectiveCurveToRelative(-0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderC.lineTo(7.59f, 14.0f);
        pathBuilderC.lineToRelative(-0.88f, -0.88f);
        pathBuilderC.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderC.reflectiveCurveToRelative(1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderC.lineTo(9.0f, 12.59f);
        pathBuilderC.lineToRelative(0.88f, -0.88f);
        pathBuilderC.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderC.reflectiveCurveToRelative(0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderC.lineTo(10.41f, 14.0f);
        pathBuilderC.lineToRelative(0.88f, 0.88f);
        pathBuilderC.curveTo(11.68f, 15.27f, 11.68f, 15.9f, 11.29f, 16.29f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _freeCancellation = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_replayCircleFilled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ReplayCircleFilled", "Landroidx/compose/material/icons/Icons$Rounded;", "getReplayCircleFilled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReplayCircleFilledKt {
    private static ImageVector _replayCircleFilled;

    public static final ImageVector getReplayCircleFilled(Icons.Rounded rounded) {
        ImageVector imageVector = _replayCircleFilled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ReplayCircleFilled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 12.74f);
        pathBuilderA.curveToRelative(-0.12f, 3.09f, -2.67f, 5.64f, -5.76f, 5.76f);
        pathBuilderA.curveToRelative(-3.01f, 0.12f, -5.56f, -1.99f, -6.12f, -4.82f);
        pathBuilderA.curveTo(5.99f, 13.07f, 6.48f, 12.5f, 7.1f, 12.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.47f, 0.0f, 0.88f, 0.33f, 0.98f, 0.8f);
        pathBuilderA.curveToRelative(0.42f, 2.07f, 2.44f, 3.57f, 4.72f, 3.12f);
        pathBuilderA.curveToRelative(1.56f, -0.3f, 2.82f, -1.56f, 3.12f, -3.12f);
        pathBuilderA.curveToRelative(0.5f, -2.56f, -1.45f, -4.8f, -3.92f, -4.8f);
        pathBuilderA.verticalLineToRelative(1.79f);
        pathBuilderA.curveToRelative(0.0f, 0.45f, -0.54f, 0.67f, -0.85f, 0.35f);
        pathBuilderA.lineTo(8.35f, 7.85f);
        pathBuilderA.curveToRelative(-0.2f, -0.2f, -0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderA.lineToRelative(2.79f, -2.79f);
        pathBuilderA.curveTo(11.46f, 4.04f, 12.0f, 4.26f, 12.0f, 4.71f);
        pathBuilderA.verticalLineTo(6.5f);
        pathBuilderA.curveTo(15.39f, 6.5f, 18.13f, 9.32f, 18.0f, 12.74f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _replayCircleFilled = imageVectorBuild;
        return imageVectorBuild;
    }
}

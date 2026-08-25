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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_taskAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TaskAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getTaskAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TaskAltKt {
    private static ImageVector _taskAlt;

    public static final ImageVector getTaskAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _taskAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TaskAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.29f, 5.89f, -10.0f, 10.0f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderR.lineToRelative(-2.83f, -2.83f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.lineToRelative(9.29f, -9.29f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveTo(21.68f, 4.87f, 21.68f, 5.5f, 21.29f, 5.89f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.77f, 2.74f);
        pathBuilderR.curveToRelative(-1.69f, -0.69f, -3.61f, -0.93f, -5.61f, -0.57f);
        pathBuilderR.curveTo(6.09f, 2.9f, 2.84f, 6.18f, 2.15f, 10.25f);
        pathBuilderR.curveTo(1.01f, 17.0f, 6.63f, 22.78f, 13.34f, 21.91f);
        pathBuilderR.curveToRelative(3.96f, -0.51f, 7.28f, -3.46f, 8.32f, -7.31f);
        pathBuilderR.curveToRelative(0.4f, -1.47f, 0.44f, -2.89f, 0.21f, -4.22f);
        pathBuilderR.curveToRelative(-0.13f, -0.8f, -1.12f, -1.11f, -1.7f, -0.54f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(-0.23f, 0.23f, -0.33f, 0.57f, -0.27f, 0.89f);
        pathBuilderR.curveToRelative(0.22f, 1.33f, 0.12f, 2.75f, -0.52f, 4.26f);
        pathBuilderR.curveToRelative(-1.16f, 2.71f, -3.68f, 4.7f, -6.61f, 4.97f);
        pathBuilderR.curveToRelative(-5.1f, 0.47f, -9.33f, -3.85f, -8.7f, -8.98f);
        pathBuilderR.curveToRelative(0.43f, -3.54f, 3.28f, -6.42f, 6.81f, -6.91f);
        pathBuilderR.curveToRelative(1.73f, -0.24f, 3.37f, 0.09f, 4.77f, 0.81f);
        pathBuilderR.curveToRelative(0.39f, 0.2f, 0.86f, 0.13f, 1.17f, -0.18f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.48f, -0.48f, 0.36f, -1.29f, -0.24f, -1.6f);
        pathBuilderR.curveTo(16.31f, 2.98f, 16.04f, 2.85f, 15.77f, 2.74f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _taskAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}

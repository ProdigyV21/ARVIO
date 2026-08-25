package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_taskAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TaskAlt", "Landroidx/compose/material/icons/Icons$Sharp;", "getTaskAlt", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TaskAltKt {
    private static ImageVector _taskAlt;

    public static final ImageVector getTaskAlt(Icons.Sharp sharp) {
        ImageVector imageVector = _taskAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.TaskAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 5.18f, 10.59f, 16.6f);
        pathBuilderJ.lineToRelative(-4.24f, -4.24f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.lineToRelative(2.83f, 2.83f);
        d.C(pathBuilderJ, 10.0f, -10.0f, 22.0f, 5.18f);
        pathBuilderJ.moveTo(19.79f, 10.22f);
        pathBuilderJ.curveTo(19.92f, 10.79f, 20.0f, 11.39f, 20.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f);
        pathBuilderJ.reflectiveCurveToRelative(-8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.42f, 3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderJ.curveToRelative(1.58f, 0.0f, 3.04f, 0.46f, 4.28f, 1.25f);
        pathBuilderJ.lineToRelative(1.44f, -1.44f);
        pathBuilderJ.curveTo(16.1f, 2.67f, 14.13f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderJ.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.19f, -0.22f, -2.33f, -0.6f, -3.39f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 19.79f, 10.22f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _taskAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}

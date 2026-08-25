package androidx.compose.material.icons.filled;

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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_task", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Task", "Landroidx/compose/material/icons/Icons$Filled;", "getTask", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TaskKt {
    private static ImageVector _task;

    public static final ImageVector getTask(Icons.Filled filled) {
        ImageVector imageVector = _task;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Task", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(14.0f, 2.0f, 6.0f);
        pathBuilderQ.curveTo(4.9f, 2.0f, 4.01f, 2.9f, 4.01f, 4.0f);
        pathBuilderQ.lineTo(4.0f, 20.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderQ.horizontalLineTo(18.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        c.x(pathBuilderQ, 8.0f, 14.0f, 2.0f);
        pathBuilderQ.moveTo(10.94f, 18.0f);
        pathBuilderQ.lineTo(7.4f, 14.46f);
        pathBuilderQ.lineToRelative(1.41f, -1.41f);
        pathBuilderQ.lineToRelative(2.12f, 2.12f);
        pathBuilderQ.lineToRelative(4.24f, -4.24f);
        d.C(pathBuilderQ, 1.41f, 1.41f, 10.94f, 18.0f);
        pathBuilderQ.moveTo(13.0f, 9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.e(pathBuilderQ, 3.5f, 18.5f, 9.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _task = imageVectorBuild;
        return imageVectorBuild;
    }
}

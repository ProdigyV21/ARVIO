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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addTask", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddTask", "Landroidx/compose/material/icons/Icons$Rounded;", "getAddTask", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddTaskKt {
    private static ImageVector _addTask;

    public static final ImageVector getAddTask(Icons.Rounded rounded) {
        ImageVector imageVector = _addTask;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AddTask", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveTo(21.68f, 4.87f, 21.68f, 5.5f, 21.29f, 5.89f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 20.0f);
        pathBuilderR.curveToRelative(-4.71f, 0.0f, -8.48f, -4.09f, -7.95f, -8.9f);
        pathBuilderR.curveToRelative(0.39f, -3.52f, 3.12f, -6.41f, 6.61f, -6.99f);
        pathBuilderR.curveToRelative(1.81f, -0.3f, 3.53f, 0.02f, 4.99f, 0.78f);
        pathBuilderR.curveToRelative(0.39f, 0.2f, 0.86f, 0.13f, 1.17f, -0.18f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.48f, -0.48f, 0.36f, -1.29f, -0.24f, -1.6f);
        pathBuilderR.curveTo(15.11f, 2.36f, 13.45f, 1.95f, 11.68f, 2.0f);
        pathBuilderR.curveToRelative(-5.14f, 0.16f, -9.41f, 4.34f, -9.67f, 9.47f);
        pathBuilderR.curveTo(1.72f, 17.24f, 6.3f, 22.0f, 12.0f, 22.0f);
        pathBuilderR.curveToRelative(1.2f, 0.0f, 2.34f, -0.21f, 3.41f, -0.6f);
        pathBuilderR.curveToRelative(0.68f, -0.25f, 0.87f, -1.13f, 0.35f, -1.65f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(-0.27f, -0.27f, -0.68f, -0.37f, -1.04f, -0.23f);
        pathBuilderR.curveTo(13.87f, 19.83f, 12.95f, 20.0f, 12.0f, 20.0f);
        a.g(pathBuilderR, 19.0f, 15.0f, -2.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.verticalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-2.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.verticalLineToRelative(-2.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderR, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addTask = imageVectorBuild;
        return imageVectorBuild;
    }
}

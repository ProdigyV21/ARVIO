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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snooze", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snooze", "Landroidx/compose/material/icons/Icons$Rounded;", "getSnooze", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnoozeKt {
    private static ImageVector _snooze;

    public static final ImageVector getSnooze(Icons.Rounded rounded) {
        ImageVector imageVector = _snooze;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Snooze", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(10.0f, 11.0f, 2.63f, -3.72f, 4.35f);
        pathBuilderG.curveTo(8.36f, 16.0f, 8.82f, 17.0f, 9.67f, 17.0f);
        pathBuilderG.lineTo(14.0f, 17.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderG.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderG.horizontalLineToRelative(-2.63f);
        pathBuilderG.lineToRelative(3.72f, -4.35f);
        pathBuilderG.curveToRelative(0.55f, -0.65f, 0.09f, -1.65f, -0.76f, -1.65f);
        pathBuilderG.lineTo(10.0f, 9.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderG.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(21.3f, 6.42f);
        pathBuilderG.curveToRelative(-0.35f, 0.42f, -0.98f, 0.48f, -1.41f, 0.13f);
        pathBuilderG.lineToRelative(-3.07f, -2.56f);
        pathBuilderG.curveToRelative(-0.42f, -0.36f, -0.48f, -0.99f, -0.12f, -1.41f);
        pathBuilderG.curveToRelative(0.35f, -0.42f, 0.98f, -0.48f, 1.41f, -0.13f);
        pathBuilderG.lineToRelative(3.07f, 2.56f);
        pathBuilderG.curveToRelative(0.42f, 0.36f, 0.48f, 0.99f, 0.12f, 1.41f);
        pathBuilderG.close();
        pathBuilderG.moveTo(2.7f, 6.42f);
        pathBuilderG.curveToRelative(0.35f, 0.43f, 0.98f, 0.48f, 1.4f, 0.13f);
        pathBuilderG.lineToRelative(3.07f, -2.56f);
        pathBuilderG.curveToRelative(0.43f, -0.36f, 0.49f, -0.99f, 0.13f, -1.41f);
        pathBuilderG.curveToRelative(-0.35f, -0.43f, -0.98f, -0.48f, -1.4f, -0.13f);
        pathBuilderG.lineTo(2.82f, 5.01f);
        pathBuilderG.curveToRelative(-0.42f, 0.36f, -0.48f, 0.99f, -0.12f, 1.41f);
        pathBuilderG.close();
        pathBuilderG.moveTo(12.0f, 6.0f);
        pathBuilderG.curveToRelative(3.86f, 0.0f, 7.0f, 3.14f, 7.0f, 7.0f);
        pathBuilderG.reflectiveCurveToRelative(-3.14f, 7.0f, -7.0f, 7.0f);
        pathBuilderG.reflectiveCurveToRelative(-7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderG.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderG.moveToRelative(0.0f, -2.0f);
        pathBuilderG.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderG.reflectiveCurveToRelative(4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderG.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderG, -4.03f, -9.0f, -9.0f, -9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _snooze = imageVectorBuild;
        return imageVectorBuild;
    }
}

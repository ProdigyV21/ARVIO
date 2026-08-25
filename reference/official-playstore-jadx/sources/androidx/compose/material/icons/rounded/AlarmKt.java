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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Alarm", "Landroidx/compose/material/icons/Icons$Rounded;", "getAlarm", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmKt {
    private static ImageVector _alarm;

    public static final ImageVector getAlarm(Icons.Rounded rounded) {
        ImageVector imageVector = _alarm;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Alarm", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.87f, 15.25f, -3.37f, -2.0f);
        pathBuilderR.lineTo(12.5f, 8.72f);
        pathBuilderR.curveToRelative(0.0f, -0.4f, -0.32f, -0.72f, -0.72f, -0.72f);
        pathBuilderR.horizontalLineToRelative(-0.06f);
        pathBuilderR.curveToRelative(-0.4f, 0.0f, -0.72f, 0.32f, -0.72f, 0.72f);
        pathBuilderR.verticalLineToRelative(4.72f);
        pathBuilderR.curveToRelative(0.0f, 0.35f, 0.18f, 0.68f, 0.49f, 0.86f);
        pathBuilderR.lineToRelative(3.65f, 2.19f);
        pathBuilderR.curveToRelative(0.34f, 0.2f, 0.78f, 0.1f, 0.98f, -0.24f);
        pathBuilderR.curveToRelative(0.21f, -0.35f, 0.1f, -0.8f, -0.25f, -1.0f);
        a.p(pathBuilderR, 21.18f, 5.01f, 18.1f, 2.45f);
        pathBuilderR.curveToRelative(-0.42f, -0.35f, -1.05f, -0.3f, -1.41f, 0.13f);
        pathBuilderR.curveToRelative(-0.35f, 0.42f, -0.29f, 1.05f, 0.13f, 1.41f);
        pathBuilderR.lineToRelative(3.07f, 2.56f);
        pathBuilderR.curveToRelative(0.42f, 0.35f, 1.05f, 0.3f, 1.41f, -0.13f);
        pathBuilderR.curveToRelative(0.36f, -0.42f, 0.3f, -1.05f, -0.12f, -1.41f);
        a.z(pathBuilderR, 4.1f, 6.55f, 3.07f, -2.56f);
        pathBuilderR.curveToRelative(0.43f, -0.36f, 0.49f, -0.99f, 0.13f, -1.41f);
        pathBuilderR.curveToRelative(-0.35f, -0.43f, -0.98f, -0.48f, -1.4f, -0.13f);
        pathBuilderR.lineTo(2.82f, 5.01f);
        pathBuilderR.curveToRelative(-0.42f, 0.36f, -0.48f, 0.99f, -0.12f, 1.41f);
        pathBuilderR.curveToRelative(0.35f, 0.43f, 0.98f, 0.48f, 1.4f, 0.13f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 4.0f);
        pathBuilderR.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderR.reflectiveCurveToRelative(4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderR.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 20.0f);
        pathBuilderR.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(7.0f, 3.14f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, -3.14f, 7.0f, -7.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarm = imageVectorBuild;
        return imageVectorBuild;
    }
}

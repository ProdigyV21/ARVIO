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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Emergency", "Landroidx/compose/material/icons/Icons$Rounded;", "getEmergency", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmergencyKt {
    private static ImageVector _emergency;

    public static final ImageVector getEmergency(Icons.Rounded rounded) {
        ImageVector imageVector = _emergency;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Emergency", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.29f, 8.37f, -1.0f, -1.73f);
        pathBuilderR.curveToRelative(-0.28f, -0.48f, -0.89f, -0.64f, -1.37f, -0.37f);
        pathBuilderR.lineTo(14.0f, 8.54f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(4.54f);
        pathBuilderR.lineTo(6.07f, 6.27f);
        pathBuilderR.curveTo(5.59f, 5.99f, 4.98f, 6.16f, 4.71f, 6.63f);
        pathBuilderR.lineToRelative(-1.0f, 1.73f);
        pathBuilderR.curveTo(3.43f, 8.84f, 3.59f, 9.46f, 4.07f, 9.73f);
        pathBuilderR.lineTo(8.0f, 12.0f);
        pathBuilderR.lineToRelative(-3.93f, 2.27f);
        pathBuilderR.curveToRelative(-0.48f, 0.28f, -0.64f, 0.89f, -0.37f, 1.37f);
        pathBuilderR.lineToRelative(1.0f, 1.73f);
        pathBuilderR.curveToRelative(0.28f, 0.48f, 0.89f, 0.64f, 1.37f, 0.37f);
        pathBuilderR.lineTo(10.0f, 15.46f);
        pathBuilderR.verticalLineTo(20.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-4.54f);
        pathBuilderR.lineToRelative(3.93f, 2.27f);
        pathBuilderR.curveToRelative(0.48f, 0.28f, 1.09f, 0.11f, 1.37f, -0.37f);
        pathBuilderR.lineToRelative(1.0f, -1.73f);
        pathBuilderR.curveToRelative(0.28f, -0.48f, 0.11f, -1.09f, -0.37f, -1.37f);
        pathBuilderR.lineTo(16.0f, 12.0f);
        pathBuilderR.lineToRelative(3.93f, -2.27f);
        pathBuilderR.curveTo(20.41f, 9.46f, 20.57f, 8.84f, 20.29f, 8.37f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emergency = imageVectorBuild;
        return imageVectorBuild;
    }
}

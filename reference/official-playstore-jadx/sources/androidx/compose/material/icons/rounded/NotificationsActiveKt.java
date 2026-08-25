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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsActive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsActive", "Landroidx/compose/material/icons/Icons$Rounded;", "getNotificationsActive", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsActiveKt {
    private static ImageVector _notificationsActive;

    public static final ImageVector getNotificationsActive(Icons.Rounded rounded) {
        ImageVector imageVector = _notificationsActive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NotificationsActive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(18.0f, 16.0f, -5.0f);
        pathBuilderI.curveToRelative(0.0f, -3.07f, -1.64f, -5.64f, -4.5f, -6.32f);
        pathBuilderI.lineTo(13.5f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, -0.83f, -0.68f, -1.5f, -1.51f, -1.5f);
        pathBuilderI.reflectiveCurveTo(10.5f, 3.17f, 10.5f, 4.0f);
        pathBuilderI.verticalLineToRelative(0.68f);
        pathBuilderI.curveTo(7.63f, 5.36f, 6.0f, 7.92f, 6.0f, 11.0f);
        pathBuilderI.verticalLineToRelative(5.0f);
        pathBuilderI.lineToRelative(-1.3f, 1.29f);
        pathBuilderI.curveToRelative(-0.63f, 0.63f, -0.19f, 1.71f, 0.7f, 1.71f);
        pathBuilderI.horizontalLineToRelative(13.17f);
        pathBuilderI.curveToRelative(0.89f, 0.0f, 1.34f, -1.08f, 0.71f, -1.71f);
        c.z(pathBuilderI, 18.0f, 16.0f, 11.99f, 22.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.horizontalLineToRelative(-4.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(6.77f, 4.73f);
        pathBuilderI.curveToRelative(0.42f, -0.38f, 0.43f, -1.03f, 0.03f, -1.43f);
        pathBuilderI.curveToRelative(-0.38f, -0.38f, -1.0f, -0.39f, -1.39f, -0.02f);
        pathBuilderI.curveTo(3.7f, 4.84f, 2.52f, 6.96f, 2.14f, 9.34f);
        pathBuilderI.curveToRelative(-0.09f, 0.61f, 0.38f, 1.16f, 1.0f, 1.16f);
        pathBuilderI.curveToRelative(0.48f, 0.0f, 0.9f, -0.35f, 0.98f, -0.83f);
        pathBuilderI.curveToRelative(0.3f, -1.94f, 1.26f, -3.67f, 2.65f, -4.94f);
        pathBuilderI.close();
        pathBuilderI.moveTo(18.6f, 3.28f);
        pathBuilderI.curveToRelative(-0.4f, -0.37f, -1.02f, -0.36f, -1.4f, 0.02f);
        pathBuilderI.curveToRelative(-0.4f, 0.4f, -0.38f, 1.04f, 0.03f, 1.42f);
        pathBuilderI.curveToRelative(1.38f, 1.27f, 2.35f, 3.0f, 2.65f, 4.94f);
        pathBuilderI.curveToRelative(0.07f, 0.48f, 0.49f, 0.83f, 0.98f, 0.83f);
        pathBuilderI.curveToRelative(0.61f, 0.0f, 1.09f, -0.55f, 0.99f, -1.16f);
        pathBuilderI.curveToRelative(-0.38f, -2.37f, -1.55f, -4.48f, -3.25f, -6.05f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsActive = imageVectorBuild;
        return imageVectorBuild;
    }
}

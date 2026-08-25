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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsPaused", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsPaused", "Landroidx/compose/material/icons/Icons$Rounded;", "getNotificationsPaused", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsPausedKt {
    private static ImageVector _notificationsPaused;

    public static final ImageVector getNotificationsPaused(Icons.Rounded rounded) {
        ImageVector imageVector = _notificationsPaused;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NotificationsPaused", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 22.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        a.p(pathBuilderA, 19.29f, 17.29f, 18.0f, 16.0f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        pathBuilderA.curveToRelative(0.0f, -3.07f, -1.64f, -5.64f, -4.5f, -6.32f);
        pathBuilderA.lineTo(13.5f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(0.68f);
        pathBuilderA.curveTo(7.63f, 5.36f, 6.0f, 7.92f, 6.0f, 11.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.lineToRelative(-1.29f, 1.29f);
        pathBuilderA.curveToRelative(-0.63f, 0.63f, -0.19f, 1.71f, 0.7f, 1.71f);
        pathBuilderA.horizontalLineToRelative(13.17f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.34f, -1.08f, 0.71f, -1.71f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.5f, 9.33f);
        pathBuilderA.curveToRelative(0.0f, 0.31f, -0.11f, 0.6f, -0.3f, 0.84f);
        pathBuilderA.lineToRelative(-2.5f, 3.03f);
        pathBuilderA.horizontalLineToRelative(1.9f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.9f, 0.4f, 0.9f, 0.9f);
        pathBuilderA.reflectiveCurveToRelative(-0.4f, 0.9f, -0.9f, 0.9f);
        pathBuilderA.horizontalLineToRelative(-2.78f);
        pathBuilderA.curveToRelative(-0.73f, 0.0f, -1.32f, -0.59f, -1.32f, -1.32f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.0f, -0.31f, 0.11f, -0.6f, 0.3f, -0.84f);
        pathBuilderA.lineToRelative(2.5f, -3.03f);
        pathBuilderA.horizontalLineToRelative(-1.9f);
        pathBuilderA.curveToRelative(-0.5f, 0.0f, -0.9f, -0.4f, -0.9f, -0.9f);
        pathBuilderA.reflectiveCurveToRelative(0.4f, -0.9f, 0.9f, -0.9f);
        pathBuilderA.horizontalLineToRelative(2.78f);
        pathBuilderA.curveToRelative(0.73f, 0.0f, 1.32f, 0.59f, 1.32f, 1.33f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsPaused = imageVectorBuild;
        return imageVectorBuild;
    }
}

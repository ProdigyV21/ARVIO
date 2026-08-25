package androidx.compose.material.icons.twotone;

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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsPaused", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsPaused", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNotificationsPaused", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsPausedKt {
    private static ImageVector _notificationsPaused;

    public static final ImageVector getNotificationsPaused(Icons.TwoTone twoTone) {
        ImageVector imageVector = _notificationsPaused;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NotificationsPaused", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.5f);
        pathBuilderA.curveToRelative(-2.49f, 0.0f, -4.0f, 2.02f, -4.0f, 4.5f);
        e.l(pathBuilderA, 6.0f, 8.0f, -6.0f);
        pathBuilderA.curveToRelative(0.0f, -2.48f, -1.51f, -4.5f, -4.0f, -4.5f);
        a.z(pathBuilderA, 14.5f, 9.8f, -2.8f, 3.4f);
        c.A(pathBuilderA, 2.8f, 14.5f, 15.0f, -5.0f);
        pathBuilderA.verticalLineToRelative(-1.8f);
        pathBuilderA.lineToRelative(2.8f, -3.4f);
        pathBuilderA.lineTo(9.5f, 9.8f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderA, 9.5f, 8.0f, 5.0f, 1.8f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(9.5f, 9.8f, 2.8f, -2.8f, 3.4f);
        b.A(pathBuilderG, 9.5f, 15.0f, 5.0f, -1.8f);
        pathBuilderG.horizontalLineToRelative(-2.8f);
        pathBuilderG.lineToRelative(2.8f, -3.4f);
        f.n(pathBuilderG, 14.5f, 8.0f, -5.0f);
        pathBuilderG.moveTo(18.0f, 16.0f);
        pathBuilderG.verticalLineToRelative(-5.0f);
        pathBuilderG.curveToRelative(0.0f, -3.07f, -1.63f, -5.64f, -4.5f, -6.32f);
        pathBuilderG.lineTo(13.5f, 4.0f);
        pathBuilderG.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderG.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderG.verticalLineToRelative(0.68f);
        pathBuilderG.curveTo(7.64f, 5.36f, 6.0f, 7.92f, 6.0f, 11.0f);
        e.v(pathBuilderG, 5.0f, -2.0f, 2.0f, 1.0f);
        a0.b.r(pathBuilderG, 16.0f, -1.0f, -2.0f, -2.0f);
        pathBuilderG.moveTo(16.0f, 17.0f);
        pathBuilderG.lineTo(8.0f, 17.0f);
        pathBuilderG.verticalLineToRelative(-6.0f);
        pathBuilderG.curveToRelative(0.0f, -2.48f, 1.51f, -4.5f, 4.0f, -4.5f);
        pathBuilderG.reflectiveCurveToRelative(4.0f, 2.02f, 4.0f, 4.5f);
        b.f(pathBuilderG, 6.0f, 12.0f, 22.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.horizontalLineToRelative(-4.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsPaused = imageVectorBuild;
        return imageVectorBuild;
    }
}

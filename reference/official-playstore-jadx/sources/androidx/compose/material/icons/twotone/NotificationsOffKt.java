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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNotificationsOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsOffKt {
    private static ImageVector _notificationsOff;

    public static final ImageVector getNotificationsOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _notificationsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NotificationsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(8.0f, 17.0f, 8.0f, -0.24f);
        pathBuilderA.lineTo(8.34f, 9.1f);
        pathBuilderA.curveTo(8.12f, 9.68f, 8.0f, 10.32f, 8.0f, 11.0f);
        b.f(pathBuilderA, 6.0f, 12.0f, 6.5f);
        pathBuilderA.curveToRelative(-0.19f, 0.0f, -0.37f, 0.03f, -0.55f, 0.06f);
        pathBuilderA.lineTo(16.0f, 11.1f);
        pathBuilderA.lineTo(16.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -2.48f, -1.51f, -4.5f, -4.0f, -4.5f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 22.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.horizontalLineToRelative(-4.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 6.5f);
        pathBuilderA2.curveToRelative(2.49f, 0.0f, 4.0f, 2.02f, 4.0f, 4.5f);
        pathBuilderA2.verticalLineToRelative(0.1f);
        pathBuilderA2.lineToRelative(2.0f, 2.0f);
        pathBuilderA2.lineTo(18.0f, 11.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.07f, -1.63f, -5.64f, -4.5f, -6.32f);
        pathBuilderA2.lineTo(13.5f, 4.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA2.verticalLineToRelative(0.68f);
        pathBuilderA2.curveToRelative(-0.24f, 0.06f, -0.47f, 0.15f, -0.69f, 0.23f);
        pathBuilderA2.lineToRelative(1.64f, 1.64f);
        pathBuilderA2.curveToRelative(0.18f, -0.02f, 0.36f, -0.05f, 0.55f, -0.05f);
        a.p(pathBuilderA2, 5.41f, 3.35f, 4.0f, 4.76f);
        pathBuilderA2.lineToRelative(2.81f, 2.81f);
        pathBuilderA2.curveTo(6.29f, 8.57f, 6.0f, 9.74f, 6.0f, 11.0f);
        e.v(pathBuilderA2, 5.0f, -2.0f, 2.0f, 1.0f);
        pathBuilderA2.horizontalLineToRelative(14.24f);
        pathBuilderA2.lineToRelative(1.74f, 1.74f);
        d.C(pathBuilderA2, 1.41f, -1.41f, 5.41f, 3.35f);
        pathBuilderA2.moveTo(16.0f, 17.0f);
        pathBuilderA2.lineTo(8.0f, 17.0f);
        pathBuilderA2.verticalLineToRelative(-6.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.68f, 0.12f, -1.32f, 0.34f, -1.9f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA2, 16.0f, 16.76f, 16.0f, 17.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

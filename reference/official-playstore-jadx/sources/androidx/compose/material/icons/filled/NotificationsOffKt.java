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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsOff", "Landroidx/compose/material/icons/Icons$Filled;", "getNotificationsOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsOffKt {
    private static ImageVector _notificationsOff;

    public static final ImageVector getNotificationsOff(Icons.Filled filled) {
        ImageVector imageVector = _notificationsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NotificationsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 18.69f, 7.84f, 6.14f);
        pathBuilderJ.lineTo(5.27f, 3.49f);
        pathBuilderJ.lineTo(4.0f, 4.76f);
        pathBuilderJ.lineToRelative(2.8f, 2.8f);
        pathBuilderJ.verticalLineToRelative(0.01f);
        pathBuilderJ.curveToRelative(-0.52f, 0.99f, -0.8f, 2.16f, -0.8f, 3.42f);
        e.v(pathBuilderJ, 5.0f, -2.0f, 2.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(13.73f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        d.l(pathBuilderJ, 21.0f, 19.72f, -1.0f, -1.03f);
        pathBuilderJ.moveTo(12.0f, 22.0f);
        pathBuilderJ.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        a.p(pathBuilderJ, 18.0f, 14.68f, 18.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.0f, -3.08f, -1.64f, -5.64f, -4.5f, -6.32f);
        pathBuilderJ.lineTo(13.5f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderJ.verticalLineToRelative(0.68f);
        pathBuilderJ.curveToRelative(-0.15f, 0.03f, -0.29f, 0.08f, -0.42f, 0.12f);
        pathBuilderJ.curveToRelative(-0.1f, 0.03f, -0.2f, 0.07f, -0.3f, 0.11f);
        pathBuilderJ.horizontalLineToRelative(-0.01f);
        pathBuilderJ.curveToRelative(-0.01f, 0.0f, -0.01f, 0.0f, -0.02f, 0.01f);
        pathBuilderJ.curveToRelative(-0.23f, 0.09f, -0.46f, 0.2f, -0.68f, 0.31f);
        pathBuilderJ.curveToRelative(0.0f, 0.0f, -0.01f, 0.0f, -0.01f, 0.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 18.0f, 14.68f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.sharp;

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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationsOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getNotificationsOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationsOffKt {
    private static ImageVector _notificationsOff;

    public static final ImageVector getNotificationsOff(Icons.Sharp sharp) {
        ImageVector imageVector = _notificationsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NotificationsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 22.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -3.07f, -1.64f, -5.64f, -4.5f, -6.32f);
        b.A(pathBuilderA, 13.5f, 2.5f, -3.0f, 2.18f);
        pathBuilderA.curveToRelative(-0.24f, 0.06f, -0.47f, 0.15f, -0.69f, 0.23f);
        b.y(pathBuilderA, 18.0f, 13.1f, 18.0f, 11.0f);
        pathBuilderA.moveTo(5.41f, 3.35f);
        pathBuilderA.lineTo(4.0f, 4.76f);
        pathBuilderA.lineToRelative(2.81f, 2.81f);
        pathBuilderA.curveTo(6.29f, 8.57f, 6.0f, 9.73f, 6.0f, 11.0f);
        e.v(pathBuilderA, 5.0f, -2.0f, 2.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(14.24f);
        pathBuilderA.lineToRelative(1.74f, 1.74f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.41f, -1.41f, 5.41f, 3.35f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.twotone;

import a0.a;
import a0.b;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notificationAdd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotificationAdd", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNotificationAdd", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotificationAddKt {
    private static ImageVector _notificationAdd;

    public static final ImageVector getNotificationAdd(Icons.TwoTone twoTone) {
        ImageVector imageVector = _notificationAdd;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NotificationAdd", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(16.0f, 14.0f, 3.0f, 8.0f, -7.0f);
        pathBuilderQ.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderQ.curveToRelative(0.85f, 0.0f, 1.64f, 0.26f, 2.28f, 0.72f);
        pathBuilderQ.lineToRelative(1.43f, -1.43f);
        pathBuilderQ.curveToRelative(-0.64f, -0.51f, -1.39f, -0.88f, -2.21f, -1.09f);
        pathBuilderQ.verticalLineTo(3.5f);
        pathBuilderQ.curveTo(13.5f, 2.67f, 12.83f, 2.0f, 12.0f, 2.0f);
        pathBuilderQ.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderQ.verticalLineToRelative(0.7f);
        pathBuilderQ.curveTo(7.91f, 4.86f, 6.0f, 7.21f, 6.0f, 10.0f);
        a.A(pathBuilderQ, 7.0f, 4.0f, 2.0f, 16.0f);
        b.f(pathBuilderQ, -2.0f, -2.0f, -3.0f, 16.0f);
        pathBuilderQ.moveTo(12.0f, 22.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.horizontalLineToRelative(-4.0f);
        pathBuilderQ.curveTo(10.0f, 21.1f, 10.9f, 22.0f, 12.0f, 22.0f);
        v.a.u(pathBuilderQ, 24.0f, 8.0f, -3.0f, 5.0f);
        v.a.o(pathBuilderQ, -2.0f, 3.0f, -3.0f, 2.0f);
        v.a.o(pathBuilderQ, 3.0f, 3.0f, 2.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 3.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notificationAdd = imageVectorBuild;
        return imageVectorBuild;
    }
}

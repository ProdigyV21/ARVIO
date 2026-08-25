package androidx.compose.material.icons.outlined;

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
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deviceHub", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeviceHub", "Landroidx/compose/material/icons/Icons$Outlined;", "getDeviceHub", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeviceHubKt {
    private static ImageVector _deviceHub;

    public static final ImageVector getDeviceHub(Icons.Outlined outlined) {
        ImageVector imageVector = _deviceHub;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DeviceHub", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(17.0f, 16.0f, -4.0f, -4.0f, 8.82f);
        pathBuilderA.curveTo(14.16f, 8.4f, 15.0f, 7.3f, 15.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 4.34f, 9.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, 1.3f, 0.84f, 2.4f, 2.0f, 2.82f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.lineToRelative(-4.0f, 4.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        e.l(pathBuilderA, 5.0f, 5.0f, -3.05f);
        pathBuilderA.lineToRelative(4.0f, -4.2f);
        b.o(pathBuilderA, 4.0f, 4.2f, 21.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderA, -5.0f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deviceHub = imageVectorBuild;
        return imageVectorBuild;
    }
}

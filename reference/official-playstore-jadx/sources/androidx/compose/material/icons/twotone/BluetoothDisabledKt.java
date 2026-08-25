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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bluetoothDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BluetoothDisabled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBluetoothDisabled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BluetoothDisabledKt {
    private static ImageVector _bluetoothDisabled;

    public static final ImageVector getBluetoothDisabled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _bluetoothDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.BluetoothDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.0f, 5.83f, 1.88f, 1.88f);
        pathBuilderR.lineToRelative(-1.6f, 1.6f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(3.02f, -3.02f);
        b.A(pathBuilderR, 12.0f, 2.0f, -1.0f, 5.03f);
        f.p(pathBuilderR, 2.0f, 2.0f, -3.2f);
        pathBuilderR.moveTo(5.41f, 4.0f);
        pathBuilderR.lineTo(4.0f, 5.41f);
        pathBuilderR.lineTo(10.59f, 12.0f);
        pathBuilderR.lineTo(5.0f, 17.59f);
        pathBuilderR.lineTo(6.41f, 19.0f);
        b.n(pathBuilderR, 11.0f, 14.41f, 22.0f, 1.0f);
        pathBuilderR.lineToRelative(4.29f, -4.29f);
        pathBuilderR.lineToRelative(2.3f, 2.29f);
        b.y(pathBuilderR, 20.0f, 18.59f, 5.41f, 4.0f);
        pathBuilderR.moveTo(13.0f, 18.17f);
        pathBuilderR.verticalLineToRelative(-3.76f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.88f, 1.88f, 13.0f, 18.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bluetoothDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}

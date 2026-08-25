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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bluetoothDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BluetoothDisabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getBluetoothDisabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BluetoothDisabledKt {
    private static ImageVector _bluetoothDisabled;

    public static final ImageVector getBluetoothDisabled(Icons.Rounded rounded) {
        ImageVector imageVector = _bluetoothDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BluetoothDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.29f, 17.89f, 6.11f, 4.7f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineTo(10.59f, 12.0f);
        pathBuilderJ.lineTo(5.7f, 16.89f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(11.0f, 14.41f);
        pathBuilderJ.verticalLineToRelative(6.18f);
        pathBuilderJ.curveToRelative(0.0f, 0.89f, 1.08f, 1.34f, 1.71f, 0.71f);
        pathBuilderJ.lineToRelative(3.59f, -3.59f);
        pathBuilderJ.lineToRelative(1.59f, 1.59f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.curveToRelative(0.38f, -0.39f, 0.38f, -1.03f, -0.01f, -1.41f);
        d.k(pathBuilderJ, 13.0f, 18.17f, -3.76f);
        d.C(pathBuilderJ, 1.88f, 1.88f, 13.0f, 18.17f);
        pathBuilderJ.moveTo(13.0f, 5.83f);
        pathBuilderJ.lineToRelative(1.88f, 1.88f);
        pathBuilderJ.lineToRelative(-1.47f, 1.47f);
        pathBuilderJ.lineToRelative(1.41f, 1.41f);
        pathBuilderJ.lineTo(17.0f, 8.42f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.42f);
        pathBuilderJ.lineToRelative(-4.29f, -4.29f);
        pathBuilderJ.curveToRelative(-0.63f, -0.63f, -1.71f, -0.19f, -1.71f, 0.7f);
        pathBuilderJ.verticalLineToRelative(3.36f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 2.0f, 2.0f, 13.0f, 5.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bluetoothDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}

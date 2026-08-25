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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bluetooth", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bluetooth", "Landroidx/compose/material/icons/Icons$Sharp;", "getBluetooth", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BluetoothKt {
    private static ImageVector _bluetooth;

    public static final ImageVector getBluetooth(Icons.Sharp sharp) {
        ImageVector imageVector = _bluetooth;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Bluetooth", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(17.71f, 7.71f, 12.0f, 2.0f, -1.0f);
        pathBuilderT.verticalLineToRelative(7.59f);
        pathBuilderT.lineTo(6.41f, 5.0f);
        pathBuilderT.lineTo(5.0f, 6.41f);
        pathBuilderT.lineTo(10.59f, 12.0f);
        pathBuilderT.lineTo(5.0f, 17.59f);
        pathBuilderT.lineTo(6.41f, 19.0f);
        pathBuilderT.lineTo(11.0f, 14.41f);
        pathBuilderT.lineTo(11.0f, 22.0f);
        pathBuilderT.horizontalLineToRelative(1.0f);
        pathBuilderT.lineToRelative(5.71f, -5.71f);
        b.D(pathBuilderT, -4.3f, -4.29f, 4.3f, -4.29f);
        pathBuilderT.moveTo(13.0f, 5.83f);
        pathBuilderT.lineToRelative(1.88f, 1.88f);
        b.y(pathBuilderT, 13.0f, 9.59f, 13.0f, 5.83f);
        pathBuilderT.moveTo(14.88f, 16.29f);
        pathBuilderT.lineTo(13.0f, 18.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.s(pathBuilderT, -3.76f, 1.88f, 1.88f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bluetooth = imageVectorBuild;
        return imageVectorBuild;
    }
}

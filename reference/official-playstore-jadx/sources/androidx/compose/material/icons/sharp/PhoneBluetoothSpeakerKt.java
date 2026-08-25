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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneBluetoothSpeaker", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneBluetoothSpeaker", "Landroidx/compose/material/icons/Icons$Sharp;", "getPhoneBluetoothSpeaker", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneBluetoothSpeakerKt {
    private static ImageVector _phoneBluetoothSpeaker;

    public static final ImageVector getPhoneBluetoothSpeaker(Icons.Sharp sharp) {
        ImageVector imageVector = _phoneBluetoothSpeaker;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PhoneBluetoothSpeaker", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.71f, 9.5f, 17.0f, 7.21f);
        pathBuilderJ.lineTo(17.0f, 11.0f);
        pathBuilderJ.horizontalLineToRelative(0.5f);
        pathBuilderJ.lineToRelative(2.85f, -2.85f);
        pathBuilderJ.lineTo(18.21f, 6.0f);
        pathBuilderJ.lineToRelative(2.15f, -2.15f);
        pathBuilderJ.lineTo(17.5f, 1.0f);
        pathBuilderJ.lineTo(17.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(3.79f);
        pathBuilderJ.lineTo(14.71f, 2.5f);
        pathBuilderJ.lineToRelative(-0.71f, 0.71f);
        pathBuilderJ.lineTo(16.79f, 6.0f);
        d.l(pathBuilderJ, 14.0f, 8.79f, 0.71f, 0.71f);
        pathBuilderJ.moveTo(18.0f, 2.91f);
        pathBuilderJ.lineToRelative(0.94f, 0.94f);
        d.C(pathBuilderJ, -0.94f, 0.94f, 18.0f, 2.91f);
        pathBuilderJ.moveTo(18.0f, 7.21f);
        pathBuilderJ.lineToRelative(0.94f, 0.94f);
        d.C(pathBuilderJ, -0.94f, 0.94f, 18.0f, 7.21f);
        pathBuilderJ.moveTo(21.0f, 15.46f);
        pathBuilderJ.lineToRelative(-5.27f, -0.61f);
        pathBuilderJ.lineToRelative(-2.52f, 2.52f);
        pathBuilderJ.curveToRelative(-2.83f, -1.44f, -5.15f, -3.75f, -6.59f, -6.59f);
        pathBuilderJ.lineToRelative(2.53f, -2.53f);
        pathBuilderJ.lineTo(8.54f, 3.0f);
        pathBuilderJ.lineTo(3.03f, 3.0f);
        pathBuilderJ.curveTo(2.45f, 13.18f, 10.82f, 21.55f, 21.0f, 20.97f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderJ, -5.51f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneBluetoothSpeaker = imageVectorBuild;
        return imageVectorBuild;
    }
}

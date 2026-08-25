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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_usb", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Usb", "Landroidx/compose/material/icons/Icons$Rounded;", "getUsb", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UsbKt {
    private static ImageVector _usb;

    public static final ImageVector getUsb(Icons.Rounded rounded) {
        ImageVector imageVector = _usb;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Usb", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(18.0f, 7.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a.y(pathBuilderA, 2.0f, -3.0f, 5.0f, 1.0f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.65f, -0.47f, 0.4f, -0.8f);
        pathBuilderA.lineToRelative(-2.0f, -2.67f);
        pathBuilderA.curveToRelative(-0.2f, -0.27f, -0.6f, -0.27f, -0.8f, 0.0f);
        pathBuilderA.lineToRelative(-2.0f, 2.67f);
        pathBuilderA.curveToRelative(-0.25f, 0.33f, -0.01f, 0.8f, 0.4f, 0.8f);
        a0.a.y(pathBuilderA, 1.0f, 8.0f, 8.0f, -2.07f);
        pathBuilderA.curveToRelative(0.83f, -0.44f, 1.38f, -1.36f, 1.14f, -2.43f);
        pathBuilderA.curveToRelative(-0.17f, -0.77f, -0.77f, -1.4f, -1.52f, -1.61f);
        pathBuilderA.curveTo(6.15f, 6.48f, 4.8f, 7.59f, 4.8f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 0.85f, 0.5f, 1.56f, 1.2f, 1.93f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.verticalLineToRelative(3.05f);
        pathBuilderA.curveToRelative(-0.86f, 0.45f, -1.39f, 1.42f, -1.13f, 2.49f);
        pathBuilderA.curveToRelative(0.18f, 0.75f, 0.79f, 1.38f, 1.54f, 1.58f);
        pathBuilderA.curveToRelative(1.46f, 0.39f, 2.8f, -0.7f, 2.8f, -2.12f);
        pathBuilderA.curveToRelative(0.0f, -0.85f, -0.49f, -1.58f, -1.2f, -1.95f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveTo(19.0f, 7.45f, 18.55f, 7.0f, 18.0f, 7.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _usb = imageVectorBuild;
        return imageVectorBuild;
    }
}

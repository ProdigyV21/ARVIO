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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiChannel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiChannel", "Landroidx/compose/material/icons/Icons$Rounded;", "getWifiChannel", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiChannelKt {
    private static ImageVector _wifiChannel;

    public static final ImageVector getWifiChannel(Icons.Rounded rounded) {
        ImageVector imageVector = _wifiChannel;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WifiChannel", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.13f, 21.0f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.92f, -0.38f, 0.99f, -0.87f);
        pathBuilderA.curveToRelative(0.65f, -4.89f, 1.95f, -9.01f, 2.88f, -10.0f);
        pathBuilderA.curveToRelative(0.91f, 0.98f, 2.19f, 5.01f, 2.86f, 9.82f);
        pathBuilderA.curveToRelative(0.08f, 0.6f, 0.59f, 1.05f, 1.19f, 1.05f);
        pathBuilderA.curveToRelative(0.54f, 0.0f, 1.02f, -0.36f, 1.16f, -0.89f);
        pathBuilderA.curveTo(13.83f, 17.73f, 15.11f, 15.0f, 16.0f, 15.0f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 2.19f, 2.83f, 2.81f, 5.2f);
        pathBuilderA.curveToRelative(0.12f, 0.48f, 0.56f, 0.8f, 1.05f, 0.8f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.12f, -0.52f, 1.09f, -1.14f);
        pathBuilderA.curveTo(20.75f, 15.89f, 19.81f, 3.0f, 16.0f, 3.0f);
        pathBuilderA.curveToRelative(-2.51f, 0.0f, -3.77f, 5.61f, -4.4f, 10.57f);
        pathBuilderA.curveTo(10.79f, 10.66f, 9.61f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(-2.92f, 0.0f, -4.41f, 8.71f, -4.85f, 11.87f);
        pathBuilderA.curveTo(3.06f, 20.47f, 3.53f, 21.0f, 4.13f, 21.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 13.0f);
        pathBuilderA.curveToRelative(-0.99f, 0.0f, -1.82f, 0.62f, -2.5f, 1.5f);
        pathBuilderA.curveToRelative(0.57f, -4.77f, 1.54f, -8.62f, 2.5f, -9.44f);
        pathBuilderA.curveToRelative(0.97f, 0.81f, 1.91f, 4.67f, 2.49f, 9.43f);
        pathBuilderA.curveTo(17.81f, 13.62f, 16.98f, 13.0f, 16.0f, 13.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiChannel = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_networkWifi1Bar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NetworkWifi1Bar", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNetworkWifi1Bar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NetworkWifi1BarKt {
    private static ImageVector _networkWifi1Bar;

    public static final ImageVector getNetworkWifi1Bar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _networkWifi1Bar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NetworkWifi1Bar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.32f, 14.84f);
        pathBuilderA.curveTo(14.34f, 14.3f, 13.2f, 14.0f, 12.0f, 14.0f);
        pathBuilderA.curveToRelative(-1.2f, 0.0f, -2.34f, 0.3f, -3.32f, 0.84f);
        pathBuilderA.lineTo(2.92f, 9.07f);
        pathBuilderA.curveTo(5.51f, 7.08f, 8.67f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.49f, 1.08f, 9.08f, 3.07f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 15.32f, 14.84f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 4.0f);
        pathBuilderA2.curveTo(7.31f, 4.0f, 3.07f, 5.9f, 0.0f, 8.98f);
        pathBuilderA2.lineTo(12.0f, 21.0f);
        pathBuilderA2.lineTo(24.0f, 8.98f);
        pathBuilderA2.curveTo(20.93f, 5.9f, 16.69f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(15.32f, 14.84f);
        pathBuilderA2.curveTo(14.34f, 14.3f, 13.2f, 14.0f, 12.0f, 14.0f);
        pathBuilderA2.curveToRelative(-1.2f, 0.0f, -2.34f, 0.3f, -3.32f, 0.84f);
        pathBuilderA2.lineTo(2.92f, 9.07f);
        pathBuilderA2.curveTo(5.51f, 7.08f, 8.67f, 6.0f, 12.0f, 6.0f);
        pathBuilderA2.reflectiveCurveToRelative(6.49f, 1.08f, 9.08f, 3.07f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 15.32f, 14.84f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _networkWifi1Bar = imageVectorBuild;
        return imageVectorBuild;
    }
}

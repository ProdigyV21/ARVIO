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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signalWifi0Bar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SignalWifi0Bar", "Landroidx/compose/material/icons/Icons$Rounded;", "getSignalWifi0Bar", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignalWifi0BarKt {
    private static ImageVector _signalWifi0Bar;

    public static final ImageVector getSignalWifi0Bar(Icons.Rounded rounded) {
        ImageVector imageVector = _signalWifi0Bar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SignalWifi0Bar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveTo(7.7f, 4.0f, 3.78f, 5.6f, 0.79f, 8.24f);
        pathBuilderA.curveTo(0.35f, 8.63f, 0.32f, 9.3f, 0.73f, 9.71f);
        pathBuilderA.lineToRelative(10.56f, 10.58f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.42f, 0.0f);
        pathBuilderA.lineTo(23.27f, 9.71f);
        pathBuilderA.curveToRelative(0.41f, -0.41f, 0.38f, -1.08f, -0.06f, -1.47f);
        pathBuilderA.curveTo(20.22f, 5.6f, 16.3f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(2.92f, 9.07f);
        pathBuilderA.curveTo(5.51f, 7.08f, 8.67f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.49f, 1.08f, 9.08f, 3.07f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 12.0f, 18.17f, 2.92f, 9.07f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _signalWifi0Bar = imageVectorBuild;
        return imageVectorBuild;
    }
}

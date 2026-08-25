package androidx.compose.material.icons.automirrored.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stopScreenShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StopScreenShare", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getStopScreenShare", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StopScreenShareKt {
    private static ImageVector _stopScreenShare;

    public static final ImageVector getStopScreenShare(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _stopScreenShare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.StopScreenShare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.79f, 18.0f, 2.0f, 2.0f);
        d.f(pathBuilderR, 24.0f, 20.0f, -2.0f);
        pathBuilderR.moveTo(13.0f, 9.13f);
        pathBuilderR.lineTo(13.0f, 7.0f);
        pathBuilderR.lineToRelative(4.0f, 3.74f);
        pathBuilderR.lineToRelative(-1.28f, 1.19f);
        pathBuilderR.lineToRelative(5.18f, 5.18f);
        pathBuilderR.lineTo(22.0f, 16.0f);
        pathBuilderR.lineTo(22.0f, 4.02f);
        pathBuilderR.lineTo(7.8f, 4.02f);
        pathBuilderR.lineToRelative(5.13f, 5.13f);
        pathBuilderR.curveToRelative(0.03f, -0.01f, 0.05f, -0.02f, 0.07f, -0.02f);
        a.z(pathBuilderR, 1.11f, 2.98f, 0.89f, 0.9f);
        pathBuilderR.verticalLineToRelative(12.14f);
        pathBuilderR.lineToRelative(2.0f, 1.99f);
        a.h(pathBuilderR, 0.0f, 18.0f, 2.0f, 18.13f);
        pathBuilderR.lineToRelative(2.71f, 2.71f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 2.52f, 1.57f, 1.11f, 2.98f);
        pathBuilderR.moveTo(9.08f, 10.95f);
        pathBuilderR.lineToRelative(1.59f, 1.59f);
        pathBuilderR.curveTo(9.13f, 12.92f, 7.96f, 13.71f, 7.0f, 15.0f);
        pathBuilderR.curveToRelative(0.31f, -1.48f, 0.94f, -2.93f, 2.08f, -4.05f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stopScreenShare = imageVectorBuild;
        return imageVectorBuild;
    }
}

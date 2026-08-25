package androidx.compose.material.icons.automirrored.outlined;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stopScreenShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StopScreenShare", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getStopScreenShare", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StopScreenShareKt {
    private static ImageVector _stopScreenShare;

    public static final ImageVector getStopScreenShare(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _stopScreenShare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.StopScreenShare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.79f, 18.0f, 2.0f, 2.0f);
        e.p(pathBuilderR, 24.0f, 20.0f, -2.0f, -2.21f);
        pathBuilderR.moveTo(1.11f, 2.98f);
        pathBuilderR.lineToRelative(1.55f, 1.56f);
        pathBuilderR.curveToRelative(-0.41f, 0.37f, -0.66f, 0.89f, -0.66f, 1.48f);
        pathBuilderR.lineTo(2.0f, 16.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.01f, 2.0f);
        a.h(pathBuilderR, 0.0f, 18.0f, 2.0f, 18.13f);
        pathBuilderR.lineToRelative(2.71f, 2.71f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 2.52f, 1.57f, 1.11f, 2.98f);
        pathBuilderR.moveTo(4.0f, 6.02f);
        pathBuilderR.horizontalLineToRelative(0.13f);
        pathBuilderR.lineToRelative(4.95f, 4.93f);
        pathBuilderR.curveTo(7.94f, 12.07f, 7.31f, 13.52f, 7.0f, 15.0f);
        pathBuilderR.curveToRelative(0.96f, -1.29f, 2.13f, -2.08f, 3.67f, -2.46f);
        pathBuilderR.lineToRelative(3.46f, 3.48f);
        d.f(pathBuilderR, 4.0f, 16.02f, -10.0f);
        pathBuilderR.moveTo(20.0f, 6.02f);
        pathBuilderR.verticalLineToRelative(10.19f);
        pathBuilderR.lineToRelative(1.3f, 1.3f);
        pathBuilderR.curveToRelative(0.42f, -0.37f, 0.7f, -0.89f, 0.7f, -1.49f);
        pathBuilderR.verticalLineToRelative(-10.0f);
        pathBuilderR.curveToRelative(0.0f, -1.11f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.lineTo(7.8f, 4.02f);
        d.C(pathBuilderR, 2.0f, 2.0f, 20.0f, 6.02f);
        pathBuilderR.moveTo(12.93f, 9.15f);
        pathBuilderR.lineToRelative(2.79f, 2.78f);
        pathBuilderR.lineToRelative(1.28f, -1.2f);
        pathBuilderR.lineTo(13.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.s(pathBuilderR, 2.13f, -0.07f, 0.02f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stopScreenShare = imageVectorBuild;
        return imageVectorBuild;
    }
}

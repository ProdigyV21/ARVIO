package androidx.compose.material.icons.automirrored.rounded;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_volumeUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolumeUp", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getVolumeUp", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolumeUpKt {
    private static ImageVector _volumeUp;

    public static final ImageVector getVolumeUp(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _volumeUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.VolumeUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(3.0f, 10.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(3.0f);
        pathBuilderI.lineToRelative(3.29f, 3.29f);
        pathBuilderI.curveToRelative(0.63f, 0.63f, 1.71f, 0.18f, 1.71f, -0.71f);
        pathBuilderI.lineTo(12.0f, 6.41f);
        pathBuilderI.curveToRelative(0.0f, -0.89f, -1.08f, -1.34f, -1.71f, -0.71f);
        pathBuilderI.lineTo(7.0f, 9.0f);
        pathBuilderI.lineTo(4.0f, 9.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(16.5f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, -1.77f, -1.02f, -3.29f, -2.5f, -4.03f);
        pathBuilderI.verticalLineToRelative(8.05f);
        pathBuilderI.curveToRelative(1.48f, -0.73f, 2.5f, -2.25f, 2.5f, -4.02f);
        d.k(pathBuilderI, 14.0f, 4.45f, 0.2f);
        pathBuilderI.curveToRelative(0.0f, 0.38f, 0.25f, 0.71f, 0.6f, 0.85f);
        pathBuilderI.curveTo(17.18f, 6.53f, 19.0f, 9.06f, 19.0f, 12.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.82f, 5.47f, -4.4f, 6.5f);
        pathBuilderI.curveToRelative(-0.36f, 0.14f, -0.6f, 0.47f, -0.6f, 0.85f);
        pathBuilderI.verticalLineToRelative(0.2f);
        pathBuilderI.curveToRelative(0.0f, 0.63f, 0.63f, 1.07f, 1.21f, 0.85f);
        pathBuilderI.curveTo(18.6f, 19.11f, 21.0f, 15.84f, 21.0f, 12.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.4f, -7.11f, -5.79f, -8.4f);
        pathBuilderI.curveToRelative(-0.58f, -0.23f, -1.21f, 0.22f, -1.21f, 0.85f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _volumeUp = imageVectorBuild;
        return imageVectorBuild;
    }
}

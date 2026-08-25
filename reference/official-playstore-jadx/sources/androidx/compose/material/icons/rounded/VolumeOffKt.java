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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_volumeOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolumeOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getVolumeOff$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getVolumeOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolumeOffKt {
    private static ImageVector _volumeOff;

    public static final ImageVector getVolumeOff(Icons.Rounded rounded) {
        ImageVector imageVector = _volumeOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.VolumeOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(3.63f, 3.63f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineTo(7.29f, 8.7f);
        pathBuilderA.lineTo(7.0f, 9.0f);
        pathBuilderA.lineTo(4.0f, 9.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.lineToRelative(3.29f, 3.29f);
        pathBuilderA.curveToRelative(0.63f, 0.63f, 1.71f, 0.18f, 1.71f, -0.71f);
        pathBuilderA.verticalLineToRelative(-4.17f);
        pathBuilderA.lineToRelative(4.18f, 4.18f);
        pathBuilderA.curveToRelative(-0.49f, 0.37f, -1.02f, 0.68f, -1.6f, 0.91f);
        pathBuilderA.curveToRelative(-0.36f, 0.15f, -0.58f, 0.53f, -0.58f, 0.92f);
        pathBuilderA.curveToRelative(0.0f, 0.72f, 0.73f, 1.18f, 1.39f, 0.91f);
        pathBuilderA.curveToRelative(0.8f, -0.33f, 1.55f, -0.77f, 2.22f, -1.31f);
        pathBuilderA.lineToRelative(1.34f, 1.34f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(5.05f, 3.63f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.42f, 0.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.82f, -0.15f, 1.61f, -0.41f, 2.34f);
        pathBuilderA.lineToRelative(1.53f, 1.53f);
        pathBuilderA.curveToRelative(0.56f, -1.17f, 0.88f, -2.48f, 0.88f, -3.87f);
        pathBuilderA.curveToRelative(0.0f, -3.83f, -2.4f, -7.11f, -5.78f, -8.4f);
        pathBuilderA.curveToRelative(-0.59f, -0.23f, -1.22f, 0.23f, -1.22f, 0.86f);
        pathBuilderA.verticalLineToRelative(0.19f);
        pathBuilderA.curveToRelative(0.0f, 0.38f, 0.25f, 0.71f, 0.61f, 0.85f);
        pathBuilderA.curveTo(17.18f, 6.54f, 19.0f, 9.06f, 19.0f, 12.0f);
        a.z(pathBuilderA, 10.29f, 5.71f, -0.17f, 0.17f);
        pathBuilderA.lineTo(12.0f, 7.76f);
        pathBuilderA.lineTo(12.0f, 6.41f);
        pathBuilderA.curveToRelative(0.0f, -0.89f, -1.08f, -1.33f, -1.71f, -0.7f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.77f, -1.02f, -3.29f, -2.5f, -4.03f);
        pathBuilderA.verticalLineToRelative(1.79f);
        pathBuilderA.lineToRelative(2.48f, 2.48f);
        pathBuilderA.curveToRelative(0.01f, -0.08f, 0.02f, -0.16f, 0.02f, -0.24f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _volumeOff = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getVolumeOff$annotations(Icons.Rounded rounded) {
    }
}

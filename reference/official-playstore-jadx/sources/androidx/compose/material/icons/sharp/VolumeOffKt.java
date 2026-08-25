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
import v.c;
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_volumeOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolumeOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getVolumeOff$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getVolumeOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolumeOffKt {
    private static ImageVector _volumeOff;

    public static final ImageVector getVolumeOff(Icons.Sharp sharp) {
        ImageVector imageVector = _volumeOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VolumeOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(4.34f, 2.93f, 2.93f, 4.34f);
        pathBuilderJ.lineTo(7.29f, 8.7f);
        pathBuilderJ.lineTo(7.0f, 9.0f);
        a.h(pathBuilderJ, 3.0f, 9.0f, 6.0f, 4.0f);
        pathBuilderJ.lineToRelative(5.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(-6.59f);
        pathBuilderJ.lineToRelative(4.18f, 4.18f);
        pathBuilderJ.curveToRelative(-0.65f, 0.49f, -1.38f, 0.88f, -2.18f, 1.11f);
        pathBuilderJ.verticalLineToRelative(2.06f);
        pathBuilderJ.curveToRelative(1.34f, -0.3f, 2.57f, -0.92f, 3.61f, -1.75f);
        pathBuilderJ.lineToRelative(2.05f, 2.05f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 4.34f, 2.93f);
        pathBuilderJ.moveTo(19.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.82f, -0.15f, 1.61f, -0.41f, 2.34f);
        pathBuilderJ.lineToRelative(1.53f, 1.53f);
        pathBuilderJ.curveToRelative(0.56f, -1.17f, 0.88f, -2.48f, 0.88f, -3.87f);
        pathBuilderJ.curveToRelative(0.0f, -4.28f, -2.99f, -7.86f, -7.0f, -8.77f);
        pathBuilderJ.verticalLineToRelative(2.06f);
        pathBuilderJ.curveToRelative(2.89f, 0.86f, 5.0f, 3.54f, 5.0f, 6.71f);
        a.z(pathBuilderJ, 12.0f, 4.0f, -1.88f, 1.88f);
        c.z(pathBuilderJ, 12.0f, 7.76f, 16.5f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.77f, -1.02f, -3.29f, -2.5f, -4.03f);
        pathBuilderJ.verticalLineToRelative(1.79f);
        pathBuilderJ.lineToRelative(2.48f, 2.48f);
        pathBuilderJ.curveToRelative(0.01f, -0.08f, 0.02f, -0.16f, 0.02f, -0.24f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _volumeOff = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getVolumeOff$annotations(Icons.Sharp sharp) {
    }
}

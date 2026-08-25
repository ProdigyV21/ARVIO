package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_volumeOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolumeOff", "Landroidx/compose/material/icons/Icons$Filled;", "getVolumeOff$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getVolumeOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolumeOffKt {
    private static ImageVector _volumeOff;

    public static final ImageVector getVolumeOff(Icons.Filled filled) {
        ImageVector imageVector = _volumeOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.VolumeOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.77f, -1.02f, -3.29f, -2.5f, -4.03f);
        pathBuilderA.verticalLineToRelative(2.21f);
        pathBuilderA.lineToRelative(2.45f, 2.45f);
        pathBuilderA.curveToRelative(0.03f, -0.2f, 0.05f, -0.41f, 0.05f, -0.63f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.94f, -0.2f, 1.82f, -0.54f, 2.64f);
        pathBuilderA.lineToRelative(1.51f, 1.51f);
        pathBuilderA.curveTo(20.63f, 14.91f, 21.0f, 13.5f, 21.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -4.28f, -2.99f, -7.86f, -7.0f, -8.77f);
        pathBuilderA.verticalLineToRelative(2.06f);
        pathBuilderA.curveToRelative(2.89f, 0.86f, 5.0f, 3.54f, 5.0f, 6.71f);
        a.p(pathBuilderA, 4.27f, 3.0f, 3.0f, 4.27f);
        pathBuilderA.lineTo(7.73f, 9.0f);
        a.h(pathBuilderA, 3.0f, 9.0f, 6.0f, 4.0f);
        pathBuilderA.lineToRelative(5.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(-6.73f);
        pathBuilderA.lineToRelative(4.25f, 4.25f);
        pathBuilderA.curveToRelative(-0.67f, 0.52f, -1.42f, 0.93f, -2.25f, 1.18f);
        pathBuilderA.verticalLineToRelative(2.06f);
        pathBuilderA.curveToRelative(1.38f, -0.31f, 2.63f, -0.95f, 3.69f, -1.81f);
        pathBuilderA.lineTo(19.73f, 21.0f);
        pathBuilderA.lineTo(21.0f, 19.73f);
        d.C(pathBuilderA, -9.0f, -9.0f, 4.27f, 3.0f);
        pathBuilderA.moveTo(12.0f, 4.0f);
        pathBuilderA.lineTo(9.91f, 6.09f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 12.0f, 8.18f, 12.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _volumeOff = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getVolumeOff$annotations(Icons.Filled filled) {
    }
}

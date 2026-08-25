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
import v.b;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ringVolume", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RingVolume", "Landroidx/compose/material/icons/Icons$Sharp;", "getRingVolume", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RingVolumeKt {
    private static ImageVector _ringVolume;

    public static final ImageVector getRingVolume(Icons.Sharp sharp) {
        ImageVector imageVector = _ringVolume;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.RingVolume", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.16f, 6.26f, -1.41f, -1.41f);
        pathBuilderR.lineToRelative(-3.56f, 3.55f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.reflectiveCurveToRelative(3.45f, -3.52f, 3.56f, -3.55f);
        b.w(pathBuilderR, 11.0f, 2.0f, 2.0f, 5.0f);
        f.x(pathBuilderR, -2.0f, 6.4f, 9.81f);
        pathBuilderR.lineTo(7.81f, 8.4f);
        pathBuilderR.lineTo(4.26f, 4.84f);
        pathBuilderR.lineTo(2.84f, 6.26f);
        pathBuilderR.curveToRelative(0.11f, 0.03f, 3.56f, 3.55f, 3.56f, 3.55f);
        a.z(pathBuilderR, 0.0f, 17.39f, 3.68f, 3.68f);
        pathBuilderR.lineToRelative(3.92f, -3.11f);
        pathBuilderR.verticalLineToRelative(-3.37f);
        pathBuilderR.curveToRelative(2.85f, -0.93f, 5.94f, -0.93f, 8.8f, 0.0f);
        pathBuilderR.verticalLineToRelative(3.38f);
        pathBuilderR.lineToRelative(3.91f, 3.1f);
        pathBuilderR.lineTo(24.0f, 17.39f);
        pathBuilderR.curveToRelative(-6.41f, -7.19f, -17.59f, -7.19f, -24.0f, 0.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ringVolume = imageVectorBuild;
        return imageVectorBuild;
    }
}

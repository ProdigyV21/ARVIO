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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_surroundSound", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SurroundSound", "Landroidx/compose/material/icons/Icons$Sharp;", "getSurroundSound", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurroundSoundKt {
    private static ImageVector _surroundSound;

    public static final ImageVector getSurroundSound(Icons.Sharp sharp) {
        ImageVector imageVector = _surroundSound;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SurroundSound", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(22.0f, 4.0f, 2.0f, 16.0f, 20.0f);
        f.r(pathBuilderH, 4.0f, 7.76f, 16.24f);
        pathBuilderH.lineToRelative(-1.41f, 1.41f);
        pathBuilderH.curveTo(4.78f, 16.1f, 4.0f, 14.05f, 4.0f, 12.0f);
        pathBuilderH.reflectiveCurveToRelative(0.78f, -4.1f, 2.34f, -5.66f);
        pathBuilderH.lineToRelative(1.41f, 1.41f);
        pathBuilderH.curveTo(6.59f, 8.93f, 6.0f, 10.46f, 6.0f, 12.0f);
        pathBuilderH.reflectiveCurveTo(6.59f, 15.07f, 7.76f, 16.24f);
        pathBuilderH.close();
        pathBuilderH.moveTo(12.0f, 16.0f);
        pathBuilderH.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderH.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderH.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderH.reflectiveCurveTo(14.21f, 16.0f, 12.0f, 16.0f);
        a.z(pathBuilderH, 17.66f, 17.66f, -1.41f, -1.41f);
        pathBuilderH.curveTo(17.41f, 15.07f, 18.0f, 13.54f, 18.0f, 12.0f);
        pathBuilderH.reflectiveCurveToRelative(-0.59f, -3.07f, -1.76f, -4.24f);
        pathBuilderH.lineToRelative(1.41f, -1.41f);
        pathBuilderH.curveTo(19.22f, 7.9f, 20.0f, 9.95f, 20.0f, 12.0f);
        pathBuilderH.reflectiveCurveTo(19.22f, 16.1f, 17.66f, 17.66f);
        pathBuilderH.close();
        pathBuilderH.moveTo(12.0f, 10.0f);
        pathBuilderH.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderH.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderH.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderH, 13.1f, 10.0f, 12.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _surroundSound = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ringVolume", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RingVolume", "Landroidx/compose/material/icons/Icons$Filled;", "getRingVolume", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RingVolumeKt {
    private static ImageVector _ringVolume;

    public static final ImageVector getRingVolume(Icons.Filled filled) {
        ImageVector imageVector = _ringVolume;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RingVolume", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.71f, 16.67f);
        pathBuilderA.curveTo(20.66f, 13.78f, 16.54f, 12.0f, 12.0f, 12.0f);
        pathBuilderA.curveTo(7.46f, 12.0f, 3.34f, 13.78f, 0.29f, 16.67f);
        pathBuilderA.curveToRelative(-0.18f, 0.18f, -0.29f, 0.43f, -0.29f, 0.71f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.11f, 0.53f, 0.29f, 0.71f);
        pathBuilderA.lineToRelative(2.48f, 2.48f);
        pathBuilderA.curveToRelative(0.18f, 0.18f, 0.43f, 0.29f, 0.71f, 0.29f);
        pathBuilderA.curveToRelative(0.27f, 0.0f, 0.52f, -0.11f, 0.7f, -0.28f);
        pathBuilderA.curveToRelative(0.79f, -0.74f, 1.69f, -1.36f, 2.66f, -1.85f);
        pathBuilderA.curveToRelative(0.33f, -0.16f, 0.56f, -0.5f, 0.56f, -0.9f);
        pathBuilderA.verticalLineToRelative(-3.1f);
        pathBuilderA.curveToRelative(1.45f, -0.48f, 3.0f, -0.73f, 4.6f, -0.73f);
        pathBuilderA.reflectiveCurveToRelative(3.15f, 0.25f, 4.6f, 0.72f);
        pathBuilderA.verticalLineToRelative(3.1f);
        pathBuilderA.curveToRelative(0.0f, 0.39f, 0.23f, 0.74f, 0.56f, 0.9f);
        pathBuilderA.curveToRelative(0.98f, 0.49f, 1.87f, 1.12f, 2.66f, 1.85f);
        pathBuilderA.curveToRelative(0.18f, 0.18f, 0.43f, 0.28f, 0.7f, 0.28f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.53f, -0.11f, 0.71f, -0.29f);
        pathBuilderA.lineToRelative(2.48f, -2.48f);
        pathBuilderA.curveToRelative(0.18f, -0.18f, 0.29f, -0.43f, 0.29f, -0.71f);
        pathBuilderA.curveToRelative(0.0f, -0.27f, -0.11f, -0.52f, -0.29f, -0.7f);
        a.z(pathBuilderA, 21.16f, 6.26f, -1.41f, -1.41f);
        pathBuilderA.lineToRelative(-3.56f, 3.55f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(3.45f, -3.52f, 3.56f, -3.55f);
        b.w(pathBuilderA, 13.0f, 2.0f, -2.0f, 5.0f);
        c.D(pathBuilderA, 2.0f, 2.0f, 6.4f, 9.81f);
        pathBuilderA.lineTo(7.81f, 8.4f);
        pathBuilderA.lineTo(4.26f, 4.84f);
        pathBuilderA.lineTo(2.84f, 6.26f);
        pathBuilderA.curveToRelative(0.11f, 0.03f, 3.56f, 3.55f, 3.56f, 3.55f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ringVolume = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.filled;

import a0.b;
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
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motionPhotosPaused", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MotionPhotosPaused", "Landroidx/compose/material/icons/Icons$Filled;", "getMotionPhotosPaused", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotionPhotosPausedKt {
    private static ImageVector _motionPhotosPaused;

    public static final ImageVector getMotionPhotosPaused(Icons.Filled filled) {
        ImageVector imageVector = _motionPhotosPaused;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MotionPhotosPaused", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, -4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilderA.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.19f, 0.22f, -2.32f, 0.6f, -3.38f);
        pathBuilderA.lineTo(4.48f, 9.3f);
        pathBuilderA.curveTo(4.17f, 10.14f, 4.0f, 11.05f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.59f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.curveToRelative(-0.95f, 0.0f, -1.85f, 0.17f, -2.69f, 0.48f);
        pathBuilderA.lineTo(8.63f, 2.59f);
        pathBuilderA.curveTo(9.69f, 2.22f, 10.82f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveTo(17.52f, 2.0f, 22.0f, 6.48f, 22.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.5f, 4.0f);
        pathBuilderA.curveTo(4.67f, 4.0f, 4.0f, 4.67f, 4.0f, 5.5f);
        pathBuilderA.reflectiveCurveTo(4.67f, 7.0f, 5.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 6.33f, 7.0f, 5.5f);
        pathBuilderA.reflectiveCurveTo(6.33f, 4.0f, 5.5f, 4.0f);
        a.x(pathBuilderA, 11.0f, 16.0f, 8.0f, 9.0f);
        e.y(pathBuilderA, 8.0f, 11.0f, 15.0f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderA, 8.0f, -2.0f, 8.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _motionPhotosPaused = imageVectorBuild;
        return imageVectorBuild;
    }
}

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motionPhotosOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MotionPhotosOff", "Landroidx/compose/material/icons/Icons$Filled;", "getMotionPhotosOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotionPhotosOffKt {
    private static ImageVector _motionPhotosOff;

    public static final ImageVector getMotionPhotosOff(Icons.Filled filled) {
        ImageVector imageVector = _motionPhotosOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MotionPhotosOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.84f, 20.84f, 3.16f, 3.16f);
        pathBuilderJ.lineTo(1.89f, 4.43f);
        pathBuilderJ.lineToRelative(1.89f, 1.89f);
        pathBuilderJ.curveTo(2.66f, 7.93f, 2.0f, 9.89f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(2.11f, 0.0f, 4.07f, -0.66f, 5.68f, -1.77f);
        b.D(pathBuilderJ, 1.89f, 1.89f, 1.27f, -1.28f);
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.55f, 0.45f, -3.0f, 1.22f, -4.23f);
        pathBuilderJ.lineToRelative(1.46f, 1.46f);
        pathBuilderJ.curveTo(6.25f, 10.06f, 6.0f, 11.0f, 6.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderJ.curveToRelative(1.0f, 0.0f, 1.94f, -0.25f, 2.77f, -0.68f);
        pathBuilderJ.lineToRelative(1.46f, 1.46f);
        pathBuilderJ.curveTo(15.0f, 19.55f, 13.55f, 20.0f, 12.0f, 20.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(6.32f, 3.77f);
        pathBuilderJ.curveTo(7.93f, 2.66f, 9.89f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveToRelative(5.52f, 0.0f, 10.0f, 4.48f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.11f, -0.66f, 4.07f, -1.77f, 5.68f);
        pathBuilderJ.lineToRelative(-1.45f, -1.45f);
        pathBuilderJ.curveTo(19.55f, 15.0f, 20.0f, 13.55f, 20.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(-1.55f, 0.0f, -3.0f, 0.45f, -4.23f, 1.22f);
        c.z(pathBuilderJ, 6.32f, 3.77f, 18.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.0f, -0.25f, 1.94f, -0.68f, 2.77f);
        pathBuilderJ.lineTo(9.23f, 6.68f);
        pathBuilderJ.curveTo(10.06f, 6.25f, 11.0f, 6.0f, 12.0f, 6.0f);
        pathBuilderJ.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _motionPhotosOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

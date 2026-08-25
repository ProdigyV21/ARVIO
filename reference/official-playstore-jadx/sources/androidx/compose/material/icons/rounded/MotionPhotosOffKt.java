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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motionPhotosOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MotionPhotosOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getMotionPhotosOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotionPhotosOffKt {
    private static ImageVector _motionPhotosOff;

    public static final ImageVector getMotionPhotosOff(Icons.Rounded rounded) {
        ImageVector imageVector = _motionPhotosOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MotionPhotosOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-0.92f, 0.0f, -1.8f, 0.22f, -2.58f, 0.59f);
        pathBuilderA.lineToRelative(7.99f, 7.99f);
        pathBuilderA.curveTo(17.78f, 13.8f, 18.0f, 12.92f, 18.0f, 12.0f);
        pathBuilderA.curveTo(18.0f, 8.69f, 15.31f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.1f, 3.51f, 2.1f, 3.51f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.42f);
        pathBuilderJ.lineToRelative(1.56f, 1.56f);
        pathBuilderJ.curveToRelative(-1.25f, 1.88f, -1.88f, 4.21f, -1.59f, 6.7f);
        pathBuilderJ.curveToRelative(0.52f, 4.54f, 4.21f, 8.23f, 8.75f, 8.75f);
        pathBuilderJ.curveToRelative(2.49f, 0.28f, 4.81f, -0.34f, 6.69f, -1.59f);
        pathBuilderJ.lineToRelative(1.56f, 1.56f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.42f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(3.51f, 3.51f);
        pathBuilderJ.curveTo(3.13f, 3.13f, 2.49f, 3.12f, 2.1f, 3.51f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.48f, 0.41f, -2.86f, 1.12f, -4.06f);
        pathBuilderJ.lineToRelative(1.47f, 1.47f);
        pathBuilderJ.curveTo(6.22f, 10.2f, 6.0f, 11.08f, 6.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.92f, 0.0f, 1.8f, -0.22f, 2.58f, -0.59f);
        pathBuilderJ.lineToRelative(1.47f, 1.47f);
        pathBuilderJ.curveTo(14.86f, 19.59f, 13.48f, 20.0f, 12.0f, 20.0f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 4.0f);
        pathBuilderA2.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.48f, -0.41f, 2.86f, -1.12f, 4.05f);
        pathBuilderA2.lineToRelative(1.45f, 1.45f);
        pathBuilderA2.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA2.curveTo(9.96f, 2.0f, 8.07f, 2.61f, 6.49f, 3.66f);
        pathBuilderA2.lineToRelative(1.45f, 1.45f);
        pathBuilderA2.curveTo(9.14f, 4.41f, 10.52f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _motionPhotosOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

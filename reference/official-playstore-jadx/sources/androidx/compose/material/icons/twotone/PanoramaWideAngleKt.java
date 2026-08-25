package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaWideAngle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaWideAngle", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPanoramaWideAngle", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaWideAngleKt {
    private static ImageVector _panoramaWideAngle;

    public static final ImageVector getPanoramaWideAngle(Icons.TwoTone twoTone) {
        ImageVector imageVector = _panoramaWideAngle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PanoramaWideAngle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-2.45f, 0.0f, -4.71f, 0.2f, -7.29f, 0.64f);
        pathBuilderA.curveTo(4.24f, 8.42f, 4.0f, 10.22f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.78f, 0.24f, 3.58f, 0.71f, 5.36f);
        pathBuilderA.curveToRelative(2.58f, 0.44f, 4.84f, 0.64f, 7.29f, 0.64f);
        pathBuilderA.reflectiveCurveToRelative(4.71f, -0.2f, 7.29f, -0.64f);
        pathBuilderA.curveToRelative(0.47f, -1.78f, 0.71f, -3.58f, 0.71f, -5.36f);
        pathBuilderA.curveToRelative(0.0f, -1.78f, -0.24f, -3.58f, -0.71f, -5.36f);
        pathBuilderA.curveTo(16.71f, 6.2f, 14.45f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.13f, 5.78f, -0.25f, -0.89f);
        pathBuilderR.lineToRelative(-0.93f, -0.16f);
        pathBuilderR.curveTo(17.22f, 4.24f, 14.73f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.reflectiveCurveToRelative(-5.22f, 0.24f, -7.95f, 0.72f);
        pathBuilderR.lineToRelative(-0.93f, 0.16f);
        pathBuilderR.lineToRelative(-0.25f, 0.9f);
        pathBuilderR.curveTo(2.29f, 7.85f, 2.0f, 9.93f, 2.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(0.29f, 4.15f, 0.87f, 6.22f);
        pathBuilderR.lineToRelative(0.25f, 0.89f);
        pathBuilderR.lineToRelative(0.93f, 0.16f);
        pathBuilderR.curveToRelative(2.73f, 0.49f, 5.22f, 0.73f, 7.95f, 0.73f);
        pathBuilderR.reflectiveCurveToRelative(5.22f, -0.24f, 7.95f, -0.72f);
        pathBuilderR.lineToRelative(0.93f, -0.16f);
        pathBuilderR.lineToRelative(0.25f, -0.89f);
        pathBuilderR.curveToRelative(0.58f, -2.08f, 0.87f, -4.16f, 0.87f, -6.23f);
        pathBuilderR.reflectiveCurveToRelative(-0.29f, -4.15f, -0.87f, -6.22f);
        pathBuilderR.close();
        pathBuilderR.moveTo(19.29f, 17.36f);
        pathBuilderR.curveToRelative(-2.58f, 0.44f, -4.84f, 0.64f, -7.29f, 0.64f);
        pathBuilderR.reflectiveCurveToRelative(-4.71f, -0.2f, -7.29f, -0.64f);
        pathBuilderR.curveTo(4.24f, 15.58f, 4.0f, 13.78f, 4.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -1.78f, 0.24f, -3.58f, 0.71f, -5.36f);
        pathBuilderR.curveTo(7.29f, 6.2f, 9.55f, 6.0f, 12.0f, 6.0f);
        pathBuilderR.reflectiveCurveToRelative(4.71f, 0.2f, 7.29f, 0.64f);
        pathBuilderR.curveToRelative(0.47f, 1.78f, 0.71f, 3.58f, 0.71f, 5.36f);
        pathBuilderR.curveToRelative(0.0f, 1.78f, -0.24f, 3.58f, -0.71f, 5.36f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaWideAngle = imageVectorBuild;
        return imageVectorBuild;
    }
}

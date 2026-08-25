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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flipCameraAndroid", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlipCameraAndroid", "Landroidx/compose/material/icons/Icons$Rounded;", "getFlipCameraAndroid", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlipCameraAndroidKt {
    private static ImageVector _flipCameraAndroid;

    public static final ImageVector getFlipCameraAndroid(Icons.Rounded rounded) {
        ImageVector imageVector = _flipCameraAndroid;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FlipCameraAndroid", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 9.0f, 10.34f, 9.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(8.0f, 9.0f, 8.0f, 9.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineTo(5.09f);
        pathBuilderJ.curveTo(6.47f, 5.61f, 9.05f, 4.0f, 12.0f, 4.0f);
        pathBuilderJ.curveToRelative(3.49f, 0.0f, 6.45f, 2.24f, 7.54f, 5.36f);
        pathBuilderJ.curveTo(19.68f, 9.75f, 20.07f, 10.0f, 20.48f, 10.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.68f, 0.0f, 1.18f, -0.67f, 0.96f, -1.31f);
        pathBuilderJ.curveTo(20.07f, 4.79f, 16.36f, 2.0f, 12.0f, 2.0f);
        pathBuilderJ.curveTo(8.73f, 2.0f, 5.82f, 3.58f, 4.0f, 6.01f);
        pathBuilderJ.verticalLineTo(5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveTo(2.45f, 4.0f, 2.0f, 4.45f, 2.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        pathBuilderJ.curveTo(7.55f, 10.0f, 8.0f, 9.55f, 8.0f, 9.0f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(16.0f, 15.0f, 16.0f, 15.0f);
        pathBuilderJ2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ2.horizontalLineToRelative(1.91f);
        pathBuilderJ2.curveToRelative(-1.38f, 2.39f, -3.96f, 4.0f, -6.91f, 4.0f);
        pathBuilderJ2.curveToRelative(-3.49f, 0.0f, -6.45f, -2.24f, -7.54f, -5.36f);
        pathBuilderJ2.curveTo(4.32f, 14.25f, 3.93f, 14.0f, 3.52f, 14.0f);
        pathBuilderJ2.horizontalLineToRelative(0.0f);
        pathBuilderJ2.curveToRelative(-0.68f, 0.0f, -1.18f, 0.67f, -0.96f, 1.31f);
        pathBuilderJ2.curveTo(3.93f, 19.21f, 7.64f, 22.0f, 12.0f, 22.0f);
        pathBuilderJ2.curveToRelative(3.27f, 0.0f, 6.18f, -1.58f, 8.0f, -4.01f);
        pathBuilderJ2.verticalLineTo(19.0f);
        pathBuilderJ2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ2.horizontalLineToRelative(0.0f);
        pathBuilderJ2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ2.verticalLineToRelative(-4.0f);
        pathBuilderJ2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ2.horizontalLineToRelative(-4.0f);
        pathBuilderJ2.curveTo(16.45f, 14.0f, 16.0f, 14.45f, 16.0f, 15.0f);
        pathBuilderJ2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flipCameraAndroid = imageVectorBuild;
        return imageVectorBuild;
    }
}

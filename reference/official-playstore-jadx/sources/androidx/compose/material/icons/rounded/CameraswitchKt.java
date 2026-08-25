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
import v.c;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cameraswitch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cameraswitch", "Landroidx/compose/material/icons/Icons$Rounded;", "getCameraswitch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraswitchKt {
    private static ImageVector _cameraswitch;

    public static final ImageVector getCameraswitch(Icons.Rounded rounded) {
        ImageVector imageVector = _cameraswitch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Cameraswitch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(16.0f, 7.0f, -1.0f, -1.0f, -1.0f);
        a.f(pathBuilderG, -4.0f, 9.0f, 7.0f, 8.0f);
        pathBuilderG.curveTo(6.9f, 7.0f, 6.0f, 7.9f, 6.0f, 9.0f);
        pathBuilderG.verticalLineToRelative(6.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(8.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(9.0f);
        pathBuilderG.curveTo(18.0f, 7.9f, 17.1f, 7.0f, 16.0f, 7.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(12.0f, 14.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderG.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderG.curveTo(14.0f, 13.1f, 13.1f, 14.0f, 12.0f, 14.0f);
        pathBuilderG.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.45f, 0.28f);
        pathBuilderA.curveTo(9.05f, 0.36f, 8.9f, 0.84f, 9.19f, 1.12f);
        pathBuilderA.lineToRelative(3.01f, 3.01f);
        pathBuilderA.curveToRelative(0.32f, 0.31f, 0.85f, 0.09f, 0.85f, -0.35f);
        pathBuilderA.verticalLineTo(2.04f);
        pathBuilderA.curveToRelative(4.45f, 0.44f, 8.06f, 3.82f, 8.84f, 8.17f);
        pathBuilderA.curveToRelative(0.08f, 0.46f, 0.5f, 0.78f, 0.97f, 0.78f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.09f, -0.57f, 0.98f, -1.18f);
        pathBuilderA.curveTo(22.61f, 2.89f, 15.79f, -1.12f, 9.45f, 0.28f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(11.8f, 19.87f);
        pathBuilderA2.curveToRelative(-0.32f, -0.32f, -0.85f, -0.09f, -0.85f, 0.35f);
        pathBuilderA2.verticalLineToRelative(1.74f);
        pathBuilderA2.curveToRelative(-4.45f, -0.44f, -8.06f, -3.82f, -8.84f, -8.17f);
        pathBuilderA2.curveToRelative(-0.08f, -0.46f, -0.5f, -0.78f, -0.97f, -0.78f);
        pathBuilderA2.curveToRelative(-0.62f, 0.0f, -1.09f, 0.57f, -0.98f, 1.18f);
        pathBuilderA2.curveToRelative(1.24f, 6.92f, 8.06f, 10.93f, 14.4f, 9.53f);
        pathBuilderA2.curveToRelative(0.39f, -0.09f, 0.55f, -0.56f, 0.26f, -0.85f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 11.8f, 19.87f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cameraswitch = imageVectorBuild;
        return imageVectorBuild;
    }
}

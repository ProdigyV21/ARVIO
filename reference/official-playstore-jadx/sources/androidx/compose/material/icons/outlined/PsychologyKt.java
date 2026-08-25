package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_psychology", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Psychology", "Landroidx/compose/material/icons/Icons$Outlined;", "getPsychology", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PsychologyKt {
    private static ImageVector _psychology;

    public static final ImageVector getPsychology(Icons.Outlined outlined) {
        ImageVector imageVector = _psychology;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Psychology", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.82f, 7.22f, -1.0f, 0.4f);
        pathBuilderR.curveToRelative(-0.21f, -0.16f, -0.43f, -0.29f, -0.67f, -0.39f);
        pathBuilderR.lineTo(14.0f, 6.17f);
        pathBuilderR.curveTo(13.98f, 6.07f, 13.9f, 6.0f, 13.8f, 6.0f);
        pathBuilderR.horizontalLineToRelative(-1.6f);
        pathBuilderR.curveToRelative(-0.1f, 0.0f, -0.18f, 0.07f, -0.19f, 0.17f);
        pathBuilderR.lineToRelative(-0.15f, 1.06f);
        pathBuilderR.curveToRelative(-0.24f, 0.1f, -0.47f, 0.23f, -0.67f, 0.39f);
        pathBuilderR.lineToRelative(-1.0f, -0.4f);
        pathBuilderR.curveToRelative(-0.09f, -0.03f, -0.2f, 0.0f, -0.24f, 0.09f);
        pathBuilderR.lineToRelative(-0.8f, 1.38f);
        pathBuilderR.curveToRelative(-0.05f, 0.09f, -0.03f, 0.2f, 0.05f, 0.26f);
        pathBuilderR.lineToRelative(0.85f, 0.66f);
        pathBuilderR.curveTo(10.02f, 9.73f, 10.0f, 9.87f, 10.0f, 10.0f);
        pathBuilderR.curveToRelative(0.0f, 0.13f, 0.01f, 0.26f, 0.03f, 0.39f);
        pathBuilderR.lineToRelative(-0.84f, 0.66f);
        pathBuilderR.curveToRelative(-0.08f, 0.06f, -0.1f, 0.17f, -0.05f, 0.25f);
        pathBuilderR.lineToRelative(0.8f, 1.39f);
        pathBuilderR.curveToRelative(0.05f, 0.09f, 0.15f, 0.12f, 0.25f, 0.09f);
        pathBuilderR.lineToRelative(0.99f, -0.4f);
        pathBuilderR.curveToRelative(0.21f, 0.16f, 0.43f, 0.29f, 0.68f, 0.39f);
        pathBuilderR.lineTo(12.0f, 13.83f);
        pathBuilderR.curveToRelative(0.02f, 0.1f, 0.1f, 0.17f, 0.2f, 0.17f);
        pathBuilderR.horizontalLineToRelative(1.6f);
        pathBuilderR.curveToRelative(0.1f, 0.0f, 0.18f, -0.07f, 0.2f, -0.17f);
        pathBuilderR.lineToRelative(0.15f, -1.06f);
        pathBuilderR.curveToRelative(0.24f, -0.1f, 0.47f, -0.23f, 0.67f, -0.39f);
        pathBuilderR.lineToRelative(0.99f, 0.4f);
        pathBuilderR.curveToRelative(0.09f, 0.04f, 0.2f, 0.0f, 0.24f, -0.09f);
        pathBuilderR.lineToRelative(0.8f, -1.39f);
        pathBuilderR.curveToRelative(0.05f, -0.09f, 0.03f, -0.19f, -0.05f, -0.25f);
        pathBuilderR.lineToRelative(-0.83f, -0.66f);
        pathBuilderR.curveTo(15.99f, 10.26f, 16.0f, 10.13f, 16.0f, 10.0f);
        pathBuilderR.curveToRelative(0.0f, -0.14f, -0.01f, -0.27f, -0.03f, -0.39f);
        pathBuilderR.lineToRelative(0.85f, -0.66f);
        pathBuilderR.curveToRelative(0.08f, -0.06f, 0.1f, -0.17f, 0.05f, -0.26f);
        pathBuilderR.lineToRelative(-0.8f, -1.38f);
        pathBuilderR.curveTo(16.02f, 7.22f, 15.91f, 7.19f, 15.82f, 7.22f);
        pathBuilderR.close();
        pathBuilderR.moveTo(13.0f, 11.43f);
        pathBuilderR.curveToRelative(-0.79f, 0.0f, -1.43f, -0.64f, -1.43f, -1.43f);
        pathBuilderR.reflectiveCurveTo(12.21f, 8.57f, 13.0f, 8.57f);
        pathBuilderR.reflectiveCurveToRelative(1.43f, 0.64f, 1.43f, 1.43f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 13.79f, 11.43f, 13.0f, 11.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.94f, 9.06f);
        pathBuilderA.curveToRelative(-0.43f, -3.27f, -3.23f, -5.86f, -6.53f, -6.05f);
        pathBuilderA.curveTo(13.27f, 3.0f, 13.14f, 3.0f, 13.0f, 3.0f);
        pathBuilderA.curveTo(9.47f, 3.0f, 6.57f, 5.61f, 6.08f, 9.0f);
        pathBuilderA.lineToRelative(-1.93f, 3.48f);
        pathBuilderA.curveTo(3.74f, 13.14f, 4.22f, 14.0f, 5.0f, 14.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.o(pathBuilderA, 1.0f, 3.0f, 7.0f, -4.68f);
        pathBuilderA.curveTo(18.62f, 15.07f, 20.35f, 12.24f, 19.94f, 9.06f);
        a.p(pathBuilderA, 14.89f, 14.63f, 14.0f, 15.05f);
        e.x(pathBuilderA, 19.0f, -3.0f, -3.0f, 8.0f);
        a0.a.o(pathBuilderA, -4.0f, 6.7f, 1.33f, -2.33f);
        pathBuilderA.curveTo(8.21f, 7.06f, 10.35f, 5.0f, 13.0f, 5.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.curveTo(18.0f, 12.09f, 16.71f, 13.88f, 14.89f, 14.63f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _psychology = imageVectorBuild;
        return imageVectorBuild;
    }
}

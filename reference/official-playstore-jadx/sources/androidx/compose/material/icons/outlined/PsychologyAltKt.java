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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_psychologyAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PsychologyAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getPsychologyAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PsychologyAltKt {
    private static ImageVector _psychologyAlt;

    public static final ImageVector getPsychologyAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _psychologyAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PsychologyAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.94f, 9.06f);
        pathBuilderA.curveTo(19.5f, 5.73f, 16.57f, 3.0f, 13.0f, 3.0f);
        pathBuilderA.curveTo(9.47f, 3.0f, 6.57f, 5.61f, 6.08f, 9.0f);
        pathBuilderA.lineToRelative(-1.93f, 3.48f);
        pathBuilderA.curveTo(3.74f, 13.14f, 4.22f, 14.0f, 5.0f, 14.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.lineToRelative(0.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        e.g(pathBuilderA, 1.0f, 3.0f, 7.0f);
        pathBuilderA.lineToRelative(0.0f, -4.68f);
        pathBuilderA.curveTo(18.62f, 15.07f, 20.35f, 12.24f, 19.94f, 9.06f);
        a.p(pathBuilderA, 14.89f, 14.63f, 14.0f, 15.05f);
        e.x(pathBuilderA, 19.0f, -3.0f, -3.0f, 8.0f);
        a0.a.o(pathBuilderA, -4.0f, 6.7f, 1.33f, -2.33f);
        pathBuilderA.curveTo(8.21f, 7.06f, 10.35f, 5.0f, 13.0f, 5.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.curveTo(18.0f, 12.09f, 16.71f, 13.88f, 14.89f, 14.63f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.5f, 12.54f);
        pathBuilderA2.curveToRelative(-0.41f, 0.0f, -0.74f, 0.31f, -0.74f, 0.73f);
        pathBuilderA2.curveToRelative(0.0f, 0.41f, 0.33f, 0.74f, 0.74f, 0.74f);
        pathBuilderA2.curveToRelative(0.42f, 0.0f, 0.73f, -0.33f, 0.73f, -0.74f);
        pathBuilderA2.curveTo(13.23f, 12.85f, 12.92f, 12.54f, 12.5f, 12.54f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(12.5f, 7.0f);
        pathBuilderA3.curveToRelative(-1.03f, 0.0f, -1.74f, 0.67f, -2.0f, 1.45f);
        pathBuilderA3.lineToRelative(0.96f, 0.4f);
        pathBuilderA3.curveToRelative(0.13f, -0.39f, 0.43f, -0.86f, 1.05f, -0.86f);
        pathBuilderA3.curveToRelative(0.95f, 0.0f, 1.13f, 0.89f, 0.8f, 1.36f);
        pathBuilderA3.curveToRelative(-0.32f, 0.45f, -0.86f, 0.75f, -1.14f, 1.26f);
        pathBuilderA3.curveToRelative(-0.23f, 0.4f, -0.18f, 0.87f, -0.18f, 1.16f);
        pathBuilderA3.horizontalLineToRelative(1.06f);
        pathBuilderA3.curveToRelative(0.0f, -0.55f, 0.04f, -0.65f, 0.13f, -0.82f);
        pathBuilderA3.curveToRelative(0.23f, -0.42f, 0.65f, -0.62f, 1.09f, -1.27f);
        pathBuilderA3.curveToRelative(0.4f, -0.59f, 0.25f, -1.38f, -0.01f, -1.8f);
        pathBuilderA3.curveTo(13.95f, 7.39f, 13.36f, 7.0f, 12.5f, 7.0f);
        pathBuilderA3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _psychologyAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}

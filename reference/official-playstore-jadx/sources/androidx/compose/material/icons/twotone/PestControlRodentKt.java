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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pestControlRodent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PestControlRodent", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPestControlRodent", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PestControlRodentKt {
    private static ImageVector _pestControlRodent;

    public static final ImageVector getPestControlRodent(Icons.TwoTone twoTone) {
        ImageVector imageVector = _pestControlRodent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PestControlRodent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.65f, 14.75f);
        pathBuilderA.curveTo(17.87f, 14.38f, 18.0f, 13.96f, 18.0f, 13.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(-1.3f, 0.0f, -2.5f, 1.08f, -2.5f, 2.5f);
        pathBuilderA.curveToRelative(0.0f, 0.69f, 0.28f, 1.32f, 0.73f, 1.77f);
        pathBuilderA.lineToRelative(-1.41f, 1.41f);
        pathBuilderA.curveTo(11.5f, 15.87f, 11.0f, 14.74f, 11.0f, 13.5f);
        pathBuilderA.curveToRelative(0.0f, -0.92f, 0.28f, -1.76f, 0.75f, -2.47f);
        pathBuilderA.curveToRelative(-0.22f, 0.01f, -0.44f, 0.02f, -0.67f, 0.07f);
        pathBuilderA.curveToRelative(-1.48f, 0.32f, -2.68f, 1.53f, -2.99f, 3.01f);
        pathBuilderA.curveToRelative(-0.26f, 1.24f, 0.02f, 2.45f, 0.8f, 3.41f);
        pathBuilderA.curveTo(9.66f, 18.46f, 10.79f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.horizontalLineToRelative(6.53f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.17f, -0.81f, -0.48f, -1.09f);
        c.z(pathBuilderA, 17.65f, 14.75f, 17.0f, 18.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveTo(18.0f, 17.55f, 17.55f, 18.0f, 17.0f, 18.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(17.0f, 17.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.86f, 14.97f, -0.93f, -0.84f);
        pathBuilderR.curveToRelative(0.48f, -3.45f, -2.87f, -6.04f, -6.05f, -4.82f);
        pathBuilderR.curveTo(13.3f, 9.11f, 12.66f, 9.0f, 12.0f, 9.0f);
        pathBuilderR.curveToRelative(-4.26f, 0.0f, -5.65f, 3.58f, -5.89f, 4.85f);
        pathBuilderR.curveTo(4.89f, 13.47f, 4.0f, 12.35f, 4.0f, 11.0f);
        pathBuilderR.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderR.horizontalLineToRelative(2.5f);
        pathBuilderR.curveTo(10.88f, 8.0f, 12.0f, 6.88f, 12.0f, 5.5f);
        pathBuilderR.curveTo(12.0f, 4.12f, 10.88f, 3.0f, 9.5f, 3.0f);
        pathBuilderR.horizontalLineTo(8.0f);
        pathBuilderR.curveTo(7.45f, 3.0f, 7.0f, 3.45f, 7.0f, 4.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(1.5f);
        pathBuilderR.curveTo(9.78f, 5.0f, 10.0f, 5.22f, 10.0f, 5.5f);
        pathBuilderR.curveTo(10.0f, 5.78f, 9.78f, 6.0f, 9.5f, 6.0f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, 2.44f, 1.76f, 4.47f, 4.07f, 4.91f);
        pathBuilderR.curveTo(6.51f, 18.79f, 8.99f, 21.0f, 12.0f, 21.0f);
        pathBuilderR.horizontalLineToRelative(6.53f);
        pathBuilderR.curveTo(21.64f, 21.0f, 23.23f, 17.11f, 20.86f, 14.97f);
        w.a.r(pathBuilderR, 18.53f, 19.0f, 12.0f);
        pathBuilderR.curveToRelative(-1.21f, 0.0f, -2.34f, -0.54f, -3.11f, -1.48f);
        pathBuilderR.curveToRelative(-0.78f, -0.95f, -1.06f, -2.16f, -0.8f, -3.41f);
        pathBuilderR.curveToRelative(0.31f, -1.48f, 1.51f, -2.69f, 2.99f, -3.01f);
        pathBuilderR.curveToRelative(0.22f, -0.05f, 0.45f, -0.06f, 0.67f, -0.07f);
        pathBuilderR.curveTo(11.28f, 11.74f, 11.0f, 12.58f, 11.0f, 13.5f);
        pathBuilderR.curveToRelative(0.0f, 1.24f, 0.5f, 2.37f, 1.32f, 3.18f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.curveTo(13.28f, 14.82f, 13.0f, 14.19f, 13.0f, 13.5f);
        pathBuilderR.curveToRelative(0.0f, -1.42f, 1.2f, -2.5f, 2.5f, -2.5f);
        pathBuilderR.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderR.curveToRelative(0.0f, 0.46f, -0.13f, 0.88f, -0.35f, 1.25f);
        pathBuilderR.lineToRelative(1.87f, 1.7f);
        pathBuilderR.curveToRelative(0.31f, 0.28f, 0.48f, 0.67f, 0.48f, 1.09f);
        pathBuilderR.curveTo(20.0f, 18.34f, 19.34f, 19.0f, 18.53f, 19.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pestControlRodent = imageVectorBuild;
        return imageVectorBuild;
    }
}

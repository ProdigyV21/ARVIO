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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pix", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pix", "Landroidx/compose/material/icons/Icons$Outlined;", "getPix", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PixKt {
    private static ImageVector _pix;

    public static final ImageVector getPix(Icons.Outlined outlined) {
        ImageVector imageVector = _pix;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Pix", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.45f, 16.52f, -3.01f, -3.01f);
        pathBuilderR.curveToRelative(-0.11f, -0.11f, -0.24f, -0.13f, -0.31f, -0.13f);
        pathBuilderR.reflectiveCurveToRelative(-0.2f, 0.02f, -0.31f, 0.13f);
        pathBuilderR.lineTo(8.8f, 16.53f);
        pathBuilderR.curveToRelative(-0.34f, 0.34f, -0.87f, 0.89f, -2.64f, 0.89f);
        pathBuilderR.lineToRelative(3.71f, 3.7f);
        pathBuilderR.curveToRelative(1.17f, 1.17f, 3.07f, 1.17f, 4.24f, 0.0f);
        pathBuilderR.lineToRelative(3.72f, -3.71f);
        pathBuilderR.curveTo(16.92f, 17.41f, 16.16f, 17.23f, 15.45f, 16.52f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(8.8f, 7.47f, 3.02f, 3.02f);
        pathBuilderR2.curveToRelative(0.08f, 0.08f, 0.2f, 0.13f, 0.31f, 0.13f);
        pathBuilderR2.reflectiveCurveToRelative(0.23f, -0.05f, 0.31f, -0.13f);
        pathBuilderR2.lineToRelative(2.99f, -2.99f);
        pathBuilderR2.curveToRelative(0.71f, -0.74f, 1.52f, -0.91f, 2.43f, -0.91f);
        pathBuilderR2.lineToRelative(-3.72f, -3.71f);
        pathBuilderR2.curveToRelative(-1.17f, -1.17f, -3.07f, -1.17f, -4.24f, 0.0f);
        pathBuilderR2.lineToRelative(-3.71f, 3.7f);
        pathBuilderR2.curveTo(7.95f, 6.58f, 8.49f, 7.16f, 8.8f, 7.47f);
        pathBuilderR2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = y.a.b(21.11f, 9.85f, -2.25f, -2.26f, 17.6f);
        pathBuilderB.curveToRelative(-0.54f, 0.0f, -1.08f, 0.22f, -1.45f, 0.61f);
        pathBuilderB.lineToRelative(-3.0f, 3.0f);
        pathBuilderB.curveToRelative(-0.28f, 0.28f, -0.65f, 0.42f, -1.02f, 0.42f);
        pathBuilderB.curveToRelative(-0.36f, 0.0f, -0.74f, -0.15f, -1.02f, -0.42f);
        pathBuilderB.lineTo(8.09f, 8.17f);
        pathBuilderB.curveToRelative(-0.38f, -0.38f, -0.9f, -0.6f, -1.45f, -0.6f);
        pathBuilderB.horizontalLineTo(5.17f);
        pathBuilderB.lineToRelative(-2.29f, 2.3f);
        pathBuilderB.curveToRelative(-1.17f, 1.17f, -1.17f, 3.07f, 0.0f, 4.24f);
        pathBuilderB.lineToRelative(2.29f, 2.3f);
        pathBuilderB.horizontalLineToRelative(1.48f);
        pathBuilderB.curveToRelative(0.54f, 0.0f, 1.06f, -0.22f, 1.45f, -0.6f);
        pathBuilderB.lineToRelative(3.02f, -3.02f);
        pathBuilderB.curveToRelative(0.28f, -0.28f, 0.65f, -0.42f, 1.02f, -0.42f);
        pathBuilderB.curveToRelative(0.37f, 0.0f, 0.74f, 0.14f, 1.02f, 0.42f);
        pathBuilderB.lineToRelative(3.01f, 3.01f);
        pathBuilderB.curveToRelative(0.38f, 0.38f, 0.9f, 0.6f, 1.45f, 0.6f);
        pathBuilderB.horizontalLineToRelative(1.26f);
        pathBuilderB.lineToRelative(2.25f, -2.26f);
        pathBuilderB.curveTo(22.3f, 12.96f, 22.3f, 11.04f, 21.11f, 9.85f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pix = imageVectorBuild;
        return imageVectorBuild;
    }
}

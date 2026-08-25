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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_childCare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ChildCare", "Landroidx/compose/material/icons/Icons$Rounded;", "getChildCare", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChildCareKt {
    private static ImageVector _childCare;

    public static final ImageVector getChildCare(Icons.Rounded rounded) {
        ImageVector imageVector = _childCare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ChildCare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(14.5f, 10.5f, -1.25f, 0.0f);
        pathBuilderB.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilderB.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(9.5f, 10.5f, -1.25f, 0.0f);
        pathBuilderB2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilderB2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.1f, 14.0f, 7.9f, 14.0f);
        pathBuilderJ.curveToRelative(-0.19f, 0.0f, -0.32f, 0.2f, -0.23f, 0.37f);
        pathBuilderJ.curveTo(8.5f, 15.94f, 10.13f, 17.0f, 12.0f, 17.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.5f, -1.06f, 4.33f, -2.63f);
        pathBuilderJ.curveToRelative(0.08f, -0.17f, -0.05f, -0.37f, -0.23f, -0.37f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(22.94f, 11.34f);
        pathBuilderJ.curveToRelative(-0.25f, -1.51f, -1.36f, -2.74f, -2.81f, -3.17f);
        pathBuilderJ.curveToRelative(-0.53f, -1.12f, -1.28f, -2.1f, -2.19f, -2.91f);
        pathBuilderJ.curveTo(16.36f, 3.85f, 14.28f, 3.0f, 12.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-4.36f, 0.85f, -5.94f, 2.26f);
        pathBuilderJ.curveToRelative(-0.92f, 0.81f, -1.67f, 1.8f, -2.19f, 2.91f);
        pathBuilderJ.curveToRelative(-1.45f, 0.43f, -2.56f, 1.65f, -2.81f, 3.17f);
        pathBuilderJ.curveToRelative(-0.04f, 0.21f, -0.06f, 0.43f, -0.06f, 0.66f);
        pathBuilderJ.curveToRelative(0.0f, 0.23f, 0.02f, 0.45f, 0.06f, 0.66f);
        pathBuilderJ.curveToRelative(0.25f, 1.51f, 1.36f, 2.74f, 2.81f, 3.17f);
        pathBuilderJ.curveToRelative(0.52f, 1.11f, 1.27f, 2.09f, 2.17f, 2.89f);
        pathBuilderJ.curveTo(7.62f, 20.14f, 9.71f, 21.0f, 12.0f, 21.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.38f, -0.86f, 5.97f, -2.28f);
        pathBuilderJ.curveToRelative(0.9f, -0.8f, 1.65f, -1.79f, 2.17f, -2.89f);
        pathBuilderJ.curveToRelative(1.44f, -0.43f, 2.55f, -1.65f, 2.8f, -3.17f);
        pathBuilderJ.curveToRelative(0.04f, -0.21f, 0.06f, -0.43f, 0.06f, -0.66f);
        pathBuilderJ.curveToRelative(0.0f, -0.23f, -0.02f, -0.45f, -0.06f, -0.66f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.0f, 14.0f);
        pathBuilderJ.curveToRelative(-0.1f, 0.0f, -0.19f, -0.02f, -0.29f, -0.03f);
        pathBuilderJ.curveToRelative(-0.2f, 0.67f, -0.49f, 1.29f, -0.86f, 1.86f);
        pathBuilderJ.curveTo(16.6f, 17.74f, 14.45f, 19.0f, 12.0f, 19.0f);
        pathBuilderJ.reflectiveCurveToRelative(-4.6f, -1.26f, -5.85f, -3.17f);
        pathBuilderJ.curveToRelative(-0.37f, -0.57f, -0.66f, -1.19f, -0.86f, -1.86f);
        pathBuilderJ.curveToRelative(-0.1f, 0.01f, -0.19f, 0.03f, -0.29f, 0.03f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.curveToRelative(0.1f, 0.0f, 0.19f, 0.02f, 0.29f, 0.03f);
        pathBuilderJ.curveToRelative(0.2f, -0.67f, 0.49f, -1.29f, 0.86f, -1.86f);
        pathBuilderJ.curveTo(7.4f, 6.26f, 9.55f, 5.0f, 12.0f, 5.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.6f, 1.26f, 5.85f, 3.17f);
        pathBuilderJ.curveToRelative(0.37f, 0.57f, 0.66f, 1.19f, 0.86f, 1.86f);
        pathBuilderJ.curveToRelative(0.1f, -0.01f, 0.19f, -0.03f, 0.29f, -0.03f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -0.9f, 2.0f, -2.0f, 2.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _childCare = imageVectorBuild;
        return imageVectorBuild;
    }
}

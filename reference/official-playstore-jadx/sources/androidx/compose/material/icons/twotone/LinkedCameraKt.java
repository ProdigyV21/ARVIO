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
import v.b;
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_linkedCamera", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LinkedCamera", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLinkedCamera", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LinkedCameraKt {
    private static ImageVector _linkedCamera;

    public static final ImageVector getLinkedCamera(Icons.TwoTone twoTone) {
        ImageVector imageVector = _linkedCamera;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LinkedCamera", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 20.0f, 4.0f, 20.0f);
        pathBuilderJ.lineTo(4.0f, 8.0f);
        pathBuilderJ.horizontalLineToRelative(4.05f);
        pathBuilderJ.lineToRelative(1.83f, -2.0f);
        pathBuilderJ.lineTo(15.0f, 6.0f);
        pathBuilderJ.lineTo(15.0f, 4.0f);
        pathBuilderJ.lineTo(9.0f, 4.0f);
        pathBuilderJ.lineTo(7.17f, 6.0f);
        pathBuilderJ.lineTo(4.0f, 6.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        b.g(pathBuilderJ, 22.0f, 9.0f, -2.0f, 11.0f);
        pathBuilderJ.moveTo(16.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(1.33f);
        pathBuilderJ.curveToRelative(2.58f, 0.0f, 4.66f, 2.09f, 4.67f, 4.66f);
        pathBuilderJ.lineTo(22.0f, 7.99f);
        pathBuilderJ.curveTo(21.99f, 4.68f, 19.31f, 2.0f, 16.0f, 2.0f);
        a.p(pathBuilderJ, 16.0f, 4.67f, 16.0f, 6.0f);
        pathBuilderJ.curveToRelative(1.11f, 0.0f, 1.99f, 0.89f, 2.0f, 1.99f);
        pathBuilderJ.horizontalLineToRelative(1.33f);
        pathBuilderJ.curveToRelative(-0.01f, -1.84f, -1.49f, -3.32f, -3.33f, -3.32f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.98f, 10.01f);
        pathBuilderA.curveToRelative(-0.13f, -0.09f, -0.26f, -0.18f, -0.39f, -0.26f);
        pathBuilderA.curveToRelative(0.14f, 0.08f, 0.27f, 0.17f, 0.39f, 0.26f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, -0.11f, -0.71f, -0.28f, -1.01f);
        pathBuilderA.curveToRelative(-0.18f, -0.3f, -0.43f, -0.55f, -0.73f, -0.72f);
        pathBuilderA.curveTo(15.7f, 7.1f, 15.36f, 7.0f, 15.0f, 7.0f);
        pathBuilderA.lineTo(15.0f, 6.0f);
        pathBuilderA.lineTo(9.88f, 6.0f);
        pathBuilderA.lineTo(8.05f, 8.0f);
        a.h(pathBuilderA, 4.0f, 8.0f, 12.0f, 16.0f);
        f.n(pathBuilderA, 20.0f, 9.0f, -3.0f);
        pathBuilderA.moveTo(12.0f, 19.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.24f, 5.0f, -5.0f, 5.0f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 9.0f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 17.0f);
        pathBuilderA2.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA2, -1.35f, 3.0f, -3.0f, 3.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _linkedCamera = imageVectorBuild;
        return imageVectorBuild;
    }
}

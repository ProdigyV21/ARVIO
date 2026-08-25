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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shapeLine", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShapeLine", "Landroidx/compose/material/icons/Icons$TwoTone;", "getShapeLine", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShapeLineKt {
    private static ImageVector _shapeLine;

    public static final ImageVector getShapeLine(Icons.TwoTone twoTone) {
        ImageVector imageVector = _shapeLine;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ShapeLine", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(16.0f, 16.0f, 5.0f, 5.0f, -5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(6.0f, 6.0f, -3.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, 6.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 11.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(8.76f, 1.0f, 6.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(1.0f, 3.24f, 1.0f, 6.0f);
        pathBuilderA.reflectiveCurveTo(3.24f, 11.0f, 6.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 3.0f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 1.65f, -1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveTo(3.0f, 7.65f, 3.0f, 6.0f);
        pathBuilderA.curveTo(3.0f, 4.35f, 4.35f, 3.0f, 6.0f, 3.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(21.0f, 14.0f, -5.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.verticalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.verticalLineToRelative(-5.0f);
        pathBuilderA2.curveTo(23.0f, 14.9f, 22.1f, 14.0f, 21.0f, 14.0f);
        b.w(pathBuilderA2, 21.0f, 21.0f, -5.0f, -5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA2, 5.0f, 21.0f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(17.71f, 7.7f);
        pathBuilderA3.curveTo(18.11f, 7.89f, 18.54f, 8.0f, 19.0f, 8.0f);
        pathBuilderA3.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA3.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA3.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA3.curveToRelative(0.0f, 0.46f, 0.11f, 0.89f, 0.3f, 1.29f);
        pathBuilderA3.lineTo(6.29f, 16.3f);
        pathBuilderA3.curveTo(5.89f, 16.11f, 5.46f, 16.0f, 5.0f, 16.0f);
        pathBuilderA3.curveToRelative(-1.65f, 0.0f, -3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA3.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderA3.reflectiveCurveToRelative(3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA3.curveToRelative(0.0f, -0.46f, -0.11f, -0.89f, -0.3f, -1.29f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA3, 17.71f, 7.7f), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shapeLine = imageVectorBuild;
        return imageVectorBuild;
    }
}

package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_garage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Garage", "Landroidx/compose/material/icons/Icons$Filled;", "getGarage", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GarageKt {
    private static ImageVector _garage;

    public static final ImageVector getGarage(Icons.Filled filled) {
        ImageVector imageVector = _garage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Garage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(15.0f, 13.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(9.0f, 13.0f, -1.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(8.33f, 7.5f, -0.66f, 2.0f), 8.66f, 0.0f, -0.66f, -2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(19.0f, 17.69f);
        pathBuilderQ.curveToRelative(0.0f, 0.45f, -0.35f, 0.81f, -0.78f, 0.81f);
        pathBuilderQ.horizontalLineToRelative(-0.44f);
        pathBuilderQ.curveToRelative(-0.44f, 0.0f, -0.78f, -0.36f, -0.78f, -0.81f);
        a0.a.s(pathBuilderQ, 16.5f, 7.0f, 1.19f);
        pathBuilderQ.curveToRelative(0.0f, 0.45f, -0.35f, 0.81f, -0.78f, 0.81f);
        pathBuilderQ.horizontalLineTo(5.78f);
        pathBuilderQ.curveTo(5.35f, 18.5f, 5.0f, 18.14f, 5.0f, 17.69f);
        pathBuilderQ.verticalLineToRelative(-6.5f);
        pathBuilderQ.curveTo(5.82f, 8.72f, 6.34f, 7.16f, 6.56f, 6.5f);
        pathBuilderQ.curveToRelative(0.05f, -0.16f, 0.12f, -0.29f, 0.19f, -0.4f);
        pathBuilderQ.curveTo(6.77f, 6.08f, 6.78f, 6.06f, 6.8f, 6.04f);
        pathBuilderQ.curveTo(7.18f, 5.51f, 7.72f, 5.5f, 7.72f, 5.5f);
        pathBuilderQ.horizontalLineToRelative(8.56f);
        pathBuilderQ.curveToRelative(0.0f, 0.0f, 0.54f, 0.01f, 0.92f, 0.53f);
        pathBuilderQ.curveToRelative(0.02f, 0.03f, 0.03f, 0.05f, 0.05f, 0.07f);
        pathBuilderQ.curveToRelative(0.07f, 0.11f, 0.14f, 0.24f, 0.19f, 0.4f);
        pathBuilderQ.curveToRelative(0.22f, 0.66f, 0.74f, 2.23f, 1.56f, 4.69f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ, 17.69f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _garage = imageVectorBuild;
        return imageVectorBuild;
    }
}

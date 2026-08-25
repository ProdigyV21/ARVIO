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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_onDeviceTraining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "OnDeviceTraining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getOnDeviceTraining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OnDeviceTrainingKt {
    private static ImageVector _onDeviceTraining;

    public static final ImageVector getOnDeviceTraining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _onDeviceTraining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.OnDeviceTraining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(6.0f, 20.0f, 12.0f, 1.0f, -12.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(6.0f, 3.0f, 12.0f, 1.0f, -12.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(11.0f, 16.0f, 2.0f, 1.0f, -2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.74f, 0.4f, 1.38f, 1.0f, 1.72f);
        e.l(pathBuilderA, 0.78f, 2.0f, -0.78f);
        pathBuilderA.curveToRelative(0.6f, -0.35f, 1.0f, -0.98f, 1.0f, -1.72f);
        pathBuilderA.curveTo(14.0f, 11.9f, 13.1f, 11.0f, 12.0f, 11.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.0f, 1.01f, 6.0f, 1.0f);
        pathBuilderJ.curveTo(4.9f, 1.0f, 4.0f, 1.9f, 4.0f, 3.0f);
        pathBuilderJ.verticalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(3.0f);
        pathBuilderJ.curveTo(20.0f, 1.9f, 19.1f, 1.01f, 18.0f, 1.01f);
        c.C(pathBuilderJ, 18.0f, 21.0f, 6.0f, -1.0f);
        c.D(pathBuilderJ, 12.0f, 21.0f, 18.0f, 18.0f);
        w.a.l(pathBuilderJ, 6.0f, 6.0f, 12.0f, 18.0f);
        c.B(pathBuilderJ, 18.0f, 4.0f, 6.0f, 3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderJ, 12.0f, 4.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(16.01f, 15.95f);
        pathBuilderA2.curveTo(16.63f, 15.12f, 17.0f, 14.11f, 17.0f, 13.0f);
        pathBuilderA2.reflectiveCurveToRelative(-0.37f, -2.12f, -0.99f, -2.95f);
        pathBuilderA2.lineToRelative(-1.07f, 1.07f);
        pathBuilderA2.curveToRelative(0.35f, 0.54f, 0.56f, 1.19f, 0.56f, 1.88f);
        pathBuilderA2.reflectiveCurveToRelative(-0.21f, 1.34f, -0.56f, 1.88f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 16.01f, 15.95f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(9.06f, 14.88f);
        pathBuilderA3.curveTo(8.71f, 14.34f, 8.5f, 13.69f, 8.5f, 13.0f);
        pathBuilderA3.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderA3.verticalLineToRelative(1.25f);
        pathBuilderA3.lineToRelative(2.25f, -2.0f);
        pathBuilderA3.lineToRelative(-2.25f, -2.0f);
        pathBuilderA3.verticalLineTo(8.0f);
        pathBuilderA3.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA3.curveToRelative(0.0f, 1.11f, 0.37f, 2.12f, 0.99f, 2.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA3, 9.06f, 14.88f), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _onDeviceTraining = imageVectorBuild;
        return imageVectorBuild;
    }
}

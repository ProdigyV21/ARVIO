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
import v.c;
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_surroundSound", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SurroundSound", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSurroundSound", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurroundSoundKt {
    private static ImageVector _surroundSound;

    public static final ImageVector getSurroundSound(Icons.TwoTone twoTone) {
        ImageVector imageVector = _surroundSound;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SurroundSound", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 18.0f, 16.0f, 6.0f, 4.0f);
        f.r(pathBuilderS, 18.0f, 16.94f, 7.06f);
        pathBuilderS.curveTo(18.32f, 8.41f, 19.0f, 10.21f, 19.0f, 12.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.68f, 3.59f, -2.05f, 4.95f);
        pathBuilderS.lineToRelative(-1.23f, -1.23f);
        pathBuilderS.curveToRelative(1.02f, -1.03f, 1.53f, -2.37f, 1.53f, -3.72f);
        pathBuilderS.curveToRelative(0.0f, -1.35f, -0.52f, -2.69f, -1.54f, -3.71f);
        c.z(pathBuilderS, 16.94f, 7.06f, 12.0f, 8.5f);
        pathBuilderS.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderS.curveToRelative(0.0f, 1.93f, -1.57f, 3.5f, -3.5f, 3.5f);
        pathBuilderS.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderS.curveTo(8.5f, 10.07f, 10.07f, 8.5f, 12.0f, 8.5f);
        v.a.z(pathBuilderS, 7.05f, 7.05f, 1.23f, 1.23f);
        pathBuilderS.curveTo(7.27f, 9.31f, 6.75f, 10.65f, 6.75f, 12.0f);
        pathBuilderS.curveToRelative(0.0f, 1.35f, 0.52f, 2.69f, 1.54f, 3.71f);
        pathBuilderS.lineToRelative(-1.23f, 1.23f);
        pathBuilderS.curveTo(5.68f, 15.59f, 5.0f, 13.79f, 5.0f, 12.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderS, 5.68f, 8.41f, 7.05f, 7.05f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderQ, 20.0f, 18.0f, 4.0f, 6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 16.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.29f, 15.71f);
        pathBuilderA.curveTo(7.27f, 14.69f, 6.75f, 13.35f, 6.75f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.35f, 0.52f, -2.69f, 1.53f, -3.72f);
        pathBuilderA.lineTo(7.05f, 7.05f);
        pathBuilderA.curveTo(5.68f, 8.41f, 5.0f, 10.21f, 5.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.68f, 3.59f, 2.06f, 4.94f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 8.29f, 15.71f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 15.5f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA2.curveToRelative(0.0f, -1.93f, -1.57f, -3.5f, -3.5f, -3.5f);
        pathBuilderA2.curveToRelative(-1.93f, 0.0f, -3.5f, 1.57f, -3.5f, 3.5f);
        pathBuilderA2.curveTo(8.5f, 13.93f, 10.07f, 15.5f, 12.0f, 15.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 10.5f);
        pathBuilderA2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 11.17f, 10.5f, 12.0f, 10.5f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(15.72f, 15.72f, 1.23f, 1.23f);
        pathBuilderR.curveTo(18.32f, 15.59f, 19.0f, 13.79f, 19.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.68f, -3.59f, -2.06f, -4.94f);
        pathBuilderR.lineToRelative(-1.23f, 1.23f);
        pathBuilderR.curveToRelative(1.02f, 1.02f, 1.54f, 2.36f, 1.54f, 3.71f);
        pathBuilderR.curveTo(17.25f, 13.35f, 16.73f, 14.69f, 15.72f, 15.72f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _surroundSound = imageVectorBuild;
        return imageVectorBuild;
    }
}

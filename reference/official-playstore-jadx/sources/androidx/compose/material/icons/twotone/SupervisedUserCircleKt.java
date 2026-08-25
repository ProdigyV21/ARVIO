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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisedUserCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisedUserCircle", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSupervisedUserCircle", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisedUserCircleKt {
    private static ImageVector _supervisedUserCircle;

    public static final ImageVector getSupervisedUserCircle(Icons.TwoTone twoTone) {
        ImageVector imageVector = _supervisedUserCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SupervisedUserCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(9.5f, 10.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.5f, 17.21f);
        pathBuilderA.curveToRelative(0.0f, -1.88f, 2.98f, -2.7f, 4.5f, -2.7f);
        pathBuilderA.curveToRelative(0.88f, 0.0f, 2.24f, 0.27f, 3.24f, 0.87f);
        pathBuilderA.curveToRelative(0.48f, -1.02f, 0.75f, -2.16f, 0.75f, -3.37f);
        pathBuilderA.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-8.0f, 3.59f, -8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.23f, 0.29f, 2.39f, 0.78f, 3.43f);
        pathBuilderA.curveToRelative(1.34f, -0.98f, 3.43f, -1.43f, 4.73f, -1.43f);
        pathBuilderA.curveToRelative(0.44f, 0.0f, 0.97f, 0.05f, 1.53f, 0.16f);
        pathBuilderA.curveToRelative(-0.63f, 0.57f, -1.06f, 1.22f, -1.3f, 1.86f);
        pathBuilderA.curveToRelative(-0.08f, 0.0f, -0.15f, -0.01f, -0.23f, -0.01f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.98f, 0.57f, -3.66f, 1.11f);
        pathBuilderA.curveToRelative(1.37f, 1.65f, 3.39f, 2.73f, 5.66f, 2.86f);
        b.f(pathBuilderA, -2.78f, 16.0f, 9.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, -0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(-1.11f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.01f, -1.11f, 0.89f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.5f, 13.0f);
        pathBuilderA.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -1.35f, 3.0f, -3.0f, 3.0f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.5f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.65f, -1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.5f, 11.0f);
        pathBuilderA2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(16.0f, 13.0f);
        pathBuilderA2.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderA2.curveToRelative(-1.11f, 0.0f, -2.01f, 0.89f, -2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(11.99f, 2.01f);
        pathBuilderA2.curveToRelative(-5.52f, 0.0f, -10.0f, 4.48f, -10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.reflectiveCurveToRelative(-4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(5.84f, 17.12f);
        pathBuilderA2.curveToRelative(0.68f, -0.54f, 2.27f, -1.11f, 3.66f, -1.11f);
        pathBuilderA2.curveToRelative(0.07f, 0.0f, 0.15f, 0.01f, 0.23f, 0.01f);
        pathBuilderA2.curveToRelative(0.24f, -0.64f, 0.67f, -1.29f, 1.3f, -1.86f);
        pathBuilderA2.curveToRelative(-0.56f, -0.1f, -1.09f, -0.16f, -1.53f, -0.16f);
        pathBuilderA2.curveToRelative(-1.3f, 0.0f, -3.39f, 0.45f, -4.73f, 1.43f);
        pathBuilderA2.curveToRelative(-0.5f, -1.04f, -0.78f, -2.2f, -0.78f, -3.43f);
        pathBuilderA2.curveToRelative(0.0f, -4.41f, 3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.2f, -0.27f, 2.34f, -0.75f, 3.37f);
        pathBuilderA2.curveToRelative(-1.0f, -0.59f, -2.36f, -0.87f, -3.24f, -0.87f);
        pathBuilderA2.curveToRelative(-1.52f, 0.0f, -4.5f, 0.81f, -4.5f, 2.7f);
        pathBuilderA2.verticalLineToRelative(2.78f);
        pathBuilderA2.curveToRelative(-2.27f, -0.13f, -4.29f, -1.21f, -5.66f, -2.86f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _supervisedUserCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}

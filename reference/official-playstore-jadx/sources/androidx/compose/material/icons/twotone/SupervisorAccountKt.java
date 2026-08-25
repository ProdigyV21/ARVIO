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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisorAccount", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisorAccount", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSupervisorAccount", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisorAccountKt {
    private static ImageVector _supervisorAccount;

    public static final ImageVector getSupervisorAccount(Icons.TwoTone twoTone) {
        ImageVector imageVector = _supervisorAccount;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SupervisorAccount", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(9.0f, 8.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(4.77f, 17.0f, 4.28f);
        pathBuilderA.curveToRelative(0.01f, -0.06f, 0.12f, -0.58f, 0.29f, -0.99f);
        pathBuilderA.curveToRelative(-0.11f, 0.0f, -0.23f, -0.01f, -0.34f, -0.01f);
        pathBuilderA.curveToRelative(-1.53f, 0.0f, -3.25f, 0.5f, -4.23f, 1.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(9.0f, 12.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA2.reflectiveCurveTo(10.93f, 5.0f, 9.0f, 5.0f);
        pathBuilderA2.reflectiveCurveTo(5.5f, 6.57f, 5.5f, 8.5f);
        pathBuilderA2.reflectiveCurveTo(7.07f, 12.0f, 9.0f, 12.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveTo(9.83f, 10.0f, 9.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveTo(8.17f, 7.0f, 9.0f, 7.0f);
        a.p(pathBuilderA2, 9.05f, 17.0f, 4.77f, 17.0f);
        pathBuilderA2.curveToRelative(0.99f, -0.5f, 2.7f, -1.0f, 4.23f, -1.0f);
        pathBuilderA2.curveToRelative(0.11f, 0.0f, 0.23f, 0.01f, 0.34f, 0.01f);
        pathBuilderA2.curveToRelative(0.34f, -0.73f, 0.93f, -1.33f, 1.64f, -1.81f);
        pathBuilderA2.curveToRelative(-0.73f, -0.13f, -1.42f, -0.2f, -1.98f, -0.2f);
        pathBuilderA2.curveToRelative(-2.34f, 0.0f, -7.0f, 1.17f, -7.0f, 3.5f);
        b.A(pathBuilderA2, 2.0f, 19.0f, 7.0f, -1.5f);
        pathBuilderA2.curveToRelative(0.0f, -0.17f, 0.02f, -0.34f, 0.05f, -0.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(16.5f, 14.5f);
        pathBuilderA2.curveToRelative(-1.84f, 0.0f, -5.5f, 1.01f, -5.5f, 3.0f);
        b.A(pathBuilderA2, 11.0f, 19.0f, 11.0f, -1.5f);
        pathBuilderA2.curveToRelative(0.0f, -1.99f, -3.66f, -3.0f, -5.5f, -3.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(17.71f, 12.68f);
        pathBuilderA2.curveToRelative(0.76f, -0.43f, 1.29f, -1.24f, 1.29f, -2.18f);
        pathBuilderA2.curveTo(19.0f, 9.12f, 17.88f, 8.0f, 16.5f, 8.0f);
        pathBuilderA2.reflectiveCurveTo(14.0f, 9.12f, 14.0f, 10.5f);
        pathBuilderA2.curveToRelative(0.0f, 0.94f, 0.53f, 1.75f, 1.29f, 2.18f);
        pathBuilderA2.curveToRelative(0.36f, 0.2f, 0.77f, 0.32f, 1.21f, 0.32f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA2, 0.85f, -0.12f, 1.21f, -0.32f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _supervisorAccount = imageVectorBuild;
        return imageVectorBuild;
    }
}

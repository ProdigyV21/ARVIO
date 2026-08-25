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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_adminPanelSettings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AdminPanelSettings", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAdminPanelSettings", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AdminPanelSettingsKt {
    private static ImageVector _adminPanelSettings;

    public static final ImageVector getAdminPanelSettings(Icons.TwoTone twoTone) {
        ImageVector imageVector = _adminPanelSettings;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AdminPanelSettings", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.0f, 7.58f, -5.5f, -2.4f);
        pathBuilderR.lineTo(5.0f, 7.58f);
        pathBuilderR.verticalLineToRelative(3.6f);
        pathBuilderR.curveToRelative(0.0f, 3.5f, 2.33f, 6.74f, 5.5f, 7.74f);
        pathBuilderR.curveToRelative(0.25f, -0.08f, 0.49f, -0.2f, 0.73f, -0.3f);
        pathBuilderR.curveTo(11.08f, 18.11f, 11.0f, 17.56f, 11.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, -2.97f, 2.16f, -5.43f, 5.0f, -5.91f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderR, 7.58f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveTo(21.0f, 14.79f, 19.21f, 13.0f, 17.0f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 14.38f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.12f, 0.51f, 1.12f, 1.12f);
        pathBuilderA.reflectiveCurveToRelative(-0.51f, 1.12f, -1.12f, 1.12f);
        pathBuilderA.reflectiveCurveToRelative(-1.12f, -0.51f, -1.12f, -1.12f);
        pathBuilderA.reflectiveCurveTo(16.38f, 14.38f, 17.0f, 14.38f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 19.75f);
        pathBuilderA.curveToRelative(-0.93f, 0.0f, -1.74f, -0.46f, -2.24f, -1.17f);
        pathBuilderA.curveToRelative(0.05f, -0.72f, 1.51f, -1.08f, 2.24f, -1.08f);
        pathBuilderA.reflectiveCurveToRelative(2.19f, 0.36f, 2.24f, 1.08f);
        pathBuilderA.curveTo(18.74f, 19.29f, 17.93f, 19.75f, 17.0f, 19.75f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(17.0f, 15.5f, -1.12f, 0.0f);
        pathBuilderB.arcToRelative(1.12f, 1.12f, 0.0f, true, true, 2.24f, 0.0f);
        pathBuilderB.arcToRelative(1.12f, 1.12f, 0.0f, true, true, -2.24f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a0.a.f(18.0f, 11.09f, 6.27f, 10.5f, 3.0f);
        pathBuilderF.lineTo(3.0f, 6.27f);
        pathBuilderF.verticalLineToRelative(4.91f);
        pathBuilderF.curveToRelative(0.0f, 4.54f, 3.2f, 8.79f, 7.5f, 9.82f);
        pathBuilderF.curveToRelative(0.55f, -0.13f, 1.08f, -0.32f, 1.6f, -0.55f);
        pathBuilderF.curveTo(13.18f, 21.99f, 14.97f, 23.0f, 17.0f, 23.0f);
        pathBuilderF.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderF.curveTo(23.0f, 14.03f, 20.84f, 11.57f, 18.0f, 11.09f);
        pathBuilderF.close();
        pathBuilderF.moveTo(11.0f, 17.0f);
        pathBuilderF.curveToRelative(0.0f, 0.56f, 0.08f, 1.11f, 0.23f, 1.62f);
        pathBuilderF.curveToRelative(-0.24f, 0.11f, -0.48f, 0.22f, -0.73f, 0.3f);
        pathBuilderF.curveToRelative(-3.17f, -1.0f, -5.5f, -4.24f, -5.5f, -7.74f);
        pathBuilderF.verticalLineToRelative(-3.6f);
        pathBuilderF.lineToRelative(5.5f, -2.4f);
        pathBuilderF.lineToRelative(5.5f, 2.4f);
        pathBuilderF.verticalLineToRelative(3.51f);
        pathBuilderF.curveTo(13.16f, 11.57f, 11.0f, 14.03f, 11.0f, 17.0f);
        pathBuilderF.close();
        pathBuilderF.moveTo(17.0f, 21.0f);
        pathBuilderF.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderF.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderF.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderF.curveTo(21.0f, 19.21f, 19.21f, 21.0f, 17.0f, 21.0f);
        pathBuilderF.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderF.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(17.0f, 17.5f);
        pathBuilderA2.curveToRelative(-0.73f, 0.0f, -2.19f, 0.36f, -2.24f, 1.08f);
        pathBuilderA2.curveToRelative(0.5f, 0.71f, 1.32f, 1.17f, 2.24f, 1.17f);
        pathBuilderA2.reflectiveCurveToRelative(1.74f, -0.46f, 2.24f, -1.17f);
        pathBuilderA2.curveTo(19.19f, 17.86f, 17.73f, 17.5f, 17.0f, 17.5f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _adminPanelSettings = imageVectorBuild;
        return imageVectorBuild;
    }
}

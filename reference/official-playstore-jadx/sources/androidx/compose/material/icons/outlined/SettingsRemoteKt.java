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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsRemote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsRemote", "Landroidx/compose/material/icons/Icons$Outlined;", "getSettingsRemote", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsRemoteKt {
    private static ImageVector _settingsRemote;

    public static final ImageVector getSettingsRemote(Icons.Outlined outlined) {
        ImageVector imageVector = _settingsRemote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SettingsRemote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 9.0f, 9.0f, 9.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(6.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.lineTo(16.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.g(pathBuilderJ, 14.0f, 21.0f, -4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderJ, 10.0f, 11.0f, 4.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 13.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(7.05f, 6.05f, 1.41f, 1.41f);
        pathBuilderR.curveTo(9.37f, 6.56f, 10.62f, 6.0f, 12.0f, 6.0f);
        pathBuilderR.reflectiveCurveToRelative(2.63f, 0.56f, 3.54f, 1.46f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.curveTo(15.68f, 4.78f, 13.93f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.68f, 0.78f, -4.95f, 2.05f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 0.0f);
        pathBuilderR.curveTo(8.96f, 0.0f, 6.21f, 1.23f, 4.22f, 3.22f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.curveTo(7.26f, 3.01f, 9.51f, 2.0f, 12.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(4.74f, 1.01f, 6.36f, 2.64f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.curveTo(17.79f, 1.23f, 15.04f, 0.0f, 12.0f, 0.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsRemote = imageVectorBuild;
        return imageVectorBuild;
    }
}

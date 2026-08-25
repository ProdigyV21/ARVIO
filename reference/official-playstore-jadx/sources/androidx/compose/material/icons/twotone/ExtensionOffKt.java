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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_extensionOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExtensionOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getExtensionOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExtensionOffKt {
    private static ImageVector _extensionOff;

    public static final ImageVector getExtensionOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _extensionOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ExtensionOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(18.0f, 13.0f, 2.17f, 8.83f, 6.0f);
        pathBuilderS.horizontalLineTo(11.0f);
        pathBuilderS.verticalLineTo(4.0f);
        pathBuilderS.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderS.reflectiveCurveTo(12.0f, 3.72f, 12.0f, 4.0f);
        b.z(pathBuilderS, 2.0f, 6.0f, 6.0f, 2.0f);
        pathBuilderS.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderS.reflectiveCurveTo(20.28f, 13.0f, 20.0f, 13.0f);
        a.x(pathBuilderS, 18.0f, 8.0f, 12.5f);
        pathBuilderS.curveToRelative(0.0f, 1.5f, -0.83f, 3.57f, -3.0f, 4.37f);
        pathBuilderS.verticalLineTo(19.0f);
        pathBuilderS.horizontalLineToRelative(2.13f);
        pathBuilderS.curveToRelative(0.8f, -2.17f, 2.87f, -3.0f, 4.37f, -3.0f);
        pathBuilderS.curveToRelative(0.69f, 0.0f, 1.5f, 0.18f, 2.25f, 0.58f);
        pathBuilderS.lineToRelative(-6.33f, -6.33f);
        pathBuilderS.curveTo(7.82f, 11.0f, 8.0f, 11.81f, 8.0f, 12.5f);
        pathBuilderS.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(1.39f, 4.22f, 1.62f, 1.62f);
        pathBuilderR.curveToRelative(0.0f, 0.05f, -0.01f, 0.1f, -0.01f, 0.16f);
        pathBuilderR.verticalLineToRelative(3.8f);
        pathBuilderR.curveTo(5.7f, 9.8f, 6.0f, 11.96f, 6.0f, 12.5f);
        pathBuilderR.curveToRelative(0.0f, 0.54f, -0.29f, 2.7f, -3.0f, 2.7f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(3.8f);
        pathBuilderR.curveToRelative(0.0f, -2.71f, 2.16f, -3.0f, 2.7f, -3.0f);
        pathBuilderR.curveToRelative(0.54f, 0.0f, 2.7f, 0.29f, 2.7f, 3.0f);
        pathBuilderR.horizontalLineTo(18.0f);
        pathBuilderR.curveToRelative(0.06f, 0.0f, 0.11f, 0.0f, 0.16f, -0.01f);
        pathBuilderR.lineToRelative(1.61f, 1.61f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderR.moveTo(11.5f, 16.0f);
        pathBuilderR.curveToRelative(-1.5f, 0.0f, -3.57f, 0.83f, -4.37f, 3.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.verticalLineToRelative(-2.13f);
        pathBuilderR.curveToRelative(2.17f, -0.8f, 3.0f, -2.87f, 3.0f, -4.37f);
        pathBuilderR.curveToRelative(0.0f, -0.69f, -0.18f, -1.5f, -0.58f, -2.25f);
        pathBuilderR.lineToRelative(6.33f, 6.33f);
        pathBuilderR.curveTo(13.0f, 16.18f, 12.19f, 16.0f, 11.5f, 16.0f);
        a.z(pathBuilderR, 8.83f, 6.0f, -2.0f, -2.0f);
        pathBuilderR.horizontalLineTo(9.0f);
        pathBuilderR.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderR.reflectiveCurveTo(14.0f, 2.62f, 14.0f, 4.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderR.reflectiveCurveTo(21.38f, 15.0f, 20.0f, 15.0f);
        a0.a.l(pathBuilderR, 2.17f, -2.0f, -2.0f, 13.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderR.reflectiveCurveTo(20.28f, 12.0f, 20.0f, 12.0f);
        a0.a.C(pathBuilderR, -2.0f, 6.0f, -6.0f, 4.0f);
        pathBuilderR.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderR.reflectiveCurveTo(11.0f, 3.72f, 11.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderR, 2.0f, 8.83f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _extensionOff = imageVectorBuild;
        return imageVectorBuild;
    }
}

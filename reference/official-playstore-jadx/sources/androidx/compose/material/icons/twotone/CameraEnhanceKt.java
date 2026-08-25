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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cameraEnhance", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CameraEnhance", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCameraEnhance", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraEnhanceKt {
    private static ImageVector _cameraEnhance;

    public static final ImageVector getCameraEnhance(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cameraEnhance;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CameraEnhance", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.95f, 7.0f, -0.59f, -0.65f);
        pathBuilderR.lineTo(14.12f, 5.0f);
        pathBuilderR.horizontalLineTo(9.88f);
        pathBuilderR.lineTo(8.65f, 6.35f);
        e.h(pathBuilderR, -0.6f, 0.65f, 4.0f, 12.0f);
        pathBuilderR.horizontalLineToRelative(16.0f);
        pathBuilderR.verticalLineTo(7.0f);
        pathBuilderR.horizontalLineToRelative(-4.05f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 18.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderR.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderR.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, -2.24f, 5.0f, -5.0f, 5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(12.0f, 10.0f, -0.94f, 2.06f);
        pathBuilderR2.lineTo(9.0f, 13.0f);
        pathBuilderR2.lineToRelative(2.06f, 0.94f);
        pathBuilderR2.lineTo(12.0f, 16.0f);
        pathBuilderR2.lineToRelative(0.94f, -2.06f);
        d.l(pathBuilderR2, 15.0f, 13.0f, -2.06f, -0.94f);
        pathBuilderR2.moveTo(20.0f, 5.0f);
        pathBuilderR2.horizontalLineToRelative(-3.17f);
        pathBuilderR2.lineTo(15.0f, 3.0f);
        pathBuilderR2.lineTo(9.0f, 3.0f);
        pathBuilderR2.lineTo(7.17f, 5.0f);
        pathBuilderR2.lineTo(4.0f, 5.0f);
        pathBuilderR2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR2.verticalLineToRelative(12.0f);
        pathBuilderR2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR2.horizontalLineToRelative(16.0f);
        pathBuilderR2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR2.lineTo(22.0f, 7.0f);
        pathBuilderR2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderR2, 20.0f, 19.0f, 4.0f, 19.0f);
        pathBuilderR2.lineTo(4.0f, 7.0f);
        pathBuilderR2.horizontalLineToRelative(4.05f);
        pathBuilderR2.lineToRelative(0.59f, -0.65f);
        pathBuilderR2.lineTo(9.88f, 5.0f);
        pathBuilderR2.horizontalLineToRelative(4.24f);
        pathBuilderR2.lineToRelative(1.24f, 1.35f);
        pathBuilderR2.lineToRelative(0.59f, 0.65f);
        d.f(pathBuilderR2, 20.0f, 7.0f, 12.0f);
        pathBuilderR2.moveTo(12.0f, 8.0f);
        pathBuilderR2.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderR2.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderR2.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderR2.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderR2.close();
        pathBuilderR2.moveTo(12.0f, 16.0f);
        pathBuilderR2.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderR2.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderR2.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR2, -1.35f, 3.0f, -3.0f, 3.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cameraEnhance = imageVectorBuild;
        return imageVectorBuild;
    }
}

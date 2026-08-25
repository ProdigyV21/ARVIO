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
import v.b;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cameraFront", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CameraFront", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCameraFront", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraFrontKt {
    private static ImageVector _cameraFront;

    public static final ImageVector getCameraFront(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cameraFront;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CameraFront", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.c(7.0f, 14.0f, 10.0f, 2.0f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(5.0f, 20.0f, 2.0f, 5.0f, 2.0f);
        pathBuilderU.lineToRelative(3.0f, -3.0f);
        f.p(pathBuilderU, -3.0f, -3.0f, 2.0f);
        v.a.C(pathBuilderU, 14.0f, 20.0f, 5.0f, 2.0f);
        f.x(pathBuilderU, -5.0f, 11.99f, 8.0f);
        pathBuilderU.curveTo(13.1f, 8.0f, 14.0f, 7.1f, 14.0f, 6.0f);
        pathBuilderU.reflectiveCurveToRelative(-0.9f, -2.0f, -2.01f, -2.0f);
        pathBuilderU.reflectiveCurveTo(10.0f, 4.9f, 10.0f, 6.0f);
        pathBuilderU.reflectiveCurveToRelative(0.89f, 2.0f, 1.99f, 2.0f);
        v.a.p(pathBuilderU, 17.0f, 0.0f, 7.0f, 0.0f);
        pathBuilderU.curveTo(5.9f, 0.0f, 5.0f, 0.9f, 5.0f, 2.0f);
        pathBuilderU.verticalLineToRelative(14.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(10.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.lineTo(19.0f, 2.0f);
        pathBuilderU.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        v.a.p(pathBuilderU, 17.0f, 16.0f, 7.0f, 16.0f);
        b.l(pathBuilderU, -2.0f, 10.0f, 2.0f);
        pathBuilderU.moveTo(17.0f, 12.5f);
        pathBuilderU.curveToRelative(0.0f, -1.67f, -3.33f, -2.5f, -5.0f, -2.5f);
        pathBuilderU.reflectiveCurveToRelative(-5.0f, 0.83f, -5.0f, 2.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderU, 7.0f, 2.0f, 10.0f, 10.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cameraFront = imageVectorBuild;
        return imageVectorBuild;
    }
}

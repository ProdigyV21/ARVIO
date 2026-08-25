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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cameraFront", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CameraFront", "Landroidx/compose/material/icons/Icons$Filled;", "getCameraFront", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraFrontKt {
    private static ImageVector _cameraFront;

    public static final ImageVector getCameraFront(Icons.Filled filled) {
        ImageVector imageVector = _cameraFront;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.CameraFront", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(10.0f, 20.0f, 5.0f, 20.0f, 2.0f);
        f.y(pathBuilderS, 5.0f, 2.0f, 3.0f, -3.0f);
        f.p(pathBuilderS, -3.0f, -3.0f, 2.0f);
        c.m(pathBuilderS, 14.0f, 20.0f, 2.0f, 5.0f);
        e.D(pathBuilderS, -2.0f, -5.0f, 12.0f, 8.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderS.reflectiveCurveTo(10.9f, 8.0f, 12.0f, 8.0f);
        a.p(pathBuilderS, 17.0f, 0.0f, 7.0f, 0.0f);
        pathBuilderS.curveTo(5.9f, 0.0f, 5.0f, 0.9f, 5.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.horizontalLineToRelative(10.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderS.lineTo(19.0f, 2.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        b.w(pathBuilderS, 7.0f, 2.0f, 10.0f, 10.5f);
        pathBuilderS.curveToRelative(0.0f, -1.67f, -3.33f, -2.5f, -5.0f, -2.5f);
        pathBuilderS.reflectiveCurveToRelative(-5.0f, 0.83f, -5.0f, 2.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 7.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cameraFront = imageVectorBuild;
        return imageVectorBuild;
    }
}

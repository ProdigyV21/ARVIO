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
import v.c;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pinEnd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PinEnd", "Landroidx/compose/material/icons/Icons$Outlined;", "getPinEnd", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinEndKt {
    private static ImageVector _pinEnd;

    public static final ImageVector getPinEnd(Icons.Outlined outlined) {
        ImageVector imageVector = _pinEnd;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PinEnd", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 12.0f, 6.0f, 4.0f, 12.0f);
        a0.a.n(pathBuilderJ, 10.0f, 0.0f, 2.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        e.y(pathBuilderJ, 6.0f, 20.0f, 19.0f, 14.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveTo(20.66f, 14.0f, 19.0f, 14.0f);
        c.C(pathBuilderJ, 14.66f, 8.0f, 9.0f, 5.66f);
        f.y(pathBuilderJ, 2.0f, -2.24f, 2.95f, 2.95f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.lineTo(12.41f, 10.0f);
        pathBuilderJ.horizontalLineToRelative(2.24f);
        pathBuilderJ.verticalLineTo(8.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pinEnd = imageVectorBuild;
        return imageVectorBuild;
    }
}

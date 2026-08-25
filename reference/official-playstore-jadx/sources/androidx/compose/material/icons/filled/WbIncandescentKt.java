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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbIncandescent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbIncandescent", "Landroidx/compose/material/icons/Icons$Filled;", "getWbIncandescent", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbIncandescentKt {
    private static ImageVector _wbIncandescent;

    public static final ImageVector getWbIncandescent(Icons.Filled filled) {
        ImageVector imageVector = _wbIncandescent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.WbIncandescent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(3.55f, 18.54f, 1.41f, 1.41f);
        pathBuilderR.lineToRelative(1.79f, -1.8f);
        b.D(pathBuilderR, -1.41f, -1.41f, -1.79f, 1.8f);
        pathBuilderR.moveTo(11.0f, 22.45f);
        c.A(pathBuilderR, 2.0f, 13.0f, 19.5f, -2.0f);
        b.f(pathBuilderR, 2.95f, 4.0f, 10.5f);
        a.h(pathBuilderR, 1.0f, 10.5f, 2.0f, 3.0f);
        b.f(pathBuilderR, -2.0f, 15.0f, 6.31f);
        pathBuilderR.lineTo(15.0f, 1.5f);
        pathBuilderR.lineTo(9.0f, 1.5f);
        pathBuilderR.verticalLineToRelative(4.81f);
        pathBuilderR.curveTo(7.21f, 7.35f, 6.0f, 9.28f, 6.0f, 11.5f);
        pathBuilderR.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderR.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderR.curveToRelative(0.0f, -2.22f, -1.21f, -4.15f, -3.0f, -5.19f);
        c.y(pathBuilderR, 20.0f, 10.5f, 2.0f, 3.0f);
        e.D(pathBuilderR, -2.0f, -3.0f, 17.24f, 18.16f);
        pathBuilderR.lineToRelative(1.79f, 1.8f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -1.8f, -1.79f, -1.4f, 1.4f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbIncandescent = imageVectorBuild;
        return imageVectorBuild;
    }
}

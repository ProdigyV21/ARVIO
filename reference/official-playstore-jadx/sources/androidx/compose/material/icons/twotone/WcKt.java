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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wc", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wc", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWc", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WcKt {
    private static ImageVector _wc;

    public static final ImageVector getWc(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wc;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Wc", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(5.5f, 22.0f, -7.5f, 4.0f, 14.5f);
        pathBuilderS.lineTo(4.0f, 9.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderS.horizontalLineToRelative(3.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(5.5f);
        pathBuilderS.lineTo(9.5f, 14.5f);
        f.n(pathBuilderS, 9.5f, 22.0f, -4.0f);
        c.m(pathBuilderS, 18.0f, 22.0f, -6.0f, 3.0f);
        pathBuilderS.lineToRelative(-2.54f, -7.63f);
        pathBuilderS.curveTo(18.18f, 7.55f, 17.42f, 7.0f, 16.56f, 7.0f);
        pathBuilderS.horizontalLineToRelative(-0.12f);
        pathBuilderS.curveToRelative(-0.86f, 0.0f, -1.63f, 0.55f, -1.9f, 1.37f);
        b.A(pathBuilderS, 12.0f, 16.0f, 3.0f, 6.0f);
        f.x(pathBuilderS, 3.0f, 7.5f, 6.0f);
        pathBuilderS.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderS.reflectiveCurveToRelative(0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(16.5f, 6.0f);
        pathBuilderS.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.0f, 0.89f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderS, 0.89f, 2.0f, 2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wc = imageVectorBuild;
        return imageVectorBuild;
    }
}

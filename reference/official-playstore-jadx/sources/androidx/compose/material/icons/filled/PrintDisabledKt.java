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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_printDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PrintDisabled", "Landroidx/compose/material/icons/Icons$Filled;", "getPrintDisabled", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrintDisabledKt {
    private static ImageVector _printDisabled;

    public static final ImageVector getPrintDisabled(Icons.Filled filled) {
        ImageVector imageVector = _printDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PrintDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(19.1f, 17.0f, 22.0f, 17.0f, -6.0f);
        pathBuilderS.curveToRelative(0.0f, -1.7f, -1.3f, -3.0f, -3.0f, -3.0f);
        c.v(pathBuilderS, -9.0f, 9.1f, 9.0f);
        pathBuilderS.moveTo(19.0f, 10.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.p(pathBuilderS, 18.0f, 7.0f, 18.0f, 3.0f);
        pathBuilderS.lineTo(6.0f, 3.0f);
        y.a.n(pathBuilderS, 1.1f, 9.0f, 7.0f);
        pathBuilderS.moveTo(1.2f, 1.8f);
        pathBuilderS.lineTo(0.0f, 3.0f);
        pathBuilderS.lineToRelative(4.9f, 5.0f);
        pathBuilderS.curveTo(3.3f, 8.1f, 2.0f, 9.4f, 2.0f, 11.0f);
        b.z(pathBuilderS, 6.0f, 4.0f, 4.0f, 11.9f);
        pathBuilderS.lineToRelative(3.0f, 3.0f);
        b.D(pathBuilderS, 1.3f, -1.3f, -21.0f, -20.9f);
        c.m(pathBuilderS, 8.0f, 19.0f, -5.0f, 2.9f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderS, 5.0f, 5.0f, 8.0f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _printDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}

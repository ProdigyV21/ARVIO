package androidx.compose.material.icons.sharp;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_printDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PrintDisabled", "Landroidx/compose/material/icons/Icons$Sharp;", "getPrintDisabled", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrintDisabledKt {
    private static ImageVector _printDisabled;

    public static final ImageVector getPrintDisabled(Icons.Sharp sharp) {
        ImageVector imageVector = _printDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PrintDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(9.65f, 7.0f, 18.0f, 7.0f);
        pathBuilderJ.lineTo(18.0f, 3.01f);
        d.f(pathBuilderJ, 6.0f, 3.01f, 0.35f);
        pathBuilderJ.moveTo(10.66f, 8.01f);
        pathBuilderJ.lineToRelative(9.0f, 8.99f);
        pathBuilderJ.lineTo(22.0f, 17.0f);
        pathBuilderJ.verticalLineToRelative(-5.99f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        f.x(pathBuilderJ, -8.34f, 19.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.p(pathBuilderJ, 1.41f, 1.6f, 0.0f, 3.01f);
        pathBuilderJ.lineToRelative(5.0f, 5.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, 1.33f, -3.0f, 2.99f);
        b.z(pathBuilderJ, 6.0f, 4.0f, 4.0f, 12.0f);
        pathBuilderJ.lineToRelative(2.95f, 2.96f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 1.41f, 1.6f);
        pathBuilderJ.moveTo(8.0f, 19.01f);
        pathBuilderJ.lineTo(8.0f, 15.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 4.0f, 4.0f, -8.0f, 0.01f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _printDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}

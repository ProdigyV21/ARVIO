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
import v.b;
import v.c;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smartToy", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmartToy", "Landroidx/compose/material/icons/Icons$Sharp;", "getSmartToy", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmartToyKt {
    private static ImageVector _smartToy;

    public static final ImageVector getSmartToy(Icons.Sharp sharp) {
        ImageVector imageVector = _smartToy;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SmartToy", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(20.0f, 9.0f, 5.0f, -5.0f, 2.0f);
        w.a.A(pathBuilderM, 9.0f, 3.0f, 4.0f, 4.0f);
        b.q(pathBuilderM, 1.0f, 6.0f, 3.0f, 6.0f);
        a0.a.j(pathBuilderM, 16.0f, -6.0f, 3.0f, 9.0f);
        v.a.x(pathBuilderM, 20.0f, 7.5f, 11.5f);
        pathBuilderM.curveTo(7.5f, 10.67f, 8.17f, 10.0f, 9.0f, 10.0f);
        pathBuilderM.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderM.reflectiveCurveTo(9.83f, 13.0f, 9.0f, 13.0f);
        pathBuilderM.reflectiveCurveTo(7.5f, 12.33f, 7.5f, 11.5f);
        c.C(pathBuilderM, 16.0f, 17.0f, 8.0f, -2.0f);
        c.D(pathBuilderM, 8.0f, 17.0f, 15.0f, 13.0f);
        pathBuilderM.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderM.reflectiveCurveTo(14.17f, 10.0f, 15.0f, 10.0f);
        pathBuilderM.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderM, 15.83f, 13.0f, 15.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smartToy = imageVectorBuild;
        return imageVectorBuild;
    }
}

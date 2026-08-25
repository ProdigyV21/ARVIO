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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrOnSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrOnSelect", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHdrOnSelect", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrOnSelectKt {
    private static ImageVector _hdrOnSelect;

    public static final ImageVector getHdrOnSelect(Icons.TwoTone twoTone) {
        ImageVector imageVector = _hdrOnSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HdrOnSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(18.0f, 18.5f, -1.0f);
        pathBuilderI.curveToRelative(0.0f, -0.8f, -0.7f, -1.5f, -1.5f, -1.5f);
        b.q(pathBuilderI, 13.0f, 6.0f, 1.5f, -2.0f);
        a0.a.n(pathBuilderI, 1.1f, 0.9f, 2.0f, 18.0f);
        pathBuilderI.lineToRelative(-0.9f, -2.1f);
        pathBuilderI.curveTo(17.6f, 19.6f, 18.0f, 19.1f, 18.0f, 18.5f);
        b.w(pathBuilderI, 16.5f, 18.5f, -2.0f, -1.0f);
        c.D(pathBuilderI, 2.0f, 18.5f, 3.5f, 18.0f);
        a0.a.y(pathBuilderI, -2.0f, -2.0f, 0.0f, 6.0f);
        a0.a.j(pathBuilderI, 1.5f, -2.5f, 2.0f, 22.0f);
        pathBuilderI.horizontalLineTo(5.0f);
        pathBuilderI.verticalLineToRelative(-6.0f);
        pathBuilderI.horizontalLineTo(3.5f);
        pathBuilderI.verticalLineTo(18.0f);
        pathBuilderI.close();
        a.A(pathBuilderI, 10.0f, 16.0f, 6.5f, 6.0f);
        pathBuilderI.horizontalLineTo(10.0f);
        pathBuilderI.curveToRelative(0.8f, 0.0f, 1.5f, -0.7f, 1.5f, -1.5f);
        pathBuilderI.verticalLineToRelative(-3.0f);
        pathBuilderI.curveTo(11.5f, 16.7f, 10.8f, 16.0f, 10.0f, 16.0f);
        c.C(pathBuilderI, 10.0f, 20.5f, 8.0f, -3.0f);
        c.D(pathBuilderI, 2.0f, 20.5f, 24.0f, 20.0f);
        a.o(pathBuilderI, -2.0f, 2.0f, -1.5f, -2.0f);
        a.o(pathBuilderI, -2.0f, -1.5f, 2.0f, -2.0f);
        c.k(pathBuilderI, 22.0f, 2.0f, 2.0f, 20.0f);
        pathBuilderI.moveTo(12.0f, 4.0f);
        pathBuilderI.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderI.reflectiveCurveToRelative(-4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderI.reflectiveCurveTo(9.79f, 4.0f, 12.0f, 4.0f);
        pathBuilderI.moveTo(12.0f, 2.0f);
        pathBuilderI.curveTo(8.69f, 2.0f, 6.0f, 4.69f, 6.0f, 8.0f);
        pathBuilderI.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderI.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderI, 15.31f, 2.0f, 12.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrOnSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}

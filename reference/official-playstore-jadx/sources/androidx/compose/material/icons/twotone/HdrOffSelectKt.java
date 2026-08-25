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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrOffSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrOffSelect", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHdrOffSelect", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrOffSelectKt {
    private static ImageVector _hdrOffSelect;

    public static final ImageVector getHdrOffSelect(Icons.TwoTone twoTone) {
        ImageVector imageVector = _hdrOffSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HdrOffSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        pathBuilderI.moveTo(10.98f, 4.15f);
        pathBuilderI.lineTo(9.42f, 2.59f);
        pathBuilderI.curveToRelative(5.1f, -2.42f, 10.41f, 2.89f, 7.99f, 7.99f);
        pathBuilderI.lineToRelative(-1.56f, -1.56f);
        pathBuilderI.curveTo(16.66f, 6.06f, 13.94f, 3.34f, 10.98f, 4.15f);
        a.p(pathBuilderI, 6.34f, 2.34f, 4.93f, 3.76f);
        pathBuilderI.lineToRelative(1.66f, 1.66f);
        pathBuilderI.curveToRelative(-2.42f, 5.1f, 2.89f, 10.41f, 7.99f, 7.99f);
        pathBuilderI.lineToRelative(1.66f, 1.66f);
        d.C(pathBuilderI, 1.41f, -1.41f, 6.34f, 2.34f);
        pathBuilderI.moveTo(8.15f, 6.98f);
        pathBuilderI.lineToRelative(4.87f, 4.87f);
        pathBuilderI.curveTo(10.06f, 12.66f, 7.34f, 9.94f, 8.15f, 6.98f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrOffSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}

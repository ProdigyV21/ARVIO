package androidx.compose.material.icons.filled;

import a0.b;
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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fiberDvr", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FiberDvr", "Landroidx/compose/material/icons/Icons$Filled;", "getFiberDvr", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FiberDvrKt {
    private static ImageVector _fiberDvr;

    public static final ImageVector getFiberDvr(Icons.Filled filled) {
        ImageVector imageVector = _fiberDvr;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FiberDvr", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(17.5f, 10.5f, 2.0f, 1.0f, -2.0f);
        a.D(pathBuilderK, 10.5f, 4.5f, 10.5f, 2.0f);
        a.t(pathBuilderK, 3.0f, -2.0f, 10.5f);
        pathBuilderK.moveTo(21.0f, 3.0f);
        pathBuilderK.horizontalLineTo(3.0f);
        pathBuilderK.curveTo(1.89f, 3.0f, 1.0f, 3.89f, 1.0f, 5.0f);
        pathBuilderK.verticalLineToRelative(14.0f);
        pathBuilderK.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderK.horizontalLineToRelative(18.0f);
        pathBuilderK.curveToRelative(1.11f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderK.verticalLineTo(5.0f);
        pathBuilderK.curveTo(23.0f, 3.89f, 22.11f, 3.0f, 21.0f, 3.0f);
        pathBuilderK.close();
        pathBuilderK.moveTo(8.0f, 13.5f);
        pathBuilderK.curveTo(8.0f, 14.35f, 7.35f, 15.0f, 6.5f, 15.0f);
        b.u(pathBuilderK, 3.0f, 9.0f, 3.5f);
        pathBuilderK.curveTo(7.35f, 9.0f, 8.0f, 9.65f, 8.0f, 10.5f);
        a.D(pathBuilderK, 13.5f, 12.62f, 15.0f, -1.5f);
        pathBuilderK.lineTo(9.37f, 9.0f);
        pathBuilderK.horizontalLineToRelative(1.5f);
        pathBuilderK.lineToRelative(1.0f, 3.43f);
        pathBuilderK.lineToRelative(1.0f, -3.43f);
        d.q(pathBuilderK, 1.5f, 12.62f, 15.0f);
        pathBuilderK.moveTo(21.0f, 11.5f);
        pathBuilderK.curveToRelative(0.0f, 0.6f, -0.4f, 1.15f, -0.9f, 1.4f);
        pathBuilderK.lineTo(21.0f, 15.0f);
        a0.a.n(pathBuilderK, -1.5f, -0.85f, -2.0f, 17.5f);
        e.A(pathBuilderK, 2.0f, 16.0f, 9.0f, 3.5f);
        pathBuilderK.curveToRelative(0.85f, 0.0f, 1.5f, 0.65f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderK, 11.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fiberDvr = imageVectorBuild;
        return imageVectorBuild;
    }
}

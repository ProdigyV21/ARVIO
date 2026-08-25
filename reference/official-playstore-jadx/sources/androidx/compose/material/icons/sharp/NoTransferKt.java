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
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noTransfer", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoTransfer", "Landroidx/compose/material/icons/Icons$Sharp;", "getNoTransfer", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoTransferKt {
    private static ImageVector _noTransfer;

    public static final ImageVector getNoTransfer(Icons.Sharp sharp) {
        ImageVector imageVector = _noTransfer;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NoTransfer", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.78f, 2.95f);
        pathBuilderA.curveTo(7.24f, 2.16f, 9.48f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(4.42f, 0.0f, 8.0f, 0.5f, 8.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 0.35f, -0.08f, 0.67f, -0.19f, 0.98f);
        b.p(pathBuilderA, 13.83f, 11.0f, 18.0f, 6.0f);
        a.w(pathBuilderA, 8.83f, 5.78f, 2.95f);
        pathBuilderA.moveTo(19.78f, 22.61f);
        b.m(pathBuilderA, 18.17f, 21.0f, 16.0f, -2.0f);
        a.A(pathBuilderA, 8.0f, 2.0f, 5.0f, -2.78f);
        pathBuilderA.curveTo(4.39f, 17.67f, 4.0f, 16.88f, 4.0f, 16.0f);
        pathBuilderA.verticalLineTo(6.83f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderA, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderA.moveTo(9.0f, 15.5f);
        pathBuilderA.curveTo(9.0f, 14.67f, 8.33f, 14.0f, 7.5f, 14.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 14.67f, 6.0f, 15.5f);
        pathBuilderA.curveTo(6.0f, 16.33f, 6.67f, 17.0f, 7.5f, 17.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 16.33f, 9.0f, 15.5f);
        v.a.p(pathBuilderA, 8.17f, 11.0f, 6.0f, 8.83f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderA, 11.0f, 8.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noTransfer = imageVectorBuild;
        return imageVectorBuild;
    }
}

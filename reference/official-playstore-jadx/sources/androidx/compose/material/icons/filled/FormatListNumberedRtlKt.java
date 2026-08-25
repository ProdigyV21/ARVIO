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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatListNumberedRtl", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatListNumberedRtl", "Landroidx/compose/material/icons/Icons$Filled;", "getFormatListNumberedRtl", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatListNumberedRtlKt {
    private static ImageVector _formatListNumberedRtl;

    public static final ImageVector getFormatListNumberedRtl(Icons.Filled filled) {
        ImageVector imageVector = _formatListNumberedRtl;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FormatListNumberedRtl", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(18.0f, 17.0f, 2.0f, 0.5f, -1.0f);
        b.z(pathBuilderK, 1.0f, 1.0f, 0.5f, -2.0f);
        a0.a.B(pathBuilderK, 1.0f, 3.0f, -4.0f, -3.0f);
        pathBuilderK.moveTo(19.0f, 8.0f);
        c.A(pathBuilderK, 1.0f, 20.0f, 4.0f, -2.0f);
        e.D(pathBuilderK, 1.0f, 1.0f, 18.0f, 11.0f);
        pathBuilderK.horizontalLineToRelative(1.8f);
        pathBuilderK.lineTo(18.0f, 13.1f);
        pathBuilderK.verticalLineToRelative(0.9f);
        e.g(pathBuilderK, 3.0f, -1.0f, -1.8f);
        pathBuilderK.lineToRelative(1.8f, -2.1f);
        f.n(pathBuilderK, 21.0f, 10.0f, -3.0f);
        a.C(pathBuilderK, 2.0f, 5.0f, 14.0f, 2.0f);
        c.z(pathBuilderK, 2.0f, 7.0f, 2.0f, 17.0f);
        d.m(pathBuilderK, 14.0f, 2.0f, 2.0f, 19.0f);
        a.C(pathBuilderK, 2.0f, 11.0f, 14.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderK, 2.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatListNumberedRtl = imageVectorBuild;
        return imageVectorBuild;
    }
}

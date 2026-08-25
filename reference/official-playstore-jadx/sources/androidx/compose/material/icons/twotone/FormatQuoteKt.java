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
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatQuote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatQuote", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFormatQuote", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatQuoteKt {
    private static ImageVector _formatQuote;

    public static final ImageVector getFormatQuote(Icons.TwoTone twoTone) {
        ImageVector imageVector = _formatQuote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FormatQuote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(16.62f, 16.0f, 0.76f, 19.0f, 12.76f);
        b.A(pathBuilderI, 19.0f, 8.0f, -4.0f, 4.0f);
        e.r(pathBuilderI, 3.62f, 6.62f, 16.0f, 0.76f);
        pathBuilderI.lineTo(9.0f, 12.76f);
        pathBuilderI.lineTo(9.0f, 8.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.j(pathBuilderI, 5.0f, 8.0f, 4.0f, 3.62f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(18.62f, 18.0f, 21.0f, 13.24f, 6.0f);
        e.g(pathBuilderN, -8.0f, 8.0f, 2.38f);
        y.a.k(pathBuilderN, -2.0f, 4.0f, 5.24f);
        e.o(pathBuilderN, 15.0f, 12.0f, 8.0f, 4.0f);
        a0.b.k(pathBuilderN, 4.76f, 17.38f, 16.0f, -0.76f);
        f.v(pathBuilderN, 2.0f, -4.0f, 15.0f);
        pathBuilderN.moveTo(3.38f, 18.0f);
        a0.b.x(pathBuilderN, 5.24f, 11.0f, 13.24f, 6.0f);
        v.a.n(pathBuilderN, 3.0f, 8.0f, 2.38f);
        b.C(pathBuilderN, -2.0f, 4.0f, 5.0f, 12.0f);
        a.j(pathBuilderN, 8.0f, 4.0f, 4.76f);
        pathBuilderN.lineTo(7.38f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderN, -0.76f, 2.0f, -4.0f, 5.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatQuote = imageVectorBuild;
        return imageVectorBuild;
    }
}

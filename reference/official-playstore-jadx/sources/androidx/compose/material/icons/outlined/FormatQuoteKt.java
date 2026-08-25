package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatQuote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatQuote", "Landroidx/compose/material/icons/Icons$Outlined;", "getFormatQuote", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatQuoteKt {
    private static ImageVector _formatQuote;

    public static final ImageVector getFormatQuote(Icons.Outlined outlined) {
        ImageVector imageVector = _formatQuote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FormatQuote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(18.62f, 18.0f, -5.24f, 2.0f, -4.0f);
        pathBuilderG.lineTo(13.0f, 14.0f);
        b.A(pathBuilderG, 13.0f, 6.0f, 8.0f, 7.24f);
        c.z(pathBuilderG, 18.62f, 18.0f, 16.62f, 16.0f);
        pathBuilderG.horizontalLineToRelative(0.76f);
        pathBuilderG.lineTo(19.0f, 12.76f);
        b.A(pathBuilderG, 19.0f, 8.0f, -4.0f, 4.0f);
        c.v(pathBuilderG, 3.62f, -2.0f, 4.0f);
        pathBuilderG.moveTo(8.62f, 18.0f);
        pathBuilderG.lineTo(3.38f, 18.0f);
        pathBuilderG.lineToRelative(2.0f, -4.0f);
        pathBuilderG.lineTo(3.0f, 14.0f);
        b.A(pathBuilderG, 3.0f, 6.0f, 8.0f, 7.24f);
        c.z(pathBuilderG, 8.62f, 18.0f, 6.62f, 16.0f);
        pathBuilderG.horizontalLineToRelative(0.76f);
        pathBuilderG.lineTo(9.0f, 12.76f);
        pathBuilderG.lineTo(9.0f, 8.0f);
        a.h(pathBuilderG, 5.0f, 8.0f, 4.0f, 3.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderG, -2.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatQuote = imageVectorBuild;
        return imageVectorBuild;
    }
}

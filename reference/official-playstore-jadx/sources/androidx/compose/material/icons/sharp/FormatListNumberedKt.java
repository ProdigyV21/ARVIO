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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatListNumbered", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatListNumbered", "Landroidx/compose/material/icons/Icons$Sharp;", "getFormatListNumbered", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatListNumberedKt {
    private static ImageVector _formatListNumbered;

    public static final ImageVector getFormatListNumbered(Icons.Sharp sharp) {
        ImageVector imageVector = _formatListNumbered;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.FormatListNumbered", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(2.0f, 17.0f, 2.0f, 0.5f);
        a.h(pathBuilderA, 3.0f, 17.5f, 1.0f, 1.0f);
        d.r(pathBuilderA, 0.5f, 2.0f, 19.0f, 1.0f);
        c.q(pathBuilderA, 3.0f, -4.0f, 2.0f, 16.0f);
        c.r(pathBuilderA, 1.0f, 3.0f, 8.0f, 1.0f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        a.h(pathBuilderA, 2.0f, 4.0f, 1.0f, 1.0f);
        c.r(pathBuilderA, 3.0f, 2.0f, 11.0f, 1.8f);
        a.h(pathBuilderA, 2.0f, 13.1f, 0.9f, 3.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.lineTo(3.2f, 13.0f);
        pathBuilderA.lineTo(5.0f, 10.9f);
        pathBuilderA.lineTo(5.0f, 10.0f);
        d.f(pathBuilderA, 2.0f, 10.0f, 1.0f);
        c.m(pathBuilderA, 7.0f, 5.0f, 2.0f, 14.0f);
        b.y(pathBuilderA, 21.0f, 5.0f, 7.0f, 5.0f);
        a.C(pathBuilderA, 7.0f, 19.0f, 14.0f, -2.0f);
        d.f(pathBuilderA, 7.0f, 17.0f, 2.0f);
        a.C(pathBuilderA, 7.0f, 13.0f, 14.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderA, 7.0f, 11.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatListNumbered = imageVectorBuild;
        return imageVectorBuild;
    }
}

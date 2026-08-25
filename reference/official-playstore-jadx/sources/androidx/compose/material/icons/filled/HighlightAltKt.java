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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_highlightAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HighlightAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getHighlightAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HighlightAltKt {
    private static ImageVector _highlightAlt;

    public static final ImageVector getHighlightAlt(Icons.Filled filled) {
        ImageVector imageVector = _highlightAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HighlightAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(17.0f, 5.0f, -2.0f, 15.0f, 3.0f);
        b.B(pathBuilderI, 2.0f, 2.0f, 15.0f, 21.0f);
        c.q(pathBuilderI, 2.0f, -2.59f, 19.59f, 21.0f);
        pathBuilderI.lineTo(21.0f, 19.59f);
        pathBuilderI.lineTo(18.41f, 17.0f);
        a.h(pathBuilderI, 21.0f, 17.0f, -2.0f, -6.0f);
        c.r(pathBuilderI, 6.0f, 19.0f, 9.0f, 2.0f);
        b.g(pathBuilderI, 21.0f, 7.0f, -2.0f, 2.0f);
        a.C(pathBuilderI, 19.0f, 13.0f, 2.0f, -2.0f);
        b.B(pathBuilderI, -2.0f, 2.0f, 11.0f, 21.0f);
        y.a.r(pathBuilderI, 2.0f, -2.0f, -2.0f, 2.0f);
        pathBuilderI.moveTo(7.0f, 5.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.lineTo(9.0f, 3.0f);
        d.f(pathBuilderI, 7.0f, 3.0f, 2.0f);
        a.C(pathBuilderI, 3.0f, 17.0f, 2.0f, -2.0f);
        d.f(pathBuilderI, 3.0f, 15.0f, 2.0f);
        pathBuilderI.moveTo(5.0f, 21.0f);
        pathBuilderI.verticalLineToRelative(-2.0f);
        pathBuilderI.lineTo(3.0f, 19.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        c.y(pathBuilderI, 19.0f, 3.0f, 2.0f, 2.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.g(pathBuilderI, 11.0f, 5.0f, 2.0f);
        b.g(pathBuilderI, 13.0f, 3.0f, -2.0f, 2.0f);
        pathBuilderI.moveTo(3.0f, 9.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.lineTo(5.0f, 7.0f);
        d.f(pathBuilderI, 3.0f, 7.0f, 2.0f);
        a.C(pathBuilderI, 7.0f, 21.0f, 2.0f, -2.0f);
        d.f(pathBuilderI, 7.0f, 19.0f, 2.0f);
        a.C(pathBuilderI, 3.0f, 13.0f, 2.0f, -2.0f);
        d.f(pathBuilderI, 3.0f, 11.0f, 2.0f);
        pathBuilderI.moveTo(3.0f, 5.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.lineTo(5.0f, 3.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _highlightAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}

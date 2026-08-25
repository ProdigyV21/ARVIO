package androidx.compose.material.icons.outlined;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_css", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Css", "Landroidx/compose/material/icons/Icons$Outlined;", "getCss", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CssKt {
    private static ImageVector _css;

    public static final ImageVector getCss(Icons.Outlined outlined) {
        ImageVector imageVector = _css;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Css", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(9.5f, 14.0f, -1.0f, 11.0f, 0.5f);
        e.g(pathBuilderQ, 2.0f, -1.0f, -2.5f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(10.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(3.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        a.A(pathBuilderQ, 1.0f, 13.0f, -0.5f, -2.0f);
        pathBuilderQ.verticalLineToRelative(1.0f);
        pathBuilderQ.horizontalLineToRelative(2.5f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineTo(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-3.0f);
        pathBuilderQ.curveTo(9.95f, 15.0f, 9.5f, 14.55f, 9.5f, 14.0f);
        v.a.g(pathBuilderQ, 17.0f, 15.0f, 3.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-1.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.j(pathBuilderQ, -2.5f, -1.0f, 2.0f, 11.0f);
        pathBuilderQ.horizontalLineTo(21.0f);
        pathBuilderQ.verticalLineToRelative(-1.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-3.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(1.5f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a.j(pathBuilderQ, 2.5f, 1.0f, -2.0f, 13.0f);
        pathBuilderQ.horizontalLineTo(16.0f);
        pathBuilderQ.verticalLineToRelative(1.0f);
        pathBuilderQ.curveTo(16.0f, 14.55f, 16.45f, 15.0f, 17.0f, 15.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(8.0f, 10.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineTo(4.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(3.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.A(pathBuilderQ, -1.0f, 6.5f, 0.5f, -2.0f);
        c.p(pathBuilderQ, -3.0f, 2.0f, 11.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _css = imageVectorBuild;
        return imageVectorBuild;
    }
}

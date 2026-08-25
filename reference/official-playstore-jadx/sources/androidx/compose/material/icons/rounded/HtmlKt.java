package androidx.compose.material.icons.rounded;

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
import v.c;
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_html", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Html", "Landroidx/compose/material/icons/Icons$Rounded;", "getHtml", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HtmlKt {
    private static ImageVector _html;

    public static final ImageVector getHtml(Icons.Rounded rounded) {
        ImageVector imageVector = _html;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Html", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 15.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineTo(9.75f);
        pathBuilderA.curveTo(20.0f, 9.34f, 20.34f, 9.0f, 20.75f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderA.verticalLineToRelative(3.75f);
        pathBuilderA.horizontalLineToRelative(1.75f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        a.v(pathBuilderA, 21.0f, 16.0f, 10.49f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.76f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderA.verticalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(13.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.25f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        a.j(pathBuilderA, 10.5f, 1.0f, 2.75f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveTo(16.0f, 13.66f, 16.0f, 13.25f);
        f.r(pathBuilderA, 10.49f, 5.0f, 9.75f);
        pathBuilderA.curveTo(5.0f, 9.34f, 4.66f, 9.0f, 4.25f, 9.0f);
        pathBuilderA.reflectiveCurveTo(3.5f, 9.34f, 3.5f, 9.75f);
        c.i(pathBuilderA, 11.0f, -2.0f, 9.75f);
        pathBuilderA.curveTo(1.5f, 9.34f, 1.16f, 9.0f, 0.75f, 9.0f);
        pathBuilderA.reflectiveCurveTo(0.0f, 9.34f, 0.0f, 9.75f);
        pathBuilderA.verticalLineToRelative(4.5f);
        pathBuilderA.curveTo(0.0f, 14.66f, 0.34f, 15.0f, 0.75f, 15.0f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        a.j(pathBuilderA, 12.5f, 2.0f, 1.75f);
        pathBuilderA.curveTo(3.5f, 14.66f, 3.84f, 15.0f, 4.25f, 15.0f);
        pathBuilderA.reflectiveCurveTo(5.0f, 14.66f, 5.0f, 14.25f);
        f.r(pathBuilderA, 9.75f, 10.25f, 10.5f);
        pathBuilderA.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderA.curveTo(11.0f, 9.34f, 10.66f, 9.0f, 10.25f, 9.0f);
        pathBuilderA.horizontalLineToRelative(-3.5f);
        pathBuilderA.curveTo(6.34f, 9.0f, 6.0f, 9.34f, 6.0f, 9.75f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.verticalLineToRelative(3.75f);
        pathBuilderA.curveTo(7.75f, 14.66f, 8.09f, 15.0f, 8.5f, 15.0f);
        pathBuilderA.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderA, 10.5f, 10.25f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _html = imageVectorBuild;
        return imageVectorBuild;
    }
}
